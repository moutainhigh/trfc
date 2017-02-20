;(function($,win){
	
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
		$.ajax({
			url:'/customer/findAll',
			data:null,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result){
				if(result.code == '000000'){
					if(result.data && result.data.length > 0){
						customer = result.data;
						for(var i=0;i<result.data.length;i++){
							var obj = result.data[i];
							$('.customer').append('<option value="'+obj.id+'">'+obj.name+'</option>');
						}
						$('#u_customer.customer').change(function(a,b,c){
							var id = $(this).val();
							var customer = result.data.filter(function(x){
								return id == x.id
							},id)[0];
							$('#u_channelcode').val(customer.channelcode);
							$('#u_salesmanname').val(customer.salesmanname);
							$('#u_transportationcompany').val(customer.transportationcompany);
						});
						$('#a_customer.customer').change(function(a,b,c){
							var id = $(this).val();
							var customer = result.data.filter(function(x){
								return id == x.id
							},id)[0];
							$('#a_channelcode').val(customer.channelcode);
							$('#a_salesmanname').val(customer.salesmanname);
							$('#a_transportationcompany').val(customer.transportationcompany);
						});
					}
				}else{
					layer.msg(result.error, {icon: 5});
				}
			}
		});
		$.ajax({
			url:'/materiel/findAll',
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
			url:'/warehouse/findAll',
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
			$.ajax({
				url:'/salesApplication/initAdd',
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
							$('#a_orgname').attr('orgid', data.orgid);
							$('#a_orgname').val(data.orgname);
							$('#a_creatorname').attr('creator', data.creator);
							$('#a_creatorname').val(data.creatname);
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
				var billtype = $('#a_billtype').val();billtype = $.trim(billtype);
				var billtimeStr = $('#a_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
				var customerid = $('#a_customer').val();
				var orgid = $('#a_orgname').attr('orgid');orgid = $.trim(orgid);
				var orgname = $('#a_orgname').val();orgname = $.trim(orgname);
				var creator = $('#a_creatorname').attr('creator');creator = $.trim(creator);
				var materielid = $('#a_materiel').val();
				var materielname = $('#a_materiel option:checked').text();
				var warehouseid = $('#a_warehouse').val();
				var warehousename = $('#a_warehouse option:checked').text();
				var salessum = $('#a_salessum').val();salessum = $.trim(salessum);
				var taxprice = $('#a_taxprice').val();taxprice = $.trim(taxprice);
				var taxrate = $('#a_taxrate').val();taxrate = $.trim(taxrate);
				var untaxprice = $('#a_untaxprice').val();untaxprice = $.trim(untaxprice);
				$.ajax({
					url:'/salesApplication/add',
					data:{
						code:code,
						billtype:billtype,
						billtimeStr:str2Long(billtimeStr),
						customerid:customerid,
						orgid:orgid,
						orgname:orgname,
						creator:creator,
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
				var billtype = $('#u_billtype').val();billtype = $.trim(billtype);
				var billtimeStr = $('#u_billtimeStr').val();billtimeStr = $.trim(billtimeStr);
				var customerid = $('#u_customer').val();
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
					url:'/salesApplication/update',
					data:{
						id:id,
						billtype:billtype,
						billtimeStr:str2Long(billtimeStr),
						customerid:customerid,
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
	}
	
	function getParams(){
		var params = {};
		var code = $('#s_code').val() || '';code = $.trim(code);
		var source = $('#s_source').val() || '';source = $.trim(source);
		var customerid = $('#s_customerid').val() || '';customerid = $.trim(customerid);
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
			url:'/salesApplication/page',
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
				var customer = obj.customerManageResp;
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
				var billtype = obj.billtype || '';
				var customername = customer.name || '';
				var billtimeStr = obj.billtimeStr || '';
				var salesmanname = customer.salesmanname || '';
				var orgname = obj.orgname || '';
				var transportationcompany = customer.transportationcompany || '';
				var creatorname = obj.creatorname || '';
				var createtimeStr = obj.createtimeStr || '';
				var auditname = obj.auditname || '';
				var audittimeStr = obj.audittimeStr || '';
				var channelcode = customer.channelcode || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+status+'</td>')
						.append('<td>'+source+'</td>')
						.append('<td>'+billtype+'</td>')
						.append('<td>'+customername+'</td>')
						.append('<td>'+billtimeStr+'</td>')
						.append('<td>'+salesmanname+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+transportationcompany+'</td>')
						.append('<td>'+creatorname+'</td>')
						.append('<td>'+createtimeStr+'</td>')
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
				var obj = $(this).closest('tr').data();
				showUpdate(obj);
			});
			$('#dataBody tr').find('.auditBtn').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				audit(obj);
			});
			$('#dataBody tr').find('.unauditBtn').off('click').on('click',function(){
				var obj = $(this).closest('tr').data();
				unaudit(obj);
			});
			$('#dataBody tr').find('.deleteBtn').off('click').on('click',function(){
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
			layer.msg('暂无数据');
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
		var billtype = obj.billtype || '';
		var billtimeStr = obj.billtimeStr || '';
		var customerid = obj.customerManageResp.id || '';
		var customername = obj.customerManageResp.name || '';
		var channelcode = obj.customerManageResp.channelcode || '';
		var orgname = obj.orgname || '';
		var salesmanname = obj.customerManageResp.salesmanname || '';
		var transportationcompany = obj.customerManageResp.transportationcompany || '';
		var creatorname = obj.creatorname || '';
		$('#u_id').val(id);
		$('#u_code').val(code);
		$('#u_billtype').val(billtype);
		$('#u_billtimeStr').val(billtimeStr);
		$('#u_customer').val(customerid);
		$('#u_customername').val(customername);
		$('#u_channelcode').val(channelcode);
		$('#u_orgname').val(orgname);
		$('#u_salesmanname').val(salesmanname);
		$('#u_transportationcompany').val(transportationcompany);
		$('#u_creatorname').val(creatorname);
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
		confirmOperation('您确定要审核么？', '/salesApplication/audit', {
			id:obj.id
		});
	}
	//反审
	function unaudit(obj){
		confirmOperation('您确定要反审么？', '/salesApplication/unaudit', {
			id:obj.id
		});
	}
	//删除销售申请单
	function deleteData(obj){
		if(obj.status == '1'){
			layer.msg('已审核的单据，不能刪除！', {icon: 5});
			return;
		}
		confirmOperation('删除操作不可恢复，您确定要继续么？', '/salesApplication/delete', {
			id:obj.id
		});
	}
	//显示更多
	function showMore(obj){
		$('#moreBody').empty();
		var detailResp = obj.detailResp;
		if(detailResp){
			$('<tr>').append('<td>'+detailResp.materielname+'</td>')
			.append('<td>'+detailResp.salessum+'</td>')
			.append('<td>'+detailResp.taxprice+'</td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td></td>')
			.append('<td>'+detailResp.remarks+'</td>')
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
		var billtype = obj.billtype || '';
		var transportationcompany = customer.transportationcompany || '';
		var billtimeStr = obj.billtimeStr || '';
		var customername = customer.name || '';
		var orgname = obj.orgname || '';
		var departmentname = obj.departmentname || '';
		var salesmanname = customer.salesmanname || '';
		var creatorname = obj.creatorname || '';
		var createtimeStr = obj.createtimeStr || '';
		var auditname = obj.auditname || '';
		$('#v_code').val(code);
		$('#v_source').val(source);
		$('#v_billtype').val(billtype);
		$('#v_transportationcompany').val(transportationcompany);
		$('#v_billtimeStr').val(billtimeStr);
		$('#v_customername').val(customername);
		$('#v_orgname').val(orgname);
		$('#v_departmentname').val(departmentname);
		$('#v_salesmanname').val(salesmanname);
		$('#v_creatorname').val(creatorname);
		$('#v_createtimeStr').val(createtimeStr);
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