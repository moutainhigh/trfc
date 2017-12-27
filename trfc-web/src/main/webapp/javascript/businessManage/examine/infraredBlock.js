;(function($){
	var URL = {
		page: '/trfc/infraredBlock/page',
		audit: '/trfc/infraredBlock/audit'
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
		getDataFormAjax(1);
	}
	function initAutoComplete(){
		var cache = {};
	}
	
	//绑定按钮
	function initBindEvent(){
		$('#refresh').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#search').off('click').on('click', function(){
			getDataFormAjax(1);
		});
		$('#audit').off('click').on('click', function(e){
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
				window.location.href = '/trfc/infraredBlock/auditView?id=' + obj.id;
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

	//将日期字符串转变为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	//获取搜索参数
	function getParams(){
		var startTime = $('#starttime').val();startTime = $.trim(startTime);
		var endTime = $('#endtime').val();endTime = $.trim(endTime);
		var pnCode = $('#pn_code').val();pnCode = $.trim(pnCode);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			startTime: str2Long(startTime),
			endTime: str2Long(endTime),
			pnCode: pnCode,
			pageSize: pageSize
		}
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
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day+" "+"23:59:59";
		return array;
	}
	//发送搜索请求
	function getDataFormAjax(pageNo){
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
		if (list && list.length > 0) {
			for (var i=0;i<list.length;i++) {
				var obj = list[i];
				$('<tr>').append('<td>'+(i+1)+'</td>')
				.append('<td>'+(obj.pnId || '')+'</td>')
				.append('<td>'+(obj.remark || '')+'</td>')
				.append('<td>'+(obj.auditStatus == '0' ? '未审批' : obj.auditStatus == '1' ? '已审批' : '')+'</td>')
				.append('<td>'+(obj.auditTimeStr || '')+'</td>')
				.append('<td>'+(obj.createtimeStr || '')+'</td>')
				
				.data(obj)
				.appendTo('#dataBody');
			}
			$('#dataBody tr').off('dblclick').on('dblclick', function() {
				var obj = $(this).data();
					window.location.href = '/trfc/infraredBlock/auditView?id=' + obj.id;
			});
		} else {
			layer.msg('暂无数据.');
		}
	}
})(jQuery);