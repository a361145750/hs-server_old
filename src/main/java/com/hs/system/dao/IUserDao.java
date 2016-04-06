package com.hs.system.dao;

import com.hs.common.model.BaseData;
import net.sf.json.JSONArray;

/**
 * Created by work_tl on 2016/4/4.
 */
public interface IUserDao {
    public JSONArray getUserByLoginId(BaseData data);

    public int addUser(BaseData data);
}
