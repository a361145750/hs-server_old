/**
 * Created by work_tl on 2016/4/15.
 */

$(document).ready(function() {
    //initCommboxData();
    initWebuploader();
});

function oprateFormat(value, rowData, rowIndex){
    return '<a href="#" style="color:blue" onClick='+'\'javascript:showDetail('+JSON.stringify(rowData)+','+rowIndex+')\''+'>查看详情</a>'
}

showDetail = function(rowData,rowIndex){
    $("#customRecordDatagrid").datagrid("selectRow",rowIndex);
    editRecord();
}

function addRecord(){
    $("#editWindow").window("open");
    var recordOUserId = $("#recordOUserId").val();
    $("#recordForm").form("clear");
    $("#recordOUserId").val(recordOUserId);
    $("#recordOprate").val("addRecord");
    $("#editWindow").panel({title:"添加客户历史记录"});
    $('#oprateDate').datebox('setValue', curentTime());
    $.ajax({
        async:false,
        url:'/busys/custom!getRecordId.action',
        dataType : 'json',
        success:function(data){
            $("#recordId").val(data.recordId);
        }
    });
}

function editRecord(){
    var row = $("#customRecordDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再编辑！");
        return;
    }
    $("#editWindow").window("open");
    var recordOUserId = $("#recordOUserId").val();
    $("#recordForm").form("clear");
    $("#recordOUserId").val(recordOUserId);
    $("#recordOprate").val("editRecord");
    $("#recordForm").form("load",row);
    $("#editWindow").panel({title:"编辑客户历史记录"});
}

function delRecord(){
    var row = $("#customRecordDatagrid").datagrid("getSelected");
    if(!row){
        $.messager.alert("Warning", "请选中后再删除！");
        return;
    }
    $.messager.confirm("Confirm","是否确定要删除？",function(r){
        if(r){
            var row = $('#customRecordDatagrid').datagrid('getSelected');
            var index = $('#customRecordDatagrid').datagrid('getRowIndex', row);
            $('#customRecordDatagrid').datagrid('deleteRow',index);
        }
    });
}

function saveCustomSubmit(){
    var url="";
    if($("#customOprate").val() == "newCustom"){
        url="/busys/custom!addCustom.action";
    } else{
        url="/busys/custom!updateCustom.action";
    }
    $('#customForm').form('submit',{
        url:url,
        onSubmit:function(param){
            if(!$("#customForm").form("validate")){
                $.messager.alert("Warning", "页面输入信息有误，请确认后再提交！");
                return false;
            }
        param.insert = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','inserted'));
        param.update = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','updated'));
        param.delete = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','deleted'));
        },
        success:function(data){
            $('#customRecordDatagrid').datagrid('acceptChanges');
            window.close();
        }
    });
}

function cancelCustom(){
    window.close();
}

function submitRecord(){
    if(!$("#recordForm").form("validate")){
        $.messager.alert("Warning", "页面输入信息有误，请确认后再提交！");
        return false;
    }
    var data ={
        recordId: $("#recordId").val(),
        recordOprateUserId: $("#recordOUserId").val(),
        oprateDate:$('#oprateDate').datebox('getValue'),
        disinerId:$('#disinerId').combobox('getValue'),
        disinType:$('#disinType').combobox('getValue'),
        totalFee:$('#totalFee').numberbox('getValue'),
        hairLevel:$('#hairLevel').combobox('getValue'),
        hairLength:$('#recordForm #hairLength').combobox('getValue'),
        hairBar:$('#hairBar').combobox('getValue'),
        hairPermBrand:$('#hairPermBrand').combobox('getValue'),
        hairDyeBrand:$('#hairDyeBrand').combobox('getValue'),
        hairCairBrand:$('#hairCairBrand').combobox('getValue'),
        hairHateItems:$('#hairHateItems').textbox('getValue'),
        customRequireItems:$('#customRequireItems').textbox('getValue'),
        oprateNoticeItems:$('#oprateNoticeItems').textbox('getValue')
    };
    if($('#recordOprate').val() == 'addRecord'){
        $('#customRecordDatagrid').datagrid('appendRow',data);
    } else {
        var row = $('#customRecordDatagrid').datagrid('getSelected');
        var index = $('#customRecordDatagrid').datagrid('getRowIndex', row);
        $('#customRecordDatagrid').datagrid('updateRow',{index:index,row:data});
    }
    $("#editWindow").window("close");
}

