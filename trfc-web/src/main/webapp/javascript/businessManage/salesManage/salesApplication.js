;(function($, win){
	//请求路径
	var URL = {
			initAddUrl:"/trfc/salesApplication/initAdd",
			addBtnUrl:"/trfc/salesApplication/add",
			updateBtnUrl:"/trfc/salesApplication/update",
			pageUrl:"/trfc/salesApplication/page",
			auditUrl:"/trfc/salesApplication/audit",
			unauditUrl:"/trfc/salesApplication/unaudit",
			deleteUrl:"/trfc/salesApplication/delete",
			billTypeAutoCompleteSearch: "/trfc/billType/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch"
	};
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
		bindEvent();
		queryData(1);
	}
	function initAutoComplete(){
		var cache = {};
	    $("#a_billtype,#u_billtype").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var billtype = cache['billtype'] || {};
	    		if ( term in billtype ) {
	    			response( billtype[ term ] );
	    			return;
	    		}
	    		$.post( URL.billTypeAutoCompleteSearch, request, function( data, status, xhr ) {
	    			billtype[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('billtypeid', ui.item.id);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('billtypeid');
	    }).change(function(){
    		if(!$(this).attr('billtypeid')){
    			$(this).val('');
    		}
	    });
	    $("#a_materiel,#u_materiel").autocomplete({
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
	    $("#a_warehouse,#u_warehouse").autocomplete({
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
	    $("#s_customer").autocomplete({
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
	    }).on('input keydown',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
    		}
	    });
	    $("#a_customer").autocomplete({
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
	    		$('#a_channelcode').val(ui.item.channelcode);
				$('#a_salesmanname').val(ui.item.salesmanname).attr('salesmanid',ui.item.salesmanid);
				$('#a_transportcompanyname').val(ui.item.transportcompanyname).attr('transportcompanyid',ui.item.transportcompanyid);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
        		$('#a_channelcode').val('');
        		$('#a_salesmanname').val('').removeAttr('salesmanid');
        		$('#a_transportcompanyname').val('').removeAttr('transportcompanyid');
    		}
	    });
	    $("#u_customer").autocomplete({
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
	    		$('#u_channelcode').val(ui.item.channelcode);
	    		$('#u_salesmanname').val(ui.item.salesmanname).attr('salesmanid',ui.item.salesmanid);
	    		$('#u_transportcompanyname').val(ui.item.transportcompanyname).attr('transportcompanyid',ui.item.transportcompanyid);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
        		$('#u_channelcode').val('');
        		$('#u_salesmanname').val('').removeAttr('salesmanid');
        		$('#u_transportcompanyname').val('').removeAttr('transportcompanyid');
    		}
	    });
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#a_addRow').off('click').on('click',function(){
			a_addRow();
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryData(1);
		});
		$('#addBtn').off('click').on('click', function(){
			layer.closeAll();
			$.ajax({
				url:URL.initAddUrl,
				data:null,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						var data = result.data;
						if(data){
							$('#a_code').val(data.code);
							$('#a_billtimeStr').val(data.nowDate);
							addChangeNum($('#a_salessum, #a_taxprice, #a_taxrate, #a_untaxprice'));
							$("#a_billtype").val(data.initBillType.name);
							$("#a_billtype").attr('billtypeid', data.initBillType.id);
							$('#addView').modal();
						}else{
							layer.msg("初始化失败，请稍后重试！");
						}
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
		$('#addCommitBtn').off('click').on('click', function(){
			if($('#addView').is(':visible')){
				this.disabled = true;
				addSalesApplication(this);
			}
		});
		$('#updateCommitBtn').off('click').on('click',function(){
			if($('#updateView').is(':visible')){
				this.disabled = true;
				updateSalesApplication(this);
			}
		});
	}
	
	function getParams(){
		var params = {};
		var code = $('#s_code').val() || '';code = $.trim(code);
		var source = $('#s_source').val() || '';source = $.trim(source);
		var customerid = $('#s_customer').attr('customerid') || '';customerid = $.trim(customerid);
		var starttime = $('#s_starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#s_endtime').val() || '';endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || '';pageSize = $.trim(pageSize);
		return {
			code:code,
			source:source,
			customerid:customerid,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
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
					    callback: pageCallback,
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
		$('#dataBody').empty();
		$('#dataMore').hide()
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var status = obj.status || '';
				//设置字体颜色 (LXY)
				var color = '';
				switch (status) {
				case '0':
					status = '未审核';
					color = 'class="colorred"';
					break;
				case '1':
					status = '已审核';
					break;
				default:
					status = '';
					break;
				}
				var source = obj.source || '';
				switch (source) {
				case '0':
					source = '联机';
					break;
				case '1':
					source = '脱机';
					break;
				default:
					source = '';
					break;
				}
				var billtypename = obj.billtypename || '';
				var customername = obj.customername || '';
				var salesmanname = obj.salesmanname || '';
				var transportcompanyname = obj.transportcompanyname || '';
				var channelcode = obj.channelcode || '';
				var billtimeStr = obj.billtimeStr || '';
				var orgname = obj.orgname || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td '+color+'>'+status+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+transportcompanyname+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>')
						.append('<td>'+auditname+'</td>')
						.append('<td>'+audittimeStr+'</td>')
						.append('<td>'+channelcode+'</td>')
						.append('<td><span class="updateBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
			                    +'<span class="auditBtn"><a><i class="iconfont " data-toggle="tooltip" data-placement="left" title="审核">&#xe692;</i></a></span>'
			                    +'<span class="unauditBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></a></span>'
			                    +'<span class="deleteBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody tr').find('.updateBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				showUpdate(obj);
			});
			$('#dataBody tr').find('.auditBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				audit(obj);
			});
			$('#dataBody tr').find('.unauditBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				unaudit(obj);
			});
			$('#dataBody tr').find('.deleteBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				deleteData(obj);
			});
			$('#dataBody tr').off('click').on('click',function(){
				var obj = $(this).data();
				showMore(obj);
			});
			$('#dataBody tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				showDetail(obj);
			});
		}else{
			layer.msg('暂无数据');
			$('#dataMore').hide();
		}
	}
	//修改
	function showUpdate(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能修改！', {icon: 5});
			return;
		}
		var id = obj.id || '';
		var code = obj.code || '';
		var billtypeid = obj.billtypeid || '';
		var billtypename = obj.billtypename || '';
		var billtimeStr = obj.billtimeStr || '';
		var customerid = obj.customerid || '';
		var customername = obj.customername || '';
		var channelcode = obj.channelcode || '';
		var salesmanname = obj.salesmanname || '';
		var orgname = obj.orgname || '';
		var transportcompanyname = obj.transportcompanyname || '';
		var makebillname = obj.makebillname || '';
		$('#u_id').val(id);
		$('#u_code').val(code);
		$('#u_billtype').val(billtypename).attr('billtypeid', billtypeid);
		$('#u_billtimeStr').val(billtimeStr);
		$('#u_customer').val(customername).attr('customerid',customerid);
		$('#u_channelcode').val(channelcode);
		$('#u_orgname').val(orgname);
		$('#u_salesmanname').val(salesmanname);
		$('#u_transportcompanyname').val(transportcompanyname);
		$('#u_creatorname').val(makebillname);
		var detailResp = obj.list[0];
		if(detailResp){
			$('#u_detailid').val(detailResp.id);
			$('#u_materiel').val(detailResp.materielname).attr('materielid', detailResp.materielid);
			$('#u_warehouse').val(detailResp.warehousename).attr('warehouseid', detailResp.warehouseid);
			$('#u_salessum').val(detailResp.salessum);
			$('#u_taxprice').val(detailResp.taxprice);
			var taxpricesum = (detailResp.salessum * detailResp.taxprice);
			$('#u_taxpricesum').val(taxpricesum.toFixed(2));
			$('#u_taxrate').val(detailResp.taxrate);
			$('#u_untaxprice').val(detailResp.untaxprice);
			var untaxpricesum = (detailResp.salessum * detailResp.untaxprice);
			$('#u_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#u_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
			updateChangeNum($('#u_salessum, #u_taxprice, #u_taxrate, #u_untaxprice'));
		}
		$('#updateView').modal();
	}
	
	function addChangeNum(_$){
		_$.off('input keydown').on('input keydown', function() {
			var salessum = $('#a_salessum').val();
			var taxprice = $('#a_taxprice').val();
			var taxrate = $('#a_taxrate').val();
			var untaxprice = $('#a_untaxprice').val();
			var taxpricesum = (salessum * taxprice);
			var untaxpricesum = (salessum * untaxprice);
			$('#a_taxpricesum').val(taxpricesum.toFixed(2));
			$('#a_untaxprice').val((taxprice * (1 - taxrate)).toFixed(2));
			$('#a_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#a_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
		});
	}
	
	function updateChangeNum(_$){
		_$.off('input keydown').on('input keydown', function() {
			var salessum = $('#u_salessum').val();
			var taxprice = $('#u_taxprice').val();
			var taxrate = $('#u_taxrate').val();
			var untaxprice = $('#u_untaxprice').val();
			var taxpricesum = (salessum * taxprice);
			var untaxpricesum = (salessum * untaxprice);
			$('#u_taxpricesum').val(taxpricesum.toFixed(2));
			$('#u_untaxprice').val((taxprice * (1 - taxrate)).toFixed(2));
			$('#u_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#u_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
		});
	}
	
	//审核
	function audit(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能继续审核！', {icon: 5});
			return;
		}
		confirmOperation('您确定要审核么？', URL.auditUrl, {
			id:obj.id
		});
	}
	//反审
	function unaudit(obj){
		if(obj.source == '0'){
			layer.msg('联机的单据，不能反审！', {icon: 5});
			return;
		}
		confirmOperation('您确定要反审么？', URL.unauditUrl, {
			id:obj.id
		});
	}
	//删除销售申请单
	function deleteData(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能刪除！', {icon: 5});
			return;
		}
		confirmOperation('删除操作不可恢复，您确定要继续么？', URL.deleteUrl, {
			id:obj.id
		});
	}
	//显示更多
	function showMore(obj){
		$('#moreBody').empty();
		var detailList = obj.list;
		if(detailList && detailList.length > 0){
			for(var i=0;i<detailList.length;i++) {
				var detailResp = detailList[i];
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+(detailResp.materielname || '')+'</td>')
				.append('<td>'+(detailResp.salessum || '')+'</td>')
				.append('<td>'+(detailResp.taxprice || '')+'</td>')
				.append('<td>'+(detailResp.storagequantity || 0)+'</td>')
				.append('<td>'+(detailResp.unstoragequantity || 0)+'</td>')
				.append('<td>'+(detailResp.pretendingtake || 0)+'</td>')
				.append('<td>'+(detailResp.margin || 0)+'</td>')
				.append('<td>'+(detailResp.remarks || '')+'</td>')
				.appendTo('#moreBody');
			}
		}else{
			$('#moreBody').append('<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
		}
		$('#dataMore').show();
	}
	//显示详情
	function showDetail(obj){
		var customer = obj.customerManageResp;
		var code = obj.code || '';
		var source = obj.source || '';
		switch (source) {
		case '0':
			source = '联机';
			break;
		case '1':
			source = '脱机';
			break;
		default:
			source = '';
			break;
		}
		var billtypename = obj.billtypename || '';
		var transportcompanyname = obj.transportcompanyname || '';
		var customername = obj.customername || '';
		var salesmanname = obj.salesmanname || '';
		var billtimeStr = obj.billtimeStr || '';
		var orgname = obj.orgname || '';
		var departmentname = obj.departmentname || '';
		var makebillname = obj.makebillname || '';
		var makebilltimeStr = obj.makebilltimeStr || '';
		var auditname = obj.auditname || '';
		$('#v_code').val(code);
		$('#v_source').val(source);
		$('#v_billtype').val(billtypename);
		$('#v_transportcompanyname').val(transportcompanyname);
		$('#v_billtimeStr').val(billtimeStr);
		$('#v_customername').val(customername);
		$('#v_orgname').val(orgname);
		$('#v_departmentname').val(departmentname);
		$('#v_salesmanname').val(salesmanname);
		$('#v_creatorname').val(makebillname);
		$('#v_createtimeStr').val(makebilltimeStr);
		$('#v_auditname').val(auditname);
		//订单明细
		$('#detailTabBody').empty();
		var detailList = obj.list;
		for(var i=0;i<detailList.length;i++) {
			var detailResp = detailList[i];
			if(detailResp){
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+(detailResp.materielname || '')+'</td>')
				.append('<td>'+(detailResp.salessum || '')+'</td>')
				.append('<td>'+(detailResp.remarks || '')+'</td>')
				.appendTo('#detailTabBody');
			}else{
				$('#detailTabBody').append('<tr><td></td><td></td><td></td></tr>');
			}
		}
		switch (obj.status) {
		case '0':
			$('#shImg').attr('src','/resources/images/un_sh.png');
			break;
		case '1':
			$('#shImg').attr('src','/resources/images/sh.png');
			break;
		default:
			break;
		}
		$('#detailView').modal();
	}
	
	function confirmOperation(confirmContent, url, params){
		var bn=layer.open({
			content: confirmContent,
			area: '600px',
			closeBtn:1,
			shadeClose:true,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//按钮【确定】的回调
				//数据存到服务器
				$.ajax({
					url:url,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							window.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
				layer.close(bn);
			},btn2: function(index, layero){
				//按钮【取消】的回调
			}
			,cancel: function(){
				//右上角关闭回调
			}
		});
	}
	//校验参数合法性
	function validate(params){
		if(!params.billtypeid){
			layer.msg('请选择订单类型', {icon: 5});return false;
		}
		if(!params.billtime){
			layer.msg('请选择业务日期', {icon: 5});return false;
		}
		if(!params.customerid){
			layer.msg('请选择客户', {icon: 5});return false;
		}
		if(!params.materielid){
			layer.msg('请选择物料', {icon: 5});return false;
		}
		if(!params.warehouseid){
			layer.msg('请选择仓库', {icon: 5});return false;
		}
		if(!params.salessum){
			layer.msg('请输入订单数量', {icon: 5});return false;
		}
		if(!params.taxprice){
			layer.msg('请输入含税单价', {icon: 5});return false;
		}
		if(!params.taxrate){
			layer.msg('请输入税率', {icon: 5});return false;
		}
		if(!params.untaxprice){
			layer.msg('请输入不含税单价', {icon: 5});return false;
		}
		return params;
	}
	//GET新增销售订单参数
	function getAddSalesApplication(){
		var billtypeid = $('#a_billtype').attr('billtypeid');billtypeid = $.trim(billtypeid);
		var billtimeStr = $('#a_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
		var customerid = $('#a_customer').attr('customerid');customerid = $.trim(customerid);
		var materielid = $('#a_materiel').attr('materielid');materielid = $.trim(materielid);
		var warehouseid = $('#a_warehouse').attr('warehouseid');warehouseid = $.trim(warehouseid);
		var salessum = $('#a_salessum').val();salessum = $.trim(salessum);
		var taxprice = $('#a_taxprice').val();taxprice = $.trim(taxprice);
		var taxrate = $('#a_taxrate').val();taxrate = $.trim(taxrate);
		var untaxprice = $('#a_untaxprice').val();untaxprice = $.trim(untaxprice);
		return {
			billtypeid:billtypeid,
			billtime:str2Long(billtimeStr),
			customerid:customerid,
			materielid:materielid,
			warehouseid:warehouseid,
			salessum:salessum,
			taxprice:taxprice,
			taxrate:taxrate,
			untaxprice:untaxprice
		};
	}
	//新增销售订单
	function addSalesApplication(_this){
		var params = getAddSalesApplication();
		if(validate(params)){
			var index = layer.load(2, {
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			$.ajax({
				url:URL.addBtnUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						_this.disabled = false;
						win.location.reload(true);
					}else{
						layer.msg(result.error, {icon: 5});
						_this.disabled = false;
					}
					layer.close(index);
				}
			});
		}else{
			_this.disabled = false;
		}
	}
	//GET修改销售订单参数
	function getUpdateSalesApplicationParams(){
		var id = $('#u_id').val();
		var billtypeid = $('#u_billtype').val();billtypeid = $.trim(billtypeid);
		var billtimeStr = $('#u_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
		var customerid = $('#u_customer').attr('customerid');customerid = $.trim(customerid);
		var detailid = $('#u_detailid').val();
		var materielid = $('#u_materiel').attr('materielid');materielid = $.trim(materielid);
		var warehouseid = $('#u_warehouse').attr('warehouseid');warehouseid = $.trim(warehouseid);
		var salessum = $('#u_salessum').val();salessum = $.trim(salessum);
		var taxprice = $('#u_taxprice').val();taxprice = $.trim(taxprice);
		var taxrate = $('#u_taxrate').val();taxrate = $.trim(taxrate);
		var untaxprice = $('#u_untaxprice').val();untaxprice = $.trim(untaxprice);
		return {
			id:id,
			billtypeid:billtypeid,
			billtime:str2Long(billtimeStr),
			customerid:customerid,
			detailid:detailid,
			materielid:materielid,
			warehouseid:warehouseid,
			salessum:salessum,
			taxprice:taxprice,
			taxrate:taxrate,
			untaxprice:untaxprice
		};
	}
	function updateSalesApplication(_this){
		var params = getUpdateSalesApplicationParams();
		if(validate(params)){
			var index = layer.load(2, {
				  shade: [0.3,'#fff'] //0.1透明度的白色背景
				});
			$.ajax({
				url:URL.updateBtnUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						_this.disabled = false;
						win.location.reload(true);
					}else{
						layer.msg(result.error, {icon: 5});
						_this.disabled = false;
					}
					layer.close(index);
				}
			});
		}else{
			_this.disabled = false;
		}
	}
	
})(jQuery, window);