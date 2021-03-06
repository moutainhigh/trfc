;(function($, win){
	var URL = {
			purchaseArriveMain: '/trfc/purchaseArrive/main',
			pageGroupMateriel: '/trfc/purchaseApplication/pageGroupMateriel',
			add: '/trfc/purchaseArrive/add',
			addVehicle: '/trfc/vehicle/add',
			addDriver: '/trfc/driver/add',
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			selectByMaterialid:"/trfc/miningpoint/selectByMaterialid"
	};
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	function initAutoComplete(){
		var cache = {};
		$("#vehicle").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var vehicle = cache['vehicle'] || {};
				if ( term in vehicle ) {
					response( vehicle[ term ] );
					return;
				}
				$.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
					vehicle[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.vehicleno;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id).attr('vehiclecode',ui.item.code);
				$('#rfid').val(ui.item.rfid);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('vehicleid').removeAttr('vehiclecode');
		}).change(function(){
			if(!$(this).attr('vehicleid') && !$(this).attr('vehiclecode')){
				$(this).val('');
			}
		});
		$("#driver").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var driver = cache['driver'] || {};
				if ( term in driver ) {
					response( driver[ term ] );
					return;
				}
				$.post( URL.driverAutoCompleteSearch, request, function( data, status, xhr ) {
					driver[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('driverid', ui.item.id);
				$('#identityno').val(ui.item.identityno);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('driverid');
		}).change(function(){
			if(!$(this).attr('driverid')){
				$(this).val('');
			}
		});
		$("#materiel").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var materiel = cache['materiel'] || {};
				if ( term in materiel ) {
					response( materiel[ term ] );
					return;
				}
				$.post( URL.materielAutoCompleteSearch, request, function( data, status, xhr ) {
					materiel[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('materielid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('materielid');
		}).change(function(){
			if(!$(this).attr('materielid')){
				$(this).val('');
			}
		});
		$("#supplier").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var supplier = cache['supplier'] || {};
				if ( term in supplier ) {
					response( supplier[ term ] );
					return;
				}
				$.post( URL.supplierAutoCompleteSearch, request, function( data, status, xhr ) {
					supplier[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('supplierid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('supplierid');
		}).change(function(){
			if(!$(this).attr('supplierid')){
				$(this).val('');
			}
		});
	}
	//绑定按钮事件
	function initBindEvent(){
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload(true);
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				addPurchaseArrive();
			}
		});
		//保存写卡
		$('#addAndAddCardBtn').off('click').on('click',function(){
			writeCardAction();
		});
		//返回按钮
		$('#backBtn').off('click').on('click',function(){
			win.location.href = URL.purchaseArriveMain;
		});
		//打开订单列表
		$('#billcode').off('click').on('click',function(){
			queryPurchaseApplication(1);
			$('#altbill').modal('show');
		});
		//订单搜索按钮
		$('#PurchaseApplicationSearchBtn').off('click').on('click',function(){
			queryPurchaseApplication(1);
		});
		//新增车辆提交按钮
		$('#addVehicleCommitBtn').off('click').on('click',function(){
			if($('#vehicleAddView').is(':visible')){
				saveVehicle();
			}
		});
		//新增司机提交按钮
		$('#addDriverCommitBtn').off('click').on('click',function(){
			if($('#driverAddView').is(':visible')){
				saveDriver();
			}
		});
		//校验到货量
		$('#arrivalamount').off('keyup').on('keyup',function(){
			var arrivalamount = $(this).val();
			if(!arrivalamount || !$.isNumeric(arrivalamount)){
				layer.tips('必须为数字，且不能为空！', this, {
					tips: [1, '#3595CC'],
					time: 2000
				});
				$(this).val('');
			}else{
				layer.closeAll('tips');
				var margin = parseFloat($('#margin').val()) || 0;
				if(arrivalamount > margin){
					layer.tips('到货量不能大于余量！', this, {
						tips: [1, '#3595CC'],
						time: 2000
					});
					$(this).val('');
				}
			}
		});
		/**
		 * 以下分页
		 */
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryPurchaseApplication(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryPurchaseApplication(1);
		});
	}
	//日期字符串转为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//获取采购订单搜索条件
	function getPurchaseApplicationParams(){
		var supplierid = $('#supplier').attr('supplierid');supplierid = $.trim(supplierid);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var purchaseApplicationCode = $('#purchaseApplicationCode').val();purchaseApplicationCode = $.trim(purchaseApplicationCode);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;
		return {
			supplierid:supplierid,
			materielid:materielid,
			code:purchaseApplicationCode,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	//查询采购订单
	function queryPurchaseApplication(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getPurchaseApplicationParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.pageGroupMateriel,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderPurchaseApplicationHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
						callback: function(pageNo){
							queryPurchaseApplication(pageNo+1);
						},
						prev_text: '上一页',
						next_text: '下一页',
						items_per_page:pageSize,
						num_display_entries:4,
						current_page:pageNo-1,
						num_edge_entries:1,
						maxentries:total,
						link_to:"javascript:void(0)"
					});
				}else{
					layer.msg(result.error);
				}
				layer.close(index);
			}
		});
	}
	//解析加载采购订单
	function renderPurchaseApplicationHtml(data){
		$('#purchaseApplicationBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var supplier = obj.supplier || {};
				var code = obj.code || '';
				var suppliername = obj.suppliername || '';
				var materielname = obj.materielname || '';
				var materielspec = obj.materielspec || '';
				var materieltype = obj.materieltype || '';
				var purchasesum = obj.purchasesum || '';
				var margin = obj.margin || '';
				var storagequantity = obj.storagequantity || '';
				var unstoragequantity = obj.unstoragequantity || '';
				var pretendingtake = obj.pretendingtake || '';
				var orgname = obj.orgname || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
			//	var minemouth = '';
			//	switch (supplier.minemouth) {
			//	case '0': minemouth = '否'; break;
			//	case '1': minemouth = '是'; break;
			//	default: break;
			//	}
				var  minemouthname= obj.minemouthname || '';
				var drivercheck = '';
				switch (supplier.drivercheck) {
				case '0': drivercheck = '否'; break;
				case '1': drivercheck = '是'; break;
				default: break;
				}
			//	var remark = obj.remark || '';
				var supplierremark = obj.supplierremark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+code+'</td>')
				.append('<td>'+suppliername+'</td>')
				.append('<td>'+materielname+'</td>')
				.append('<td>'+materielspec+'</td>')
				.append('<td>'+materieltype+'</td>')
				.append('<td>'+purchasesum+'</td>')
				.append('<td>'+margin+'</td>')
				.append('<td>'+storagequantity+'</td>')
				.append('<td>'+unstoragequantity+'</td>')
				.append('<td>'+pretendingtake+'</td>')
				.append('<td>'+orgname+'</td>')
				.append('<td>'+billtimeStr+'</td>')
				.append('<td>'+departmentname+'</td>')
				.append('<td>'+minemouthname+'</td>')
			//	.append('<td>'+drivercheck+'</td>')
				.append('<td>'+supplierremark+'</td>')
				.data(obj)
				.appendTo('#purchaseApplicationBody');
			}
			

			//弹出框表格选中的行 ，颜色加深
			$('#purchaseApplicationBody tr').off('click').on('click',function(){
				$(this).addClass("active").siblings().removeClass("active");
			});
			//弹出框表格的确定按钮传值
			$('#returnpurchasedata').off('click').on('click',function(){
				var obj = $('#purchaseApplicationBody tr.active').data();
				pushPurchaseArrive(obj);
			});
			
		}else{
			layer.msg('暂无数据');
		}
	}
	//显示相关订单信息
	function pushPurchaseArrive(obj){
		$('#billcode').val(obj.code || '').attr('billid', obj.id || '').attr('billdetailid', obj.detailid);
		$('#suppliername').val(obj.suppliername || '').attr('supplierid',obj.supplierid);
		if(obj.minemouthname){
			$('#suppliername').attr('minemouthname',obj.minemouthname);
		}
		$('#materielname').val(obj.materielname || '').attr('packagetype',obj.packagetype);
		$('#purchasesum').val(obj.purchasesum || '');
		$('#margin').val(obj.margin || '');
		$('#unit').val(obj.unit || '');
		$('#departmentname').val(obj.departmentname || '');
		$('#supplierremark').val(obj.supplierremark || '');
		$('#altbill').modal('hide');
		if($("#miningpointid").length!=0){
			$("#miningpointid").parent().remove();
		}
		var materialid = obj.materielid;
		$("#materielid").val(materialid);
		var supplierid = obj.supplierid;
		$("#supplierid").val(supplierid);
		$.ajax({
			url:URL.selectByMaterialid,
			data:{materialid:materialid,supplierid:supplierid},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
					var data = result || [];
					if(data.length>0){
						var html = "<div class='daohuo_add_solo'><label>采矿点：</label><select id='miningpointid'>";
						for(var i=0;i<data.length;i++){
							var htmls = "<option value='"+data[i].id+"'>"+data[i].miningpointname+"</option>";
							html=html+htmls;
						}
						html = html+"</select></div>";
						$("#applasttr").after(html);
					}else{
						//$("#applasttr").va
					}
			}
		});
	}
	//新增车辆
	function saveVehicle(){
		var transporttype = $('#v_transporttype').val(); transporttype = $.trim(transporttype);
		var vehicleno = $('#v_vehicleno').val(); vehicleno = $.trim(vehicleno);
		var vehicletype = $('#v_vehicletype').val(); vehicletype = $.trim(vehicletype);
		var transportunit = $('#v_transportunit').val(); transportunit = $.trim(transportunit);
		var maxweight = $('#v_maxweight').val(); maxweight = $.trim(maxweight);
		var tareweight = $('#v_tareweight').val(); tareweight = $.trim(tareweight);
		var ownername = $('#v_ownername').val(); ownername = $.trim(ownername);
		var telephone = $('#v_telephone').val(); telephone = $.trim(telephone);
		var address = $('#v_address').val(); address = $.trim(address);
		var isvalid = 0;
		if($('#v_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remarks = $('#v_remarks').val(); remarks = $.trim(remarks);
		if(!vehicleno){
			layer.msg('车牌号码不能为空!', {icon: 5});return;
		}
		$.ajax({
			url:URL.addVehicle,
			data:{
				transporttype:transporttype,
				vehicleno:vehicleno,
				vehicletype:vehicletype,
				transportunit:transportunit,
				maxweight:maxweight,
				tareweight:tareweight,
				ownername:ownername,
				telephone:telephone,
				address:address,
				isvalid:isvalid,
				remarks:remarks
			},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					layer.msg(result.error, {icon: 1});
					var vehicle = result.data;
					$('#vehicle').val(vehicle.vehicleno).attr('vehicleid',vehicle.id);
					$('#vehicleAddView').modal('hide');
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	//新增司机
	function saveDriver(){
		var name = $('#d_name').val(); name = $.trim(name);
		var abbrname = $('#d_abbrname').val(); abbrname = $.trim(abbrname);
		var address = $('#d_address').val(); address = $.trim(address);
		var telephone = $('#d_telephone').val(); telephone = $.trim(telephone);
		var identityno = $('#d_identityno').val(); identityno = $.trim(identityno);
		var isvalid = 0;
		if($('#d_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remarks = $('#d_remarks').val(); remarks = $.trim(remarks);
		if(!name){
			layer.msg('司机名称不能为空!', {icon: 5});return;
		}
		if(!telephone){
			layer.msg('司机电话不能为空!', {icon: 5});return;
		}
		if(!identityno){
			layer.msg('身份证号不能为空!', {icon: 5});return;
		}
		$.ajax({
			url:URL.addDriver,
			data:{
				name:name,
				abbrname:abbrname,
				address:address,
				telephone:telephone,
				identityno:identityno,
				isvalid:isvalid,
				remarks:remarks
			},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					layer.msg(result.error, {icon: 1});
					var driver = result.data;
					$('#driver').val(driver.name).attr('driverid',driver.id);
					$('#identityno').val(driver.identityno);
					$('#driverAddView').modal('hide');
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}

	//写卡并保存
	function writeCardAction() {


		var params = getPurchaseArriveAddParams();
		var obj = getWriteCardParams();
		if(initCardReader()) {
			//打开读卡器
			readerOpen();
			//开打卡片获取卡号
			var cardno = openCard();
			if(cardno){
				//卡号放入待保存数据
				params.icardno = cardno;
				//蜂鸣
				readerBeep();
				if(validate(params)){
					$.ajax({
						url: URL.add,
						data:params,
						async:true,
						cache:false,
						dataType:'json',
						type:'post',
						success:function(result){
							if(result.code == '000000'){
								try{
									//写卡
									writeObjToCard(obj);
									//蜂鸣
									readerBeep();
									window.location.href = URL.purchaseArriveMain;
								} catch (e) {
									layer.alert('写卡失败!('+e.Message+')');
								}
							}else{
								layer.msg(result.error, {icon: 5});
								$('#addBtn').removeClass('disabled');
							}
							//关闭读卡器
							readerClose();
						}
					});
				}else{
					$('#addBtn').removeClass('disabled');
					//关闭读卡器
					readerClose();
				}
			}
			
		}else{
			layer.alert('当前游览器不支持!(只兼容IE游览器)');
		}
	}

	//获取写卡数据
	function getWriteCardParams() {
		var billcode = $('#billcode').val() || ''; billcode = $.trim(billcode);
		var rfid = $('#rfid').val() || ''; rfid = $.trim(rfid);
		var vehicleno = $('#vehicle').val() || '';vehicleno = $.trim(vehicleno);
		var vehicleid = $('#vehicle').attr('vehicleid') || '';vehicleid = $.trim(vehicleid);
		var supplierid = $('#suppliername').attr('supplierid') || '';supplierid = $.trim(supplierid);
		var materielname = $('#materielname').val() || '';materielname = $.trim(materielname);
		//设置业务类型为 采购到货
		var businesstype = '0';
		var supplierremark = $('#supplierremark').val() || '';supplierremark = $.trim(supplierremark);
		var arrivalamount = $('#arrivalamount').val() || 0;
		var packagetype = $('#materielname').attr('packagetype');
		var minemouthname = $('#suppliername').attr('minemouthname');
		return {
			notice:billcode,
			rfid:rfid,
			packagetype:packagetype,
			minemouthname:minemouthname,
			vehicleno:vehicleno,
			vehicleid:vehicleid,
			supplierid:supplierid,
			materielname:materielname,
			businesstype:businesstype,
			status:'0',
			supplierremark:supplierremark,
			arrivalamount:arrivalamount
		};
	}

	//获取通知单新增参数
	function getPurchaseArriveAddParams(){
		var billid = $('#billcode').attr('billid') || ''; billid = $.trim(billid);
		var billcode = $('#billcode').val() || ''; billcode = $.trim(billcode);
		var billdetailid = $('#billcode').attr('billdetailid') || ''; billdetailid = $.trim(billdetailid);
		var makebilltime = $('#makebilltime').val() || ''; makebilltime = $.trim(makebilltime); makebilltime = str2Long(makebilltime);
		var unit = $('#unit').val(); unit = $.trim(unit);
		var vehicleid = $('#vehicle').attr('vehicleid'); vehicleid = $.trim(vehicleid);
		var driverid = $('#driver').attr('driverid'); driverid = $.trim(driverid);
		var arrivalamount = $('#arrivalamount').val(); arrivalamount = $.trim(arrivalamount);
		var remark = $('#remark').val(); remark = $.trim(remark);
		var miningpointid = $("#miningpointid").val();miningpointid = $.trim(miningpointid);
		var miningpointname = $("#miningpointid").find("option:selected").text();miningpointname = $.trim(miningpointname);
		return {
			billid:billid,
			billcode:billcode,
			billdetailid:billdetailid,
			makebilltime:makebilltime,
			unit:unit,
			vehicleid:vehicleid,
			driverid:driverid,
			arrivalamount:arrivalamount,
			remark:remark,
			miningpointid:miningpointid,
			miningpointname:miningpointname
		};
	}
	//校验参数是否合法
	function validate(params){
		if(!params.billid || !params.billdetailid){
			layer.msg('请选择订单！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('车辆不能为空！', {icon: 5}); return false;
		}
//		if(!params.driverid){
//			layer.msg('司机不能为空！', {icon: 5}); return false;
//		}
		if(!params.arrivalamount || params.arrivalamount <= 0){
			layer.msg('到货量不能为空且大于零！', {icon: 5});return false;
		}
		var materialid = $("#materielid").val();
		
		var matbolean = true;
		
		$.ajax({
			url:URL.selectByMaterialid,
			data:{materialid:materialid},
			async:false,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				
					var data = result;
					if(data.length>0){
						if($("#miningpointid").length==0){
							layer.msg('采矿点不能为空！', {icon: 5});matbolean=false;
						}else{
							var materiel = $("#miningpointid").val();
							if(materiel==""){
								layer.msg('采矿点不能为空！', {icon: 5});matbolean=false;
							}
						}
						
					}
			}
		});
		if(matbolean==false){
			return false;
		}
		return params;
	}
	//新增到货通知单
	function addPurchaseArrive(){
		var params = getPurchaseArriveAddParams();
		if(validate(params)){
			$.ajax({
				url: URL.add,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.purchaseArriveMain;
					}else{
						layer.msg(result.error, {icon: 5});
						$('#addBtn').removeClass('disabled');
					}
				}
			});
		}else{
			$('#addBtn').removeClass('disabled');
		}
	}
})(jQuery, window);