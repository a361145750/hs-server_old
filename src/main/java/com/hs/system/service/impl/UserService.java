package com.hs.system.service.impl;

import com.hs.common.model.BaseData;
import com.hs.system.dao.IUserDao;
import com.hs.system.service.IUserService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/5.
 */
@Repository
public class UserService implements IUserService {
    @Resource
    private IUserDao userDao;

    public JSONArray getUserByLoginId(BaseData data) {
        return userDao.getUserByLoginId(data);
    }

    public int addUser(BaseData data) {
        return userDao.addUser(data);
    }
}
