;
(function($, win) {
	// 请求路径
	var URL = {
		addTransportUrl : "/trfc/transport/addTransport",
		addBtnUrl : "/trfc/common/code/transportCode",
		pageUrl : "/trfc/transport/page",
		updateTransportUrl : "/trfc/transport/updateTransport",
		delTransportUrl : "/trfc/transport/delTransport"
	};

	init();
	function init() {
		bindEvent();
		queryData(1);
		$('#show_isvalid').click();
	}

	var transportData = {};

	function bindEvent() {
		$('#refreshBtn').off('click').on('click', function() {
			queryData(1);
			layer.closeAll('dialog');
		});
		$('#searshBtn').off('click').on('click', function() {
			queryData(1);
		});
		$('#addBtn').off('click').on('click', function() {
			addTransportAction();
		});
		$('#addTransport').off('click').on('click', function() {
			addTransport();
		});
		$('#update').off('click').on('click', function(e) {
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {
				layer.msg('需要选中一行才能操作哦！');
				return;
			}
			updateView(obj);
		});
		$('#delete').off('click').on('click', function(e) {
			e.stopPropagation();
			var obj = $('table.maintable tbody tr.active').data();
			if (!obj) {
				layer.msg('需要选中一行才能操作哦！');
				return;
			}
			deleteView(obj);
		});
		// 跳转按钮绑定 点击事件
		$('#jumpButton').click(jumpPageAction);
		$('#pageSize').change(function() {
			queryData(1);
		});
		$('#show_isvalid').off('click').on('click', function() {
			queryData(1);
		});
		// 修改运输单位信息
		$('#update_transport').off('click').on('click', function() {
			updateTransport();
		});
		// 绑定新增页面输入名称时,键盘按钮按下事件
		$('#transport_name').keyup(function() {
			$('#transport_abbrname').val($(this).val());
		});
		// 绑定修改页面输入名称时,键盘按钮按下事件
		$('#update_transport_name').keyup(function() {
			$('#update_transport_abbrname').val($(this).val());
		});
	}

	function queryData(pageNo) {
		var index = layer.load(2, {
			shade : [ 0.3, '#fff' ]
		// 0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		// 获取当前页面记录数
		var pageSize = $('#pageSize').val();
		var name = '';
		var internalcode = '';
		// 获取查询条件
		var keyword = $('#transport_keyword').val();
		keyword = $.trim(keyword);
		if ($('#transport_query').val() == 'name') {
			name = keyword;
		} else {
			internalcode = keyword;
		}

		var orgname = $('#transport_organizename').val();
		var isvalid = 0;
		if ($('#show_isvalid')[0].checked) {
			isvalid = 1;
		}
		var params = {
			pageSize : pageSize,
			name : name,
			internalcode : internalcode,
			orgname : orgname,
			isvalid : isvalid
		};
		// 获取当前页数
		params.pageNo = pageNo;
		// console.log(params);
		$
				.post(
						url,
						params,
						function(result) {
							if (result.code == '000000') {
								// console.log(result.data);
								var data = result.data;
								var list = data.list;
								// console.log(list);
								var pageNo = data.pageNo;
								var pageSize = data.pageSize;
								var total = data.total;
								var tbody = $('#transports').empty();
								// 添加数据总数
								$('.colorred').html(data.total);
								// 绑定 一个maxpageno属性
								$('#jumpPageNo').attr(
										'maxPageNo',
										parseInt((total + pageSize - 1)
												/ pageSize));
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
								if (!list) {
									return;
								}
								for (var i = 0; i < list.length; i++) {
									var transport = list[i];
									var tr = $('<tr><td>'
											+ ((pageNo - 1) * pageSize + i + 1)
											+ '</td><td>'
											+ transport.code
											+ '</td><td>'
											+ transport.internalcode
											+ '</td><td>'
											+ transport.name
											+ '</td><td>'
											+ transport.orgname
											+ '</td><td><input type="checkbox" disabled id="list_isvalid'
											+ i + '" ></td><td>'
											+ transport.remarks + '</td></tr>');
									tr.data(transport);
									tbody.append(tr);
									$('#list_isvalid' + i)[0].checked = false;
									if (transport.isvalid == 1) {
										$('#list_isvalid' + i)[0].checked = true;
									}
								}

							} else {
								layer.msg(result.error, {
									icon : 5
								});
							}
						});
		layer.close(index);
	}

	function pageCallback(pageNo) {
		queryData(pageNo + 1);
	}

	// 页面跳转
	function jumpPageAction() {
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if (!pageno || !$.isNumeric(pageno) || pageno <= 0
				|| pageno > maxpageno) {
			layer.msg('输入的数字必须在1~' + maxpageno + '之间');
		} else {
			queryData(pageno);
			$('#jumpPageNo').val('');
		}
	}

	function addTransportAction() {
		// console.log('addTransportAction');
		var url = URL.addBtnUrl;
		var param = {};
		$.post(url, param, function(result) {
			if (result.code = '000000') {
				var data = result.data;
				// console.log(data);
				$('#transport_code').val(data.code);
				$('#transport_internalcode').val(data.internalcode);
				$('#transport_name').val('');
				$('#transport_abbrname').val('');
				$('#transport_address').val('');
				$('#transport_telephone').val('');
				$('#transport_orgname').val('');
				$('#transport_isvalid').attr('checked', 'checked');
				$('#transport_remarks').val('');
			} else {
				layer.msg(result.error, {
					icon : 5
				});
			}
		});
	}

	function addTransport() {
		// console.log('addTransport');
		if ($('#add').is(':visible')) {
			var url = URL.addTransportUrl;
			var code = $('#transport_code').val();
			code = $.trim(code);
			var internalcode = $('#transport_internalcode').val();
			internalcode = $.trim(internalcode);
			var name = $('#transport_name').val();
			name = $.trim(name);
			if (!name) {
				layer.msg('名称不能为空哦', {
					icon : 5
				});
				$('#add .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var abbrname = $('#transport_abbrname').val();
			abbrname = $.trim(abbrname);
			var address = $('#transport_address').val();
			address = $.trim(address);
			var telephone = $('#transport_telephone').val();
			telephone = $.trim(telephone);
			var isvalid = 0;
			if ($('#transport_isvalid')[0].checked) {
				isvalid = 1;
			}
			var orgid = $('#transport_orgname').attr('orgid');
			orgid = $.trim(orgid);
			var orgname = $('#transport_orgname').val();
			orgname = $.trim(orgname);
			var remarks = $('#transport_remarks').val();
			remarks = $.trim(remarks);
			var params = {
				code : code,
				internalcode : internalcode,
				name : name,
				abbrname : abbrname,
				address : address,
				telephone : telephone,
				isvalid : isvalid,
				orgid : orgid,
				orgname : orgname,
				remarks : remarks
			};
			$('#addTransport').attr('data-dismiss', 'modal');
			$.post(url, params, function(result) {
				if (result.code = '000000') {
					// console.log(result.data);
					queryData(1);
				} else {
					layer.msg(result.error, {
						icon : 5
					});
				}
			});
		}

	}
	
	function updateView(obj){
		$('#update_transport_code').val(obj.code);
		$('#update_transport_internalcode').val(obj.internalcode);
		$('#update_transport_name').val(obj.name);
		$('#update_transport_abbrname').val(obj.abbrname);
		$('#update_transport_address').val(obj.address);
		$('#update_transport_telephone').val(obj.telephone);
		$('#update_transport_orgname').val(obj.orgname);
		$('#update_transport_remarks').val(obj.remarks);
		$('#update_transport_isvalid')[0].checked = false;
		if (obj.isvalid == 1) {
			$('#update_transport_isvalid')[0].checked = true;
		}
		transportData.id = obj.id;
		$('#edit').modal('show');
	}

	function updateTransport() {
		if ($('#edit').is(':visible')) {
			var id = transportData.id;
			var name = $('#update_transport_name').val();
			name = $.trim(name);
			if (!name) {
				layer.msg('名称不能为空哦', {
					icon : 5
				});
				$('#edit .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var abbrname = $('#update_transport_abbrname').val();
			abbrname = $.trim(abbrname);
			var address = $('#update_transport_address').val();
			address = $.trim(address);
			var telephone = $('#update_transport_telephone').val();
			telephone = $.trim(telephone);
			var isvalid = 0;
			if ($('#update_transport_isvalid')[0].checked) {
				isvalid = 1;
			}
			var orgname = $('#update_transport_orgname').val();
			orgname = $.trim(orgname);
			var remarks = $('#update_transport_remarks').val();
			remarks = $.trim(remarks);
			var url = URL.updateTransportUrl;
			var params = {
				id : id,
				name : name,
				abbrname : abbrname,
				address : address,
				telephone : telephone,
				orgname : orgname,
				isvalid : isvalid,
				remarks : remarks
			};
			$('#update_transport').attr('data-dismiss', 'modal');
			$.post(url, params, function(result) {
				if (result.code = '000000') {
					queryData(1);
				} else {
					layer.msg(result.error, {
						icon : 5
					});
				}
			});
		}
	}
	
	function deleteView(obj){
		transportData.id = obj.id;
		var bn = layer.open({
			content : '您确定要删除吗？',
			area : '600px',
			closeBtn : 1,
			shadeClose : true,
			btn : [ '确定', '取消' ],
			yes : function(index, layero) {
				// 按钮【确定】的回调
				deleteTransport();
				layer.close(bn);
			},
			btn2 : function(index, layero) {
				// 按钮【取消】的回调
			},
			cancel : function() {
				// 右上角关闭回调
			}
		});
	}

	function deleteTransport() {
		var url = URL.delTransportUrl;
		var param = {
			id : transportData.id
		};
		$.post(url, param, function(result) {
			if (result.code = '000000') {
				queryData(1);
			} else {
				layer.msg(result.error, {
					icon : 5
				});
			}
		});
	}
})(jQuery, window);