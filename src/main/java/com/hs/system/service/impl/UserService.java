package com.hs.system.service.impl;

import com.hs.common.model.BaseData;
import com.hs.common.service.impl.BaseService;
import com.hs.system.dao.IUserDao;
import com.hs.system.service.IUserService;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/5.
 */
@Repository
public class UserService extends BaseService implements IUserService {
    @Resource
    private IUserDao userDao;

    public int addUser(BaseData data) {
        JSONArray userJs = userDao.queryDataForJSONArray("userMapper.queryUserByLoginId", data);
        if (userJs != null && userJs.size() > 0) {
            return -1;
        }
        return userDao.addUser(data);
    }
}
