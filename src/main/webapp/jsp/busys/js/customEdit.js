/**
 * Created by work_tl on 2016/4/15.
 */
var attchI={};
var attchD={};

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
    clearRecordForm();
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
    $("#editWindow").panel({title:"编辑客户历史记录"});
    var recordOUserId = $("#recordOUserId").val();
    clearRecordForm();
    $("#recordOUserId").val(recordOUserId);
    $("#recordOprate").val("editRecord");
    $("#recordForm").form("load",row);
    addAttchImg(row.attchs);
}

function clearRecordForm(){
    $("#recordForm").form("clear");
    $('#imgDiv').empty();
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
    $('#customForm').form('submit',{
        url:"/busys/custom!saveCustom.action",
        onSubmit:function(param){
            if(!$("#customForm").form("validate")){
                $.messager.alert("Warning", "页面输入信息有误，请确认后再提交！");
                return false;
            }
        //param.insert = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','inserted'));
        //param.update = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','updated'));
        //param.delete = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','deleted'));
        },
        success:function(data){
            //$('#customRecordDatagrid').datagrid('acceptChanges');
            //window.close();
            if($("#customOprate").val() == "newCustom"){
                //window.close();
                location.href = '/busys/custom!editCustom.action?customId=' + $("#customId").val();
            } else{
                location.reload();
            }
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
    $.ajax({
        async:false,
        url:'/busys/custom!getCustom.action?customId=' + $("#customId").val(),
        dataType : 'json',
        success:function(data){
            if(data && data.length == 0){
                $.messager.alert("Warning", "请先保存客户信息！");
            } else {
                //var data ={
                //    customId: $("#customId").val(),
                //    recordId: $("#recordId").val(),
                //    recordOprateUserId: $("#recordOUserId").val(),
                //    oprateDate:$('#oprateDate').datebox('getValue'),
                //    disinerId:$('#disinerId').combobox('getValue'),
                //    disinType:$('#disinType').combobox('getValue'),
                //    totalFee:$('#totalFee').numberbox('getValue'),
                //    hairLevel:$('#hairLevel').combobox('getValue'),
                //    hairLength:$('#recordForm #hairLength').combobox('getValue'),
                //    hairBar:$('#hairBar').combobox('getValue'),
                //    hairPermBrand:$('#hairPermBrand').combobox('getValue'),
                //    hairDyeBrand:$('#hairDyeBrand').combobox('getValue'),
                //    hairCairBrand:$('#hairCairBrand').combobox('getValue'),
                //    hairHateItems:$('#hairHateItems').textbox('getValue'),
                //    customRequireItems:$('#customRequireItems').textbox('getValue'),
                //    oprateNoticeItems:$('#oprateNoticeItems').textbox('getValue'),
                //    attchI:attchI[$("#recordId").val()]?attchI[$("#recordId").val()]:[],
                //    attchD:attchD[$("#recordId").val()]?attchD[$("#recordId").val()]:[]
                //};

                //if($('#recordOprate').val() == 'addRecord'){
                //    //$('#customRecordDatagrid').datagrid('appendRow',data);
                //} else {
                //    var row = $('#customRecordDatagrid').datagrid('getSelected');
                //    var index = $('#customRecordDatagrid').datagrid('getRowIndex', row);
                //    data.attchs = row.attchs;
                //    //$('#customRecordDatagrid').datagrid('updateRow',{index:index,row:data});
                //}
                $('#recordForm').form('submit',{
                    url:"/busys/custom!saveRecord.action",
                    onSubmit:function(param){
                        //param.insert = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','inserted'));
                        //param.update = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','updated'));
                        //param.delete = JSON.stringify($('#customRecordDatagrid').datagrid('getChanges','deleted'));
                        param.customId = $("#customId").val();
                        param.recordOprateUserId = $("#recordOUserId").val();
                        param.attchI = attchI[$("#recordId").val()]?JSON.stringify(attchI[$("#recordId").val()]):'[]';
                        param.attchD = attchD[$("#recordId").val()]?JSON.stringify(attchD[$("#recordId").val()]):'[]';
                    },
                    success:function(data){
                        $('#customRecordDatagrid').datagrid('reload');
                        attchI={};
                        attchD={};
                    }
                });
                $("#editWindow").window("close");
            }
        }
    });

}

function canRecord(){
    $("#editWindow").window("close");
}

function openImg(fileName, widthHeight,recordAttchId,filePathFull){
    var size = widthHeight.split('x');
    if(parseInt(size[0]) > window.innerWidth - 20){
        size[0] = window.innerWidth - 20;
    }

    if(parseInt(size[1]) > window.innerHeight - 40){
        size[1] = window.innerHeight - 40;
    }
    var winW = parseInt(size[0]) + 14;
    var winH = parseInt(size[1]) + 36;
    $("#imgWindow").window({
        title:fileName,
        width:winW,
        height:winH,
        modal:true
    }).window("open");
    $("#imgWindow").parent().css('top',(window.innerHeight - winH) / 2);
    $("#imgWindow").parent().css('left',(window.innerWidth - winW) / 2);
    $("#imgWindow").parent().next().css('top',(window.innerHeight - winH) / 2);
    $("#imgWindow").parent().next().css('left',(window.innerWidth - winW) / 2);
    $('#imgWindow').css('padding','0');
    $('#imgDiv1').empty();
    $('#imgDiv1').append('<img id = "img" name="img" src="/busys/custom!downloadFile.action?recordAttchId='+recordAttchId+'&filePathFull='+ filePathFull +'"  width="'+size[0]+'" height="'+size[1]+'" />');
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
            $('#imgDiv').append('<div id="'+data[i].recordAttchId+'" name="'+data[i].recordAttchId+'" class="imgDiv1"  width="50" height="50"> <img class="img-square" onClick="openImg(\''+data[i].fileName+'\', \''+data[i].fileSize+'\', \''+data[i].recordAttchId+'\', \''+data[i].filePathFull+'\''+')" src="/busys/custom!downloadFile.action?filePathFull='+data[i].filePathFull+ '&recordAttchId='+ data[i].recordAttchId +'" width="50" height="50"> <div class="closeLayer"  onClick="deleteImg(\''+data[i].recordAttchId+'\', \''+data[i].filePath+'\''+')" href="#" id="deleteImg"> <img src="/jsp/common/img/close.jpg" width="10px" height="10px"> </div> </div> <div class="imgDiv1"  width="5" height="50"></div>');
        }
    }
}

function addAttchStr(data){
    if(data && data.length> 0){
        //var attch = $('#attchI').val().split(',');
        //if(attch && attch.length>0&& attch[0] == ''){
        //    attch.shift();
        //}
        //for(var i =0;i<data.length;i++){
        //    attch.push(JSON.stringify(data[i]));
        //    addRowAttch(data[i]);
        //}
        //$('#attchI').val(attch.join(","));

        var attch = attchI[$("#recordId").val()];
        if(!attch){
            attch=[];
        }
        for(var i =0;i<data.length;i++){
            //attch.push(JSON.stringify(data[i]));
            attch.push(data[i]);
            addRowAttch(data[i]);
        }
        attchI[$("#recordId").val()] = attch;
    }
}

function delAttchStr(attchId){
    if(attchId!=''){
        //var find = false;
        //var attchI = $('#attchI').val().split(',');
        //if(attchI && attchI.length>0){
        //    for(var i =0;i<attchI.length;i++){
        //        if(attchI[i].recordAttchId == attchId){
        //            attchI.slice(i,1);
        //            delRowAttch(attchId);
        //            find = true;
        //            break;
        //        }
        //    }
        //}
        //$('#attchI').val(attchI.join(","));
        //
        //if(!find){
        //    var attchD = $('#attchD').val().split(',');
        //    if(attchD && attchD.length>0&& attchD[0] == ''){
        //        attchD.shift();
        //    }
        //    attchD.push(attchId);
        //    delRowAttch(attchId);
        //    $('#attchD').val(attchD.join(","));

        var find = false;
        var attchIn = attchI[$("#recordId").val()];
        if(attchIn && attchIn.length>0){
            for(var i =0;i<attchIn.length;i++){
                if(attchIn[i].recordAttchId == attchId){
                    attchIn.splice(i,1);
                    delRowAttch(attchId);
                    find = true;
                    break;
                }
            }
        }

        if(!find){
            var attchDe = attchD[$("#recordId").val()];
            if(!attchDe){
                attchDe=[];
            }
            var temp = {recordAttchId:attchId};
            attchDe.push(temp);
            attchD[$("#recordId").val()] = attchDe;
            delRowAttch(attchId);
        }
    }
}

function addRowAttch(attch){
    var row = $("#customRecordDatagrid").datagrid("getSelected");
    if(row){
        if(row.attchs){
            row.attchs[row.attchs.length] = attch;
        }
    }
}

function delRowAttch(attchId){
    var row = $("#customRecordDatagrid").datagrid("getSelected");
    if(row){
        if(row.attchs && row.attchs.length>0){
            for(var i =0;i<row.attchs.length;i++){
                if(row.attchs[i].recordAttchId == attchId){
                    row.attchs.splice(i,1);
                    break;
                }
            }
        }
    }
}

function fileChange(){
    //alert($('#file').filebox('getValue'));
}

function deleteImg(recordAttchId,filePath){
    if($('#customOprate').val() == 'showCustom'){
        return;
    }
    $.messager.confirm("Confirm","是否确定要删除图片？",function(r){
        if(r){
            $.ajax({
                url:'/busys/custom!deleteFile.action',
                data:{recordAttchId:recordAttchId,filePath:filePath},
                success:function(data){
                    $('#'+recordAttchId).next().remove();
                    $('#'+recordAttchId).remove();
                    delAttchStr(recordAttchId);
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
            mimeTypes: 'image/jpeg,image/png,image/jpg,image/gif,image/bmp'
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