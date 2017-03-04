<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%--<!-- Navigation -->--%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/system/login!main.action">主页</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>${sessionScope.user.userName}<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="/system/login!logout.action"><i class="fa fa-fw fa-power-off"></i>退出</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <c:if test="${sessionScope.user.userRight == 255}">
                <li>
                    <a href="/system/register!resInit.action"><i class="fa fa-fw fa-dashboard"></i>登陆账户管理</a>
                </li>
                <li>
                    <a href="/busys/main!custom.action"><i class="fa fa-fw fa-bar-chart-o"></i>客户管理</a>
                </li>
                <li>
                    <a href="/system/config!confInit.action"><i class="fa fa-fw fa-dashboard"></i>字典数据管理</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.user.userRight == 255 || sessionScope.user.userRight == 127}">
                <li>
                    <a href="/busys/main!tempCustomInput.action"><i class="fa fa-fw fa-table"></i>客户自助录入</a>
                </li>
            </c:if>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>