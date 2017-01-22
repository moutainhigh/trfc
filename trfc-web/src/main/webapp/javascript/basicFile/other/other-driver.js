;(function($,win){
	//请求路径
	var URL = {
			pageUrl:"/trfc/basicFile/other/driver/page",
			toAddOtherDriverUrl:"/trfc/basicFile/other/driver/toAddOtherDriver",
			addOtherDriverUrl:"/trfc/basicFile/other/driver/addOtherDriver",
			editOtherDriverUrl:"/trfc/basicFile/other/driver/editOtherDriver",
			deleteOtherDriverUrl:"/trfc/basicFile/other/driver/deleteOtherDriver",
			checkNameUrl:"/trfc/basicFile/other/driver/checkName",
	};
	
	init();
	function init() {
		bindEvent();
		queryData(1);
	}
	
	function bindEvent() {
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addBtn').off('click').on('click',function(){
			toAddOtherDriver();
		});
		$('#add_driver').off('click').on('click',function(){
			addCheckNameAction();
		});
		//监听每页记录改变事件
		$('#pageSize').change(function() {
			queryData(1);
		});
		//跳转按钮绑定 点击事件
		$('#jumpButton').click(jumpPageAction);
		//监听编辑按钮点击事件
		$('#drivers').on('click','tr .update_driver',function(){
			var driver=$(this).closest('tr').data();
//			console.log(driver);
			$('#update_driver_code').val(driver.code);
			$('#update_driver_innercode').val(driver.innercode);
			$('#update_driver_name').val(driver.name);
			$('#update_driver_info').val(driver.info);
			$('#update_driver_idcard').val(driver.idcard);
			$('#update_driver_telphone').val(driver.telphone);
			$('#update_driver_orgname').val(driver.orgname);
			$('#update_driver_remark').val(driver.remark);
			$('#update_driver_isvalid')[0].checked=false;
			if(driver.isvalid==1){
				$('#update_driver_isvalid')[0].checked=true;
			}
			driverData.id=driver.id;
			driverData.name=driver.name;
		});
		//修改其他司机信息
		$('#edit_driver').off('click').on('click',function(){
			updateCheckNameAction();
		});
		//监听删除按钮点击事件
		$('#drivers').on('click','tr .delete_driver',function(){
			var driver=$(this).closest('tr').data();
			driverData.id=driver.id;
			driverData.name=driver.name;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteDriver();
					layer.close(bn);
		        },btn2: function(index, layero){
		            //按钮【取消】的回调
		        }
		        ,cancel: function(){
		            //右上角关闭回调
		        }
		    });
			
		});
		//绑定新增页面输入名称时,键盘按钮按下事件
		$('#driver_name').keyup(function() {
			$('#driver_info').val($(this).val());
		});
		//绑定修改页面输入名称时,键盘按钮按下事件
		$('#update_driver_name').keyup(function() {
			$('#update_driver_info').val($(this).val());
		});
	}
	
	
	function queryData(pageNo) {
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		//获取当前页面记录数
		var pageSize=$('#pageSize').val();
		var name='';
		var innercode='';
		//获取查询条件
		var keyword = $('#driver_keyword').val();
		if($('#driver_query').val()=='name'){
			name=keyword;
		}else{
			innercode=keyword;
		}
		
		var orgname = $('#driver_organizename').val();
		var params={
				pageSize:pageSize,
				namelike:'%'+name+'%',
				innercodelike:'%'+innercode+'%',
				orgname:orgname
		};
		//获取当前页数
		params.pageNo=pageNo;
//		console.log(params);
		$.post(url,params,function(result){
			if(result.code == '000000'){
//				console.log(result.data);
				var data=result.data;
				var list=data.list;
//				console.log(list);
				var pageNo=data.pageNo;
				var pageSize=data.pageSize;
				var total=data.total;
				var tbody=$('#drivers').empty();
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
					var driver=list[i];
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+driver.code+'</td><td>'+driver.innercode+'</td><td>'+driver.name+'</td><td>'+driver.idcard+'</td><td>'+driver.orgname+
							'</td><td>'+driver.remark+'</td><td>'+'<span class="update_driver"><a data-toggle="modal" data-target="#edit" ><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
							'</span><span class="delete_driver">'+'<a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>');
					tr.data(driver);
					tbody.append(tr);
				}
				
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
		layer.close(index);
	}
	
	

	
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	//页面跳转
	function jumpPageAction() {
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			alert('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			queryData(pageno);
		}
	} 
	
	var driverData={};
	
	function toAddOtherDriver(){
		var url=URL.toAddOtherDriverUrl;
		var param={};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(result.data);
				$('#driver_code').val(data.code);
				$('#driver_innercode').val(data.innercode);
				$('#driver_name').val('');
				$('#driver_info').val('');
				$('#driver_idcard').val('');
				$('#driver_telphone').val('');
				$('#driver_orgname').val('');
				$('#driver_isvalid').removeAttr('checked');
				$('#driver_remark').val('');
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	
	function addDriver() {
		var code=$('#driver_code').val().trim();
		var innercode=$('#driver_innercode').val().trim();
		var name=$('#driver_name').val().trim();
		var info=$('#driver_info').val().trim();
		var idcard=$('#driver_idcard').val().trim();
		var telphone=$('#driver_telphone').val().trim();
		var orgname=$('#driver_orgname').val().trim();
		var isvalid = 0;
		if($('#driver_isvalid').prop('checked')){
			isvalid = 1;
		};
		var remark=$('#driver_remark').val().trim();
		
		var url=URL.addOtherDriverUrl;
		var params={
				code:code,
				innercode:innercode,
				name:name,
				info:info,
				idcard:idcard,
				telphone:telphone,
				orgname:orgname,
				isvalid:isvalid,
				remark:remark
		};
//		console.log(params);
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				queryData(1);
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
	}
	
	function updateDriver() {
		var url=URL.editOtherDriverUrl;
		var name=$('#update_driver_name').val().trim();
		var info=$('#update_driver_info').val().trim();
		var telphone=$('#update_driver_telphone').val().trim();
		var orgname=$('#update_driver_orgname').val().trim();
		var remark=$('#update_driver_remark').val().trim();
		var isvalid = 0;
		if($('#update_driver_isvalid').prop('checked')){
			isvalid = 1;
		};
		var params={
				id:driverData.id,
				name:name,
				info:info,
				telphone:telphone,
				orgname:orgname,
				isvalid:isvalid,
				remark:remark
		};
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				queryData(1);
			}else{
				layer.msg(result.error,{icon: 5});
			}
		});
	}
	

	
	
	function deleteDriver() {
		var url=URL.deleteOtherDriverUrl;
		var param={id:driverData.id};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon: 5});
			}
		});
	}
	
	
	
	
	
	
	
	
	//检测新增名称是否存在
	function addCheckNameAction(){
		if(addCheckName()){
			if(confirm("确定要保存吗!")){
				addDriver();
				$('#add_driver').attr('data-dismiss','modal');
			}else{
				$('#add_driver').removeAttr('data-dismiss');
			}
		}else{
			$('#add_driver').removeAttr('data-dismiss');
		}
	}
	//提交新增信息前 检测信息
	function addCheckName(){
		url = URL.checkNameUrl;
		var name = $('#driver_name').val();name=$.trim(name);
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
				updateDriver();
				$('#edit_driver').attr('data-dismiss','modal');
			}else{
				$('#edit .btn-primary').removeAttr('data-dismiss');
			}
		}else{
			$('#edit .btn-primary').removeAttr('data-dismiss');
		}
	}
	//提交修改信息前 检测信息
	function updateCheckName(){
		url = URL.checkNameUrl;
		var name = $('#update_driver_name').val();name=$.trim(name);
		if(!name){
			alert('名称不能为空');
			return false;
		}
		if(name==driverData.name){
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

	
	
	
	
})(jQuery,window);