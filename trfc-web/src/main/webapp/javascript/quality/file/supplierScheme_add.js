$(function(){
	//加载运算符拉框框
	loadSymbol();
	//刷新
	$('#fresh').click(function(){window.location.reload()});
	//保存
	$('#save').click(saveAction);
	//返回
	$('#goBack').click(function(){window.location.replace(URL.mainUrl)});
	initAdd();
	
	//保存数据
	function saveAction(){
		var param = addData();
		if(param){
			//保存数据到服务器
			$.post(URL.saveUrl,param,function(result){
				if('000000'==result.code){
					//更新编码计数
					updateCode();
					//返回列表
					$('#goBack').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	};
	//获取新增数据
	function addData(){
		var code = $('#add_code').val();
		var name = $('#add_name').val();
		var supplierid = $('#add_supplier').val();
		var supremark = $('#add_supremark').val();
		var materialid = $('#add_material').val();
		var schemeid = $('#add_scheme').val();
		var starttime = new Date($('#add_starttime').val());
		starttime = starttime.getTime();
		var endtime = new Date($('#add_endtime').val());
		endtime = endtime.getTime();
		var mean = $('#add_mean').val();
		var deduct = $('#add_deduct').val();
		var invalid = '1';
		if($('#add_invalid').prop('checked')){
			invalid = '0';
		}
		var ref = '1';
		if($('#add_ref').prop('checked')){
			ref = '0';
		}
		var createtime = new Date($('#add_createtime').val());
		createtime = createtime.getTime();
		var remark = $('#add_remark').val();
		var param = {
				code:code,
				name:name,
				supplierid:supplierid,
				supremark:supremark,
				materialid:materialid,
				schemeid:schemeid,
				starttime:starttime,
				endtime:endtime,
				mean:mean,
				deduct:deduct,
				invalid:invalid,
				ref:ref,
				createtime:createtime,
				remark:remark,
				user:userid
		};
		return param;
	}
	
	//初始化新增页面
	function initAdd(){
		supplierSelect();
		materialSelect();
		//设置编码代号为FA
		var code = 'SF';
		//设置类型为编码
		var codeType = true;
		var param = {
				userid:userid,
				code:code,
				codeType:codeType
		};
		//获取编号,并赋值
		$.post(URL.getCodeUrl,param,function(result){
			if(result.code=='000000'){
				$('#add_code').val(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
		//默认开始日期为当月第一天,结束日期为当天
		var now = getNowFormatDate(false);
		var starttime = now.substr(0,now.length-2)+"01";
		$('#add_starttime').val(starttime);
		var endtime = now;
		$('#add_endtime').val(endtime);
		var createtime = getNowFormatDate(true);
		$('#add_createtime').val(createtime);
		var user = $('.user').find('label').html();
		$('#add_creator').val(user);
		
		//绑定物料下拉框监听事件,当物料发生改变,获取对应的质检方案
		$('#add_material').change(schemeSelect);
		//绑定质检方案下拉框监听事件,当发生改变,获取方案详细
		$('#add_scheme').change(getDetailData);
	}
	
	//添加成功后,刷新标号(增1)
	function updateCode(){
		//设置编码代号
		var code = 'SF';
		//编制编号
		var codeType = true;
		var param = {
				userid:userid,
				code:code,
				codeType:codeType
		}; 
		//更新编码
		$.post(URL.updateCodeUrl,param,function(result){
			if(result.code=='000000'){
				//加载列表
				ShowAction(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//加载运算符拉框框
	function loadSymbol(){
		var options = '<option></option><option value="1">小于</option><option value="3">'
			+'小于等于</option><option value="0">大于</option><option value="2">大于等于</option><option value="4">等于</option>';
		$('.comparison').html(options);
	}
	
	//获取下拉框数据并填充
	function schemeSelect(){
		
		//获取数据
		var materialid = $('#add_material').val();
		$selector = $.post(URL.getSchemeUrl,{invalid:"0",materialid:materialid},function(result){
			if(result.code=='000000'){
				//填充数据
				fillSchemeSelect(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
	//填充数据
	function fillSchemeSelect(list){
		var selecter = $('#add_scheme').html('');
		//设置默认值
		selecter.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var msg = obj.name;
				if(obj.spec){
					msg = obj.name + '（'+obj.spec+'）';
				}
				var option = '<option value='+obj.id+'>'+msg+'</option>';
				//追加数据
				selecter.append(option);
			}
		}
	}
	//获取质检方案详细
	function getDetailData(){
		var schemeid=$('#add_scheme').val();
		var param = {
				schemeid:schemeid,
				invalid:'0',
				status:'1'
		};
		$.post(URL.getDetailUrl,param,function(result){
			if('000000'==result.code){
				
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
});