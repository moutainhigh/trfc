$(function(){

	//加载下拉框
	materialSelect();
	userSelect();
	
	//绑定保存按钮
	$('#save').click(updateAction);
	//绑定刷新按钮
	$('#refresh').click(function(){window.location.reload()});
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
		$.post(URL.copyUrl,{id:id},function(result){
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
				
				//设置ajax执行完成后,执行
				$.when(post1,post2).done(function(){
					//设置默认值
					$('#edit_material').val(obj.material);
					$('#user_select').val(obj.assayer);
					$('.material_select2').select2();
				});
			}else{
				layer.msg(result.error, {icon:5});
			}
		});


	}
	//提交修改数据
	function updateAction(){
		var param = getData();
		if(param){
			if(confirm('确定要修改吗?')){
				$.post(URL.updateUrl,param,function(result){
					if(result.code=='000000'){
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
		var assayer = $('#user_select').val();
		var assayorg = $('#edit_assayorg').val();
		var starttime = new Date($('#edit_starttime').val());
		var endtime = new Date($('#edit_endtime').val());
		var user = $('.user').attr("userid");
		var code = $('#edit_code').val();
		var factorycode = $('#edit_factorycode').val();
		if(!checkFC(factorycode)){
			return null;
		}
		var count = $('#edit_count').val();
		var material = $('#edit_material').val();
		var producedtime = new Date($('#edit_producedtime').val());
		var testtime = new Date($('#edit_testtime').val());
		var remark = $('#edit_remark').val();
		var data = {
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
		
		if(!material){
			alert('物料详细不能为空!');
			data = null;
		}
		return data;
	}
	//下拉框
	function materialSelect(){
		post1 = $.post(URL.selectorUrl,{},function(result){
			if(result.code=='000000'){
				fillContent(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	function fillContent(list){
		var select = $('#edit_material');
		select.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var msg = obj.name;
				if(obj.spec){
					msg = obj.name+' | '+obj.spec;
				}
				var option = '<option value='+obj.id+'>'+msg+'</option>';
				select.append(option);
			}
		}
	}
	//检测是否重复
	function checkFC(factorycode){

		var reg = new RegExp('\\D');
		var bl = false;
		if(!factorycode){
			alert('批号不能为空');
			return bl;
		}else if(factorycode.length<5 || reg.test(factorycode.substr(-5))){
			alert('批号必须大于5位且后5位必须是数字')
			return bl;
		}else if(factorycode==$('#edit_factorycode').attr('factorycode')){
			return true;
		}else{
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