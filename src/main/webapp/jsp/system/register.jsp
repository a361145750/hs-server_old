<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><s:include value="/jsp/common/head.jsp" /></head>
<body>
<h2>欢迎使用客户管理系统！</h2>
<s:form action="register!register" namespace="/system">
<s:hidden name="userId" value="%{#session.user.userId}"></s:hidden>
    <table>
        <tr>
            <td class="label">登陆用户名:</td>
            <td><input class="easyui-textbox" name="loginId" required="true" /></td>
        </tr>
        <tr>
            <td class="label">昵称:</td>
            <td><input class="easyui-textbox" name="userName" required="true" /></td>
        </tr>
        <tr>
            <td class="label">密码:</td>
            <td><input class="easyui-textbox" type="password" name="passWord" required="true" /></td>
        </tr>
        <tr>
            <td class="label">确认密码:</td>
            <td><input class="easyui-textbox" type="password" name="passWord1" required="true" /></td>
        </tr>
        <tr>
            <td class="label">角色:</td>
            <td><select class="easyui-combobox" name="role" required="true">
                <option value="0">管理员</option>
                <option value="1">操作员</option>
            </select></td>
        </tr>
        <tr><td colspan="2" align="right"><s:submit value="注册"></s:submit></td></tr>
        <tr><td colspan="2" align="left><s:actionerror cssStyle="color: red"></s:actionerror></td></tr>
    </table>
</s:form>
</body>
</html>
