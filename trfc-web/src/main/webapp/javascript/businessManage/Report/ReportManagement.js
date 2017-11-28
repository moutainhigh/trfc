(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/purchaseReport/commonPage",		//采购逐车
			materUrl:"/trfc/purchaseReport/materPage",			//物料统计
			customUrl:"/trfc/purchaseReport/customPage",		//单位统计
			receiveUrl:"/trfc/purchaseReport/receivePage",    //收料统计
//			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
		bindEvent();
		queryData();
	}
	$('#thing').off('click').on('click',function(){
		queryData4(1);
	});
	$('#receive').off('click').on('click',function(){
		queryData3(1);
	});
	$('#unit').off('click').on('click',function(){
		queryData2(1);
	});
	$('#buyCar').off('click').on('click',function(){
		queryData(1);
	});
	
	$('#searchBtn').off('click').on('click',function(){
		queryData(1);
	});
	$('#clean').off('click').on('click',function(){
		clean();
	});
	function bindEvent(){
		
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
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	function getParams(){
		var params = {};
		var bbg_gys = $('#bbg_gys').val();bbg_gys = $.trim(bbg_gys);
		var bbg_kk = $('#bbg_kk').val();bbg_kk = $.trim(bbg_kk);
		var gys = $('#gys').val();gys = $.trim(gys);
		var bbg_sjn = $('#bbg_sjn').val();bbg_sjn = $.trim(bbg_sjn);
		var clock1 = $('#clock1').val();clock1 = $.trim(clock1);
		var clock2 = $('#clock2').val();clock2 = $.trim(clock2);
		var bbg_cph = $('#bbg_cph').val();bbg_cph = $.trim(bbg_cph);
		var bbg_bz = $('#bbg_bz').val();bbg_bz = $.trim(bbg_bz);
		var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
		return {
			bbg_gys:bbg_gys,
			bbg_kk:bbg_kk,
			gys:gys,
			bbg_sjn:bbg_sjn,
			clock1:str2Long(clock1),
			clock2:str2Long(clock2),
			bbg_cph:bbg_cph,
			bbg_bz:bbg_bz,
			pageSize:pageSize
		};
	}
	
	function clean(){
		 $('#bbg_gys').val("");
		 $('#bbg_kk').val("");
		 $('#gys').val("");
		 $('#bbg_sjn').val("");
		 $('#clock1').val("");
		 $('#clock2').val("");
		 $('#bbg_cph').val("");
		 $('#bbg_bz').val("");
//		 queryData(1);
	}
//	$("#buyCar").click(function(){
//		//初始化页面
//		queryData(1);
		function queryData(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
//			逐车明细
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
//				$('#dataMore').hide();
			}
		}

////		单位统计
//		queryData2(1);
		function queryData2(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.customUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						renderHtml2(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(pageNo){
								queryData2(pageNo+1);
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
		
		function renderHtml2(data){
			$('#RMg2').empty();
			var list = data.list;
			if(list && list.length>0){
				for(var i=0;i<list.length;i++){
					$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
							.append('<td>'+(list[i].minemouthname||"")+'</td>')
							.append('<td>'+(list[i].cargo||"")+'</td>')
							.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
							.append('<td>'+(list[i].sumGrossweight||"")+'</td>')
							.append('<td>'+(list[i].sumTareweight||"")+'</td>')
							.append('<td>'+(list[i].sumNetweight||"")+'</td>')
							.appendTo('#RMg2');
				}
			}else{
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
	
////	收料统计
//	queryData3(1);
	function queryData3(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.receiveUrl,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml3(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
							queryData3(pageNo+1);
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
	
	function renderHtml3(data){
		$('#RMg3').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
						.append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].materialname||"")+'</td>')
						.append('<td>'+(list[i].vehicleno||"")+'</td>')
						.append('<td>'+(list[i].minemouthname||"")+'</td>')
						.append('<td>'+(list[i].yardname||"")+'</td>')
						.append('<td>'+(list[i].signpersonname||"")+'</td>')
						.append('<td>'+(list[i].signtime||"")+'</td>')
						.append('<td>'+("")+'</td>')
						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
						.append('<td>'+(list[i].netweight||"")+'</td>')
						.append('<td>'+(list[i].weighttime||"")+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg3');
			}
		}else{
			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}

	
//	物料统计
	queryData4(1);
	function queryData4(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.materUrl,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml4(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
							queryData4(pageNo+1);
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
	
	function renderHtml4(data){
		$('#RMg4').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].cargo||"")+'</td>')
						.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
						.append('<td>'+(list[i].sumOriginalnetweight||"")+'</td>')
						.append('<td>'+(list[i].sumGrossweight||"")+'</td>')
						.append('<td>'+(list[i].sumTareweight||"")+'</td>')
						.append('<td>'+(list[i].sumNetweight||"")+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg4');
			}
		}else{
			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}
})(jQuery, window);