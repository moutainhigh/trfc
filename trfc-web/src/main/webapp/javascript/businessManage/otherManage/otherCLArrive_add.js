$(function() {
	URL = {	
			mainUrl:"/trfc/otherCLArrive/main",
			addUrl:"/trfc/otherCLArrive/add",
			findOne:"/trfc/otherCLArrive/findOne",
			getCodeUrl:"/trfc/quality/sales/report/getCode",
			updateCodeUrl:"/trfc/quality/sales/report/updateCode",
			addVehicle: '/trfc/vehicle/add',
			addDriver: '/trfc/driver/add',
			vehicleAutoCompleteSearch: "/trfc/vehicle/autoCompleteSearch",
			driverAutoCompleteSearch: "/trfc/driver/autoCompleteSearch",
			materielAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			supplierAutoCompleteSearch: "/trfc/supplier/autoCompleteSearch",
			warehouseAutoCompleteSearch: "/trfc/warehouse/autoCompleteSearch",
	};
	//获取用户名
	var userid = $('.user').attr('userid');
	initAddData();

	//绑定车辆新增按钮
	$('#add_vehicle + a').click(function() {
		initVehicleModel();
	});
	//绑定司机新增按钮
	$('#add_driver + a').click(function() {
		initDriverModel();
	});
	//绑定车辆和司机保存按钮
	$('#vehicle_sure').click(function() {
		saveVehicleData();
	});
	$('#driver_sure').click(function() {
		saveDriverData();
	});

	$('#saveBtn').click(function() {
		saveDataAction();
	});

	$('#goback').click(function() {
		window.location.href = URL.mainUrl;
	});

	$('#refresh').click(function() {
		window.location.reload(true);
	});


	//日期字符串转为时间戳
	function str2Long(dateStr){
		if(dateStr){
			return Date.parseYMD_HMS(dateStr).getTime();
		}
		return '';
	}

	function getAddData() {
		var materielid = $('#add_materiel').attr('materielid');
		var receivedepartmentid = $('#add_receivedepartmentid').attr('orgid');
		var vehicleid = $('#add_vehicle').attr('vehicleid');
		var driverid = $('#add_driver').attr('driverid');
		var createtime = $('#add_createtime').val();
		var remark = $('#add_remark').val();
		var reason = $('#add_reason').val();
		return {
			materielid:materielid,
			receivedepartmentid:receivedepartmentid,
			vehicleid:vehicleid,
			driverid:driverid,
			createtime:str2Long(createtime),
			remark:remark,
			reason:reason
		};

	}
	function validata(params) {
		
/*		if(!params.materielid){
			layer.msg('物料不能为空!');return false;
		}*/
		if(!params.vehicleid){
			layer.msg('车辆不能为空!');return false;
		}
/*		if(!params.driverid){
			layer.msg('司机不能为空!');return false;
		}*/
		
		return true;
	}


	function saveDataAction() {
		var params = getAddData();
		if(params && validata(params)){
			layer.confirm('注：确定要保存吗？', {
				area: '600px',
				btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					url:URL.addUrl,
					data:params,
					async:true,
					cache:false,
					dataType:'json',
					type:'post',
					success:function(result){
						if(result.code == '000000'){
							window.location.href = URL.mainUrl;
						}else{
							layer.msg(result.error, {icon: 5});
						}
					}
				});
			});
		}

	}


	function initAutoComplete() {
		var cache = {};
		$("#add_vehicle").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var vehicle = cache['vehicle'] || {};
				if ( term in vehicle ) {
					response( vehicle[ term ] );
					return;
				}
				$.post( URL.vehicleAutoCompleteSearch, request, function( data, status, xhr ) {
					vehicle[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.vehicleno;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.vehicleno).attr('vehicleid', ui.item.id).attr('vehiclecode',ui.item.code);
				$('#add_rfid').val(ui.item.rfid);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('vehicleid').removeAttr('vehiclecode');
		}).change(function(){
			if(!$(this).attr('vehicleid') && !$(this).attr('vehiclecode')){
				$(this).val('');
			}
		});
		$("#add_driver").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var driver = cache['driver'] || {};
				if ( term in driver ) {
					response( driver[ term ] );
					return;
				}
				$.post( URL.driverAutoCompleteSearch, request, function( data, status, xhr ) {
					driver[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('driverid', ui.item.id);
				$('#add_identityno').val(ui.item.identityno);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('driverid');
		}).change(function(){
			if(!$(this).attr('driverid')){
				$(this).val('');
			}
		});
		$("#add_materiel").autocomplete({
			source: function( request, response ) {
				var term = request.term;
				var materiel = cache['materiel'] || {};
				if ( term in materiel ) {
					response( materiel[ term ] );
					return;
				}
				$.post( URL.materielAutoCompleteSearch, request, function( data, status, xhr ) {
					materiel[ term ] = data;
					response( data );
				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('materielid', ui.item.id).attr('packagetype',ui.item.packagetype);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input propertychange',function(){
			$(this).removeAttr('materielid');
		}).change(function(){
			if(!$(this).attr('materielid')){
				$(this).val('');
			}
		});
		
	}

	function saveDriverData() {
		var name = $('#driver_name').val();
		var abbrname = $('#driver_abbrname').val();
		var address = $('#driver_address').val();
		var orgname = $('#driver_org').val();
		var orgid = $('#driver_org').attr('orgid');
		var telephone = $('#driver_telephone').val();
		var identityno = $('#driver_identityno').val();
		var isvalid = '0';
		if($('#driver_isvalid').get(0).checked){
			isvalid = '1';
		}
		var remarks = $('#driver_remarks').val();
		var params = {
				name:name,
				abbrname:abbrname,
				address:address,
				orgname:orgname,
				orgid:orgid,
				telephone:telephone,
				identityno:identityno,
				isvalid:isvalid,
				remarks:remarks
		}
		$.post(URL.addDriver,params,function(result) {
			if(result.code=='000000'){
				layer.msg(result.error, {icon: 1});
				var driver = result.data;
				$('#add_driver').val(driver.name).attr('driverid',driver.id);
				$('#add_identityno').val(driver.identityno);
				$('#driver_cancel').click();
			}else{
				layer.msg(result.error,{icon:5});
			}
		})

	}

	function saveVehicleData() {
		var vehicletype = $('#vehicle_vehicletype').val();
		var transportunit = $('#vehicle_transportunit').val();
		var maxweight = $('#vehicle_maxweight').val();
		var tareweight = $('#vehicle_tareweight').val();
		var owner = $('#vehicle_owner').val();
		var telephone = $('#vehicle_telephone').val();
		var address = $('#vehicle_address').val();
		var org = $('#vehicle_org').val();
		var isvalid = '0';
		if($('#vehicle_isvalid')[0].checked){
			isvalid = '1';
		}
		var remarks = $('#vehicle_remarks').val();
		var vehicleno = $('#vehicle_vehicleno').val();
		var params = {
				vehicletype:vehicletype,
				transportunit:transportunit,
				maxweight:maxweight,
				tareweight:tareweight,
				owner:owner,
				telephone:telephone,
				address:address,
				org:org,
				isvalid:isvalid,
				remarks:remarks,
				vehicleno:vehicleno
		}
		$.post(URL.addVehicle,params,function(result) {
			if(result.code == '000000'){
				layer.msg(result.error, {icon: 1});
				var vehicle = result.data;
				$('#add_vehicle').val(vehicle.vehicleno).attr('vehicleid',vehicle.id);
				$('#vehicle_cancel').click();
			}else{
				layer.msg(result.error,{icon:5});
			}
		})
	}

	function initVehicleModel() {

		//设置编码代号为FA
		var code = 'CL';
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
				$('#vehicle_code').val(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
		$('#vehicle_vehicletype').val('');
		$('#vehicle_transportunit').val('');
		$('#vehicle_maxweight').val('');
		$('#vehicle_tareweight').val('');
		$('#vehicle_owner').val('');
		$('#vehicle_telephone').val('');
		$('#vehicle_address').val('');
		$('#vehicle_isvalid')[0].checked=false;
		$('#vehicle_remarks').val('');
		$('#vehicle_vehicleno').val('');

	}
	function initDriverModel() {
		//设置编码代号为FA
		var code = 'DR';
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
				$('#driver_code').val(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
		param.codeType = false;
		//获取内码,并赋值
		$.post(URL.getCodeUrl,param,function(result){
			if(result.code=='000000'){
				$('#driver_internalcode').val(result.data);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
		$('#driver_name').val('');
		$('#driver_abbrname').val('');
		$('#driver_address').val('');
		$('#driver_telephone').val('');
		$('#driver_identityno').val('');
		$('#driver_isvalid').get(0).checked=false;
		$('#driver_remarks').val('');

	}
	function initAddData(){
		//设置编码代号为FA
		var code = 'GCN';
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

		initAutoComplete();
		var user = $('.user label').html();
		$('#add_creator').val(user);
		$('#add_createtime').val(getNowFormatDate(true));
		var href = window.location.href;
		hrefStrs = href.split('?id=');
		var id = hrefStrs[1];
		if(id){
			$.ajax({
				url:URL.findOne,
				data:{id:id},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						var obj = result.data;
						$('#add_materiel').val(obj.materielname).attr('materielid',obj.materielid);
						$('#add_receivedepartmentid').val(obj.receivedepartmentname).attr('orgid',obj.receivedepartmentid);
						$('#add_vehicle').val(obj.vehicleno).attr('vehicleid',obj.vehicleid);
						$('#add_driver').val(obj.drivername).attr('driverid',obj.driverid);
						$('#add_rfid').val(obj.rfid);
						$('#add_identityno').val(obj.driveridentityno);
						$('#add_count').val(obj.count);
						$('#add_remark').val(obj.remark);
						$('#add_reason').val(obj.reason);
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		}
	}


	function getNowFormatDate(param,time) {
		var date ;
//		判断time参数是否存在
		if(time){
			date = new Date(time);
		}else{
			date = new Date();
		}
		var seperator1 = "-";
		var seperator2 = ":";
//		获取月份
		var month = date.getMonth() + 1;
//		获取日期
		var strDate = date.getDate();
//		月或者日 为个位数时前面加'0'
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var hours = date.getHours();
		var minutes = date.getMinutes();
		var seconds = date.getSeconds();
		if (hours >= 0 && hours <= 9) {
			hours = "0" + hours;
		}
		if (minutes >= 0 && minutes <= 9) {
			minutes = "0" + minutes;
		}
		if (seconds >= 0 && seconds <= 9) {
			seconds = "0" + seconds;
		}
//		判断返回结果
		if(param){
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
			+ " " + hours + seperator2 + minutes
			+ seperator2 + seconds;
		}else{
			var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		}
		return currentdate;
	}



});