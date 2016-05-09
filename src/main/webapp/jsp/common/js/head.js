/**
 * Created by work_tl on 2016/4/23.
 */

var character ="/jsp/common/dict/character.json";
var characterMap = {};
var disiner ="/jsp/common/dict/disiner.json";
var disinerMap = {};
var disinType ="/jsp/common/dict/disinType.json";
var disinTypeMap = {};
var hairBar ="/jsp/common/dict/hairBar.json";
var hairBarMap = {};
var hairCairBrand ="/jsp/common/dict/hairCairBrand.json";
var hairCairBrandMap = {};
var hairDyeBrand ="/jsp/common/dict/hairDyeBrand.json";
var hairDyeBrandMap = {};
var hairExclude ="/jsp/common/dict/hairExclude.json";
var hairExcludeMap = {};
var hairFrequency ="/jsp/common/dict/hairFrequency.json";
var hairFrequencyMap = {};
var hairLength ="/jsp/common/dict/hairLength.json";
var hairLengthMap = {};
var hairLevel ="/jsp/common/dict/hairLevel.json";
var hairLevelMap = {};
var hairNumber ="/jsp/common/dict/hairNumber.json";
var hairNumberMap = {};
var hairOprateType ="/jsp/common/dict/hairOprateType.json";
var hairOprateTypeMap = {};
var hairPermBrand ="/jsp/common/dict/hairPermBrand.json";
var hairPermBrandMap = {};
var hairPrefer ="/jsp/common/dict/hairPrefer.json";
var hairPreferMap = {};
var hairQuality ="/jsp/common/dict/hairQuality.json";
var hairQualityMap = {};
var sex ="/jsp/common/dict/sex.json";
var sexMap = {};
$(function(){
    $.ajaxSettings.async = false;
    $.getJSON(character, function(data){
        for(var i=0;i<data.length;i++){
            characterMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(disiner, function(data){
        for(var i=0;i<data.length;i++){
            disinerMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(disinType, function(data){
        for(var i=0;i<data.length;i++){
            disinTypeMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairBar, function(data){
        for(var i=0;i<data.length;i++){
            hairBarMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairCairBrand, function(data){
        for(var i=0;i<data.length;i++){
            hairCairBrandMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairDyeBrand, function(data){
        for(var i=0;i<data.length;i++){
            hairDyeBrandMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairExclude, function(data){
        for(var i=0;i<data.length;i++){
            hairExcludeMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairFrequency, function(data){
        for(var i=0;i<data.length;i++){
            hairFrequencyMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairLength, function(data){
        for(var i=0;i<data.length;i++){
            hairLengthMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairLevel, function(data){
        for(var i=0;i<data.length;i++){
            hairLevelMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairNumber, function(data){
        for(var i=0;i<data.length;i++){
            hairNumberMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairOprateType, function(data){
        for(var i=0;i<data.length;i++){
            hairOprateTypeMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairPermBrand, function(data){
        for(var i=0;i<data.length;i++){
            hairPermBrandMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairPrefer, function(data){
        for(var i=0;i<data.length;i++){
            hairPreferMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(hairQuality, function(data){
        for(var i=0;i<data.length;i++){
            hairQualityMap[data[i].id] = data[i].text;
        }
    });
    $.getJSON(sex, function(data){
        for(var i=0;i<data.length;i++){
            sexMap[data[i].id] = data[i].text;
        }
    });
    $.ajaxSettings.async = true;
});

function characterFormat(value, rowData, rowIndex){
    return characterMap[value];
}
function disinerFormat(value, rowData, rowIndex){
    return disinerMap[value];
}
function disinTypeFormat(value, rowData, rowIndex){
    return disinTypeMap[value];
}
function hairBarFormat(value, rowData, rowIndex){
    return hairBarMap[value];
}
function hairCairBrandFormat(value, rowData, rowIndex){
    return hairCairBrandMap[value];
}
function hairDyeBrandFormat(value, rowData, rowIndex){
    return hairDyeBrandMap[value];
}
function hairExcludeFormat(value, rowData, rowIndex){
    return hairExcludeMap[value];
}
function hairFrequencyFormat(value, rowData, rowIndex){
    return hairFrequencyMap[value];
}
function hairLengthFormat(value, rowData, rowIndex){
    return hairLengthMap[value];
}
function hairLevelFormat(value, rowData, rowIndex){
    return hairLevelMap[value];
}
function hairNumberFormat(value, rowData, rowIndex){
    return hairNumberMap[value];
}
function hairOprateTypeFormat(value, rowData, rowIndex){
    return hairOprateTypeMap[value];
}
function hairPermBrandFormat(value, rowData, rowIndex){
    return hairPermBrandMap[value];
}
function hairPreferFormat(value, rowData, rowIndex){
    return hairPreferMap[value];
}
function hairQualityFormat(value, rowData, rowIndex){
    return hairQualityMap[value];
}
function sexFormat(value, rowData, rowIndex){
    return sexMap[value];
}


function curentTime() {
    var now = new Date();
    var year = now.getFullYear(); //年
    var month = now.getMonth() + 1; //月
    var day = now.getDate(); //日
    var clock = year + "-";
    if (month < 10) clock += "0";
    clock += month + "-";
    if (day < 10) clock += "0";
    clock += day + " ";
    return (clock);
}


function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function myparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}
