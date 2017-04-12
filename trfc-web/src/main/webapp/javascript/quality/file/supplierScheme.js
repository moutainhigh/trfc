$(function(){
	//加载列表
	ShowAction(1);
	initSelect();
	$('#list').on("click","tr",showDetail);
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定新增按钮
	$('#addBtn').click(function(){window.location.replace(URL.addMainUrl)});
	
	//绑定跳转按钮
	$("#jumpButton").click(jumpPageAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){ShowAction(1);});
	//绑定搜索按钮
	$('#seek').click(function(){ShowAction(1);});
	//绑定删除按钮
	$('#list').on('click','tr [title="删除"]',deleteAction);
	//绑定编辑按钮
	$('#list').on('click','tr [title="编辑"]',function(event){
		//停止事件冒泡
		event.stopPropagation();
		//跳转页面
		window.location.replace(URL.editMainUrl+'?id='+$(this).closest('tr').data('obj').id);
	});




	//删除数据
	function deleteAction(event){
		//停止事件冒泡
		event.stopPropagation();
		//获取id
		var id = $(this).closest('tr').data('obj').id;
		//弹出删除确认框
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//提交删除的数据
			submitDelete(id);
			//关闭对话框
			layer.close(index);
		}, function(){
		});
	}
	function submitDelete(id){
		//提交删除数据
		$.post(URL.deleteUrl,{id:id},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				ShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}


//
//
//	页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			ShowAction(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo){
		ShowAction(pageNo+1);
	}

//	展示数据列表
	function ShowAction(pageNo){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var starttime = $('#seek_starttime').val();
		if(starttime){
			starttime = new Date(starttime);
			starttime = starttime.getTime();
		}
		var endtime = $('#seek_endtime').val();
		if(endtime){
			endtime = new Date(endtime);
			endtime = endtime.getTime();
		}
		if(starttime && endtime){
			if(starttime>endtime){
				layer.msg('开始时间不能大于结束时间!',{icon:5});
				layer.close(index);
				return;
			}
		}
		var materialid = $('#seek_material').attr('materialid');
		var supplierid = $('#seek_supplier').attr('supplierid');
		var code = $('#seek_code').val();
		var invalid = $('#seek_invalid').val();
		//放入params对象
		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
				materialid:materialid,
				supplierid:supplierid,
				code:code,
				invalid:invalid
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(URL.pageUrl,params,function(result){
			if(result.code=='000000'){
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
				//如果数据不为空,则展示数据
				if(list){
					showPageData(list,pageSize,pageNo);
				}
				//关闭缓冲图标
				layer.close(index);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo){
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		//清空列表
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var invalid = obj.invalid;
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td>'+(obj.name || '')+'</td>'
				+'<td>'+(obj.suppliername || '')+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.supremark || '')+'</td>'
				+'<td>'+(getNowFormatDate(false,obj.starttime) || '')+'</td>'
				+'<td>'+(getNowFormatDate(false,obj.endtime) || '')+'</td>'
				+'<td><input type="checkbox" '+("0"==invalid?'checked="true"':"")+' disabled="true"></td>'
				+'<td class="colorblue">'+(obj.schemename || '')+'</td>'
				+'<td>'+(obj.remark || '')+'</td>'
				+'<td><span> <a data-toggle="modal"'
				+'		data-target="#edit"><i class="iconfont"'
				+'			data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="删除">&#xe63d;</i></a>'
				+'</span></td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
	}

	//显示详情
	function showDetail(){
		$(".intel_result").css("display", "block");
		var obj = $(this).data('obj');
		var param = {
				schemeid:obj.schemeid,
				invalid:'0',
				status:'1'
		};
		$.post(URL.getDetailUrl,param,function(result){
			if('000000'==result.code){
				var list = result.data;
				var tbody = $('#detail_list').empty();
				//设置TYPE变量 ,作用为 显示结果
				var COMP = {
						0:' >',
						1:' <',
						2:' >=',
						3:' <=',
						4:' ='
				};
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					var invalid = obj.invalid;
					var tr = '<tr>'
						+'<td>'+(i+1)+'</td>'
						+'<td>'+(obj.itemname || '')+'</td>'
						+'<td>'+(COMP[obj.comparison2] || '')+'</td>'
						+'<td>'+(obj.lowlimit || '')+'</td>'
						+'<td>'+(COMP[obj.comparison3] || '')+'</td>'
						+'<td>'+(obj.upperlimit || '')+'</td>'
						+'<td>'+(obj.baseval || '')+'</td>'
						+'<td>'+(obj.floatval || '')+'</td>'
						+'<td>'+(obj.charge || '')+'</td>'
						+'<td>'+(obj.deduct || '')+'</td>'
						+'</tr>';
					//转换为jquery对象
					tr=$(tr);
					//追加
					tbody.append(tr);
				}
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	};
	
});