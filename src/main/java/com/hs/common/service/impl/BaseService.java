package com.hs.common.service.impl;

import com.hs.common.dao.IBaseDao;
import com.hs.common.model.BaseData;
import com.hs.common.service.IBaseService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/6.
 */
@Repository
public class BaseService implements IBaseService{

    @Resource
    private IBaseDao baseDao;

    public JSONArray queryDataForJSONArray(String sqlname, BaseData data) {
        return baseDao.queryDataForJSONArray(sqlname, data);
    }

    public JSONObject queryDataForJSONObject(String sqlname, BaseData data) {
        return baseDao.queryDataForJSONObject(sqlname, data);
    }

    public int insertData(String sqlname, BaseData data) {
        return baseDao.insertData(sqlname, data);
    }

    public int updateData(String sqlname, BaseData data) {
        return baseDao.updateData(sqlname, data);
    }

    public int deleteData(String sqlname, BaseData data) {
        return baseDao.deleteData(sqlname, data);
    }
}
