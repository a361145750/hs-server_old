package com.hs.system.action;

import com.hs.common.action.HsBaseAction;
import com.hs.common.model.BaseData;
import com.hs.system.service.IUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/1.
 */
public class UserAction extends HsBaseAction {

    @Resource
    private IUserService loginService;

    public String init(){
        return "init";
    }

    public String resInit(){
        return "init";
    }

    public String login() throws Exception {
        BaseData data = this.getBaseData();
        String inputPass = data.getInputString("passWord");
        loginService.getUserByLoginId(data);
        JSONArray outUser = data.getOutputForJSONArray();
        if(outUser!=null && outUser.size() == 1){
            String dbPass = JSONObject.fromObject(outUser.get(0)).getString("passWord");
            if(inputPass.equals(dbPass)){
                this.session.put("user", JSONObject.fromObject(outUser.get(0)));
                return "success";
            }
            else{
                addActionError("用户密码不正确！");
            }
        }
        else{
            addActionError("用户不存在！");
        }
        return "input";
    }

    public String register() throws Exception {
        BaseData data = this.getBaseData();
        String inputPass = data.getInputString("passWord");
        String inputPass1 = data.getInputString("passWord1");
        if(!inputPass.equals(inputPass1))
        {
            addActionError("两次输入的密码不相同！");
        }
        data.getInput().put("userRight",127);
        int ret = loginService.addUser(data);
        if(ret > 0){
            addActionMessage("用户注册成功！");
            return "success";
        }
        else{
            addActionError("添加用户失败！");
        }
        return "input";
    }
}
