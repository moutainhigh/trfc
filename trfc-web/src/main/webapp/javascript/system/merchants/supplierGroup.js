;(function($){
	var URL = {
			page: '/trfc/system/merchants/supplierGroup/page',
			supplierAutoCompleteSearch: '/trfc/supplier/autoCompleteSearch',
			supplierAutoCompleteNotGroupSearch: '/trfc/system/merchants/supplierGroup/supplierAutoCompleteNotGroupSearch',
			addSupplierGroup: '/trfc/system/merchants/supplierGroup/addSupplierGroup'
	};
	//初始化方法
	init();
	//初始化内容
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//绑定DOM事件
		bindEvent();
		//初始化分页
		queryData(1);
	}
	//初始化autocomplete
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
	    $("#a_supplier").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		$.post( URL.supplierAutoCompleteNotGroupSearch, request, function( data, status, xhr ) {
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
	    $(".supplier").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		$.post( URL.supplierAutoCompleteNotGroupSearch, request, function( data, status, xhr ) {
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
	    		$(this).closest('tr').next('tr').find('input').attr('readonly',false).attr('disabled', false);
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
	//初始化DOM事件
	function bindEvent(){
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addSupplierGroupBtn').off('click').on('click',function(){
			addSupplierGroup();
		});
		$('#addSupplierToGroupBtn').off('click').on('click',function(){
			addSupplierToGroup();
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
	}
	//GET分页条件
	function getQueryParams(){
		var params = {};
		var qtp = $('#qtp').val(); qtp = $.trim(qtp);
		var keyword = $('#keyword').val(); keyword = $.trim(keyword);
		if(qtp == 'bh'){
			params.suppliercode = keyword;
		}
		if(qtp == 'mc'){
			params.suppliername = keyword;
		}
		var supplierid = $('#supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		params.supplierid = supplierid;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		return params;
	}
	//查询分页
	function queryData(pageNo){
		var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getQueryParams();
		params.pageNo = pageNo;
		$.ajax({
			url : URL.page,
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
					    	queryData(pageNo+1);
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
	//渲染分页
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+(obj.suppliercode || '')+'</td>')
						.append('<td>'+(obj.suppliername || '')+'</td>')
						.append('<td>'+(obj.remark || '')+'</td>')
						.append('<td><span><a data-toggle="modal" data-target="#update" class="updateView"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="添加成员">&#xe630;</i></a></span>'
								+'<span><a data-toggle="modal" data-target="#update1" class="updateView1"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="添加成员">&#xe62c;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody .updateView').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#supplierGroup').val(obj.suppliername || '').attr('supplierid', obj.supplierid || '');
				$('#tab').find('input').val('').removeClass('supplierid');
				$('#tab').find('tbody>tr:gt(0) input').attr('readonly',true).attr('disabled',true);
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	
	function getAddSupplierGroupParams(){
		var supplierid = $('#a_supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var remark = $('#remark').val();
		return {
			supplierid: supplierid,
			remark: remark
		}
	}
	
	function validate(params){
		if(!params.supplierid){
			layer.msg('请选择供应商！', {icon: 5}); return false;
		}
		return params;
	}
	//添加组
	function addSupplierGroup(){
		var params = getAddSupplierGroupParams();
		if(validate(params)){
			$.ajax({
				url : URL.addSupplierGroup,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.reload();
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		}
	}
	
	
	function addSupplierToGroup(){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery);