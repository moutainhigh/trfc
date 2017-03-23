$(function() {
	//请求路径
	var URL = {
			detail: '/trfc/cardReissue/detail',
			page: '/trfc/cardReissue/page',
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch"
	};
	ShowAction(1);
	initAutoComplete();
	$('#seek').click(function() {
		ShowAction(1);
	});
	$('#fresh').click(function() {
		ShowAction(1);
	});
	$('#list').on('dblclick','tr',function() {
		var id = $(this).data('obj').id;
		window.location.href = URL.detail + '?id=' + id;
	});
	$('#seek_reader').click(function() {
		//打开读卡器
		readerOpen();
		//开打卡片获取卡号
		var cardno = openCard();
		if(cardno){
			ShowAction(1, cardno)
			//蜂鸣
			readerBeep();
		}
		//关闭读卡器
		readerClose();
	});
	//加载下拉框
	function initAutoComplete(){
		var cache = {};
	    $("#seek_material").autocomplete({
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
	    $("#seek_vehicle").autocomplete({
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
	}
	
	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	
//	页面跳转
	function jumpPageAction() {
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
	function pageCallback(pageNo) {
		ShowAction(pageNo+1);
	}

//	展示数据列表
	function ShowAction(pageNo,card) {
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var icardno = '';
		if(card){
			icardno = card;
		}
		var code = $('#seek_access').val();code = $.trim(code);
		var materielid = $('#seek_material').attr('materielid');materielid = $.trim(materielid);
		var vehicleid = $('#seek_vehicle').attr('vehicleid');vehicleid = $.trim(vehicleid);
		var businesstype = $('#seek_business').val();businesstype = $.trim(businesstype);
		var accesstype = $('#seek_type').val();accesstype = $.trim(accesstype);
		var starttime = $('#seek_starttime').val();starttime = $.trim(starttime);
		var endtime = $('#seek_endtime').val();endtime = $.trim(endtime);
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		//获取查询条件
		var params = {
				icardno:icardno,
				accesscode: code,
				materialid: materielid,
				vehicleid: vehicleid,
				businesstype: businesstype,
				accesstype: accesstype,
				starttime: str2Long(starttime),
				endtime: str2Long(endtime),
				pageSize:pageSize,
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(URL.page,params,function(result) {
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
				$('#list').empty();
				if(list){
					showPageData(list,pageSize,pageNo);
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				alert(result.error);
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo) {
		var BT = {
				'1':'采购',
				'2':'销售',
				'3':'其他入库',
				'4':'其他出库'
		};
		var AT = {
				'1':'入厂',
				'2':'出厂'
		};
		var CY = {
				'0':'IC采样卡',
				'1':'IC过磅卡'
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list');
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+(obj.accesscode || '')+'</td>'
				+'<td>'+(BT[obj.businesstype] || '')+'</td>'
				+'<td>'+(AT[obj.accesstype] || '')+'</td>'
				+'<td>'+(obj.vehicleno || '')+'</td>'
				+'<td>'+(obj.materielname || '')+'</td>'
				+'<td>'+(obj.applicationcode || '')+'</td>'
				+'<td>'+(obj.companyname || '')+'</td>'
				+'<td>'+(obj.rfid || '')+'</td>'
				+'<td>'+(obj.icardno || '')+'</td>'
				+'<td>'+(obj.icardcode || '')+'</td>'
				+'<td>'+(getNowFormatDate(true, obj.entertime) || '')+'</td>'
				+'<td>'+(CY[obj.cardtype] || '')+'</td>'
				+'<td>'+(obj.minemouthname || '')+'</td>'
				+'<td>'+(obj.supplierremark || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
	}

	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
	//time(获取指定时间的字符串) 默认返回当前时间
	function getNowFormatDate(param,time) {
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
	}

	
});