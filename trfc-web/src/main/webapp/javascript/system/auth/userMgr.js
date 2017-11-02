$(function() {
	// 访问的url
	var URL = {
		saveUrl : "/trfc/system/auth/user/addUser",
		pageUrl : "/trfc/system/auth/user/page",
		editUser : "/trfc/system/auth/user/editUser",
		deleteUrl : "/trfc/system/auth/user/deleteUser",
		resetPwd : "/trfc/system/auth/user/resetPwd"
	}

	// 获取用户id
	var userid = $('.user').attr('userid');

	var pageSize = 10;
	// 列表数据加载
	initData(1, pageSize);
	// 查询操作
	$(".searchBtn").off('click').on("click", function() {
		initData(1, pageSize);
	});
	// 分页操作

	// 新增绑定
	$("#addButton").off('click').on("click", function() {
		openAddWindow();
	});
	// 新增弹出框 确认操作
	$("#addModal").off('click').on("click", ".submitBtn", addsubmit);
	// 修改弹出框 确认操作
	$('#updateCommit').off('click').on('click', function() {
		if ($('#updateView').is(':visible')) {
			updateCommit();
		}
	});
	// 删除按钮绑定
	// 删除操作
	$('#list').on('click', 'tr [title="删除"]', function() {
		deleteAction(this);
	});
	// 绑定跳转按钮
	$("#jumpButton").click(function() {
		jumpPageAction();
	});
	// 监听每页记录 事件
	$('#pageSize').change(function() {
		initData(1);
	});

	// 刷新按钮
	$(".refreshButton").off('click').on("click", ".refreshButton", refreshLocation);

	/**
	 * 获取搜索信息
	 */
	function getSeekData() {
		var codeLike = '';
		var nameLike = '';
		var accountLike = '';
		var key = $('.keyInput').val();
		switch ($('.keySelect').val()) {
		case 'codeLike':
			codeLike = key;
			break;
		case 'nameLike':
			nameLike = key;
			break;
		case 'accountLike':
			accountLike = key;
		}
		var identityTypes = $('#identityTypes').val();
		var pageSize = $('#pageSize').val();
		return {
			codeLike : codeLike,
			nameLike : nameLike,
			accountLike : accountLike,
			identityTypes: identityTypes,
			pageSize : pageSize
		};
	}

	// 页面跳转
	function jumpPageAction() {
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if (!pageno || !$.isNumeric(pageno) || pageno <= 0
				|| pageno > eval(maxpageno)) {
			layer.msg('输入的数字必须在1~' + maxpageno + '之间');
		} else {
			initData(pageno);
		}
	}
	// 前进一页
	function pageCallback(pageNo) {
		initData(pageNo + 1);
	}

	/**
	 * 分页方法
	 */
	function initData(pageno) {
		// 启动缓冲动画
		var index = layer.load(2, {
			shade : [ 0.3, '#fff' ]
		// 0.1透明度的白色背景
		});

		$(".intel_table").find("tbody").empty();
		var param = getSeekData();
		param.pageNo = pageno || 1;
		$.ajax({
			url : URL.pageUrl,
			data : param,
			type : "post",
			dataType : "json",
			success : function(result) {
				if (result && result.code == "000000") {
					var list = result.data.list;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					var total = result.data.total;
					// 添加数据总数
					$('#total').html(result.data.total);
					// 绑定 一个maxpageno属性
					$('#jumpPageNo').attr('maxPageNo',
							parseInt((total + pageSize - 1) / pageSize));
					// 分页插件
					$("#pagination").pagination(total, {
						callback : pageCallback,
						prev_text : '上一页',
						next_text : '下一页',
						items_per_page : pageSize,
						num_display_entries : 4,
						current_page : pageNo - 1,
						num_edge_entries : 1,
						maxentries : total,
						link_to : "javascript:void(0)"
					});

					renderDate(list, pageNo, pageSize);
				} else {
					layer.msg(rs.error, {
						icon : 5
					});
				}
				// 关闭缓冲图标
				layer.close(index);
			}
		})
	}

	/**
	 * 渲染数据
	 */
	function renderDate(list, pageNo, pageSize) {
		var tbody = $('list').empty();
		if (list) {
			for (var i = 0; i < list.length; i++) {
				var obj = list[i];
				var invalid = '';
				switch (obj.isvalid) {
				case 0:
					invalid = '无效';
					break;
				case 1:
					invalid = '有效';
				}
				$(
						'<tr>' + '<td>'
								+ ((pageNo - 1) * pageSize + i + 1)
								+ '</td>'
								+ '<td>'
								+ (obj.code || '')
								+ '</td>'
								+ '<td>'
								+ (obj.account || '')
								+ '</td>'
								+ '<td>'
								+ (obj.name || '')
								+ '</td>'
								+ '<td>'
								+ (obj.orgName || '')
								+ '</td>'
								+ '<td>'
								+ (invalid || '')
								+ '</td>'
								+ '<td>'
								+ (obj.source || '')
								+ '</td>'
								+ '<td>'
								+ (obj.logincount || 0)
								+ '</td>'
								+ '<td>'
								+ (obj.lastLogintimeStr || '')
								+ '</td>'
								+ '<td>'
								+ (obj.remark || '')
								+ '</td>'
								+ '<td><span class="update"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
								+ '<span class="resetPwd"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="重置密码">&#xe633;</i></a></span>'
								+ '<span class="delete"><a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span></td>'
								+ '</tr>').data('obj', obj).appendTo('#list');
			}
			$('#list').find('tr>td>span.update').off('click').on('click',
					function() {
						var obj = $(this).closest('tr').data('obj');
						initEditData(obj);
					});
			$('#list').find('tr>td>span.resetPwd').off('click').on('click',
					function() {
						var obj = $(this).closest('tr').data('obj');
						resetPwd(obj);
					});
		} else {
			layer.msg('暂无数据');
		}
	}
	/**
	 * 编辑页面初始化
	 */
	function initEditData(obj) {
		$('#userId').val(obj.id);
		$('#edit_code').val(obj.code);
		$('#edit_account').val(obj.account);
		$('#edit_name').val(obj.name);
		$('#edit_org').val(obj.orgName);
		switch (obj.isvalid) {
		case 0:
			$('#edit_isvalid')[0].checked = false;
			break;
		case 1:
			$('#edit_isvalid')[0].checked = true;
		}
		$('#edit_remark').val(obj.remark);
		$('#updateView').modal('show');
	}
	/**
	 * 打开新增弹出框
	 */
	function openAddWindow() {
		$("#addModal").modal();
	}
	function addsubmit() {
		$.ajax({
			url : URL.saveUrl,
			data : $("#addModal .formele").serialize(),
			type : "post",
			dataType : "json",
			success : function(rs) {
				if (rs && rs.code == "000000") {
					$('#add_cancel').click();
					initData();
				} else {
					layer.msg(rs.error, {
						icon : 5
					});
				}
			}
		});
	}
	// 监听新增模态框关闭事件
	$('#addModal').on('hidden.bs.modal', function(e) {
		$("#addModal .formele").val("");
	});
	/**
	 * 页面重新刷新
	 */
	function refreshLocation() {
		initData();
	}

	/**
	 * 删除功能
	 */
	function deleteAction(span) {
		var id = $(span).closest('tr').data('obj').id;
		var params = {
			id : id,
		};
		layer.confirm('注：您确定要删除吗？', {
			btn : [ '确定', '取消' ],
			area : '600px'
		}, function() {
			$.ajax({
				url : URL.deleteUrl,
				data : params,
				async : true,
				cache : false,
				dataType : 'json',
				type : 'post',
				success : function(result) {
					if (result.code == '000000') {
						window.location.reload(true);
					} else {
						layer.msg(result.error, {
							icon : 5
						});
					}
				}
			});
		});
	}
	function getUpdateParams() {
		var id = $('#userId').val();
		id = $.trim(id);
		var password = $('#edit_psd').val();
		password = $.trim(password);
		var remark = $('#edit_remark').val();
		remark = $.trim(remark);
		var isvalid = Number($('#edit_isvalid')[0].checked);
		return {
			id : id,
			password : password,
			remark : remark,
			isvalid : isvalid
		}
	}
	function validateUpdate(params) {
		if (!params.id) {
			layer.msg('请先选择待修改的用户！', {
				icon : 5
			});
			return false;
		}
		if (!params.password) {
			layer.msg('请输入密码！', {
				icon : 5
			});
			return false;
		}
		return params;
	}
	// 修改
	function updateCommit() {
		var params = getUpdateParams();
		if (validateUpdate(params)) {
			$.ajax({
				url : URL.editUser,
				data : params,
				async : true,
				cache : false,
				dataType : 'json',
				type : 'post',
				success : function(result) {
					if (result.code == '000000') {
						window.location.reload(true);
					} else {
						layer.msg(result.error, {
							icon : 5
						});
					}
				}
			});
		}
	}
	// 重置密码
	function resetPwd(obj) {
		layer.open({
			title : '重置密码',
			area : [ '500px', '300px' ],
			btn : [ '确定', '取消' ],
			shadeClose : true, //开启遮罩关闭
			content : '<form class="layui-form" action="">'
						+ '<div class="layui-form-item">'
							+ '<label class="layui-form-label">账户：</label>'
							+ '<div class="layui-input-inline">'
								+ '<input type="text" class="layui-input" readonly="true" value="' + obj.name + '">'
							+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
							+ '<label class="layui-form-label">新密码：</label>'
							+ '<div class="layui-input-inline">'
								+ '<input type="password" name="password1" required lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">'
							+ '</div>'
						+ '</div>'
						+ '<div class="layui-form-item">'
							+ '<label class="layui-form-label">确认密码：</label>'
							+ '<div class="layui-input-inline">'
								+ '<input type="password" name="password2" required lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">'
							+ '</div>' 
						+ '</div>' 
					+ '</form>',
			success : function(layero, index) {
				var password1 = layero.find("input[name='password1']");
				var password2 = layero.find("input[name='password2']");
				password2.keyup(function(){
					layer.closeAll('tips');
					var p1 = password1.val();
					var p2 = password2.val();
					if(p1.length >= p2.length){
						if(p1.substr(0, p2.length) != p2){
							layer.tips('输入的密码不一致！', password2, {tips: [2, '#f44336']});
						}
					}else{
						layer.tips('输入的密码不一致！', password2, {tips: [2, '#f44336']});
					}
				});
			},
			yes : function(index, layero) {
				var password = layero.find("input[name='password2']").val();
				$.ajax({
					url : URL.resetPwd,
					data : {
						id: obj.id,
						password: password
					},
					async : true,
					cache : false,
					dataType : 'json',
					type : 'post',
					success : function(result) {
						if (result.code == '000000') {
							layer.msg('修改成功！', {icon: 1});
						} else {
							layer.msg(result.error, {
								icon : 5
							});
						}
					}
				});
			}
		});
	}

});