;(function($, win){
	var URL = {
			salesPoundNoteMain: '/trfc/poundNote/sales/main',
			batchNumberPage: '/trfc/quality/sales/batchnum/page',
			updateSerialNumber: '/trfc/poundNote/sales/updateSerialNumber',
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch"
	};
	//日期字符串转为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
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
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload(true);
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				updateSerialNumber();
			}
		});
		//打开出厂编号列表
		$('#batchnumber').off('click').on('click',function(){
			queryBatchNumber(1);
			$('#altchuchang').modal('show');
		});
		//出厂编号搜索按钮
		$('#searchBtn').off('click').on('click',function(){
			queryBatchNumber($.trim($('#jumpPageNo1').val()) || 1);
		});
		//清空
		$('#clearBtn').off('click').on('click',function(){
			$('#factorycode').val('');
			$('#materiel').val('').removeAttr('materielid');
			$('#starttime').val('');
			$('#endtime').val('');
		});
		/**
		 * 以下分页
		 */
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				querySalesApplication(pageNo);
			}
		});
		$('#pageSize').change(function(){
			querySalesApplication(1);
		});
	}
	
	//获取出厂编号搜索条件
	function getSalesApplicationParams(){
		var factorycode = $('#factorycode').val();factorycode = $.trim(factorycode);
		var materielid = $('#materiel1').attr('materielid');materielid = $.trim(materielid);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;
		return {
			factorycode:factorycode,
			material:materielid,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	//查询出厂编号
	function queryBatchNumber(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getSalesApplicationParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.batchNumberPage,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderBatchNumberHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
						callback: function(pageNo){
							queryBatchNumber(pageNo+1);
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
	function timeToStr(time){
		if(time){
			var date = new Date(time);
			return date.format("yyyy-MM-dd HH:mm:ss");
		}
		return '';
	}
	//解析加载出厂编号
	function renderBatchNumberHtml(data){
		$('#batchNumberBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var materialname = obj.material || '';
				var factorycode = obj.factorycode || '';
				var count = obj.count || 0;
				var producedtime = timeToStr(obj.producedtime);
				var testtime = timeToStr(obj.testtime);
				var assaytime = timeToStr(obj.assaytime);
				var assayer = obj.assayer || '';
				var assayorg = obj.assayorg || '';
				$('<tr>').attr('title','双击选择').append('<td>'+code+'</td>')
				.append('<td>'+materialname+'</td>')
				.append('<td>'+factorycode+'</td>')
				.append('<td>'+count+'</td>')
				.append('<td></td>')
				.append('<td></td>')
				.append('<td>'+producedtime+'</td>')
				.append('<td>'+testtime+'</td>')
				.append('<td>'+assaytime+'</td>')
				.append('<td>'+assayer+'</td>')
				.append('<td>'+assayorg+'</td>')
				.data(obj)
				.appendTo('#batchNumberBody');
			}
		}else{
			layer.msg('暂无数据');
		}
		$('#batchNumberBody>tr').off('dblclick').on('dblclick',function(){
			var obj = $(this).data();
			$('#batchnumber').val(obj.factorycode).attr('batchnumberid',obj.id);
			$('#altchuchang').modal('hide');
		});
	}
	
	function getUpdateParams(){
		var id = $('#poundNoteId').val();
		var batchnumberid = $('#batchnumber').attr('batchnumberid');
		var serialnumber = $('#batchnumber').val();
		return {
			id: id,
			batchnumberid: batchnumberid,
			serialnumber: serialnumber
		}
	}
	
	function validate(params){
		if(!params.batchnumberid || !params.serialnumber){
			layer.msg('请选择出厂编号！', {icon: 5}); return false;
		}
		return params;
	}
	
	function updateSerialNumber(){
		var params = getUpdateParams();
		if(validate(params)){
			$.ajax({
				url:URL.updateSerialNumber,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = URL.salesPoundNoteMain;
					}else{
						layer.msg(result.error, {icon: 5});
						$('#addBtn').removeClass('disabled');
					}
				}
			});
		}
	}
	
})(jQuery, window);