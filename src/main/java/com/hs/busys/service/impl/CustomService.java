package com.hs.busys.service.impl;

import com.hs.busys.dao.ICustomDao;
import com.hs.busys.service.ICustomService;
import com.hs.common.model.BaseData;
import com.hs.common.service.impl.BaseService;
import com.hs.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/17.
 */
@Repository
public class CustomService extends BaseService implements ICustomService{

    @Resource
    ICustomDao customDao;

    @Override
    public int addCustom(BaseData baseData) throws Exception {
//        baseData.addInput("customId", NumberSequenceUtil.getCustomNum());
        customDao.insertData("customMapper.insertCustom", baseData);
        JSONArray insert = JSONArray.fromObject(baseData.getInput().get("insert"));
        if(insert!=null && insert.size() > 0){
            for (int i=0;i<insert.size();i++) {
                JSONObject insertData = insert.getJSONObject(i);
                if(insertData.isNullObject()){
                    continue;
                }
                BaseData baseDataI = new BaseData();
                baseDataI.addInput(insertData);
//                baseDataI.addInput("recordId", NumberSequenceUtil.getRecordNum());
                baseDataI.addInput("customId", baseData.getInputString("customId"));
                customDao.insertData("customMapper.insertRecord",baseDataI);
                JSONArray attchI = JSONArray.fromObject(baseDataI.getInput().get("attchI"));
                if(attchI!=null && attchI.size() > 0){
                    for (int j = 0; j < attchI.size(); j++) {
                        JSONObject attch = attchI.getJSONObject(i);
                        if(attch.isNullObject()){
                            continue;
                        }
                        BaseData attchDataI = new BaseData();
                        attchDataI.addInput(attch);
                        customDao.insertData("customMapper.insertRecordAttch",attchDataI);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int updateCustom(BaseData baseData) throws Exception {
        customDao.updateData("customMapper.updateCustom", baseData);
        String customId = baseData.getInputString("customId");

        JSONArray insert = JSONArray.fromObject(baseData.getInput().get("insert"));
        if(insert!=null && insert.size() > 0){
            for (int i=0;i<insert.size();i++) {
                JSONObject insertData = insert.getJSONObject(i);
                if(insertData.isNullObject()){
                    continue;
                }
                BaseData baseDataI = new BaseData();
                baseDataI.addInput(insertData);
//                baseDataI.addInput("recordId", NumberSequenceUtil.getRecordNum());
                baseDataI.addInput("customId", customId);
                customDao.insertData("customMapper.insertRecord",baseDataI);
                JSONArray attchI = JSONArray.fromObject(baseDataI.getInput().get("attchI"));
                if(attchI!=null && attchI.size() > 0){
                    for (int j = 0; j < attchI.size(); j++) {
                        JSONObject attch = attchI.getJSONObject(j);
                        if(attch.isNullObject()){
                            continue;
                        }
                        BaseData attchDataI = new BaseData();
                        attchDataI.addInput(attch);
                        customDao.insertData("customMapper.insertRecordAttch",attchDataI);
                    }
                }
            }
        }
        JSONArray update = JSONArray.fromObject(baseData.getInput().get("update"));
        if(update!=null && update.size() > 0){
            for (int i=0;i<update.size();i++) {
                JSONObject updateData = update.getJSONObject(i);
                if(updateData.isNullObject()){
                    continue;
                }
                BaseData baseDataU = new BaseData();
                baseDataU.addInput(updateData);
                baseDataU.addInput("customId", customId);
                customDao.updateData("customMapper.updateRecord",baseDataU);
                JSONArray attchI = JSONArray.fromObject(baseDataU.getInput().get("attchI"));
                if(attchI!=null && attchI.size() > 0){
                    for (int j = 0; j < attchI.size(); j++) {
                        JSONObject attch = attchI.getJSONObject(j);
                        if(attch.isNullObject()){
                            continue;
                        }
                        BaseData attchDataI = new BaseData();
                        attchDataI.addInput(attch);
                        customDao.insertData("customMapper.insertRecordAttch",attchDataI);
                    }
                }
                JSONArray attchD = JSONArray.fromObject(baseDataU.getInput().get("attchD"));
                if(attchD!=null && attchD.size() > 0){
                    for (int j = 0; j < attchD.size(); j++) {
                        JSONObject attch = attchD.getJSONObject(j);
                        if(attch.isNullObject()){
                            continue;
                        }
                        BaseData attchDataD = new BaseData();
                        attchDataD.addInput(attch);
                        customDao.deleteData("customMapper.deleteRecordAttch",attchDataD);
                    }
                }
            }
        }
        JSONArray delete = JSONArray.fromObject(baseData.getInput().get("delete"));
        if(delete!=null && delete.size() > 0){
            for (int i=0;i<delete.size();i++) {
                JSONObject deleteData = delete.getJSONObject(i);
                if(deleteData.isNullObject()){
                    continue;
                }
                BaseData baseDataD = new BaseData();
                baseDataD.addInput(deleteData);
                baseDataD.addInput("customId", customId);
                customDao.deleteData("customMapper.deleteRecord",baseDataD);
                customDao.deleteData("customMapper.deleteRecordAttch",baseDataD);
                FileUtil.deleteAttchByRecord(baseDataD);
            }
        }
        return 0;
    }

    @Override
    public int deleteCustom(BaseData baseData) throws Exception {
        customDao.deleteData("customMapper.deleteCustom", baseData);
        customDao.deleteData("customMapper.deleteRecord",baseData);
        customDao.deleteData("customMapper.deleteRecordAttch",baseData);
        return 0;
    }

    @Override
    public int addRecord(BaseData baseData) throws Exception {
        customDao.insertData("customMapper.insertRecord",baseData);
        JSONArray attchI = JSONArray.fromObject(baseData.getInput().get("attchI"));
        if(attchI!=null && attchI.size() > 0){
            for (int j = 0; j < attchI.size(); j++) {
                JSONObject attch = attchI.getJSONObject(j);
                if(attch.isNullObject()){
                    continue;
                }
                BaseData attchDataI = new BaseData();
                attchDataI.addInput(attch);
                customDao.insertData("customMapper.insertRecordAttch",attchDataI);
            }
        }
        return 0;
    }

    @Override
    public int updateRecord(BaseData baseData) throws Exception {
        customDao.updateData("customMapper.updateRecord",baseData);
        JSONArray attchI = JSONArray.fromObject(baseData.getInput().get("attchI"));
        if(attchI!=null && attchI.size() > 0){
            for (int j = 0; j < attchI.size(); j++) {
                JSONObject attch = attchI.getJSONObject(j);
                if(attch.isNullObject()){
                    continue;
                }
                BaseData attchDataI = new BaseData();
                attchDataI.addInput(attch);
                customDao.insertData("customMapper.insertRecordAttch",attchDataI);
            }
        }
        JSONArray attchD = JSONArray.fromObject(baseData.getInput().get("attchD"));
        if(attchD!=null && attchD.size() > 0){
            for (int j = 0; j < attchD.size(); j++) {
                JSONObject attch = attchD.getJSONObject(j);
                if(attch.isNullObject()){
                    continue;
                }
                BaseData attchDataD = new BaseData();
                attchDataD.addInput(attch);
                customDao.deleteData("customMapper.deleteRecordAttch",attchDataD);
            }
        }
        return 0;
    }

    @Override
    public int deleteRecord(BaseData baseData) throws Exception {
        customDao.deleteData("customMapper.deleteRecord",baseData);
        customDao.deleteData("customMapper.deleteRecordAttch",baseData);
        return 0;
    }

}
