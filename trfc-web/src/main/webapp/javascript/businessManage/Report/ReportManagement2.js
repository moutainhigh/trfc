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
//    除页脚页眉的js代码
var HKEY_Root,HKEY_Path,HKEY_Key;
HKEY_Root="HKEY_CURRENT_USER";
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
//设置网页打印的页眉页脚为空
function PageSetup_Null()
{
    try
    {
        var Wsh=new ActiveXObject("WScript.Shell");
        HKEY_Key="header";
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
        HKEY_Key="footer";
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
    }
    catch(e)
    {}

}

//设置网页打印的页眉页脚为默认值
function PageSetup_Default()
{
    try
    {
        var Wsh=new ActiveXObject("WScript.Shell");
        HKEY_Key="header";
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页码,&p/&P");
        HKEY_Key="footer";
        Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
    }
    catch(e)
    {}

}
PageSetup_Default();
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
        return  this.year + "-" + this.month + "-" + this.date +"  "+this.hour + ":" + this.minute + ":" + this.second ;
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
    var bbg_tiaojian1=document.querySelector(".bbg_tiaojian1");
    var bbg_tiaojian2=document.querySelector(".bbg_tiaojian2");
    var bbg_tiaojian3=document.querySelector(".bbg_tiaojian3");
    var bbg_tiaojian4=document.querySelector(".bbg_tiaojian4");
    bbg_tiaojian1.innerHTML=bbg_gys+" "+bbg_kk+" "+gys+" "+bbg_sjn+" "+clock1+" "+clock2+" "+bbg_cph+" "+bbg_bz;
    bbg_tiaojian2.innerHTML=bbg_gys+" "+bbg_kk+" "+gys+" "+bbg_sjn+" "+clock1+" "+clock2+" "+bbg_cph+" "+bbg_bz;
    bbg_tiaojian3.innerHTML=bbg_gys+" "+bbg_kk+" "+gys+" "+bbg_sjn+" "+clock1+" "+clock2+" "+bbg_cph+" "+bbg_bz;
    bbg_tiaojian4.innerHTML=bbg_gys+" "+bbg_kk+" "+gys+" "+bbg_sjn+" "+clock1+" "+clock2+" "+bbg_cph+" "+bbg_bz;

}

//页面时间
var clock1 = new Clock();
clock1.display(document.getElementById("clock1"));
var clock2 = new Clock();
clock2.display(document.getElementById("clock2"));
var clock3 = new Clock1();
clock3.display(document.querySelector(".clock3"));
var clock4 = new Clock();
clock4.display(document.querySelector(".clock4"));
var clock5 = new Clock1();
clock5.display(document.querySelector(".clock5"));
var clock6 = new Clock1();
clock6.display(document.querySelector(".clock6"));
var clock7 = new Clock();
clock7.display(document.querySelector(".clock7"));
var clock8 = new Clock1();
clock8.display(document.querySelector(".clock8"));
var clock9 = new Clock1();
clock9.display(document.querySelector(".clock9"));
var clock10 = new Clock();
clock10.display(document.querySelector(".clock10"));
var clock11 = new Clock1();
clock11.display(document.querySelector(".clock11"));
var clock12 = new Clock1();
clock12.display(document.querySelector(".clock12"));
var clock13 = new Clock();
clock13.display(document.querySelector(".clock13"));
var clock14 = new Clock1();
clock14.display(document.querySelector(".clock14"));
// 物料的四个tab切换菜单
var wl_li = $('.wuliao_tab ul li');
wl_li.click(function () {
    $(this).addClass('select').siblings().removeClass('select');
    var index = wl_li.index(this);
    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
});
$( function() {
    var availableTags = [
        "上海欧瑞仪器设备有限公司",
        "上海对接口",
        "阿拉滴上海",
        "我德歌功",
        "上海啊",
        "上海对接口",
        "阿拉滴上海",
        "上海呵呵呵限公司",
        "上海对接口",
        "阿拉滴上海"
    ];
    $( "#gys" ).autocomplete({
        source: availableTags
    });

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