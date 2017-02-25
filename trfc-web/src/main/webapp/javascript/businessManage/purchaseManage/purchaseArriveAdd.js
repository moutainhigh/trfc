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
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
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
	    		$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id);
	    		$('#rfid').val(ui.item.rfid);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).change(function(){
	    	$(this).val('').removeAttr('vehicleid');
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
	    }).change(function(){
	    	$(this).val('').removeAttr('driverid');
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
	    }).change(function(){
	    	$(this).val('').removeAttr('materielid');
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
	    }).change(function(){
	    	$(this).val('').removeAttr('supplierid');
	    });
	}
	//绑定按钮事件
	function initBindEvent(){
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload();
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			addPurchaseArrive();
		});
		//保存写卡
		$('#addAndAddCardBtn').off('click').on('click',function(){
			alert('待开发...');
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
		/**
		 * 以下分页
		 */
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
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
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
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
					alert(result.error);
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
				var arrivalquantity = obj.arrivalquantity || '';
				var orgname = obj.orgname || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
				var minemouth = '';
				switch (supplier.minemouth) {
				case '0': minemouth = '否'; break;
				case '1': minemouth = '是'; break;
				default: break;
				}
				var drivercheck = '';
				switch (supplier.drivercheck) {
				case '0': drivercheck = '否'; break;
				case '1': drivercheck = '是'; break;
				default: break;
				}
				var remark = obj.remark || '';
				$('<tr title="双击选择">').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+suppliername+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+materielspec+'</td>')
						.append('<td>'+materieltype+'</td>')
						.append('<td>'+purchasesum+'</td>')
						.append('<td>'+margin+'</td>')
						.append('<td>'+storagequantity+'</td>')
						.append('<td>'+unstoragequantity+'</td>')
						.append('<td>'+arrivalquantity+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
						.append('<td>'+minemouth+'</td>')
						.append('<td>'+drivercheck+'</td>')
						.append('<td>'+remark+'</td>')
						.data(obj)
						.appendTo('#purchaseApplicationBody');
			}
			$('#purchaseApplicationBody tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				pushPurchaseArrive(obj);
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	//显示相关订单信息
	function pushPurchaseArrive(obj){
		$('#billcode').val(obj.code || '').attr('billid', obj.id || '').attr('billdetailid', obj.detailid);
		$('#suppliername').val(obj.suppliername || '');
		$('#materielname').val(obj.materielname || '');
		$('#purchasesum').val(obj.purchasesum || '');
		$('#margin').val(obj.margin || '');
		$('#unit').val(obj.unit || '');
		$('#departmentname').val(obj.departmentname || '');
		$('#supplierremark').val(obj.supplierremark || '');
		$('#altbill').modal('hide');
	}
	//新增车辆
	function saveVehicle(){
		var code = $('#v_code').val(); code = $.trim(code);
		var transporttype = $('#v_transporttype').val(); transporttype = $.trim(transporttype);
		var vehicleno = $('#v_vehicleno').val(); vehicleno = $.trim(vehicleno);
		var vehicletype = $('#v_vehicletype').val(); vehicletype = $.trim(vehicletype);
		var transportunit = $('#v_transportunit').val(); transportunit = $.trim(transportunit);
		var maxweight = $('#v_maxweight').val(); maxweight = $.trim(maxweight);
		var tareweight = $('#v_tareweight').val(); tareweight = $.trim(tareweight);
		var ownername = $('#v_ownername').val(); ownername = $.trim(ownername);
		var telephone = $('#v_telephone').val(); telephone = $.trim(telephone);
		var address = $('#v_address').val(); address = $.trim(address);
		var orgid = $('#v_orgname').attr('orgid'); orgid = $.trim(orgid);
		var orgname = $('#v_orgname').val(); orgname = $.trim(orgname);
		var isvalid = 0;
		if($('#v_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remarks = $('#v_remarks').val(); remarks = $.trim(remarks);
		$.ajax({
			url:URL.addVehicle,
			data:{
				code:code,
				transporttype:transporttype,
				vehicleno:vehicleno,
				vehicletype:vehicletype,
				transportunit:transportunit,
				maxweight:maxweight,
				tareweight:tareweight,
				ownername:ownername,
				telephone:telephone,
				address:address,
				orgid:orgid,
				orgname:orgname,
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
		var code = $('#d_code').val(); code = $.trim(code);
		var internalcode = $('#d_internalcode').val(); internalcode = $.trim(internalcode);
		var name = $('#d_name').val(); name = $.trim(name);
		var abbrname = $('#d_abbrname').val(); abbrname = $.trim(abbrname);
		var address = $('#d_address').val(); address = $.trim(address);
		var orgid = $('#d_orgname').attr('orgid'); orgid = $.trim(orgid);
		var orgname = $('#d_orgname').val(); orgname = $.trim(orgname);
		var telephone = $('#d_telephone').val(); telephone = $.trim(telephone);
		var identityno = $('#d_identityno').val(); identityno = $.trim(identityno);
		var isvalid = 0;
		if($('#d_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remarks = $('#d_remarks').val(); remarks = $.trim(remarks);
		$.ajax({
			url:URL.addDriver,
			data:{
				code:code,
				internalcode:internalcode,
				name:name,
				abbrname:abbrname,
				address:address,
				orgid:orgid,
				orgname:orgname,
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
					$('#driverid').val(driver.name).attr('driverid',driver.id);
					$('#identityno').val(driver.identityno);
					$('#driverAddView').modal('hide');
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
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
		if(!billid || !billcode || !billdetailid){
			layer.msg('订单号不能为空!', {icon: 5});return false;
		}
		if(!vehicleid){
			layer.msg('车辆不能为空!', {icon: 5});return false;
		}
		if(!driverid){
			layer.msg('司机不能为空!', {icon: 5});return false;
		}
		if(!arrivalamount){
			layer.msg('到货量不能为空!', {icon: 5});return false;
		}
		return {
			billid:billid,
			billcode:billcode,
			billdetailid:billdetailid,
			makebilltime:makebilltime,
			unit:unit,
			vehicleid:vehicleid,
			driverid:driverid,
			arrivalamount:arrivalamount,
			remark:remark
		};
	}
	//新增到货通知单
	function addPurchaseArrive(){
		var params = getPurchaseArriveAddParams();
		if(params){
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
					}
				}
			});
		}
	}
})(jQuery, window);