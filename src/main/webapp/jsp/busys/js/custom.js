/**
 * Created by work_tl on 2016/4/15.
 */
function oprateFormat(value, rowData, rowIndex){
    return '<a href="#" style="color:blue" onClick='+'\'javascript:showDetail('+JSON.stringify(rowData)+','+rowIndex+')\''+'>查看详情</a>'
}

showDetail = function(rowData,rowIndex){
    window.open('/busys/custom!showCustom.action?customId=' + rowData.customId+ "&readOnly=disabled",'_blank');
}

function addUser(){
    window.open('/busys/custom!newCustom.action','_blank');
}

function editUser(){
    var row = $("#customDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再编辑！");
        return;
    }
    window.open('/busys/custom!editCustom.action?customId=' + row.customId,'_blank');
}

function delUser(){
    var row = $("#customDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再删除！");
        return;
    }
    $.messager.confirm("Confirm","是否确定要删除？",function(r){
        if(r){
            $.ajax({
                url:'/busys/custom!delCustom.action',
                data:{customId:row.customId},
                success:function(data){
                    $("#customDatagrid").datagrid("reload");
                }
            });
        }
    });
}

function queryUser(){
    $("#queryWindow").window("open");
    $("#queryForm").form("clear");
}

function queryCustomSubmit(){
    var queryParams = {
        customId:$("#customId").val(),
        cardId:$("#cardId").val(),
        userName:$("#userName").val(),
        phone:$("#phone").val(),
        weixin:$("#weixin").val(),
        birthday:$("#birthday").datebox('getValue')
    };
    $("#customDatagrid").datagrid("load",queryParams);
    $("#queryWindow").window("close");
}