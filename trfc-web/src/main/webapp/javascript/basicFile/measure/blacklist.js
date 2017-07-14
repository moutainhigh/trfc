;(function($, win){
	//请求路径
	var URL = {
			page: '/trfc/blacklist/page',
			add: '/trfc/blacklist/add',
			del:'/trfc/blacklist/del',
			autoCompleteSearch:'/trfc/vehicle/autoCompleteNotBlackSearch'
	};
	//初始化
	init();
	function init(){
		initAutoComplete();
		bindEvent();
		getDataFormAjax(1);	
	}
	//初始化autocomplete
	function initAutoComplete(){
		var cache = {};
	    $(".vehicle").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var vehicle = cache['vehicle'] || {};
	    		if ( term in vehicle ) {
	    			response( vehicle[ term ] );
	    			return;
	    		}
	    		$.post( URL.autoCompleteSearch, request, function( data, status, xhr ) {
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
	//绑定事件
	function bindEvent(){
		$('#refresh').off('click').on('click',function(){
			layer.closeAll('dialog');
			getDataFormAjax(1);
		});
		$('#search').off('click').on('click',function(){
			getDataFormAjax(1);
		});
		$('#add').off('click').on('click',function(){
			$('#addView').modal('show');
		});
		$('#addCommit').off('click').on('click', function(){
			addBlacklist();
		});
		$('#delete').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			delBlacklist(obj.id);
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
	//获取查询条件
	function getSearchParams(){
		var vehicleId = $('#vehicle').attr('vehicleId');vehicleId = $.trim(vehicleId);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			vehicleid: vehicleId,
			pageSize: pageSize
		}
	}
	//分页查询数据
	function getDataFormAjax(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		//获取查询条件
		var params = getSearchParams();
		params.pageNo = pageNo;
		$.post(URL.page, params, function(result){
			if (result) {
				if (result.code == '000000') {
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo) {
					    	getDataFormAjax(pageNo);
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
				} else {
					layer.msg(result.error, {icon: 5});
				}
			} else {
				layer.msg('请求失败，请稍候重试！', {icon: 5});
			}
			layer.close(index);
		});
	}
	//解析分页数据
	function renderHtml(data) {
		$('#dataBody').empty();
		var list = data.list;
		if (list && list.length>0) {
			for (var i = 0; i < list.length; i++) {
				var obj = list[i] || {};
				$('<tr>').append('<td>' + (i + 1) + '</td>')
						 .append('<td>'+(obj.vehicleNo || '')+'</td>')
						 .append('<td>'+(obj.createName || '')+'</td>')
						 .append('<td>'+(obj.createTimeStr || '')+'</td>')
						 .append('<td>'+(obj.remarks || '')+'</td>')
						 .data(obj)
						 .appendTo('#dataBody');
			}
		} else {
			layer.msg('暂无数据...');
		}
	}
	
	function getAddParams(){
		var vehicleId = $('#add_vehicle').attr('vehicleId'); 
		var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
		return {
			vehicleid: vehicleId,
			remarks: remarks
		};
	}
	
	function validateAdd(params){
		if(!params.vehicleid){
			layer.msg('请先选择车辆！', {icon: 5}); return false;
		}
		return params;
	}
	function addBlacklist() {
		if($('#addView').is(':visible')){
			var params = getAddParams();
			if (validateAdd(params)) {
				$.post(URL.add, params, function(result){
					if(result.code='000000'){
						layer.msg('操作成功！', {icon:1})
						getDataFormAjax(1);
					}else{
						layer.msg(result.error,{icon:5});
					}
				});
			}
		}
	}
	
	function delBlacklist(id) {
		layer.confirm('确认要移除黑名单吗？', {
			btn: ['确定', '取消'],
			area: '600px',
		}, function(index){
			$.post(URL.del, {id: id}, function(result){
				if(result.code='000000'){
					layer.msg('操作成功！', {icon:1})
					getDataFormAjax(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
			layer.close(index);
		});
	}
	
})(jQuery, window);