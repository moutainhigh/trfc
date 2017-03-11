
	var MWRFATL = new ActiveXObject("MWREADERRF.mwReaderRfCtrl.1"); //启用控件

	//打开读卡器
	function readerOpen() {
		try {
			var version = MWRFATL.openReader(4, 9600);
			if (MWRFATL.LastRet != 0) {
				layer.alert("打开读写器失败");
				return;
			}
			MWRFATL.readerLoadKey(0, 1, "ffffffffffff"); //加载1扇区密码,对M1卡操作时使用加载密码认证
			if (MWRFATL.LastRet != 0) {
				layer.alert("扇区加载密码失败");
			}
		}
		catch (e) {
			alert(e.Message);
		}
	}
	//关闭读写器
	function readerClose() {
		try {
			var result = MWRFATL.closeReader();
			if (MWRFATL.LastRet != 0) {
				layer.alert("关闭读写器失败");
			}
		}
		catch (e) {
			alert(e.Message);
		}
	}
	  //读写器鸣响
    function readerBeep() {
        try {
            MWRFATL.readerBeep(30);
            if (MWRFATL.LastRet != 0) {
               layer.alert("读写器鸣响失败");
            }
        }
        catch (e) {
            alert(e.Message);
        }
    }
    //读卡并返回卡号
	function openCard(){
		var result = MWRFATL.openCard(1, 10);  //打开卡片,以10进制字符串显示卡号
		if (MWRFATL.LastRet != 0) {
			layer.alert("打开卡片失败");
			return;
		}
		return result;
	}
	//读取扇区内容
	function getICCardData(section){
		try{
			MWRFATL.cardVerifyPassword(0, section); //加载密码认证,此函数传入的是扇区号
			if (MWRFATL.LastRet != 0) {
				layer.alert("验证密码失败");
				return;
			}
			var msg = "";
			for(i=0;i<3;i++){
				msg += MWRFATL.cardRead(section*4+i);
				if (MWRFATL.LastRet != 0) {
					layer.alert("获取数据失败");
					return;
				}
			}
			return msg;
		}
		catch (e) {
			alert(e.Message);
		}
		
	}






