;(function($){
	var URL = {
			page: '/trfc/primary/page',
			addView: '/trfc/primary/addView',
			add: '/trfc/primary/add',
			updateView: '/trfc/primary/updateView',
			update: '/trfc/primary/update',
			deletePs: '/trfc/primary/delete',
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"
	};
	//初始化
	init();
	//初始化方法
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化页面按钮绑定事件
		initBindEvent();
		//初始化查询页面
		getPrimarySettingData(1);
	}
	function initAutoComplete(){
		var cache = {};
	    $("#supplier, #a_supplier, #u_supplier").autocomplete({
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
	    $("#material, #a_material, #u_material").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var material = cache['material'] || {};
	    		if ( term in material ) {
	    			response( material[ term ] );
	    			return;
	    		}
	    		$.post( URL.materielAutoCompleteSearch, request, function( data, status, xhr ) {
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
	    		$(this).val(ui.item.name).attr('materialid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('materialid');
	    }).change(function(){
    		if(!$(this).attr('materialid')){
    			$(this).val('');
    		}
	    });
	}
	//DOM绑定事件方法
	function initBindEvent(){
		$('#refresh').off('click').on('click', function(){
			layer.closeAll();
			getPrimarySettingData(1);
		});
		$('#add').off('click').on('click', function(){
			$.get(URL.addView, null, function(result){
				if(result.code == '000000'){
					$('#a_code').val(result.data.code);
					$('#a_supplier').val('').removeClass('supplierid');
					$('#a_material').val('').removeClass('materialid');
					$('#a_creator').val(result.data.creator);
					$('#a_createtime').val(result.data.createtime);
					$('#a_isvalid')[0].checked = true;
					$('#a_remark').val('');
				}else{
					layer.msg(result.error, {icon: 5});
				}
			});
			$('#addView').modal('show');
		});
		$('#addCommit').off('click').on('click', function(){
			this.disabled = true;
			addPrimarySetting(this);
		});
		$('#updateCommit').off('click').on('click', function(){
			this.disabled = true;
			updatePrimarySetting(this);
		});
		$('#search').off('click').on('click', function(){
			layer.closeAll();
			getPrimarySettingData(1);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				getPrimarySettingData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			getPrimarySettingData(1);
		});
	}
	

	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//GET采购原型设置搜索条件
	function getSearchParams(){
		var code = $('#code').val(); code = $.trim(code);
		var supplierid = $('#supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var materialid = $('#material').attr('materialid'); materialid = $.trim(materialid);
		var starttime = $('#starttime').val(); starttime = $.trim(starttime);
		var endtime = $('#endtime').val(); endtime = $.trim(endtime);
		var isvalid = $('#isvalid').val(); isvalid = $.trim(isvalid);
		var pageSize = $('#pageSize').val(); pageSize = $.trim(pageSize);
		return {
			code: code,
			supplierid: supplierid,
			materialid: materialid,
			starttime: str2Long(starttime),
			endtime: str2Long(endtime),
			isvalid: isvalid,
			pageSize: pageSize
			
		}
	}
	//AJAX获取采购原型设置搜索结果
	function getPrimarySettingData(pageNo){
		var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getSearchParams();
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
							layer.closeAll();
					    	getPrimarySettingData(pageNo+1);
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
	//渲染采购原发设置搜索结果
	function renderHtml(data){
		var list = data.list;
		$('#dataBody').empty();
		if(list && list.length > 0){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				$('<tr>').append('<td>'+(i + 1)+'</td>')
						 .append('<td>'+(obj.code || '')+'</td>')
						 .append('<td>'+(obj.suppliername || '')+'</td>')
						 .append('<td>'+(obj.materialname || '')+'</td>')
						 .append('<td><input type="checkbox" disabled="disabled" '+(obj.isvalid == '1' ? 'checked' : '')+'></td>')
						 .append('<td>'+(obj.remark || '')+'</td>')
						 .append('<td><span><a class="update"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>' 
								 +'<span><a class="delete"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span></td>')
						 .data(obj).appendTo('#dataBody');
			}
			//修改
			$('#dataBody>tr>td a.update').off('click').on('click', function(){
				var obj = $(this).closest('tr').data();
				initAddViewParams(obj.id);
			});
			//删除
			$('#dataBody>tr>td a.delete').off('click').on('click', function(){
				var obj = $(this).closest('tr').data();
				deletePrimarySetting(obj.id);
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	//GET新增原发设置参数
	function getAddParams(){
		var supplierid = $('#a_supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var materialid = $('#a_material').attr('materialid'); materialid = $.trim(materialid);
		var isvalid = 0;
		if($('#a_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remark = $('#a_remark').val(); remark = $.trim(remark);
		return {
			supplierid: supplierid,
			materialid: materialid,
			isvalid: isvalid,
			remark: remark
		}
	}
	//校验新增原发设置参数合法性
	function validateAdd(params){
		if(!params.supplierid){
			layer.msg('请先选择供应商！', {icon: 5}); return false;
		}
		if(!params.materialid){
			layer.msg('请先选择物料！', {icon: 5}); return false;
		}
		return params;
	}
	//新增采购原发设置
	function addPrimarySetting(_this){
		var params = getAddParams();
		if(validateAdd(params)){
			$.ajax({
				url:URL.add,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#addView').modal('hide');
						layer.msg(result.error, {icon: 1});
						$('#refresh').click();
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
	//初始化修改参数
	function initAddViewParams(id){
		$.get(URL.updateView, {id: id}, function(result){
			if(result.code == '000000'){
				var data = result.data || {};
				$('#primarySettingId').val(data.id || '');
				$('#u_code').val(data.code || '');
				$('#u_supplier').val(data.suppliername || '').attr('supplierid', data.supplierid || '');
				$('#u_material').val(data.materialname || '').attr('materialid', data.materialid || '');
				$('#u_creator').val(data.createname || '');
				$('#u_createtime').val(data.createtimeStr || '');
				$('#u_isvalid').attr('checked',data.isvalid == 1 ? true : false);
				$('#u_remark').val(data.remark || '');
				$('#updateView').modal('show');
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	//GET修改原发设置参数
	function getUpdateParams(){
		var id = $('#primarySettingId').val();
		var supplierid = $('#u_supplier').attr('supplierid'); supplierid = $.trim(supplierid);
		var materialid = $('#u_material').attr('materialid'); materialid = $.trim(materialid);
		var isvalid = 0;
		if($('#u_isvalid').is(':checked')){
			isvalid = 1;
		}
		var remark = $('#u_remark').val(); remark = $.trim(remark);
		return {
			id: id,
			supplierid: supplierid,
			materialid: materialid,
			isvalid: isvalid,
			remark: remark
		}
	}
	//校验修改原发设置参数合法性
	function validateUpdate(params){
		if(!params.id){
			layer.msg('请先选中一行！', {icon: 5}); return false;
		}
		if(!params.supplierid){
			layer.msg('请先选择供应商！', {icon: 5}); return false;
		}
		if(!params.materialid){
			layer.msg('请先选择物料！', {icon: 5}); return false;
		}
		return params;
	}
	//修改采购原发设置
	function updatePrimarySetting(_this){
		var params = getUpdateParams();
		if(validateUpdate(params)){
			$.ajax({
				url:URL.update,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#updateView').modal('hide');
						layer.msg(result.error, {icon: 1});
						$('#refresh').click();
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
	//删除采购原发设置
	function deletePrimarySetting(id){
		layer.confirm('注：删除操作不可恢复，您确定要继续么？', {
			btn: ['确定', '取消'],
			area: '600px'
		}, function(){
			$.ajax({
				url:URL.deletePs,
				data:{
					id: id
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
	}
})(jQuery);