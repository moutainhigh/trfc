;(function($){
	var URL = {
			page: '/trfc/poundNote/otherInto/page',
			addView: '/trfc/poundNote/otherInto/addView',
			invalid: '/trfc/poundNote/otherInto/invalid',
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			yardAutoCompleteSearch: "/trfc/yard/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch"
	};
	//初始化
	init();
	//初始化方法
	function init(){
		//初始化默认查询当天的数据
		var array = getTodayStr();
		$('#starttime').val(array[0]);
		$('#endtime').val(array[1]);
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化列表
		getDataFormAjax(1);
	}
	//初始化autocomplete
	function initAutoComplete(){
		var cache = {};
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
	}
	//绑定按钮事件
	function initBindEvent(){
		$('#searchBtn').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#refresh').off('click').on('click', function(){
			getDataFormAjax(1);
			layer.closeAll('dialog');
		});
		$('#addBtn').off('click').on('click', function(){
			window.open(URL.addView);
		});
		$('#invalid').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			invalid(obj);
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
	
	function getSearchParams(){
		var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
		var warehouseid = $('#warehouse').attr('warehouseid') || '';warehouseid = $.trim(warehouseid);
		var supplierid = $('#supplier').attr('supplierid') || '';supplierid = $.trim(supplierid);
		var materielid = $('#materiel').attr('materielid') || '';materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid') || '';vehicleid = $.trim(vehicleid);
		var code = $('#code').val() || '';code = $.trim(code);
		var operator = $('#operator').val() || '';operator = $.trim(operator);
		var netweight = $('#netweight').val() || '';netweight = $.trim(netweight);
		var status = $('#status').val() || '';status = $.trim(status);
		var pageSize = $('#pageSize').val() || '';pageSize = $.trim(pageSize);
		return {
			starttime: Date.parseTime_YMD_HMS(starttime) || '',
			endtime: Date.parseTime_YMD_HMS(endtime) || '',
			warehouseid: warehouseid,
			supplierid: supplierid,
			materielid: materielid,
			vehicleid: vehicleid,
			code: code,
			operator: operator,
			netweight: netweight,
			status: status,
			pageSize: pageSize
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
	
	//异步请求获取分页数据
	function getDataFormAjax(pageNo){
		var params = getSearchParams();
		params.pageNo = pageNo;
		var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
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
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}
	//解析加载页面
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || {};
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+(obj.code || '')+'</td>')
						.append('<td'+(obj.status == '1' || obj.status == '3' ? ' class="colorred"' : '')+'>'+(obj.status == '0' ? '计量系统' : obj.status == '1' ? '补增入库' : obj.status == '3' ? '作废' : '')+'</td>')
						.append('<td>'+(obj.noticecode || '')+'</td>')
						.append('<td>'+(obj.suppliername || '')+'</td>')
						.append('<td>'+(obj.department || '')+'</td>')
						.append('<td>'+(obj.receivedepartmentname || '')+'</td>')
						.append('<td>'+(obj.warehousename || '')+'</td>')
						.append('<td>'+(obj.materialname || '')+'</td>')
						.append('<td>'+(obj.cargo || '')+'</td>')
						.append('<td>'+(obj.vehicleno || '')+'</td>')
						.append('<td>'+(obj.grossweight || '')+'</td>')
						.append('<td>'+(obj.tareweight || '')+'</td>')
						.append('<td>'+(obj.netweight || '')+'</td>')
						.append('<td>'+(obj.lighttimeStr || '')+'</td>')
						.append('<td>'+(obj.weighttimeStr || '')+'</td>')
						.append('<td>'+(obj.makebillname || '')+'</td>')
						.append('<td>'+(obj.makebilltimeStr || '')+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
		}else{
			layer.msg("暂无数据.");
		}
	}
	//作废
	function invalid(data){
		if(data.status != '3'){
			layer.confirm('确认要作废此单据吗？', {
				btn: ['确定', '取消'],
				area: '600px'
			}, function(){
				$.ajax({
					url:URL.invalid,
					data:{
						id: data.id
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							layer.msg(result.error, {icon: 1});
							$('#refresh').click();
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			});
		}else{
			layer.msg('该单据已作废！', {icon: 5});
			return;
		}
	}
})(jQuery);