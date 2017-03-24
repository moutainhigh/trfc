$(function() {
	var URL = {
			getAccessData : '/trfc/cardReissue/getAccessData',
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
	initPage();
	
	
	
	//读卡
	function readcard() {
		//切换至读卡详情
		$('.cg_tabtit ul li').eq(2).click();
		//打开读卡器
		readerOpen();
		//开打卡片获取卡号
		var cardno = openCard();
		if(cardno){
			//蜂鸣
			readerBeep();
			var inputs4 = $('#icard_detail > div > input');
			inputs4.eq(0).val(cardno);
			inputs4.eq(2).val(getICCardData(7));
			inputs4.eq(3).val(getICCardData(4));
			inputs4.eq(4).val(getICCardData(5));
			inputs4.eq(6).val(getICCardData(3));
			inputs4.eq(11).val(getICCardData(6));
			inputs4.eq(12).val(getICCardData(8));
		}
		//关闭读卡器
		readerClose();
		
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
				//判断业务类型
				if(record.businesstype === '1') {
					showPurchaseData(record);
				}else if(record.businesstype === '2') {
					showSalesData(record);
				}else if(record.businesstype === '3') {

				}else if(record.businesstype === '4') {

				}
				//读卡信息
				var inputs4 = $('#icard_detail > div > input');
				inputs4.val('');
			}
		});

		$('#goBack').click(function() {
			window.location.href = "/trfc/cardReissue/main";
		});
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