	//请求路径
var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
	main:{},
	util:{},
	URL:{
		pageUrl:"/trfc/salesArrive/page",
		updateViewUrl:"/trfc/salesArrive/updateView",
		auditUrl:"/trfc/salesArrive/audit",
		unauditUrl:"/trfc/salesArrive/unaudit",
		invalidUrl:"/trfc/salesArrive/invalid",
		outfactoryUrl:"/trfc/salesArrive/outfactory"
	}
}
PAGE.mod.util = {
		parseStr2Long: function(str){
			if(str){
				var date = new Date(str);
				return date.getTime();
			}
			return undefined;
		}
}
PAGE.mod.main = {
		top: PAGE.mod,
		init:function(){
			this.bindEvent();
			this.getData($.trim($('#jumpPageNo').val()) || 1);
		},
		bindEvent:function(){
			var _this = this;
			$('#refreshBtn').off('click').on('click',function(){
				_this.getData(1);
			});
			$('#searchBtn').off('click').on('click',function(){
				_this.getData(1);
			});
			$('#jumpPageNoBtn').off('click').on('click',function(){
				var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
				var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
				if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
					alert('此处必须为1-'+pageMaxNo+'的数字');
					$('input#jumpPageNo').val('');
				}else{
					$('input#jumpPageNo').val(pageNo);
					_this.getData(pageNo);
				}
			});
			$('#pageSize').change(function(){
				_this.getData(1);
			});
//			$('#addBtn').off('click').on('click',function(){
//				
//			});
		},
		getParams:function(){
			var _this = this;
			var billcode = $('#billcode').val() || ''; billcode = $.trim(billcode);
			var code = $('#code').val() || ''; code = $.trim(code);
			var customerid = $('#customerid').val() || ''; customerid = $.trim(customerid);
			var vehicleid = $('#vehicleid').val() || ''; vehicleid = $.trim(vehicleid);
			var auditstatus = $('#auditstatus').val() || ''; auditstatus = $.trim(auditstatus);
			var materielid = $('#materielid').val() || ''; materielid = $.trim(materielid);
			var driverid = $('#driverid').val() || ''; driverid = $.trim(driverid);
			var source = $('#source').val() || ''; source = $.trim(source);
			var status = $('#status').val() || ''; status = $.trim(status);
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
				starttime:_this.top.util.parseStr2Long(starttime),
				endtime:_this.top.util.parseStr2Long(endtime),
				pageSize:pageSize
			}
		},
		getData:function(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var _this = this;
			var params = _this.getParams();
			params.pageNo = pageNo;
			$.ajax({
				url:_this.top.URL.pageUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						_this.renderHtml(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(){
						    	_this.getData(pageNo+1);
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
		},
		renderHtml:function(data){
			var _this = this;
			$('#dataBody').empty();
			$('#dataMore').hide()
			var list = data.list;
			if(list && list.length>0){
				for(var i=0;i<list.length;i++){
					var obj = list[i] || '';
					var salesApplication = obj.salesApplication;
					var salesApplicationDetail = salesApplication.detailResp;
					var customer = salesApplication.customerManageResp;
					var code = obj.code || '';
					var auditstatus = obj.auditstatus || '';
					switch (auditstatus) {
					case '0': auditstatus = '未审核'; break;
					case '1': auditstatus = '已审核'; break;
					default: auditstatus = ''; break;
					}
					var source = obj.source || '';
					switch (source) {
					case '0': source = '业务平台'; break;
					case '1': source = '客商平台'; break;
					case '2': source = '客商APP'; break;
					default: source = ''; break;
					}
					var status = obj.status || '';
					switch (status) {
					case '0': status = '未入厂'; break;
					case '1': status = '空车'; break;
					case '2': status = '重车'; break;
					case '3': status = '作废'; break;
					case '4': status = '发卡'; break;
					case '5': status = '出厂'; break;
					case '6': status = '入厂'; break;
					case '7': status = '装车'; break;
					default: status = ''; break;
					}
					var vehicleno = obj.vehicleno || '';
					var billcode = obj.billcode || '';
					var customername = customer.name || '';
					var materiel = salesApplicationDetail.materiel;
					var materielname = materiel.name || '';
					var billtimeStr = salesApplication.billtimeStr || '';
					var channelcode = customer.channelcode || '';
					var makebillname = obj.makebillname || '';
					var makebilltimeStr = obj.makebilltimeStr || '';
					var abnormalpersonname = obj.abnormalpersonname || '';
					var abnormaltimeStr = obj.abnormaltimeStr || '';
					var remarks = obj.remarks || '';
					$('<tr>').append('<td>'+(i+1)+'</td>')
							.append('<td>'+code+'</td>')
							.append('<td>'+auditstatus+'</td>')
							.append('<td>'+source+'</td>')
							.append('<td>'+status+'</td>')
							.append('<td>'+vehicleno+'</td>')
							.append('<td>'+billcode+'</td>')
							.append('<td>'+customername+'</td>')
							.append('<td>'+materielname+'</td>')
							.append('<td>'+billtimeStr+'</td>')
							.append('<td>'+channelcode+'</td>')
							.append('<td>'+makebilltimeStr+'</td>')
							.append('<td>'+makebillname+'</td>')
							.append('<td>'+abnormalpersonname+'</td>')
							.append('<td>'+abnormaltimeStr+'</td>')
							.append('<td>'+remarks+'</td>')
							.append('<td><span><i class="iconfont update" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></span>'
						                +'<span><i class="iconfont audit" data-toggle="tooltip" data-placement="left" title="审核">&#xe692;</i></span>'
						                +'<span><i class="iconfont unaudit" data-toggle="tooltip" data-placement="left" title="反审">&#xe651;</i></span>'
						                +'<span><i class="iconfont invalid" data-toggle="tooltip" data-placement="left" title="作废">&#xe60c;</i></span>'
						                +'<span><i class="iconfont outfactory" data-toggle="tooltip" data-placement="left" title="出厂">&#xe63c;</i></span></td>')
							.data(obj)
							.appendTo('#dataBody');
				}
				$('#dataBody').find('i.update').off('click').on('click',function(){
					var obj = $(this).closest('tr').data();
					_this.update(obj);
				});
				$('#dataBody').find('i.audit').off('click').on('click',function(){
					var obj = $(this).closest('tr').data();
					_this.audit(obj);
				});
				$('#dataBody').find('i.unaudit').off('click').on('click',function(){
					var obj = $(this).closest('tr').data();
					_this.unaudit(obj);
				});
				$('#dataBody').find('i.invalid').off('click').on('click',function(){
					var obj = $(this).closest('tr').data();
					_this.invalid(obj);
				});
				$('#dataBody').find('i.outfactory').off('click').on('click',function(){
					var obj = $(this).closest('tr').data();
					_this.outfactory(obj);
				});
				$('#dataBody').find('tr').off('dblclick').on('dblclick',function(){
					var obj = $(this).closest('tr').data();
					window.location.href = 'detailView?id='+obj.id;
				});
			}else{
				alert('暂无数据');
				$('#dataMore').hide();
			}
			$('#dataBody').find('tr').off('click').on('click',function(){
				var obj = $(this).data();
				var salesApplication = obj.salesApplication;
				var salesApplicationDetail = salesApplication.detailResp;
				var customer = salesApplication.customerManageResp;
				$('#dataMore').empty();
				var $tabDiv = $('<div class="cg_tabtit"><ul><li class="select">物料信息</li><li>订单信息</li><li>过磅信息</li></ul></div>').appendTo('#dataMore');
				var $tabCont = $('<div>').addClass('cg_tabbox').appendTo('#dataMore');
				$tabDiv.find('ul li').off('click').on('click',function(){
					$(this).addClass('select').siblings('li').removeClass('select');
					$tabCont.empty();
					switch ($(this).index()) {
					case 0:
						$('<table>').addClass('table table-bordered')
								.append('<thead><tr><th>车号</th><th>预提数量</th><th>司机</th><th>身份证号</th><th>制单日期</th></tr></thead>')
								.append('<tbody><tr><td>'+(obj.vehicleno || '')+'</td><td>'+(obj.takeamount || '')+'</td><td>'+(obj.drivername || '')+'</td><td>'+(obj.driveridentityno || '')+'</td><td>'+(obj.makebilltimeStr || '')+'</td><td></td></tr></tbody>')
								.appendTo($tabCont);
						break;
					case 1:
						$('<table>').addClass('table table-bordered')
								.append('<thead><tr><th>订单编号</th><th>类型</th><th>客户</th><th>物料</th><th>订单量</th><th>余量</th><th>出库占用量</th><th>未出库占用量</th><th>预提量</th><th>订单日期</th><th>制单人</th></tr></thead>')
								.append('<tbody><tr><td>'+(obj.billcode || '')+'</td><td>'+(salesApplication.billtypename || '')+'</td><td>'+(customer.name || '')+'</td><td>'+(salesApplicationDetail.materielname || '')+'</td><td>'+(salesApplicationDetail.salessum || '')+'</td><td></td><td></td><td></td><td>'+(obj.takeamount || '')+'</td><td>'+(salesApplication.billtimeStr || '')+'</td><td>'+(salesApplication.creatorname || '')+'</td></tr></tbody>')
								.appendTo($tabCont);
						break;
					case 2:
						$('<table>').addClass('table table-bordered')
								.append('<thead><tr><th>磅单号</th><th>车号</th><th>毛重</th><th>皮重</th><th>净重</th><th>轻车时间</th><th>重车时间</th></tr></thead>')
								.append('<tbody><tr><td></td><td>'+(obj.vehicleno || '')+'</td><td></td><td></td><td></td><td></td><td></td></tr></tbody>')
								.appendTo($tabCont);
						break;
					default:
						break;
					}
				});
				$tabDiv.find('ul li:eq(0)').trigger('click');
				$('#dataMore').show();
			});
		},
		update:function(obj){
			var _this = this;
			if(obj.auditstatus == '1'){
				layer.msg('此单据已审核，无法继续编辑！', {icon: 5});
				return;
			}
			if(obj.status == '3'){
				layer.msg('此单据已作废，无法继续编辑！', {icon: 5});
				return;
			}else if(obj.status != '0'){
				layer.msg('此单据已使用，无法继续编辑！', {icon: 5});
				return;
			}else{
				window.location.href = _this.top.URL.updateViewUrl+'?id='+obj.id;
			}
		},
		audit:function(obj){
			var _this = this;
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
			if(obj.status == '3'){
				layer.msg('此单据已使用，无法审核操作！', {icon: 5});
				return;
			}
			if(obj.status == '2'){
				layer.msg('此单据已过重车，无法审核操作！', {icon: 5});
				return;
			}
			_this.confirmOperation('您确定要审核么？', _this.top.URL.auditUrl, {id: obj.id});
		},
		unaudit:function(obj){
			var _this = this;
			if(obj.auditstatus == '0'){
				layer.msg('此单据未审核,无法继续反审!', {icon: 5});
				return;
			}
			if(obj.status == '4'){
				layer.msg('此单据已发卡,无法反审操作!', {icon: 5});
				return;
			}
			if(obj.status == '5'){
				layer.msg('此单据已出厂,无法反审操作!', {icon: 5});
				return;
			}
			if(obj.status == '6'){
				layer.msg('此单据已入厂,无法审核操作!', {icon: 5});
				return;
			}
			if(obj.status == '7'){
				layer.msg('此单据已装货,无法审核操作!', {icon: 5});
				return;
			}
			if(obj.status == '3'){
				layer.msg('此单据已作废,无法反审操作!', {icon: 5});
				return;
			}
			if(obj.status == '1'){
				layer.msg('此单据已过轻车,无法反审操作!', {icon: 5});
				return;
			}
			if(obj.status == '2'){
				layer.msg('此单据已过重车,无法反审操作!', {icon: 5});
				return;
			}
			_this.confirmOperation('您确定要反审么？',  _this.top.URL.unauditUrl, {id: obj.id});
		},
		invalid:function(obj){
			var _this = this;
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
			_this.confirmOperation('您确定要作废么？', _this.top.URL.invalidUrl, {id: obj.id});
		},
		outfactory:function(obj){
			var _this = this;
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
			_this.confirmOperation('您确定要出厂么？', _this.top.URL.outfactoryUrl, {id: obj.id});
		},
		confirmOperation: function(confirmContent, url, params){
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
}
$(function(){
	PAGE.mod.main.init();
});