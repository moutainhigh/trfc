$(function(){

	var URL = {
			selectorUrl:"/trfc/quality/sales/file/selector",
			checkUrl:"/trfc/quality/sales/file/check",
			pageUrl:"/trfc/quality/sales/file/page",
			deleteUrl:"/trfc/quality/sales/file/delete",
			updateUrl:"/trfc/quality/sales/file/update",
			saveUrl:"/trfc/quality/sales/file/add",
	};
	//绑定刷新按钮
	$('#fresh').click(function(){window.location.reload();});
	//绑定新增按钮
	$('#addBtn').click(addData);
	//绑定跳转按钮
	$("jumpButton").click(jumpPageAction);
	ShowAction(1);
	
	
	function addData(){
		console.log(1);
	}
	
	//页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			batchnumShowAction(pageno);
		}
	}
	//前进一页
	function pageCallback(pageNo){
		batchnumShowAction(pageNo+1);
	}

	//展示数据列表
	function batchnumShowAction(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var materialid = $('#seek_material').val();
		
		
		var materialtype = $('#seek_materialtype').val();
		

		var params = {
				materialid:materialid,
				materialtype:materialtype
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(url,params,function(result){
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
				if(list){
					showPageData(list,pageSize,pageNo);
				}
				layer.close(index);
			}else{
				alert(result.error);
			}
		});
	}
	//展示列表
	function showPageData(list,pageSize,pageNo){
		var SHOWS = {
				0:"全部显示",
				1:"部分显示",
				2:"不显示"
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];

			var assaytime = getNowFormatDate(false,obj.assaytime);
			var starttime = getNowFormatDate(false,obj.starttime);
			var endtime = getNowFormatDate(false,obj.endtime);
			var audittime = null;
			if(obj.audittime){
				audittime = getNowFormatDate(true,obj.audittime);
			}
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+(obj.code || '')+'</td>'
				+'<td class="colorblue">'+(AUDIT[obj.auditstate] || '')+'</td>'
				+'<td class="colorred">'+(BILLS[obj.billsstate] || '')+'</td>'
				+'<td>'+(TEST[obj.teststate] || '')+'</td>'
				+'<td>'+(obj.material || '')+'</td>'
				+'<td>'+(obj.factorycode || '')+'</td>'
				+'<td>'+(3000 || '')+'</td>'
				+'<td>0</td>'
				+'<td>'+(3000 || '')+'</td>'
				+'<td>'+(assaytime || '')+'</td>'
				+'<td>'+(obj.assayer || '')+'</td>'
				+'<td>'+(starttime || '')+'</td>'
				+'<td>'+(endtime || '')+'</td>'
				+'<td>'+(audittime || '')+'</td>'
				+'<td>'+(obj.auditer || '')+'</td>'
				+'<td><span> <a data-toggle="modal"'
				+'		data-target="#dele"><i class="iconfont"'
				+'			data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="停用">&#xe624;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="审核">&#xe692;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="反审">&#xe651;</i></a>'
				+'</span> </span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="复制">&#xe61c;</i></a>'
				+'</span> <span >'
				+'<a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span></td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('batchnum_obj',obj);
		}
	}


	
	
	
	
	
});