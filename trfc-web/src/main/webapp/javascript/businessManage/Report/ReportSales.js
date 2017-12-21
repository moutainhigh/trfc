(function($, win){
	//请求路径
	var URL = {
			commonUrl:"/trfc/salesPound/salesPage",		//销售逐车
			commonListUrl:"/trfc/salesPound/salesList",	
//			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
		bindEvent();
		queryData();

	}
	$('#buyCar').off('click').on('click',function(){
		$('input#jumpPageNo').val('');

		queryData(1);
	});
	
//	// 物料的四个tab切换菜单
	var wl_li = $('.wuliao_tab ul li');
	wl_li.click(function () {
	    $(this).addClass('select').siblings().removeClass('select');
	    var index = wl_li.index(this);
//	    $('.wuliao_tabbox > .wuliao_tabcont').eq(index).show().siblings().hide();
	});
	
	$("#allExport").off('click').on('click',function(){
		commonList();
		method('.tableExcelA');
	})
	function commonList(){
		$.ajax({
            url:URL.commonListUrl,
            async:false,
            cache:false,
            dataType:'json',
            type:'post',
            success:function(result){          
                if(result.code == 'E10002'){
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
							.append('<td>'+(list[i].customername||"")+'</td>')
							.append('<td>'+(list[i].cargo||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
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
	
	$('#searchBtn').off('click').on('click',function(){
			queryData(1);
			var clock1=document.getElementById("clock1").value;
			var clock2=document.getElementById("clock2").value;
			if(clock1!==""&&clock2!==""){
			document.querySelector(".clock6").innerHTML=clock1.slice(0,10);
			document.querySelector(".clock7").innerHTML=clock2.slice(0,10);
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
		var code = $('#bbg_gys').val();code = $.trim(code);
		var customername = $('#bbg_kk').val();customername = $.trim(customername);
		var cargo = $('#gys').val();cargo = $.trim(cargo);
		var drivername = $('#bbg_sjn').val();drivername = $.trim(drivername);
		var beginTime = $('#clock1').val();beginTime = $.trim(beginTime);
		var endTime = $('#clock2').val();endTime = $.trim(endTime);
		var vehicleno = $('#bbg_cph').val();vehicleno = $.trim(vehicleno);
		var billcode = $('#billcode').val();billcode = $.trim(billcode);
		var pageSize = $('#pageSize').val() || 20;pageSize = $.trim(pageSize);
		return {
			code:code,
			customername:customername,
			cargo:cargo,
			drivername:drivername,
			beginTime:str2Long(beginTime),
			endTime:str2Long(endTime),
			vehicleno:vehicleno,
			billcode:billcode,
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
//			销售逐车
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
		 //磅单编号 客户   物料   车号   库号    毛重   皮重   净重   结算净重   轻车时间   重车时间 
		function renderHtml(data){
			$('#RMg1').empty();   
			var list = data.list||[];
			if(list && list.length>0){
				var str1=0,str2=0,str3=0;
				for(var i=0;i<list.length;i++){
					str1+=list[i].grossweight;
					str2+=list[i].tareweight;
					str3+=list[i].netweight;
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
							.append('<td>'+(list[i].customername||"")+'</td>')
							.append('<td>'+(list[i].cargo||"")+'</td>')
							.append('<td>'+(list[i].vehicleno||"")+'</td>')
							.append('<td>'+(list[i].noticecode||"")+'</td>')
							.append('<td>'+(list[i].grossweight||"")+'</td>')
							.append('<td>'+(list[i].tareweight||"")+'</td>')
							.append('<td>'+(list[i].netweight||"")+'</td>')
							.append('<td>'+(lightt)+'</td>')
							.append('<td>'+(weightt)+'</td>')
							.appendTo('#RMg1');
				}
				$('<tr>').append('<td>'+("总计")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+(str1.toFixed(2))+'</td>')
				.append('<td>'+(str2.toFixed(2))+'</td>')
				.append('<td>'+(str3.toFixed(2))+'</td>')
				.append('<td>'+("---")+'</td>')
				.append('<td>'+("---")+'</td>')
				.appendTo('#RMg1');
			}else{
				layer.msg('暂无数据');
//				$('#dataMore').hide();
			}
		}
})(jQuery, window);