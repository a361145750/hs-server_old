<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>客户管理</title>
    <s:include value="/jsp/common/head.jsp" />
    <script type="text/javascript" src="/jsp/busys/js/custom.js"></script>
</head>
<body>
    <s:include value="/jsp/common/userHead.jsp" />
    <div>
    <table id="customDatagrid" class="easyui-datagrid" fitColumns="false" singleSelect="true" url="/busys/custom!getList.action"
           toolbar="#tb" pagination="true">
        <thead>
        <tr>
            <th field="customId" width="150">客户ID</th>
            <th field="cardId" width="100" >会员卡ID</th>
            <th field="userName" width="100" >称呼</th>
            <th field="phone" width="100" >电话</th>
            <th field="weixin" width="100" >微信</th>
            <th field="age" width="50">年龄</th>
            <th field="sex" width="50" formatter="sexFormat">性别</th>
            <th field="job" width="150">职业</th>
            <th field="birthday" width="120">生日</th>
            <th field="oprate" width="150" formatter="oprateFormat">操作</th>
        </tr>
        </thead>
    </table>
    </div>
    <div id="tb">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delUser()">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryUser()">查询</a>
    </div>
    <div style="display: none">
        <div id="queryWindow" class="easyui-window" title="查询" style="width:280px;height:235px"
             modal="true" closed="true">
            <form id="queryForm">
                <table>
                    <tr>
                        <td>客户ID</td>
                        <td><input class="easyui-validatebox" name="customId" id="customId" /></td>
                    </tr>
                    <tr>
                        <td>会员卡ID</td>
                        <td><input class="easyui-validatebox" name="cardId" id="cardId" /></td>
                    </tr>
                    <tr>
                        <td>称呼</td>
                        <td><input class="easyui-validatebox" name="userName" id="userName" /></td>
                    </tr>
                    <tr>
                        <td>电话</td>
                        <td><input class="easyui-validatebox" name="phone" id="phone" /></td>
                    </tr>
                    <tr>
                        <td>微信</td>
                        <td><input class="easyui-validatebox" name="weixin" id="weixin" /></td>
                    </tr>
                    <tr>
                        <td>生日</td>
                        <td><input class="easyui-datebox" name="birthday" id="birthday" data-options="formatter:myformatter,parser:myparser" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="queryCustomSubmit();"  iconCls='icon-search'>查询</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>
