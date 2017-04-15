;(function($, window){
	var URL = {
			page: '/trfc/poundNote/sales/page',
			addView: '/trfc/poundNote/sales/addView',
			updateSerialNumberView: '/trfc/poundNote/sales/updateSerialNumberView',
			detail: '/trfc/poundNote/sales/detail',
			redcollide: '/trfc/poundNote/sales/redcollide',
			invalid: '/trfc/poundNote/sales/invalid',
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"
	};
	//日期字符串转为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
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
	    $("#customer").autocomplete({
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
	    		$(this).val(ui.item.name).attr('customerid', ui.item.id).attr('select',true);
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
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
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
		var serialnumber = $('#serialnumber').val() || '';serialnumber = $.trim(serialnumber);
		var billcode = $('#billcode').val() || '';billcode = $.trim(billcode);
		var noticecode = $('#noticecode').val() || '';noticecode = $.trim(noticecode);
		var operator = $('#operator').val() || '';operator = $.trim(operator);
		var netweight = $('#netweight').val() || '';netweight = $.trim(netweight);
		var status = $('#status').val() || '';status = $.trim(status);
		var returnstatus = $('#returnstatus').val() || '';returnstatus = $.trim(returnstatus);
		var customerid = $('#customer').attr('customerid') || '';customerid = $.trim(customerid);
		var materielid = $('#materiel').attr('materielid') || '';materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid') || '';vehicleid = $.trim(vehicleid);
		var starttime = $('#starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || '';endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || '';pageSize = $.trim(pageSize);
		var params = {
				code: code,
				serialnumber:serialnumber,
				billcode: billcode,
				noticecode: noticecode,
				operator: operator,
				netweight: netweight,
				status: status,
				returnstatus: returnstatus,
				supplierid: customerid,
				materielid: materielid,
				vehicleid: vehicleid,
				starttime: str2Long(starttime),
				endtime: str2Long(endtime),
				pageSize: pageSize
			}; 
		if(status == '0'){
			delete params.status;
			params.redcollide = '1';
		}
		return params;
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
				case '1': status = '补增出库';break;
				case '2': status = '计量退货';break;
				case '3': status = '作废';break;
				default: break;
				}
				var putinwarehousecode = obj.putinwarehousecode || '';
				var billcode = obj.billcode || '';
				var noticecode = obj.noticecode || '';
				var customername = obj.customername || '';
				var receivedepartmentname = obj.receivedepartmentname || '';
				var materielname = obj.materielname || '';
				var vehicleno = obj.vehicleno || '';
				var grossweight = obj.grossweight;
				var tareweight = obj.tareweight;
				var netweight = obj.netweight;
				var serialnumber = obj.serialnumber || '';
				var weighername = obj.weighername || '';
				var lighttimeStr = obj.lighttimeStr || '';
				var weighttimeStr = obj.weighttimeStr || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td'+(obj.returnstatus == '0' ? ' class="colorred"' : '')+'>'+returnstatus+'</td>')
						.append('<td'+(obj.redcollide == '1' ? ' class="colorred"' : '')+'>'+redcollide+'</td>')
						.append('<td'+(obj.status == '1' || obj.status == '3' ? ' class="colorred"' : '')+'>'+status+'</td>')
						.append('<td>'+putinwarehousecode+'</td>')
						.append('<td>'+billcode+'</td>')
						.append('<td>'+noticecode+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+receivedepartmentname+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+vehicleno+'</td>')
						.append('<td>'+grossweight+'</td>')
						.append('<td>'+tareweight+'</td>')
						.append('<td>'+netweight+'</td>')
						.append('<td>'+serialnumber+'</td>')
						.append('<td>'+weighername+'</td>')
						.append('<td>'+lighttimeStr+'</td>')
						.append('<td>'+weighttimeStr+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>')
						.append('<td><span><a class="updateSerialNumberView"><i class="iconfont" data-toggle="tooltip" data-placement="bottom" title="批号编辑">&#xe65e;</i></a></span>'
									+'<span><a class="redcollide"><i class="iconfont" data-toggle="tooltip" data-placement="bottom" title="红冲">&#xe631;</i></a></span>'
									+'<span><a class="invalid"><i class="iconfont" data-toggle="tooltip" data-placement="bottom" title="作废">&#xe60c;</i></a></span></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody>tr>td>span a.updateSerialNumberView').off('click').on('click', function(){
				//批号编辑
				var obj = $(this).closest('tr').data();
				updateSerialNumberView(obj);
			});
			$('#dataBody>tr>td>span a.redcollide').off('click').on('click', function(){
				//红冲
				var obj = $(this).closest('tr').data();
				redcollideOper(obj);
			});
			$('#dataBody>tr>td>span a.invalid').off('click').on('click', function(){
				//作废
				var obj = $(this).closest('tr').data();
				invalidOper(obj);
			});
			$('#dataBody>tr').off('dblclick').on('dblclick', function(){
				var obj = $(this).closest('tr').data();
				//详情
				window.open(URL.detail + '?id=' + obj.id);
			});
		}else{
			layer.msg("暂无数据.");
		}
	}
	//出厂编号
	function updateSerialNumberView(obj){
		if(obj.status == '3'){
			layer.msg('该单据已作废！', {icon: 5});
			return;
		}
		window.open(URL.updateSerialNumberView + '?id=' + obj.id);
	}
	//红冲
	function redcollideOper(obj){
		if(obj.status == '3'){
			layer.msg('该单据已作废！', {icon: 5});
			return;
		}else if(obj.redcollide == '1'){
			layer.msg('已红冲的单据不能红冲操作！', {icon: 5});
			return;
		}else if(obj.returnstatus == '0'){
			layer.msg('数据未推单，不能进行红冲操作！', {icon: 5});
			return;
		}
		operationAjax('是否确认红冲此单据！', URL.redcollide, {
			id:obj.id
		});
	}
	//作废
	function invalidOper(obj){
		if(obj.status == '3'){
			layer.msg('该单据已作废！', {icon: 5});
			return;
		}else if(obj.returnstatus == '2'){
			layer.msg('已推单的单据不能作废操作！', {icon: 5});
			return;
		}else if(obj.status == '3'){
			layer.msg('该单据已作废！', {icon: 5});
			return;
		}
		operationAjax('是否确认作废此单据！', URL.invalid, {
			id:obj.id
		});
	}
	
	function operationAjax(confirmContent, url, params){
		var bn=layer.open({
			content: confirmContent,
			area: '600px',
			closeBtn:1,
			shadeClose:true,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//按钮【确定】的回调
				//数据存到服务器
				$.ajax({
					url: url,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							window.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
				layer.close(bn);
			},btn2: function(index, layero){
				//按钮【取消】的回调
			}
			,cancel: function(){
				//右上角关闭回调
			}
		});
		
		
	}
	
})(jQuery, window);