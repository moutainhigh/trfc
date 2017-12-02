(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/purchaseReport/commonPage",		//采购逐车
			materUrl:"/trfc/purchaseReport/materPage",			//物料统计
			customUrl:"/trfc/purchaseReport/customPage",		//单位统计
			receiveUrl:"/trfc/purchaseReport/receivePage",    //收料统计
			commonListUrl:"/trfc/purchaseReport/commonList",
			customListUrl:"/trfc/purchaseReport/customList",
			receiveListUrl:"/trfc/purchaseReport/receiveList",    //收料统计
			materListUrl:"/trfc/purchaseReport/materList",			//物料统计
//			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
		bindEvent();
		queryData();
		$(".wuliao_tabcont").hide();
		$(".hide_thing").show();
	}
	$('#thing').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_thing").show();
		queryData4(1);
		materList();
	});
	$('#receive').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_receive").show();
		queryData3(1);
		receiveList();
	});
	$('#unit').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
	    $(".wuliao_tabcont").hide();
		$(".hide_unit").show();
		queryData2(1);
		customList();
	});
	$('#buyCar').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_buyCar").show();
		queryData(1);
		commonList();
	});	

//	// 物料的四个tab切换菜单
	var wl_li = $('.wuliao_tab ul li');
	wl_li.click(function () {
	    $(this).addClass('select').siblings().removeClass('select');
	    var index = wl_li.index(this);
//	    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
	});
//	采购逐车
	function commonList(){
		 $.ajax({
	            url:URL.commonListUrl,
	            async:true,
	            cache:false,
	            dataType:'json',
	            type:'post',
	            success:function(result){
	                if(result.code == '000000'){
	                console.log(result.data)           	
	                	$('#RMgA').empty();
	        	        var list = result.data;
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
	        	                .appendTo('#RMgA');
	        	            }       	
	                }
	            }
	        });
	}
//	单位统计
	function customList(){
		 $.ajax({
	            url:URL.customListUrl,
	            async:true,
	            cache:false,
	            dataType:'json',
	            type:'post',
	            success:function(result){
	                if(result.code == '000000'){
	                	console.log(2)
	                console.log(result.data)           	
	                	$('#RMgB').empty();
	        	        var list = result.data;
	        	            for(var i=0;i<list.length;i++){
	        	            	$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
								.append('<td>'+(list[i].minemouthname||"")+'</td>')
								.append('<td>'+(list[i].cargo||"")+'</td>')
								.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
								.append('<td>'+(list[i].sumGrossweight||"")+'</td>')
								.append('<td>'+(list[i].sumTareweight||"")+'</td>')
								.append('<td>'+(list[i].sumNetweight||"")+'</td>')
	        	                .appendTo('#RMgB');
	        	            }       	
	                }
	            }
	        });
	}
//	收料统计
	function receiveList(){
		 $.ajax({
	            url:URL.receiveListUrl,
	            async:true,
	            cache:false,
	            dataType:'json',
	            type:'post',
	            success:function(result){
	                if(result.code == '000000'){
	                	console.log(2)
	                console.log(result.data)           	
	                	$('#RMgC').empty();
	        	        var list = result.data;
	        	            for(var i=0;i<list.length;i++){
	        	            	$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
	    						.append('<td>'+(list[i].suppliername||"")+'</td>')
	    						.append('<td>'+(list[i].materialname||"")+'</td>')
	    						.append('<td>'+(list[i].vehicleno||"")+'</td>')
	    						.append('<td>'+(list[i].minemouthname||"")+'</td>')
	    						.append('<td>'+(list[i].yardname||"")+'</td>')
	    						.append('<td>'+(list[i].signpersonname||"")+'</td>')
	    						.append('<td>'+(new Date(list[i].signtime).format("yyyy-MM-dd HH:mm:ss")||"")+'</td>')
	    						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
	    						.append('<td>'+(list[i].netweight||"")+'</td>')
	    						.append('<td>'+(new Date(list[i].weighttime||"").format("yyyy-MM-dd HH:mm:ss"))+'</td>')
	    						.append('<td>'+(list[i].remark||"")+'</td>')
	        	                .appendTo('#RMgC');
	        	            }       	
	                }
	            }
	        });
	}
//	物料统计
	function materList(){
		 $.ajax({
	            url:URL.materListUrl,
	            async:true,
	            cache:false,
	            dataType:'json',
	            type:'post',
	            success:function(result){
	                if(result.code == '000000'){
	                	console.log(2)
	                console.log(result.data)           	
	                	$('#RMgC').empty();
	        	        var list = result.data;
	        	            for(var i=0;i<list.length;i++){
	        	            	$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
	    						.append('<td>'+(list[i].cargo||"")+'</td>')
	    						.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
	    						.append('<td>'+(list[i].sumOriginalnetweight||"")+'</td>')
	    						.append('<td>'+(list[i].sumGrossweight||"")+'</td>')
	    						.append('<td>'+(list[i].sumTareweight||"")+'</td>')
	    						.append('<td>'+(list[i].sumNetweight||"")+'</td>')
	    						.append('<td>'+(list[i].remark||"")+'</td>')
	        	                .appendTo('#RMgD');
	        	            }       	
	                }
	            }
	        });
	}
	$('#searchBtn').off('click').on('click',function(){
		if(queryData){
			queryData(1);
		}
		if(queryData2){
			queryData2(1);	
		}
		if(queryData3){
			queryData3(1);
		}
		if(queryData4){
			queryData4(1);
		}
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
				if(queryData){
					queryData(pageNo);
				}
				if(queryData2){
					queryData2(pageNo);	
				}
				if(queryData3){
					queryData3(pageNo);
				}
				if(queryData4){
					queryData4(pageNo);
				}
				
			}
		});
		$('#pageSize').change(function(){
			if(queryData){
				queryData(1);
			}
			if(queryData2){
				queryData2(1);
			}
			if(queryData3){
				queryData3(1);
			}
			if(queryData4){
				queryData4(1);
			}
//			queryData(1);
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
		var suppliername = $('#bbg_gys').val();suppliername = $.trim(suppliername);
		var minemouthname = $('#bbg_kk').val();minemouthname = $.trim(minemouthname);
		var cargo = $('#gys').val();cargo = $.trim(cargo);
		var drivername = $('#bbg_sjn').val();drivername = $.trim(drivername);
		var beginTime = $('#clock1').val();beginTime = $.trim(beginTime);
		var endTime = $('#clock2').val();endTime = $.trim(endTime);
		var vehicleno = $('#bbg_cph').val();vehicleno = $.trim(vehicleno);
		var remark = $('#bbg_bz').val();remark = $.trim(remark);
		var pageSize = $('#pageSize').val() || 20;pageSize = $.trim(pageSize);
		return {
			suppliername:suppliername,
			minemouthname:minemouthname,
			cargo:cargo,
			drivername:drivername,
			beginTime:str2Long(beginTime),
			endTime:str2Long(endTime),
			vehicleno:vehicleno,
			remark:remark,
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
//				layer.msg('暂无数据');
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
//				layer.msg('暂无数据');
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
						.append('<td>'+(new Date(list[i].signtime).format("yyyy-MM-dd HH:mm:ss")||"")+'</td>')
						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
						.append('<td>'+(list[i].netweight||"")+'</td>')
						.append('<td>'+(new Date(list[i].weighttime||"").format("yyyy-MM-dd HH:mm:ss"))+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg3');
			}
		}else{
//			layer.msg('暂无数据');
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
//			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}
})(jQuery, window);