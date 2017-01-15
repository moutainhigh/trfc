;(function($,win){
	init();

	//初始化
	function init(){
		//绑定事件
		bindEvent();
		//加载数据
		jqueryData();
	}
	function bindEvent(){
		//修改按钮 绑定点击事件
		$('#systemCode_tbody').on('click','tr .systemCode_edit_button',editCode);
		//修改界面确定按钮 绑定点击事件
		$('#systemCode_edit_ensure').click(toModifyCode);
		//刷新按钮 绑定点击事件
		$('#systemCode_refresh').click(function(){jqueryData();});
		//新增按钮 绑定点击事件
		$('#systemCode_add').click(addCode);
		//新增界面 确定按钮 绑定点击事件
		$('#systemCode_add_ensure').click(toAddCode);
		//删除按钮 绑定点击事件
		$('#systemCode_tbody').on('click','tr [title="删除"]',deleteCode);
		//删除页面 确定按钮 绑定事件
		$('#systemCode_delete_ensure').attr('data-dismiss','modal').click(deleteCodeAction);
	}

	//检测后修改
	function toModifyCode(){
		var code = $('#systemCode_edit_code').val();
		var rawData = $('#systemCode_edit_id').data("code");
		if( code==rawData || checkCode(code) ){
			editCodeAction();
			$('#systemCode_edit_cancle').click();
		}
	}
	//检测后新增
	function toAddCode(){
		var code = $('#systemCode_add_code').val();
		if(checkCode(code)){
			addCodeAction();
			$('#systemCode_add_cancle').click();
		}
	}
	//检测key值是否重复
	function checkCode(code){
		if(!code || code == ""){
			alert("单据代号不可为空!");
			return false;
		}
		var url = "checkCode";
		var param = {code:code};
		var bl = false;
		$.ajax({url:url,
				data:param,
				async:false,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code=='000000'){
						if(result.data){
							bl=true;
						}else{
							alert("单据代号重复!");
						}
					}else{
						layer.msg(result.error,{icon:5});
					}
				}});
		return bl;
	}
	//删除时 提交删除信息
	function deleteCodeAction(){
		var url = "delete";
		var params = {id:param.codeId};
		$.post(url,params,function(result){
			if(result.code=='000000'){
				jqueryData();
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
	//删除时 获取id 并赋值给公共变量
	function deleteCode(){
		var data = $(this).closest('tr').data('codeData');
		param.codeId = data.id;
	}
	var param = {};
	//重置新增页面
	function addCode(){
		$('#systemCode_add_key').val('');
		$('#systemCode_add_code').val('');
		$('#systemCode_add_codeType').val(0);
		$('#systemCode_add_splitType').val(0);
		$('#systemCode_add_codeBegin').val(0);
		$('#systemCode_add_innerCodeBegin').val(0);
		$('#systemCode_add_example').val('');
	}
	//提交新增数据
	function addCodeAction(){
		var key = $('#systemCode_add_key').val();
		var code = $('#systemCode_add_code').val();
		var codeType = $('#systemCode_add_codeType').val();
		var splitType = $('#systemCode_add_splitType').val();
		var codeBegin = $('#systemCode_add_codeBegin').val();
		var innerCodeBegin = $('#systemCode_add_innerCodeBegin').val();
		var example = $('#systemCode_add_example').val();
		var params = {
				key:key,
				code:code,
				codeType:codeType,
				splitType:splitType,
				codeBegin:codeBegin,
				innerCodeBegin:innerCodeBegin,
				example:example
		};
		var url = "add";
		$.post(url,params,function(result){
			if(result.code=='000000'){
				jqueryData();
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	//提交修改数据
	function editCodeAction(){
		var id = $('#systemCode_edit_id').val();
		var key = $('#systemCode_edit_key').val();
		var code = $('#systemCode_edit_code').val();
		var codeType = $('#systemCode_edit_codeType').val();
		var splitType = $('#systemCode_edit_splitType').val();
		var codeBegin = $('#systemCode_edit_codeBegin').val();
		var innerCodeBegin = $('#systemCode_edit_innerCodeBegin').val();
		var example = $('#systemCode_edit_example').val();
		var params = {
				id:id,
				key:key,
				code:code,
				codeType:codeType,
				splitType:splitType,
				codeBegin:codeBegin,
				innerCodeBegin:innerCodeBegin,
				example:example
		};
		var url = "edit";
		$.post(url,params,function(result){
			if(result.code=='000000'){
				jqueryData();
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	//修改 获取原数据
	function editCode(){
		var codeData = $(this).closest('tr').data('codeData');
		$('#systemCode_edit_id').val(codeData.id).data("code",codeData.code);
		$('#systemCode_edit_key').val(codeData.key);
		$('#systemCode_edit_code').val(codeData.code);
		$('#systemCode_edit_codeType').val(codeData.codeType);
		$('#systemCode_edit_splitType').val(codeData.splitType);
		$('#systemCode_edit_codeBegin').val(codeData.codeBegin);
		$('#systemCode_edit_innerCodeBegin').val(codeData.innerCodeBegin);
		$('#systemCode_edit_example').val(codeData.example);
	}
	//加载数据
	function jqueryData(){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = "page";
		var params = {};
		$.post(url,params,function(result){
			if(result.code=='000000'){
				showData(result.data);
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
		layer.close(index);
	}
	//展示列表 
	function showData(list){

		//编码/内码初始值列表
		var codeSelector = new Array('001','0001','00001','000001',
				'0000001','00000001','000000001');
		//分隔符列表
		var splitSelector = new Array('','-','.');
		//编码类型列表
		var typeSelector = new Array('','yyyy','yyyyMM','yyyyMMdd');

		var tbody = $('#systemCode_tbody').empty();
		if(list && !list.length>0){
			layer.msg('暂无数据...');
			return;
		}
		for(var i=0;i<list.length;i++){
			var key = list[i].key || "";
			var code = list[i].code || "";
			var codeType = typeSelector[(list[i].codeType)] || "";
			var splitType = splitSelector[(list[i].splitType)] || "";
			var codeBegin = codeSelector[(list[i].codeBegin)] || "";
			var innerCodeBegin = codeSelector[(list[i].innerCodeBegin)] || "";
			var tr = '<tr>'
				+'<td>'+(i+1)+'</td>'
				+'<td>'+key+'</td>'
				+'<td>'+code+'</td>'
				+'<td>'+codeType+'</td>'
				+'<td>'+splitType+'</td>'
				+'<td>'+codeBegin+'</td>'
				+'<td>'+innerCodeBegin+'</td>'
				+'<td>'+(list[i].example || "")+'</td>'
				+'<td><span >'
				+'<a data-toggle="modal" data-target="#edit" class="systemCode_edit_button"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span>'
				+'<span>'
				+'<a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip"'
				+'data-placement="left" title="删除">&#xe63d;</i></a>'
				+'</span>'	
				+'</td>'
				+'</tr>';
			tr = $(tr);
			tbody.append(tr);
			tr.data('codeData',list[i]);
		}
	}

})(jQuery, window);