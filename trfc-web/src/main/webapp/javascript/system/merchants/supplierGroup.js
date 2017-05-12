;(function($){
	var URL = {
			page: '/trfc/system/merchants/supplierGroup/page',
			supplierAutoCompleteSearch: '/trfc/supplier/autoCompleteSearch',
			supplierAutoCompleteNotGroupSearch: '/trfc/system/merchants/supplierGroup/supplierAutoCompleteNotGroupSearch',
			addSupplierGroup: '/trfc/system/merchants/supplierGroup/addSupplierGroup',
			addSupplierToGroup: '/trfc/system/merchants/supplierGroup/addSupplierToGroup',
			supplierGroupDetail: '/trfc/system/merchants/supplierGroup/supplierGroupDetail'
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
	    		var flag = true;
	    		$(this).closest('tr').siblings().each(function(){
	    			if($(this).find('td:eq(1) input').attr('supplierid') == ui.item.id){
	    				flag = false;
	    				return;
	    			}
	    		});
	    		if(flag){
	    			$(this).val(ui.item.name).attr('supplierid', ui.item.id).attr('select',true);
		    		$(this).closest('tr').next('tr').find('input').attr('readonly',false).attr('disabled', false);
		    		return false;
	    		}else{
	    			layer.tips('该供应商已选择！', this, {
						tips: [1, '#3595CC'],
						time: 2000
					});
	    			return false;
	    		}
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
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addSupplierGroupBtn').off('click').on('click',function(){
			if($('#add').is('visible')){
				_this.disabled = true;
				addSupplierGroup(_this);
			}
		});
		$('#addSupplierToGroupBtn').off('click').on('click',function(){
			if($('#addGroup').is('visible')){
				_this.disabled = true;
				addSupplierToGroup(_this);
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
				var obj = list[i] || {};
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+(obj.suppliercode || '')+'</td>')
						.append('<td>'+(obj.suppliername || '')+'</td>')
						.append('<td>'+(obj.remark || '')+'</td>')
						.append('<td><span><a data-toggle="modal" data-target="#addGroup" class="updateView"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="添加成员">&#xe632;</i></a></span>'
								+'<span><a class="detailView"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="添加成员">&#xe62c;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody .updateView').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#supplierGroup').val(obj.suppliername || '').attr('groupid', obj.supplierid || '');
				$('#tab').find('input').val('').removeClass('supplierid');
				$('#tab').find('tbody>tr:gt(0) input').attr('readonly',true).attr('disabled',true);
			});
			$('#dataBody .detailView').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#supplierGroupDetail').val(obj.suppliername || '');
				showGroupDetail(obj.supplierid)
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	//GET组成员
	function showGroupDetail(groupid){
		$.ajax({
			url : URL.supplierGroupDetail,
			data:{
				groupid: groupid
			},
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					loadGroupDetail(result.data);
					$('#detail').modal('show');
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
	}
	//加载组成员
	function loadGroupDetail(list){
		$('#tab_detail tbody').empty();
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || {};
				$('<tr>').append('<td style="line-height: 30px;">'+(i+1)+'</td>')
						 .append('<td style="line-height: 30px;">'+(obj.suppliercode || '')+'</td>')
						 .append('<td style="line-height: 30px;max-width: 200px;text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">'+(obj.suppliername || '')+'</td>')
						 .append('<td style="line-height: 30px;">'+(obj.remark || '')+'</td>')
						 .appendTo('#tab_detail tbody');
			}
		}
	}
	//获取组参数
	function getAddSupplierGroupParams(){
		var supplierid = $('#a_supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var remark = $('#remark').val();
		return {
			supplierid: supplierid,
			remark: remark
		}
	}
	//校验参数
	function validate(params){
		if(!params.supplierid){
			layer.msg('请选择供应商！', {icon: 5}); return false;
		}
		return params;
	}
	//添加组
	function addSupplierGroup(_this){
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
					_this.disabled = false;
				}
			});
		}else{
			_this.disabled = false;
		}
	}
	//获取组成员参数
	function getAddSupplierToGroupParams(){
		var groupid = $('#supplierGroup').attr('groupid');
		var childrenList = [];
		$('#tab tbody').find('tr').each(function(){
			var input1 = $(this).find('td:eq(1) input');
			var input1_val_id = input1.attr('supplierid'); input1_val_id = $.trim(input1_val_id);
			if(!input1.disabled && input1_val_id){
				var input2 = $(this).find('td:eq(2) input');
				childrenList.push({
					supplierid: input1_val_id,
					remark: input2.val()
				});
			}
		});
		return {
			groupid: groupid,
			childrenList: JSON.stringify(childrenList)
		}
	}
	//校验参数
	function validateAddSupplierToGroup(params){
		if(!params.groupid){
			layer.msg('请先选择供应商再添加！', {icon: 5}); return false;
		}
		if(!params.childrenList || params.childrenList.length == 0){
			layer.msg('请在权限明细中添加供应商！', {icon: 5}); return false;
		}
		return params;
	}
	//添加组成员
	function addSupplierToGroup(_this){
		var params = getAddSupplierToGroupParams();
		if(validateAddSupplierToGroup(params)){
			$.ajax({
				url : URL.addSupplierToGroup,
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
					_this.disabled = false;
				}
			});
		}else{
			_this.disabled = false;
		}
	}
	
})(jQuery);