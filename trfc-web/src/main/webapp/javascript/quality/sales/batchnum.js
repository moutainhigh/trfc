$(function(){



	var URL = {
			pageUrl:"/trfc/quality/sales/batchnum/page",
			selectorUrl:"/trfc/quality/sales/batchnum/selector",
			deleteUrl:"/trfc/quality/sales/batchnum/delete",
			updateUrl:"/trfc/quality/sales/batchnum/update",
			addUrl:"/trfc/quality/sales/batchnum/addMain"
	};
	//console.log(1);
	//加载页面
	batchnumShowAction(1);
	materialSelect();
	//绑定搜索按钮
	$('#seek').click(function(){batchnumShowAction(1);});
	//绑定删除按钮
	$('#list').on('click','tr [title="删除"]',deleteAction);
	//绑定审查按钮
	$('#list').on('click','tr [title="审核"]',function(){updateAudit(this,"1");});
	//绑定反审按钮
	$('#list').on('click','tr [title="反审"]',function(){updateAudit(this,'0');});
	
	//绑定停用按钮
	$('#list').on('click','tr [title="停用"]',updateStopState);
	//绑定复制按钮
	$('#list').on('click','tr [title="复制"]',copyAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){batchnumShowAction(1);});
	
	//获取用户id
	var user = $('.user').attr("userid");
	
	
	//--------------------------------------------------------------------------------
	
	//复制
	function copyAction(){
		window.location.replace(URL.addUrl+"?id="+$(this).closest('tr').data('batchnum_obj').id);
	}
	
	//停用
	function updateStopState(){
		var audit = $(this).closest('tr').find('td').eq(3).html();
		if('停用'==audit){
			return;
		}
		var id = $(this).closest('tr').data('batchnum_obj').id;
		var param = {id:id,
				billsstate:'0',
					user:user};
		$.post(URL.updateUrl,param,function(result){
			if(result.code=='000000'){
				//加载页面
				batchnumShowAction(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		
	}
	
	
	//审核
	function updateAudit(bt,auditstate){
		var audit = $(bt).closest('tr').find('td').eq(2).html();
		if(auditstate=='1'){
			if(audit=='已审核'){
				return;
			}
		}
		if(auditstate=='0'){
			if(audit=='待审核'){
				return;
			}
		}
		var id = $(bt).closest('tr').data('batchnum_obj').id;
		var param = {id:id,
					auditstate:auditstate,
					user:user
				};
		$.post(URL.updateUrl,param,function(result){
			if(result.code=='000000'){
				//加载页面
				batchnumShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
	//删除action
	function deleteAction(){
		var id = $(this).closest('tr').data('batchnum_obj').id;
		
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			submitDelete(id);
			layer.close(index);
		}, function(){
		});
	}
	//提交删除信息
	function submitDelete(id){
		$.post(URL.deleteUrl,{id:id},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				batchnumShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}


	function materialSelect(){
		$.post(URL.selectorUrl,{},function(result){
			if(result.code=='000000'){
				fillContent(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
	function fillContent(list){
		var select = $('#seek_material').empty();
		select.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var option = '<option value='+obj.id+'>'+(obj.name || '')+'</option>';
				select.append(option);
			}
		}
	}

	function pageCallback(pageNo){
		batchnumShowAction(pageNo+1);
	}
	//获取当前时间
	function getNowFormatDate(param,time) {
		var date ;
		if(time){
			date = new Date(time);
		}else{
			date = new Date();
		}
		var seperator1 = "-";
		var seperator2 = ":";
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + date.getHours() + seperator2 + date.getMinutes()
			+ seperator2 + date.getSeconds();
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
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
		var code = $('#seek_code').val();
		var factorycode = $('#seek_factorycode').val();
		var material = $('#seek_material').val();
		var billsstate = $('#seek_billsstate').val();

		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
				code:code,
				factorycode:factorycode,
				material:material,
				billsstate:billsstate
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
		var AUDIT = {
				0:"待审核",
				1:"已审核"
		};
		var BILLS = {
				0:"停用",
				1:"启用"	
		};
		var TEST = {
				0:"未化验",
				1:"已化验"
		};

		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];

			var assaytime = getNowFormatDate(false,obj.assaytime);
			//assaytime = assaytime.toLocaleDateString();
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
				+'</span></td>'
				+'</tr>';
			tr=$(tr);
			tbody.append(tr);
			tr.data('batchnum_obj',obj);
		}
	}



});