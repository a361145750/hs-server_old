package com.hs.system.service;

import com.hs.common.model.BaseData;
import com.hs.common.service.IBaseService;

/**
 * Created by work_tl on 2016/4/5.
 */
public interface IUserService extends IBaseService {
    public int addUser(BaseData data);
}
