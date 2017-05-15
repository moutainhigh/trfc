;(function($, win){
	
	//请求路径
	var URL = {
			pageUrl:"/trfc/warehouse/page",
			updateFromDc:"/trfc/warehouse/updateFromDc"
	};
	init();
	function init(){
		bindEvent();
		queryData(1);
	}
	function bindEvent(){
		$('#refreshMater').off('click').on('click',function(){
			queryData(1);
		});
		$('#updateFromDc').off('click').on('click',function(){
			updateFromDc();
		});
		$('#searchMater').off('click').on('click',function(){
			queryData(1);
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
	function getParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		var orgid = $('#orgid').val();orgid = $.trim(orgid);
		if(qtp == 'mc'){
			params.name = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
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
					layer.msg(result.error,{icon:5});
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#materBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var internalcode = obj.internalcode || '';
				var name = obj.name || '';
				var orgname = obj.orgname || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+remarks+'</td>')
						.data(obj)
						.appendTo('#materBody');
			}
		}else{
			layer.msg('暂无数据');
		}
	}
	function updateFromDc(){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		$.get(URL.updateFromDc,{},function(result){
			if(result && result.code == '000000'){
				queryData(1);
			}else{
				layer.msg(result.error, {icon: 5});
			}
			layer.close(index);
		});
	}
})(jQuery, window);