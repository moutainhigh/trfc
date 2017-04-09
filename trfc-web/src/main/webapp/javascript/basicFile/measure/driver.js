;(function($, win){
	//请求路径
	var URL = {
			addUrl:"/trfc/driver/add",
			addViewUrl:"/trfc/driver/addView",
			pageUrl:"/trfc/driver/page",
			updateUrl:"/trfc/driver/update",
			deleteUrl:"/trfc/driver/delete"
	};
	
	init();
	function init(){
		bindEvent();
		queryData(1);
	}

	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#updateDriverBtn').off('click').on('click',function(){
			updateData();
		});
		$('#addBtn').off('click').on('click',function(){
			$.ajax({
				url:URL.addViewUrl,
				data:{},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						$('#add_code').val(result.data.code);
						$('#add_internalcode').val(result.data.internalcode);
						$('#addDriver').modal();
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
		$('#addDriverBtn').off('click').on('click',function(){
			var _addBtn = this;
			if($('#addDriver').is(':visible')){
				_addBtn.disabled = true;
				addDriver(_addBtn);
			}
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
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
	function getParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		if(qtp == 'mc'){
			params.name = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		var orgid = $('#orgid').val();orgid = $.trim(orgid);
		params.orgid = orgid;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		return params;
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
			url : URL.pageUrl,
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
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var internalcode = obj.internalcode || '';
				var name = obj.name || '';
				var identityno = obj.identityno || '';
				var orgname = obj.orgname || '';
				var telephone = obj.telephone || '';
				var address = obj.address || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+identityno+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+telephone+'</td>')
						.append('<td>'+address+'</td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span><a class="updateView"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
									+'<span><a class="delView"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody .updateView').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#driverid').val(obj.id);
				$('#update_code').val(obj.code);
				$('#update_internalcode').val(obj.internalcode);
				$('#update_name').val(obj.name);
				$('#update_abbrname').val(obj.abbrname);
				$('#update_address').val(obj.address);
				$('#update_telephone').val(obj.telephone);
				$('#update_identityno').val(obj.identityno);
				if(obj.isvalid == 1){
					$('#update_isvalid')[0].checked = true;
				}else{
					$('#update_isvalid')[0].checked = false;
				}
				$('#update_orgname').val(obj.orgname);
				$('#update_remarks').val(obj.remarks);
				$('#updateDriver').modal();
			});
			$('#dataBody .delView').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				layer.confirm('注：删除操作不可恢复，您确定要继续吗？', {
					btn: ['确认','取消'] //按钮
				}, function(){
					$.ajax({
						url:URL.deleteUrl,
						data:{id: obj.id},
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
				});
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	/**
	 * 获取修改司机参数
	 */
	function getUpdateParams(){
		var id = $('#driverid').val();id = $.trim(id);
		var name = $('#update_name').val();name = $.trim(name);
		var abbrname = $('#update_abbrname').val();abbrname = $.trim(abbrname);
		var address = $('#update_address').val();address = $.trim(address);
		var telephone = $('#update_telephone').val();telephone = $.trim(telephone);
		var identityno = $('#update_identityno').val();identityno = $.trim(identityno);
		var isvalid = 0;
		if($('#update_isvalid')[0].checked){
			isvalid = 1;
		}
		var remarks = $('#update_remarks').val();remarks = $.trim(remarks);
		return {
			id:id,
			name:name,
			abbrname:abbrname,
			address:address,
			telephone:telephone,
			identityno:identityno,
			isvalid:isvalid,
			remarks:remarks
		};
	}
	/**
	 * 修改司机
	 */
	function updateData(){
		if($('#updateDriver').is(':visible')){
			var params = getUpdateParams();
			if(validate(params)){
				$.ajax({
					url:URL.updateUrl,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload();
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		}
	}
	/**
	 * 获取司机新增参数
	 */
	function getAddParams(){
		var name = $('#add_name').val();name = $.trim(name);
		var abbrname = $('#add_abbrname').val();abbrname = $.trim(abbrname);
		var address = $('#add_address').val();address = $.trim(address);
		var telephone = $('#add_telephone').val();telephone = $.trim(telephone);
		var identityno = $('#add_identityno').val();identityno = $.trim(identityno);
		var isvalid = 0;
		if($('#add_isvalid')[0].checked){
			isvalid = 1;
		}
		var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
		return {
			name:name,
			abbrname:abbrname,
			address:address,
			telephone:telephone,
			identityno:identityno,
			isvalid:isvalid,
			remarks:remarks
		}
	}
	/**
	 * 校验参数是否合法
	 */
	function validate(params){
		if(!params.name){
			layer.msg('司机名称不能为空!', {icon: 5});return;
		}
		if(!params.telephone){
			layer.msg('司机电话不能为空!', {icon: 5});return;
		}
		if(!params.identityno){
			layer.msg('身份证号不能为空!', {icon: 5});return;
		}
		return params;
	}
	/**
	 * 新增司机
	 */
	function addDriver(_addBtn){
		var params = getAddParams();
		if(validate(params)){
			$.ajax({
				url:URL.addUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						win.location.reload();
					}else{
						layer.msg(result.error, {icon: 5});
					}
					_addBtn.disabled = false;
				}
			});
		}else{
			_addBtn.disabled = false;
		}
	}
})(jQuery, window);