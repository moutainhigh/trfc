;(function($,win){
	//请求路径
	var URL = {
			findCAllUrl:"/trfc/customer/findAll",
			findMAllUrl:"/trfc/materiel/findAll",
			findWAllUrl:"/trfc/warehouse/findAll",
			initAddUrl:"/trfc/salesApplication/initAdd",
			
			addBtnUrl:"/trfc/salesApplication/add",
			updateBtnUrl:"/trfc/salesApplication/update",
			pageUrl:"/trfc/salesApplication/page",
			auditUrl:"/trfc/salesApplication/audit",
			unauditUrl:"/trfc/salesApplication/unaudit",
			deleteUrl:"/trfc/salesApplication/delete",
	};
	var customer = null;
	var materiel = null;
	var warehouse = null;
	
	function str2Long(dateStr){
		var time = '';
		if(dateStr){
			var date = new Date(dateStr);
			time = date.getTime();
		}
		return time;
	}
	
	function long2String(time){
		var dateStr = '';
		if(time){
			var date = new Date(time);
			dateStr = date.format("yyyy-MM-dd HH:mm:ss");
		}
		return dateStr;
	}
	
	init();
	function init(){
		initSelect();
		bindEvent();
		queryData(1);
	}
	function initSelect(){
//		$.ajax({
//			url:URL.findCAllUrl,
//			data:null,
//			async:true,
//			cache:false,
//			dataType:'json',
//			type:'post',
//			success:function(result){
//				if(result.code == '000000'){
//					if(result.data && result.data.length > 0){
//						customer = result.data;
//						for(var i=0;i<result.data.length;i++){
//							var obj = result.data[i];
//							$('.customer').append('<option value="'+obj.id+'">'+obj.name+'</option>');
//						}
//						$('#u_customer.customer').change(function(a,b,c){
//							var id = $(this).val();
//							var customer = result.data.filter(function(x){
//								return id == x.id
//							},id)[0];
//							$('#u_channelcode').val(customer.channelcode);
//							$('#u_salesmanname').val(customer.salesmanname);
//							$('#u_transportationcompany').val(customer.transportationcompany);
//						});
//						$('#a_customer.customer').change(function(a,b,c){
//							var id = $(this).val();
//							var customer = result.data.filter(function(x){
//								return id == x.id
//							},id)[0];
//							$('#a_channelcode').val(customer.channelcode);
//							$('#a_salesmanname').val(customer.salesmanname);
//							$('#a_transportationcompany').val(customer.transportationcompany);
//						});
//					}
//				}else{
//					layer.msg(result.error, {icon: 5});
//				}
//			}
//		});
		$.ajax({
			url:URL.findMAllUrl,
			data:null,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					if(result.data && result.data.length > 0){
						materiel = result.data;
						for(var i=0;i<result.data.length;i++){
							var obj = result.data[i];
							$('.materiel').append('<option value="'+obj.id+'">'+obj.name+'</option>');
						}
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
		$.ajax({
			url:URL.findWAllUrl,
			data:null,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					if(result.data && result.data.length > 0){
						warehouse = result.data;
						for(var i=0;i<result.data.length;i++){
							var obj = result.data[i];
							$('.warehouse').append('<option value="'+obj.id+'">'+obj.name+'</option>');
						}
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
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
		$('#addBtn').off('click').on('click', function(){
			layer.closeAll();
			$.ajax({
				url:URL.initAddUrl,
				data:null,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						var data = result.data;
						if(data){
							$('#a_code').val(data.code);
							$('#a_billtimeStr').val(data.nowDate);
							addChangeNum($('#a_salessum, #a_taxprice, #a_taxrate, #a_untaxprice'));
							$('#addView').modal();
						}else{
							alert("初始化失败，请稍后重试！");
						}
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		})
		$('#addCommitBtn').off('click').on('click', function(){
			if($('#addView').is(':visible')){
				var code = $('#a_code').val();code = $.trim(code);
				var billtypeid = $('#a_billtype').val();billtypeid = $.trim(billtypeid);
				var billtypename = $('#a_billtype>option:checked').text();billtypename = $.trim(billtypename);
				var billtimeStr = $('#a_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
				var customerid = $('#a_customer').val();customerid = $.trim(customerid);
				var customername = $('#a_customer>option:checked').text();customername = $.trim(customername);
				var channelcode = $('#a_channelcode').val();channelcode = $.trim(channelcode);
				var salesmanid = $('#a_salesmanname').attr('salesmanid');salesmanid = $.trim(salesmanid);
				var salesmanname = $('#a_salesmanname').val();salesmanname = $.trim(salesmanname);
				var transportcompanyid = $('#a_transportationcompany').attr('transportcompanyid');transportcompanyid = $.trim(transportcompanyid);
				var transportcompanyname = $('#a_transportationcompany').val();transportcompanyname = $.trim(transportcompanyname);
				var orgid = $('#a_orgname').attr('orgid');orgid = $.trim(orgid);
				var orgname = $('#a_orgname').val();orgname = $.trim(orgname);
				var makerid = $('#a_makername').attr('makerid');makerid = $.trim(makerid);
				var makebillname = $('#a_makebillname').val();makebillname = $.trim(makebillname);
				var materielid = $('#a_materiel').val();
				var materielname = $('#a_materiel option:checked').text();
				var warehouseid = $('#a_warehouse').val();
				var warehousename = $('#a_warehouse option:checked').text();
				var salessum = $('#a_salessum').val();salessum = $.trim(salessum);
				var taxprice = $('#a_taxprice').val();taxprice = $.trim(taxprice);
				var taxrate = $('#a_taxrate').val();taxrate = $.trim(taxrate);
				var untaxprice = $('#a_untaxprice').val();untaxprice = $.trim(untaxprice);
				$.ajax({
					url:URL.addBtnUrl,
					data:{
						code:code,
						billtypeid:billtypeid,
						billtypename:billtypename,
						billtime:str2Long(billtimeStr),
						customerid:customerid,
						customername:customername,
						channelcode:channelcode,
						salesmanid:salesmanid,
						salesmanname:salesmanname,
						transportcompanyid:transportcompanyid,
						transportcompanyname:transportcompanyname,
						orgid:orgid,
						orgname:orgname,
						makerid:makerid,
						makebillname:makebillname,
						materielid:materielid,
						materielname:materielname,
						warehouseid:warehouseid,
						warehousename:warehousename,
						salessum:salessum,
						taxprice:taxprice,
						taxrate:taxrate,
						untaxprice:untaxprice
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload();
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		});
		$('#updateCommitBtn').off('click').on('click',function(){
			if($('#updateView').is(':visible')){
				var id = $('#u_id').val();
				var billtypeid = $('#u_billtype').val();billtypeid = $.trim(billtypeid);
				var billtypename = $('#u_billtype>option:checked').text();billtypename = $.trim(billtypename);
				var billtimeStr = $('#u_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
				var customerid = $('#u_customer').val();customerid = $.trim(customerid);
				var customername = $('#u_customer>option:checked').text();customername = $.trim(customername);
				var channelcode = $('#u_channelcode').val();channelcode = $.trim(channelcode);
				var salesmanid = $('#u_salesmanname').attr('salesmanid');salesmanid = $.trim(salesmanid);
				var salesmanname = $('#u_salesmanname').val();salesmanname = $.trim(salesmanname);
				var transportcompanyid = $('#u_salesmanname').attr('transportcompanyid');transportcompanyid = $.trim(transportcompanyid);
				var transportcompanyname = $('#u_salesmanname').val();transportcompanyname = $.trim(transportcompanyname);
				var detailid = $('#u_detailid').val();
				var materielid = $('#u_materiel').val();
				var materielname = $('#u_materiel option:checked').text();
				var warehouseid = $('#u_warehouse').val();
				var warehousename = $('#u_warehouse option:checked').text();
				var salessum = $('#u_salessum').val();salessum = $.trim(salessum);
				var taxprice = $('#u_taxprice').val();taxprice = $.trim(taxprice);
				var taxrate = $('#u_taxrate').val();taxrate = $.trim(taxrate);
				var untaxprice = $('#u_untaxprice').val();untaxprice = $.trim(untaxprice);
				$.ajax({
					url:URL.updateBtnUrl,
					data:{
						id:id,
						billtypeid:billtypeid,
						billtypename:billtypename,
						billtimeStr:str2Long(billtimeStr),
						customerid:customerid,
						customername:customername,
						channelcode:channelcode,
						salesmanid:salesmanid,
						salesmanname:salesmanname,
						transportcompanyid:transportcompanyid,
						transportcompanyname:transportcompanyname,
						detailid:detailid,
						materielid:materielid,
						materielname:materielname,
						warehouseid:warehouseid,
						warehousename:warehousename,
						salessum:salessum,
						taxprice:taxprice,
						taxrate:taxrate,
						untaxprice:untaxprice
					},
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload();
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			}
		});
		$('#s_customerid').off('click').on('click',function(){
			var _this = this;
			initCustomer(function(obj){
				$(_this).val(obj.name).attr('customerid',obj.id);
			});
		});
		/**
		 * 找鑫鹏确认客户区域码业务员等关系
		 */
		$('#a_customer').off('click').on('click',function(){
			var _this = this;
			initCustomer(function(obj){
				$(_this).val(obj.name).attr('customerid',obj.id);
			});
		});
		$('#u_customer').off('click').on('click',function(){
			var _this = this;
			initCustomer(function(obj){
				$(_this).val(obj.name).attr('customerid',obj.id);
			});
		});
	}
	
	function getParams(){
		var params = {};
		var code = $('#s_code').val() || '';code = $.trim(code);
		var source = $('#s_source').val() || '';source = $.trim(source);
		var customerid = $('#s_customerid').attr('customerid') || '';customerid = $.trim(customerid);
		var starttime = $('#s_starttime').val() || '';starttime = $.trim(starttime);
		var endtime = $('#s_endtime').val() || '';endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val() || '';pageSize = $.trim(pageSize);
		return {
			code:code,
			source:source,
			customerid:customerid,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		};
	}
	
	function pageCallback(pageNo){
		queryData(pageNo+1);
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
				}else{
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}
	
	function renderHtml(data){
		$('#dataBody').empty();
		$('#dataMore').hide()
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var status = obj.status || '';
				switch (status) {
				case '0':
					status = '未审核';
					break;
				case '1':
					status = '已审核';
					break;
				default:
					status = '';
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
				var customername = obj.customername || '';
				var salesmanname = obj.salesmanname || '';
				var transportcompanyname = obj.transportcompanyname || '';
				var channelcode = obj.channelcode || '';
				var billtimeStr = obj.billtimeStr || '';
				var orgname = obj.orgname || '';
				var makebillname = obj.makebillname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+status+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+billtypename+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+transportcompanyname+'</td>')
						.append('<td>'+makebillname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>')
						.append('<td>'+auditname+'</td>')
						.append('<td>'+audittimeStr+'</td>')
						.append('<td>'+channelcode+'</td>')
						.append('<td><span class="updateBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
			                    +'<span class="auditBtn"><a><i class="iconfont " data-toggle="tooltip" data-placement="left" title="审核">&#xe692;</i></a></span>'
			                    +'<span class="unauditBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></a></span>'
			                    +'<span class="deleteBtn"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></td>')
						.data(obj)
						.appendTo('#dataBody');
			}
			$('#dataBody tr').find('.updateBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				showUpdate(obj);
			});
			$('#dataBody tr').find('.auditBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				audit(obj);
			});
			$('#dataBody tr').find('.unauditBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				unaudit(obj);
			});
			$('#dataBody tr').find('.deleteBtn').off('click').on('click',function(){
				layer.closeAll();
				var obj = $(this).closest('tr').data();
				deleteData(obj);
			});
			$('#dataBody tr').off('click').on('click',function(){
				var obj = $(this).data();
				showMore(obj);
			});
			$('#dataBody tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				showDetail(obj);
			});
		}else{
			alert('暂无数据');
			$('#dataMore').hide();
		}
	}
	//修改
	function showUpdate(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能修改！', {icon: 5});
			return;
		}
		var id = obj.id || '';
		var code = obj.code || '';
		var billtypeid = obj.billtypeid || '';
		var billtypename = obj.billtypename || '';
		var billtimeStr = obj.billtimeStr || '';
		var customerid = obj.customerid || '';
		var customername = obj.customername || '';
		var channelcode = obj.channelcode || '';
		var salesmanid = obj.salesmanid || '';
		var salesmanname = obj.salesmanname || '';
		var orgid = obj.orgid || '';
		var orgname = obj.orgname || '';
		var transportcompanyid = obj.transportcompanyid || '';
		var transportcompanyname = obj.transportcompanyname || '';
		var makerid = obj.makerid || '';
		var makebillname = obj.makebillname || '';
		$('#u_id').val(id);
		$('#u_code').val(code);
		$('#u_billtype').val(billtypeid);
		$('#u_billtimeStr').val(billtimeStr);
		$('#u_customer').val(customerid);
		$('#u_channelcode').val(channelcode);
		$('#u_orgname').val(orgname);
		$('#u_salesmanname').val(salesmanname).attr('salesmanid',salesmanid);
		$('#u_transportationcompany').val(transportcompanyname).attr('transportcompanyid',transportcompanyid);
		$('#u_creatorname').val(makebillname).attr('makerid',makerid);
		var detailResp = obj.detailResp;
		if(detailResp){
			$('#u_detailid').val(detailResp.id);
			$('#u_materiel').val(detailResp.materielid);
			$('#u_warehouse').val(detailResp.warehouseid);
			$('#u_salessum').val(detailResp.salessum);
			$('#u_taxprice').val(detailResp.taxprice);
			var taxpricesum = (detailResp.salessum * detailResp.taxprice);
			$('#u_taxpricesum').val(taxpricesum.toFixed(2));
			$('#u_taxrate').val(detailResp.taxrate);
			$('#u_untaxprice').val(detailResp.untaxprice);
			var untaxpricesum = (detailResp.salessum * detailResp.untaxprice);
			$('#u_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#u_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
			updateChangeNum($('#u_salessum, #u_taxprice, #u_taxrate, #u_untaxprice'));
		}
		$('#updateView').modal();
	}
	
	function addChangeNum(_$){
		_$.off('input propertychange').on('input propertychange', function() {
			var salessum = $('#a_salessum').val();
			var taxprice = $('#a_taxprice').val();
			var taxrate = $('#a_taxrate').val();
			var untaxprice = $('#a_untaxprice').val();
			var taxpricesum = (salessum * taxprice);
			var untaxpricesum = (salessum * untaxprice);
			$('#a_taxpricesum').val(taxpricesum.toFixed(2));
			$('#a_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#a_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
		});
	}
	
	function updateChangeNum(_$){
		_$.off('input propertychange').on('input propertychange', function() {
			var salessum = $('#u_salessum').val();
			var taxprice = $('#u_taxprice').val();
			var taxrate = $('#u_taxrate').val();
			var untaxprice = $('#u_untaxprice').val();
			var taxpricesum = (salessum * taxprice);
			var untaxpricesum = (salessum * untaxprice);
			$('#u_taxpricesum').val(taxpricesum.toFixed(2));
			$('#u_untaxpricesum').val(untaxpricesum.toFixed(2));
			$('#u_taxratesum').val((taxpricesum - untaxpricesum).toFixed(2));
		});
	}
	
	//审核
	function audit(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能继续审核！', {icon: 5});
			return;
		}
		confirmOperation('您确定要审核么？', URL.auditUrl, {
			id:obj.id
		});
	}
	//反审
	function unaudit(obj){
		confirmOperation('您确定要反审么？', URL.unauditUrl, {
			id:obj.id
		});
	}
	//删除销售申请单
	function deleteData(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能刪除！', {icon: 5});
			return;
		}
		confirmOperation('删除操作不可恢复，您确定要继续么？', URL.deleteUrl, {
			id:obj.id
		});
	}
	//显示更多
	function showMore(obj){
		$('#moreBody').empty();
		var detailResp = obj.detailResp;
		var materiel = detailResp.materiel;
		if(detailResp){
			var materielname = '';
			if(materiel){
				materielname = materiel.name;
			}
			$('<tr>').append('<td>'+(materielname || '')+'</td>')
			.append('<td>'+(detailResp.salessum || '')+'</td>')
			.append('<td>'+(detailResp.taxprice || '')+'</td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td>'+(detailResp.remarks || '')+'</td>')
			.appendTo('#moreBody');
		}else{
			$('#moreBody').append('<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>');
		}
		$('#dataMore').show();
	}
	//显示详情
	function showDetail(obj){
		var customer = obj.customerManageResp;
		var code = obj.code || '';
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
		var transportcompanyname = obj.transportcompanyname || '';
		var customername = obj.customername || '';
		var salesmanname = obj.salesmanname || '';
		var billtimeStr = obj.billtimeStr || '';
		var orgname = obj.orgname || '';
		var departmentname = obj.departmentname || '';
		var makebillname = obj.makebillname || '';
		var makebilltimeStr = obj.makebilltimeStr || '';
		var auditname = obj.auditname || '';
		$('#v_code').val(code);
		$('#v_source').val(source);
		$('#v_billtype').val(billtypename);
		$('#v_transportationcompany').val(transportcompanyname);
		$('#v_billtimeStr').val(billtimeStr);
		$('#v_customername').val(customername);
		$('#v_orgname').val(orgname);
		$('#v_departmentname').val(departmentname);
		$('#v_salesmanname').val(salesmanname);
		$('#v_creatorname').val(makebillname);
		$('#v_createtimeStr').val(makebilltimeStr);
		$('#v_auditname').val(auditname);
		//订单明细
		$('#detailTabBody').empty();
		var detailResp = obj.detailResp;
		if(detailResp){
			$('<tr>').append('<td>'+(detailResp.materielname || '')+'</td>')
			.append('<td>'+(detailResp.salessum || '')+'</td>')
			.append('<td>'+(detailResp.remarks || '')+'</td>')
			.appendTo('#detailTabBody');
		}else{
			$('#detailTabBody').append('<tr><td></td><td></td><td></td></tr>');
		}
		switch (obj.status) {
		case '0':
			$('#shImg').attr('src','/resources/images/un_sh.png');
			break;
		case '1':
			$('#shImg').attr('src','/resources/images/sh.png');
			break;
		default:
			break;
		}
		$('#detailView').modal();
	}
	
	function confirmOperation(confirmContent, url, params){
		$.confirm({
		    title: '提示',
		    content: confirmContent,
		    columnClass: 'col-md-4 col-md-offset-4',
		    confirmButton: '确定',
		    cancelButton: '取消',
		    confirm: function(){
		    	$.ajax({
					url:url,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							win.location.reload();
						}else{
							layer.msg(result.error, {icon: 5});
						}
						layer.close(index);
					}
				});
		    }
		});
	}
	
	
})(jQuery, window);