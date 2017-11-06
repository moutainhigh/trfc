$(function(){
	//访问路径
	var URL = {
			page: '/trfc/salesArrive/page',
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch"
	};
	init();
	
	function init() {
		ShowAction(1);
		initAutoComplete();
		$('#refreshBtn').off('click').on('click', function(){
			ShowAction(1);
		});
		$('#searchBtn').off('click').on('click', function(){
			ShowAction(1);
		});
		$('#pageSize').change(function(){
			initPageList(1);
		});
		$('#jumpPageNo').off('click').on('click',function() {
			jumpPageAction();
		});
		$('#readSearch').off('click').on('click',function(){
			readSearch();
		});
		
	}
	function readSearch() {
		if (initCardReader()) {
			//打开读卡器
			readerOpen();
			if (openCard()) {
				//开打卡片获取卡序号
				var vehicleno = getDataFromCard(2);
				//蜂鸣
				readerBeep();
				if(vehicleno){
					get({vehicleno:vehicleno});
				}
			}
			//关闭读卡器
			readerClose();
		}else{
			layer.msg('当前游览器不支持!(只兼容IE游览器)');
		}
	}
	//获取查询条件
	function getParams(){
		var billcode = $('#billcode').val() || ''; billcode = $.trim(billcode);
		var code = $('#code').val() || ''; code = $.trim(code);
		var auditstatus = $('#auditstatus').val() || ''; auditstatus = $.trim(auditstatus);
		var source = $('#source').val() || ''; source = $.trim(source);
		var status = $('#status').val() || ''; status = $.trim(status);
		var customerid = $('#customer').attr('customerid') || ''; customerid = $.trim(customerid);
		var vehicleid = $('#vehicle').attr('vehicleid') || ''; vehicleid = $.trim(vehicleid);
		var materielid = $('#materiel').attr('materielid') || ''; materielid = $.trim(materielid);
		var driverid = $('#driver').attr('driverid') || ''; driverid = $.trim(driverid);
		var starttime = $('#starttime').val() || ''; starttime = $.trim(starttime);
		var endtime = $('#endtime').val() || ''; endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || ''; pageSize = $.trim(pageSize);
		return {
			billcode:billcode,
			code:code,
			customerid:customerid,
			vehicleid:vehicleid,
			auditstatus:auditstatus,
			materielid:materielid,
			driverid:driverid,
			source:source,
			status:status,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
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
	    }).on('input keydown',function(){
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
	    }).on('input keydown',function(){
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
	    }).on('input keydown',function(){
	    	$(this).removeAttr('materielid');
	    }).change(function(){
    		if(!$(this).attr('materielid')){
    			$(this).val('');
    		}
	    });
	    $("#driver").autocomplete({
	    	source: function( request, response ) {
	    		var term = request.term;
	    		var driver = cache['driver'] || {};
	    		if ( term in driver ) {
	    			response( driver[ term ] );
	    			return;
	    		}
	    		$.post( URL.driverAutoCompleteSearch, request, function( data, status, xhr ) {
	    			driver[ term ] = data;
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
	    		$(this).val(ui.item.name).attr('driverid', ui.item.id);
	    		return false;
	    	}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input keydown',function(){
	    	$(this).removeAttr('driverid');
	    }).change(function(){
    		if(!$(this).attr('driverid')){
    			$(this).val('');
    		}
	    });
	}
	
	//日期字符串转换成时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	
//	页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			ShowAction(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo){
		ShowAction(pageNo+1);
	}

//	展示数据列表
	function ShowAction(pageNo){
		layer.closeAll();
		var params = getParams();
		params.pageNo = pageNo;
		get(params);
	}
	function get(params) {
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		$.post(URL.page,params,function(result){
			if(result.code=='000000'){
				var list = result.data.list;
				var pageNo = result.data.pageNo;
				var pageSize = result.data.pageSize;
				var total = result.data.total;
				//添加数据总数
				$('#total').html(result.data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				//分页插件
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
				if(list){
					showPageData(list,pageSize,pageNo);
				}else{
					 $('#list').empty();
					 layer.msg('暂无数据');
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				alert(result.error);
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo){
		//车辆状态
		var STATUS = {
				'0':'未入厂',
				'1':'一次过磅',
				'2':'二次过磅',
				'3':'作废',
				'4':'发卡',
				'5':'出厂',
				'6':'入厂',
				'7':'装车'
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var salesApplication = obj.listApplication.filter(function(x){
				return x.id == this;
			},obj.billid)[0];
			var salesApplicationDetail = salesApplication.list[0];
			
			if(!obj.poundNoteResp){
				obj.poundNoteResp = "";
			}
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.vehicleno || '')+'</td>'
				+'<td '+(obj.status==='3'?'class="colorred">':'class="colorblue">')+(obj.forceOutFactory == '1' ? '强制出厂' : (STATUS[obj.status] || ''))+'</td>'
				+'<td>'+(obj.makebilltimeStr || '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.enterTime)|| '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.poundNoteResp.lighttime) || '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.poundNoteResp.weighttime) || '')+'</td>'
				+'<td>'+(obj.startLoadingTime || '')+'</td>'
				+'<td>'+(obj.endLoadingTime || '')+'</td>'
				+'<td>'+(obj.sealTime || '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.outTime) || '')+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td>'+(obj.billcode || '')+'</td>'
				+'<td>'+(salesApplication.customername|| '')+'</td>'
				+'<td>'+(salesApplicationDetail.materielname || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			
		}
		
	}
	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
	//time(获取指定时间的字符串) 默认返回当前时间
	function getNowFormatDate(param,time) {
		if(typeof(time)=='undefined'){
			return '';
		}
		var date ;
//		判断time参数是否存在
		if(time){
			date = new Date(time);
		}else{
			date = new Date();
		}
		var seperator1 = "-";
		var seperator2 = ":";
//		获取月份
		var month = date.getMonth() + 1;
//		获取日期
		var strDate = date.getDate();
//		月或者日 为个位数时前面加'0'
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		if (hours >= 0 && hours <= 9) {
			hours = "0" + hours;
		}
		if (minutes >= 0 && minutes <= 9) {
			minutes = "0" + minutes;
		}
		if (seconds >= 0 && seconds <= 9) {
			seconds = "0" + seconds;
		}
//		判断返回结果
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + hours + seperator2 + minutes
			+ seperator2 + seconds;
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
	};
	
});