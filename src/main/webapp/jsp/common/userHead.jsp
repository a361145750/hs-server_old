<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: work_tl
  Date: 2016/4/14
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div style="width:30%;text-align: left;float:left;background:#E0EEEE;"><b><s:property value="#request.title"></s:property>&nbsp;</b></div>
<div style="width:70%;text-align: right;float:left;background:#E0EEEE;">
    <a href="/system/login!main.action">主页</a>
    <a href="/system/login!logout.action">退出</a>
    <span>当前用户：<s:property value="%{#session.user.userName}"></s:property></span>
</div>
<hr>
