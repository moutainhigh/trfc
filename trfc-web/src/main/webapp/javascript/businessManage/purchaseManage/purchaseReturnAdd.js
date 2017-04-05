;(function($, window){
	var URL = {
			purchaseArriveMain: '/trfc/purchaseReturn/main',
			poundNotePage: '/trfc/poundNote/purchase/page',
			add: '/trfc/purchaseReturn/add',
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
		$("#vehicle, #vehicle1").autocomplete({
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
		}).on('input propertychange',function(){
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
		}).on('input propertychange',function(){
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
		}).on('input propertychange',function(){
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
		}).on('input propertychange',function(){
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
			window.location.reload();
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				addPurchaseArrive();
			}
		});
		//返回按钮
		$('#backBtn').off('click').on('click',function(){
			window.location.href = URL.purchaseArriveMain;
		});
		//打开磅单列表
		$('#poundnote').off('click').on('click',function(){
			queryPoundNote(1);
			$('#altbill').modal('show');
		});
		//磅单搜索按钮
		$('#poundNoteSearchBtn').off('click').on('click',function(){
			queryPoundNote(1);
		});
		//清空搜索条件
		$('#clearBtn').off('click').on('click',function(){
			$('#supplier').val('').removeAttr('supplierid');
			$('#materiel').val('').removeAttr('materielid');
			$('#vehicle1').val('').removeAttr('vehicleid');
			$('#starttime').val('');
			$('#endtime').val('');
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
		$('#arrivalamount').off('input propertychange').on('input propertychange',function(){
			var arrivalamount = $(this).val();
			if(!arrivalamount || !$.isNumeric(arrivalamount)){
				layer.tips('必须为数字，且不能为空！', this, {
					tips: [1, '#3595CC'],
					time: 2000
				});
				$(this).val('');
			}else{
				var netweight = parseFloat($('#netweight').val()) || 0;
				if(arrivalamount > netweight){
					layer.tips('退货量不能大于净重！', this, {
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
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryPoundNote(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryPoundNote(1);
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
	function getPoundNoteParams(){
		var supplierid = $('#supplier').attr('supplierid');supplierid = $.trim(supplierid);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var vehicleid = $('#vehicle1').attr('vehicleid');vehicleid = $.trim(vehicleid);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;
		return {
			supplierid:supplierid,
			materielid:materielid,
			vehicleid:vehicleid,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	//查询采购订单
	function queryPoundNote(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getPoundNoteParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.poundNotePage,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderPoundNoteHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
						callback: function(pageNo){
							queryPoundNote(pageNo+1);
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
	function renderPoundNoteHtml(data){
		$('#poundNoteBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var billcode = obj.billcode || '';
				var suppliername = obj.suppliername || '';
				var receivedepartmentname = obj.receivedepartmentname || '';
				var vehicleno = obj.vehicleno || '';
				var materielname = obj.materielname || '';
				var netweight = obj.netweight || '';
				var noticecode = obj.noticecode || '';
				var originalnetweight = obj.originalnetweight || '';
				var weighttimeStr = obj.weighttimeStr || '';
				var lighttimeStr = obj.lighttimeStr || '';
				$('<tr title="双击选择">').append('<td>'+(i+1)+'</td>')
				.append('<td>'+code+'</td>')
				.append('<td>'+billcode+'</td>')
				.append('<td>'+suppliername+'</td>')
				.append('<td>'+receivedepartmentname+'</td>')
				.append('<td>'+vehicleno+'</td>')
				.append('<td>'+materielname+'</td>')
				.append('<td>'+netweight+'</td>')
				.append('<td>'+noticecode+'</td>')
				.append('<td>'+originalnetweight+'</td>')
				.append('<td>'+weighttimeStr+'</td>')
				.append('<td>'+lighttimeStr+'</td>')
				.data(obj)
				.appendTo('#poundNoteBody');
			}
			$('#poundNoteBody tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				pushPurchaseArrive(obj);
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	//显示相关订单信息
	function pushPurchaseArrive(obj){
		$('#poundnote').val(obj.code || '').attr('poundnoteid', obj.id || '');
		$('#weighttime').val(obj.weighttimeStr || '');
		$('#suppliername').val(obj.suppliername || '');
		$('#materielname').val(obj.materielname || '');
		$('#billcode').val(obj.billcode || '');
		$('#margin').val(obj.margin || 0);
		$('#noticecode').val(obj.noticecode || '');
		$('#netweight').val(obj.netweight || 0);
		$('#unit').val(obj.unit || '');
		$('#departmentname').val(obj.departmentname || '');
		$('#arrivalamount').val(obj.netweight || 0);
		$('#altbill').modal('hide');
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
					$('#driver').val(driver.name).attr('driverid',driver.id);
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
		var poundnoteid = $('#poundnote').attr('poundnoteid') || ''; poundnoteid = $.trim(poundnoteid);
		var makebilltime = $('#makebilltime').val() || ''; makebilltime = $.trim(makebilltime); makebilltime = str2Long(makebilltime);
		var unit = $('#unit').val(); unit = $.trim(unit);
		var vehicleid = $('#vehicle').attr('vehicleid'); vehicleid = $.trim(vehicleid);
		var driverid = $('#driver').attr('driverid'); driverid = $.trim(driverid);
		var arrivalamount = $('#arrivalamount').val(); arrivalamount = $.trim(arrivalamount);
		var remark = $('#remark').val(); remark = $.trim(remark);
		return {
			poundnoteid:poundnoteid,
			makebilltime:makebilltime,
			unit:unit,
			vehicleid:vehicleid,
			driverid:driverid,
			arrivalamount:arrivalamount,
			remark:remark
		};
	}
	//校验参数是否合法
	function validate(params){
		if(!params.poundnoteid){
			layer.msg('请选择磅单！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('车辆不能为空！', {icon: 5}); return false;
		}
		if(!params.driverid){
			layer.msg('司机不能为空！', {icon: 5}); return false;
		}
		if(!params.arrivalamount){
			layer.msg('退货量不能为空！', {icon: 5}); return false;
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
		}
	}
})(jQuery, window);