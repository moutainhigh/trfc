;(function($, win){
	//访问路径
	var URL = {
			page: '/trfc/purchaseArrive/page',
			addView: '/trfc/purchaseArrive/addView',
			updateView: '/trfc/purchaseArrive/updateView',
			audit: '/trfc/purchaseArrive/audit',
			unaudit: '/trfc/purchaseArrive/unaudit',
			invalid: '/trfc/purchaseArrive/invalid',
			outfactory: '/trfc/purchaseArrive/outfactory'
	};
	//初始化页面按钮绑定事件
	initBindEvent();
	//初始化加载页面数据
	initPageList(1);
	//页面按钮绑定事件
	function initBindEvent(){
		$('#refreshBtn').off('click').on('click', function(){
			initPageList(1);
		});
		$('#searchBtn').off('click').on('click', function(){
			initPageList(1);
		});
		$('#addBtn').off('click').on('click', function(){
			win.location.href = URL.addView;
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				alert('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				initPageList(pageNo);
			}
		});
		$('#pageSize').change(function(){
			initPageList(1);
		});
	}
	//日期字符串转换成时间戳
	function str2Long(dateStr){
		return Date.parseYMD_HMS(dateStr).getTime();
	}
	//获取查询条件
	function getParams(){
		var billcode = $('#billcode').val();billcode = $.trim(billcode);
		var code = $('#code').val();code = $.trim(code);
		var supplierid = $('#supplier').val();supplierid = $.trim(supplierid);
		var vehicleid = $('#vehicle').val();vehicleid = $.trim(vehicleid);
		var auditstatus = $('#auditstatus').val();auditstatus = $.trim(auditstatus);
		var materielid = $('#materiel').val();materielid = $.trim(materielid);
		var driverid = $('#driver').val();driverid = $.trim(driverid);
		var source = $('#source').val();source = $.trim(source);
		var status = $('#status').val();status = $.trim(status);
		var starttime = $('#starttime').val();starttime = $.trim(starttime);
		var endtime = $('#endtime').val();endtime = $.trim(endtime);
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		return {
			billcode:billcode,
			code:code,
			supplierid:supplierid,
			vehicleid:vehicleid,
			auditstatus:auditstatus,
			materielid:materielid,
			driverid:driverid,
			source:source,
			status:status,
			starttime:str2Long(starttime),
			endtime:str2Long(endtime),
			pageSize:pageSize
		}
	}
	//根据条件分页查询订单列表
	function initPageList(pageNo){
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
					    callback: function(){
					    	initPageList(pageNo+1);
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
	//解析数据加载列表
	function renderHtml(data){
		var list = data.list;
		$('#dataBody').empty();
		if(list && list.length > 0){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var purchaseApplication = obj.purchaseApplicationResp;
				var purchaseApplicationDetail = obj.purchaseApplicationDetailResp;
				var code = obj.code || '';
				var auditstatus = '';
				switch (obj.auditstatus) {
				case '0': auditstatus = '未审核'; break;
				case '1': auditstatus = '已审核'; break;
				default: break;
				}
				var source = '';
				switch (obj.source) {
				case '0': source = '业务平台'; break;
				case '1': source = '客商平台'; break;
				case '2': source = '客商APP'; break;
				default: break;
				}
				var status = '';
				switch (obj.status) {
				case '0': status = '未入厂'; break;
				case '1': status = '空车'; break;
				case '2': status = '重车'; break;
				case '3': status = '作废'; break;
				case '4': status = '发卡'; break;
				case '5': status = '出厂'; break;
				case '6': status = '入厂'; break;
				case '7': status = '装车'; break;
				default: break;
				}
				var vehicleno = obj.vehicleno || '';
				var billcode = obj.billcode || '';
				var suppliername = purchaseApplication.suppliername || '';
				var materielname = purchaseApplicationDetail.materielname || '';
				var minemouthname = purchaseApplication.minemouthname || '';
				var makebilltimeStr = obj.makebilltimeStr || '';
				var billtimeStr = purchaseApplication.makebilltimeStr || '';
				var abnormalpersonname = obj.abnormalpersonname || '';
				var abnormaltimeStr = obj.abnormaltimeStr || '';
				var remark = obj.remark || '';
				var supplierremark = purchaseApplication.supplierremark || '';
				$('<tr>').append('<td>'+(i+1)+'</td>').append('<td>'+code+'</td>')
						.append('<td>'+auditstatus+'</td>').append('<td>'+source+'</td>')
						.append('<td>'+status+'</td>').append('<td>'+vehicleno+'</td>')
						.append('<td>'+billcode+'</td>').append('<td>'+suppliername+'</td>')
						.append('<td>'+materielname+'</td>').append('<td>'+minemouthname+'</td>')
						.append('<td>'+makebilltimeStr+'</td>').append('<td>'+billtimeStr+'</td>')
						.append('<td>'+abnormalpersonname+'</td>').append('<td>'+abnormaltimeStr+'</td>')
						.append('<td>'+remark+'</td>').append('<td>'+supplierremark+'</td>')
						.append('<td><span><i class="iconfont update" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></span>'
									+'<span><i class="iconfont audit" data-toggle="tooltip" data-placement="left" title="审核">&#xe692;</i></span>'
									+'<span><i class="iconfont unaudit" data-toggle="tooltip" data-placement="left" title=" 反审">&#xe651;</i></span>'
									+'<span><i class="iconfont invalid" data-toggle="tooltip" data-placement="left" title=" 作废">&#xe60c;</i></span>'
									+'<span><i class="iconfont outfactory" data-toggle="tooltip" data-placement="left" title=" 出厂">&#xe63c;</i></span></td>')
						.data(obj).appendTo('#dataBody');
			}
			tableBindEvent();
		}else{
			layer.msg('暂无数据');
		}
	}
	//绑定列表操作按钮事件
	function tableBindEvent(){
		$('#dataBody>tr').off('click').on('click',function(){
			var obj = $(this).data();
			showMore(obj);
		});
		$('#dataBody>tr').off('dblclick').on('dblclick',function(){
			var obj = $(this).data();
			//跳转到详情页面
			window.location.href = '/trfc/purchaseArrive/detailView?id='+obj.id;
		});
		$('#dataBody>tr').find('.update').off('click').on('click',function(){
			var obj = $(this).closest('tr').data();
			//跳转到修改页面
			window.location.href = '/trfc/purchaseArrive/updateView?id='+obj.id;
		});
		$('#dataBody>tr').find('.audit').off('click').on('click',function(){
			var obj = $(this).closest('tr').data();
			auditOperation(obj);
		});
		$('#dataBody>tr').find('.unaudit').off('click').on('click',function(){
			var obj = $(this).closest('tr').data();
			unauditOperation(obj);
		});
		$('#dataBody>tr').find('.invalid').off('click').on('click',function(){
			var obj = $(this).closest('tr').data();
			invalidOperation(obj);
		});
		$('#dataBody>tr').find('.outfactory').off('click').on('click',function(){
			var obj = $(this).closest('tr').data();
			outfactoryOperation(obj);
		});
	}
	//显示更多
	function showMore(obj){
		var purchaseApplication = obj.purchaseApplicationResp || {};
		var purchaseApplicationDetail = obj.purchaseApplicationDetailResp || {};
		$('#vehicleno, #vehicleno2').html(obj.vehicleno || '');
		$('#arrivalamount').html(obj.arrivalamount || '');
		$('#drivername').html(obj.drivername || '');
		$('#driveridentityno').html(obj.driveridentityno || '');
		$('#makebilltimeStr').html(obj.makebilltimeStr || '');
		$('#makebillname').html(obj.makebillname || '');
		$('#billno').html(obj.billcode || '');
		$('#suppliername').html(purchaseApplication.suppliername || '');
		$('#materielname').html(purchaseApplicationDetail.materielname || '');
		$('#orgname').html(purchaseApplication.orgname || '');
		$('#purchasesum').html(purchaseApplicationDetail.purchasesum || '');
		$('#billtime').html(purchaseApplication.makebilltimeStr || '');
		$('#ind_tab').show();
	}
	//审核
	function auditOperation(obj){
		if(obj.auditstatus == '1'){
			layer.msg('此单据已审核，无法继续审核！', {icon: 5});
			return;
		}
		if(obj.status == '4'){
			layer.msg('此单据已发卡，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('此单据已出厂，无法反审操作！', {icon: 5});
			return;
		}
		if(obj.status == '6'){
			layer.msg('此单据已入厂，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '7'){
			layer.msg('此单据已装货，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '3'){
			layer.msg('此单据已作废，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('此单据已使用，无法审核操作！', {icon: 5});
			return;
		}
		if(obj.status == '1'){
			layer.msg('此单据已核销，无法审核操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要审核吗？', URL.audit, {id: obj.id});
	}
	//反审
	function unauditOperation(obj){
		if(obj.auditstatus == '0'){
			layer.msg('此单据未审核,无法继续反审!', {icon: 5});
			return;
		}
		if(obj.status == '7'){
			layer.msg('此单据已装货,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '6'){
			layer.msg('此单据已入厂,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('此单据已出厂,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '4'){
			layer.msg('此单据已发卡,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '3'){
			layer.msg('此单据已作废,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('此单据已使用,无法反审操作!', {icon: 5});
			return;
		}
		if(obj.status == '1'){
			layer.msg('此单据已核销,无法反审操作!', {icon: 5});
			return;
		}
		confirmOperation('您确定要反审吗？', URL.unaudit, {id: obj.id});
	}
	//作废
	function invalidOperation(obj){
		if(obj.status == '3'){
			layer.msg('数据已作废，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('数据已出厂，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status == '2'){
			layer.msg('数据已过重车，不能进行作废操作！', {icon: 5});
			return;
		}
		if(obj.status != '0' && obj.status != '4'){
			layer.msg('数据已入厂，不能进行作废操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要作废吗？', URL.invalid, {id: obj.id});
	}
	//强制出厂
	function outfactoryOperation(obj){
		if(obj.status == '3'){
			layer.msg('数据已作废，不能进行出厂操作！', {icon: 5});
			return;
		}
		if(obj.status == '5'){
			layer.msg('数据已出厂，不能进行出厂操作！', {icon: 5});
			return;
		}
		if(obj.status != '2'){
			layer.msg('数据未过重车，不能进行出厂操作！', {icon: 5});
			return;
		}
		confirmOperation('您确定要强制出厂吗？', URL.outfactory, {id: obj.id});
	}
	//confirm选择公共方法
	function confirmOperation(confirmContent, url, params){
		layer.confirm(confirmContent, {
			btn: ['确认','取消'] //按钮
		}, function(){
			$.ajax({
				url:url,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.reload();
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		});
	}
})(jQuery, window);