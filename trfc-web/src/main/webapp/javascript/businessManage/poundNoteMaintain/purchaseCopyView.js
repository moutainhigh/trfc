;(function($) {
	var URL = {
			main: '/trfc/poundNote/purchase/main',
			copy: '/trfc/poundNote/purchase/copy',
			pageGroupMateriel: '/trfc/purchaseApplication/pageGroupMateriel',
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
		$('#refresh').off('click').on('click',function(){
			window.location.reload(true);
		});
		$('#save').off('click').on('click', function(){
			this.disabled = true;
			save(this);
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
			starttime:Date.parseTime_YMD_HMS(starttime),
			endtime:Date.parseTime_YMD_HMS(endtime),
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
		$('#margin').val(obj.margin || '');
		$('#minemouth').val(obj.minemouthname || '');
		$('#supplierremark').val(obj.supplierremark || '');
		$('#altbill').modal('hide');
	}
	//GET参照榜单参数
	function getParams() {
		var poundNoteId = $('#poundNoteId').val();
		var billId = $('#billcode').attr('billid');
		var billDetailId = $('#billcode').attr('billdetailid');
		return {
			poundNoteId: poundNoteId,
			billId: billId,
			billDetailId: billDetailId
		};
	}
	//校验参数是否合法
	function validate(params){
		if(!params.poundNoteId){
			layer.msg('榜单ID不能为空！', {icon: 5});
			return false;
		}
		if(!params.billId || !params.billDetailId){
			layer.msg('请先选择订单！', {icon: 5});
			return false;
		}
		return params;
	}
	//保存参照榜单
	function save(_this) {
		var params = getParams();
		if (validate(params)) {
			$.post(URL.copy, params, function(result) {
				if (result) {
					if (result.code == '000000') {
						window.location.href = URL.main;
					} else {
						layer.msg(result.error, {icon: 5});
					}
				} else {
					layer.msg('请求失败了，请等下再来吧！', {icon: 5});
				}
				_this.disabled = false;
			});
		} else {
			_this.disabled = false;
		}
	}
	
	
	
	
	
	
	
	
})(jQuery);