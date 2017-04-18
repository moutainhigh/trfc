$(function(){
	//访问的url
	var URL={
			saveUrl:"/trfc/system/auth/user/addUser",
			pageUrl:"/trfc/system/auth/user/page",
			deleteUrl:"/trfc/system/auth/user/deleteUser",
	}

	//获取用户id
	var userid = $('.user').attr('userid');

	var pageSize = 10;
	//列表数据加载
	initData(1, pageSize);
	//查询操作
	$(".searchBtn").off('click').on("click", function(){
		initData(1, pageSize);
	});
	//分页操作

	//新增绑定
	$(".addButton").off('click').on("click",function(){
		openAddWindow();
	});
	//新增弹出框 确认操作
	$("#addModal").off('click').on("click",".submitBtn",addsubmit);
	//修改按钮绑定
	$('#list').on('click','tr [title="编辑"]',function() {
		initEditData(this);
	});
	//修改弹出框 确认操作
	//删除按钮绑定
	//删除操作
	$('#list').on('click','tr [title="删除"]',function() {
		deleteAction(this);
	});
	//绑定跳转按钮
	$("#jumpButton").click(function() {
		jumpPageAction();
	});
	//监听每页记录 事件
	$('#pageSize').change(function(){initData(1);});

	//刷新按钮
	$(".right").off('click').on("click",".refreshButton",refreshLocation);

	
	/**
	 * 编辑页面初始化
	 */
	function initEditData(span){
		var obj = $(span).closest('tr').data('obj');
		$('#edit_code').val(obj.code);
		$('#edit_account').val(obj.account);
		$('#edit_name').val(obj.name);
		$('#edit_org').val(obj.orgName);
		switch(obj.isvalid){
		case 0:
			$('#edit_isvalid')[0].checked=false;break;
		case 1:
			$('#edit_isvalid')[0].checked=true;
		}
		$('#edit_psd').val(obj.password);
		$('#edit_remark').val(obj.remark);
		
	}
	
	
	/**
	 * 获取搜索信息
	 */
	function getSeekData(){
		var codeLike = '';
		var nameLike = '';
		var accountLike = '';
		var key = $('.keyInput').val();
		switch($('.keySelect').val()){
		case 'codeLike':
			codeLike = key;break;
		case 'nameLike':
			nameLike = key;break;
		case 'accountLike':
			accountLike = key;
		}
		var pageSize = $('#pageSize').val();
		return {
			codeLike:codeLike,
			nameLike:nameLike,
			accountLike:accountLike,
			pageSize:pageSize
		};
	}

//	页面跳转
	function jumpPageAction(){
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>eval(maxpageno) ){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			initData(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo){
		initData(pageNo+1);
	}

	/**
	 * 分页方法
	 */
	function initData(pageno){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		
		$(".intel_table").find("tbody").empty();
		var param = getSeekData();
		param.pageNo = pageno || 1;
		$.ajax({
			url : URL.pageUrl,
			data : param,
			type : "post",
			dataType:"json",
			success:function(result){
				if( result && result.code =="000000" ){
					var list = result.data.list;
					var pageNo = result.data.pageNo;
					var pageSize = result.data.pageSize;
					var total = result.data.total;
					//添加数据总数
					$('#total').html(result.data.total);
					//绑定 一个maxpageno属性
					$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
					//分页插件
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

					renderDate(list,pageNo,pageSize);
				}else{
					layer.msg(rs.error,{icon:5});
				}
				//关闭缓冲图标
				layer.close(index);
			}
		})
	}



	/**
	 * 渲染数据
	 */
	function renderDate(list,pageNo,pageSize){
		var tbody = $('list').empty();
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var invalid = '';
				switch(obj.isvalid){
				case 0:
					invalid = '无效';break;
				case 1:
					invalid = '有效';
				}
				var tr = $('<tr>'
						+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
						+'<td>'+(obj.code || '')+'</td>'
						+'<td>'+(obj.account || '')+'</td>'
						+'<td>'+(obj.name || '')+'</td>'
						+'<td>'+(obj.orgName || '')+'</td>'
						+'<td>'+(invalid || '')+'</td>'
						+'<td>'+(obj.source || '')+'</td>'
						+'<td>'+(obj.logincount || 0)+'</td>'
						+'<td>'+(obj.lastLogintimeStr || '')+'</td>'
						+'<td>'+(obj.remark || '')+'</td>'
						+'<td><span><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'
						+'<span><a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span></td>'
						+'</tr>');

				$('#list').append(tr);
				tr.data('obj',obj);
			}
		}else{
			layer.msg('暂无数据');
		}


//		var dataArr=[]; 
//		$(list).each(function(i,item){
//		dataArr.push(' <tr>');
//		dataArr.push('<td>'+(i+1)+'</td>');
//		dataArr.push('<td>'+item.code+'</td>');
//		dataArr.push('<td>'+item.account+'</td>');
//		dataArr.push('<td>'+item.name+'</td>');
//		dataArr.push('<td>'+(item.orgName||"汝州天瑞水泥")+'</td>');
//		if( item.isvalid ==1){
//		dataArr.push('<td>有效</td>');
//		}else{
//		dataArr.push('<td>无效</td>');
//		}

//		//来源
//		dataArr.push('<td>'+item.source+'</td>');
//		//登录次数
//		dataArr.push('<td>'+(item.logincount||0)+'</td>');
//		dataArr.push('<td>'+(item.lastLogintimeStr||"")+'</td>');
//		dataArr.push('<td>'+(item.remark||"")+'</td>');
//		//操作
//		dataArr.push('<td>');
//		//修改操作
//		dataArr.push('<span><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>');
//		dataArr.push('<span><a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a></span>');
//		dataArr.push('</td>');
//		dataArr.push('</tr>');

//		})
//		$(".intel_table").find("tbody").append(dataArr.join(" "));
		//dataArr.data('obj',item);
	}
	/**
	 * 打开新增弹出框
	 */
	function openAddWindow(){
		$("#addModal").modal();
	}
	function addsubmit(){
		$.ajax({
			url:URL.saveUrl,
			data:$("#addModal .formele").serialize(),
			type : "post",
			dataType:"json",
			success:function(rs){
				if( rs && rs.code =="000000" ){
					$('#add_cancel').click();
					initData();
				}else{
					layer.msg(rs.error,{icon:5});
				}
			}
		});
	}
	//监听新增模态框关闭事件
	$('#addModal').on('hidden.bs.modal', function (e) {
		$("#addModal .formele").val("");
	});
	/**
	 * 页面重新刷新
	 */
	function refreshLocation(){
		initData();
	}

	/**
	 * 删除功能
	 */
	function deleteAction(span){
		var id = $(span).closest('tr').data('obj').id;
		var params = {
				id:id,
		};
		var bn=layer.open({
			content: '您确定要删除吗？',
			area: '600px',
			closeBtn:1,
			shadeClose:true,
			btn: ['确定', '取消'],
			yes: function(index, layero){
				//按钮【确定】的回调
				$.ajax({
					url: URL.deleteUrl,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							window.location.reload(true);
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
				layer.close(bn);
			},btn2: function(index, layero){
				//按钮【取消】的回调
			}
			,cancel: function(){
				//右上角关闭回调
			}
		});

	}

});