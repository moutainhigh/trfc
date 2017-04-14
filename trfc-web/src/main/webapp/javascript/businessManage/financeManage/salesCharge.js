;(function($, win){
	
	var URL={
			pageUrl:"/trfc/salescharge/page",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch"
	};
	
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		bindEvent();
		queryData(1);
	}
	
	
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	
	
	function initAutoComplete(){
		var cache = {};
	   
	    $("#s_customer").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var customer = cache['customer'] || {};
	    		if ( term in customer ) {
	    			response( customer[ term ] );
	    			return;
	    		}
	    		$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
	    			customer[ term ] = data;
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.name;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
    		}
	    });
	    $("#a_customer").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var customer = cache['customer'] || {};
	    		if ( term in customer ) {
	    			response( customer[ term ] );
	    			return;
	    		}
	    		$.post( URL.customerAutoCompleteSearch, request, function( data, status, xhr ) {
	    			customer[ term ] = data;
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.name;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id);
	    		$('#a_channelcode').val(ui.item.channelcode);
				$('#a_salesmanname').val(ui.item.salesmanname).attr('salesmanid',ui.item.salesmanid);
				$('#a_transportcompanyname').val(ui.item.transportcompanyname).attr('transportcompanyid',ui.item.transportcompanyid);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('customerid');
	    }).change(function(){
    		if(!$(this).attr('customerid')){
    			$(this).val('');
        		$('#a_channelcode').val('');
        		$('#a_salesmanname').val('').removeAttr('salesmanid');
        		$('#a_transportcompanyname').val('').removeAttr('transportcompanyid');
    		}
	    });
	}
	
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#jumpButton').off('click').on('click',function(){
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
	

	function queryData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		var starttime=$('#s_starttime').val();starttime=$.trim(starttime);
		var endtime=$('#s_endtime').val();endtime=$.trim(endtime);
		var customername=$('#s_customer').val();customername=$.trim(customername);
		var chargeunit=$('#branch option:checked').text();
		var auditstatus=$('#auditstatus').val();
		if(auditstatus=='4'){
			auditstatus='';
		}
		var code=$('#code').val();code=$.trim(code);
		var pageNo=pageNo;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		
		var params={
				starttime:str2Long(starttime),
				endtime:str2Long(endtime),
				customername:customername,
				chargeunit:chargeunit,
				auditstatus:auditstatus,
				code:code,
				pageNo:pageNo,
				pageSize:pageSize
		};
		
//		console.log(params);
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				var list=data.list;
				var pageNo=data.pageNo;
				var pageSize=data.pageSize;
				var total=data.total;
				var tbody=$('#charges').empty();
				//添加数据总数
				$('.colorred').html(data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
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
				if(!list){
					layer.msg('暂无数据.');
					return;
				}
				for(var i=0;i<list.length;i++){
					var charge=list[i];
//					console.log(charge);
					var status=charge.auditstatus;
					switch(status){
						case '0':
							status='未审核';
							break;
						case '1':
							status='审核中';
							break;
						case '2':
							status='已审核';
							break;
						default:
							status='';
							break;
					}
					var billdate=new Date(charge.billdate).format("yyyy-MM-dd hh:mm:ss");
					if(!charge.billdate){
						billdate='';
					}
					var audittime=new Date(charge.audittime).format("yyyy-MM-dd hh:mm:ss");
					if(!charge.audittime){
						audittime='';
					}
					var auditname=charge.auditname;
					if(!charge.auditname){
						auditname='';
					}
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+charge.code+'</td><td id="if_audit'+i+'">'+status+'</td><td>'+
							billdate+'</td><td>'+charge.customername+'</td><td>'+charge.creditmoney+'</td><td>'+
							charge.orgmoney+'</td><td>'+charge.chargeunit+'</td><td>'+
							auditname+'</td><td>'+audittime+'</td><td>'+charge.makebillname+'</td>');
					tbody.append(tr);
					if(status=='未审核'){
						$('#if_audit'+i).addClass('colorred');
					}
					if(status=='审核中'){
						$('#if_audit'+i).addClass('colorblue');
					}
					tr.data(charge);
				}
				
				
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		layer.close(index);
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	Date.prototype.format = function(fmt) { 
	     var o = { 
	        "M+" : this.getMonth()+1,                 //月份 
	        "d+" : this.getDate(),                    //日 
	        "h+" : this.getHours(),                   //小时 
	        "m+" : this.getMinutes(),                 //分 
	        "s+" : this.getSeconds(),                 //秒 
	        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	        "S"  : this.getMilliseconds()             //毫秒 
	    }; 
	    if(/(y+)/.test(fmt)) {
	            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	    }
	     for(var k in o) {
	        if(new RegExp("("+ k +")").test(fmt)){
	             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	         }
	     }
	    return fmt; 
	}        
	
	
	
	
})(jQuery, window);