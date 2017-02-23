var URL = {
		mainUrl:"/trfc/quality/sales/file/supplierScheme/main",
		addMainUrl:"/trfc/quality/sales/file/supplierScheme/addMain",
		pageUrl:"/trfc/quality/sales/file/supplierScheme/page",
		deleteUrl:"/trfc/quality/sales/file/supplierScheme/delete",
		updateUrl:"/trfc/quality/sales/file/supplierScheme/update",
		saveUrl:"/trfc/quality/sales/file/supplierScheme/add",
		codeUrl:"/trfc/quality/sales/file/supplierScheme/getCode",
		updateCodeUrl:"/trfc/quality/sales/file/supplierScheme/updateCode",
		getSchemeUrl:"/trfc/quality/sales/file/supplierScheme/getSchemeData",
		getMaterialUrl:"/trfc/quality/sales/file/supplierScheme/getMaterialData",
		getSupplierUrl:"/trfc/quality/sales/file/supplierScheme/getSupplierData",
		getDetailUrl:"/trfc/quality/sales/file/supplierScheme/getDetailData",
		getCodeUrl:"/trfc/quality/sales/file/supplierScheme/getCode",
		updateCodeUrl:"/trfc/quality/sales/file/supplierScheme/updateCode"
};

//设置一个公共变量
var supplierDATA = {};
//获取用户id
var userid = $('.user').attr('userid');
//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
//time(获取指定时间的字符串) 默认返回当前时间//获取指定时间的字符串
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
//	判断返回结果
	if(param){
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		+ " " + date.getHours() + seperator2 + date.getMinutes()
		+ seperator2 + date.getSeconds();
	}else{
		var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
	}
	return currentdate;
}

//获取下拉框数据并填充
function materialSelect(){
	//获取数据
	$selector = $.post(URL.getMaterialUrl,{state:"1"},function(result){
		if(result.code=='000000'){
			//填充数据
			fillMaterialSelect(result.data);
		}else{
			layer.msg(result.error, {icon:5});
		}
	});
}
//填充数据
function fillMaterialSelect(list){
	var selecter = $('.materialSelect').html('');
	//设置默认值
	selecter.append("<option></option>");
	if(list){
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var msg = obj.name;
			if(obj.spec){
				msg = obj.name + '（'+obj.spec+'）';
			}
			var option = '<option value='+obj.id+'>'+msg+'</option>';
			//追加数据
			selecter.append(option);
		}
	}
}
//获取下拉框数据并填充
function supplierSelect(){
	//获取数据
	$selector = $.post(URL.getSupplierUrl,{state:"1"},function(result){
		if(result.code=='000000'){
			//填充数据
			fillSupplierSelect(result.data);
		}else{
			layer.msg(result.error, {icon:5});
		}
	});
}
//填充数据
function fillSupplierSelect(list){
	var selecter = $('.supplierSelect').html('');
	//设置默认值
	selecter.append("<option></option>");
	if(list){
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var msg = obj.name;
			if(obj.spec){
				msg = obj.name + '（'+obj.spec+'）';
			}
			var option = '<option value='+obj.id+'>'+msg+'</option>';
			//追加数据
			selecter.append(option);
		}
	}
}

