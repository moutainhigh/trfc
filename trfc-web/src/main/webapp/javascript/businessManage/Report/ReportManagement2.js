var URL = {
			materialAutoCompleteSearch:"/trfc/materiel/autoCompleteSearch",   //物料
			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch",        //供应商
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch" ,     //司机姓名
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",   //车号
			yardAutoCompleteSearch: "/trfc/yard/autoCompleteSearch",      //堆场
			autoCompleteSearch1:"/trfc/minemouth/autoCompleteSearch",      //矿口
	};

$( function() {
	 //获取下拉框数据并填充
	//矿口
	autoCompleteSearch1();
   function autoCompleteSearch1(){
   var cache={};
   $("#bbg_kk").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.autoCompleteSearch1, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label = x.name;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.name);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
 
	
	//物料	
	yardAutoCompleteSearch();
   function yardAutoCompleteSearch(){
   var cache={};
   $("#duichang").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.yardAutoCompleteSearch, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label = x.name;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.name);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
 
	
	//物料	
	materialSelect();
   function materialSelect(){
   var cache={};
   $("#gys").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.materialAutoCompleteSearch, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label = x.name;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.name);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
 
 //供应商
   autoCompleteSearch();
   function autoCompleteSearch(){
   var cache={};
   $("#bbg_gys").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.autoCompleteSearch, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label = x.name;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.name);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
   
 //司机姓名
   driverAutoCompleteSearch();
   function driverAutoCompleteSearch(){
   var cache={};
   $("#bbg_sjn").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.driverAutoCompleteSearch, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label = x.name;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.name);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
   
 //车号
   vehicleAutoCompleteSearch();
   function vehicleAutoCompleteSearch(){
   var cache={};
   $("#bbg_cph").autocomplete({
   //数据源
   source: function( request, response ) {
   var term = request.term;
   var material = cache['material'] || {};
   if ( term in material ) {
   response( material[ term ] );
   return;
   }
   $.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
	    material[ term ] = data;
	    response( data );
   });
   },
   //显示下拉框
   response: function( event, ui ) {
   if(ui.content && ui.content.length > 0){
   //展示下拉框
   ui.content.forEach(function(x,i,a){
   x.label =  x.vehicleno;
   });
   }
   },
   //选定,显示结果到输入框
   select: function( event, ui ) {
   $(this).val(ui.item.vehicleno);
   return false;
   }
   }).off('click').on('click',function(){
   $(this).autocomplete('search',' ');
   }).change(function(){
   $(this).val('');
   });
   };
   
   //layer.js调用，删除
   $('.delete').on('click', function(){
       layer.confirm('你确定要删除吗?', {
           area: '600px', //弹出框宽度
           btn: ['确定','取消'] //按钮文字
       }, function(index){
           // 确定按钮执行的操作，自定义、
           //关闭对话框,插件必须的
           layer.close(index);
       }, function(){
           // 取消按钮执行的操作，自定义
       });
   });

} );

//导出
var idTmr;
function  getExplorer() {
    var explorer = window.navigator.userAgent ;
    //ie
    if (explorer.indexOf("MSIE") >= 0) {
        return 'ie';
    }
    //firefox
    else if (explorer.indexOf("Firefox") >= 0) {
        return 'Firefox';
    }
    //Chrome
    else if(explorer.indexOf("Chrome") >= 0){
        return 'Chrome';
    }
    //Opera
    else if(explorer.indexOf("Opera") >= 0){
        return 'Opera';
    }
    //Safari
    else if(explorer.indexOf("Safari") >= 0){
        return 'Safari';
    }
}
function method(tableid) {
    if(getExplorer()=='ie')
    {
        var curTbl = document.querySelector(tableid);
        var oXL = new ActiveXObject("Excel.Application");
        var oWB = oXL.Workbooks.Add();
        var xlsheet = oWB.Worksheets(1);
        var sel = document.body.createTextRange();
        sel.moveToElementText(curTbl);
        sel.select();
        sel.execCommand("Copy");
        xlsheet.Paste();
        oXL.Visible = true;

        try {
            var fname = oXL.Application.GetSaveAsFilename("Excel.xls", "Excel Spreadsheets (*.xls), *.xls");
        } catch (e) {
            print("Nested catch caught " + e);
        } finally {
            oWB.SaveAs(fname);
            oWB.Close(savechanges = false);
            oXL.Quit();
            oXL = null;
            idTmr = window.setInterval("Cleanup();", 1);
        }

    }
    else
    {
        tableToExcel(tableid)
    }
}
function Cleanup() {
    window.clearInterval(idTmr);
    CollectGarbage();
}
var tableToExcel = (function() {
        var uri = 'data:application/vnd.ms-excel;base64,',
            template = '<html><head><meta charset="UTF-8"></head><body><table width="100%" border="1" cellspacing="0" cellpadding="0">{table}</table></body></html>',
            base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) },
            format = function(s, c) {
                return s.replace(/{(\w+)}/g,
                    function(m, p) { return c[p]; }) }
        return function(table, name) {
            if (!table.nodeType) table = document.querySelector(table)
            var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
            window.location.href = uri + base64(format(template, ctx))
        }
    })()
