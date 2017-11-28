(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/purchaseReport/commonPage",
			materUrl:"/trfc/purchaseReport/materPage",
			customUrl:"/trfc/purchaseReport/customPage",
			receiveUrl:"/trfc/purchaseReport/receivePage",
//			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
//		bindEvent();
		//初始化页面
		queryData(1);
	}
	function getParams(){
		var params = {};
		var requisitionNum = $('#requisitionNum').val();requisitionNum = $.trim(requisitionNum);
		var noticeNum = $('#noticeNum').val();noticeNum = $.trim(noticeNum);
		var pushStatus = $('#pushStatus').val();pushStatus = $.trim(pushStatus);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
		return {
			requisitionNum:requisitionNum,
			noticeNum:noticeNum,
			pushStatus:pushStatus,
			pageSize:pageSize
		};
	}
	
	function clean(){
		 $('#requisitionNum').val("");
		 $('#noticeNum').val("");
		 $('#pushStatus').val("");
		 $('#starttime').val("");
		 $('#endtime').val("");
//		 queryData(1);
	}
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
//		逐车明细
		$.ajax({
			url:URL.commonUrl,
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
					layer.msg(result.error);
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#RMg1').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
						.append('<td>'+(list[i].billcode||"")+'</td>')
						.append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].minemouthname||"")+'</td>')
						.append('<td>'+(list[i].yardname||"")+'</td>')
						.append('<td>'+(list[i].materialname||"")+'</td>')
						.append('<td>'+(list[i].vehicleid||"")+'</td>')
						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
						.append('<td>'+(list[i].grossweight||"")+'</td>')
						.append('<td>'+(list[i].tareweight||"")+'</td>')
						.append('<td>'+(list[i].netweight||"")+'</td>')
						.append('<td>'+(list[i].lighttime||"")+'</td>')
						.append('<td>'+(list[i].weighttime||"")+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg1');
			}
		}else{
			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}
	
})(jQuery, window);