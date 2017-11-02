;(function($, win){
	var URL = {
			purchasePoundNoteMain: '/trfc/poundNote/purchase/main',
			pageGroupMateriel: '/trfc/purchaseApplication/pageGroupMateriel',
			addPurchase: '/trfc/poundNote/purchase/addPoundNote',
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
			minemouthAutoCompleteSearch: "/trfc/minemouth/autoCompleteSearch",
			yardAutoCompleteSearch: "/trfc/yard/autoCompleteSearch",
			systemUserAutoCompleteSearch: "/trfc/system/auth/user/autoCompleteSearch"
	};
	//日期字符串转为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	function initAutoComplete(){
		var cache = {};
		$("#receiverperson").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var systemUser = cache['systemUser'] || {};
	    		if ( term in systemUser ) {
	    			response( systemUser[ term ] );
	    			return;
	    		}
	    		$.post( URL.systemUserAutoCompleteSearch, {nameLike: $.trim(term)}, function( data, status, xhr ) {
	    			systemUser[ term ] = data.data;
	    			response( data.data );
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
	    		$(this).val(ui.item.name).attr('receiverpersonid', ui.item.id);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('receiverpersonid');
	    }).change(function(){
    		if(!$(this).attr('receiverpersonid')){
    			$(this).val('');
    		}
	    });
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
		}).on('input keydown',function(){
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
	    		$('#driveridentityno').val(ui.item.identityno);
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
	    $("#warehouse").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var warehouse = cache['warehouse'] || {};
	    		if ( term in warehouse ) {
	    			response( warehouse[ term ] );
	    			return;
	    		}
	    		$.post( URL.warehouseAutoCompleteSearch, request, function( data, status, xhr ) {
	    			warehouse[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('warehouseid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('warehouseid');
	    }).change(function(){
	    	if(!$(this).attr('warehouseid')){
	    		$(this).val('');
	    	}
	    });
	    $("#minemouth").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var minemouth = cache['minemouth'] || {};
	    		if ( term in minemouth ) {
	    			response( minemouth[ term ] );
	    			return;
	    		}
	    		$.post( URL.minemouthAutoCompleteSearch, request, function( data, status, xhr ) {
	    			minemouth[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('minemouthid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('minemouthid');
	    }).change(function(){
	    	if(!$(this).attr('minemouthid')){
	    		$(this).val('');
	    	}
	    });
	    $("#yard").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var yard = cache['yard'] || {};
	    		if ( term in yard ) {
	    			response( yard[ term ] );
	    			return;
	    		}
	    		$.post( URL.yardAutoCompleteSearch, request, function( data, status, xhr ) {
	    			yard[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('yardid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('yardid');
	    }).change(function(){
	    	if(!$(this).attr('yardid')){
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
				addPurchasePoundNote();
			}
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
		/**
		 * 自动计算净重
		 */
		$('#grossweight').off('keyup').on('keyup',function(){
			var grossweight = $(this).val();
			if(!grossweight || !$.isNumeric(grossweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				layer.closeAll('tips');
				var tareweight = $('#tareweight').val();
				if(tareweight && $.isNumeric(tareweight)){
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
		$('#tareweight').off('keyup').on('keyup',function(){
			var tareweight = $(this).val();
			if(!tareweight || !$.isNumeric(tareweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				layer.closeAll('tips');
				var grossweight = $('#grossweight').val();
				if(grossweight && $.isNumeric(grossweight)){
					$('#netweight').val(grossweight-tareweight);
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
					layer.msg(result.error,{icon:5});
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
		$('#receivedepartmentname').val(obj.orgname || '');
		$('#suppliername').val(obj.suppliername || '');
		$('#purchasesum').val(obj.purchasesum || '');
		$('#materielname').val(obj.materielname || '');
		$('#minemouth').val(obj.minemouthname || '').attr('minemouthid', obj.minemouthid);
		$('#margin').val(obj.margin || '');
		$('#supplierremark').val(obj.supplierremark || '');
		$('#altbill').modal('hide');
	}
	//获取采购计量参数
	function getPurchasePoundNoteParams(){
		var billid = $('#billcode').attr('billid');
		var billdetailid = $('#billcode').attr('billdetailid');
		var grossweight = $('#grossweight').val();
		var tareweight = $('#tareweight').val();
		var netweight = $('#netweight').val();
		var originalnetweight = $('#originalnetweight').val();
		var vehicleid = $('#vehicle').attr('vehicleid');
		var driverid = $('#driver').attr('driverid');
		var warehouseid = $('#warehouse').attr('warehouseid');
		var minemouthid = $('#minemouth').attr('minemouthid');
		var yardid = $('#yard').attr('yardid');
		var receiverpersonid = $('#receiverperson').attr('receiverpersonid');
		var weighttime = $('#weighttime').val();
		var lighttime = $('#lighttime').val();
		var makebilltime = $('#makebilltime').val();
		return {
			billid: billid,
			billdetailid: billdetailid,
			grossweight: grossweight,
			tareweight: tareweight,
			netweight: netweight,
			originalnetweight: originalnetweight,
			vehicleid: vehicleid,
			driverid: driverid,
			warehouseid: warehouseid,
			minemouthid: minemouthid,
			yardid: yardid,
			receiverpersonid: receiverpersonid,
			weighttime: str2Long(weighttime),
			lighttime: str2Long(lighttime),
			makebilltime: str2Long(makebilltime)
		}
	}
	//校验参数是否合法
	function validate(params){
		if(!params.billid || !params.billdetailid){
			layer.msg('请选择订单！', {icon: 5}); return false;
		}
		if(!params.grossweight || params.grossweight == 0){
			layer.msg('毛重不能为空且不能为零！', {icon: 5}); return false;
		}
		if(!params.tareweight || params.tareweight == 0){
			layer.msg('皮重不能为空且不能为零！', {icon: 5}); return false;
		}
		if(eval(params.tareweight) > eval(params.grossweight)){
			layer.msg('皮重不能大于毛重！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车辆！', {icon: 5}); return false;
		}
		return params;
	}
	//新增采购计量
	function addPurchasePoundNote(){
		var params = getPurchasePoundNoteParams();
		if(validate(params)){
			$.ajax({
				url:URL.addPurchase,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.purchasePoundNoteMain;
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