$(function(){

//	console.log('OK');
	//加载页面，显示数据字典列表(默认第一条数据字典被选中)
	listSystemDataDicts();
	//绑定数据字典类别增加按钮点击事件
	$('#showAddDict').click(showAddDictAction);
	//绑定增加数据字典类别页面确认按钮点击事件
	$('#adddict .btn-primary').on('click',function(){
		if($('#adddict').is(':visible')){
			this.disabled = true;
			addDictAction(this);
		}
	});
	//绑定数据字典类别编辑按钮点击事件
	$('#showEditDict').click(showEditDictAction);
	//监听数据字典类别点击事件，显示数据字典明细列表
	$('#dicts').on('click','li',showSystemDataDictItems);
	//绑定数据字典类别编辑页面确认按钮点击事件
	$('#editdict .btn-primary').on('click',function(){
		if($('#editdict').is(':visible')){
			this.disabled = true;
			editDataDict(this);
		}
	});
	//绑定数据字典类别删除页面确认按钮点击事件
	$('#showDeleteDict').click(deleteDataDict);
	//绑定数据字典明细新增按钮点击事件
	$('#showAddItem').click(showAddItemAction);
	//绑定新增数据字典明细页面确认按钮点击事件
	$('#additem .btn-primary').on('click',function(){
		if($('#additem').is(':visible')){
			this.disabled = true;
			addItemAction(this);
		}
	});
	//绑定数据字典明细编辑按钮点击事件
	$('#items').on('click','tr .update_item',updateItemAction);
	//绑定数据字典明细编辑页面确认按钮点击事件
	$('#edititem .btn-primary').on('click',function(){
		if($('#edititem').is(':visible')){
			this.disabled = true;
			updateItem(this);
		}
	});
	//绑定数据字典明细删除按钮点击事件
	$('#items').on('click','tr .dele_item',deleteItemAction);
	//绑定数据字典明细删除页面确认按钮点击事件
//	$('.delete').click(deleteItem);
	//绑定刷新按钮点击事件
	$('#refresh').click(refreshPage);
});
//请求路径
var URL = {
		listDictUrl:"/trfc/system/base/dataDict/listDict",
		addDictUrl:"/trfc/system/base/dataDict/addDict",
		listItemUrl:"/trfc/system/base/dataDict/listItem",
		editDictUrl:"/trfc/system/base/dataDict/editDict",
		deleteDictUrl:"/trfc/system/base/dataDict/deleteDict",
		addItemUrl:"/trfc/system/base/dataDict/addItem",
		editItemUrl:"/trfc/system/base/dataDict/editItem",
		deleteItemUrl:"/trfc/system/base/dataDict/deleteItem"
};
//设置两个公用对象用来存储数据
var dictData={};
var itemData={};
//显示数据字典类别列表
function listSystemDataDicts() {
	var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
	
	var url=URL.listDictUrl;
	var param={};
	var tbody=$('#items').empty();
	$.post(url,param,function(result){
		if(result.code == '000000'){
			var data=result.data;
//			console.log(result.data);
			var total=data.length;
//			console.log(total);
			dictData.total=total;
			var sys_data=$('#sys_data').empty();
			var sys_yewu=$('#sys_yewu').empty();
			for(var i=0;i<data.length;i++){
				var dict=data[i];
//				console.log(dict);
				var li=$('<li>'+'<a href="#"><i class="iconfont">&#xe60b;</i>'+
						 '<label>'+dict.name+'</label></a></li>');
				li.data(dict);
				if(dict.type==1){
					sys_data.append(li);
				}else{
					sys_yewu.append(li);
				}
			}
			//加载页面后默认第一条数据字典被选中
			$('#sys_data').children().eq(0).click();
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
			layer.close(index);
}
//获取新增时需要的编号
function showAddDictAction() {
//	console.log('showAddDictAction');
	var total=dictData.total;
//	console.log(total);
	$('#dict_code').val(total+1);
	$('#dict_name').val('');
	$('#dict_type').val(1);
	$('#dict_info').val('');
}
//增加数据字典类别
function addDictAction(_this) {
	var url=URL.addDictUrl;
	var code=$('#dict_code').val().trim();
	var name=$('#dict_name').val().trim();
	var type=$('#dict_type').val();
	var info=$('#dict_info').val().trim();
	var params={
			code:code,
			name:name,
			type:type,
			info:info
	};
	if(validate(params)){
		var index=layer.load(2,{
			shade:[0.3,'#fff']
		});
		$.post(url,params,function(result){
			if(result.code == '000000'){
				_this.disabled=false;
				location.reload(true);
			}else{
			   layer.msg(result.error, {icon: 5});
				_this.disabled=false;
			}
			layer.close(index);
		});
	}else{
		_this.disabled=false;
	}
	
}
function validate(params){
	if(!params.name){
		layer.msg('名称不能为空!',{icon:5});
		return false;
	}
	return true;
}	



//将原来的数据字典类别信息显示到编辑页面
function showEditDictAction() {
//	console.log('showEditDictAction');
	var dict=dictData.dict;
	$('#update_dict_code').val(dict.code);
	$('#update_dict_name').val(dict.name);
	$('#update_dict_type').val(dict.type);
	$('#update_dict_info').val(dict.info);
}
//显示数据字典明细列表
function showSystemDataDictItems() {
//	console.log('showSystemDataDictItems');
	var index = layer.load(2, {
		  shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
	
	//设置选中数据字典的显示效果
	$('#dicts li').removeClass('data_selected');
	$(this).addClass('data_selected');
	
	var dict=$(this).data(dict);
	dictData.dict=dict;
	var dictid=dict.id;
	var name=dict.name;
	var code=dict.code;
//	console.log(dictid);
	//更改辅助资料明细标题
	$('#fuzhu_dtile_name').html('辅助资料明细 - '+name);
	
	var url=URL.listItemUrl;
	var param={dictid:dictid};
	$.post(url,param,function(result){
		if(result.code == '000000'){
			var data=result.data;
//			console.log(data);
			var tbody=$('#items').empty();
			if(!data){
				itemData.total=0;
				return;
			}
			var total=data.length;
			itemData.total=total;
			for(var i=0;i<data.length;i++){
				var item=data[i];
				
				var tr=$('<tr><td>'+(i+1)+'</td><td>'+item.code+'</td><td>'+item.name+'</td><td><input type="checkbox" disabled id="item_isvalid'+i+'" ></td>'+
						 '<td><input type="checkbox" disabled checked="true"></td>'+
						 '<td><input type="checkbox" disabled checked="true"></td><td>'+(code+1)+'</td><td>'+item.info+'</td><td><span class="update_item">'+
						 '<a data-toggle="modal" data-target="#edititem"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'+
						 '<span class="dele_item"><a data-toggle="modal" data-target="#deleitem"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+
						 '</span></td></tr>');
				tr.data(item);
				tbody.append(tr);
				$('#item_isvalid'+i)[0].checked=false;
				if(item.isvalid==1){
					$('#item_isvalid'+i)[0].checked=true;
				}
			}
			
		}else{
		   layer.msg(result.error, {icon: 5});
		}
	});
	layer.close(index);
}
//修改数据字典类别
function editDataDict(_this) {
//	console.log('editDataDict');
	
	var url=URL.editDictUrl;
	var code=$('#update_dict_code').val().trim();
	var name=$('#update_dict_name').val().trim();
	
	var type=$('#update_dict_type').val();
	var info=$('#update_dict_info').val().trim();
	var params={
			id:dictData.dict.id,
			code:code,
			name:name,
			type:type,
			info:info
	};
	if(validate(params)){
		var index=layer.load(2,{
			shade:[0.3,'#fff']
		});
		$.post(url,params,function(result){
			if(result.code == '000000'){
				_this.disabled=false;
				location.reload(true);
			}else{
			   layer.msg(result.error, {icon: 5});
				_this.disabled=false;
			}
			layer.close(index);
		});
	}else{
		_this.disabled=false;
	}
	
}
//删除数据字典类别
function deleteDataDict() {
	var url=URL.deleteDictUrl;
	var param={id:dictData.dict.id};
	var bn=layer.open({
        content: '您确定要删除吗？',
        area: '600px',
        closeBtn:1,
        shadeClose:true,
        btn: ['确定', '取消'],
        yes: function(index, layero){
            //按钮【确定】的回调
        	$.post(url,param,function(result){
        		if(result.code == '000000'){
        			listSystemDataDicts();
        		}else{
        			layer.msg('此条数据不能被删除哦！',{icon: 5});
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
//获取新增数据字典明细时需要的编号
function showAddItemAction() {
//	console.log('showAddItemAction');
	var tr=$('#items tr').first();
//	console.log(tr);
	var item_code=tr.children().eq(1).html();
//	console.log(item_code);
	if(!item_code){
		item_code=0;
	}
//	var total=itemData.total;
//	console.log(total);
	$('#item_code').val(parseInt(item_code)+1);
	$('#item_name').val('');
	$('#item_addisvalid').prop('checked','true');
	$('#item_info').val('');
	
}
//新增数据字典明细
function addItemAction(_this) {
	
	var url=URL.addItemUrl;
	var code=$('#item_code').val().trim();
	var name=$('#item_name').val().trim();
	var isvalid = 0;
	if($('#item_addisvalid').prop('checked')){
		isvalid = 1;
	};
	var info=$('#item_info').val().trim();
	var dictid=dictData.dict.id;
	var params={
			dictid:dictid,
			code:code,
			name:name,
			isvalid:isvalid,
			info:info
	};
	
	if(validate(params)){
		var index=layer.load(2,{
			shade:[0.3,'#fff']
		});
		$.post(url,params,function(result){
			if(result.code == '000000'){
				_this.disabled=false;
//				$('#additem .btn-primary').attr('data-dismiss','modal');
//				$('.data_selected').click();
				location.reload(true);
			}else{
			   layer.msg(result.error, {icon: 5});
				_this.disabled=false;
			}
			layer.close(index);
		});
	}else{
		_this.disabled=false;
	}
	
}
//将原来的数据字典明细信息显示到编辑页面
function updateItemAction() {
	var tr=$(this).parent().parent();
	var item=tr.data(item);
	itemData.id=item.id;
	$('#update_item_code').val(item.code);
	$('#update_item_name').val(item.name);
	$('#update_item_isvalid')[0].checked=false;
	if(item.isvalid==1){
		$('#update_item_isvalid')[0].checked=true;
	}
	$('#update_item_info').val(item.info);
}
//修改数据字典明细
function updateItem(_this) {
	var url=URL.editItemUrl;
	var code=$('#update_item_code').val().trim();
	var name=$('#update_item_name').val().trim();
	var isvalid = 0;
	if($('#update_item_isvalid').prop('checked')){
		isvalid = 1;
	};
	var info=$('#update_item_info').val().trim();
	var params={
			id:itemData.id,
			code:code,
			name:name,
			isvalid:isvalid,
			info:info
	};
	if(validate(params)){
		var index=layer.load(2,{
			shade:[0.3,'#fff']
		});
		$.post(url,params,function(result){
			if(result.code == '000000'){
				_this.disabled=false;
				location.reload(true);
			}else{
			   layer.msg(result.error, {icon: 5});
				_this.disabled=false;
			}
			layer.close(index);
		});
	}else{
		_this.disabled=false;
	}
	
}
//获取删除时所需要的id
function deleteItemAction() {
//	console.log('deleteItemAction');
	var tr=$(this).parent().parent();
	var item=tr.data(item);
	itemData.id=item.id;
//	console.log(item.id);
	
	var bn=layer.open({
        content: '您确定要删除吗？',
        area: '600px',
        closeBtn:1,
        shadeClose:true,
        btn: ['确定', '取消'],
        yes: function(index, layero){
            //按钮【确定】的回调
			deleteItem();
			layer.close(bn);
        },btn2: function(index, layero){
            //按钮【取消】的回调
        }
        ,cancel: function(){
            //右上角关闭回调
        }
    });
}
//删除选中的数据字典明细
function deleteItem() {
//	console.log('deleteItem');
	var url=URL.deleteItemUrl;
	var param={id:itemData.id};
//	console.log(param);
//	$('#deleitem .btn-primary').attr('data-dismiss','modal');
//	layer.closeAll();
	$.post(url,param,function(result){
		if(result.code == '000000'){
			$('.data_selected').click();
		}else{
			layer.msg(result.error, {icon: 5});
		}
	});
}
//刷新页面
function refreshPage() {
	listSystemDataDicts();
}

