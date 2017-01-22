$(function(){
	
var URL = {
		pageUrl:"/trfc/basicFile/other/supplier/page",
		addUrl:"/trfc/basicFile/other/supplier/add",
		codeUrl:"/trfc/basicFile/other/supplier/code",
		updateUrl:"/trfc/basicFile/other/supplier/update",
		deleteUrl:"/trfc/basicFile/other/supplier/delete"
};	
var params = {};

	showPage(1);
	//绑定 监听修改事件
	$('#pageSize').change(function(){showPage(1)});
	//绑定跳转事件
	$('#jumpButton').click(jumpPageAction);
	//新增按钮 绑定点击事件
	$('#showAddSupplier').click(toInsert);
	//新增界面提交按钮 绑定点击事件
	$('#supplier_add').click(addAction);
	//搜索按钮绑定 点击事件
	$('[class="btn btnblue"]').click(function(){showPage(1);});
	//刷新按钮绑定点击事件
	$('#freshButton').click(function(){window.location.reload();});
	//绑定列表中的修改按钮点击事件
	$('#supplier_list').on('click','tr [title="编辑"]',toModify);
	//绑定修改确认按钮 点击事件
	$('#supplier_modify').click(modifyAction);
	//绑定列表中删除按钮点击事件
	$('#supplier_list').on('click','tr [title="删除"]',todelete);
	
	//绑定新增界面输入姓名时,简称和姓名同步
	$('#add_name').keyup(function(){$('#add_info').val($(this).val());});
	
	//删除数据
	function todelete(){
		var data = $(this).closest('tr').data('supplier');
		params.id = data.id;

		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			deleteAction();
			layer.close(index);
		}, function(){
		});
	}
	//提交删除数据
	function deleteAction(){
		var id = params.id;
		var param = {id:id};
		$.post(URL.deleteUrl,param,function(result){
			if(result.code=='000000'){
				showPage(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//提交修改信息
	function modifyAction(){
		var id = params.id;
		var name = $('#modify_name').val();
		var orgname = $('#modify_orgname').val();
		var info = $('#modify_info').val();
		 var remark = $('#modify_remark').val();
		var isvalid = 0;
		if($('#modify_isvalid').prop('checked')){
			isvalid = 1;
		}
		var userName = $('.user label').html();
		var param = {
				id:id,
				name:name,
				orgname:orgname,
				info:info,
				remark:remark,
				isvalid:isvalid,
				userName:userName
		};
		$.post(URL.updateUrl,param,function(result){
			if(result.code=='000000'){
				showPage(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//展示修改信息
	function toModify(){
		var obj = $(this).closest('tr').data('supplier');
		$('#modify_code').val(obj.code);
		$('#modify_innercode').val(obj.innercode);
		$('#modify_name').val(obj.name);
		$('#modify_orgname').val(obj.orgname);
		$('#modify_info').val(obj.info);
		$('#modify_remark').val(obj.remark);
		$('#modify_isvalid').removeAttr('checked');
		if(obj.isvalid==1){
			$('#modify_isvalid')[0].checked=true;
		}
		params.id = obj.id;
	}
	//提交新增信息
	function addAction(){
		var code = $('#add_code').val();
		var innercode = $('#add_innercode').val();
		var name = $('#add_name').val();
		var orgname = $('#add_orgname').val();
		var info = $('#add_info').val();
		 var remark = $('#add_remark').val();
		var isvalid = 0;
		if($('#add_isvalid').prop('checked')){
			isvalid = 1;
		}
		var userName = $('.user label').html();
		var param = {
				code:code,
				innercode:innercode,
				name:name,
				orgname:orgname,
				info:info,
				remark:remark,
				isvalid:isvalid,
				userName:userName
		};
		$.post(URL.addUrl,param,function(result){
			if(result.code='000000'){
				showPage(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	//显示新增界面
	function toInsert(){
		$('#add_name').val('');
		$('#add_info').val('');
		$('#add_remark').val('');
		$('#add_isvalid')[0].checked=true;
		$('#add_name').val('');
		
		$.post(URL.codeUrl,function(result){
			if(result.code=='000000'){
				$('#add_code').val(result.data.code);
				$('#add_innercode').val(result.data.innercode);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	//页面跳转
	function jumpPageAction(){
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			showPage(pageno);
		}
	}
	//显示列表
	function showPage(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		var keytype = $('#keytype').val();
		var key = $('#key').val();
		var name = null;
		var innercode = null;
		if(keytype=='name'){
			name = key;
		}else{
			innercode = key;
		}
		var pageSize = $('#pageSize').val();
		var orgname = $('#orgname').val();
		var params = {
				pageNo:pageNo,
				pageSize:pageSize,
				nameLike:name,
				innercodeLike:innercode,
				orgname:orgname
		};
		$.post(url,params,function(result){
			if(result.code=='000000'){
				//添加数据总数
				var total = result.data.total;
				//获取当前页
				var pageNo = result.data.pageNO;
				//获取每页显示条数
				var pageSize = result.data.pageSize;
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				$('#supplier_total').html(total);
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
				data2show(result.data);
				layer.close(index);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	function data2show(data){
		var list = data.list;
		if(list!=null && list.length>0){
			var tbody = $('#supplier_list tbody').empty();
			
			for(var i=0;i<list.length;i++){
				var tr = ' <tr>'
					+'<td> '+((data.pageNo-1)*data.pageSize+1+i)+'</td>'
					+' <td>'+(list[i].code || '')+'</td>'
					+' <td>'+(list[i].innercode || '')+'</td>'
					+' <td>'+(list[i].name || '')+'</td>'
					+' <td>'+(list[i].orgname || '')+'</td>'
					+' <td>'+(list[i].remark || '')+'</td>'
					+' <td>'
					+'   <span >'
					+'   <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
					+'</span>'
					+' <span >'
					+'   <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'
					+' </span>'
					+'</td>'
					+'</tr>';
				tr = $(tr);
				tbody.append(tr);
				tr.data('supplier',list[i]);
			}
			
		}
	}
	
	function pageCallback(pageNo){
		showPage(pageNo+1);
	}
	
});