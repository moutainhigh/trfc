$(function(){
	
	//加载数据并展示列表
	CustomersShowAction(1);
	//新增按钮 绑定点击事件
	$('#showAddCustomer').click(toInsert);
	//新增界面提交按钮 绑定点击事件
	$('#customer_add').click(addCustomerAction);
	//每页记录绑定 监听改变事件
	$('#pageSize').change(function(){CustomersShowAction(1);});
	//跳转按钮绑定 点击事件
	$('#jumpButton').click(jumpPageAction);
	//搜索按钮绑定 点击事件
	$('.btnblue').click(searchAction);
	//刷新按钮绑定点击事件
	$('#freshButton').click(function(){window.location.reload();});
	//绑定列表中的修改按钮点击事件
	$('#customer_list').on('click','tr .customer_modify',toModify);
	//绑定修改确认按钮 点击事件
	$('#customer_modify').click(modifyCustomerAction);
	//绑定列表中删除按钮点击事件
	$('#customer_list').on('click','tr .customer_delete',todelete);
	//绑定 删除确认事件
	$('#customer_delete').attr('data-dismiss','modal').click(deleteAction);
	//绑定新增界面输入姓名时,简称和姓名同步
	$('#customer_name').keyup(function(){$('#customer_info').val($(this).val());});

});
//请求路径
var URL = {
		checkNameUrl:"/trfc/basicFile/other/customer/checkName",
		deleteUrl:"/trfc/basicFile/other/customer/delete",
		updateUrl:"/trfc/basicFile/other/customer/update",
		toupdateUrl:"/trfc/basicFile/other/customer/toupdate",
		insertUrl:"/trfc/basicFile/other/customer/insert",
		toinsertUrl:"/trfc/basicFile/other/customer/toinsert",
		pageUrl:"/trfc/basicFile/other/customer/page"
};
//提交修改信息,-->检测名字-->提交
function modifyCustomerAction(){
	var name = $('#customer_modify_name').val().trim();
	if(name==pageData.obj.name||toCheckName(name)){
        $('#customer_modify_cancel').click();      
		modifyAction();
	}
}
//提交新增信息,-->检测名字-->提交
function addCustomerAction(){
	var name = $('#customer_name').val().trim();
	if(toCheckName(name)){
		if(confirm("确定要保存吗!")){
			$('#customer_add_hide').click();
			insert();
		}
	}
}
//检测名字是否重复信息
function toCheckName(name){
	url = URL.checkNameUrl;
	if(!name){
		alert('名称不能为空');
		return false;
	}
	param={name:name};
	var bl = false;
	$.ajax({
		url:url,
		data:param,
		async:false,
		cache:false,
		dataType:'json',
		type:'post',
		success:function(result){
			if(result.code=='000000'){
				if(eval(result.data)){
					bl =  true;
				}else{
					alert("名称已存在");
				}
			}else{
				alert(result.error);
			}
		}
	});
	return bl;
}

//设置一个公用对象
var pageData = {};

function todelete(){
	var id = $(this).parent().parent().data('customer_id');
	//添加id到公用对象
	pageData.customerId=id;
}

//执行删除操作
function deleteAction(){

	var url = URL.deleteUrl;
	var id = pageData.customerId;
	var param = {id:id};
	$.post(url,param,function(result){
		if(result.code=='000000'){
			if($(result.data)){
				CustomersShowAction(1);
			}else{
				alert('删除失败');
			}
		}else{
			alert(result.error);
		}
	});
}
//提交修改操作
function modifyAction(){
	var url = URL.updateUrl;
	//通过公共对象获得id
	var id = pageData.customerId;
	var name = $('#customer_modify_name').val();
	var info = $('#customer_modify_info').val();
	var orgname = $('#customer_modify_orgname').val();
	var remark = $('#customer_modify_remark').val();
	var userName = $('.user label').html();
	var isvalid = 0;
	if($('#customer_modify_isvalid').prop('checked')){
		isvalid = 1;
	}
	
	var obj = pageData.obj;
	var params = {
			id:id,
			name:name,
			info:info,
			orgname:orgname,
			remark:remark,
			isvalid:isvalid,
			userName:userName
	};
	if(name==obj.name&&info==obj.info&&orgname==obj.orgname&&remark==obj.remark&&isvalid==obj.isvalid){
		return;
	}
	$.post(url,params,function(result){
		if(result.code=='000000'){
			CustomersShowAction(1);
		}else{
			alert(result.error);
		}
	});
}
//修改时 获取原数据
function toModify(){
	var tr = $(this).parent().parent();
	var id = tr.data('customer_id');
	var url = URL.toupdateUrl;
	var param = {"id":id};
	pageData.customerId=id;
	$.post(url,param,function(result){
		if(result.code=='000000'){
			var obj = result.data;
			//将数据添加到公共变量上
			pageData.obj = obj;
			$('#customer_modify_code').val(obj.code);
			$('#customer_modify_innercode').val(obj.innercode);
			$('#customer_modify_name').val(obj.name);
			$('#customer_modify_info').val(obj.info);
			$('#customer_modify_orgname').val(obj.orgname);
			$('#customer_modify_remark').val(obj.remark);
			$('#customer_modify_isvalid').removeAttr('checked');
			$('#customer_modify_isvalid')[0].checked=true;
			if(obj.isvalid!=1){
				$('#customer_modify_isvalid')[0].checked=false;
			}
		}else{
			alert(result.error);
		}
	});
}

