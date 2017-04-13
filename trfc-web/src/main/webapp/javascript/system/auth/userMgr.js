$(function(){
	//访问的url
	var URL={
		saveUrl:"/trfc/system/auth/user/addUser",
		pageUrl:"/trfc/system/auth/user/page",
	}
	//列表数据加载
	initData(1);
	//查询操作
	$(".intel_search").on("click",".searchBtn",initData);
	//分页操作
	
	//新增绑定
	$(".right").on("click",".addButton",openAddWindow);
	//新增弹出框 确认操作
	$("#addModal").on("click",".submitBtn",addsubmit);
	//修改按钮绑定
	//修改弹出框 确认操作
	//删除按钮绑定
	//删除操作
	//刷新按钮
	$(".right").on("click",".refreshButton",refreshLocation);
	
	
	/**
	 * 分页方法
	 */
	function initData(pageNo,pageSize){
		$(".intel_table").find("tbody").empty();
		var key=$(".intel_search .keySelect").val();
		var param ={
				"pageNo" : (pageNo||1),
				"pageSize":(pageSize||10)
		};
		param[key]=$(".intel_search .keyInput").val();
    	$.ajax({
			url : URL.pageUrl,
			data : param,
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					renderDate(rs.data.list);
				}else{
					layer.msg(rs.error,{icon:5});
				}
			}
		})
	}
	
	
	
	/**
	 * 渲染数据
	 */
	function renderDate(list){
		var dataArr=[]; 
		$(list).each(function(i,item){
			dataArr.push(' <tr>');
			dataArr.push('<td>'+(i+1)+'</td>');
			dataArr.push('<td>'+item.code+'</td>');
			dataArr.push('<td>'+item.account+'</td>');
			dataArr.push('<td>'+item.name+'</td>');
			dataArr.push('<td>'+(item.orgName||"汝州天瑞水泥")+'</td>');
			if( item.isvalid ==1){
				dataArr.push('<td>有效</td>');
			}else{
				dataArr.push('<td>无效</td>');
			}
			
			//来源
			dataArr.push('<td>'+item.source+'</td>');
			//登录次数
			dataArr.push('<td>'+(item.logincount||0)+'</td>');
			dataArr.push('<td>'+(item.lastLogintimeStr||"")+'</td>');
			dataArr.push('<td>'+(item.remark||"")+'</td>');
			//操作
			dataArr.push('<td>');
			//修改操作
			//dataArr.push('<span><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>');
			//dataArr.push('<span><a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span>');
			dataArr.push('</td>');
			dataArr.push('</tr>');
			
		})
		$(".intel_table").find("tbody").append(dataArr.join(" "));
	}
	/**
	 * 打开新增弹出框
	 */
	function openAddWindow(){
		$("#addModal").modal();
	}
	function addsubmit(){
		$.ajax({
			url:URL.saveUrl,
			data:$("#addModal .formele").serialize(),
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					$("#addModal").modal("hide");
				}else{
					layer.msg(rs.error,{icon:5});
				}
			}
		});
	}
	//监听新增模态框关闭事件
	$('#addModal').on('hidden.bs.modal', function (e) {
		$("#addModal .formele").val("");
	});
	/**
	 * 页面重新刷新
	 */
	function refreshLocation(){
		window.location.reload(true);
	}
	
});