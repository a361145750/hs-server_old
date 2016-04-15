package com.hs.common.dao;

import com.hs.common.model.BaseData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Created by work_tl on 2016/4/5.
 */
public interface IBaseDao {
    public JSONObject queryDataForJSONObject(String sqlname, BaseData data);

    public JSONArray queryDataForJSONArray(String sqlname, BaseData data);

    public int insertData(String sqlname, BaseData data);

    public int updateData(String sqlname, BaseData data);

    public int deleteData(String sqlname, BaseData data);
}
