var MWRFATL;
//初始化控件
function initCardReader(){
	try {
		MWRFATL = new ActiveXObject("MWREADERRF.mwReaderRfCtrl.1"); //启用控件
	} catch (e) {
		return false;
	}
	return true;
}
//打开读卡器
function readerOpen() {
	try {
		var version = MWRFATL.openReader(4, 9600);
		if (MWRFATL.LastRet != 0) {
			layer.msg("打开读写器失败");
			return;
		}
		MWRFATL.readerLoadKey(0, 2, "ffffffffffff"); //加载2扇区密码,对M1卡操作时使用加载密码认证
		if (MWRFATL.LastRet != 0) {
			layer.msg("扇区加载密码失败");
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
			layer.msg("读写器鸣响失败");
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
		return;
	}
	return result;
}
//读取区域块内容
function getDataFromCard(index){

	MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
	if (MWRFATL.LastRet != 0) {
		layer.msg("验证密码失败");
		return;
	}
	var msg = MWRFATL.cardRead(index);
	if (MWRFATL.LastRet != 0) {
		layer.msg("获取数据失败");
		return;
	}

	return msg;

}

//msg:待写入的数据   index:局域块(64块)
function writeDataToCard(msg,index){
	MWRFATL.cardVerifyPassword(0, Math.floor(index/4)); //加载密码认证,此函数传入的是扇区号
	if (MWRFATL.LastRet != 0) {
		layer.msg("验证密码失败");
		return;
	}
	MWRFATL.cardWrite(index,msg); //大卡座卡片发送指令(取随机数)
	if (MWRFATL.LastRet != 0) {
		layer.msg("写入数据失败");
		return;
	}
}




