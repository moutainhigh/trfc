//整合url
	var URL = {
			selectorUrl:"/trfc/quality/sales/file/selector",
			copyUrl:"/trfc/quality/sales/batchnum/copy",
			checkUrl:"/trfc/quality/sales/batchnum/check",
			pageUrl:"/trfc/quality/sales/batchnum/page",
			deleteUrl:"/trfc/quality/sales/batchnum/delete",
			updateUrl:"/trfc/quality/sales/batchnum/update",
			addUrl:"/trfc/quality/sales/batchnum/addMain",
			editUrl:"/trfc/quality/sales/batchnum/editMain",
			saveUrl:"/trfc/quality/sales/batchnum/add",
			usersUrl:"/trfc/quality/sales/batchnum/userData"
	};

//(化验人)下拉框
function userSelect(){
	post2 = $.post(URL.usersUrl,{},function(result){
		if(result.code=='000000'){
			//填充数据
			fillUserContent(result.data);
		}else{
			layer.msg(result.error,{icon:5});
		}
	});
}
//填充数据
function fillUserContent(list){
	var select = $('#user_select');
	select.append("<option></option>");
	if(list){
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var msg = obj.name;
			var option = '<option value='+obj.id+'>'+msg+'</option>';
			select.append(option);
		}
	}
}




//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
//		time(获取指定时间的字符串) 默认返回当前时间
function getNowFormatDate(param,time) {
	var date ;
	//判断time参数是否存在
	if(time){
		date = new Date(time);
	}else{
		date = new Date();
	}
	var seperator1 = "-";
	var seperator2 = ":";
	//获取月份
	var month = date.getMonth() + 1;
	//获取日期
	var strDate = date.getDate();
	//月或者日 为个位数时前面加'0'
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	//判断返回结果
	if(param){
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		+ " " + date.getHours() + seperator2 + date.getMinutes()
		+ seperator2 + date.getSeconds();
	}else{
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	}
	return currentdate;
}
