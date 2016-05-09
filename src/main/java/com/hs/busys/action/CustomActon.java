package com.hs.busys.action;

import com.hs.busys.service.ICustomService;
import com.hs.common.action.BaseAction;
import com.hs.common.model.BaseData;
import com.hs.util.FileUtil;
import com.hs.util.NumberSequenceUtil;
import com.hs.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by work_tl on 2016/4/17.
 */
public class CustomActon extends BaseAction{

    @Resource
    ICustomService customService;
    private List<File> file;
    private List<String> fileContentType;
    private List<String> fileFileName;

    public List<File> getFile() {
        return file;
    }

    public void setFile(List<File> file) {
        this.file = file;
    }

    public List<String> getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(List<String> fileContentType) {
        this.fileContentType = fileContentType;
    }

    public List<String> getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(List<String> fileFileName) {
        this.fileFileName = fileFileName;
    }

    public void getList() throws Exception{
        BaseData data = this.getBaseData();
        queryDataForJSONArrayPage(customService, "customMapper.queryCustom", data);
        this.flushOutput();
    }

    public void getRecordList() throws Exception{
        BaseData data = this.getBaseData();
        queryDataForJSONArrayPage(customService, "customMapper.queryRecord", data);

        BaseData attchData = new BaseData();
        attchData.addInput("customId",data.getInputString("customId"));
        customService.queryDataForJSONArray("customMapper.queryRecordAttch", attchData);
        JSONArray attchArray = attchData.getOutputForJSONArray();
        Map<String,JSONArray> map = new HashMap<String,JSONArray>();
        if(attchArray!=null){
            for (int i = 0; i < attchArray.size(); i++) {
                JSONObject attchObject = attchArray.getJSONObject(i);
                String recordId = attchObject.getString("recordId");
                if(!StringUtil.isEmpty(recordId)){
                    if(!map.containsKey(recordId)){
                        map.put(recordId, new JSONArray());
                    }
                    map.get(recordId).add(attchObject);
                }
            }
        }
        JSONObject recordObject = data.getOutputForJSONObject();
        if(recordObject!=null){
            JSONArray records = recordObject.getJSONArray("rows");
            for (int i = 0; i < records.size(); i++) {
                JSONObject record = records.getJSONObject(i);
                String recordId = record.getString("recordId");
                if(!StringUtil.isEmpty(recordId)){
                    if( map.containsKey(recordId)){
                        record.put("attchs",map.get(recordId));
                    } else {
                        record.put("attchs",new JSONArray());
                    }
                }
            }
        }
        this.flushOutput();
    }

    public String newCustom() throws Exception{
        httpServletRequest.setAttribute("title","新增客户信息");
        httpServletRequest.setAttribute("oprate","newCustom");
        JSONObject data = new JSONObject();
        data.put("customId", NumberSequenceUtil.getCustomNum());
        httpServletRequest.setAttribute("custom", data);
        deleteUnUseFile("");
        return "newCustom";
    }

    public String editCustom() throws Exception{
        httpServletRequest.setAttribute("title","编辑客户信息");
        httpServletRequest.setAttribute("oprate","editCustom");
        BaseData data = this.getBaseData();
        customService.queryDataForJSONObject("customMapper.queryCustom", data);
        httpServletRequest.setAttribute("custom", data.getOutputForJSONObject());
        deleteUnUseFile("");
        return "editCustom";
    }

    public String showCustom() throws Exception{
        httpServletRequest.setAttribute("title","查看客户信息");
        httpServletRequest.setAttribute("oprate","showCustom");
        BaseData data = this.getBaseData();
        customService.queryDataForJSONObject("customMapper.queryCustom", data);
        httpServletRequest.setAttribute("custom", data.getOutputForJSONObject());
        httpServletRequest.setAttribute("readOnly", data.getInputString("readOnly"));
        return "editCustom";
    }

    public void getRecordId() throws Exception{
        BaseData data = this.getBaseData();
        JSONObject retObject = new JSONObject();
        retObject.put("recordId", NumberSequenceUtil.getRecordNum());
        data.setOutput(retObject);
        this.flushOutput();
    }