function canRecord(){
    $("#editWindow").window("close");
}

function openImg(fileName, widthHeight, imgUrl){
    var size = widthHeight.split('x');
    var winW = parseInt(size[0]) + 14;
    var winH = parseInt(size[1]) + 36;
    $("#imgWindow").window({
        title:fileName,
        width:winW,
        height:winH,
        modal:true
    }).window("open");
    $('#imgDiv1').empty();
    $('#imgDiv1').append('<img id = "img" name="img" src="'+imgUrl+'"  width="'+size[0]+'" height="'+size[1]+'" />');
}

function uploadFile(){
    $.ajaxFileUpload({
        url:'/busys/custom!uploadFile.action?customId='+$('#customId').val()+'&recordId='+$('#recordId').val(),//处理图片脚本
        fileElementId : $('input[type="file"]').attr('id'),//'file',//file控件id
        dataType : 'json',
        success : function (data){
            addAttchStr(data);
            addAttchImg(data);
        },
        error: function(data, status, e){
            alert(e);
        }
    })
}

function addAttchImg(data){
    if(data && data.length> 0){
        for(var i =0;i<data.length;i++){
            $('#imgDiv').append('<div id="'+data[i].recordAttchId+'" name="'+data[i].recordAttchId+'" class="imgDiv1"  width="50" height="50"> <img class="img-square" onClick="openImg(\''+data[i].fileName+'\', \''+data[i].fileSize+'\',\''+data[i].filePath+'\')" src="'+data[i].filePath+'" width="50" height="50"> <div class="closeLayer"  onClick="deleteImg(\''+data[i].recordAttchId+'\',\''+data[i].filePath+'\')" href="#" id="deleteImg"> <img src="/jsp/common/img/close.jpg" width="10px" height="10px"> </div> </div> <div class="imgDiv1"  width="5" height="50"></div>');
            //$('#imgDiv').append('<img id="'+data[i].recordAttchId+'" name="'+data[i].recordAttchId+'" src="'+data[i].filePath+'" width="50" height="50" onclick="openImg(\''+data[i].fileName+'\', \''+data[i].fileSize+'\', \''+data[i].filePath+'\')"/>&nbsp;');
        }
    }
}

function addAttchStr(data){
    if(data && data.length> 0){
        var attch = $('#attch').val().split(',');
        if(attch && attch.length>0&& attch[0] == ''){
            attch.shift();
        }
        for(var i =0;i<data.length;i++){
            attch.push(JSON.stringify(data[i]));
        }
        $('#attch').val(attch.join(","));
    }
}

function fileChange(){
    //alert($('#file').filebox('getValue'));
}

function deleteImg(recordAttchId,filePath){
    $.messager.confirm("Confirm","是否确定要删除？",function(r){
        if(r){
            $('#'+recordAttchId).next().remove();
            $('#'+recordAttchId).remove();
            $.ajax({
                url:'/busys/custom!deleteFile.action',
                data:{recordAttchId:recordAttchId,filePath:filePath},
                success:function(data){
                }
            });
        }
    });
}

function initWebuploader(){

    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        swf: 'http://localhost:8088/jsp/common/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server:'http://localhost:8088/busys/custom!uploadFile.action',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker',
        //可以重复
        duplicate: true ,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                '<img>' +
                '<div class="info">' + file.name + '</div>' +
                '</div>'
            ),
            $img = $li.find('img');

        // $list为容器jQuery实例
        $('#fileList').empty();
        $('#fileList').append( $li );

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight
        var thumbnailWidth = 230;
        var thumbnailHeight = 230;
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file, response ) {
        $('#pic').val(response);
        $( '#'+file.id ).addClass('upload-state-done');
    });

    // 文件上传失败，显示上传出错。
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id );
        var $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('上传失败');
    });

}