;(function($){
	var URL = {
		page: '/trfc/infraredBlock/page',
		audit: '/trfc/infraredBlock/audit',
		customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
		vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
		materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"	
	};
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化查询
		getDataFormAjax(1);
	}
	function initAutoComplete(){
		var cache = {};
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
				$(this).val(ui.item.name).attr('customerId', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('customerId');
		}).change(function(){
			if(!$(this).attr('customerId')){
				$(this).val('');
			}
		});
		$("#material").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.materialAutoCompleteSearch, request, function( data, status, xhr ) {
					material[ term ] = data;
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
				$(this).val(ui.item.name).attr('materialId', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('materialId');
		}).change(function(){
			if(!$(this).attr('materialId')){
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
				$(this).val(ui.item.vehicleno).attr('vehicleId', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('vehicleId');
		}).change(function(){
			if(!$(this).attr('vehicleId')){
				$(this).val('');
			}
		});
	}
	
	//绑定按钮
	function initBindEvent(){
		$('#refresh').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#search').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#audit').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
				//window.location.href = '/trfc/exceptionAudit/emptyCarLeavingFactory/auditView?id=' + obj.id;
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				getDataFormAjax(pageNo);
			}
		});
		$('#pageSize').change(function(){
			getDataFormAjax(1);
		});
	}

	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//获取搜索参数
	function getParams(){
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pnCode = $('#pn_code').val();pnCode = $.trim(pnCode);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			starttime: str2Long(starttime),
			endtime: str2Long(endtime),
			pnCode: pnCode,
			pageSize: pageSize
		}
	}
	//发送搜索请求
	function getDataFormAjax(pageNo){
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
							getDataFormAjax(pageNo+1);
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
	//渲染页面
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if (list && list.length > 0) {
			for (var i=0;i<list.length;i++) {
				var obj = list[i];
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+(obj.pnId || '')+'</td>')
				.append('<td>'+(obj.remark || '')+'</td>')
				.append('<td>'+(obj.auditStatus == '0' ? '未审批' : obj.auditStatus == '1' ? '已审批' : '')+'</td>')
				.append('<td>'+(obj.auditPerson || '')+'</td>')
				.append('<td>'+(obj.auditTime || '')+'</td>')
				.append('<td>'+(obj.createtime || '')+'</td>')
				
				.data(obj)
				.appendTo('#dataBody');
			}
			$('#dataBody tr').off('dblclick').on('dblclick', function() {
				var obj = $(this).data();
					//window.location.href = '/trfc/exceptionAudit/emptyCarLeavingFactory/auditView?id=' + obj.id;
			});
		} else {
			layer.msg('暂无数据.');
		}
	}
})(jQuery);