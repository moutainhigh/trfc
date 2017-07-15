;(function($, win){
	//请求路径
	var URL = {
			addView:"/trfc/vehicle/addView",
			add:"/trfc/vehicle/add",
			page:"/trfc/vehicle/page",
			update:"/trfc/vehicle/update",
			black:"/trfc/vehicle/black",
			white:"/trfc/vehicle/white",
			del:"/trfc/vehicle/delete",
	};
	//初始化
	init();
	function init(){
		bindEvent();
		queryData(1);
	}
	//绑定事件
	function bindEvent(){
		$('#refresh').off('click').on('click',function(){
			layer.closeAll('dialog');
			queryData(1);
		});
		$('#search').off('click').on('click',function(){
			layer.closeAll('dialog');
			queryData(1);
		});
		$('#add').off('click').on('click',function(){
			layer.closeAll('dialog');
			showAddView();
		});
		$('#addCommit').off('click').on('click',function(){
			layer.closeAll('dialog');
			this.disabled = true;
			add(this);
		});
		$('#update').off('click').on('click',function(e){
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			showUpdateView(obj);
		});
		$('#updateCommit').off('click').on('click',function(){
			layer.closeAll('dialog');
			this.disabled = true;
			update(this);
		});
		$('#update').off('click').on('click',function(e){
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			showUpdateView(obj);
		});
		$('#delete').off('click').on('click',function(e){
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			del(obj.id);
		});
		$('#black').off('click').on('click',function(e){
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			if(obj.isblacklist == '1'){layer.msg('该车辆已被添加进黑名单中，不能重复添加！', {icon: 5});return;}
			showBlackView(obj);
		});
		$('#addBlack').off('click').on('click', function(){
			layer.closeAll('dialog');
			this.disabled = true;
			black(this);
		});
		$('#white').off('click').on('click',function(e){
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			if(obj.isblacklist == '0'){layer.msg('该车辆没有被列入黑名单，无需移除！', {icon: 5});return;}
			white(obj.id);
		});
		$('#jumpPageNoBtn').off('click').on('click',function(){
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			}else{
				$('input#jumpPageNo').val(pageNo);
				queryData(pageNo);
			}
		});
		$('#pageSize').change(function(){
			layer.closeAll('dialog');
			queryData(1);
		});
	}
	//获取搜索条件
	function getParams(){
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		var orgid = $('#orgid').attr("orgid");orgid = $.trim(orgid);
		var orgname = $('#orgid').val();orgname = $.trim(orgname);
		var transporttype = $('#transporttype').val();transporttype = $.trim(transporttype);
		var isblacklist = $('#isblacklist').val();isblacklist = $.trim(isblacklist);
		if(qtp == 'mc'){
			params.vehicleno = keyword;
		}
		if(qtp == 'nm'){
			params.internalcode = keyword;
		}
		params.orgid = orgid;
		params.orgname = orgname;
		params.transporttype = transporttype;
		params.isblacklist = isblacklist;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		return params;
	}
	//发送搜索请求
	function queryData(pageNo){
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.post(URL.page, params, function(result){
			if(result.code == '000000'){
				renderHtml(result.data);
				var total = result.data.total;
				var pageNo = result.data.pageNo;
				var pageSize = result.data.pageSize;
				$('#total').html(total);
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$("#pagination").pagination(total, {
				    callback: function(pageNo){
						queryData(pageNo+1);
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
		});
	}
	//解析加载页面
	function renderHtml(data){
		$('#vehicleBody').empty();
		var list = data.list;
		if(list && list.length>0){
			for(var i=0;i<list.length;i++){
				var obj = list[i] || '';
				var code = obj.code || '';
				var rfid = obj.rfid || '';
				var vehicleno = obj.vehicleno || '';
				var orgname = obj.orgname || '';
				var transportunit = obj.transportunit || '';
				var isblacklist = obj.isblacklist || '';
				switch (isblacklist) {
				case '0':
					isblacklist = '否';
					break;
				case '1':
					isblacklist = '是';
					break;
				default:
					isblacklist = '';
					break;
				}
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+rfid+'</td>')
						.append('<td>'+vehicleno+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+transportunit+'</td>')
						.append('<td '+(obj.isblacklist=='1'?'style="color:red;"':'')+'>'+isblacklist+'</td>')
						.append('<td>'+remarks+'</td>')
						.data(obj)
						.appendTo('#vehicleBody');
			}
		}else{
			layer.msg('暂无数据...');
		}
	}
	//车牌号正则校验
	function validateVehicle(vehicle) {
		var flag = false;
		if (vehicle.length == 7){
			var express = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$/;
			flag = express.test(vehicle);
		}
		return flag;
	}
	//初始化新增modal
	function showAddView() {
		$.get(URL.addView, null, function(result){
			if(result.code == '000000'){
				$('#add_code').val(result.data.code);
				$('#add_orgname').val(result.data.orgName);
				$('#addView').modal('show');
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	//获取新增参数
	function getAddParams(){
		var code = $('#add_code').val();code = $.trim(code);
		var transporttype = $('#add_transporttype').val();transporttype = $.trim(transporttype);
		var vehicleno = $('#add_vehicleno').val();vehicleno = $.trim(vehicleno);
		var vehicletype = $('#add_vehicletype').val();vehicletype = $.trim(vehicletype);
		var transportunit = $('#add_transportunit').val();transportunit = $.trim(transportunit);
		var maxweight = $('#add_maxweight').val();maxweight = $.trim(maxweight);
		var tareweight = $('#add_tareweight').val();tareweight = $.trim(tareweight);
		var ownername = $('#add_ownername').val();ownername = $.trim(ownername);
		var telephone = $('#add_telephone').val();telephone = $.trim(telephone);
		var address = $('#add_address').val();address = $.trim(address);
		var orgname = $('#add_orgname').val();orgname = $.trim(orgname);
		var orgid = $('#add_orgid').attr('orgid');orgid = $.trim(orgid);
		var isvalid = '0';
		if($('#_isvalid')[0].checked){
			isvalid = '1';
		}
		var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
		return {
			code:code,
			transporttype:transporttype,
			vehicleno:vehicleno,
			vehicletype:vehicletype,
			transportunit:transportunit,
			maxweight:maxweight,
			tareweight:tareweight,
			ownername:ownername,
			telephone:telephone,
			address:address,
			orgname:orgname,
			orgid:orgid,
			isvalid:isvalid,
			remarks:remarks
		}
	}
	//校验新增参数
	function validateAdd(params){
		if(!params.vehicleno){
			layer.msg('车牌号码不能为空哟！', {icon: 5});return;
		}
		if(!validateVehicle(params.vehicleno)){
			layer.msg('给我一个正确的车牌号吧！', {icon: 5});return;
		}
		return params;
	}
	//新增
	function add(_this){
		var params = getAddParams();
		if(validateAdd(params)){
			$.post(URL.add, params, function(result) {
				if(result){
					if(result.code == '000000'){
						$('#addView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}else{
					layer.msg('请求失败了，等下再来试一遍吧！', {icon: 5});
				}
				_this.disabled = false;
			});
		}else{
			_this.disabled = false;
		}
	}
	//初始化修改modal
	function showUpdateView(obj){
		$('#vehicleid').val(obj.id);
		$('#_code').val(obj.code);
		$('#_transporttype').val(obj.transporttype);
		$('#_vehicleno').val(obj.vehicleno);
		$('#_vehicletype').val(obj.vehicletype);
		$('#_transportunit').val(obj.transportunit);
		$('#_maxweight').val(obj.maxweight);
		$('#_tareweight').val(obj.tareweight);
		$('#_ownername').val(obj.ownername);
		$('#_telephone').val(obj.telephone);
		$('#_address').val(obj.address);
		$('#_orgname').val(obj.orgname);
		if(obj.isvalid == '1'){
			$('#_isvalid')[0].checked = true;
		}else{
			$('#_isvalid')[0].checked = false;
		}
		$('#_remarks').val(obj.remarks);
		$('#updateView').modal('show');
	}
	//获取修改参数
	function getUpdateParams(){
		var id = $('#vehicleid').val();id = $.trim(id);
		var transporttype = $('#_transporttype').val();transporttype = $.trim(transporttype);
		var vehicleno = $('#_vehicleno').val();vehicleno = $.trim(vehicleno);
		var vehicletype = $('#_vehicletype').val();vehicletype = $.trim(vehicletype);
		var transportunit = $('#_transportunit').val();transportunit = $.trim(transportunit);
		var maxweight = $('#_maxweight').val();maxweight = $.trim(maxweight);
		var tareweight = $('#_tareweight').val();tareweight = $.trim(tareweight);
		var ownername = $('#_ownername').val();ownername = $.trim(ownername);
		var telephone = $('#_telephone').val();telephone = $.trim(telephone);
		var address = $('#_address').val();address = $.trim(address);
		var isvalid = '0';
		if($('#_isvalid')[0].checked){
			isvalid = '1';
		}
		var remarks = $('#_remarks').val();remarks = $.trim(remarks);
		return {
			id:id,
			transporttype:transporttype,
			vehicleno:vehicleno,
			vehicletype:vehicletype,
			transportunit:transportunit,
			maxweight:maxweight,
			tareweight:tareweight,
			ownername:ownername,
			telephone:telephone,
			address:address,
			isvalid:isvalid,
			remarks:remarks
		};
	}
	//校验修改参数
	function validateAddUpdate(params){
		if(!params.id){
			layer.msg('请先选中一行哟！', {icon: 5});return;
		}
		if(!params.vehicleno){
			layer.msg('车牌号码不能为空哟！', {icon: 5});return;
		}
		if(!validateVehicle(params.vehicleno)){
			layer.msg('给我一个正确的车牌号吧！', {icon: 5});return;
		}
		return params;
	}
	//修改
	function update(_this){
		var params = getAddParams();
		if(validateAddUpdate(params)){
			$.post(URL.update, params, function(result) {
				if(result){
					if(result.code == '000000'){
						$('#updateView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}else{
					layer.msg('请求失败了，等下再来试一遍吧！', {icon: 5});
				}
				_this.disabled = false;
			});
		}else{
			_this.disabled = false;
		}
	}
	//删除
	function del(id) {
		layer.confirm('真的要删除这个车辆吗？', {
			btn: ['确定', '取消'],
			area: '600px'
		}, function(index) {
			$.post(URL.del, {id: id}, function(result) {
				if (result) {
					if (result.code == '000000') {
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					} else {
						layer.msg(result.error, {icon: 5});
					}
				} else {
					layer.msg('请求失败了，等下再来试一遍吧！', {icon: 5});
				}
			});
			layer.close(index);
		});
	}
	//添加黑名单
	function showBlackView(obj) {
		$('#b_vehicleid').val(obj.id);
		$('#b_vehicleno').val(obj.vehicleno);
		$('#blackView').modal('show');
	}
	//获取带添加黑名单参数
	function getBlackParams(){
		var id = $('#b_vehicleid').val();
		var remarks = $('#b_remarks').val();
		return {
			id: id,
			blackRemarks: remarks
		}
	}
	//校验黑名单参数
	function validateBlack(params){
		if(!params.id){
			layer.msg('请先选中一行哟！', {icon: 5});return;
		}
		return params;
	}
	//添加黑名单
	function black(_this) {
		var params = getBlackParams();
		if(validateBlack(params)){
			$.post(URL.black, params, function(result) {
				if(result){
					if(result.code == '000000'){
						$('#blackView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}else{
					layer.msg('请求失败了，等下再来试一遍吧！', {icon: 5});
				}
				_this.disabled = false;
			});
		}else{
			_this.disabled = false;
		}
	}
	//添加白名单
	function white(id){
		layer.confirm('真的要把这个车辆添加到白名单吗？', {
			btn: ['确定', '取消'],
			area: '600px'
		}, function(index) {
			$.post(URL.white, {id: id}, function(result) {
				if(result){
					if(result.code == '000000'){
						$('#blackView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}else{
					layer.msg('请求失败了，等下再来试一遍吧！', {icon: 5});
				}
			});
			layer.close(index);
		});
	}
})(jQuery, window);