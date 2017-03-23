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


	var cg_li = $('.cg_tabtit ul li');
	cg_li.click(function () {
		$(this).addClass('select').siblings().removeClass('select');
		var index = cg_li.index(this);
		$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
	});
	var id = getId();
	initPage();

	//获取id
	function getId() {
		var href = window.location.href;
		var strs = href.split('?id=');
		return strs[1];
	}
	//初始化页面
	function initPage() {
		$.post(URL.findOne,{id:id},function(result) {
			if('000000' === result.code){
				var record = result.data;
				//判断业务类型
				if(record.businesstype === '1') {
					showSalesData(record);
				}else if(record.businesstype === '2') {
					showPurchaseData(record);
				}else if(record.businesstype === '3') {

				}else if(record.businesstype === '4') {

				}
			}
		});

		$('#goBack').click(function() {
			window.location.href = "/trfc/cardReissue/main";
		});
		$('#fresh').click(function() {
			window.location.reload();
		});
	};

	function showSalesData(record) {
		$('#business_detail').append('<div class="daohuo_add_solo">'
				+'<label>供应商备注：</label>'
				+'<input type="text"  readOnly="readOnly">'
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
		$.post(URL.findPurchase,{id:record.noticeid},function(result) {
			if('000000' === result.code) {
				if(result.data) {
					var arrive = result.data;
					$.post(URL.findPurchaseDateil,{id:arrive.billid},function(result) {
						if('000000' === result.code) {
							var detail = result.data;
							$.post(URL.findPoundDateil,{billid:arrive.billid},function(result) {
								if('000000' === result.code) {
									var pound = result.data;
									inputs1.eq(0).val(record.code);
									inputs1.eq(1).val(record.noticecode);
									inputs1.eq(2).val(getNowFormatDate(true,record.entertime));
									inputs1.eq(3).val(arrive.vehicleno);
									inputs1.eq(4).val(detail.listdetail[0].materielname);
									inputs1.eq(5).val(detail.suppliername);
									inputs1.eq(6).val(detail.sumnum);
									inputs1.eq(7).val(arrive.vehiclerfid);
									inputs1.eq(8).val(arrive.icordno);
									inputs1.eq(9).val(detail.minemouthname);
									inputs1.eq(10).val(detail.supplierremark);
									inputs2.eq(0).val(record.noticecode);
									inputs2.eq(1).val(detail.code);
									inputs2.eq(2).val(getNowFormatDate(true,arrive.makebilltime));
									inputs2.eq(3).val(detail.orgname);
									inputs2.eq(4).val(detail.suppliername);
									inputs2.eq(5).val(detail.listdetail[0].materielname);
									inputs2.eq(6).val(arrive.vehicleno);
									inputs2.eq(7).val(arrive.drivername);
									inputs2.eq(8).val(arrive.driveridentityno);
									inputs2.eq(9).val(detail.departmentname);
									inputs2.eq(10).val(detail.departmentname);
									inputs2.eq(11).val(arrive.status);
									inputs2.eq(12).val(getNowFormatDate(true,detail.makebilltime));
									inputs2.eq(13).val(detail.makebillname);
									inputs2.eq(14).val(detail.remark);
									inputs3.eq(0).val(pound.code);
									inputs3.eq(1).val('待开发');
									inputs3.eq(2).val(detail.suppliername);
									inputs3.eq(3).val(detail.listdetail[0].materielname);
									inputs3.eq(4).val('待开发');
									inputs3.eq(5).val(arrive.vehicleno);
									inputs3.eq(6).val(pound.grossweight);
									inputs3.eq(7).val(pound.tareweight);
									inputs3.eq(8).val(pound.netweight);
									inputs3.eq(9).val(arrive.drivername);
									inputs3.eq(10).val(arrive.driveridentityno);
									inputs3.eq(11).val(pound.weighername);
									inputs3.eq(12).val(getNowFormatDate(true, pound.lighttime));
									inputs3.eq(13).val(getNowFormatDate(true, pound.weighttime));
									inputs3.eq(14).val(record.noticecode);
									inputs3.eq(15).val(detail.code);
									inputs3.eq(16).val(detail.sumnum);
									inputs3.eq(17).val(pound.remark);
								}else{
									layer.msg(result.error,{icon:5});
								}
							});
						}else{
							layer.msg(result.error,{icon:5});
						}
					});
				}
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	function showPurchaseData(record) {
		$('#pound_detail').append('<div class="daohuo_add_solo"><label>订单一量：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单二量：</label><input type="text" value="0.000" readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单三量：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单一单价：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单二单价：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单三单价：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单一金额：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单二金额：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
				.append('<div class="daohuo_add_solo"><label>订单三金额：</label><input type="text" value="0.000"  readOnly="readOnly"></div>')
		var inputs1 = $('#business_detail > div > input');
		var inputs2 = $('#arrive_detail > div > input');
		var inputs3 = $('#pound_detail > div > input');
		var labels3 = $('#pound_detail > div > label');
		labels3.eq(1).html('收货单位：');
		labels3.eq(2).html('客户：');
		labels3.eq(4).html('批号：');
		labels3.eq(15).html('订单一：');
		labels3.eq(16).html('订单二：');
		labels3.eq(17).html('订单三：');
		$.post(URL.findPurchase,{id:record.noticeid},function(result) {
			if('000000' === result.code) {
				if(result.data) {
					var arrive = result.data;
					$.post(URL.findPurchaseDateil,{id:arrive.billid},function(result) {
						if('000000' === result.code) {
							var detail = result.data;
							$.post(URL.findPoundDateil,{billid:arrive.billid},function(result) {
								if('000000' === result.code) {
									var pound = result.data;
									inputs1.eq(0).val(record.code);
									inputs1.eq(1).val(record.noticecode);
									inputs1.eq(2).val(getNowFormatDate(true,record.entertime));
									inputs1.eq(3).val(arrive.vehicleno);
									inputs1.eq(4).val(detail.listdetail[0].materielname);
									inputs1.eq(5).val(detail.suppliername);
									inputs1.eq(6).val(detail.sumnum);
									inputs1.eq(7).val(arrive.vehiclerfid);
									inputs1.eq(8).val(arrive.icordno);
									inputs1.eq(9).val(detail.minemouthname);
									inputs1.eq(10).val(detail.supplierremark);
									inputs2.eq(0).val(record.noticecode);
									inputs2.eq(1).val(detail.code);
									inputs2.eq(2).val(getNowFormatDate(true,arrive.makebilltime));
									inputs2.eq(3).val(detail.orgname);
									inputs2.eq(4).val(detail.suppliername);
									inputs2.eq(5).val(detail.listdetail[0].materielname);
									inputs2.eq(6).val(arrive.vehicleno);
									inputs2.eq(7).val(arrive.drivername);
									inputs2.eq(8).val(arrive.driveridentityno);
									inputs2.eq(9).val(detail.departmentname);
									inputs2.eq(10).val(detail.departmentname);
									inputs2.eq(11).val(arrive.status);
									inputs2.eq(12).val(getNowFormatDate(true,detail.makebilltime));
									inputs2.eq(13).val(detail.makebillname);
									inputs2.eq(14).val(detail.remark);
									inputs3.eq(0).val(pound.code);
									inputs3.eq(1).val('待开发');
									inputs3.eq(2).val(detail.suppliername);
									inputs3.eq(3).val(detail.listdetail[0].materielname);
									inputs3.eq(4).val('待开发');
									inputs3.eq(5).val(arrive.vehicleno);
									inputs3.eq(6).val(pound.grossweight);
									inputs3.eq(7).val(pound.tareweight);
									inputs3.eq(8).val(pound.netweight);
									inputs3.eq(9).val(arrive.drivername);
									inputs3.eq(10).val(arrive.driveridentityno);
									inputs3.eq(11).val(pound.weighername);
									inputs3.eq(12).val(getNowFormatDate(true, pound.lighttime));
									inputs3.eq(13).val(getNowFormatDate(true, pound.weighttime));
									inputs3.eq(14).val(record.noticecode);
									inputs3.eq(15).val(detail.code);
									inputs3.eq(16).val(detail.sumnum);
									inputs3.eq(17).val(pound.remark);
								}else{
									layer.msg(result.error,{icon:5});
								}
							});
						}else{
							layer.msg(result.error,{icon:5});
						}
					});
				}
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
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