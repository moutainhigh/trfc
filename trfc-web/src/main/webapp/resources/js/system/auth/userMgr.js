$(function(){
	//访问的url
	var URL={
		saveUrl:"/system/auth/user/addUser"
	}
	//列表数据加载
	initData(1);
	//查询操作
	$(".intel_search").on("click",".searchBtn",initData(1));
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
		if(pageNo ==1){$(".intel_table").find("tbody").empty();}
		var key=$(".intel_search .keySelect").val();
    	$.ajax({
			url : URL.pageUrl,
			data : {
				key : $(".intel_search .keyInput").val(),
				"pageNo" : pageNo,
				"pageSize":(pageSize||10)
			},
			type : "post",
			dataType:"json",
			success:function(rs){
				cosole.info(rs);
//				if( rs && rs.code =="000000" ){
//					renderDate(rs.data.list || [],pageNo);
//				}else{
//					alert(rs.error);
//				}
			}
		})
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
					alert(rs.error);
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
		window.location.reload();
	}
	
});