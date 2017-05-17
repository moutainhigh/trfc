;(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/supplier/page",
			updateSupplierUrl:"/trfc/supplier/updateSupplier",
			updateFromDc:"/trfc/supplier/updateFromDc"
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
		$('#updateFromDc').off('click').on('click',function(){
			updateFromDc();
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
		if(qtp == 'py'){
			params.pinyincode = keyword;
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
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var internalcode = obj.internalcode || '';
				var name = obj.name || '';
				var orgname = obj.orgname || '';
				var minemouth = obj.minemouth || '';
				var drivercheck = obj.drivercheck || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td><input type="checkbox" '+(minemouth == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td><input type="checkbox" '+(drivercheck == '0' ? '' : 'checked')+' disabled></td>')
						.append('<td>'+remarks+'</td>')
						.append('<td><span><a class="updateViewBtn"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');//updateViewBtn
			}
			$('#dataBody .updateViewBtn').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				$('#supplierid').val(obj.id);
				$('#code').val(obj.code);
				$('#internalcode').val(obj.internalcode);
				$('#name').val(obj.name);
				$('#abbrname').val(obj.abbrname);
				$('#pinyincode').val(obj.pinyincode);
				if(obj.minemouth == '1'){
					$('#minemouth')[0].checked = true;
				}else{
					$('#minemouth')[0].checked = false;
				}
				$('#queuingprefix').val(obj.queuingprefix);
				if(obj.drivercheck == '1'){
					$('#drivercheck')[0].checked = true;
				}else{
					$('#drivercheck')[0].checked = false;
				}
				$('#remarks').val(obj.remarks);
				$('#editData').modal();
			});
		}else{
			layer.msg('暂无数据');
		}
	}
	
	function editData(){
		if($('#editData').is(':visible')){
			var id = $('#supplierid').val();
			var name = $('#name').val();
			var abbrname = $('#abbrname').val();
			var pinyincode = $('#pinyincode').val();
			var minemouth = '0';
			if($('#minemouth')[0].checked){
				minemouth = '1';
			}
			var drivercheck = '0';
			if($('#drivercheck')[0].checked){
				drivercheck = '1';
			}
			var remarks = $('#remarks').val();
			$.ajax({
				url:URL.updateSupplierUrl,
				data:{
					id:id,
					name:name,
					abbrname:abbrname,
					pinyincode:pinyincode,
					minemouth:minemouth,
					drivercheck:drivercheck,
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
						layer.msg(result.error,{icon:5});
					}
				}
			});
		}
	}
	function updateFromDc(){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		$.get(URL.updateFromDc,{},function(result){
			if(result && result.code == '000000'){
				setTimeout(function(){
					queryData(1);
				}, 3000);
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
})(jQuery, window);