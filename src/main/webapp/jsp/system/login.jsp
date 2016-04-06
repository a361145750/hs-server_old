<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><s:include value="/jsp/common/head.jsp" /></head>
<body>
<h2>欢迎使用客户管理系统！</h2>
<s:form action="login!login" namespace="/system">
    <table>
        <tr>
            <td class="label">用户名:</td>
            <td><input class="easyui-textbox" name="loginId" required="true" /></td>
        </tr>
        <tr>
            <td class="label">密码:</td>
            <td><input class="easyui-textbox" type="password" name="passWord" required="true" /></td>
        </tr>
        <tr><td colspan="2" align="right"><s:submit value="登陆"></s:submit></td></tr>
        <tr><td colspan="2" align="left><s:actionerror cssStyle="color: red"></s:actionerror></td></tr>
    </table>
</s:form>
</body>
</html>
