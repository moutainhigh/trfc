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
			BuZengUrl:"/trfc/purchaseReport/buZengPage",     //补增
			BuZengListUrl:"/trfc/purchaseReport/buZengList",     
	};
	init();
	function init(){
		bindEvent();
		materList();
		$(".wuliao_tabcont").hide();
		$(".hide_thing").show();
		searchData(1);
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
	$('#buyAdd').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_buyAdd").show();
		queryData(1);
		BuZengList();
	});	
//	// 物料的四个tab切换菜单
	var wl_li = $('.wuliao_tab ul li');
	wl_li.click(function () {
	    $(this).addClass('select').siblings().removeClass('select');
	    var index = wl_li.index(this);
//	    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
	});
	
//	采购补增
	function BuZengList(){
		 $.ajax({
	            url:URL.BuZengListUrl,
	            async:true,
	            cache:false,
	            dataType:'json',
	            type:'post',
	            success:function(result){
	                if(result.code == '000000'){      	
	                	$('#RMgA').empty();
	        	        var list = result.data||[];
	        	            for(var i=0;i<list.length;i++){
	        	            	var lightt,weightt;
	        					if(list[i].lighttime){
	        						lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						lightt="";
	        					}
	        					if(list[i].weighttime){
	        						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						weightt="";
	        					}
	        	            	$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
	        					.append('<td>'+(list[i].suppliername||"")+'</td>')
	        					.append('<td>'+(list[i].minemouthname||"")+'</td>')
	        					.append('<td>'+(list[i].yardname||"")+'</td>')
	        					.append('<td>'+(list[i].materialname||"")+'</td>')
	        					.append('<td>'+(list[i].vehicleno||"")+'</td>')
	        					.append('<td>'+(list[i].originalnetweight||"")+'</td>')
	        					.append('<td>'+(list[i].grossweight||"")+'</td>')
	        					.append('<td>'+(list[i].tareweight||"")+'</td>')
	        					.append('<td>'+(list[i].netweight||"")+'</td>')
	        					.append('<td>'+(lightt)+'</td>')
	        					.append('<td>'+(weightt)+'</td>')
	        					.append('<td>'+(list[i].remark||"")+'</td>')
	        	                .appendTo('#RMgE');
	        	            }       	
	                }
	            }
	        });
	}
	
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
	                	$('#RMgA').empty();
	        	        var list = result.data||[];
	        	            for(var i=0;i<list.length;i++){
	        	            	var lightt,weightt;
	        					if(list[i].lighttime){
	        						lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						lightt="";
	        					}
	        					if(list[i].weighttime){
	        						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						weightt="";
	        					}
	        	            	$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
	        					.append('<td>'+(list[i].billcode||"")+'</td>')
	        					.append('<td>'+(list[i].suppliername||"")+'</td>')
	        					.append('<td>'+(list[i].minemouthname||"")+'</td>')
	        					.append('<td>'+(list[i].yardname||"")+'</td>')
	        					.append('<td>'+(list[i].materialname||"")+'</td>')
	        					.append('<td>'+(list[i].vehicleno||"")+'</td>')
	        					.append('<td>'+(list[i].originalnetweight||"")+'</td>')
	        					.append('<td>'+(list[i].grossweight||"")+'</td>')
	        					.append('<td>'+(list[i].tareweight||"")+'</td>')
	        					.append('<td>'+(list[i].netweight||"")+'</td>')
	        					.append('<td>'+(lightt)+'</td>')
	        					.append('<td>'+(weightt)+'</td>')
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
	                	$('#RMgB').empty();
	        	        var list = result.data||[];
	        	            for(var i=0;i<list.length;i++){
	        	            	$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
								.append('<td>'+(list[i].minemouthname||"")+'</td>')
								.append('<td>'+(list[i].materialname||"")+'</td>')
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
	                	$('#RMgC').empty();
	        	        var list = result.data||[];
	        	            for(var i=0;i<list.length;i++){
	        	            	var signt,weightt;
	        					if(list[i].signtime){
	        						signt=new Date(list[i].signtime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						signt="";
	        					}
	        					if(list[i].weighttime){
	        						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
	        					}else{
	        						weightt="";
	        					}
	        	            	$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
	    						.append('<td>'+(list[i].suppliername||"")+'</td>')
	    						.append('<td>'+(list[i].materialname||"")+'</td>')
	    						.append('<td>'+(list[i].vehicleno||"")+'</td>')
	    						.append('<td>'+(list[i].minemouthname||"")+'</td>')
	    						.append('<td>'+(list[i].yardname||"")+'</td>')
	    						.append('<td>'+(list[i].signpersonname||"")+'</td>')
	    						.append('<td>'+(signt)+'</td>')
	    						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
	    						.append('<td>'+(list[i].netweight||"")+'</td>')
	    						.append('<td>'+(weightt)+'</td>')
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
	                	$('#RMgC').empty();
	        	        var list = result.data||[];
	        	            for(var i=0;i<list.length;i++){
	        	            	$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
	    						.append('<td>'+(list[i].materialname||"")+'</td>')
	    						.append('<td>'+(list[i].countVehicleNo||"")+'</td>')
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
	
	function searchData(page) {
		var type=$('.tj_tab ul>li.select').attr('data-type');
		console.log(type)
		if(type==0){
			queryData5(page);
			console.log(queryData)
		}
		if(type==1){
			queryData(page);
			console.log(queryData2)
		}
		if(type==2){
			queryData2(page);
			console.log(queryData3)
		}
		if(type==3){
			queryData3(page);
			console.log(queryData4)
		}
		if(type==4){
			queryData4(page);
			console.log(queryData5)
		}
	}
	$('#searchBtn').off('click').on('click',function(){
		searchData(1);
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
				searchData(pageNo);
			
			}
		});
		$('#pageSize').change(function(){
			searchData(1);
	
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
		var yardname=$('#duichang').val();yardname = $.trim(yardname);
		var returnstatus = $('#pushStatus').val();returnstatus = $.trim(returnstatus);
		var pageSize = $('#pageSize').val() || 20;pageSize = $.trim(pageSize);
		return {
			suppliername:suppliername,
			minemouthname:minemouthname,
			cargo:cargo,
			returnstatus:returnstatus,
			drivername:drivername,
			beginTime:str2Long(beginTime),
			endTime:str2Long(endTime),
			vehicleno:vehicleno,
			remark:remark,
			yardname:yardname,
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
		 $('#duichang').val("");
		 $('#pushStatus').val("");
	}
//		//初始化页面
	function queryData5(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
//		补增明细
		$.ajax({
			url:URL.BuZengUrl,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderHtml5(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
							queryData5(pageNo+1);
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
	
	function renderHtml5(data){
		$('#RMg1').empty();
		var list = data.list||[];
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var lightt,weightt;
				if(list[i].lighttime){
					lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
				}else{
					lightt="";
				}
				if(list[i].weighttime){
					weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
				}else{
					weightt="";
				}
				$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')	
						.append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].minemouthname||"")+'</td>')
						.append('<td>'+(list[i].yardname||"")+'</td>')
						.append('<td>'+(list[i].materialname||"")+'</td>')
						.append('<td>'+(list[i].vehicleno||"")+'</td>')
						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
						.append('<td>'+(list[i].grossweight||"")+'</td>')
						.append('<td>'+(list[i].tareweight||"")+'</td>')
						.append('<td>'+(list[i].netweight||"")+'</td>')
						.append('<td>'+(lightt)+'</td>')
						.append('<td>'+(weightt)+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg5');
			}
		}else{
//			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}
//	逐车明细
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
			var list = data.list||[];
			if(list && list.length>0){
				for(var i=0;i<list.length;i++){
					var lightt,weightt;
					if(list[i].lighttime){
						lightt=new Date(list[i].lighttime).format("yyyy-MM-dd HH:mm:ss");
					}else{
						lightt="";
					}
					if(list[i].weighttime){
						weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
					}else{
						weightt="";
					}
					$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
							.append('<td>'+(list[i].billcode||"")+'</td>')
							.append('<td>'+(list[i].suppliername||"")+'</td>')
							.append('<td>'+(list[i].minemouthname||"")+'</td>')
							.append('<td>'+(list[i].yardname||"")+'</td>')
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].originalnetweight||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.append('<td>'+(list[i].remark||"")+'</td>')
							.appendTo('#RMg1');
				}
			}else{
//				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}

////		单位统计
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
			var list = data.list||[];
			if(list && list.length>0){
				for(var i=0;i<list.length;i++){
					$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
							.append('<td>'+(list[i].minemouthname||"")+'</td>')
							.append('<td>'+(list[i].materialname||"")+'</td>')
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
		var list = data.list||[];
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var signt,weightt;
				if(list[i].signtime){
					signt=new Date(list[i].signtime).format("yyyy-MM-dd HH:mm:ss");
				}else{
					signt="";
				}
				if(list[i].weighttime){
					weightt=new Date(list[i].weighttime).format("yyyy-MM-dd HH:mm:ss");
				}else{
					weightt="";
				}
				$('<tr>').append('<td>'+(list[i].poundcode||"")+'</td>')
						.append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].materialname||"")+'</td>')
						.append('<td>'+(list[i].vehicleno||"")+'</td>')
						.append('<td>'+(list[i].minemouthname||"")+'</td>')
						.append('<td>'+(list[i].yardname||"")+'</td>')
						.append('<td>'+(list[i].signpersonname||"")+'</td>')
						.append('<td>'+(signt)+'</td>')
						.append('<td>'+(list[i].originalnetweight||"")+'</td>')
						.append('<td>'+(list[i].netweight||"")+'</td>')
						.append('<td>'+(weightt)+'</td>')
						.append('<td>'+(list[i].remark||"")+'</td>')
						.appendTo('#RMg3');
			}
		}else{
//			layer.msg('暂无数据');
//			$('#dataMore').hide();
		}
	}

	
//	物料统计
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
		var list = data.list||[];
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				$('<tr>').append('<td>'+(list[i].suppliername||"")+'</td>')
						.append('<td>'+(list[i].materialname||"")+'</td>')
						.append('<td>'+(list[i].countVehicleNo||"")+'</td>')	
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