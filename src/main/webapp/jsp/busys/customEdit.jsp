<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#assign disabled="${readOnly!}"]>
-->
<html>
<head>
    <@s.include value="/jsp/common/head.jsp" />
    <script type="text/javascript" src="/jsp/busys/js/customEdit.js"></script>
    <title>客户管理</title>
</head>
<body>
<@s.include value="/jsp/common/userHead.jsp" />
<form id="customForm" method="post">
    <input type="hidden" name="customOprate" id="customOprate" value="${(oprate)!}" />
    <input type="hidden"  name="oprateUserId" id="oprateUserId" value="${(user.userId)!}" />
    <input type="hidden"  name="customId" id="customId" value="${(custom.customId)!}" />
    <div>
        <div>
            <table style="border-top-width: 0px; border-right-width: 0px;border-bottom-width: 1px;border-left-width: 1px;width:1200px;height:98px;">
                <tr>
                    <td class="table-lable" width="10%">会员卡号：</td>
                    <td width="15%"><input class="easyui-validatebox" id="cardId" name="cardId"  required="required" ${disabled} value="${(custom.cardId)!}"/></td>
                    <td class="table-lable" width="10%">称呼：</td>
                    <td width="15%"><input class="easyui-validatebox" id="userName" name="userName"  required="required" ${disabled} value="${(custom.userName)!}"/></td>
                    <td class="table-lable" width="10%">电话：</td>
                    <td width="15%"><input class="easyui-validatebox" id="phone" name="phone" ${disabled} value="${(custom.phone)!}"/></td>
                    <td class="table-lable" width="10%">微信号：</td>
                    <td width="15%"><input class="easyui-textbox" id="weixin" name="weixin" ${disabled} value="${(custom.weixin)!}"/></td>
                </tr>
                <tr>
                    <td class="table-lable">年龄：</td>
                    <td width="15%"><input class="easyui-textbox" id="age" name="age" ${disabled} value="${(custom.age)!}"/></td>
                    <td class="table-lable">性别：</td>
                    <td><input class="easyui-combobox" id="sex" name="sex" ${disabled} value="${(custom.sex)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/sex.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">职业：</td>
                    <td><input class="easyui-textbox" id="job" name="job" ${disabled} value="${(custom.job)!}"/></td>
                    <td class="table-lable">性格：</td>
                    <td><input class="easyui-combobox" id="customCharacter" name="customCharacter" ${disabled} value="${(custom.customCharacter)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/character.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                </tr>
                <tr>
                    <td class="table-lable">发量：</td>
                    <td><input class="easyui-combobox" id="hairNumber" name="hairNumber" ${disabled} value="${(custom.hairNumber)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairNumber.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">发长：</td>
                    <td><input class="easyui-combobox" id="hairLength" name="hairLength" ${disabled} value="${(custom.hairLength)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairLength.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">发质：</td>
                    <td colspan="3"><input class="easyui-combobox" id="hairQuality" name="hairQuality" ${disabled} value="${(custom.hairQuality)!}"
                    data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairQuality.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                </tr>
                <tr>
                    <td class="table-lable">打理方式：</td>
                    <td><input class="easyui-combobox" id="hairOprateType" name="hairOprateType" ${disabled} value="${(custom.hairOprateType)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairOprateType.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">打造方向：</td>
                    <td><input class="easyui-combobox" id="hairPrefer" name="hairPrefer" ${disabled} value="${(custom.hairPrefer)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairPrefer.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">回避方向：</td>
                    <td><input class="easyui-combobox" id="hairExclude" name="hairExclude" ${disabled} value="${(custom.hairExclude)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairExclude.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                    <td class="table-lable">做头发的周期：</td>
                    <td><input class="easyui-combobox" id="hairFrequency" name="hairFrequency" ${disabled} value="${(custom.hairFrequency)!}"
                               data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairFrequency.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"></td>
                </tr>
                <tr>
                    <td class="table-lable" width="10%">生日：</td>
                    <td width="15%"><input class="easyui-datebox" id="birthday" name="birthday" ${disabled} value="${(custom.birthday)!}"
                                           data-options="formatter:myformatter,parser:myparser"/></td>
                    <td class="table-lable">备注：</td>
                    <td colspan="5"><input class="easyui-textbox" id="comment" name="comment" ${disabled} value="${(custom.comment)!}"></td>
                </tr>
            </table>
        </div>
        <hr>
        <div>
            客户历史记录:
        </div>
        <div>
            <table id="customRecordDatagrid" class="easyui-datagrid" fitColumns="false" singleSelect="true" url="/busys/custom!getRecordList.action?customId=${(custom.customId)!'customId'}"
                  <#if disabled != "disabled"> toolbar="#tb" </#if>pagination="true">
                <thead>
                <tr>
                    <th field="oprateDate" width="100" >日期</th>
                    <th field="disinerId" width="100" formatter="disinerFormat">设计师</th>
                    <th field="disinType" width="100"  formatter="disinTypeFormat">操作类型</th>
                    <th field="totalFee" width="100">价格</th>
                    <th field="hairLevel" width="100"  formatter="hairLevelFormat">头发层次</th>
                    <th field="hairLength" width="100"  formatter="hairLengthFormat">头发长度</th>
                    <th field="hairBar" width="150" formatter="hairBarFormat">头发杠子</th>
                    <th field="hairPermBrand" width="150" formatter="hairPermBrandFormat">烫发品牌</th>
                    <th field="hairDyeBrand" width="150" formatter="hairDyeBrandFormat">染发品牌</th>
                    <th field="hairCairBrand" width="150" formatter="hairCairBrandFormat">护理品牌</th>
                    <th field="oprate" width="100" formatter="oprateFormat">操作</th>
                </tr>
                </thead>
            </table>
            <#if disabled != "disabled">
            <div id="tb">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRecord()">添加</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRecord()">编辑</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRecord()">删除</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="queryRecord()">查询</a>
            </div>
            </#if>
        </div>
        <hr>
        <#if disabled != "disabled">
        <div>
            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="saveCustomSubmit();"  iconCls='icon-search'>保存</a>
            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="cancelCustom();"  iconCls='icon-cancel'>取消</a>
        </div>
        </#if>
    </div>
