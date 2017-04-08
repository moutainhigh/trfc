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
			return Date.parseYMD_HMS(str).getTime();
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
		
		$('#takeamount').trigger('input').trigger('propertychange');
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
		$('#returnApplication').off('click').on('click',function(){
			if($('#salesApplication').is(':visible')){
				var trs = $('#salesApplication').find('tr.active');
				if(trs.length == 0){
					layer.msg('至少选择一个订单！');return;
				}else if(trs.length == 1){
					var obj = trs.data();
					selectSalesApplication(obj, [{
						billid: obj.id,
						billdetailid: obj.detailid
					}], trs);
				}else if(trs.length >1 && trs.length <= 3){
					var flag = true;
					var bills = [];
					var salessum = 0;
					var marginsum = 0;
					var div = '<div class="layer-content-radio-div">';
					trs.each(function(i){
						var rightData = $(trs[i]).data();
						bills.push({
							billid: rightData.id,
							billdetailid: rightData.detailid
						});
						if(i > 0){
							var leftData = $(trs[i-1]).data();
							if(leftData.customerid == rightData.customerid && leftData.materielname == rightData.materielname){
								
							}else{
								layer.msg('不能同时选择多个客户和物料！'); flag = false;
								return;
							}
						}
						marginsum += rightData.margin;
						if(rightData.margin){
							salessum += rightData.salessum;
						}
						div += '<div><label><input name="billcode" type="radio">'+rightData.code+'</label></div>';
					});
					div += '</div>';
					if(flag){
						var i = layer.open({
							type: '1',
							area: ['400px', '250px'],
							shadeClose: true,
							content: div,
							btn: ['确认', '取消'],
							yes: function(index, layero){
								if($('.layer-content-radio-div input[type="radio"]:checked').length == 0){
									layer.msg('请选择主订单！', {icon: 5});return false;
								}else{
									var index = $('.layer-content-radio-div input:radio:checked').closest('div').index();
									var obj = $(trs[index]).data();
									trs.sort(function(a,b){
										var aData = $(a).data();
										var bData = $(b).data();
										if($('#maindeduction')[0].checked){
											if(aData.billid == obj.billid){
												return -1;
											}
										}
										return aData.margin - bData.margin;
									});
									selectSalesApplication(obj, bills, trs);
									$('#margin').val(marginsum);
									if(!$('#maindeduction').is(':checked')){
										$('#salessum').val(salessum);
									}
									layer.close(i);
									$('#takeamount').trigger('input').trigger('propertychange');
								}
							}
						});
					}
				}else if(trs.length > 3){
					layer.msg('一次最多选择3个订单！');return;
				}
			}
		});
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload();
		});
		$('#updateBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				saveSalesArrive();
			}
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
		$('#takeamount').off('input propertychange').on('input propertychange', function(){
			var marginsum = parseFloat($('#margin').val()) || 0;
			var value = parseFloat($(this).val() || 0);
			if(!$.isNumeric(value)){
				layer.tips('提货量必须为数字!', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				 $(this).val(''); return;
			}else if(value > marginsum){
				layer.msg('提货量不能大于余量!', {icon: 5}); $(this).val(''); return;
			}
			$('#salesApplicationDetailBody tr').each(function(){
				var yl = $(this).find('td.yl').text(); yl = parseFloat(yl);
				if(value > 0){
					if(yl >= value){
						$(this).find('td.yt').html(value);
					}else{
						$(this).find('td.yt').html(yl);
					}
				}else{
					$(this).find('td.yt').html(0);
				}
				value -= yl;
			});
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
					    callback: function(pageNo){
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
				var margin = obj.margin || 0;
				var storagequantity = obj.storagequantity || 0;
				var unstoragequantity = obj.unstoragequantity || 0;
				var pretendingtake = obj.pretendingtake || 0;
				var orgname = obj.orgname || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
				var salesmanname = obj.salesmanname || '';
				var makebillname = obj.makebillname || '';
				var channelcode = obj.channelcode || '';
				$('<tr>').append('<td><input type="checkbox"/></td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+salessum+'</td>')
						.append('<td>'+margin+'</td>')
						.append('<td>'+storagequantity+'</td>')
						.append('<td>'+unstoragequantity+'</td>')
						.append('<td>'+pretendingtake+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+channelcode+'</td>')
						.data(obj)
						.appendTo('#salesApplicationBody');
			}
		}else{
			layer.msg('暂无数据');
		}
		$('#salesApplicationBody>tr').find('td:eq(0)>input[type="checkbox"]').off('change').on('change',function(){
			if(this.checked == true){
				$(this).closest('tr').addClass('active');
			}else{
				$(this).closest('tr').removeClass('active');
			}
		}).off('click').on('click',function(e){
			e.stopPropagation();
		});
		$('#salesApplicationBody>tr').off('click').on('click',function(e){
			e.stopPropagation();
			$(this).find('td:eq(0)>input').trigger('click');
		});
	}
	function selectSalesApplication(obj, bills, trs){
		$('#billcode').val(obj.code || '').attr('billid', obj.id || '').attr('billdetailid', obj.detailid || '').attr('bills', JSON.stringify(bills));
		$('#customername').val(obj.customername || '');
		$('#channelcode').val(obj.channelcode || '');
		$('#materielname').val(obj.materielname || '');
		$('#departmentname').val(obj.departmentname || '');
		$('#unit').val(obj.unit || '');
		$('#salessum').val(obj.salessum || '');
		$('#margin').val(obj.margin || 0);
		$('#billtime').val(obj.billtimeStr || '').attr('billtime', obj.billtime || '');
		$('#salesApplication').modal('hide');
		$('#salesApplicationDetailBody').empty();
		trs.each(function(i){
			var data = $(this).data();
			$('#salesApplicationDetailBody').append('<tr><td>'+(i+1)+'</td><td>'+(data.code || '')+'</td><td>'+(data.billtypename || '')+'</td>'
					+'<td>'+(data.billtimeStr || '')+'</td><td>'+(data.materielname || '')+'</td>'
					+'<td>'+(data.unit || '')+'</td><td>'+(data.salessum || '')+'</td>'
					+'<td class="yl">'+(data.margin || 0)+'</td><td class="yt">0</td><td>'+(data.orgname || '')+'</td>'
					+'<td>'+(data.customername || '')+'</td><td>'+(data.departmentname || '')+'</td>'
					+'<td>'+(data.salesmanname || '')+'</td><td>'+data.makebillname || ''+'</td></tr>');
		});
	}
	function validate(params){
		if(!params.billid || !params.billdetailid){
			layer.msg('请选择订单！', {icon: 5});return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车辆！', {icon: 5});return false;
		}
		if(!params.takeamount || params.takeamount <= 0){
			layer.msg('提货量不能为空且大于零！', {icon: 5});return false;
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
		var bills = $('#billcode').attr('bills');
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
			icardid:icardid,
			bills:bills
		};
	}
	function saveSalesArrive(){
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
						$('#updateBtn').removeClass('disabled');
						layer.close(index);
					}
				}
			});
		}else{
			$('#updateBtn').removeClass('disabled');
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