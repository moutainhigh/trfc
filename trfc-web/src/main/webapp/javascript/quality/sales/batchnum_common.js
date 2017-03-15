//整合url
	var URL = {
			copyUrl:"/trfc/quality/sales/batchnum/copy",
			checkUrl:"/trfc/quality/sales/batchnum/check",
			pageUrl:"/trfc/quality/sales/batchnum/page",
			deleteUrl:"/trfc/quality/sales/batchnum/delete",
			updateUrl:"/trfc/quality/sales/batchnum/update",
			addUrl:"/trfc/quality/sales/batchnum/addMain",
			editUrl:"/trfc/quality/sales/batchnum/editMain",
			detailUrl:"/trfc/quality/sales/batchnum/detailMain",
			saveUrl:"/trfc/quality/sales/batchnum/add",
			materialAutoCompleteSearch:"/trfc/materiel/autoCompleteSearch",
			userAutoCompleteSearch:"/trfc/system/auth/user/autoCompleteSearch"
	};

//(化验人)下拉框
function userSelect(){
	
	var cache = {};
	$("#user_select").autocomplete({
		//数据源
		source: function( request, response ) {
			var term = request.term;
			var systemUser = cache['systemUser'] || {};
			if ( term in systemUser ) {
				response( systemUser[ term ] );
				return;
			}
			request.nameLike = request.term.trim();
			$.post( URL.userAutoCompleteSearch, request, function( result ) {
				systemUser[ term ] = result.data;
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
			$(this).val(ui.item.name).attr('assayerid', ui.item.id);
			return false;
		}
	}).off('click').on('click',function(){
		$(this).autocomplete('search',' ');
	}).on('input propertychange',function(){
    	$(this).removeAttr('assayerid');
    }).change(function(){
		if(!$(this).attr('assayerid')){
			$(this).val('');
		}
    });
}
//获取下拉框数据并填充
function materialSelect(){
	var cache={};
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
	}).on('input propertychange',function(){
    	$(this).removeAttr('materialid');
    }).change(function(){
		if(!$(this).attr('materialid')){
			$(this).val('');
		}
    });
};




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

