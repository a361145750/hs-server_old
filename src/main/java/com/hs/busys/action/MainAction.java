package com.hs.busys.action;

import com.hs.common.action.BaseAction;

/**
 * Created by work_tl on 2016/4/1.
 */
public class MainAction extends BaseAction {

    public String custom(){
        httpServletRequest.setAttribute("title","客户信息管理");
        return "custom";
    }

    public String tempCustomInput() throws Exception {
        httpServletRequest.setAttribute("title","客户信息管理(洗发)");
        return "tempCustomInput";
    }
}
