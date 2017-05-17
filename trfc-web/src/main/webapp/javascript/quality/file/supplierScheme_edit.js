$(function(){
	//获取id
	var id = getID();
	//加载原数据
	getOldData();
	//刷新
	$('#fresh').click(function(){window.location.reload(true)});
	//保存
	$('#save').click(saveAction);
	//返回
	$('#goBack').click(function(){window.location.replace(URL.mainUrl)});


	//获取id
	function getID(){
		//获取地址栏数据
		var href = window.location.href;
		//将字符串拆分
		var trs = href.split('?id=');
		return trs[1];
	}
	//保存数据
	function saveAction(){
		//获取数据
		var param = editData();
		if(param){
			//保存数据到服务器
			$.post(URL.updateUrl,param,function(result){
				if('000000'==result.code){
					//返回列表
					$('#goBack').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	};
	//获取编辑数据
	function editData(){
		var code = $('#edit_code').val();
		var name = $('#edit_name').val();
		var supplierid = $('#edit_supplier').attr('supplierid');
		var supremark = $('#edit_supremark').val();
		var materialid = $('#edit_material').attr('materialid');
		var schemeid = $('#edit_scheme').attr('qschemeid');
		var starttime = new Date($('#edit_starttime').val());
		starttime = starttime.getTime();
		var endtime = new Date($('#edit_endtime').val());
		endtime = endtime.getTime();
		var mean = $('#edit_mean').val();
		var deduct = $('#edit_deduct').val();
		var invalid = '1';
		//判断是否已勾选
		if($('#edit_invalid').prop('checked')){
			invalid = '0';
		}
		var ref = '1';
		if($('#edit_ref').prop('checked')){
			ref = '0';
		}
		var createtime = new Date($('#edit_createtime').val());
		createtime = createtime.getTime();
		var remark = $('#edit_remark').val();
		var param = {
				id:id,
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
				detail:getDetail(),
				user:userid
		};
		return param;
	}
	//初始化
	function getOldData(){
		$.post(URL.selectByIdUrl,{id:id},function(result){
			if('000000'==result.code){
				//填充数据
				initEdit(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//初始化编辑页面
	function initEdit(data){
		initSelect();
		$('#edit_scheme').blur(getDetailData);
		$('#edit_code').val(data.code);
		$('#edit_name').val(data.name);
		$('#edit_supplier').val(data.suppliername).attr('supplierid',data.supplierid);
		$('#edit_material').val(data.materialname).attr('materialid',data.materialid);
		$('#edit_scheme').val(data.schemename).attr('qschemeid',data.schemeid).blur();
		$('#edit_supremark').val(data.supremark);
		
		var starttime = getNowFormatDate(false,data.starttime);
		$('#edit_starttime').val(starttime);
		var endtime = getNowFormatDate(false,data.endtime);
		$('#edit_endtime').val(endtime);
		$('#edit_mean').val(data.mean);
		$('#edit_deduct').val(data.deduct);
		$('#edit_remark').val(data.remark);
		var createtime = getNowFormatDate(true,data.createtime);
		$('#edit_createtime').val(createtime);
		var user = data.creator;
		$('#edit_creator').val(user);
		//为checkbox赋值
		$('#edit_invalid')[0].checked=true;
		if('1'==data.invalid){
			$('#edit_invalid')[0].checked=false;
		}
		$('#edit_ref')[0].checked=true;
		if('1'==data.ref){
			$('#edit_ref')[0].checked=false;
		}
		
	}



	//获取下拉框数据并填充
	function schemeSelect(id){
		//获取数据
		var materialid = $('#edit_material').val();
		if(materialid){
			$schSelect=$.post(URL.getSchemeUrl,{invalid:"0",materialid:materialid},function(result){
				if(result.code=='000000'){
					//填充数据
					fillSchemeSelect(result.data);
					if(id){
						$('#edit_scheme').val(id).select2();
						getDetailData();
					}
				}else{
					layer.msg(result.error, {icon:5});
				}
			});
		}
	}
	//填充数据
	function fillSchemeSelect(list){
		var selecter = $('#edit_scheme').html('');
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
		var schemeid=$('#edit_scheme').attr('qschemeid');
		if(schemeid){
			//如果质检方案id不为空,这去数据库查询数据
			var param = {
					schemeid:schemeid,
					invalid:'0',//设置为有效
					status:'1'//设置为1(已初始化)
			};
			$.post(URL.getDetailUrl,param,function(result){
				if('000000'==result.code){
					//成功后,展示数据
					showDetailData(result.data);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}else{
			$('#edit_detail').empty();
		}
	}

	//加载运算符拉框框
	function loadSymbol($obj){
		var options = '<option></option><option value="1">小于</option><option value="3">'
			+'小于等于</option><option value="0">大于</option><option value="2">大于等于</option><option value="4">等于</option>';
		$obj.html(options);
	}
	//展示详细列表
	function showDetailData(list){
		var tbody = $('#edit_detail').empty();
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var tr = '<tr>'
					+'<td>'+(i+1)+'</td>'
					+'<td>'+(obj.itemname || '')
					+'</td>'
					+'<td><select class="form-control"></select></td>'
					+'<td><input type="text" class="w80" value="'+obj.lowlimit+'"></td>'
					+'<td><select class="form-control"></select></td>'
					+'<td><input type="text" class="w80" value="'+obj.upperlimit+'"></td>'
					+'<td><input type="text" class="w80" value="'+obj.baseval+'"></td>'
					+'<td><input type="text" class="w80" value="'+obj.floatval+'"></td>'
					+'<td><input type="text" class="w80" value="'+obj.charge+'"></td>'
					+'<td><input type="text" class="w80" value="'+obj.deduct+'"></td>'
					+'</tr>';
				tr=$(tr);
				tbody.append(tr);
				//将数据绑定到tr上
				tr.data('obj',obj);
				//加载下拉框,并赋值
				loadSymbol(tr.find('select'));
				tr.find('td select').eq(0).val(obj.comparison2);
				tr.find('td select').eq(1).val(obj.comparison3);
			}
		}
	}

	//获取详细数据
	function getDetail(){
		//获取trs
		var trs = $('#edit_detail tr');
		var arr = new Array();
		//遍历trs
		for(var i=0;i<trs.length;i++){
			var obj = trs.eq(i).data('obj');
			var inputs = trs.eq(i).find('input');
			var selects = trs.eq(i).find('select');
			var id = obj.id;
			var comparison2 = selects.eq(0).val();
			var comparison3 = selects.eq(1).val();
			var lowlimit = inputs.eq(1).val();
			var upperlimit = inputs.eq(2).val();
			var baseval = inputs.eq(3).val();
			var floatval = inputs.eq(4).val();
			var charge = inputs.eq(5).val();
			var deduct = inputs.eq(6).val();
			var param = {
					id:id,
					comparison2:comparison2,
					comparison3:comparison3,
					lowlimit:lowlimit,
					upperlimit:upperlimit,
					baseval:baseval,
					floatval:floatval,
					charge:charge,
					deduct:deduct
			};
			if(!contrast(obj,param)){
				arr.push(param);
			}
		}
		//将数组转化为json字符串并返回
		return JSON.stringify(arr);
	}
	//对比两个对象 如果相同则返回true
	function contrast(obj1,obj2){
		if(obj1.id==obj2.id &&
				obj1.comparison2==obj2.comparison2 &&
				obj1.comparison3==obj2.comparison3 &&
				obj1.lowlimit==obj2.lowlimit &&
				obj1.upperlimit==obj2.upperlimit &&
				obj1.baseval==obj2.baseval &&
				obj1.floatval==obj2.floatval &&
				obj1.charge==obj2.charge &&
				obj1.deduct==obj2.deduct
		){
			return true;
		}
		return false;
	}
});