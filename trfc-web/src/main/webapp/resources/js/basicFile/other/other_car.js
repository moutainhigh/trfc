
$(function(){
//	console.log('OK');
	//加载数据列表，分页查询数据
	listOtherVehicleAction(1);
	//绑定新增按钮点击事件
	$('#showAddVehicle').click(addVehicleAction);
	//绑定新增页面确认按钮点击事件
	$('#add_vehicle').click(addCheckNameAction);
	//绑定搜索按钮点击事件
	$('.btnblue').click(searchAction);
	//绑定跳转按钮点击事件
	$('.fr .btn-default').click(jumpPageAction);
	//监听每页记录改变事件
	$('#pageSize').change(pageSizeChangeAction);
	//绑定刷新按钮点击事件
	$('#refresh').click(refreshPage);
	//绑定编辑按钮点击事件
	$('#vehicles').on('click','tr .update_vehicle',updateVehicleAction);
	//绑定编辑页面确认按钮点击事件
	$('#edit .btn-primary').click(updateCheckNameAction);
	//绑定删除按钮点击事件
	$('#vehicles').on('click','tr .delete_vehicle',deleteVehicleAction);
	//绑定删除页面确认按钮点击事件
	$('#dele .btn-primary').click(deleteVehicle);
	//绑定新增页面输入名称时,键盘按钮按下事件
	$('#vehicle_name').keyup(addkeyUpAction);
	//绑定修改页面输入名称时,键盘按钮按下事件
	$('#update_vehicle_name').keyup(updatekeyUpAction);
});

//显示数据列表
function listOtherVehicleAction(pageNo){
	var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
	var url='page';
	//获取当前页面记录数
	var pageSize=$('#pageSize').val();
	var name='';
	var innercode='';
	//获取查询条件
	var keyword = $('#vehicle_keyword').val();
	if($('#vehicle_query').val()=='name'){
		name=keyword;
	}else{
		innercode=keyword;
	}
	
	var orgname = $('#vehicle_organizename').val();
	var params={
			pageSize:pageSize,
			namelike:'%'+name+'%',
			innercodelike:'%'+innercode+'%',
			orgname:orgname
	};
	//获取当前页数
	params.pageNo=pageNo;
//	console.log(params);
	$.post(url,params,function(result){
		if(result.code == '000000'){
//			console.log(result.data);
			var data=result.data;
			var list=data.list;
//			console.log(list);
			var pageNo=data.pageNo;
			var pageSize=data.pageSize;
			var total=data.total;
			var tbody=$('#vehicles').empty();
			//添加数据总数
			$('.colorred').html(data.total);
			//绑定 一个maxpageno属性
			$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
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
			if(!list){
				return;
			}
			for(var i=0;i<list.length;i++){
				var vehicle=list[i];
				var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+vehicle.code+'</td><td>'+vehicle.innercode+'</td><td>'+vehicle.name+'</td><td>'+vehicle.orgname+
						'</td><td>'+vehicle.remark+'</td><td>'+'<span class="update_vehicle"><a data-toggle="modal" data-target="#edit" ><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
						'</span><span class="delete_vehicle">'+'<a data-toggle="modal" data-target="#dele" ><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>');
				tr.data(vehicle);
//				$('.edit').closest('tr').data();
				tbody.append(tr);
			}
			
		}else{
			layer.msg(result.error, {icon: 5});
		}
		layer.close(index);
	});
		layer.close(index);
}

function pageCallback(pageNo){
	listOtherVehicleAction(pageNo+1);
}

//更改每页记录
function pageSizeChangeAction() {
	listOtherVehicleAction(1);
}

