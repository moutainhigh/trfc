(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/allotPound/allotPage",		//场内倒运
			commonListUrl:"/trfc/allotPound/allotList",		
			allotMaterUrl:"/trfc/allotPound/allotMaterPage",//调入堆场
			allotMaterListUrl:"/trfc/allotPound/allotMaterList",
			allotMaterDCUrl:"/trfc/allotPound/allotMaterDCPage",//调出堆场
			allotMaterDCListUrl:"/trfc/allotPound/allotMaterDCList",
			allotMaterVeUrl:"/trfc/allotPound/allotMaterVePage",//物料车号
			allotMaterVeListUrl:"/trfc/allotPound/allotMaterVeList"
	};
	init();
	function init(){
		bindEvent();
		queryData();
		$(".wuliao_tabcont").hide();
		$(".hide_buyCar").show();
	}
	$('#buyCar').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_buyCar").show();
		queryData(1);
		commonList();
	});
	$('#thing').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_thing").show();
		queryData4(1);
		commonList2();
	});
	$('#receive').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
		$(".wuliao_tabcont").hide();
		$(".hide_receive").show();
		queryData3(1);
		commonList3();
	});
	$('#unit').off('click').on('click',function(){
		$('input#jumpPageNo').val('');
	    $(".wuliao_tabcont").hide();
		$(".hide_unit").show();
		queryData2(1);
		commonList4();
	});
	
//	// 物料的四个tab切换菜单
	var wl_li = $('.wuliao_tab ul li');
	wl_li.click(function () {
	    $(this).addClass('select').siblings().removeClass('select');
	    var index = wl_li.index(this);
//	    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
	});
	
	//场内倒运
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
        	            	$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')						
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
        	                .appendTo('#RMgA');
        	            }       	
                }
            }
        });
	}
	//调入堆场
	function commonList2(){
		$.ajax({
            url:URL.allotMaterListUrl,
            async:true,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgB').empty();
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
        	            	$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')						
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
        	                .appendTo('#RMgB');
        	            }       	
                }
            }
        });
	}
	//调出堆场
	function commonList3(){
		$.ajax({
            url:URL.allotMaterDCListUrl,
            async:true,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMgC').empty();
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
        	            	$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')						
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
        	                .appendTo('#RMgC');
        	            }       	
                }
            }
        });
	}
	//物料车号
	function commonList4(){
		$.ajax({
            url:URL.allotMaterVeListUrl,
            async:true,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){
                if(result.code == '000000'){
                console.log(result.data)           	
                	$('#RMg').empty();
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
        	            	$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')						
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
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
		var customername = $('#bbg_gys').val();customername = $.trim(customername);
		var cargo = $('#gys').val();cargo = $.trim(cargo);
		var drivername = $('#bbg_sjn').val();drivername = $.trim(drivername);
		var beginTime = $('#clock1').val();beginTime = $.trim(beginTime);
		var endTime = $('#clock2').val();endTime = $.trim(endTime);
		var vehicleno = $('#bbg_cph').val();vehicleno = $.trim(vehicleno);
		var pageSize = $('#pageSize').val() || 20;pageSize = $.trim(pageSize);
		return {
			customername:customername,
			cargo:cargo,
			drivername:drivername,
			beginTime:str2Long(beginTime),
			endTime:str2Long(endTime),
			vehicleno:vehicleno,
			pageSize:pageSize
		};
	}
	
	function clean(){
		 $('#bbg_gys').val("");	
		 $('#gys').val("");
		 $('#bbg_sjn').val("");
		 $('#clock1').val("");
		 $('#clock2').val("");
		 $('#bbg_cph').val("");
//		 queryData(1);
	}
//		//初始化页面
//		queryData(1);
		//	场内倒运
		function queryData(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
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
					$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')		
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg1');
				}
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		调入堆场
		function queryData2(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterUrl,
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml2(data){
			$('#RMg2').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str=0,str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					str+=list[i].grossweight;
					str1+=list[i].tareweight;
					str2+=list[i].netweight;
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
					$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')		
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg2');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')		
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+(str)+'</td>')
				.append('<td>'+(str1)+'</td>')
				.append('<td>'+(str2)+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.appendTo('#RMg2');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		调出堆场
		function queryData3(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterDCUrl,
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml3(data){
			$('#RMg3').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str=0,str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					str+=list[i].grossweight;
					str1+=list[i].tareweight;
					str2+=list[i].netweight;
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
					$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')		
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg3');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')		
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+(str)+'</td>')
				.append('<td>'+(str1)+'</td>')
				.append('<td>'+(str2)+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.appendTo('#RMg3');
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
//		物料车号
		function queryData4(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var params = getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:URL.allotMaterVeUrl,
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
		//过磅单号   通知单号   发货单位   收货单位     仓库  物料    车号   毛重    皮重    净重      轻车时间   重车时间  
		function renderHtml4(data){
			$('#RMg4').empty();
			var list = data.list||[];
			if(list && list.length>0){
				var str=0,str1=0,str2=0;
				for(var i=0;i<list.length;i++){
					str+=list[i].grossweight;
					str1+=list[i].tareweight;
					str2+=list[i].netweight;
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
					$('<tr>').append('<td>'+(list[i].code||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].enteryardname||"")+'</td>')
							.append('<td>'+(list[i].leaveyardname||"")+'</td>')		
							.append('<td>'+(list[i].materialname||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg4');
				}
				$('<tr>').append('<td>总计</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')		
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+(str)+'</td>')
				.append('<td>'+(str1)+'</td>')
				.append('<td>'+(str2)+'</td>')
				.append('<td>'+("")+'</td>')
				.append('<td>'+("")+'</td>')
				.appendTo('#RMg4');
				
			}else if(list.length<=0){
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
})(jQuery, window);