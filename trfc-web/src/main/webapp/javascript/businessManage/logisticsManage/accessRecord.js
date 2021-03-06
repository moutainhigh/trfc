;(function($, win){
	var clickNumber = 0;
	var timeoutIndex;
	var URL = {
			page: '/trfc/accessRecord/page',
			invalid: '/trfc/accessRecord/invalid',
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch"
	};
	init();
	function init(){

		//初始化默认查询当天的数据
		var array = getTodayStr();
		$('#starttime').val(array[0]);
		$('#endtime').val(array[1]);
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
		//初始化查询
		searchParamsGetData(1);
	}
	function initAutoComplete(){
		var cache = {};
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
	}
	//绑定按钮
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			searchParamsGetData(1);
		});
		$('#searchBtn').off('click').on('click', function(){
			searchParamsGetData(1);
		});
		$('#invalid').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
			clearTimeout(timeoutIndex);
			clickNumber++;
			if (clickNumber == 8) {
				setTimeout(function(){
					if (clickNumber == 8) {
						clickNumber = 0;
						invalid(obj);
					}
				},1000)
			}
			timeoutIndex = setTimeout(function(){
				clickNumber = 0;
			},1000);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				searchParamsGetData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			searchParamsGetData(1);
		});
	}
	function invalid(obj) {
		if(obj.state == '0'){
			layer.msg('数据已作废，不能进行作废操作！', {icon: 5});
			return;
		}
		layer.confirm('您确定要作废此单据么？', {
			btn: ['确定', '取消'],
			area: '600px'
		}, function(){
			$.post(URL.invalid, {id: obj.id}, function(result) {
				if(result.code == '000000'){
					layer.msg(result.error, {icon: 1});
					$('#refreshBtn').click();
				}else{
					layer.msg(result.error, {icon: 5});
				}
			});
		});
	}
	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//获取搜索参数
	function getParams(){
		var code = $('#code').val();code = $.trim(code);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var vehicleid = $('#vehicle').attr('vehicleid');vehicleid = $.trim(vehicleid);
		var businesstype = $('#businesstype').val();businesstype = $.trim(businesstype);
		var accesstype = $('#accesstype').val();accesstype = $.trim(accesstype);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var state = $('#state').val();state = $.trim(state);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			code: code,
			materielid: materielid,
			vehicleid: vehicleid,
			businesstype: businesstype,
			accesstype: accesstype,
			starttime: str2Long(starttime),
			endtime: str2Long(endtime),
			state: state,
			pageSize: pageSize
		}
	}
	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var day1 = myDate.getDate()+1;        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		if(day1<10){
			day1 = "0"+day1;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day1+" "+"00:00:00";
		return array;
	}
	
	//发送搜索请求
	function searchParamsGetData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
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
							searchParamsGetData(pageNo+1);
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
					layer.msg(result.error,{icon:5});
				}
				layer.close(index);
			}
		});
	}
	//渲染页面
	function renderHtml(data){
		$('#dataBody').empty();
		var list = data.list;
		if(list && list.length > 0){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var businesstype = '';
				switch(obj.businesstype){
				case '1':
					businesstype = '采购';
					break;
				case '2':
					businesstype = '销售';
					break;
				case '5':
					businesstype = '其他入库';
					break;
				case '7':
					businesstype = '其他出库';
					break;
				case '9':
					businesstype = '工程车辆';
					break;
				case '4':
					businesstype = '厂内倒运';
					break;
				default:
				}
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+(obj.code || '')+'</td>')
				.append('<td>'+(businesstype || '')+'</td>')
				.append('<td>'+(obj.accesstype ? obj.accesstype == '1' ? '入厂' : obj.accesstype == '2' ? '出厂' : '' : '')+'</td>')
				.append('<td>'+(obj.vehicleno || '')+'</td>')
				.append('<td>'+(obj.materielname || '')+'</td>')
				.append('<td>'+(obj.noticecode || '')+'</td>')
				.append('<td>'+(obj.otherparty || '')+'</td>')
				.append('<td>'+(obj.rfid || '')+'</td>')
				.append('<td>'+(obj.icardno || '')+'</td>')
				.append('<td>'+(obj.icardcode || '')+'</td>')
				.append('<td>'+(obj.entertimeStr || '')+'</td>')
				.append('<td>'+(obj.outtimeStr || '')+'</td>')
				.data(obj)
				.appendTo('#dataBody');
			}
		}else{
			layer.msg('暂无数据.');
		}
	}
})(jQuery, window);