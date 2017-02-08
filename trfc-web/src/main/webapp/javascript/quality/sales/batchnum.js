$(function(){
	
	var URL = {
			pageUrl:"/trfc/quality/sales/batchnum/page"
			
	};
	
	console.log(1);
	batchnumShowAction(1);
	
	
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
//		var namelike = '';
//		var innercodelike='';
		//获取查询条件
//		var keyword = $('#customer_keyword').val().trim();
//		if($('#customer_query').val()=='name'){
//			namelike=keyword;
//		}else{
//			innercodelike=keyword;
//		}
		//获取所属组织
//		var orgnamelike = $('#customer_orgname1').val().trim();
		var params = {
				pageSize:pageSize
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
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+obj.code+'</td>'
				+'<td class="colorblue">'+obj.auditstate+'</td>'
				+'<td class="colorred">'+obj.billsstate+'</td>'
				+'<td>'+obj.teststate+'</td>'
				+'<td>'+obj.material+'</td>'
				+'<td>'+obj.factorycode+'</td>'
				+'<td>'+3000+'</td>'
				+'<td>'+0+'</td>'
				+'<td>'+3000+'</td>'
				+'<td>'+obj.assaytime+'</td>'
				+'<td>'+obj.assayer+'</td>'
				+'<td>'+obj.starttime+'</td>'
				+'<td>'+obj.endtime+'</td>'
				+'<td>'+0+'</td>'
				+'<td>'+0+'</td>'
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
				+'</span></td>'
				+'</tr>';
			tr=$(tr);
			tbody.append(tr);
			tr.data('batchnum_obj',obj);
		}
	}

	
	
});