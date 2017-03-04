<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head><s:include value="/jsp/common/head.jsp" /></head>
<style>
    .form-signin{
        max-width: 380px;
        padding: 15px;
        margin: 0 auto;
    }
    input {
        margin-bottom: 3px;
    }
</style>
<body style="background-image: url('/jsp/system/img/bg.jpg')">
<div class="container">
<form class="form-signin" role="form" action="login!login.action" namespace="/system" method="post">
    <h2>欢迎使用客户管理系统！</h2>
    <input type="text" class="form-control" name="loginId" placeholder="用户名" required autofocus />
    <input type="password" class="form-control" name="passWord" placeholder="密码" required />
    <button type="submit" class="btn btn-lg btn-primary btn-block">登陆</button>
    <s:actionerror cssStyle="color: red"></s:actionerror>
</form>
</div>
</body>
</html>