    public void getAttchId() throws Exception{
        BaseData data = this.getBaseData();
        JSONObject retObject = new JSONObject();
        retObject.put("attchId", NumberSequenceUtil.getAttchNum());
        data.setOutput(retObject);
        this.flushOutput();
    }

    public void addCustom() throws Exception{
        BaseData data = this.getBaseData();
        customService.addCustom(data);
        this.flushOutput();
    }

    public void updateCustom() throws Exception{
        BaseData data = this.getBaseData();
        customService.updateCustom(data);
        this.flushOutput();
    }

    public void delCustom() throws Exception{
        BaseData data = this.getBaseData();
        customService.deleteCustom(data);
        FileUtil.deleteAttchByCustom(data);
        this.flushOutput();
    }

    public void uploadFile() throws Exception{
        BaseData baseData = this.getBaseData();
        JSONArray jsonArray = new JSONArray();

        if (this.file != null && this.file.size()>0) {
            for (int i = 0; i < this.file.size(); i++) {
                File file = this.file.get(i);
                FileInputStream fis = new FileInputStream(file);
                BufferedImage bufferedImg = ImageIO.read(fis);
                int imgWidth = bufferedImg.getWidth();
                int imgHeight = bufferedImg.getHeight();
                String fileName = this.fileFileName.get(i);
                String attchFileName = FileUtil.creatAttchFileName(baseData, fileName);
                FileUtil.copyFile(file.getAbsolutePath(), this.httpServletRequest.getSession().getServletContext().getRealPath("/") + attchFileName);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("recordAttchId",NumberSequenceUtil.getAttchNum());
                jsonObject.put("recordId",baseData.getInputString("recordId"));
                jsonObject.put("customId",baseData.getInputString("customId"));
                jsonObject.put("fileName",fileName);
                jsonObject.put("filePath",attchFileName);
                jsonObject.put("fileSize",imgWidth + "x" + imgHeight);
                jsonArray.add(jsonObject);
            }
        }
        baseData.setOutput(jsonArray);
        this.flushOutput();
    }

    public void deleteFile() throws Exception{
        BaseData baseData = this.getBaseData();
        String recordAttchId = baseData.getInputString("recordAttchId");
        String filePath = baseData.getInputString("filePath");

        if (!StringUtil.isEmpty(filePath)) {
            File file = new File(this.httpServletRequest.getSession().getServletContext().getRealPath("/") + filePath);
            file.delete();
        }
    }

    public void deleteUnUseFile(String customID){
        BaseData data = new BaseData();
        data.addInput("", "");
        customService.queryDataForJSONArray("customMapper.queryRecordAttch", data);

        JSONArray attchArray = data.getOutputForJSONArray();
        List<String> attchList = new ArrayList<String>();
        if(attchArray!=null && attchArray.size() > 0){
            for (int i = 0; i < attchArray.size(); i++) {
                JSONObject attch = attchArray.getJSONObject(i);
                String filePath = attch.getString("filePath");
                attchList.add(filePath.substring(filePath.lastIndexOf("/") +1));
            }
        }
        List<File> fileNames = new ArrayList<File>();
        getFiles(this.httpServletRequest.getSession().getServletContext().getRealPath("/") + FileUtil.FILE_UPLOAD_PATH, fileNames);
        for(int i=0;i<fileNames.size();i++){
            File file = fileNames.get(i);
            String filePath = file.getPath();
            if(!attchList.contains(filePath.substring(filePath.lastIndexOf("\\") +1)))
            {
                file.delete();
            }
        }
    }

    public void getFiles(String path, List<File> fileNames){
        if(!StringUtil.isEmpty(path)){
            if (fileNames == null) {
                fileNames = new ArrayList<File>();
            }
            File oriFile = new File(path);
            File[] files = oriFile.listFiles();
            if (files == null) {
                return;
            }
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file);
                } else {
                    getFiles(file.getPath(), fileNames);
                }
            }
        }
    }
}
