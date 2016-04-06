package com.hs.busys.action;

import com.hs.system.dao.IUserDao;
import com.opensymphony.xwork2.ActionSupport;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/1.
 */
public class MainAction extends ActionSupport {

    @Resource
    private IUserDao userDao;

    public String customManage(){
        return "customManage";
    }

    public String tempCustomInput() throws Exception {
        return "tempCustomInput";
    }
}
