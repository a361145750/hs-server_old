package com.hs.system.action;

import com.hs.common.action.BaseAction;
import com.hs.common.model.BaseData;
import com.hs.system.service.IUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;

/**
 * Created by work_tl on 2016/4/1.
 */
public class UserAction extends BaseAction {

    @Resource
    private IUserService userService;

    public String init(){
        return "init";
    }

    public String resInit(){
        httpServletRequest.setAttribute("title","登陆账户管理");
        return "resInit";
    }

    public String login() throws Exception {
        BaseData data = this.getBaseData();
        String inputPass = data.getInputString("passWord");
        userService.queryDataForJSONArray("userMapper.queryUserByLoginId", data);
        JSONArray outUser = data.getOutputForJSONArray();
        if(outUser!=null && outUser.size() == 1){
            String dbPass = JSONObject.fromObject(outUser.get(0)).getString("passWord");
            if(inputPass.equals(dbPass)){
                this.session.put("user", JSONObject.fromObject(outUser.get(0)));
                httpServletRequest.setAttribute("title","主页");
                return "login";
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

    public String main() throws Exception{
        if(this.session.containsKey("user")){
            httpServletRequest.setAttribute("title","主页");
            return "main";
        }
        return "logout";
    }

    public String logout() throws Exception{
        this.session.clear();
        return "logout";
    }

    public String register() throws Exception {
        BaseData data = this.getBaseData();
        String inputPass = data.getInputString("passWord");
        String inputPass1 = data.getInputString("passWord1");
        if(!inputPass.equals(inputPass1))
        {
            addActionError("两次输入的密码不相同！");
        }
        data.addInput("userRight",127);
        int ret = userService.addUser(data);
        if(ret == 1){
            addActionMessage("用户注册成功！");
            httpServletRequest.setAttribute("title","主页");
            return "success";
        }
        if(ret == -1){
            addActionError("用户已经存在！");
        }
        else{
            addActionError("添加用户失败！");
        }
        return "input";
    }

    public void getList() throws Exception{
        BaseData data = this.getBaseData();
        queryDataForJSONArrayPage(userService, "userMapper.queryUser", data);
        this.flushOutput();
    }

    public void checkLoginId() throws Exception {
        BaseData data = this.getBaseData();
        userService.queryDataForJSONArray("userMapper.queryUserByLoginId", data);
        this.flushOutput();
    }

    public void addUser() throws Exception {
        BaseData data = this.getBaseData();
        userService.insertData("userMapper.insertUser", data);
        this.flushOutput();
    }

    public void updateUser() throws Exception {
        BaseData data = this.getBaseData();
        userService.updateData("userMapper.updateUser", data);
        this.flushOutput();
    }

    public void delUser() throws Exception {
        BaseData data = this.getBaseData();
        userService.deleteData("userMapper.deleteUser", data);
        this.flushOutput();
    }
}
