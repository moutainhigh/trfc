var PAGE;
if(PAGE){
	PAGE = null;
}
PAGE = {};
PAGE.mod = {
	main:{},
	util:{},
	URL: {
			addUrl:"/trfc/salesArrive/add",
			pageUrl:"/trfc/salesApplication/page",
			mainUrl:"/trfc/salesArrive/main",
			addVehicleUrl:"/trfc/vehicle/addVehicle",
			addDriverUrl:"/trfc/driver/addDriver"
	}
}
PAGE.mod.util = {
		parseStr2Long: function(str){
			if(str){
				var date = new Date(str);
				return date.getTime();
			}
			return undefined;
		},
		parseLong2Str: function(time){
			if(time){
				var date = new Date(time);
				return date.format('yyyy-MM-dd HH:mm:ss');
			}
			return '';
		}
}
PAGE.mod.main = {
		top: PAGE.mod,
		init:function(){
			this.bindEvent();
		},
		bindEvent:function(){
			var _this = this;
			$('#jumpPageNoBtn').off('click').on('click',function(){
				var pageNo = $('input#jumpPageNo').val();pageNo = $.trim(pageNo);pageNo = parseInt(pageNo);
				var pageMaxNo = $('input#jumpPageNo').attr('maxpageno');pageMaxNo = $.trim(pageMaxNo);pageMaxNo = parseInt(pageMaxNo);
				if(!pageNo || !$.isNumeric(pageNo) || pageNo < 0 || pageNo > pageMaxNo){
					alert('此处必须为1-'+pageMaxNo+'的数字');
					$('input#jumpPageNo').val('');
				}else{
					$('input#jumpPageNo').val(pageNo);
					_this.getSalesApplicationData(pageNo);
				}
			});
			$('#pageSize').change(function(){
				_this.getSalesApplicationData(1);
			});
			$('#searchBtn').off('click').on('click',function(){
				_this.getSalesApplicationData($.trim($('#jumpPageNo').val()) || 1);
			});
			$('#clearBtn').off('click').on('click',function(){
				$('#materielid').val("");
				$('#customer').val("").removeAttr('customerid');
				$('#billcode').val("");
				$('#starttime').val("");
				$('#endtime').val("");
			});
			$('#billcode').off('click').on('click',function(){
				_this.getSalesApplicationData($.trim($('#jumpPageNo').val()) || 1);
				$('#salesApplication').modal();
			});
			$('#vehicleid').change(function(){
				var rfid = $(this).find('option:checked').attr('rfid');
				$('#rfid').val(rfid);
			});
			$('#driverid').change(function(){
				var identityno = $(this).find('option:checked').attr('identityno');
				$('#identityno').val(identityno);
			});
			$('#refreshBtn').off('click').on('click',function(){
				window.location.reload();
			});
			$('#addBtn').off('click').on('click',function(){
				_this.saveSalesArrive();
			});
			$('#addAndAddCardBtn').off('click').on('click',function(){
				alert('待开发.');
			});
			$('#backBtn').off('click').on('click',function(){
				window.location.href = _this.top.URL.mainUrl;
			});
			$('#addVehicleCommitBtn').off('click').on('click',function(){
				if($('#vehicleAddView').is(':visible')){
					_this.saveVehicle();
				}
			});
			$('#addDriverCommitBtn').off('click').on('click',function(){
				if($('#driverAddView').is(':visible')){
					_this.saveDriver();
				}
			});
			$('#takeamount').change(function(){
				$('#advanceAmount').html($(this).val());
			});
			$('#customer').off('click').on('click',function(){
				var _this = this;
				initCustomer(function(obj){
					$(_this).val(obj.name).attr('customerid',obj.id);
				});
			});
		},
		getSarelApplicationParams:function(){
			var _this = this;
			var billcode = $('#billcode').val() || ''; billcode = $.trim(billcode);
			var customerid = $('#customer').attr('customerid') || ''; customerid = $.trim(customerid);
			var materielid = $('#materielid').val() || ''; materielid = $.trim(materielid);
			var starttime = $('#starttime').val() || ''; starttime = $.trim(starttime);
			var endtime = $('#endtime').val() || ''; endtime = $.trim(endtime);
			var pageSize = $('#pageSize').val() || ''; pageSize = $.trim(pageSize);
			return {
				billcode:billcode,
				customerid:customerid,
				materielid:materielid,
				starttime:_this.top.util.parseStr2Long(starttime),
				endtime:_this.top.util.parseStr2Long(endtime),
				status:1,
				pageSize:pageSize
			}
		},
		getSalesApplicationData:function(pageNo){
			var index = layer.load(2, {
			  shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			var _this = this;
			var params = _this.getSarelApplicationParams();
			params.pageNo = pageNo;
			$.ajax({
				url:_this.top.URL.pageUrl,
				data:params,
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						_this.renderHtml(result.data);
						var total = result.data.total;
						var pageNo = result.data.pageNo;
						var pageSize = result.data.pageSize;
						$('#total').html(total);
						$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
						$("#pagination").pagination(total, {
						    callback: function(){
						    	_this.getSalesApplicationData(pageNo+1);
						    },
						    prev_text: '上一页',
						    next_text: '下一页',
						    items_per_page:pageSize,
						    num_display_entries:4,
						    current_page:pageNo-1,
						    num_edge_entries:1,
						    maxentries:total,
						    link_to:"javascript:void(0)"
						});
					}else{
						layer.msg(result.error, {icon: 5});
					}
					layer.close(index);
				}
			});
		},
		renderHtml:function(data){
			var _this = this;
			$('#salesApplicationBody').empty();
			var list = data.list;
			if(list && list.length>0){
				for(var i=0;i<list.length;i++){
					var obj = list[i] || '';
					var customer = obj.customerManageResp;
					var salesApplicationDetail = obj.detailResp;
					var code = obj.code || '';
					var billtypename = obj.billtypename || '';
					var customername = obj.customername || '';
					var materielname = salesApplicationDetail.materielname || '';
					var salessum = salesApplicationDetail.salessum || '';
					var orgname = obj.orgname || '';
					var billtimeStr = obj.billtimeStr || '';
					var departmentname = obj.departmentname || '';
					var salesmanname = obj.salesmanname || '';
					var creatorname = obj.creatorname || '';
					var channelcode = obj.channelcode || '';
					$('<tr>').attr('title','双击确定')
							.append('<td>'+code+'</td>')
							.append('<td>'+billtypename+'</td>')
							.append('<td>'+customername+'</td>')
							.append('<td>'+materielname+'</td>')
							.append('<td>'+salessum+'</td>')
							.append('<td></td>')
							.append('<td></td>')
							.append('<td></td>')
							.append('<td></td>')
							.append('<td>'+orgname+'</td>')
							.append('<td>'+billtimeStr+'</td>')
							.append('<td>'+departmentname+'</td>')
							.append('<td>'+salesmanname+'</td>')
							.append('<td>'+creatorname+'</td>')
							.append('<td>'+channelcode+'</td>')
							.data(obj)
							.appendTo('#salesApplicationBody');
				}
			}else{
				alert('暂无数据');
			}
			$('#salesApplicationBody').find('tr').off('click').on('click',function(){
				$(this).addClass('active').siblings().removeClass('active');
			});
			$('#salesApplicationBody').find('tr').off('dblclick').on('dblclick',function(){
				var obj = $(this).data();
				var customer = obj.customerManageResp;
				var salesApplicationDetail = obj.detailResp;
				var billid = obj.id || '';
				var billcode = obj.code || '';
				var createtime = obj.createtime || '';
				var createtimeStr = obj.createtimeStr || '';
				var customerid = obj.customerid || '';
				var customername = obj.customername || '';
				var channelcode = obj.channelcode || '';
				var orgid = obj.orgid || '';
				var orgname = obj.orgname || '';
				var materielid = salesApplicationDetail.materielid || '';
				var materielname = salesApplicationDetail.materielname || '';
				var departmentid = obj.departmentid || '';
				var departmentname = obj.departmentname || '';
				var unit = salesApplicationDetail.unit || '';
				var salessum = salesApplicationDetail.salessum || '';
				var billtime = obj.billtime || '';
				var billtimeStr = obj.billtimeStr || '';
				var code = obj.code || '';
				var billtype = obj.billtype || '';
				var salesmanname = obj.salesmanname || '';
				var creatorname = obj.creatorname || '';
				$('#billcode').val(billcode).attr('billid', billid);
				$('#customername').val(customername).attr('customerid', customerid);
				$('#channelcode').val(channelcode);
				//$('#orgname').val(orgname).attr('orgid', orgid);
				$('#materielname').val(materielname).attr('materielid', materielid);
				$('#departmentname').val(departmentname).attr('departmentid', departmentid);
				$('#salessum').val(salessum);
				$('#billtime').val(billtimeStr).attr('billtime', billtime);
				$('#salesApplication').modal('hide');
				$('#salesApplicationDetailBody').empty().append('<tr><td>'+code+'</td><td>'+billtype+'</td><td>'+billtimeStr+'</td><td>'+materielname+'</td><td>'+unit+'</td><td>'+salessum+'</td><td></td><td id="advanceAmount">0.00</td><td>'+orgname+'</td><td>'+customername+'</td><td>'+departmentname+'</td><td>'+salesmanname+'</td><td>'+creatorname+'</td></tr>');
			});
		},
		saveSalesArrive:function(){
			var _this = this;
			var billid = $('#billcode').attr('billid'); billid = $.trim(billid);
			var billcode = $('#billcode').val(); billcode = $.trim(billcode);
			var maindeduction = '0';
			if($('#maindeduction').is(':checked')){
				maindeduction = '1';
			}
			var code = $('#code').val(); code = $.trim(code);
			var createtimeStr = $('#createtimeStr').val(); code = $.trim(code);
			var createtime = this.top.util.parseStr2Long(createtimeStr);
			var unit = $('#unit').val(); unit = $.trim(unit);
			var vehicleid = $('#vehicleid').val(); vehicleid = $.trim(vehicleid);
			var vehicleno = $('#vehicleid>option:checked').text(); vehicleno = $.trim(vehicleno);
			var vehiclerfid = $('#rfid').val(); vehiclerfid = $.trim(vehiclerfid);
			var driverid = $('#driverid').val(); driverid = $.trim(driverid);
			var drivername = $('#driverid>option:checked').text(); drivername = $.trim(drivername);
			var driveridentityno = $('#identityno').val(); driveridentityno = $.trim(driveridentityno);
			var takeamount = $('#takeamount').val(); takeamount = $.trim(takeamount);
			var remarks = $('#remarks').val(); remarks = $.trim(remarks);
			var spraycode = $('#spraycode').val(); spraycode = $.trim(spraycode);
			var serialnumber = $('#serialnumber').val(); serialnumber = $.trim(serialnumber);
			var icardid = $('#icardid').attr('icardid'); icardid = $.trim(icardid);
			var icardno = $('#icardid').attr('icardno'); icardno = $.trim(icardno);
			$.ajax({
				url:_this.top.URL.addUrl,
				data:{
					billid:billid,
					billcode:billcode,
					maindeduction:maindeduction,
					code:code,
					makebilltime:createtime,
					unit:unit,
					vehicleid:vehicleid,
					vehicleno:vehicleno,
					vehiclerfid:vehiclerfid,
					driverid:driverid,
					drivername:drivername,
					driveridentityno:driveridentityno,
					takeamount:takeamount,
					remarks:remarks,
					spraycode:spraycode,
					serialnumber:serialnumber,
					icardid:icardid,
					icardno:icardno
				},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						window.location.href = _this.top.URL.mainUrl;
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		},
		saveVehicle:function(){
			var _this = this;
			var code = $('#v_code').val(); code = $.trim(code);
			var transporttype = $('#v_transporttype').val(); transporttype = $.trim(transporttype);
			var vehicleno = $('#v_vehicleno').val(); vehicleno = $.trim(vehicleno);
			var vehicletype = $('#v_vehicletype').val(); vehicletype = $.trim(vehicletype);
			var transportunit = $('#v_transportunit').val(); transportunit = $.trim(transportunit);
			var maxweight = $('#v_maxweight').val(); maxweight = $.trim(maxweight);
			var tareweight = $('#v_tareweight').val(); tareweight = $.trim(tareweight);
			var ownername = $('#v_ownername').val(); ownername = $.trim(ownername);
			var telephone = $('#v_telephone').val(); telephone = $.trim(telephone);
			var address = $('#v_address').val(); address = $.trim(address);
			var orgid = $('#v_orgname').attr('orgid'); orgid = $.trim(orgid);
			var orgname = $('#v_orgname').val(); orgname = $.trim(orgname);
			var isvalid = 0;
			if($('#v_isvalid').is(':checked')){
				isvalid = 1;
			}
			var remarks = $('#v_remarks').val(); remarks = $.trim(remarks);
			$.ajax({
				url:_this.top.URL.addVehicleUrl,
				data:{
					code:code,
					transporttype:transporttype,
					vehicleno:vehicleno,
					vehicletype:vehicletype,
					transportunit:transportunit,
					maxweight:maxweight,
					tareweight:tareweight,
					ownername:ownername,
					telephone:telephone,
					address:address,
					orgid:orgid,
					orgname:orgname,
					isvalid:isvalid,
					remarks:remarks
				},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						layer.msg(result.error, {icon: 1});
						var vehicle = result.data;
						$('#vehicleid').append('<option value="'+vehicle.id+'">'+vehicle.vehicleno+'</option>').val(vehicle.id);
						$('#vehicleAddView').modal('hide');
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		},
		saveDriver:function(){
			var _this = this;
			var code = $('#d_code').val(); code = $.trim(code);
			var internalcode = $('#d_internalcode').val(); internalcode = $.trim(internalcode);
			var name = $('#d_name').val(); name = $.trim(name);
			var abbrname = $('#d_abbrname').val(); abbrname = $.trim(abbrname);
			var address = $('#d_address').val(); address = $.trim(address);
			var orgid = $('#d_orgname').attr('orgid'); orgid = $.trim(orgid);
			var orgname = $('#d_orgname').val(); orgname = $.trim(orgname);
			var telephone = $('#d_telephone').val(); telephone = $.trim(telephone);
			var identityno = $('#d_identityno').val(); identityno = $.trim(identityno);
			var isvalid = 0;
			if($('#d_isvalid').is(':checked')){
				isvalid = 1;
			}
			var remarks = $('#d_remarks').val(); remarks = $.trim(remarks);
			$.ajax({
				url:_this.top.URL.addDriverUrl,
				data:{
					code:code,
					internalcode:internalcode,
					name:name,
					abbrname:abbrname,
					address:address,
					orgid:orgid,
					orgname:orgname,
					telephone:telephone,
					identityno:identityno,
					isvalid:isvalid,
					remarks:remarks
				},
				async:true,
				cache:false,
				dataType:'json',
				type:'post',
				success:function(result){
					if(result.code == '000000'){
						layer.msg(result.error, {icon: 1});
						var driver = result.data;
						$('#driverid').append('<option value="'+driver.id+'">'+driver.name+'</option>').val(driver.id);
						$('#identityno').val(driver.identityno);
						$('#driverAddView').modal('hide');
					}else{
						layer.msg(result.error, {icon: 5});
					}
				}
			});
		}
}
$(function(){
	PAGE.mod.main.init();
});