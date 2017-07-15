;(function($, win) {
	//请求路径
	var URL = {
			add:"/trfc/driver/add",
			addView:"/trfc/driver/addView",
			page:"/trfc/driver/page",
			update:"/trfc/driver/update",
			del:"/trfc/driver/delete"
	};
	//初始化
	init();
	function init() {
		bindEvent();
		queryData(1);
	}
	//绑定事件
	function bindEvent() {
		$('#refresh').off('click').on('click',function() {
			layer.closeAll('dialog');
			queryData(1);
		});
		$('#search').off('click').on('click',function() {
			layer.closeAll('dialog');
			queryData(1);
		});
		$('#add').off('click').on('click',function() {
			layer.closeAll('dialog');
			showAddView();
		});
		$('#addCommit').off('click').on('click',function(e) {
			e.stopPropagation();
			layer.closeAll('dialog');
			this.disabled = true;
			add(this);
		});
		$('#update').off('click').on('click',function(e) {
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			showUpdateView(obj);
		});
		$('#updateCommit').off('click').on('click',function(e) {
			e.stopPropagation();
			layer.closeAll('dialog');
			this.disabled = true;
			update(this);
		});
		$('#delete').off('click').on('click',function(e) {
			e.stopPropagation();
			layer.closeAll('dialog');
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {layer.msg('需要选中一行才能操作哟！'); return;}
			del(obj.id);
		});
		$('#jumpPageNoBtn').off('click').on('click',function() {
			var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
			var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
			if (!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo) {
				layer.msg('此处必须为1-'+pageMaxNo+'的数字');
				$('input#jumpPageNo').val('');
			} else {
				$('input#jumpPageNo').val(pageNo);
				queryData(pageNo);
			}
		});
		$('#pageSize').change(function() {
			layer.closeAll('dialog');
			queryData(1);
		});
	}
	//搜索条件
	function getParams() {
		var params = {};
		var qtp = $('#qtp').val();qtp = $.trim(qtp);
		var keyword = $('#keyword').val();keyword = $.trim(keyword);
		if (qtp == 'mc') {
			params.name = keyword;
		}
		if (qtp == 'nm') {
			params.internalcode = keyword;
		}
		var orgid = $('#orgid').val();orgid = $.trim(orgid);
		params.orgid = orgid;
		var pageSize = $('#pageSize').val();pageSize = $.trim(pageSize);
		params.pageSize = pageSize;
		return params;
	}
	//发送搜索请求
	function queryData(pageNo) {
		var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params = getParams();
		params.pageNo = pageNo;
		$.ajax({
			url : URL.page,
			data:params,
			async:true,
			cache:false,
			dataType:'json',
			type:'post',
			success:function(result) {
				if (result.code == '000000') {
					renderHtml(result.data);
					var total = result.data.total;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					$('#total').html(total);
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					$("#pagination").pagination(total, {
					    callback: function(pageNo) {
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
				} else {
					layer.msg(result.error, {icon: 5});
				}
				layer.close(index);
			}
		});
	}
	//解析加载页面
	function renderHtml(data) {
		$('#dataBody').empty();
		var list = data.list;
		if (list && list.length>0) {
			for(var i=0;i<list.length;i++) {
				var obj = list[i] || '';
				var code = obj.code || '';
				var internalcode = obj.internalcode || '';
				var name = obj.name || '';
				var identityno = obj.identityno || '';
				var orgname = obj.orgname || '';
				var telephone = obj.telephone || '';
				var address = obj.address || '';
				var remarks = obj.remarks || '';
				$('<tr>').append('<td>'+(i+1)+'</td>')
						.append('<td>'+code+'</td>')
						.append('<td>'+internalcode+'</td>')
						.append('<td>'+name+'</td>')
						.append('<td>'+identityno+'</td>')
						.append('<td>'+orgname+'</td>')
						.append('<td>'+telephone+'</td>')
						.append('<td>'+address+'</td>')
						.append('<td>'+remarks+'</td>')
						.data(obj)
						.appendTo('#dataBody');
			}
		} else {
			layer.msg('暂无数据...');
		}
	}
	//初始化新增modal
	function showAddView() {
		$.get(URL.addView, null, function(result) {
			if (result.code == '000000') {
				$('#add_code').val(result.data.code);
				$('#add_internalcode').val(result.data.internalCode);
				$('#add_orgname').val(result.data.orgName);
				$('#addView').modal();
			} else {
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	//获取司机新增参数
	function getAddParams() {
		var name = $('#add_name').val();name = $.trim(name);
		var abbrname = $('#add_abbrname').val();abbrname = $.trim(abbrname);
		var address = $('#add_address').val();address = $.trim(address);
		var telephone = $('#add_telephone').val();telephone = $.trim(telephone);
		var identityno = $('#add_identityno').val();identityno = $.trim(identityno);
		var isvalid = 0;
		if ($('#add_isvalid')[0].checked) {
			isvalid = 1;
		}
		var remarks = $('#add_remarks').val();remarks = $.trim(remarks);
		return {
			name:name,
			abbrname:abbrname,
			address:address,
			telephone:telephone,
			identityno:identityno,
			isvalid:isvalid,
			remarks:remarks
		}
	}
	//校验新增参数是否合法
	function validateAdd(params) {
		if (!params.name) {
			layer.msg('司机名称不能为空哟！', {icon: 5});return;
		}
		if (!params.telephone) {
			layer.msg('司机电话不能为空哟！', {icon: 5});return;
		}
		if (!params.identityno) {
			layer.msg('身份证号不能为空哟！', {icon: 5});return;
		}
		return params;
	}
	//新增司机
	function add(_this) {
		var params = getAddParams();
		if (validateAdd(params)) {
			$.ajax({
				url:URL.add,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result) {
					if (result.code == '000000') {
						$('#addView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					} else {
						layer.msg(result.error, {icon: 5});
					}
					_this.disabled = false;
				}
			});
		} else {
			_this.disabled = false;
		}
	}
	//初始化修改modal
	function showUpdateView(obj) {
		$('#driverid').val(obj.id);
		$('#update_code').val(obj.code);
		$('#update_internalcode').val(obj.internalcode);
		$('#update_name').val(obj.name);
		$('#update_abbrname').val(obj.abbrname);
		$('#update_address').val(obj.address);
		$('#update_telephone').val(obj.telephone);
		$('#update_identityno').val(obj.identityno);
		if (obj.isvalid == 1) {
			$('#update_isvalid')[0].checked = true;
		} else {
			$('#update_isvalid')[0].checked = false;
		}
		$('#update_orgname').val(obj.orgname);
		$('#update_remarks').val(obj.remarks);
		$('#updateView').modal('show');
	}
	//获取修改司机参数
	function getUpdateParams() {
		var id = $('#driverid').val();id = $.trim(id);
		var name = $('#update_name').val();name = $.trim(name);
		var abbrname = $('#update_abbrname').val();abbrname = $.trim(abbrname);
		var address = $('#update_address').val();address = $.trim(address);
		var telephone = $('#update_telephone').val();telephone = $.trim(telephone);
		var identityno = $('#update_identityno').val();identityno = $.trim(identityno);
		var isvalid = 0;
		if ($('#update_isvalid')[0].checked) {
			isvalid = 1;
		}
		var remarks = $('#update_remarks').val();remarks = $.trim(remarks);
		return {
			id:id,
			name:name,
			abbrname:abbrname,
			address:address,
			telephone:telephone,
			identityno:identityno,
			isvalid:isvalid,
			remarks:remarks
		};
	}
	//校验修改参数是否合法
	function validateUpdate(params) {
		if (!params.id) {
			layer.msg('请先选中一行哟！', {icon: 5});return;
		}
		if (!params.name) {
			layer.msg('司机名称不能为空哟！', {icon: 5});return;
		}
		if (!params.telephone) {
			layer.msg('司机电话不能为空哟！', {icon: 5});return;
		}
		if (!params.identityno) {
			layer.msg('身份证号不能为空哟！', {icon: 5});return;
		}
		return params;
	}
	//修改司机
	function update(_this) {
		var params = getUpdateParams();
		if (validateUpdate(params)) {
			$.ajax({
				url:URL.update,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result) {
					if (result.code == '000000') {
						$('#updateView').modal('hide');
						$('#refresh').click();
						layer.msg(result.error, {icon: 1});
					} else {
						layer.msg(result.error, {icon: 5});
					}
					_this.disabled = false;
				}
			});
		} else {
			_this.disabled = false;
		}
	}
	//删除
	function del(id) {
		layer.confirm('真的要删除这个司机吗？', {
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
})(jQuery, window);