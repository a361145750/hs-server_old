<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>客户编辑</title>
    <s:include value="/jsp/common/head.jsp" />
    <link rel="stylesheet" href="/jsp/common/webuploader-0.1.5/webuploader.css" type="text/css"></link>
    <script type="text/javascript" src="/jsp/common/webuploader-0.1.5/webuploader.min.js"></script>
    <script type="text/javascript" src="/jsp/busys/js/customEdit.js"></script>
</head>

<body>
<div id="wrapper">
    <%@ include  file="/jsp/common/userHead.jsp"%>
    <div id="page-wrapper" style="height: 100%">
        <div class="container-fluid">
            <form id="customForm" method="post">
                <input type="hidden" name="customOprate" id="customOprate" value="${oprate}" />
                <input type="hidden"  name="oprateUserId" id="oprateUserId" value="${user.userId}" />
                <input type="hidden"  name="customId" id="customId" value="${custom.customId}" />
                <div>
                    <div>
                        <table style="border-top-width: 0px; border-right-width: 0px;border-bottom-width: 1px;border-left-width: 1px;width:980px;height:118px;">
                            <tr>
                                <td class="table-lable" width="10%">会员卡号：</td>
                                <td width="15%"><input class="easyui-validatebox" id="cardId" name="cardId"  required="required" ${disabled} value="${custom.cardId}"/></td>
                                <td class="table-lable" width="10%">称呼：</td>
                                <td width="15%"><input class="easyui-validatebox" id="userName" name="userName"  required="required" ${disabled} value="${custom.userName}"/></td>
                                <td class="table-lable" width="10%">电话：</td>
                                <td width="15%"><input class="easyui-validatebox" id="phone" name="phone" ${disabled} value="${custom.phone}"/></td>
                                <td class="table-lable" width="10%">微信号：</td>
                                <td width="15%"><input class="easyui-textbox" id="weixin" name="weixin" ${disabled} value="${custom.weixin}"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable">年龄：</td>
                                <td width="15%"><input class="easyui-textbox" id="age" name="age" ${disabled} value="${custom.age}"/></td>
                                <td class="table-lable">性别：</td>
                                <td><input class="easyui-combobox" id="sex" name="sex" ${disabled} value="${custom.sex}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=SEX'"></td>
                                <td class="table-lable">职业：</td>
                                <td><input class="easyui-textbox" id="job" name="job" ${disabled} value="${custom.job}"/></td>
                                <td class="table-lable">性格：</td>
                                <td><input class="easyui-combobox" id="customCharacter" name="customCharacter" ${disabled} value="${custom.customCharacter}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=CHARACTER'"></td>
                            </tr>
                            <tr>
                                <td class="table-lable">发量：</td>
                                <td><input class="easyui-combobox" id="hairNumber" name="hairNumber" ${disabled} value="${custom.hairNumber}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_NUMBER'"></td>
                                <td class="table-lable">发长：</td>
                                <td><input class="easyui-combobox" id="hairLength" name="hairLength" ${disabled} value="${custom.hairLength}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_LENGTH'"></td>
                                <td class="table-lable">发质：</td>
                                <td colspan="3"><input class="easyui-combobox" id="hairQuality" name="hairQuality" ${disabled} value="${custom.hairQuality}"  data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_QUALITY'"></td>
                            </tr>
                            <tr>
                                <td class="table-lable">打理方式：</td>
                                <td><input class="easyui-combobox" id="hairOprateType" name="hairOprateType" ${disabled} value="${custom.hairOprateType}" data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_OPRATE_TYPE'"></td>
                                <td class="table-lable">打造方向：</td>
                                <td><input class="easyui-combobox" id="hairPrefer" name="hairPrefer" ${disabled} value="${custom.hairPrefer}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_PREFER'"></td>
                                <td class="table-lable">回避方向：</td>
                                <td><input class="easyui-combobox" id="hairExclude" name="hairExclude" ${disabled} value="${custom.hairExclude}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_EXCLUDE'"></td>
                                <td class="table-lable">做头发的周期：</td>
                                <td><input class="easyui-combobox" id="hairFrequency" name="hairFrequency" ${disabled} value="${custom.hairFrequency}"
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_FREQUENCY'"></td>
                            </tr>
                            <tr>
                                <td class="table-lable" width="10%">生日：</td>
                                <td width="15%"><input class="easyui-datebox" id="birthday" name="birthday" ${disabled} value="${custom.birthday}"
                                                       data-options="formatter:myformatter,parser:myparser"/></td>
                                <td class="table-lable">备注：</td>
                                <td colspan="5"><input class="easyui-textbox" id="comment" name="comment" ${disabled} value="${custom.comment}"></td>
                            </tr>
                        </table>
                    </div>
                    <hr>
                    <div>
                        客户历史记录:
                    </div>
                    <div>
                        <table id="customRecordDatagrid" class="easyui-datagrid" fitColumns="false" singleSelect="true" url="/busys/custom!getRecordList.action?customId=${custom.customId}"
                        <c:if  test="${disabled != 'disabled'}"> toolbar="#tb" </c:if>pagination="true">
                        <thead>
                        <tr>
                            <th field="oprate" width="100" formatter="oprateFormat">操作</th>
                            <th field="oprateDate" width="100" >日期</th>
                            <th field="disinerName" width="100" >设计师</th>
                            <th field="disinTypeName" width="100"  >操作类型</th>
                            <th field="totalFee" width="100">价格</th>
                            <th field="hairLevelName" width="100"  >头发层次</th>
                            <th field="hairLengthName" width="100" >头发长度</th>
                            <th field="hairBarName" width="150" >头发杠子</th>
                            <th field="hairPermBrandName" width="150" >烫发品牌</th>
                            <th field="hairDyeBrandName" width="150" >染发品牌</th>
                            <th field="hairCairBrandName" width="150" >护理品牌</th>
                        </tr>
                        </thead>
                        </table>
                        <c:if  test="${disabled != 'disabled'}">
                            <div id="tb">
                                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRecord()">添加</a>
                                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRecord()">编辑</a>
                                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRecord()">删除</a>
                                <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryRecord()">查询</a>
                            </div>
                        </c:if>
                    </div>
                    <hr>
                    <c:if  test="${disabled != 'disabled'}">
                        <div>
                            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="saveCustomSubmit();"  iconCls='icon-search'>保存</a>
                            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="cancelCustom();"  iconCls='icon-cancel'>取消</a>
                        </div>
                    </c:if>
                </div>
            </form>
            <div style="display: none">
                <div id="editWindow" class="easyui-window" title="编辑" style="width:630px;height:600px"
                     modal="true" closed="true">
                    <form id="recordForm" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="recordOUserId" id="recordOUserId" value="${user.userId}" />
                        <input type="hidden" name="recordOprate" id="recordOprate" />
                        <input type="hidden"  name="recordId" id="recordId" />
                        <input type="hidden"  name="attchI" id="attchI" />
                        <input type="hidden"  name="attchD" id="attchD" />
                        <table style="table-layout:fixed;">
                            <tr>
                                <td class="table-lable" style="width:135px;">日期:</td>
                                <td style="width:250px;word-wrap:break-word;"><input style="width:220px;" class="easyui-datebox" id="oprateDate" name="oprateDate" required="required"  ${disabled}
                                                                                     data-options="formatter:myformatter2,parser:myparser2"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">设计师:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="disinerId" id="disinerId" ${disabled}
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=DISINER'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">操作类型:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="disinType" id="disinType" required="required" ${disabled}
                                           data-options="multiple:true,valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=DISIN_TYPE'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">价格:</td>
                                <td><input style="width:220px;" class="easyui-numberbox" name="totalFee" id="totalFee" ${disabled}/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">头发层次:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairLevel" id="hairLevel" ${disabled}
                                           data-options="multiple:true,valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_LEVEL'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">头发长度:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairLength" id="hairLength" ${disabled}
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_LENGTH'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">头发杠子:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairBar" id="hairBar" ${disabled}
                                           data-options="multiple:true,valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_BAR'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">烫发品牌:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairPermBrand" id="hairPermBrand" ${disabled}
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_PERM_BRAND'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">染发品牌:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairDyeBrand" id="hairDyeBrand" ${disabled}
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_DYE_BRAND'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">护理品牌:</td>
                                <td><input style="width:220px;" class="easyui-combobox" name="hairCairBrand" id="hairCairBrand" ${disabled}
                                           data-options="valueField:'confCode',textField:'confName',url:'/system/config!getConfigType.action?confType=HAIR_CAIR_BRAND'"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">客人有不喜欢的<br/>卷度或颜色等:</td>
                                <td><input style="width:300px;height: 100px;" class="easyui-textbox" name="hairHateItems" id="hairHateItems" multiline="true" ${disabled}
                                           style="height:66px;width:170px;"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">顾客要求要点:</td>
                                <td><input style="width:300px;height: 100px;" class="easyui-textbox" name="customRequireItems" id="customRequireItems" multiline="true" ${disabled}
                                           style="height:66px;width:170px;"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable" style="width:135px;">技术操作要点:</td>
                                <td><input style="width:300px;height: 100px;" class="easyui-textbox" name="oprateNoticeItems" id="oprateNoticeItems" multiline="true" ${disabled}
                                           style="height:66px;width:170px;"/></td>
                            </tr>
                            <tr>
                                <td class="table-lable">图片:</td>
                                <td style="text-align: left;">
                                    <input style="width:242px;" class="easyui-filebox" id="file" name='file'  ${disabled} data-options="prompt:'请选择文件...', buttonText:'选择文件', buttonAlign:'right',onChange:fileChange, accept:'image/*', multiple:'multiple'"/>
                                    <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="uploadFile();"   ${disabled} iconCls='icon-save'>上传</a>
                                </td>
                            </tr>
                            <tr>
                                <td class="table-lable"></td>
                                <td id="imgDiv"  ${disabled}>
                                </td>
                            </tr>

                            <c:if  test="${disabled != 'disabled'}">
                                <tr>
                                    <td colspan="2" align="left">
                                        <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="submitRecord();"  iconCls='icon-ok'>确认</a>
                                        <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="canRecord();"  iconCls='icon-cancel'>取消</a>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </form>
                </div>
            </div>
            <div style="display: none">
                <div id="imgWindow" class="easyui-window" modal="true" closed="true">
                    <div id="imgDiv1" name="imgDiv1"></div>
                </div>
            </div>


        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->
</div>
</body>
</html>
