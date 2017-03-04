;(function($, win){
	var URL = {
		mainUrl:"/trfc/salesArrive/main",
		pageUrl:"/trfc/salesApplication/pageGroupMateriel",
		updateUrl:"/trfc/salesArrive/update",
		addVehicleUrl:"/trfc/vehicle/addVehicle",
		addDriverUrl:"/trfc/driver/addDriver",
		vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
		driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
		materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
		customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch"
	};
	function str2Long(str){
		if(str){
			var date = new Date(str);
			return date.getTime();
		}
		return undefined;
	}
	//初始化
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮
		bindEvent();
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
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('vehicleid');
	    }).change(function(){
    		if(!$(this).attr('vehicleid')){
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
	    $("#customer").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var customer = cache['customer'] || {};
	    		if ( term in customer ) {
	    			response( customer[ term ] );
	    			return;
	    		}
	    		$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
	    			customer[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
    		}
	    });
	}
	//绑定按钮事件
	function bindEvent(){
		$('#searchBtn').off('click').on('click',function(){
			getSalesApplicationData($.trim($('#jumpPageNo').val()) || 1);
		});
		$('#billcode').off('click').on('click',function(){
			getSalesApplicationData($.trim($('#jumpPageNo').val()) || 1);
			$('#salesApplication').modal();
		});
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload();
		});
		$('#updateBtn').off('click').on('click',function(){
			this.disabled = true;
			saveSalesArrive(this);
		});
		$('#backBtn').off('click').on('click',function(){
			window.location.href = URL.mainUrl;
		});
		$('#addVehicleCommitBtn').off('click').on('click',function(){
			if($('#vehicleAddView').is(':visible')){
				saveVehicle();
			}
		});
		$('#addDriverCommitBtn').off('click').on('click',function(){
			if($('#driverAddView').is(':visible')){
				saveDriver();
			}
		});
		$('#takeamount').change(function(){
			$('#advanceAmount').html($(this).val());
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				getSalesApplicationData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			getSalesApplicationData(1);
		});
	}
	function getSarelApplicationParams(){
		var billcode = $('#applicationcode').val() || ''; billcode = $.trim(billcode);
		var customerid = $('#customer').attr('customerid') || ''; customerid = $.trim(customerid);
		var materielid = $('#materiel').attr('materielid') || ''; materielid = $.trim(materielid);
		var starttime = $('#starttime').val() || ''; starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || ''; endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || ''; pageSize = $.trim(pageSize);
		return {
			code:billcode,
			customerid:customerid,
			materielid:materielid,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			status:1,
			pageSize:pageSize
		}
	}
	function getSalesApplicationData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getSarelApplicationParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.pageUrl,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(){
					    	getSalesApplicationData(pageNo+1);
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
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}
	function renderHtml(data){
		$('#salesApplicationBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var customer = obj.customerManageResp;
				var code = obj.code || '';
				var billtypename = obj.billtypename || '';
				var customername = obj.customername || '';
				var materielname = obj.materielname || '';
				var salessum = obj.salessum || '';
				var orgname = obj.orgname || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
				var salesmanname = obj.salesmanname || '';
				var creatorname = obj.creatorname || '';
				var channelcode = obj.channelcode || '';
				$('<tr>').attr('title','双击确定')
						.append('<td>'+code+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+salessum+'</td>')
						.append('<td></td>')
						.append('<td></td>')
						.append('<td></td>')
						.append('<td></td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+creatorname+'</td>')
						.append('<td>'+channelcode+'</td>')
						.data(obj)
						.appendTo('#salesApplicationBody');
			}
		}else{
			layer.msg('暂无数据');
		}
		$('#salesApplicationBody').find('tr').off('click').on('click',function(){
			$(this).addClass('active').siblings().removeClass('active');
		});
		$('#salesApplicationBody').find('tr').off('dblclick').on('dblclick',function(){
			var obj = $(this).data();
			selectSalesApplication(obj);
		});
	}
	function selectSalesApplication(obj){
		$('#billcode').val(obj.code || '').attr('billid', obj.id || '').attr('billdetailid', obj.detailid || '');
		$('#customername').val(obj.customername || '');
		$('#channelcode').val(obj.channelcode || '');
		$('#materielname').val(obj.materielname || '');
		$('#departmentname').val(obj.departmentname || '');
		$('#unit').val(obj.unit || '');
		$('#salessum').val(obj.salessum || '');
		$('#billtime').val(obj.billtimeStr || '').attr('billtime', obj.billtime || '');
		$('#salesApplication').modal('hide');
		$('#salesApplicationDetailBody').empty()
										.append('<tr><td>'+(obj.code || '')+'</td><td>'+(obj.billtypename || '')+'</td>'
												+'<td>'+(obj.billtimeStr || '')+'</td><td>'+(obj.materielname || '')+'</td>'
												+'<td>'+obj.unit || ''+'</td><td>'+(obj.salessum || '')+'</td>'
												+'<td></td><td id="advanceAmount">0.00</td><td>'+(obj.orgname || '')+'</td>'
												+'<td>'+(obj.customername || '')+'</td><td>'+(obj.departmentname || '')+'</td>'
												+'<td>'+(obj.salesmanname || '')+'</td><td>'+obj.makerbillname || ''+'</td></tr>');
	}
	function validate(params){
		if(!params.billid || !params.billdetailid){
			layer.msg('请选择订单！', {icon: 5});return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车辆！', {icon: 5});return false;
		}
		if(!params.takeamount){
			layer.msg('请输入提货量！', {icon: 5});return false;
		}
		return params;
	}
	function getSalesArriveParams(){
		var id = $('#salesArriveId').val(); id = $.trim(id);
		var billid = $('#billcode').attr('billid'); billid = $.trim(billid);
		var billdetailid = $('#billcode').attr('billdetailid'); billdetailid = $.trim(billdetailid);
		var maindeduction = '0';
		if($('#maindeduction').is(':checked')){
			maindeduction = '1';
		}
		var code = $('#code').val(); code = $.trim(code);
		var createtimeStr = $('#createtimeStr').val(); code = $.trim(code);
		var vehicleid = $('#vehicle').attr('vehicleid'); vehicleid = $.trim(vehicleid);
		var driverid = $('#driver').attr('driverid'); driverid = $.trim(driverid);
		var takeamount = $('#takeamount').val(); takeamount = $.trim(takeamount);
		var remarks = $('#remarks').val(); remarks = $.trim(remarks);
		var spraycode = $('#spraycode').val(); spraycode = $.trim(spraycode);
		var serialnumber = $('#serialnumber').val(); serialnumber = $.trim(serialnumber);
		var icardid = $('#icardid').attr('icardid'); icardid = $.trim(icardid);
		return {
			id:id,
			billid:billid,
			billdetailid:billdetailid,
			maindeduction:maindeduction,
			code:code,
			makebilltime:str2Long(createtimeStr),
			vehicleid:vehicleid,
			driverid:driverid,
			takeamount:takeamount,
			remarks:remarks,
			spraycode:spraycode,
			serialnumber:serialnumber,
			icardid:icardid
		};
	}
	function saveSalesArrive(_this){
		var params = getSalesArriveParams();
		if(validate(params)){
			var index = layer.load(2, {
				  shade: [0.3,'#fff'] //0.1透明度的白色背景
				});
			$.ajax({
				url:URL.updateUrl,
				data:getSalesArriveParams(),
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.mainUrl;
					}else{
						layer.msg(result.error, {icon: 5});
						_this.disabled = false;
						layer.close(index);
					}
				}
			});
		}
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
			url:URL.addVehicleUrl,
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
			url:URL.addDriverUrl,
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
})(jQuery, window);