var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
		main:{},
		util:{},
		URL: {
			mainUrl:"/trfc/salesArrive/main"
		}
}
PAGE.mod.util = {
}
PAGE.mod.main = {
		top: PAGE.mod,
		init:function(){
			this.bindEvent();
		},
		bindEvent:function(){
			var _this = this;
			$('#refreshBtn').off('click').on('click',function(){
				window.location.reload(true);
			});
			$('#readCardBtn').off('click').on('click',function(){
				//读卡
				showCardData();
			});
			$('#backBtn').off('click').on('click',function(){
				window.location.href = _this.top.URL.mainUrl;
			});
			function showCardData(){

				if(initCardReader()) {
					//打开读卡器
					readerOpen();
					//开打卡片获取卡号
					var cardno = openCard();
					if(cardno){
						//蜂鸣
						readerBeep();
						try{
							var obj = readObjFromCard();
							$('#detail_cardno').val(cardno);
							$('#detail_vehiclecode').val(obj.vehicleobj.code);
							$('#detail_vehicleno').val(obj.vehicleno);
							$('#detail_customername').val(obj.customerobj.name);
							$('#detail_materielname').val(obj.materielname);
							$('#detail_businesstype').val(obj.businesstype);
							$('#detail_packagetype').val(obj.packagetype);
							$('#detail_notice').val(obj.notice);
							$('#detail_takeamount').val(obj.takeamount);
							$('#detail_bags').val('待确定');
							$('#detail_spraycode').val(obj.spraycode);
							$('#detail_handset').val(obj.handset);
							$('#detail_status').val(obj.status);
						} catch (e) {
							layer.msg(e.Message);
						}
					}
					//关闭读卡器
					readerClose();
				}else{
					layer.msg('当前游览器不支持!(只兼容IE游览器)');
				}
			}

		}
}
$(function(){
	PAGE.mod.main.init();
});