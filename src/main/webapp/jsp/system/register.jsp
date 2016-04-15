<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>登录用户管理</title>
    <s:include value="/jsp/common/head.jsp" />
    <script type="text/javascript" src="/jsp/system/js/register.js"></script>
</head>
<body>
    <s:include value="/jsp/common/userHead.jsp" />
    <table id="userDatagrid" class="easyui-datagrid" fitColumns="false" singleSelect="true" url="/system/register!getList.action"
           toolbar="#tb" pagination="true">
        <thead>
        <tr>
            <th field="userId" width="150">用户ID</th>
            <th field="loginId" width="150" >用户名</th>
            <th field="userName" width="150" >昵称</th>
            <th field="role" width="150" formatter="roleFormat">角色</th>
            <th field="userRight" width="150" formatter="rightFormat">权限</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUser()">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryUser()">查询</a>
    </div>

    <div style="display: none">
        <div id="editWindow" class="easyui-window" title="编辑" style="width:290px;height:205px"
             modal="true" closed="true">
        <form id="editForm">
        <s:hidden name="oprateUserId" id="oprateUserId" value="%{#session.user.userId}"></s:hidden>
        <s:hidden name="userId" id="userId"></s:hidden>
        <s:hidden name="userRight" id="userRight"></s:hidden>
        <s:hidden name="oprate" id="oprate"></s:hidden>
        <table>
            <tr>
                <td class="label">登陆用户名:</td>
                <td><input class="easyui-validatebox" name="loginId" id="loginId" required="required" /></td>
            </tr>
            <tr>
                <td class="label">昵称:</td>
                <td><input class="easyui-validatebox" name="userName" id="userName" required="required"/></td>
            </tr>
            <tr>
                <td class="label">密码:</td>
                <td><input class="easyui-validatebox" type="password" name="passWord" id="passWord" required="required"/></td>
            </tr>
            <tr>
                <td class="label">确认密码:</td>
                <td><input class="easyui-validatebox" type="password" name="passWord1" id="passWord1" required="required" validType="equals['#passWord']"/></td>
            </tr>
            <tr>
                <td class="label">角色:</td>
                <td><select class="easyui-combobox" name="role" id="role"  required="true">
                    <option value="1">管理员</option>
                    <option value="0">操作员</option>
                </select></td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="submitUser();"  iconCls='icon-ok'>确认</a>
                    <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="canUser();"  iconCls='icon-cancel'>取消</a>
                </td>
            </tr>
        </table>
        </form>
        </div>
    </div>

</body>
</html>
