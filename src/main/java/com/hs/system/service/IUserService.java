package com.hs.system.service;

import com.hs.common.model.BaseData;
import net.sf.json.JSONArray;

/**
 * Created by work_tl on 2016/4/5.
 */
public interface IUserService {
    public JSONArray getUserByLoginId(BaseData data);

    public int addUser(BaseData data);
}
