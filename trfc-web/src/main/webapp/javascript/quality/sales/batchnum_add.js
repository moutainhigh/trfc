$(function(){
	var URL = {
			selectorUrl:"/trfc/quality/sales/batchnum/selector",
			saveUrl:"/trfc/quality/sales/batchnum/add",
			copyUrl:"/trfc/quality/sales/batchnum/copy",
			checkUrl:"/trfc/quality/sales/batchnum/check"
	};
	//加载下拉框
	materialSelect();
	var reg = new RegExp("id=");
	var href = window.location.href;

	//监听下拉框
	$('#material tr select').change(fillData);
	//保存按钮绑定时间
	$('#save').click(saveAction);

	//判断新增或复制
	if(reg.test(href)){
		loadOld();
	}else{
		loadNew();
	}

	//----------------------------------------------------------------

	function checkFC(factorycode){

		var reg = new RegExp('\\D');
		var bl = false;
		if(!factorycode){
			alert('批号不能为空');
			return bl;
		}else if(factorycode.length<5 || reg.test(factorycode.substr(-5))){
			alert('批号必须大于5位且后5位必须是数字')
			return bl;
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





	function loadOld(){
		//获取id
		var strs = href.split('id=');
		var materialId = strs[1];
		$.post(URL.copyUrl,{id:materialId},function(result){
			if(result.code=='000000'){
				var obj = result.data;

				//制单日期
				$('#add_createtime').val(getNowFormatDate(true,obj.createtime));
				//制单人
				$('#add_creator').val(obj.creator);
				$('#add_assaytime').val(getNowFormatDate(false,obj.assaytime));
				$('#add_starttime').val(getNowFormatDate(false,obj.starttime));
				$('#add_assayer').val(obj.assayer);
				$('#add_endtime').val(getNowFormatDate(false,obj.endtime));
				$('#add_assayorg').val(obj.assayorg);
				//设置ajax执行完成后,执行
				$.when(post1).done(function(){
					$('#material tr:first select').find('option[value="'+obj.material+'"]').attr("selected","selected");
				});
				var tds = $('#material tr:first td');
				tds.eq(2).find('input').val(obj.factorycode);
				tds.eq(3).find('input').val(obj.count);
				tds.eq(4).find('input').val(getNowFormatDate(false,obj.producedtime));
				tds.eq(5).find('input').val(getNowFormatDate(false,obj.testtime));
				tds.eq(6).find('input').val(obj.remark);

			}else{
				layer.msg(result.error, {icon:5});
			}
		});


	}
	function loadNew(){
		//制单日期
		$('#add_createtime').val(getNowFormatDate(true));
		//制单人
		var user = $('.user label').html();
		var nowDate = getNowFormatDate(false);
		$('#add_creator').val(user);
		$('#add_assaytime').val(nowDate);
		$('#add_starttime').val(nowDate);

		$('#add_endtime').val(getLastMonthDay(nowDate));
		$('#add_assayorg').val("卫辉市天瑞水泥有限公司");

	}
	//提交保存数据
	function saveAction(){
		var param = getData();
		if(param){
			if(confirm('确定要保存吗?')){

				$.post(URL.saveUrl,param,function(result){
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
		var assaytime =new Date($('#add_assaytime').val());
		var assayer = $('#add_assayer').val();
		var assayorg = $('#add_assayorg').val();
		var starttime = new Date($('#add_starttime').val());
		var endtime = new Date($('#add_endtime').val());
		var createtime = new Date($('#add_createtime').val());
		var user = $('.user').attr("userid");
		var data = {
				assaytime:assaytime.getTime(),
				assayer:assayer,
				assayorg:assayorg,
				starttime:starttime.getTime(),
				endtime:endtime.getTime(),
				createtime:createtime.getTime(),
				user:user
		};
		var trs = $('#material tr[statu="true"]');
		var arr = new Array();
		for(var i=0;i<trs.length;i++){
			var tds = trs[i].children;
			var material = $(tds[1]).find('select').val();
			var factorycode = $(tds[2]).find('input').val();
			if(!checkFC(factorycode)){
				return null;
			}
			var count = $(tds[3]).find('input').val();
			var producedtime =new Date($(tds[4]).find('input').val());
			var testtime = new Date($(tds[5]).find('input').val());
			var remark = $(tds[6]).find('input').val();
			var mater = {
					material:material,
					factorycode:factorycode,
					count:count,
					producedtime:producedtime.getTime(),
					testtime:testtime.getTime(),
					remark:remark
			}
			arr[i]=mater;
		}
		//讲数组转换为JSON字符串
		data.arrStr = JSON.stringify(arr);
		//判断是否为空,为空则返回null
		if('[]'==data.arrStr){
			alert('物料详细不能为空!');
			data = null;
		}
		return data;
	}

	//当选择下拉框时,填充数据
	function fillData(){
		$(this).closest('tr').removeAttr("statu");
		if($(this).val()){
			var tds = $(this).closest('tr').attr("statu",true).find('input');
			tds[1].value = 5000;
			tds[2].value = getNowFormatDate(false);
			tds[3].value = getNowFormatDate(false);
		}
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
		var select = $('#material tr select');
		select.append("<option></option>");
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var option = '<option value='+obj.id+'>'+(obj.name || '')+'</option>';
				select.append(option);
			}
		}
	}
	function getLastMonthDay(date){
		var year = date.substr(0,4);
		var month = date.substr(5,2);
		var  day = new Date(year,month,0);   
		var lastdate = year + '-' + month + '-' + day.getDate();//获取当月最后一天日期    
//		给文本控件赋值。同下  

		return lastdate;  
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
});