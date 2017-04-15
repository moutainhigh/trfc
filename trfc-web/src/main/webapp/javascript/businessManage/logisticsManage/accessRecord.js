;(function($, win){
	var URL = {
			page: '/trfc/accessRecord/page',
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch"
	};
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化查询
		searchParamsGetData(1);
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
	    }).on('input propertychange',function(){
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
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('vehicleid');
	    }).change(function(){
	    	if(!$(this).attr('vehicleid')){
	    		$(this).val('');
	    	}
	    });
	}
	//绑定按钮
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			searchParamsGetData(1);
		});
		$('#searchBtn').off('click').on('click', function(){
			searchParamsGetData(1);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				searchParamsGetData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			searchParamsGetData(1);
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
		var code = $('#code').val();code = $.trim(code);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid');vehicleid = $.trim(vehicleid);
		var businesstype = $('#businesstype').val();businesstype = $.trim(businesstype);
		var accesstype = $('#accesstype').val();accesstype = $.trim(accesstype);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			code: code,
			materielid: materielid,
			vehicleid: vehicleid,
			businesstype: businesstype,
			accesstype: accesstype,
			starttime: str2Long(starttime),
			endtime: str2Long(endtime),
			pageSize: pageSize
		}
	}
	//发送搜索请求
	function searchParamsGetData(pageNo){
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
					    	searchParamsGetData(pageNo+1);
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
		if(list && list.length > 0){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				$('<tr>').append('<td>'+(i+1)+'</td>')
							  .append('<td>'+(obj.code || '')+'</td>')
							  .append('<td>'+(obj.businesstype ? obj.businesstype == '1' ? '采购' : obj.businesstype == '2' ? '销售' : obj.businesstype == '3' ? '其他入库' : obj.businesstype == '4' ? '其他出库' : '' : '')+'</td>')
							  .append('<td>'+(obj.accesstype ? obj.accesstype == '1' ? '入厂' : obj.accesstype == '2' ? '出厂' : '' : '')+'</td>')
							  .append('<td>'+(obj.vehicleno || '')+'</td>')
							  .append('<td>'+(obj.materielname || '')+'</td>')
							  .append('<td>'+(obj.noticecode || '')+'</td>')
							  .append('<td>'+(obj.otherparty || '')+'</td>')
							  .append('<td>'+(obj.rfid || '')+'</td>')
							  .append('<td>'+(obj.icardno || '')+'</td>')
							  .append('<td>'+(obj.icardcode || '')+'</td>')
							  .append('<td>'+(obj.entertimeStr || '')+'</td>')
							  .append('<td>'+(obj.outtimeStr || '')+'</td>')
							  .appendTo('#dataBody');
			}
		}else{
			layer.msg('暂无数据.');
		}
	}
})(jQuery, window);