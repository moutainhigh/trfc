var MWRFATL;
//初始化控件
function initCardReader(){
	if(!MWRFATL){
		try {
			MWRFATL = new ActiveXObject("MWREADERRF.mwReaderRfCtrl.1"); //启用控件
		} catch (e) {
			return false;
		}
	}
	return true;
}
//打开读卡器
function readerOpen() {
	try {
		var version = MWRFATL.openReader(3, 9600);
		if (MWRFATL.LastRet != 0) {
			layer.msg("打开读写器失败");
			return;
		}
		MWRFATL.readerLoadKey(0, 2, "ffffffffffff"); //加载2扇区密码,对M1卡操作时使用加载密码认证
		if (MWRFATL.LastRet != 0) {
			//console.log("加载扇区密码失败");
			return;
		}
	}
	catch (e) {
		layer.msg(e.Message);
	}
}
//关闭读写器
function readerClose() {
	try {
		var result = MWRFATL.closeReader();
		if (MWRFATL.LastRet != 0) {
			layer.msg("关闭读写器失败");
		}
	}
	catch (e) {
		layer.msg(e.Message);
	}
}
//读写器鸣响
function readerBeep() {
	try {
		MWRFATL.readerBeep(30);
		if (MWRFATL.LastRet != 0) {
		}
	}
	catch (e) {
		layer.msg(e.Message);
	}
}
//读卡并返回卡号
function openCard(){
	var result = MWRFATL.openCard(1, 10);  //打开卡片,以10进制字符串显示卡号
	if (MWRFATL.LastRet != 0) {
		layer.msg("打开卡片失败");
		readerClose();
		return;
	}
	return result;
}
//读取区域块内容
function getDataFromCard(index){

	MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
	if (MWRFATL.LastRet != 0) {
		//console.log(index + '块,密码验证失败')
		return;
	}
	var msg = MWRFATL.cardRead(index);
	if (MWRFATL.LastRet != 0) {
		layer.msg("获取数据失败");
		return;
	}

	return (msg == '0000000000000000') ? '':msg;

}