</form>
<div style="display: none">
        <div id="editWindow" class="easyui-window" title="编辑" style="width:410px;height:580px"
             modal="true" closed="true">
            <form id="recordForm" method="post">
                <input type="hidden" name="recordOUserId" id="recordOUserId" value="${(user.userId)!}" />
                <input type="hidden" name="recordOprate" id="recordOprate" />
                <table>
                    <tr>
                        <td class="label">日期:</td>
                        <td><input class="easyui-datebox" id="oprateDate" name="oprateDate" required="required"  ${disabled}
                                   data-options="formatter:myformatter,parser:myparser"/></td>
                    </tr>
                    <tr>
                        <td class="label">设计师:</td>
                        <td><input class="easyui-combobox" name="disinerId" id="disinerId" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/disiner.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">操作类型:</td>
                        <td><input class="easyui-combobox" name="disinType" id="disinType" required="required" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/disinType.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">价格:</td>
                        <td><input class="easyui-numberbox" name="totalFee" id="totalFee" ${disabled}/></td>
                    </tr>
                    <tr>
                        <td class="label">头发层次:</td>
                        <td><input class="easyui-combobox" name="hairLevel" id="hairLevel" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairLevel.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">头发长度:</td>
                        <td><input class="easyui-combobox" name="hairLength" id="hairLength" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairLength.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">头发杠子:</td>
                        <td><input class="easyui-combobox" name="hairBar" id="hairBar" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairBar.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">烫发品牌:</td>
                        <td><input class="easyui-combobox" name="hairPermBrand" id="hairPermBrand" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairPermBrand.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">染发品牌:</td>
                        <td><input class="easyui-combobox" name="hairDyeBrand" id="hairDyeBrand" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairDyeBrand.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">护理品牌:</td>
                        <td><input class="easyui-combobox" name="hairCairBrand" id="hairCairBrand" ${disabled}
                                   data-options="valueField:'id',textField:'text',url:'/jsp/common/dict/hairCairBrand.json',filter: function(q, row){return row['id'] != null && (row['id'].indexOf(q) >= 0 || row['text'].indexOf(q) >= 0);	}"/></td>
                    </tr>
                    <tr>
                        <td class="label">客人有不喜欢的<br/>卷度或颜色等:</td>
                        <td><input class="easyui-textbox" name="hairHateItems" id="hairHateItems" multiline="true" ${disabled}
                                   style="height:66px;width:170px;"/></td>
                    </tr>
                    <tr>
                        <td class="label">顾客要求要点:</td>
                        <td><input class="easyui-textbox" name="customRequireItems" id="customRequireItems" multiline="true" ${disabled}
                                   style="height:66px;width:170px;"/></td>
                    </tr>
                    <tr>
                        <td class="label">技术操作要点:</td>
                        <td><input class="easyui-textbox" name="oprateNoticeItems" id="oprateNoticeItems" multiline="true" ${disabled}
                                   style="height:66px;width:170px;"/></td>
                    </tr>
                    <tr>
                        <td class="label">图片上传:</td>
                        <td><input class="easyui-filebox" name="imgUrl" id="imgUrl" multiple="true" ${disabled}
                                   data-options="prompt:'请选择图片...'"/></td>
                    </tr>
                    <tr>
                        <td class="label">图片:</td>
                        <td><a href="#" onclick="openImg();" name="imgUrls" id="imgUrls">图片1</a></td>
                    </tr>

                    <#if disabled != "disabled">
                    <tr>
                        <td colspan="2" align="right">
                            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="submitRecord();"  iconCls='icon-ok'>确认</a>
                            <a class="easyui-linkbutton"  href="javascript:void(0);" onclick="canRecord();"  iconCls='icon-cancel'>取消</a>
                        </td>
                    </tr>
                    </#if>
                </table>
            </form>
        </div>
    </div>
<div style="display: none">
    <div id="imgWindow" class="easyui-window" title="图片" style="width:410px;height:580px"
         modal="true" closed="true">
        <img id="img" name="img" />
    </div>
</div>
</body>
</html>
