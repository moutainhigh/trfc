;(function($){
	var URL = {
		page: '/trfc/exceptionAudit/page',
		customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
		vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
		materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"	
	};
	init();
	function init(){

		//初始化默认查询当天的数据
			var array = getTodayStr();
			$('#starttime').val(array[0]);
			$('#endtime').val(array[1]);

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
			var type = $('#type').val();
			switch (type) {
			case '1':
				window.location.href = '/trfc/exceptionAudit/emptyCarLeavingFactory/auditView?id=' + obj.id;
				break;
			case '4':
				window.location.href = '/trfc/exceptionAudit/noNeedToFillTheBag/auditView?id=' + obj.id;
				break;
			default:
				break;
			}
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
		var startTime = $('#starttime').val();startTime = $.trim(startTime);
		var endTime = $('#endtime').val();endTime = $.trim(endTime);
		var pnCode = $('#pn_code').val();pnCode = $.trim(pnCode);
		var customerId = $('#customer').attr('customerId');customerId = $.trim(customerId);
		var materialId = $('#materiel').attr('materialId');materialId = $.trim(materialId);
		var vehicleId = $('#vehicle').attr('vehicleId');vehicleId = $.trim(vehicleId);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		var type = $('#type').val();
		var auditStatus = $('#auditStatus').val();auditStatus=$.trim(auditStatus);
		return {
			startTime: str2Long(startTime),
			endTime: str2Long(endTime),
			pnCode: pnCode,
			customerId: customerId,
			materialId: materialId,
			vehicleId: vehicleId,
			type: type,
			auditStatus:auditStatus,
			pageSize: pageSize
		}
	}
	
	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var day1 = myDate.getDate()+1;        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		if(day1<10){
			day1 = "0"+day1;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day1+" "+"00:00:00";
		return array;
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
				.append('<td>'+(obj.pnCode || '')+'</td>')
				.append('<td>'+(obj.returnStatus == '0' ? '未推单' : obj.returnStatus == '1' ? '推单中' : obj.returnStatus == '2' ? '已推单' : '')+'</td>')
				.append('<td>'+(obj.auditStatus == '0' ? '未审批' : obj.auditStatus == '1' ? '已审批' : '')+'</td>')
				.append('<td>'+(obj.noticeCode || '')+'</td>')
				.append('<td>'+(obj.customerName || '')+'</td>')
				.append('<td>'+(obj.materialName || '')+'</td>')
				.append('<td>'+(obj.vehicleNo || '')+'</td>')
				.append('<td>'+(obj.grossWeight || '')+'</td>')
				.append('<td>'+(obj.tareWeight || '')+'</td>')
				.append('<td>'+(obj.netWeight || '')+'</td>')
				.append('<td></td>')
				.append('<td>'+(obj.lightTime || '')+'</td>')
				.append('<td>'+(obj.weightTime || '')+'</td>')
				.data(obj)
				.appendTo('#dataBody');
			}
			$('#dataBody tr').off('dblclick').on('dblclick', function() {
				var obj = $(this).data();
				var type = $('#type').val();
				switch (type) {
				case '1':
					window.location.href = '/trfc/exceptionAudit/emptyCarLeavingFactory/auditView?id=' + obj.id;
					break;
				case '4':
					window.location.href = '/trfc/exceptionAudit/noNeedToFillTheBag/auditView?id=' + obj.id;
					break;
				default:
					break;
				}
			});
		} else {
			layer.msg('暂无数据.');
		}
	}
})(jQuery);