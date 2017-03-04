package com.hs.busys.service;

import com.hs.common.model.BaseData;
import com.hs.common.service.IBaseService;

/**
 * Created by work_tl on 2016/4/17.
 */
public interface ICustomService extends IBaseService {
    public int addCustom(BaseData baseData) throws Exception;
    public int updateCustom(BaseData baseData) throws Exception;
    public int deleteCustom(BaseData baseData) throws Exception;
    public int addRecord(BaseData baseData) throws Exception;
    public int updateRecord(BaseData baseData) throws Exception;
    public int deleteRecord(BaseData baseData) throws Exception;
}
