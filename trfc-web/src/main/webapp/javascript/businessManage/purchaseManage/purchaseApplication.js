;(function($, win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/purchaseApplication/page",
	};
	init();
	function init(){
		initAutoComplete();
		bindEvent();
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
	    		$.post( "/trfc/supplier/autoCompleteSearch", request, function( data, status, xhr ) {
	    			supplier[ term ] = data;
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		ui.content.forEach(function(x,i,a){
	    			x.label = x.name;
	    			x.value = x.id;
	    		});
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search');
	    }).change(function(){
	    	$(this).val('').removeAttr('supplierid');
	    });
	}
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searchBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
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
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
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
					alert(result.error);
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
				switch (auditstatus) {
				case '0':
					auditstatus = '未审核';
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
				var buyername = obj.buyername || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				var remark = obj.remark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td class="colorred">'+auditstatus+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+suppliername+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
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
						var remark = detail.remark || '';
						$('<tr>').append('<td>'+(i+1)+'</td>')
								.append('<td>'+orgname+'</td>')
								.append('<td>'+materielname+'</td>')
								.append('<td>'+materielspec+'</td>')
								.append('<td>'+materieltype+'</td>')
								.append('<td>'+purchasesum+'</td>')
								.append('<td></td>')
								.append('<td></td>')
								.append('<td></td>')
								.append('<td></td>')
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
				$('#v_source').val(source);
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