//打印
function preview()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint-->";
    eprnstr="<!--endprint-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
}
function preview1()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint1-->";
    eprnstr="<!--endprint1-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
}
function preview2()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint2-->";
    eprnstr="<!--endprint2-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
}
function preview3()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint3-->";
    eprnstr="<!--endprint3-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
}
function preview4()
{
    bdhtml=window.document.body.innerHTML;
    sprnstr="<!--startprint4-->";
    eprnstr="<!--endprint4-->";
    prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);
    prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));
    window.document.body.innerHTML=prnhtml;
    window.print();
}
$('#yearNow').off('click').on('click',function(){
	  var date = new Date();
	    this.year = date.getFullYear();
	    var clock1=document.getElementById("clock1");
	    var clock2=document.getElementById("clock2");
	    clock1.value="";
	    clock2.value="";
	    clock1.value=this.year+"年";
	    clock2.value=this.year+"年";
});
$('#monthNow').off('click').on('click',function(){
	 var date = new Date();
	    this.month = date.getMonth() + 1;
	    var clock1=document.getElementById("clock1");
	    var clock2=document.getElementById("clock2");
	    clock1.value="";
	    clock2.value="";
	    clock1.value=this.month+"月";
	    clock2.value=this.month+"月";
});
$('#dayNow').off('click').on('click',function(){
	 var date = new Date();
	    this.date = date.getDate();
	    var clock1=document.getElementById("clock1");
	    var clock2=document.getElementById("clock2");
	    clock1.value="";
	    clock2.value="";
	    clock1.value=this.date+"日";
	    clock2.value=this.date+"日";
});
//获取系统时间 年月日
function Clock() {
    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.date = date.getDate();
    this.toString = function() {
        return  this.year + "-" + this.month + "-" + this.date;
    };
    this.display = function(ele) {
        var clock = new Clock();
        ele.placeholder= clock.toString();
        ele.innerHTML= clock.toString();
    };
}
//获取时间日期年月日时分秒
function Clock1() {
    var date = new Date();
    this.year = date.getFullYear();
    this.month = date.getMonth() + 1;
    this.date = date.getDate();
    this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

    this.toString = function() {
        return  this.year + "-" + this.month + "-" + this.date +"  "+this.hour + ":" + this.minute;
    };

    this.display = function(ele) {
        var clock = new Clock1();
        ele.innerHTML = clock.toString();
    };
}
//条件搜索
function bbgClick(){
    var bbg_gys=document.getElementById("bbg_gys").value;
    var bbg_kk=document.getElementById("bbg_kk").value;
    var gys=document.getElementById("gys").value;
    var bbg_sjn=document.getElementById("bbg_sjn").value;
    var clock1=document.getElementById("clock1").value;
    var clock2=document.getElementById("clock2").value;
    var bbg_cph=document.getElementById("bbg_cph").value;
    var bbg_bz=document.getElementById("bbg_bz").value;
    var duichang=document.getElementById("duichang").value;
    var pushStatus=document.getElementById("pushStatus").value;
    var bbg_tiaojian1=document.querySelector(".bbg_tiaojian1");
    var bbg_tiaojian2=document.querySelector(".bbg_tiaojian2");
    var bbg_tiaojian3=document.querySelector(".bbg_tiaojian3");
    var bbg_tiaojian4=document.querySelector(".bbg_tiaojian4");
    var str="";
    if(duichang!=""){
        str+="堆场："+duichang+" ";
    }
    if(pushStatus!=""){
    	if(pushStatus==0){
    		str+="推单状态：未推单";
    	}
    	if(pushStatus==1){
    		str+="推单状态：推单中";
    	}
    	if(pushStatus==2){
    		str+="推单状态：已推单";
    	}
    }
    if(bbg_gys!=""){
        str+="供应商："+bbg_gys+" ";
    }
   if(bbg_kk!=""){
        str+="矿口："+bbg_kk+" ";
    }
   if(gys!=""){
        str+="物料："+gys+" ";
    }else if(bbg_sjn!=""){
        str+="司机姓名：："+bbg_sjn+" ";
    }
    if(bbg_cph!=""){
        str+="车牌号："+bbg_cph+" ";
    }
    if(bbg_bz!=""){
        str+="备注："+bbg_bz+" ";
    }
    bbg_tiaojian1.innerHTML=str;
    bbg_tiaojian2.innerHTML=str;
    bbg_tiaojian3.innerHTML=str;
    bbg_tiaojian4.innerHTML=str;
}

//页面时间
var clock1 = new Clock();
clock1.display(document.getElementById("clock1"));
var clock2 = new Clock();
clock2.display(document.getElementById("clock2"));
var clock3 = new Clock();
clock3.display(document.querySelector(".clock3"));
var clock4 = new Clock();
clock4.display(document.querySelector(".clock4"));
var clock5 = new Clock1();
clock5.display(document.querySelector(".clock5"));
var clock6 = new Clock();
clock6.display(document.querySelector(".clock6"));
var clock7 = new Clock();
clock7.display(document.querySelector(".clock7"));
var clock8 = new Clock1();
clock8.display(document.querySelector(".clock8"));
var clocka = new Clock();
clocka.display(document.querySelector(".clocka"));
var clockb = new Clock();
clockb.display(document.querySelector(".clockb"));
var clockc = new Clock1();
clockc.display(document.querySelector(".clockc"));
var clock9 = new Clock();
clock9.display(document.querySelector(".clock9"));
var clock10 = new Clock();
clock10.display(document.querySelector(".clock10"));
var clock11 = new Clock1();
clock11.display(document.querySelector(".clock11"));
var clock12 = new Clock();
clock12.display(document.querySelector(".clock12"));
var clock13 = new Clock();
clock13.display(document.querySelector(".clock13"));
var clock14 = new Clock1();
clock14.display(document.querySelector(".clock14"));