//msg:待写入的数据   index:局域块(64块)
function writeDataToCard(msg,index){
	if(!msg){
		msg = '0000000000000000';
	}
		MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
		if (MWRFATL.LastRet != 0) {
			//console.log(index + '块,密码验证失败')
			return;
		}
		MWRFATL.cardWrite(index,msg); //大卡座卡片发送指令(取随机数)
		if (MWRFATL.LastRet != 0) {
			layer.msg("写入数据失败");
			return;
		}
}
//读取区域块内容
function getDataFromCardHex(index){

	MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
	if (MWRFATL.LastRet != 0) {
		//console.log(index + '块,密码验证失败')
		return;
	}
	var msg = MWRFATL.cardReadHex(index);
	if (MWRFATL.LastRet != 0) {
		layer.msg("获取数据失败");
		return;
	}

	return (msg == '00000000000000000000000000000000') ? '':msg;

}
//16进制的数据
//msg:待写入的数据   index:局域块(64块)
function writeDataToCardHex( msg , index ) {
	if(!msg || msg.length!=32){
		msg = '00000000000000000000000000000000';
	}else{
		msg = $.trim(msg);
	}
		MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
		if (MWRFATL.LastRet != 0) {
			//console.log(index + '块,密码验证失败')
			return;
		}
		MWRFATL.cardWriteHex( index , msg ); //大卡座卡片发送指令(取随机数)
		if (MWRFATL.LastRet != 0) {
			layer.msg("写入数据失败");
			return;
		}
	
}
//将对象写入卡
function writeObjToCard(obj) {
	writeDataToCard(obj.rfid,1);
	writeDataToCard(obj.vehicleno,2);
	writeDataToCardHex(obj.vehicleid.toUpperCase(),4);
	writeDataToCard(obj.materielname,5);
	writeDataToCard(obj.packagetype,6);
	writeDataToCard(obj.businesstype,8);
	writeDataToCard(obj.notice,9);
	writeDataToCard(obj.batchnum,10);
	writeDataToCard(obj.arrivalamount,12);
	writeDataToCard(obj.minemouthname,13);
	writeDataToCard(obj.takeamount,14);
	writeDataToCard(obj.status,16);
	writeDataToCard((obj.supplierid || obj.customerid).substr(0,16),20);
	writeDataToCard((obj.supplierid || obj.customerid).substr(16),21);
	writeDataToCard(obj.supplierremark,50);
	writeDataToCard(obj.warehousename,34);
	writeDataToCard(obj.spraycode,52);
}
//获取卡中数据,返回对象
function readObjFromCard() {
	//业务类型
	var BT = {
			'0':'采购到货',
			'1':'采购退货',
			'2':'销售提货',
			'3':'销售退货',
			'4':'厂内倒运',
			'5':'其他入库',
			'6':'其它入库退货',
			'7':'其他出库',
			'8':'其它出库退货',
			'9':'工程车辆'
	};
	//物料类型
	var MT = {
			'0':'袋装',
			'1':'水泥散装',
			'2':'其他散装'
	};
	//状态
	var STATUS = {
			'0' : '未入厂',
			'1' : '一次过磅',
			'2' : '二次过磅',
			'3' : '作废',
			'4' : '发卡',
			'5' : '出厂',
			'6' : '入厂',
			'7' : '装车'
	};
	var rfid = getDataFromCard(1);
	var vehicleno = getDataFromCard(2);
	var vehicleid = getDataFromCardHex(4);
	var materielname = getDataFromCard(5);
	var packagetype = getDataFromCard(6);
	var businesstype = getDataFromCard(8);
	var notice = getDataFromCard(9);
	var batchnum = getDataFromCard(10);
	var arrivalamount = getDataFromCard(12);
	var minemouthname = getDataFromCard(13);
	var takeamount = getDataFromCard(14);
	var status = getDataFromCard(16);
	var salesReturn = getDataFromCard(29)==1?'退货':'';
	var suremount = getDataFromCard(28);
	var id = getDataFromCard(20);
	id += getDataFromCard(21);
	var supplierid = '';
	var customerid = '';
	if(businesstype != '1' && businesstype != '3' && businesstype != '11' && businesstype != '31' ){
		customerid = id;
	}else{
		supplierid = id;
	}
	var supplierramark = getDataFromCard(50);
	var warehousename = getDataFromCard(34);
	var spraycode = getDataFromCard(52);
	var handset = getDataFromCard(37);
	handset = (handset == '1') ? '已确认':'';
	var vehicleobj = {};
	if(vehicleid){
		$.ajax({
			url:'/trfc/vehicle/findOne',
			data:{id:vehicleid},
			async:false,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				vehicleobj = result;
			}
		});
	}
	var supplierobj = {};
	if(supplierid){
		$.ajax({
			url:'/trfc/supplier/findOne',
			data:{id:supplierid},
			async:false,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				supplierobj = result;
			}
		});
	}
//	var warehouseobj = {};
//	if(warehouseid){
//		$.ajax({
//			url:'/trfc/warehouse/findOne',
//			data:{id:warehouseid},
//			async:false,
//			cache:false,
//			dataType:'json',
//			type:'post',
//			success:function(result){
//				warehouseobj = result;
//			}
//		});
//	}
	var customerobj = {};
	if(customerid){
		$.ajax({
			url:'/trfc/customer/findOne',
			data:{id:customerid},
			async:false,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				customerobj = result;
			}
		});
	};


	return {
		rfid : rfid,
		vehicleno : vehicleno,
		vehicleid : vehicleid,
		materielname : materielname,
		packagetype : MT[packagetype],
		businesstype : BT[businesstype],
		notice : notice,
		batchnum : batchnum,
		arrivalamount : arrivalamount,
		minemouthname : minemouthname,
		takeamount : takeamount,
		status : STATUS[status],
		supplierid : supplierid,
		supplierramark : supplierramark,
		vehicleobj:vehicleobj,
		supplierobj:supplierobj,
		warehousename:warehousename,
		customerobj:customerobj,
		salesReturn:salesReturn,
		suremount:suremount,
		spraycode:spraycode,
		handset:handset
	}

}


