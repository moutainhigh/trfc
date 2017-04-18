$(function(){
	//1加载数据显示列表	
	MaterialShowAction(1);
	
	//2列表中删除按钮点击事件
	$('#material_list').on('click','tr .material_delete',toDelete);
	// 删除确认事件
//	$('#material_delete').attr('data-dismiss','modal').click(deleteAction);
	
	//3列表中的修改按钮 点击事件
	$('#material_list').on('click','tr .material_modify',toModify);
	//修改确认按钮 点击事件
	$('#material_modify').attr('data-dismiss','modal').click(modifyAction);
	
	//4新增按钮点击事件
	$('#showAddMaterial').click(toInsert);
	//新增界面提交点击事件
	$('#material_add').attr('data-dismiss',"modal").click(insertAction);
	
	//5搜索按钮绑定 点击事件
	$('.btnblue').click(searchAction);
	
	//6刷新按
	$('#freshButton').click(function(){window.location.reload(true);});
	
	//7跳转页面
	$('#jumpButton').click(jumpPageAction);
	
	
	//8每页记录 监听改变事件
	$('#pageSize').change(function(){MaterialShowAction(1);});
	
});

//请求路径
var URL = {
		deleteUrl:"/trfc/basicFile/other/material/deleteOtherBdMaterial",
		updateUrl:"/trfc/basicFile/other/material/updateOtherBdMaterial",
		toupdateUrl:"/trfc/basicFile/other/material/toUpdate",
		insertUrl:"/trfc/basicFile/other/material/addOtherBdMaterial",
		toinsertUrl:"/trfc/basicFile/other/material/toInsert",
		pageUrl:"/trfc/basicFile/other/material/page"
};

//7页面跳转
function jumpPageAction(){
	var maxpageno = $('#jumpPageNo').attr('maxpageno');
	var pageno = $('#jumpPageNo').val();
	if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
		layer.msg('输入的数字必须在1~'+maxpageno+'之间');
	}else{
		
		MaterialShowAction(pageno);
	}
}


//5搜索功能
function searchAction(){
	MaterialShowAction(1);
}

