var URL = {
		mainUrl:"/trfc/quality/sales/file/supplierScheme/main",
		addMainUrl:"/trfc/quality/sales/file/supplierScheme/addMain",
		editMainUrl:"/trfc/quality/sales/file/supplierScheme/editMain",
		pageUrl:"/trfc/quality/sales/file/supplierScheme/page",
		deleteUrl:"/trfc/quality/sales/file/supplierScheme/delete",
		updateUrl:"/trfc/quality/sales/file/supplierScheme/update",
		saveUrl:"/trfc/quality/sales/file/supplierScheme/add",
		codeUrl:"/trfc/quality/sales/file/supplierScheme/getCode",
		selectByIdUrl:"/trfc/quality/sales/file/supplierScheme/selectById",
		getSchemeUrl:"/trfc/quality/sales/file/supplierScheme/getSchemeData",
		getMaterialUrl:"/trfc/quality/sales/file/supplierScheme/getMaterialData",
		getSupplierUrl:"/trfc/quality/sales/file/supplierScheme/getSupplierData",
		getDetailUrl:"/trfc/quality/sales/file/supplierScheme/getDetailData",
		getCodeUrl:"/trfc/quality/sales/report/getCode",
		updateCodeUrl:"/trfc/quality/sales/report/updateCode",
		qschemeAutoCompleteSearch: "/trfc/quality/sales/file/qualityScheme/autoCompleteSearch",
		materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
		supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
};

//设置一个公共变量
var supplierDATA = {};
//获取用户id
var userid = $('.user').attr('userid');
//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
//time(获取指定时间的字符串) 默认返回当前时间
function getNowFormatDate(param,time) {
	var date ;
//	判断time参数是否存在
	if(time){
		date = new Date(time);
	}else{
		date = new Date();
	}
	var seperator1 = "-";
	var seperator2 = ":";
//	获取月份
	var month = date.getMonth() + 1;
//	获取日期
	var strDate = date.getDate();
//	月或者日 为个位数时前面加'0'
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	if (hours >= 0 && hours <= 9) {
		hours = "0" + hours;
	}
	if (minutes >= 0 && minutes <= 9) {
		minutes = "0" + minutes;
	}
	if (seconds >= 0 && seconds <= 9) {
		seconds = "0" + seconds;
	}
//	判断返回结果
	if(param){
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		+ " " + hours + seperator2 + minutes
		+ seperator2 + seconds;
	}else{
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	}
	return currentdate;
}
function initSelect(){
	var cache = {};
	if($(".qschemeSel")){
		$(".qschemeSel").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var qscheme = cache['qscheme'] || {};
				if ( term in qscheme ) {
					response( qscheme[ term ] );
					return;
				}
				$.post( URL.qschemeAutoCompleteSearch, request, function( result ) {
					qscheme[ term ] = result.data;
					response( result.data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('qschemeid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('qschemeid');
		}).change(function(){
			if(!$(this).attr('qschemeid')){
				$(this).val('');
			}
		});
	}
	if($(".supplierSel")){
		$(".supplierSel").autocomplete({
			//获取数据
			source: function( request, response ) {
				var term = request.term;
				var supplier = cache['supplier'] || {};
				if ( term in supplier ) {
					response( supplier[ term ] );
					return;
				}
				$.post( URL.supplierAutoCompleteSearch, request, function(data) {
						supplier[ term ] = data;
						response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				//当选择的时候 显示到输入框
				$(this).val(ui.item.name).attr('supplierid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('supplierid');
		}).change(function(){
			if(!$(this).attr('supplierid')){
				$(this).val('');
			}
		});
	}
	if($(".materialSel")){
		$(".materialSel").autocomplete({
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
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('materialid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('materialid');
		}).change(function(){
			if(!$(this).attr('materialid')){
				$(this).val('');
			}
		});
	}
};

