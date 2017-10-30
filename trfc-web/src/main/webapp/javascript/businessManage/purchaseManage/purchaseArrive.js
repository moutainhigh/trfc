;(function($, win){
	//访问路径
	var URL = {
			page: '/trfc/purchaseArrive/page',
			addView: '/trfc/purchaseArrive/addView',
			updateView: '/trfc/purchaseArrive/updateView',
			audit: '/trfc/purchaseArrive/audit',
			unaudit: '/trfc/purchaseArrive/unaudit',
			invalid: '/trfc/purchaseArrive/invalid',
			outfactory: '/trfc/purchaseArrive/outfactory',
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch"
	};
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化页面按钮绑定事件
		initBindEvent();
		//初始化加载页面数据
		initPageList(1);
		//页面按钮绑定事件
	}
	function initAutoComplete(){
		var cache = {};
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
	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id).attr('select',true);
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
	//绑定按钮事件
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			initPageList(1);
			$('#ind_tab').hide();
			layer.closeAll('dialog');
		});
		$('#searchBtn').off('click').on('click', function(){
			initPageList(1);
		});
		$('#addBtn').off('click').on('click', function(e){
			e.stopPropagation();
			win.location.href = URL.addView;
		});
		$('#update').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			updateOperation(obj);
		});
		$('#audit').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			auditOperation(obj);
		});
		$('#unaudit').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			unauditOperation(obj);
		});
		$('#invalid').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			invalidOperation(obj);
		});
		$('#outfactory').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			outfactoryOperation(obj);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				initPageList(pageNo);
			}
		});
		$('#pageSize').change(function(){
			initPageList(1);
		});
	}
	//日期字符串转换成时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//获取查询条件
	function getParams(){
		var billcode = $('#billcode').val();billcode = $.trim(billcode);
		var code = $('#code').val();code = $.trim(code);
		var supplierid = $('#supplier').attr('supplierid');supplierid = $.trim(supplierid);
		var vehicleid = $('#vehicle').attr('vehicleid');vehicleid = $.trim(vehicleid);
		var auditstatus = $('#auditstatus').val();auditstatus = $.trim(auditstatus);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var driverid = $('#driver').attr('driverid');driverid = $.trim(driverid);
		var source = $('#source').val();source = $.trim(source);
		var status = $('#status').val();status = $.trim(status);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			billcode:billcode,
			code:code,
			supplierid:supplierid,
			vehicleid:vehicleid,
			auditstatus:auditstatus,
			materielid:materielid,
			driverid:driverid,
			source:source,
			status:status,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		}
	}
	//根据条件分页查询订单列表
	function initPageList(pageNo){
		var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.page,
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
					    	initPageList(pageNo+1);
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
	//解析数据加载列表
	function renderHtml(data){
		var list = data.list;
		$('#dataBody').empty();
		if(list && list.length > 0){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var purchaseApplication = obj.purchaseApplicationResp;
				var purchaseApplicationDetail = obj.purchaseApplicationDetailResp;
				var code = obj.code || '';
				var auditstatus = '';
				//设置字体颜色 (LXY)
				var color = '';
				switch (obj.auditstatus) {
				case '0': auditstatus = '未审核'; color = 'class="colorred"';break;
				case '1': auditstatus = '已审核'; break;
				default: break;
				}
				var source = '';
				switch (obj.source) {
				case '0': source = '业务平台'; break;
				case '1': source = '客商平台'; break;
				case '2': source = '客商APP'; break;
				default: break;
				}
				var status = '';
				switch (obj.status) {
				case '0': status = '未入厂'; break;
				case '1': status = '重车'; break;
				case '2': status = '空车'; break;
				case '3': status = '作废'; break;
				case '4': status = '发卡'; break;
				case '5': status = '出厂'; break;
				case '6': status = '入厂'; break;
				case '7': status = '卸货'; break;
				default: break;
				}
				var vehicleno = obj.vehicleno || '';
				var billcode = obj.billcode || '';
				var suppliername = purchaseApplication.suppliername || '';
				var materielname = purchaseApplicationDetail.materielname || '';
				var minemouthname = purchaseApplication.minemouthname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var billtimeStr = purchaseApplication.makebilltimeStr || '';
				var abnormalpersonname = obj.abnormalpersonname || '';
				var abnormaltimeStr = obj.abnormaltimeStr || '';
				var remark = obj.remark || '';
				var supplierremark = purchaseApplication.supplierremark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>').append('<td>'+code+'</td>')
						.append('<td '+color+'>'+auditstatus+'</td>').append('<td>'+source+'</td>')
						.append('<td>'+status+'</td>').append('<td>'+vehicleno+'</td>')
						.append('<td>'+billcode+'</td>').append('<td>'+suppliername+'</td>')
						.append('<td>'+materielname+'</td>').append('<td>'+minemouthname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>').append('<td>'+billtimeStr+'</td>')
						.append('<td>'+abnormalpersonname+'</td>').append('<td>'+abnormaltimeStr+'</td>')
						.append('<td>'+remark+'</td>').append('<td>'+supplierremark+'</td>')
						.data(obj).appendTo('#dataBody');
			}
			$('#dataBody>tr').off('click').on('click',function(){
				var obj = $(this).data();
				showMore(obj);
			});
			$('#dataBody>tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				//跳转到详情页面
				window.location.href = '/trfc/purchaseArrive/detailView?id='+obj.id;
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	//显示更多
	function showMore(obj){
		var purchaseApplication = obj.purchaseApplicationResp || {};
		var purchaseApplicationDetail = obj.purchaseApplicationDetailResp || {};
		var poundNoteResp = obj.poundNoteResp || {};
		$('#vehicleno').html(obj.vehicleno || '');
		$('#arrivalamount').html(obj.arrivalamount || '');
		$('#drivername').html(obj.drivername || '');
		$('#driveridentityno').html(obj.driveridentityno || '');
		$('#makebilltimeStr').html(obj.makebilltimeStr || '');
		$('#makebillname').html(obj.makebillname || '');
		$('#billno').html(obj.billcode || '');
		$('#suppliername').html(purchaseApplication.suppliername || '');
		$('#materielname').html(purchaseApplicationDetail.materielname || '');
		$('#orgname').html(purchaseApplication.orgname || '');
		$('#purchasesum').html(purchaseApplicationDetail.purchasesum || '');
		$('#billtime').html(purchaseApplication.makebilltimeStr || '');
		$('#poundnotecode').html(poundNoteResp.code || '');
		$('#vehicleno2').html(poundNoteResp.vehicleno || '');
		$('#grossweight').html(poundNoteResp.grossweight || '');
		$('#tareweight').html(poundNoteResp.tareweight || '');
		$('#netweight').html(poundNoteResp.netweight || '');
		$('#lighttime').html(poundNoteResp.lighttimeStr || '');
		$('#weighttime').html(poundNoteResp.weighttimeStr || '');
		$('#ind_tab').show();
	}
	//修改
	function updateOperation(obj){
		if(obj.auditstatus == '1'){
			layer.msg('此单据已审核，无法继续编辑！', {icon: 5});
			return;
		}
		if(obj.status != '0'){
			layer.msg('当前通知单已使用，无法编辑！', {icon: 5});
			return;
		}
		//跳转到修改页面
		window.location.href = '/trfc/purchaseArrive/updateView?id='+obj.id;
	}
	//审核
	function auditOperation(obj){
		if(obj.auditstatus == '1'){
			layer.msg('此单据已审核，无法继续审核！', {icon: 5});
			return;
		}
		if(obj.status == '4'){
			layer.msg('此单据已发卡，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('此单据已出厂，无法反审操作！', {icon: 5});
			return;
		}
		if(obj.status == '6'){
			layer.msg('此单据已入厂，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '7'){
			layer.msg('此单据已装货，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '3'){
			layer.msg('此单据已作废，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('此单据已使用，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '1'){
			layer.msg('此单据已核销，无法审核操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要审核吗？', URL.audit, {id: obj.id});
	}
	//反审
	function unauditOperation(obj){
		if(obj.auditstatus == '0'){
			layer.msg('此单据未审核,无法继续反审!', {icon: 5});
			return;
		}
		if(obj.status == '7'){
			layer.msg('此单据已装货,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '6'){
			layer.msg('此单据已入厂,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('此单据已出厂,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '4'){
			layer.msg('此单据已发卡,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '3'){
			layer.msg('此单据已作废,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('此单据已使用,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '1'){
			layer.msg('此单据已核销,无法反审操作!', {icon: 5});
			return;
		}
		confirmOperation('您确定要反审吗？', URL.unaudit, {id: obj.id});
	}
	//作废
	function invalidOperation(obj){
		if(obj.status == '3'){
			layer.msg('数据已作废，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('数据已出厂，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('数据已过重车，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status != '0' && obj.status != '4'){
			layer.msg('数据已入厂，不能进行作废操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要作废吗？', URL.invalid, {id: obj.id});
	}
	//强制出厂
	function outfactoryOperation(obj){
		if(obj.status == '3'){
			layer.msg('数据已作废，不能进行出厂操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('数据已出厂，不能进行出厂操作！', {icon: 5});
			return;
		}
		if(obj.status != '2'){
			layer.msg('数据未过重车，不能进行出厂操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要强制出厂吗？', URL.outfactory, {id: obj.id});
	}
	//confirm选择公共方法
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
})(jQuery, window);