//4获取新增 时所需展示的 数据
function toInsert(){
	var url =URL.toinsertUrl;
	var params = {};
	$.post(url,params,function(result){
		if(result.code=='000000'){
			var data = result.data;
			$('#material_code').val(data.code);
			$('#material_innercode').val(data.innercode);
			//console.log(data);
			$('#material_name').val('');
			$('#material_info').val('');
			$('#material_orgname').val('');
			$('#material_isvalid').removeAttr('checked');
			$('#material_remark').val('');
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}
//提交需要添加的数据
function insertAction(){
	var url = URL.insertUrl;
	var code = $('#material_code').val();
	var innercode = $('#material_innercode').val();
	var name = $('#material_name').val();
	if(!name){
		$('#add .btn-primary').attr('data-dismiss','modal');
		return;
	}
	var info = $('#material_info').val();
	var orgName = $('#material_orgname').val();
	var isvalid = 0;
	if($('#material_isvalid').prop('checked')){
		isvalid = 1;
	};
	var remark = $("#material_remark").val();
	var params = {
			code:code,
			innercode:innercode,
			name:name,
			info:info,
			orgname:orgName,
			isvalid:isvalid,
			remark:remark
	};
//	console.log(params);
	$.post(url,params,function(result){
		if(result.code=='000000'){
			MaterialShowAction(1);
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}



//3修改之前 获取原始数据
function toModify(){
	var tr = $(this).parent().parent();
	var id = tr.data('material_id');
	var url = URL.toupdateUrl;
	var param = {"id":id};
	pageData.materialId=id;
	$.post(url,param,function(result){
		if(result.code=='000000'){
			var obj = result.data;
			$('#material_modify_code').val(obj.code);
			$('#material_modify_innercode').val(obj.innercode);
			$('#material_modify_name').val(obj.name);
			
			$('#material_modify_info').val(obj.info);
			$('#material_modify_orgname').val(obj.orgname);
			$('#material_modify_remark').val(obj.remark);
			//$('#material_modify_isvalid').removeAtter('checked');
			$('#material_modify_isvalid')[0].checked=false;
			if(obj.isvalid==1){
				$('#material_modify_isvalid')[0].checked=true;
				//$('#material_modify_isvalid').attr('checked','checked');
				
			}
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}

//提交修改
function modifyAction(){
	var url = URL.updateUrl;
	//通过公共对象获得id
	var id = pageData.materialId;
	var name = $('#material_modify_name').val();
	if(!name){
		$('#edit .btn-primary').attr('data-dismiss','modal');
		return;
	}
	var info = $('#material_modify_info').val();
	var orgname = $('#material_modify_orgname').val();
	var remark = $('#material_modify_remark').val();
	var isvalid = 0;
	if($('#material_modify_isvalid').prop('checked')){
		isvalid = 1;
	}
	var params = {
			id:id,
			name:name,
			info:info,
			orgname:orgname,
			remark:remark,
			isvalid:isvalid
	};
	$.post(url,params,function(result){
		if(result.code=='000000'){
			MaterialShowAction(1);
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}






//设置一个公用对象
var pageData = {};

function toDelete(){
	var id = $(this).parent().parent().data('material_id');
	//添加id到公用对象
	pageData.materialId=id;
	
	var bn=layer.open({
        content: '您确定要删除吗？',
        area: '600px',
        closeBtn:1,
        shadeClose:true,
        btn: ['确定', '取消'],
        yes: function(index, layero){
            //按钮【确定】的回调
        	deleteAction();
			layer.close(bn);
        },btn2: function(index, layero){
            //按钮【取消】的回调
        }
        ,cancel: function(){
            //右上角关闭回调
        }
    });
}

//2执行删除操作
function deleteAction(){	
	var url = URL.deleteUrl;
	var id = pageData.materialId;
	var param = {id:id};
	$.post(url,param,function(result){
		if(result.code=='000000'){
			if($(result.data)){
				MaterialShowAction(1);
			}else{
				layer.msg(result.error, {icon: 5});
			}
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}

function getParams(){
	var params={};
	return params;
}
function pageCallback(pageNo){
	MaterialShowAction(pageNo+1);
}
//1加载并显示数据
function MaterialShowAction(pageNo){
	
	var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
	
	
	var url=URL.pageUrl;
	//var url = "/trfc-web/Other/page";
	//获取当前页面记录数	
	var pageSize=$('#pageSize').val();
	var name='';
	var innercode='';
	//获取查询条件
	var keyword=$('#material_keyword').val().trim();
	if($('#material_query').val()=='name'){
		name=keyword;
	}else{
		innercode=keyword;
	}
	var params = {
			pageSize:pageSize,
			name:name,
			innercode:innercode
		};
	//获取当前页面标记
	params.pageNo=pageNo;
//	console.log(params);
	$.post(url,params,function(result){
          if(result.code=='000000'){
			
			var list = result.data.list;
			
			var pageNo = result.data.pageNo;
			
			var pageSize = result.data.pageSize;
			
			var tbody = $('#material_list');
			var total = result.data.total;
			tbody.empty();
			//添加数据总数
			$('#material_total').html(result.data.total);
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
				
				var tr = '<tr>'
					+' <td>'+((pageNo-1)*pageSize+i+1)+'</td>'
					+' <td>'+(list[i].code || '')+'</td>'
					+' <td>'+(list[i].innercode || '')+'</td>'
					+' <td>'+(list[i].name || '')+'</td>'
					+' <td>'+(list[i].orgname||'')+'</td>'
					+' <td>'+(list[i].remark || '')+'</td>'
					+' <td>'
					+' <span class="material_modify">'
					+' <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip"'
					+'  data-placement="left" title="编辑">&#xe600;</i></a>'
					+' </span>'
					+' <span class="material_delete">'
					+' <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip"'
					+' data-placement="left" title="删除">&#xe63d;</i></a>'
					+' </span>'
					+' </td>'
					+' </tr>';
				tr=$(tr);
				tbody.append(tr);
				tr.data('material_id',list[i].id);
			}
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});		
	layer.close(index);
}







