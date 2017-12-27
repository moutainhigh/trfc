;(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/purchaseApplication/page",
			autoCompleteSearch: "/trfc/supplier/autoCompleteSearch"
	};
	init();
	function init(){
		//初始化默认查询当nian的数据
		var array = getTodayStr();
		$('#starttime').val(array[0]);
		$('#endtime').val(array[1]);
		//初始化autocomplete
		initAutoComplete();
		//初始化页面按钮绑定事件
		bindEvent();
		//初始化页面
		queryData(1);
	}
	function initAutoComplete(){
		var cache = {};
	    $("#supplier").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var supplier = cache['supplier'] || {};
	    		if ( term in supplier ) {
	    			response( supplier[ term ] );
	    			return;
	    		}
	    		$.post( URL.autoCompleteSearch, request, function( data, status, xhr ) {
	    			supplier[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('supplierid');
	    }).change(function(){
    		if(!$(this).attr('supplierid')){
    			$(this).val('');
    		}
	    });
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
			$('#dataMore').hide();
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
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
		var code = $('#code').val();code = $.trim(code);
		var supplierid = $('#supplier').attr('supplierid');supplierid = $.trim(supplierid);
		var source = $('#source').val();source = $.trim(source);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;pageSize = $.trim(pageSize);
		return {
			code:code,
			supplierid:supplierid,
			source:source,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		var array = new Array();
		array[0]=year+"-"+"01"+"-"+"01"+" "+"00:00:00";
		array[1]=year+"-"+"12"+"-"+"31"+" "+"23:59:59";
		return array;
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
				var code = obj.code || '';
				var auditstatus = obj.auditstatus || '';
				//设置字体颜色 (LXY)
				var color = '';
				switch (auditstatus) {
				case '0':
					auditstatus = '未审核';
					color = 'class="colorred"';
					break;
				case '1':
					auditstatus = '已审核';
					break;
				default:
					auditstatus = '';
					break;
				}
				var source = obj.source || '';
				switch (source) {
				case '0':
					source = '联机';
					break;
				case '1':
					source = '脱机';
					break;
				default:
					source = '';
					break;
				}
				var billtypename = obj.billtypename || '';
				var suppliername = obj.suppliername || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
				var minemouthname = obj.minemouthname || '';
				var buyername = obj.buyername || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				var remark = obj.supplierremark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td '+color+'>'+auditstatus+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+suppliername+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
						.append('<td>'+minemouthname+'</td>')
						.append('<td>'+buyername+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>')
						.append('<td>'+auditname+'</td>')
						.append('<td>'+audittimeStr+'</td>')
						.append('<td>'+remark+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody tr').off('click').on('click',function(){
				$('#moreBody').empty();
				var obj = $(this).data();
				var listdetail = obj.listdetail;
				if(listdetail && listdetail.length > 0){
					for(var i=0;i<listdetail.length;i++){
						var detail = listdetail[i];
						var orgname = detail.orgname || '';
						var materielname = detail.materielname || '';
						var materielspec = detail.materielspec || '';
						var materieltype = detail.materieltype || '';
						var purchasesum = detail.purchasesum;
						var margin = detail.margin;
						var storagequantity = detail.storagequantity;
						var unstoragequantity = detail.unstoragequantity;
						var pretendingtake = detail.pretendingtake;
						var remark = detail.remark || '';
						$('<tr>').append('<td>'+(i+1)+'</td>')
								.append('<td>'+orgname+'</td>')
								.append('<td>'+materielname+'</td>')
								.append('<td>'+materielspec+'</td>')
								.append('<td>'+materieltype+'</td>')
								.append('<td>'+purchasesum+'</td>')
								.append('<td>'+storagequantity+'</td>')
								.append('<td>'+unstoragequantity+'</td>')
								.append('<td>'+pretendingtake+'</td>')
								.append('<td>'+margin+'</td>')
								.append('<td>'+remark+'</td>')
								.appendTo('#moreBody');
					}
				}
				$('#dataMore').show();
			});
			$('#dataBody tr').off('dblclick').on('dblclick',function(){
				$('#detailtab1').empty();
				var obj = $(this).data();
				var code = obj.code || '';
				var source = obj.source || '';
				var billtypename = obj.billtypename || '';
				var billtimeStr = obj.billtimeStr || '';
				var suppliername = obj.suppliername || '';
				//var sumnum = obj.sumnum;
				var buyername = obj.buyername || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var remark = obj.remark || '';
				$('#v_code').val(code);
				$('#v_source').val(source == '0' ? '联机' : source == '1' ? '脱机' : '');
				$('#v_billtypename').val(billtypename);
				$('#v_billtime').val(billtimeStr);
				$('#v_suppliername').val(suppliername);
				//$('#v_sumnum').val(sumnum);
				$('#v_buyername').val(buyername);
				$('#v_makebillname').val(makebillname);
				$('#v_makebilltime').val(makebilltimeStr);
				$('#v_remark').val(remark);
				var listdetail = obj.listdetail;
				if(listdetail && listdetail.length > 0){
					var sumnum = 0;
					for(var i=0;i<listdetail.length;i++){
						var detail = listdetail[i];
						var orgname = detail.orgname || '';
						var materielname = detail.materielname || '';
						var purchasesum = detail.purchasesum;
						var remark = detail.remark || '';
						$('<tr>').append('<td>'+(i+1)+'</td>')
								.append('<td>'+orgname+'</td>')
								.append('<td>'+materielname+'</td>')
								.append('<td></td>')
								.append('<td>'+purchasesum+'</td>')
								.append('<td>'+remark+'</td>')
								.appendTo('#detailtab1');
						sumnum += purchasesum;
					}
					$('<tr>').append('<td></td>')
							.append('<td>合计</td>')
							.append('<td></td>')
							.append('<td></td>')
							.append('<td>'+sumnum+'</td>')
							.append('<td></td>')
							.appendTo('#detailtab1');
					$('#v_sumnum').val(sumnum);
				}
				switch (obj.auditstatus) {
				case '0':
					$('#shImg').attr('src','/resources/images/un_sh.png');
					break;
				case '1':
					$('#shImg').attr('src','/resources/images/sh.png');
					break;
				default:
					break;
				}
				$('#dataDetail').modal();
			});
		}else{
			layer.msg('暂无数据');
			$('#dataMore').hide();
		}
	}
	
})(jQuery, window);