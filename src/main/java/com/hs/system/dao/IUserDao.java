package com.hs.system.dao;

import com.hs.common.dao.IBaseDao;
import com.hs.common.model.BaseData;

/**
 * Created by work_tl on 2016/4/4.
 */
public interface IUserDao extends IBaseDao{
    public int addUser(BaseData data);
}