//获取新增时需要的编号和内码
function addVehicleAction(){
	var url="toAddOtherVehicle";
	var param={};
	$.post(url,param,function(result){
		if(result.code=='000000'){
			var data = result.data;
			$('#vehicle_code').val(data.code);
			$('#vehicle_innercode').val(data.innercode);
			//console.log(data);
			$('#vehicle_name').val('');
			$('#vehicle_info').val('');
			$('#vehicle_orgname').val('');
			$('#vehicle_isvalid').removeAttr('checked');
			$('#vehicle_remark').val('');
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}
//新增其他车辆
function addVehicle(){
//	console.log('addOtherVehicle');
	var name=$('#vehicle_name').val();name=$.trim(name);
	var orgname=$('#vehicle_orgname').val();orgname=$.trim(orgname);
	var code=$('#vehicle_code').val();code=$.trim(code);
	var innercode=$('#vehicle_innercode').val();innercode=$.trim(innercode);
	var info=$('#vehicle_info').val();info=$.trim(info);
	var remark=$('#vehicle_remark').val();remark=$.trim(remark);
	var isvalid = 0;
	if($('#vehicle_isvalid').prop('checked')){
		isvalid = 1;
	};
	var url='addOtherVehicle';
	var param={
			code:code,
			innercode:innercode,
			name:name,
			info:info,
			orgname:orgname,
			isvalid:isvalid,
			remark:remark
	};
//	console.log(param);
//	$('#add_vehicle').attr('data-dismiss','modal');
	$.post(url,param,function(result){
//		console.log(result);
		if(result.code=='000000'){
			var data = result.data;
			console.log(data);
			listOtherVehicleAction(1);
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}
//刷新页面
function refreshPage(){
//	location.href='main';
	listOtherVehicleAction(1);
}

//根据条件查询数据
function searchAction(){
	listOtherVehicleAction(1);
}

//页面跳转
function jumpPageAction() {
	var maxpageno = $('#jumpPageNo').attr('maxpageno');
	var pageno = $('#jumpPageNo').val();
	if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
		alert('输入的数字必须在1~'+maxpageno+'之间');
	}else{
		listOtherVehicleAction(pageno);
	}
} 
//将原来的车辆信息显示到编辑页面
function updateVehicleAction() {
//	console.log($(this));
	var tr=$(this).parent().parent();
//	console.log(tr);
	var vehicle=tr.data(vehicle);
//	console.log(vehicle);
	$('#update_vehicle_code').val(vehicle.code);
	$('#update_vehicle_innercode').val(vehicle.innercode);
	$('#update_vehicle_name').val(vehicle.name);
	$('#update_vehicle_info').val(vehicle.info);
	$('#update_vehicle_code').val(vehicle.code);
	$('#update_vehicle_orgname').val(vehicle.orgname);
	$('#update_vehicle_remark').val(vehicle.remark);
	$('#update_vehicle_isvalid').removeAttr('checked');
	$('#update_vehicle_isvalid')[0].checked=false;
	if(vehicle.isvalid==1){
		$('#update_vehicle_isvalid')[0].checked=true;
	}
	vehicleData.id=vehicle.id;
	vehicleData.name=vehicle.name;
}

//页面中的数据模型,用于存储数据
var vehicleData={};

//更改车辆信息
function updateVehicle() {
//	console.log('updateVehicle');
	var url='editOtherVehicle';
	var isvalid = 0;
	if($('#update_vehicle_isvalid').prop('checked')){
		isvalid = 1;
	};
	var params={
			id:vehicleData.id,
			code:$('#update_vehicle_code').val(),
			innercode:$('#update_vehicle_innercode').val(),
			name:$('#update_vehicle_name').val(),
			info:$('#update_vehicle_info').val(),
			orgname:$('#update_vehicle_orgname').val(),
			isvalid:isvalid,
			remark:$('#update_vehicle_remark').val()	
	};
//	console.log(params);
//	$('#update_vehicle').attr('data-dismiss','modal');
	$.post(url,params,function(result){
		if(result.code == '000000'){
//			console.log(result.data);
			listOtherVehicleAction();
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}
//删除按钮点击事件
function deleteVehicleAction() {
	var tr=$(this).parent().parent();
	var vehicle=tr.data(vehicle);
	vehicleData.id=vehicle.id;
}



//删除其他车辆信息
function deleteVehicle() {
//	console.log('deleteVehicle');
	var url='deleteOtherVehicle';
	var param={id:vehicleData.id};
//	console.log(param);
	$('#delete_vehicle').attr('data-dismiss','modal');
	$.post(url,param,function(result){
		if(result.code == '000000'){
//			console.log(result.data);
			listOtherVehicleAction();
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}

//实现新增时简称和名称同步
function addkeyUpAction() {
	$('#vehicle_info').val($(this).val());
}

//实现修改时简称和名称同步
function updatekeyUpAction() {
	$('#update_vehicle_info').val($(this).val());
}

//检测新增名称是否存在
function addCheckNameAction(){
	if(addCheckName()){
		if(confirm("确定要保存吗!")){
			addVehicle();
			$('#add_vehicle').attr('data-dismiss','modal');
		}
	}else{
		$('#add_vehicle').removeAttr('data-dismiss');
	}
}
//提交新增信息前 检测信息
function addCheckName(){
	url = "checkName";
	var name = $('#vehicle_name').val();name=$.trim(name);
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
				layer.msg(result.error, {icon: 5});
			}
		}
	});
	return bl;
}

//检测修改名称是否存在
function updateCheckNameAction(){
	if(updateCheckName()){
		if(confirm("确定要保存吗!")){
			updateVehicle();
			$('#update_vehicle').attr('data-dismiss','modal');
		}
	}else{
		$('#edit .btn-primary').removeAttr('data-dismiss');
	}
}
//提交修改信息前 检测信息
function updateCheckName(){
	url = "checkName";
	var name = $('#update_vehicle_name').val();name=$.trim(name);
	if(!name){
		alert('名称不能为空');
		return false;
	}
	if(name==vehicleData.name){
		return true;
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
				layer.msg(result.error, {icon: 5});
			}
		}
	});
	return bl;
}