//搜索功能
function searchAction(){
	CustomersShowAction(1);
}

//页面跳转
function jumpPageAction(){
	var maxpageno = $('#jumpPageNo').attr('maxpageno');
	var pageno = $('#jumpPageNo').val();
	if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
		alert('输入的数字必须在1~'+maxpageno+'之间');
	}else{
		CustomersShowAction(pageno);
	}
}
//获取需要提交的数据
function getInsertData(){
	var params = {};
	var code = $('#customer_code').val().trim();
	var innercode = $('#customer_innercode').val().trim();
	var name = $('#customer_name').val().trim();
	var info = $('#customer_info').val().trim();
	var orgName = $('#customer_orgname').val().trim();
	var userName = $('.user label').html();
	var isvalid = 0;
	if($('#customer_isvalid').prop('checked')){
		isvalid = 1;
	};
	var remark = $("#customer_remark").val().trim();
	var obj = pageData.obj;
	params = {
			code:code,
			innercode:innercode,
			name:name,
			info:info,
			orgname:orgName,
			isvalid:isvalid,
			remark:remark,
			userName:userName
	};
	return params;
}
//提交需要添加的数据
function insert(){
	var url = URL.insertUrl;
	var params = getInsertData();
	$.post(url,params,function(result){
		if(result.code=='000000'){
			CustomersShowAction(1);
		}else{
			alert(result.error);
		}
	});
}
//获取新增 时所需展示的 数据
function toInsert(){
	var url = URL.toinsertUrl;
	$('#customer_name').val("");
	$('#customer_info').val("");
	$('#customer_isvalid')[0].checked=true;
	$("#customer_remark").val("");
	var params = {};
	$.post(url,params,function(result){
		if(result.code=='000000'){
			var data = result.data;
			$('#customer_code').val(data.code);
			$('#customer_innercode').val(data.innercode);
		}else{
			alert(result.error);
		}
	});
}
function pageCallback(pageNo){
	CustomersShowAction(pageNo+1);
}

//展示数据列表
function CustomersShowAction(pageNo){
	var index = layer.load(2, {
		shade: [0.3,'#fff'] //0.1透明度的白色背景
	});
	var url = URL.pageUrl;
	//获取当前页面记录数
	var pageSize = $('#pageSize').val();
	var namelike = '';
	var innercodelike='';
	//获取查询条件
	var keyword = $('#customer_keyword').val().trim();
	if($('#customer_query').val()=='name'){
		namelike=keyword;
	}else{
		innercodelike=keyword;
	}
	//获取所属组织
	var orgnamelike = $('#customer_orgname1').val().trim();
	var params = {
			pageSize:pageSize,
			namelike:namelike,
			innercodelike:innercodelike,
			orgnamelike:orgnamelike
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
			$('#customers_total').html(result.data.total);
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
	//layer.close(index);
}
//展示列表
function showPageData(list,pageSize,pageNo){
	//加载时清空列表和跳转值
	$('#jumpPageNo').val('');
	var tbody = $('#customer_list').empty();
	for(var i=0;i<list.length;i++){
		var tr = '<tr>'
			+' <td>'+((pageNo-1)*pageSize+i+1)+'</td>'
			+' <td>'+(list[i].code || '')+'</td>'
			+' <td>'+(list[i].innercode || '')+'</td>'
			+' <td>'+(list[i].name || '')+'</td>'
			+' <td>'+(list[i].orgname||'')+'</td>'
			+' <td>'+(list[i].remark || '')+'</td>'
			+' <td>'
			+' <span class="customer_modify">'
			+' <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip"'
			+'  data-placement="left" title="编辑">&#xe600;</i></a>'
			+' </span>'
			+' <span class="customer_delete">'
			+' <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip"'
			+' data-placement="left" title="删除">&#xe63d;</i></a>'
			+' </span>'
			+' </td>'
			+' </tr>';
		tr=$(tr);
		tbody.append(tr);
		tr.data('customer_id',list[i].id);
	}
}


