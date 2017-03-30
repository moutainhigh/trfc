$(function() {
	var URL = {
			getAccessData : '/trfc/cardReissue/getAccessData',
			updateCard : '/trfc/cardReissue/updateCard',
			findOne: '/trfc/accessRecord/findOne',
			findSales:'/trfc/salesArrive/findOne',
			findPurchase:'/trfc/purchaseArrive/findOne',
			findSalesDetail:'/trfc/salesApplication/findOne',
			findPurchaseDateil:'/trfc/purchaseApplication/findOne',
			findPoundDateil:'/trfc/poundNote/purchase/findByBillid'
	};
	var ST = {
			'0':'未入厂',	
			'1':'一次过磅',	
			'2':'二次过磅',	
			'3':'作废',	
			'4':'发卡',	
			'5':'出厂',
			'6':'入厂',
			'7':'装车'
	};
	//业务类型
	var BT = {
			'1':'采购',
			'2':'销售',
			'3':'其他入库',
			'4':'其他出库'
	};

	//物料类型
	var MT = {
			'0':'袋装',
			'1':'水泥散装',
			'2':'其他散装'
	};

	//获取id
	var id = getId();
	//详情切换
	var cg_li = $('.cg_tabtit ul li');
	cg_li.click(function () {
		$(this).addClass('select').siblings().removeClass('select');
		var index = cg_li.index(this);
		$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
	});
	$('#readCard').click(readcard);
	$('#cardReissue').click(writecard);
	initPage();

	//写卡
	function writecard(){
		var obj = $('#business_detail').data('obj');
		if(initCardReader()) {
			//打开读卡器
			readerOpen();
			//开打卡片获取卡号
			var cardno = openCard();
			if(cardno){
				//蜂鸣
				readerBeep();
				try{

					writeDataToCard(obj.rfid.substr(0,16), 1);
					writeDataToCard(obj.rfid.substr(16),2);
					writeDataToCard(obj.vehicleno,4);
					writeDataToCard((obj.suppliername || obj.customername).substr(0,16),5);
					writeDataToCard((obj.suppliername || obj.customername).substr(16),6);
					writeDataToCard(obj.materielname,8);
					writeDataToCard(MT[obj.materieltype],9);
					writeDataToCard(BT[obj.businesstype],10);
					writeDataToCard(obj.noticecode,12);
					writeDataToCard(obj.batchnum,13);
					writeDataToCard(obj.vehiclecode || '',18);
					writeDataToCard(obj.supperlierremark || '',24);
					writeDataToCard(obj.minemouthname || obj.spraycode,28);
					writeDataToCard(obj.takeamount || '',32);
					writeDataToCard(obj.arrivalamount || '',17);
					updateCardno(cardno,obj);
				} catch (e) {
					alert(e.Message);
				}

			}
			//关闭读卡器
			readerClose();
			//	layer.alert('补卡成功');
		}else{
			layer.alert('当前游览器不支持!(只兼容IE游览器)');
		}
	}

	//更新卡号到通知单
	function updateCardno(cardno,obj) {
		var param = {
				icardno:cardno,
				noticeid:obj.noticeid,
				businesstype:obj.businesstype
		};
		$.post(URL.updateCard,param,function(result) {
			if('000000' === result.code) {
				layer.alert('补卡成功');
			}else {
				layer.msg(result.error,{icon:5});
			}
		});
	}

	//读卡
	function readcard() {
		//切换至读卡详情
		$('.cg_tabtit ul li').eq(2).click();
		if(initCardReader()) {
		//打开读卡器
		readerOpen();
		//开打卡片获取卡号
		var cardno = openCard();
		if(cardno){
			//蜂鸣
			readerBeep();
			try{
				var inputs4 = $('#icard_detail > div > input');
				inputs4.eq(0).val(cardno);
				inputs4.eq(1).val(getDataFromCard(18));
				inputs4.eq(2).val(getDataFromCard(4));
				inputs4.eq(3).val(getDataFromCard(5)+getDataFromCard(6));
				inputs4.eq(4).val(getDataFromCard(8));
				inputs4.eq(5).val(getDataFromCard(9));
				inputs4.eq(6).val(getDataFromCard(12));
				inputs4.eq(7).val(getDataFromCard(32) || getDataFromCard(17));
				inputs4.eq(8).val('有效');
				inputs4.eq(9).val(getDataFromCard(10));
				inputs4.eq(10).val('');
				inputs4.eq(11).val(getDataFromCard(28));
				inputs4.eq(12).val(getDataFromCard(24));
			} catch (e) {
				alert(e.Message);
			}
		}
		//关闭读卡器
		readerClose();
		}else{
			layer.alert('当前游览器不支持!(只兼容IE游览器)');
		}

	}

	//获取id
	function getId() {
		var href = window.location.href;
		var strs = href.split('?id=');
		return strs[1];
	}

	//初始化页面
	function initPage() {
		$.post(URL.getAccessData,{id:id},function(result) {
			if('000000' === result.code){
				var record = result.data;
				$('#business_detail').data('obj',record);
				//判断业务类型
				if(record.businesstype === '1') {
					showPurchaseData(record);
				}else if(record.businesstype === '2') {
					showSalesData(record);
				}else if(record.businesstype === '3') {

				}else if(record.businesstype === '4') {

				}
				//读卡信息 初始化
				var inputs4 = $('#icard_detail > div > input');
				inputs4.val('');
			}
		});

		//返回按钮 返回到列表
		$('#goBack').click(function() {
			window.location.href = "/trfc/cardReissue/main";
		});
		//刷新
		$('#fresh').click(function() {
			window.location.reload();
		});
	};

	function showPurchaseData(record) {
		$('#business_detail').append('<div class="daohuo_add_solo">'
				+'<label>供应商备注：</label>'
				+'<input type="text"  readOnly="true">'
				+'</div>');
		var inputs1 = $('#business_detail > div > input');
		var labels1 = $('#business_detail > div > label');
		labels1.eq(5).html('供应商：');
		labels1.eq(6).html('到货量：');
		labels1.eq(9).html('矿口：');

		var inputs2 = $('#arrive_detail > div > input');
		var labels2 = $('#arrive_detail > div > label');
		labels2.eq(3).html('采购组织：');
		labels2.eq(4).html('供应商：');
		labels2.eq(9).html('到货量：');
		var inputs3 = $('#pound_detail > div > input');
		//业务详情
		inputs1.eq(0).val(record.accesscode);
		inputs1.eq(1).val(record.noticecode);
		inputs1.eq(2).val(getNowFormatDate(true,record.entertime));
		inputs1.eq(3).val(record.vehicleno);
		inputs1.eq(4).val(record.materielname);
		inputs1.eq(5).val(record.suppliername);
		inputs1.eq(6).val(record.arrivalamount);
		inputs1.eq(7).val(record.rfid);
		inputs1.eq(8).val(record.icardno);
		inputs1.eq(9).val(record.minemouthname);
		inputs1.eq(10).val(record.supplierremark);
		//订单详情
		inputs2.eq(0).val(record.noticecode);
		inputs2.eq(1).val(record.applicationcode);
		inputs2.eq(2).val(record.billtime);
		inputs2.eq(3).val(record.orgname);
		inputs2.eq(4).val(record.suppliername);
		inputs2.eq(5).val(record.materielname);
		inputs2.eq(6).val(record.vehicleno);
		inputs2.eq(7).val(record.drivername);
		inputs2.eq(8).val(record.driveridentityno);
		inputs2.eq(9).val(record.arrivalamount);
		inputs2.eq(10).val(record.departmentname);
		inputs2.eq(11).val(ST[record.status]);
		inputs2.eq(12).val(record.makebilltime);
		inputs2.eq(13).val(record.makebillname);
		inputs2.eq(14).val(record.applicationremark);
		//磅单详情
		inputs3.eq(0).val(record.poundcode);
		inputs3.eq(1).val(record.receivedepartmentname);
		inputs3.eq(2).val(record.suppliername);
		inputs3.eq(3).val(record.materielname);
		inputs3.eq(4).val(record.warehousename);
		inputs3.eq(5).val(record.vehicleno);
		inputs3.eq(6).val(record.grossweight);
		inputs3.eq(7).val(record.tareweight);
		inputs3.eq(8).val(record.netweight);
		inputs3.eq(9).val(record.drivername);
		inputs3.eq(10).val(record.driveridentityno);
		inputs3.eq(11).val(record.weighername);
		inputs3.eq(12).val(getNowFormatDate(true, record.lighttime));
		inputs3.eq(13).val(getNowFormatDate(true, record.weighttime));
		inputs3.eq(14).val(record.noticecode);
		inputs3.eq(15).val(record.applicationcode);
		inputs3.eq(16).val(record.purchasesum);
		inputs3.eq(17).val(record.price);
	}
	function showSalesData(record) {
		$('#pound_detail').append('<div class="daohuo_add_solo"><label>订单一量：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单二量：</label><input type="text" value="0.000" readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单三量：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单一单价：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单二单价：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单三单价：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单一金额：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单二金额：</label><input type="text" value="0.000"  readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>订单三金额：</label><input type="text" value="0.000"  readOnly="true"></div>')
		var inputs1 = $('#business_detail > div > input');
		var inputs2 = $('#arrive_detail > div > input');
		var inputs3 = $('#pound_detail > div > input');
		var labels3 = $('#pound_detail > div > label');


		labels3.eq(1).html('发货单位：');
		labels3.eq(2).html('客户：');
		labels3.eq(4).html('批号：');
		labels3.eq(15).html('订单一：');
		labels3.eq(16).html('订单二：');
		labels3.eq(17).html('订单三：');

		$('#icard_detail').append('<div class="daohuo_add_solo"><label>Nice44：</label><input type="text" readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>Nice45：</label><input type="text" readOnly="true"></div>')
		.append('<div class="daohuo_add_solo"><label>Nice46：</label><input type="text" readOnly="true"></div>');
		var labels4 = $('#icard_detail > div > label');
		labels4.eq(6).html('提货单号：');
		labels4.eq(7).html('预提量：');
		labels4.eq(11).html('喷码：');
		labels4.eq(12).html('袋数：');
		//业务详情
		inputs1.eq(0).val(record.accesscode);
		inputs1.eq(1).val(record.noticecode);
		inputs1.eq(2).val(getNowFormatDate(true,record.entertime));
		inputs1.eq(3).val(record.vehicleno);
		inputs1.eq(4).val(record.materielname);
		inputs1.eq(5).val(record.customername);
		inputs1.eq(6).val(record.takeamount);
		inputs1.eq(7).val(record.rfid);
		inputs1.eq(8).val(record.icardno);
		inputs1.eq(9).val(record.spraycode);
		//订单详情
		inputs2.eq(0).val(record.noticecode);
		inputs2.eq(1).val(record.applicationcode);
		inputs2.eq(2).val(record.billtime);
		inputs2.eq(3).val(record.orgname);
		inputs2.eq(4).val(record.customername);
		inputs2.eq(5).val(record.materielname);
		inputs2.eq(6).val(record.vehicleno);
		inputs2.eq(7).val(record.drivername);
		inputs2.eq(8).val(record.driveridentityno);
		inputs2.eq(9).val(record.takeamount);
		inputs2.eq(10).val(record.departmentname);
		inputs2.eq(11).val(ST[record.status]);
		inputs2.eq(12).val(record.makebilltime);
		inputs2.eq(13).val(record.makebillname);
		inputs2.eq(14).val(record.applicationremark);
		//磅单详情
		inputs3.eq(0).val(record.poundcode);
		inputs3.eq(1).val(record.senddepartmentname);
		inputs3.eq(2).val(record.customername);
		inputs3.eq(3).val(record.materielname);
		inputs3.eq(4).val(record.batchnum);
		inputs3.eq(5).val(record.vehicleno);
		inputs3.eq(6).val(record.grossweight);
		inputs3.eq(7).val(record.tareweight);
		inputs3.eq(8).val(record.netweight);
		inputs3.eq(9).val(record.drivername);
		inputs3.eq(10).val(record.driveridentityno);
		inputs3.eq(11).val(record.weighername);
		inputs3.eq(12).val(getNowFormatDate(true, record.lighttime));
		inputs3.eq(13).val(getNowFormatDate(true, record.weighttime));
		inputs3.eq(14).val(record.noticecode);
		inputs3.eq(15).val(record.applicationcode);
		inputs3.eq(18).val(record.purchasesum || 0.00);
		inputs3.eq(21).val(record.price || 0.00);
		inputs3.eq(24).val(record.purchasesum*record.price || 0.00)
	}

	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
	//time(获取指定时间的字符串) 默认返回当前时间
	function getNowFormatDate(param,time) {
		var date ;
//		判断time参数是否存在
		if(time){
			date = new Date(time);
		}else{
			date = new Date();
		}
		var seperator1 = "-";
		var seperator2 = ":";
//		获取月份
		var month = date.getMonth() + 1;
//		获取日期
		var strDate = date.getDate();
//		月或者日 为个位数时前面加'0'
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
//		判断返回结果
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + hours + seperator2 + minutes
			+ seperator2 + seconds;
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
	}

});