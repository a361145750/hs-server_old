package com.hs.system.dao.impl;


import com.hs.common.dao.impl.BaseDao;
import com.hs.common.model.BaseData;
import com.hs.system.dao.IUserDao;
import org.springframework.stereotype.Repository;

/**
 * Created by work_tl on 2016/4/4.
 */
@Repository
public class UserDao extends BaseDao implements IUserDao {

    public int addUser(BaseData data) {
       return insertData("userMapper.insertUser", data);
    }
}
