package com.hs.system.dao.impl;


import com.hs.common.dao.impl.HsBaseDao;
import com.hs.common.model.BaseData;
import com.hs.system.dao.IUserDao;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Repository;

/**
 * Created by work_tl on 2016/4/4.
 */
@Repository
public class UserDao extends HsBaseDao implements IUserDao {

    public JSONArray getUserByLoginId(BaseData data) {
        return queryDataForJSONArray("userMapper.getUserByLoginId", data);
    }

    public int addUser(BaseData data) {
       return insertData("userMapper.insertUser", data);
    }
}
