;(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/PushSingleAction/page",
//			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
		//初始化autocomplete
//		initAutoComplete();
		//初始化页面按钮绑定事件
		bindEvent();
		//初始化页面
		queryData(1);
	}
//	function initAutoComplete(){
//		var cache = {};
//	    $("#supplier").autocomplete({
//	    	source: function( request, response ) {
//	    		var term = request.term;
//	    		var supplier = cache['supplier'] || {};
//	    		if ( term in supplier ) {
//	    			response( supplier[ term ] );
//	    			return;
//	    		}
//	    		$.post( URL.autoCompleteSearch, request, function( data, status, xhr ) {
//	    			supplier[ term ] = data;
//	    			response( data );
//	    		});
//	    	},
//	    	response: function( event, ui ) {
//	    		if(ui.content && ui.content.length > 0){
//		    		ui.content.forEach(function(x,i,a){
//		    			x.label = x.name;
//		    			x.value = x.id;
//		    		});
//	    		}
//	    	},
//	    	select: function( event, ui ) {
//	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id);
//	    		return false;
//    		}
//	    }).off('click').on('click',function(){
//	    	$(this).autocomplete('search',' ');
//	    }).on('input keydown',function(){
//	    	$(this).removeAttr('supplierid');
//	    }).change(function(){
//    		if(!$(this).attr('supplierid')){
//    			$(this).val('');
//    		}
//	    });
//	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
			$('#dataMore').hide();
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#clean').off('click').on('click',function(){
			clean();
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
	
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
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
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
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
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var requisitionNum = obj.requisitionNum || '';
				var noticeNum = obj.noticeNum || '';
				var desc1 = obj.desc1 || '';
				var reasonFailure = obj.reasonFailure || '';
				var lightCarTime = obj.lightCarTime || '';
				var heavyCarTime = obj.heavyCarTime || '';
				var netWeight = obj.netWeight || '';
				var creator = obj.creator || '';
				var createtime = obj.createtime || '';
				var modifier = obj.modifier || '';
				var modifytime = obj.modifytime || '';
				//设置字体颜色 (LXY)
				var pushStatus = obj.pushStatus || '';
				switch (pushStatus) {
				case '1':
					pushStatus = '推单中';
					break;
				case '2':
					pushStatus = '推单成功';
					break;
				case '3':
					pushStatus = '推单失败';
					break;
				default:
					pushStatus = '';
					break;
				}
				var requisitionType = obj.requisitionType || '';
				switch (requisitionType) {
				case '1':
					requisitionType = '入库单';
					break;
				case '2':
					requisitionType = '申请单';
					break;
				case '3':
					requisitionType = '提货单';
					break;
				default:
					requisitionType = '';
					break;
				}
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+requisitionNum+'</td>')
						.append('<td>'+noticeNum+'</td>')
						.append('<td>'+pushStatus+'</td>')
						.append('<td>'+desc1+'</td>')
						.append('<td>'+requisitionType+'</td>')
						.append('<td>'+reasonFailure+'</td>')
						.append('<td>'+lightCarTime+'</td>')
						.append('<td>'+heavyCarTime+'</td>')
						.append('<td>'+netWeight+'</td>')
						.append('<td>'+creator+'</td>')
						.append('<td>'+createtime+'</td>')
						.append('<td>'+modifier+'</td>')
						.append('<td>'+modifytime+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
		}else{
			layer.msg('暂无数据');
			$('#dataMore').hide();
		}
	}
	
})(jQuery, window);