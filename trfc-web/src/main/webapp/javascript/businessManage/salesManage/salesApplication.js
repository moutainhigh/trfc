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
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch"
	};
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	
	init();
	function init(){
		//初始化默认查询当天的数据
		var array = getTodayStr();
		$('#s_starttime').val(array[0]);
		$('#s_endtime').val(array[1]);

		//初始化autocomplete
		initAutoComplete();
		bindEvent();
		queryData(1);
	}
	function initAutoComplete(){
		var cache = {};
	    $(".materiel").autocomplete({
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
	    $(".warehouse").autocomplete({
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
	    $(".customer").autocomplete({
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
	    		$('.channelcode').val(ui.item.channelcode);
	    		$('.salesmanname').val(ui.item.salesmanname);
	    		$('.transportcompanyname').val(ui.item.transportcompanyname);
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
        		$('#u_salesmanname').val('');
        		$('#u_transportcompanyname').val('');
    		}
	    }); 
	    $(".vehicle").autocomplete({
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
	    		$('.add_detail .rfid').val(ui.item.rfid);
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
	    $(".driver").autocomplete({
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
	    		$('.add_detail .idNo').val(ui.item.identityno);
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
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
			layer.closeAll('dialog');
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#a_addRow').off('click').on('click',function(){
			a_addRow();
		});
		$('#update').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			showUpdate(obj);
		});
		$('#audit').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			audit(obj);
		});
		$('#unaudit').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			unaudit(obj);
		});
		$('#delete').off('click').on('click',function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			deleteData(obj);
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
		$('#oneBillOneCar').off('click').on('click', function(){
			layer.closeAll();
			$.get(URL.initAddUrl, null, function(result) {
				if(result.code == '000000'){
					var data = result.data;
					if(data){
						$('#addView .billCode').val(data.code);
						$('#addView .billTime').val(data.nowDate);
						$('#addView').modal();
						$('#addView #billType').val(0);
						$('#addView .add_detail ul li.oneBillOneCarVehicle').show();
					}else{
						layer.msg("初始化失败，请稍后重试！");
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			});
		});
		$('#oneBillMoreCar').off('click').on('click', function(){
			layer.closeAll();
			$.get(URL.initAddUrl, null, function(result) {
				if(result.code == '000000'){
					var data = result.data;
					if(data){
						$('#addView .billCode').val(data.code);
						$('#addView .billTime').val(data.nowDate);
						$('#addView').modal();
						$('#billType').val(1);
						$('#addView .add_detail ul li.oneBillOneCarVehicle').hide();
					}else{
						layer.msg("初始化失败，请稍后重试！");
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			});
		});
		$('#addCommitBtn').off('click').on('click', function(){
			if($('#addView').is(':visible')){
				this.disabled = true;
				addSalesApplication(this, $('#addView #billType').val());
			}
		});
		$('#updateCommitBtn').off('click').on('click',function(){
			if($('#updateView').is(':visible')){
				this.disabled = true;
				updateSalesApplication(this, $('#updateView #billType').val());
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
	

	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day+" "+"23:59:59";
		return array;
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
						.data(obj)
						.appendTo('#dataBody');
			}
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
		var a = obj;
		var b = obj.list[0]
		$('#updateView #billId').val(a.id);
		$('#updateView #billDetailId').val(b.id);
		$('#updateView #billType').val(a.billtypeid);
		$('#updateView .billCode').val(a.code);
		$('#updateView .billTime').val(a.billtimeStr);
		$('#updateView .customer').val(a.customername).attr('customerid', a.customerid);
		$('#updateView .channelcode').val(a.channelcode);
		$('#updateView .salesOrg').val(a.orgid);
		$('#updateView .salesmanname').val(a.salesmanname);
		$('#updateView .transportcompanyname').val(a.transportcompanyname);
		$('#updateView .makebillname').val(a.makebillname);
		$('#updateView .add_detail .materiel').val(b.materielname).attr('materielid', b.materielid);
		$('#updateView .add_detail .warehouse').val(b.warehousename).attr('warehouseid', b.warehouseid);
		$('#updateView .add_detail .number').val(b.salessum);
		if (a.billtypeid == 0) {
			$('#updateView .add_detail .vehicle').val(a.vehicleNo).attr('vehicleid', a.vehicleId);
			$('#updateView .add_detail .rfid').val(a.rfid);
			$('#updateView .add_detail .driver').val(a.driverName).attr('driverid', a.driverId);
			$('#updateView .add_detail .idNo').val(a.idNo);
			$('#updateView .add_detail .remark').val(a.remarks);
			$('#updateView .add_detail ul li.oneBillOneCarVehicle').show();
		} else {
			$('#updateView .add_detail ul li.oneBillOneCarVehicle').hide();
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
		if(obj.status == '0'){
			layer.msg('未审核的单据，无需反审！', {icon: 5});
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
		if(obj.source == '0'){
			layer.msg('联机的单据，不能刪除！', {icon: 5});
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
							$('#refreshBtn').trigger('click');
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
		if(!params.billTime){
			layer.msg('请选择业务日期', {icon: 5});return false;
		} else {
			params.billTime = Date.parseYMD_HMS(params.billTime).getTime();
		}
		if(!params.customer){
			layer.msg('请选择客户', {icon: 5});return false;
		}
		if(!params.salesOrg){
			layer.msg('请输入订单数量', {icon: 5});return false;
		}
		if(!params.materiel){
			layer.msg('请选择物料', {icon: 5});return false;
		}
		if(!params.warehouse){
			layer.msg('请选择仓库', {icon: 5});return false;
		}
		if(!params.number){
			layer.msg('请输入订单量', {icon: 5});return false;
		}
		if(params.billType){
			if(params.billType == 0){
				if(!params.vehicle){
					layer.msg('请选择车辆', {icon: 5});return false;
				}
			}
		} else {
			layer.msg('订单类型不能为空', {icon: 5});return false;
		}
		return params;
	}
	//GET新增销售订单参数
	function getAddSalesApplication(billType){
		var billTime = $('#addView .billTime').val();
		var customer = $('#addView .customer').attr('customerid');
		var salesOrg = $('#addView .salesOrg').val();
		var materiel = $('#addView .materiel').attr('materielid');
		var warehouse = $('#addView .warehouse').attr('warehouseid');
		var number = $('#addView .number').val();
		var vehicle = $('#addView .vehicle').attr('vehicleid');
		var driver = $('#addView .driver').attr('driverid');
		var remark = $('#addView .remark').val();
		return {
			billType: billType,
			billTime: billTime,
			customer: customer,
			salesOrg: salesOrg,
			materiel: materiel,
			warehouse: warehouse,
			number: number,
			vehicle: vehicle,
			driver: driver,
			remark: remark
		};
	}
	//新增销售订单
	function addSalesApplication(_this, billType/*订单类型（0：一单一车，1:一单多车）*/){
		var params = getAddSalesApplication(billType);
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
	function getUpdateSalesApplicationParams(billType){
		var billId = $('#updateView #billId').val();
		var billDetailId = $('#updateView #billDetailId').val();
		var billTime = $('#updateView .billTime').val();
		var customer = $('#updateView .customer').attr('customerid');
		var salesOrg = $('#updateView .salesOrg').val();
		var materiel = $('#updateView .materiel').attr('materielid');
		var warehouse = $('#updateView .warehouse').attr('warehouseid');
		var number = $('#updateView .number').val();
		var vehicle = $('#updateView .vehicle').attr('vehicleid');
		var driver = $('#updateView .driver').attr('driverid');
		var remark = $('#updateView .remark').val();
		return {
			id: billId,
			detailId: billDetailId,
			billType: billType,
			billTime: billTime,
			customer: customer,
			salesOrg: salesOrg,
			materiel: materiel,
			warehouse: warehouse,
			number: number,
			vehicle: vehicle,
			driver: driver,
			remark: remark
		};
	}
	function updateSalesApplication(_this, billType){
		var params = getUpdateSalesApplicationParams(billType);
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