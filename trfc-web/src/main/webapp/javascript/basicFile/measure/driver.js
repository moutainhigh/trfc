;(function($, win){
	//请求路径
	var URL = {
			addDriverUrl:"/trfc/driver/addDriver",
			addBtnUrl:"/trfc/common/code/driverCode",
			pageUrl:"/trfc/driver/page",
			updateDriverUrl:"/trfc/driver/updateDriver"
	};
	
	init();
	function init(){
		bindEvent();
		queryData(1);
	}

	console.log(URL);
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
				url:URL.addBtnUrl,
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
			if($('#addDriver').is(':visible')){
				var code = $('#add_code').val();code = $.trim(code);
				var internalcode = $('#add_internalcode').val();internalcode = $.trim(internalcode);
				var name = $('#add_name').val();name = $.trim(name);
				var abbrname = $('#add_abbrname').val();abbrname = $.trim(abbrname);
				var address = $('#add_address').val();address = $.trim(address);
				var telephone = $('#add_telephone').val();telephone = $.trim(telephone);
				var identityno = $('#add_identityno').val();identityno = $.trim(identityno);
				var isvalid = 0;
				if($('#add_isvalid')[0].checked){
					isvalid = 1;
				}
				var orgid = $('#add_orgname').attr('orgid');orgid = $.trim(orgid);
				var orgname = $('#add_orgname').val();orgname = $.trim(orgname);
				var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
				$.ajax({
					url:URL.addDriverUrl,
					data:{
						code:code,
						internalcode:internalcode,
						name:name,
						abbrname:abbrname,
						address:address,
						telephone:telephone,
						identityno:identityno,
						isvalid:isvalid,
						orgid:orgid,
						orgname:orgname,
						remarks:remarks
					},
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
				$('#del_id').val(obj.id);
				$('#delDriver').modal();
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	
	function updateData(){
		if($('#updateDriver').is(':visible')){
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
			$.ajax({
				url:URL.updateDriverUrl,
				data:{
					id:id,
					name:name,
					abbrname:abbrname,
					address:address,
					telephone:telephone,
					identityno:identityno,
					isvalid:isvalid,
					remarks:remarks
				},
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
})(jQuery, window);