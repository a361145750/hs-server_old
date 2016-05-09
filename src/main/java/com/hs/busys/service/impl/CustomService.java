package com.hs.busys.service.impl;

import com.hs.busys.dao.ICustomDao;
import com.hs.busys.service.ICustomService;
import com.hs.common.model.BaseData;
import com.hs.common.service.impl.BaseService;
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
                BaseData baseDataI = new BaseData();
                baseDataI.addInput(insertData);
//                baseDataI.addInput("recordId", NumberSequenceUtil.getRecordNum());
                baseDataI.addInput("customId", baseData.getInputString("customId"));
                customDao.insertData("customMapper.insertRecord",baseDataI);
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
                BaseData baseDataI = new BaseData();
                baseDataI.addInput(insertData);
//                baseDataI.addInput("recordId", NumberSequenceUtil.getRecordNum());
                baseDataI.addInput("customId", customId);
                customDao.insertData("customMapper.insertRecord",baseDataI);
            }
        }
        JSONArray update = JSONArray.fromObject(baseData.getInput().get("update"));
        if(update!=null && update.size() > 0){
            for (int i=0;i<update.size();i++) {
                JSONObject updateData = update.getJSONObject(i);
                BaseData baseDataR = new BaseData();
                baseDataR.addInput(updateData);
                baseDataR.addInput("customId", customId);
                customDao.updateData("customMapper.updateRecord",baseDataR);
            }
        }
        JSONArray delete = JSONArray.fromObject(baseData.getInput().get("delete"));
        if(delete!=null && delete.size() > 0){
            for (int i=0;i<delete.size();i++) {
                JSONObject deleteData = delete.getJSONObject(i);
                BaseData baseDataD = new BaseData();
                baseDataD.addInput(deleteData);
                baseDataD.addInput("customId", customId);
                customDao.deleteData("customMapper.deleteRecord",baseDataD);
            }
        }
        return 0;
    }

    @Override
    public int deleteCustom(BaseData baseData) throws Exception {
        customDao.deleteData("customMapper.deleteCustom", baseData);
        customDao.deleteData("customMapper.deleteRecord",baseData);
        return 0;
    }
}
