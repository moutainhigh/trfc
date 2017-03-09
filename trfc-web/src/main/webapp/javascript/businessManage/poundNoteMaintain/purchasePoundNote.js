;(function($, window){
	var URL = {
			page: '/trfc/poundNote/purchase/page',
			addView: '/trfc/poundNote/purchase/addView',
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"
	};
	//日期字符串转为时间戳
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	//初始化
	init();
	//初始化方法
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化列表
		getDataFormAjax(1);
	}
	//初始化autocomplete
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
	    		$.post( URL.supplierAutoCompleteSearch, request, function( data, status, xhr ) {
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
	    		$(this).val(ui.item.name).attr('supplierid', ui.item.id).attr('select',true);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('supplierid');
	    }).change(function(){
    		if(!$(this).attr('supplierid')){
    			$(this).val('');
    		}
	    });
	    $("#vehicle").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var vehicle = cache['vehicle'] || {};
	    		if ( term in vehicle ) {
	    			response( vehicle[ term ] );
	    			return;
	    		}
	    		$.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
	    			vehicle[ term ] = data;
	    			response( data );
	    		});
	    	},
	    	response: function( event, ui ) {
	    		if(ui.content && ui.content.length > 0){
	    			ui.content.forEach(function(x,i,a){
	    				x.label = x.vehicleno;
	    				x.value = x.id;
	    			});
	    		}
	    	},
	    	select: function( event, ui ) {
	    		$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('vehicleid');
	    }).change(function(){
    		if(!$(this).attr('vehicleid')){
    			$(this).val('');
    		}
	    });
	    $("#materiel").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var materiel = cache['materiel'] || {};
	    		if ( term in materiel ) {
	    			response( materiel[ term ] );
	    			return;
	    		}
	    		$.post( URL.materielAutoCompleteSearch, request, function( data, status, xhr ) {
	    			materiel[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('materielid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('materielid');
	    }).change(function(){
    		if(!$(this).attr('materielid')){
    			$(this).val('');
    		}
	    });
	}
	//绑定按钮事件
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#searchBtn').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#addBtn').off('click').on('click', function(){
			window.open(URL.addView);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				getDataFormAjax(pageNo);
			}
		});
		$('#pageSize').change(function(){
			getDataFormAjax(1);
		});
	}
	//获取采购磅单查询条件
	function getSearchParams(){
		var code = $('#code').val() || '';code = $.trim(code);
		var billcode = $('#billcode').val() || '';billcode = $.trim(billcode);
		var noticecode = $('#noticecode').val() || '';noticecode = $.trim(noticecode);
		var operator = $('#operator').val() || '';operator = $.trim(operator);
		var netweight = $('#netweight').val() || '';netweight = $.trim(netweight);
		var status = $('#status').val() || '';status = $.trim(status);
		var returnstatus = $('#returnstatus').val() || '';returnstatus = $.trim(returnstatus);
		var supplierid = $('#supplier').attr('supplierid') || '';supplierid = $.trim(supplierid);
		var materielid = $('#materiel').attr('materielid') || '';materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid') || '';vehicleid = $.trim(vehicleid);
		var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || '';pageSize = $.trim(pageSize);
		return {
			code: code,
			billcode: billcode,
			noticecode: noticecode,
			operator: operator,
			netweight: netweight,
			status: status,
			returnstatus: returnstatus,
			supplierid: supplierid,
			materielid: materielid,
			vehicleid: vehicleid,
			starttime: str2Long(starttime),
			endtime: str2Long(endtime),
			pageSize: pageSize
		}
	}
	
	function validate(params){
		return params;
	}
	//
	function getDataFormAjax(pageNo){
		var params = getSearchParams();
		if(validate(params)){
			params.pageNo = pageNo;
			var index = layer.load(2, {
				  shade: [0.3,'#fff'] //0.1透明度的白色背景
				});
			$.ajax({
				url:URL.page,
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
						    	getDataFormAjax(pageNo+1);
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
						layer.msg(result.error, {icon: 5});
					}
					layer.close(index);
				}
			});
		}
	}
	//解析加载页面
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var returnstatus = '';
				switch (obj.returnstatus) {
				case '0': returnstatus = '未推单';break;
				case '1': returnstatus = '推单中';break;
				case '2': returnstatus = '已推单';break;
				default: break;
				}
				var redcollide = '';
				switch (obj.redcollide) {
				case '0': redcollide = '否';break;
				case '1': redcollide = '是';break;
				default: break;
				}
				var status = '';
				switch (obj.status) {
				case '0': status = '计量系统';break;
				case '1': status = '补增入库';break;
				case '2': status = '计量退货';break;
				default: break;
				}
				var putinwarehousecode = obj.putinwarehousecode || '';
				var noticecode = obj.noticecode || '';
				var suppliername = obj.suppliername || '';
				var supplierremark = obj.supplierremark || '';
				var receivedepartmentname = obj.receivedepartmentname || '';
				var minemouthname = obj.minemouthname || '';
				var materielname = obj.materielname || '';
				var vehicleno = obj.vehicleno || '';
				var grossweight = obj.grossweight;
				var tareweight = obj.tareweight;
				var netweight = obj.netweight;
				var deductionweight = obj.deductionweight;
				var deductionother = obj.deductionother;
				var lighttimeStr = obj.lighttimeStr || '';
				var weighttimeStr = obj.weighttimeStr || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+returnstatus+'</td>')
						.append('<td>'+redcollide+'</td>')
						.append('<td>'+status+'</td>')
						.append('<td>'+putinwarehousecode+'</td>')
						.append('<td>'+noticecode+'</td>')
						.append('<td>'+suppliername+'</td>')
						.append('<td>'+receivedepartmentname+'</td>')
						.append('<td>'+minemouthname+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+vehicleno+'</td>')
						.append('<td>'+grossweight+'</td>')
						.append('<td>'+tareweight+'</td>')
						.append('<td>'+netweight+'</td>')
						.append('<td>'+deductionweight+'</td>')
						.append('<td>'+deductionother+'</td>')
						.append('<td>'+lighttimeStr+'</td>')
						.append('<td>'+weighttimeStr+'</td>')
						.append('<td>'+supplierremark+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>')
						.append('<td><span><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="bottom" title="编辑">&#xe600;</i></a></span>'
								+'<span> <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="bottom" title="删除">&#xe63d;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
		}else{
			layer.msg("暂无数据.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(jQuery, window);