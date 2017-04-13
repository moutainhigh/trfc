;(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/customer/page",
			updateCustomerUrl:"/trfc/customer/updateCustomer"
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
		$('#updateBtn').off('click').on('click',function(){
			editData();
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
		var qtp = $('#qtp').val() || '';qtp = $.trim(qtp);
		var keyword = $('#keyword').val() || '';keyword = $.trim(keyword);
		if(qtp == 'mc'){
			params.name = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		if(qtp == 'py'){
			params.pinyincode = keyword;
		}
		var orgid = $('#orgid').attr('orgid') || '';orgid = $.trim(orgid);
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
			url:URL.pageUrl,
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
				var channeltype = obj.channeltype || '';
				var channelcode = obj.channelcode || '';
				var orgname = obj.orgname || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+channeltype+'</td>')
						.append('<td>'+channelcode+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span><a class="updateViewBtn"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody .updateViewBtn').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#update_id').val(obj.id);
				$('#update_code').val(obj.code);
				$('#update_internalcode').val(obj.internalcode);
				$('#update_name').val(obj.name);
				$('#update_abbrname').val(obj.abbrname);
				$('#update_orgname').val(obj.orgname);
				$('#update_channelcode').val(obj.channelcode);
				$('#update_pinyincode').val(obj.pinyincode);
				$('#update_customertype').val(obj.customertype);
				$('#update_remarks').val(obj.remarks);
				$('#updateCustomer').modal();
			});
		}else{
			layer.msg('暂无数据...');
		}
	}
	
	function editData(){
		if($('#updateCustomer').is(':visible')){
			var id = $('#update_id').val() || '';id = $.trim(id);
			var name = $('#update_name').val() || '';name = $.trim(name);
			var abbrname = $('#update_abbrname').val() || '';abbrname = $.trim(abbrname);
			var channelcode = $('#update_channelcode').val() || '';channelcode = $.trim(channelcode);
			var pinyincode = $('#update_pinyincode').val() || '';pinyincode = $.trim(pinyincode);
			var customertype = $('#update_customertype').val() || '';customertype = $.trim(customertype);
			var remarks = $('#update_remarks').val() || '';
			$.ajax({
				url:URL.updateCustomerUrl,
				data:{
					id:id,
					name:name,
					abbrname:abbrname,
					channelcode:channelcode,
					pinyincode:pinyincode,
					customertype:customertype,
					remarks:remarks
				},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						win.location.reload(true);
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		}
	}
})(jQuery, window);