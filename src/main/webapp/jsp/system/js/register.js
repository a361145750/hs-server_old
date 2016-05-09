/**
 * Created by work_tl on 2016/4/11.
 */
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value, param){
            return value == $(param[0]).val();
        },
        message: '两次密码输入不一致！'
    }

});

//////////////////////////////////////////////////
function roleFormat(value, row){
    if(value == 1){
        return "管理员";
    } else {
        return "访客";
    }
}
function rightFormat(value, row){
    return value;
}

function oprate(value, rowData, rowIndex){
    return '<a href="#" style="color:blue" onClick='+'\'javascript:showDetail('+JSON.stringify(rowData)+','+rowIndex+')\''+'>查看详情</a>'
}

showDetail = function(rowData,rowIndex){
    $.ajax({
        url:'/system/register!getList.action',
        success:function(data){
            alert('success' + rowData.userId);
        }
    });
}

function addUser(){
    $("#editWindow").window("open");
    var oprateUser = $("#oprateUserId").val();
    $("#editForm").form("clear");
    $("#oprateUserId").val(oprateUser);
    $("#oprate").val("newUser");
    $("#editWindow").panel({title:"添加用户"});
}

function editUser(){
    var row = $("#userDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再编辑！");
        return;
    }
    $("#editWindow").window("open");
    var oprateUser = $("#oprateUserId").val();
    $("#editForm").form("clear");
    $("#oprateUserId").val(oprateUser);
    $("#oprate").val("editUser");
    row.passWord1 = row.passWord;
    $("#editForm").form("load",row);
    $("#editWindow").panel({title:"编辑用户"});
}

function delUser(){
    var row = $("#userDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再删除！");
        return;
    }
    $.messager.confirm("Confirm","是否确定要删除？",function(r){
        if(r){
            $.ajax({
                url:'/system/register!delUser.action',
                data:{userId:row.userId},
                success:function(data){
                    $("#userDatagrid").datagrid("reload");
                }
            });
        }
    });
}

function queryUser(){
    $("#queryWindow").window("open");
    $("#queryForm").form("clear");
}

function queryUserSubmit(){
    var queryParams = {queryKey:$("#queryKey").val()};
    $("#userDatagrid").datagrid("load",queryParams);
    $("#queryWindow").window("close");
}

function submitUser(){
    var url="";
    if($("#oprate").val() == "newUser"){
        url="/system/register!addUser.action";
    } else{
        url="/system/register!updateUser.action";
    }
    if($("#role").combobox('getValue') =="1"){
        $("#userRight").val(255);
    } else {
        $("#userRight").val(127);
    }
    $('#editForm').form('submit',{
        url:url,
        onSubmit: function(param){
            if(!$("#editForm").form("validate")){
                $.messager.alert("Warning", "页面输入信息有误，请确认后再提交！");
                return false;
            }
            var ret = true;
            $.ajax({
                async:false,
                url:'/system/register!checkLoginId.action',
                data:{loginId:$("#loginId").val()},
                success:function(data){
                    var retData = JSON.parse(data);
                    if($("#oprate").val() == "newUser" && retData.length > 0){
                        $.messager.alert("Warning", "用户已经存在！");
                        ret = false;
                    }
                    if($("#oprate").val() == "editUser"  && (retData.length > 1 || (retData.length == 1 && retData[0].userId != $("#userId").val()))){
                        $.messager.alert("Warning", "用户已经存在！");
                        ret = false;
                    }
                }
            });
            return ret;
    },
    success:function(data){
        $("#userDatagrid").datagrid("reload");
        $("#editWindow").window("close");
    }
});
}

function canUser(){
    $("#editWindow").window("close");
}