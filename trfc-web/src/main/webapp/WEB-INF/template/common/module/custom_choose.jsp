<%@ page language="java"  pageEncoding="UTF-8"%>
<style>
	.tablecontent td,th {
		text-align: center;
		cursor: default;
	}
</style>
<div class="modal fade" id="customerView" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document"
		style="width: 1000px; height: 630px;">
		<div class="modal-content" style="height: inherit;">
			<div class="modal-header" style="height: 8%;">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="alt_head">
					<h5>客户选择</h5>
				</div>
			</div>
			<div class="modal-body" style="height: 92%;">
				<div class="dhadd_search">
					<div class="dhsearch_solo">
						<label>查询条件：</label> 
						<select id="qtp" class="form-control">
							<option value="mc">名称</option>
							<option value="nm">内码</option>
							<option value="py">拼音码</option>
						</select>
					</div>
					<div class="dhsearch_solo">
						<label>关键字：</label> <input id="keyword" type="text">
					</div>
					<div class="dhsearch_solo">
						<button id="searchBtn1" class="btn btnblue ">搜索</button>
					</div>
				</div>
				<div class="tablecontent">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>编号</th>
								<th>内码</th>
								<th>名称</th>
								<th>渠道类型</th>
								<th>渠道类型内码</th>
								<th>所属组织</th>
								<th>描述</th>
							</tr>
						</thead>
						<tbody id="customerBody">
							
						</tbody>
					</table>
				</div>

				<!--分页效果开始-->
				<div class="page">
					<div class="page_date">
						<label>数据共：</label><i id="total1" class="colorred">100</i><label>条</label>
					</div>
					<div class="page_date">
						<label>跳到第：</label> <input id="jumpPageNo1" type="text" value="1">
						<label>页</label>
						<button id="jumpPageNoBtn1" class="btn btn-default">确定</button>
					</div>
					<div class="page_date">
						<label>每页记录：</label> <select id="pageSize1" class="form-control">
							<option value="10">10</option>
							<option value="20">20</option>
							<option value="30">30</option>
						</select>
					</div>
					<div class="page_btn" id="pagination1"></div>
				</div>
				<!--分页效果结束-->
			</div>
		</div>
	</div>
</div>	

<script>
function initCustomer(callback){
	$('#customerView').modal();
	queryData(1);
	$('#qtp').val('mc')
	$('#keyword').val('');
	
	$('#jumpPageNoBtn1').off('click').on('click',function(){
		var pageNo = $('input#jumpPageNo1').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
		var pageMaxNo = $('input#jumpPageNo1').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
		if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
			alert('此处必须为1-'+pageMaxNo+'的数字');
			$('input#jumpPageNo1').val('');
		}else{
			$('input#jumpPageNo1').val(pageNo);
			queryData(pageNo);
		}
	});
	$('#pageSize1').change(function(){
		queryData(1);
	});
	
	$('#searchBtn1').off('click').on('click', function(){
		queryData(1);
	});
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function queryData(pageNo){
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
		var pageSize = $('#pageSize1').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		params.pageNo = pageNo;
		$.ajax({
			url:'/trfc/customer/page',
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
					$('#total1').html(total);
					$('#jumpPageNo1').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination1").pagination(total, {
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
		$('#customerBody').empty();
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
				$('<tr title="双击选择">').append('<td><div>'+(i+1)+'</div></td>')
						.append('<td><div>'+code+'</div></td>')
						.append('<td><div>'+internalcode+'</div></td>')
						.append('<td><div>'+name+'</div></td>')
						.append('<td><div>'+channeltype+'</div></td>')
						.append('<td><div>'+channelcode+'</div></td>')
						.append('<td><div>'+orgname+'</div></td>')
						.append('<td><div>'+remarks+'</div></td>')
						.data(obj)
						.appendTo('#customerBody');
			}
		}else{
			layer.msg('暂无数据...');
		}
	}
	
	$('#customerBody').on("dblclick","tr",function(){
		callback($(this).data());
		$('#customerView').modal('hide');
	});
	
}
</script>					