$(function(){

	//加载下拉框
	userSelect();
	materialSelect();
	//绑定保存按钮
	$('#save').click(updateAction);
	//绑定刷新按钮
	$('#refresh').click(function(){window.location.reload()});
	//加载需要修改的数据
	loadOld();
	
	//获取id
	function getId(){
		var href = window.location.href;
		//获取id
		var strs = href.split('id=');
		var id = strs[1];
		return id;
	}
	//加载数据
	function loadOld(){
		var id = getId();
		//根据id获取数据
		$.post(URL.copyUrl,{id:id},function(result){
			//操作成功
			if(result.code=='000000'){
				var obj = result.data;
				$('#edit_code').val(obj.code);
				//批号
				$('#edit_factorycode').val(obj.factorycode).attr('factorycode',obj.factorycode);
				$('#edit_count').val(obj.count);
				$('#edit_producedtime').val(getNowFormatDate(false,obj.producedtime));
				$('#edit_testtime').val(getNowFormatDate(false,obj.testtime));
				$('#edit_remark').val(obj.remark);
				//制单日期
				$('#edit_createtime').val(getNowFormatDate(true,obj.createtime));
				//制单人
				$('#edit_creator').val(obj.creator);
				$('#edit_assaytime').val(getNowFormatDate(false,obj.assaytime));
				$('#edit_starttime').val(getNowFormatDate(false,obj.starttime));
				$('#user_select').val(obj.assayer);
	
				$('#edit_endtime').val(getNowFormatDate(false,obj.endtime));
				$('#edit_assayorg').val(obj.assayorg);
				
			
				//设置默认值
				$('#edit_material').val(obj.materialname).attr('materialid',obj.material);
				$('#user_select').val(obj.assayername).attr('assayerid',obj.assayer);
				
			}else{
				layer.msg(result.error, {icon:5});
			}
		});


	}
	//提交修改数据
	function updateAction(){
		//获取参数
		var param = getData();
		if(param){
			//弹出对话框
			if(confirm('确定要修改吗?')){
				$.post(URL.updateUrl,param,function(result){
					if(result.code=='000000'){
						//保存成功后,跳转到列表页面
						window.location.assign("/trfc/quality/sales/batchnum/main");
					}else{
						layer.msg(result.error,{icon:5});
					}
				});
			}
		}
	}
	//获取需保存的参数
	function getData(){
		var assaytime =new Date($('#edit_assaytime').val());
		var assayer = $('#user_select').attr('assayerid');
		var assayorg = $('#edit_assayorg').val();
		var starttime = new Date($('#edit_starttime').val());
		var endtime = new Date($('#edit_endtime').val());
		//获取用户id
		var user = $('.user').attr("userid");
		var code = $('#edit_code').val();
		var factorycode = $('#edit_factorycode').val();
		//判断批号
		if(!checkFC(factorycode)){
			return null;
		}
		var count = $('#edit_count').val();
		var material = $('#edit_material').attr('materialid');
		var producedtime = new Date($('#edit_producedtime').val());
		var testtime = new Date($('#edit_testtime').val());
		var remark = $('#edit_remark').val();
		var data = {
				//获取id
				id:getId(),
				assaytime:assaytime.getTime(),
				assayer:assayer,
				assayorg:assayorg,
				starttime:starttime.getTime(),
				endtime:endtime.getTime(),
				user:user,
				factorycode:factorycode,
				count:count,
				material:material,
				producedtime:producedtime.getTime(),
				testtime:testtime.getTime(),
				remark:remark
		};
		//判断物料
		if(!material){
			alert('物料详细不能为空!');
			data = null;
		}
		return data;
	}

	//检测批号是否重复
	function checkFC(factorycode){
		//设置正则表达式
		var reg = new RegExp('\\D');
		var bl = false;
		if(!factorycode){
			alert('批号不能为空');
			return bl;
		}else if(factorycode.length<5 || reg.test(factorycode.substr(-5))){
			alert('批号必须是5~32位且后5位必须是数字')
			return bl;
		}else if(factorycode==$('#edit_factorycode').attr('factorycode')){
			return true;
		}else{
			//判断批号是否重复
			var param = {factorycode:factorycode};
			$.ajax({url:URL.checkUrl,
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
							alert("批号已存在!");
						}
					}else{
						layer.msg(result.error,{icon:5});
					}
				}});
			return bl;
		}
	}



});