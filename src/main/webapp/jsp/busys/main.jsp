<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><s:include value="/jsp/common/head.jsp" /></head>
<body>
<s:include value="/jsp/common/userHead.jsp" />
    <table>
        <tr>
            <td><a href="/busys/main!customManage.action" >客户管理</td>
        </tr>
        <tr>
            <td><a href="/busys/main!tempCustomInput.action" >客户自助录入</td>
        </tr>
        <s:if test="%{#session.user.userRight == 255}">
        <tr>
            <td><a href="/system/register!resInit.action" >登陆账户管理</a></td>
        </tr>
        </s:if>
    </table>
</body>
</html>
