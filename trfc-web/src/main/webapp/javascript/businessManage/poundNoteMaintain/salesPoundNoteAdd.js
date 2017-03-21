;(function($, win){
	var URL = {
			salesPoundNoteMain: '/trfc/poundNote/sales/main',
			pageGroupMateriel: '/trfc/salesApplication/pageGroupMateriel',
			batchNumberPage: '/trfc/quality/sales/batchnum/page',
			addSalesPoundNote: '/trfc/poundNote/sales/addPoundNote',
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			customerAutoCompleteSearch: "/trfc/customer/autoCompleteSearch"
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
	init();
	function init(){
		//初始化autocomplete
		initAutoComplete();
		//初始化按钮事件
		initBindEvent();
	}
	function initAutoComplete(){
		var cache = {};
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
	    		$('#identityno').val(ui.item.identityno);
	    		return false;
    		}
	    }).off('click').on('click',function(){
	    	$(this).autocomplete('search',' ');
	    }).on('input propertychange',function(){
	    	$(this).removeAttr('driverid');
	    }).change(function(){
    		if(!$(this).attr('driverid')){
    			$(this).val('');
    		}
	    });
		$("#materiel, #materiel1").autocomplete({
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
	}
	//绑定按钮事件
	function initBindEvent(){
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			window.location.reload();
		});
		//保存
		$('#addBtn').off('click').on('click',function(){
			if(!$(this).hasClass('disabled')){
				$(this).addClass('disabled');
				addSalesPoundNotePoundNote();
			}
		});
		//打开订单列表
		$('#billcode').off('click').on('click',function(){
			querySalesApplication(1);
			$('#salesApplication').modal('show');
		});
		//搜索按钮
		$('#searchBtn').off('click').on('click',function(){
			querySalesApplication($.trim($('#jumpPageNo').val()) || 1);
		});
		//清空
		$('#clearBtn').off('click').on('click',function(){
			$('#materiel').val('').removeAttr('materielid');
			$('#customer').val('').removeAttr('customerid');
			$('#applicationcode').val('');
			$('#starttime').val('');
			$('#endtime').val('');
		});
		//打开出厂编号列表
		$('#batchnumber').off('click').on('click',function(){
			queryBatchNumber(1);
			$('#altchuchang').modal('show');
		});
		//出厂编号搜索按钮
		$('#searchBtn1').off('click').on('click',function(){
			queryBatchNumber($.trim($('#jumpPageNo1').val()) || 1);
		});
		//清空
		$('#clearBtn1').off('click').on('click',function(){
			$('#factorycode').val('');
			$('#materiel1').val('').removeAttr('materielid');
			$('#starttime').val('');
			$('#endtime').val('');
		});
		$('#returnApplication').off('click').on('click',function(){
			if($('#salesApplication').is(':visible')){
				var trs = $('#salesApplication').find('tr.active');
				if(trs.length == 0){
					layer.msg('至少选择一个订单！');return;
				}else if(trs.length == 1){
					var obj = trs.data();
					selectSalesApplication(obj, [{
						billid: obj.id,
						billdetailid: obj.detailid
					}], trs);
				}else if(trs.length >1 && trs.length <= 3){
					var flag = true;
					var bills = [];
					var salessum = 0;
					var marginsum = 0;
					var div = '<div class="layer-content-radio-div">';
					trs.each(function(i){
						var rightData = $(trs[i]).data();
						bills.push({
							billid: rightData.id,
							billdetailid: rightData.detailid
						});
						if(i > 0){
							var leftData = $(trs[i-1]).data();
							if(leftData.customerid == rightData.customerid && leftData.materielname == rightData.materielname){
								
							}else{
								layer.msg('不能同时选择多个客户和物料！'); flag = false;
								return;
							}
						}
						marginsum += rightData.margin;
						if(rightData.margin){
							salessum += rightData.salessum;
						}
						div += '<div><label><input name="billcode" type="radio">'+rightData.code+'</label></div>';
					});
					div += '</div>';
					if(flag){
						var i = layer.open({
							type: '1',
							area: ['400px', '250px'],
							shadeClose: true,
							content: div,
							btn: ['确认', '取消'],
							yes: function(index, layero){
								if($('.layer-content-radio-div input[type="radio"]:checked').length == 0){
									layer.msg('请选择主订单！', {icon: 5});return false;
								}else{
									var index = $('.layer-content-radio-div input:radio:checked').closest('div').index();
									var obj = $(trs[index]).data();
									trs.sort(function(a,b){
										var aData = $(a).data();
										var bData = $(b).data();
										if($('#maindeduction')[0].checked){
											if(aData.billid == obj.billid){
												return -1;
											}
										}
										return aData.margin - bData.margin;
									});
									selectSalesApplication(obj, bills, trs);
									$('#margin').val(marginsum);
									if(!$('#maindeduction').is(':checked')){
										$('#salessum').val(salessum);
									}
									layer.close(i);
								}
							}
						});
					}
				}else if(trs.length > 3){
					layer.msg('一次最多选择3个订单！');return;
				}
			}
		});
		$('#pickupquantity').off('input propertychange').on('input propertychange',function(){
			var pickupquantity = $(this).val();
			if(!pickupquantity || !$.isNumeric(pickupquantity)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
			}
		});
		/**
		 * 自动计算净重
		 */
		$('#grossweight').off('input propertychange').on('input propertychange',function(){
			var grossweight = $(this).val();
			if(!grossweight || !$.isNumeric(grossweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				var tareweight = $('#tareweight').val();
				if(tareweight && $.isNumeric(tareweight)){
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
		$('#tareweight').off('input propertychange').on('input propertychange',function(){
			var tareweight = $(this).val();
			if(!tareweight || !$.isNumeric(tareweight)){
				layer.tips('必须为数字，且不能为空！', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				$(this).val('');
				$('#netweight').val('');
			}else{
				var grossweight = $('#grossweight').val();
				if(grossweight && $.isNumeric(grossweight)){
					$('#netweight').val(grossweight-tareweight);
				}
			}
		});
		$('#pickupquantity').off('input propertychange').on('input propertychange', function(){
			var marginsum = parseFloat($('#margin').val()) || 0;
			var value = parseFloat($(this).val() || 0);
			if(!$.isNumeric(value)){
				layer.tips('提货量必须为数字!', this, {
					  tips: [1, '#3595CC'],
					  time: 2000
					});
				 $(this).val(''); return;
			}else if(value > marginsum){
				layer.msg('提货量不能大于'+marginsum+'!', {icon: 5}); $(this).val(''); return;
			}
			$('#salesApplicationDetailBody tr').each(function(){
				var yl = $(this).find('td.yl').text(); yl = parseFloat(yl);
				if(value > 0){
					if(yl >= value){
						$(this).find('td.yt').html(value);
					}else{
						$(this).find('td.yt').html(yl);
					}
				}else{
					$(this).find('td.yt').html(0);
				}
				value -= yl;
			});
		});
		/**
		 * 以下分页
		 */
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				querySalesApplication(pageNo);
			}
		});
		$('#pageSize').change(function(){
			querySalesApplication(1);
		});
		$('#jumpPageNoBtn1').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo1').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo1').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo1').val('');
			}else{
				$('input#jumpPageNo1').val(pageNo);
				queryBatchNumber(pageNo);
			}
		});
		$('#pageSize').change(function(){
			queryBatchNumber(1);
		});
	}
	//日期字符串转为时间戳
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	//获取销售订单搜索条件
	function getSalesApplicationParams(){
		var customerid = $('#customer').attr('customerid');customerid = $.trim(customerid);
		var materielid = $('#materiel').attr('materielid');materielid = $.trim(materielid);
		var salesApplicationCode = $('#salesApplicationCode').val();salesApplicationCode = $.trim(salesApplicationCode);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || 10;
		return {
			customerid:customerid,
			materielid:materielid,
			code:salesApplicationCode,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	//查询销售订单
	function querySalesApplication(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getSalesApplicationParams();
		params.pageNo = pageNo;
		$.ajax({
			url:URL.pageGroupMateriel,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					renderSalesApplicationHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo){
					    	querySalesApplication(pageNo+1);
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
	//解析加载销售订单
	function renderSalesApplicationHtml(data){
		$('#salesApplicationBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var customer = obj.customerManageResp;
				var code = obj.code || '';
				var billtypename = obj.billtypename || '';
				var customername = obj.customername || '';
				var materielname = obj.materielname || '';
				var salessum = obj.salessum || '';
				var margin = obj.margin || 0;
				var storagequantity = obj.storagequantity || 0;
				var unstoragequantity = obj.unstoragequantity || 0;
				var pretendingtake = obj.pretendingtake || 0;
				var orgname = obj.orgname || '';
				var billtimeStr = obj.billtimeStr || '';
				var departmentname = obj.departmentname || '';
				var salesmanname = obj.salesmanname || '';
				var makebillname = obj.makebillname || '';
				var channelcode = obj.channelcode || '';
				$('<tr>').append('<td><input type="checkbox"/></td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+materielname+'</td>')
						.append('<td>'+salessum+'</td>')
						.append('<td>'+margin+'</td>')
						.append('<td>'+storagequantity+'</td>')
						.append('<td>'+unstoragequantity+'</td>')
						.append('<td>'+pretendingtake+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+departmentname+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+channelcode+'</td>')
						.data(obj)
						.appendTo('#salesApplicationBody');
			}
		}else{
			layer.msg('暂无数据');
		}
		$('#salesApplicationBody>tr').find('td:eq(0)>input[type="checkbox"]').off('change').on('change',function(){
			if(this.checked == true){
				$(this).closest('tr').addClass('active');
			}else{
				$(this).closest('tr').removeClass('active');
			}
		}).off('click').on('click',function(e){
			e.stopPropagation();
		});
		$('#salesApplicationBody>tr').off('click').on('click',function(e){
			e.stopPropagation();
			$(this).find('td:eq(0)>input').trigger('click');
		});
	}
	
	//显示相关订单信息
	function selectSalesApplication(obj, bills, trs){
		$('#billcode').val(obj.code || '').attr('billid', obj.id || '').attr('billdetailid', obj.detailid || '').attr('bills', JSON.stringify(bills));
		$('#customername').val(obj.customername || '');
		$('#orgname').val(obj.orgname || '');
		$('#materielname').val(obj.materielname || '');
		$('#salessum').val(obj.salessum || '');
		$('#margin').val(obj.margin || 0);
		$('#salesApplication').modal('hide');
		$('#salesApplicationDetailBody').empty();
		trs.each(function(i){
			var data = $(this).data();
			$('#salesApplicationDetailBody').append('<tr><td>'+(i+1)+'</td><td>'+(data.code || '')+'</td><td>'+(data.billtypename || '')+'</td>'
					+'<td>'+(data.billtimeStr || '')+'</td><td>'+(data.materielname || '')+'</td>'
					+'<td>'+(data.unit || '')+'</td><td>'+(data.salessum || '')+'</td>'
					+'<td class="yl">'+(data.margin || 0)+'</td><td class="yt">0</td><td>'+(data.taxprice)+'</td><td>'+(data.orgname || '')+'</td>'
					+'<td>'+(data.customername || '')+'</td></tr>');
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
					alert(result.error);
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
	
	//获取销售计量参数
	function getSalesPoundNoteParams(){
		var billid = $('#billcode').attr('billid');
		var billdetailid = $('#billcode').attr('billdetailid');
		var maindeduction = 0;
		if($('#maindeduction').is(':checked')){
			maindeduction = 1;
		}
		var vehicleid = $('#vehicle').attr('vehicleid');
		var pickupquantity = $('#pickupquantity').val();
		var grossweight = $('#grossweight').val();
		var tareweight = $('#tareweight').val();
		var batchnumberid = $('#batchnumber').attr('batchnumberid');
		var serialnumber = $('#batchnumber').val();
		var weighttime = $('#weighttime').val();
		var lighttime = $('#lighttime').val();
		var driverid = $('#driver').attr('driverid');
		var makebilltime = $('#makebilltime').val();
		var bills = $('#billcode').attr('bills');
		return {
			billid: billid,
			billdetailid: billdetailid,
			maindeduction: maindeduction,
			vehicleid: vehicleid,
			pickupquantity: pickupquantity,
			grossweight: grossweight,
			tareweight: tareweight,
			batchnumberid: batchnumberid,
			serialnumber: serialnumber,
			weighttime: str2Long(weighttime),
			lighttime: str2Long(lighttime),
			driverid: driverid,
			makebilltime: str2Long(makebilltime),
			bills: bills
		}
	}
	//校验参数是否合法
	function validate(params){
		if(!params.billid || !params.billdetailid){
			layer.msg('请选择订单！', {icon: 5}); return false;
		}
		if(!params.vehicleid){
			layer.msg('请选择车号！', {icon: 5}); return false;
		}
		if(!params.pickupquantity){
			layer.msg('预提量不能为空且不能为零！', {icon: 5}); return false;
		}
		if(!params.grossweight || params.grossweight == 0){
			layer.msg('毛重不能为空且不能为零！', {icon: 5}); return false;
		}
		if(!params.tareweight || params.tareweight == 0){
			layer.msg('皮重不能为空且不能为零！', {icon: 5}); return false;
		}
		if(parseFloat(params.netweight) < 0){
			layer.msg('毛重不能小于净重！', {icon: 5}); return false;
		}
		if(!params.batchnumberid || !params.serialnumber){
			layer.msg('请选择出厂编号！', {icon: 5}); return false;
		}
		if(!params.weighttime){
			layer.msg('请选择重车时间！', {icon: 5}); return false;
		}
		if(!params.lighttime){
			layer.msg('请选择轻车时间！', {icon: 5}); return false;
		}
		return params;
	}
	//新增销售计量
	function addSalesPoundNotePoundNote(){
		var params = getSalesPoundNoteParams();
		if(validate(params)){
			$.ajax({
				url:URL.addSalesPoundNote,
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
		}else{
			$('#addBtn').removeClass('disabled');
		}
	}
	
})(jQuery, window);