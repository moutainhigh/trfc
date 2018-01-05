var URL = {
		//列表
		pageUrl:"/trfc/quality/sales/batchnum/page",
		//主页
		mainUrl:"/trfc/quality/sales/report/main",
		//获取编码
		getCodeUrl:"/trfc/quality/sales/report/getCode",
		//获取物料方案数据
		getMschemeData:"/trfc/quality/sales/file/MaterialScheme/findOne",
		//刷新编码计数
		updateCodeUrl:"/trfc/quality/sales/report/updateCode",
		//保存数据
		saveUrl:"/trfc/quality/sales/report/save",
		//获取一条化验报告的数据
		findOneUrl:"/trfc/quality/sales/report/selectOne",
		//获取详情和检测值
		getDetailAndVal:"/trfc/quality/sales/report/getDetailAndVal",
		//下拉框
		qschemeAutoCompleteSearch: "/trfc/quality/sales/file/qualityScheme/autoCompleteSearch",
		materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
		mschemeAutoCompleteSearch: "/trfc/quality/sales/file/MaterialScheme/autoCompleteSearch",
		//根据物料查询
		selectMaterialUrl:"/trfc/quality/sales/file/MaterialScheme/selectMaterial",
		//根据批号id查询报告
		selectBatchnumidUrl:"/trfc/quality/sales/report/selectBatchnumid"
};
$(function(){

	$('#goBack').click(function(){
		window.location.href=URL.mainUrl;
	});
	$('#fresh').click(function(){
		window.location.reload(true);
	});
	//选择批号是 对批号列表进行初始话
	$('#add_batchcode').click(function(){batchnumShowAction(1);});
	//下拉框初始化
	initSelect();
	//绑定搜索按钮
	$('#select_seek').click(function(){batchnumShowAction(1);});
	//监听每页记录 事件
	$('#pageSize').change(function(){batchnumShowAction(1);});
	//跳转页面
	$('#jumpButton').click(jumpPageAction);
//	$('#select_list').on('dblclick','tr',function(){
//		var obj = $(this).data('obj');
//		$('#add_batchcode').val(obj.factorycode).attr('batchnumid',obj.id);
//		selectBatchnumid(obj.id,obj.factorycode);
//		
//		$('#add_producedtime').val(getNowFormatDate(false,obj.producedtime));
//		$('#add_testtime').val(getNowFormatDate(false,obj.testtime));
//		selectMaterial(obj.id);
//		$('#closeBth').click();
//	});
	//根据批号id查询是否可以添加化验报告
	function selectBatchnumid(batchnumid,factorycode){
		$.ajax({
			url : URL.selectBatchnumidUrl,
			data : {"batchnumid":batchnumid},
			async : false,
			cache : false,
			dataType : 'json',
			type : 'post',
			success : function(result) {
				if (result.code =='000000') {
					var list =result.data;
					if(list&&list.length>0){
						for(var i=0;i<list.length;i++){
							var obj = list[i];
							if(obj.reporttype=='0'){
//								$('#add_batchcode').val(factorycode).attr('batchnumid',obj.id);
								$("#reporttype").val('1').attr('selected',true);
							}else{
//								$('#add_batchcode').val(factorycode).attr('batchnumid',obj.id);
								$("#reporttype").val('0').attr('selected',true);
							}
						}
					}else{
//						$('#add_batchcode').val(factorycode).attr('batchnumid',batchnumid);
					}
				}else{
					layer.msg(result.error,{icon:5});
//					window.location.reload(true);
					return;
				}
			}
		});
	}
	function selectMaterial(material){
		$.ajax({
			url : URL.selectMaterialUrl,
			data : {"materialid":material},
			async : false,
			cache : false,
			dataType : 'json',
			type : 'post',
			success : function(result) {
				if (result.code == '000000') {
//					$("#add_materialtype").val(result.data.materialtype||"");
					$('#add_materialtype').val(result.data.materialtype).attr('mschemeid',result.data.id);
					$("#add_strength").val(result.data.strength||"");
					$("#add_admixture").val(result.data.admixture||"");
					$("#add_admixtureadd").val(result.data.admixtureadd||"");
					$("#add_gypsum").val(result.data.gypsum||"");
					$("#add_gypsumadd").val(result.data.gypsumadd||"");
					$("#add_aid").val(result.data.aid||"");
					$("#add_aidadd").val(result.data.aidadd||"");
				}else{
					$('#closeBth').click();
				}
			}
		});
	}
	//在保存按钮上 绑定事件
	$('#save').click(function(){
		var data = getAddData();
		if(data){
			var bn=layer.open({
				content: '您确定要保存吗？',
				area: '600px',
				closeBtn:1,
				shadeClose:true,
				btn: ['确定', '取消'],
				yes: function(index, layero){
					//按钮【确定】的回调
					$.post(URL.saveUrl,data,function(result){
						if('000000'==result.code){
							//更新编码计数
							updateCode();

						}else{
							layer.msg(result.error,{icon:5});
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
	});
	//获取编号
	initCode();
	//获取用户名
	var userid = $('.user').attr('userid');
	//对新增页面进行初始化
	var id = getId();
	initPage();
	if(id){
		initCopyPage();
	}

	//-------------------------------------------------------------------------------

	function initCopyPage(){
		$.post(URL.findOneUrl,{id:id},function(result){
			if('000000'==result.code){
				var obj = result.data;
				$('#add_batchcode').val(obj.batchcode).attr('batchnumid',obj.batchnumid);
				$('#add_pstate')[0].checked=true;
				if(obj.pstate==0){
					$('#add_pstate')[0].checked=false;
				}
				$('#add_producedtime').val(getNowFormatDate(false, obj.producetime));
				$('#add_testtime').val(getNowFormatDate(false, obj.testtime));
				$('#add_materialtype').val(obj.materialtype).attr('mschemeid',obj.mschemeid);
				getMschemeData(obj.mschemeid);
				$('#add_addr').val(obj.addr);
				$('#add_remark').val(obj.remark);
				$('#add_selldate').val(getNowFormatDate(false,obj.selldate));
				$('#add_qscheme').val(obj.qschemename).attr('qschemeid',obj.qschemeid);
				showDetail(obj.qschemeid,id);

			}else{
				layer.msg(result.error,{icon:5});
			}
		});

	}
	function initPage(){
		var username = $(".user label").html();
		$('#add_reportorg').val('天瑞集团水泥有限公司（裕泰公司）');
		$('#add_creattime').val(getNowFormatDate(true));
		$('#add_reporter').val(username);
		$('#add_creator').val(username);
	}
	//获取编号
	function getId(){
		var href = window.location.href;
		var trs = href.split('?id=');
		return trs[1];
	};
	function initCode(){
		//获取编号
		var param = {
				code:'ZJ',
				Userid:userid,
				codeType:true
		};
		$.post(URL.getCodeUrl,param,function(result){
			if('000000'==result.code){
				$('#add_code').val(result.data);
			}
		});
	}
	//获取新增数据
	function getAddData(){
		var reporttype =$("#reporttype").val();
		var code = $('#add_code').val();
		var batchnumid = $('#add_batchcode').attr('batchnumid');
		if(!batchnumid){
			layer.msg("批号不能为空!");
			return null;
		}
		var mscheme = $('#add_materialtype').attr('mschemeid');
		if(!mscheme){
			layer.msg("物料方案不能为空!");
			return null;
		}
		var qscheme = $('#add_qscheme').attr('qschemeid');
		if(!qscheme){
			layer.msg("质检方案不能为空!")
			return null;
		}
		var reportorg = $('#add_reportorg').val();
		var reporter = $('#add_reporter').val();
		var addr = $('#add_addr').val();
		var producedtime = $('#add_producedtime').val();
		if(producedtime){
			producedtime = new Date(producedtime);
			producedtime = producedtime.getTime();
		}
		var testtime = $('#add_testtime').val();
		if(testtime){
			testtime = new Date(testtime);
			testtime = testtime.getTime();
		}
		var createtime = $('#add_creattime').val();
		if(createtime){
			createtime = Date.parseYMD_HMS(createtime);
			createtime = createtime.getTime();
		}else{
			createtime = new Date().getTime();
		}
		var selldate = $('#add_selldate').val();
		if(selldate){
			selldate = Date.parseYMD_HMS(selldate);
			selldate = selldate.getTime();
		}else{
			createtime = new Date().getTime();
		}
		var remark = $('#add_remark').val();
		var pstate = '0';
		if($('#add_pstate').prop('checked')){
			pstate = '1';
		}
		var data = {
				code:code,
				batchnumid:batchnumid,
				mschemeid:mscheme,
				qschemeid:qscheme,
				reportorg:reportorg,
				reporter:reporter,
				reporttype:reporttype,
				addr:addr,
				producedtime:producedtime,
				testtime:testtime,
				createtime:createtime,
				user:userid,
				selldate:selldate,
				pstate:pstate,
				arrStr:getDetailData(),
				remark:remark
		};
		return data;
	}
	function getDetailData(){
		var trs = $('#detail_list tr');
		var arr = new Array();
		for(var i=0;i<trs.length;i++){
			var tr = trs.eq(i);
			obj = tr.data('obj');
			var itemid = obj.itemid;
			var testval = tr.find('input').val();
			arr[i] = {itemid:itemid,testval:testval};
		}
		return JSON.stringify(arr);
	}

	//添加成功后,刷新标号(增1)
	function updateCode(){
		//设置编码代号
		var param = {
				code:'ZJ',
				userid:userid,
				codeType:true
		}; 
		//更新编码
		$.post(URL.updateCodeUrl,param,function(result){
			if(result.code=='000000'){
				//跳转到主页
				window.location.href=URL.mainUrl;
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}




	//当物料方案改变时 填充数据
	function fillMSDetail(obj){
		$('#add_strength').val(obj.strength);
		$('#add_admixture').val(obj.admixture);
		$('#add_admixtureadd').val(obj.admixtureadd);
		$('#add_gypsum').val(obj.gypsum);
		$('#add_gypsumadd').val(obj.gypsumadd);
		$('#add_aid').val(obj.aid);
		$('#add_aidadd').val(obj.aidadd);
	}
	//获取mscheme的详细信息
	function getMschemeData(id){
		$.post(URL.getMschemeData,{id:id},function(result){
			if('000000'==result.code){
				fillMSDetail(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		})
	}
	//获取详情数据,并展示列表
	function showDetail(id,assayid){
		$.post(URL.getDetailAndVal,{schemeid:id,assayid:assayid},function(result){
			if('000000'==result.code){
				fillQSDetail(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//展示详情
	function fillQSDetail(list){
		var tbody = $('#detail_list').empty();
		if(list){
			for(var i=0;i<list.length;i++){
				var obj = list[i];
				var assaytime = getNowFormatDate(false,obj.assaytime);
				var starttime = getNowFormatDate(false,obj.starttime);
				var endtime = getNowFormatDate(false,obj.endtime);
				var audittime = null;
				if(obj.audittime){
					audittime = getNowFormatDate(true,obj.audittime);
				}
				var tr = '<tr>'
					+'<td>'+(i+1)+'</td>'
					+'<td>'+(obj.itemname || '')+'</td>'
					+'<td>'+(obj.units || '')+'</td>'
					+'<td>'+(obj.standardval || '')+'</td>'
					+'<td><input type="text" value="'+(obj.testval || '0')+'"></td>'
					+'</tr>';
				tr = $(tr);
				//追加
				tbody.append(tr);
				tr.data('obj',obj);
			}
		}
	}
	//页面跳转
	function jumpPageAction(){
		//获取总页数
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		//获取当前页
		var pageno = $('#jumpPageNo').val();
		//判断跳转值是否在符合规范
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			//加载指定的列表数据
			batchnumShowAction(pageno);
		}
	}
	//前进一页
	function pageCallback(pageNo){
		batchnumShowAction(pageNo+1);
	}

	//展示数据列表
	function batchnumShowAction(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var starttime = $('#select_starttime').val();
		if(starttime){
			starttime = new Date(starttime);
			starttime = starttime.getTime();
		}
		var endtime = $('#select_endtime').val();
		if(endtime){
			endtime = new Date(endtime);
			endtime = endtime.getTime();
		}
		var factorycode = $('#select_factorycode').val();
		var material = $('#select_material').attr('materialid');
		var billsstate = $('#select_billsstate').val();
		if(billsstate==''){
			billsstate='1'
		}

		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
				factorycode:factorycode,
				material:material,
				billsstate:billsstate
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var list = result.data.list;
				var pageNo = result.data.pageNo;
				var pageSize = result.data.pageSize;
				var total = result.data.total;
				//添加数据总数
				$('#total').html(result.data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
				//分页插件
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
				if(list){
					showPageData(list,pageSize,pageNo);
				}
				layer.close(index);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//展示列表
	function showPageData(list,pageSize,pageNo){
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#select_list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];

			var assaytime = getNowFormatDate(false,obj.assaytime);
			var starttime = getNowFormatDate(false,obj.starttime);
			var endtime = getNowFormatDate(false,obj.endtime);
			var audittime = null;
			if(obj.audittime){
				audittime = getNowFormatDate(true,obj.audittime);
			}
			$('<tr>').append('<td><input type="checkbox"/></td>')
			.append('<td>'+(obj.code || '')+'</td>')
			.append('<td>'+(obj.material || '')+'</td>')
			.append('<td>'+(obj.factorycode || '')+'</td>')
			.append('<td>'+(obj.count || '')+'</td>')
			.append('<td>'+(starttime || '')+'</td>')
			.append('<td>'+(assaytime || '')+'</td>')
			.data(obj)
			.appendTo('#select_list');
			
			
//			var tr = '<tr><td><input type="checkbox"/></td>'
//				+'<td>'+(obj.code || '')+'</td>'
//				+'<td>'+(obj.material || '')+'</td>'
//				+'<td>'+(obj.factorycode || '')+'</td>'
//				+'<td>'+(obj.count || '')+'</td>'
//				+'<td>'+(starttime || '')+'</td>'
//				+'<td>'+(assaytime || '')+'</td>'
//				+'</tr>';
//			//转换为jquery对象
//			tr=$(tr);
//			//追加
//			tbody.append(tr);
//			tr.data('obj',obj);
		}
		$('#select_list>tr').find('td:eq(0)>input[type="checkbox"]').off('click').on('click',function(e){
			e.stopPropagation();
			if(this.checked == true){
				$(this).closest('tr').addClass('active');
			}else{
				$(this).closest('tr').removeClass('active');
			}
		});
		$('#select_list>tr').off('click').on('click',function(e){
			e.stopPropagation();
			$(this).find('td:eq(0)>input').trigger('click');
		});
	}
	$('#returnApplication').off('click').on('click',function(){
		if($('#altbill').is(':visible')){
			var trs = $('#altbill').find('tr.active');
			if(trs.length == 0){
				layer.msg('至少选择一个订单！');return;
			}else if(trs.length == 1){
				var obj = trs.data();
				selectList(obj);
			}else if(trs.length>1){
				layer.msg('只能选择一个订单！');return;
			}
		}
	});
	function selectList(obj){
		$('#add_batchcode').val(obj.factorycode).attr('batchnumid',obj.id);
		selectBatchnumid(obj.id,obj.factorycode);
		$('#add_producedtime').val(getNowFormatDate(false,obj.producedtime));
		$('#add_testtime').val(getNowFormatDate(false,obj.testtime));
		selectMaterial(obj.id);
		$('#altbill').modal('hide');
	}
	//加载下拉框
	function initSelect(){
		var cache = {};
		$("#add_qscheme").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var qscheme = cache['qscheme'] || {};
				if ( term in qscheme ) {
					response( qscheme[ term ] );
					return;
				}
				request.type='1'
				$.post( URL.qschemeAutoCompleteSearch, request, function( result ) {
					qscheme[ term ] = result.data;
					response( result.data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('qschemeid', ui.item.id);
				showDetail(ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('qschemeid');
		}).change(function(){
			if(!$(this).attr('qschemeid')){
				$(this).val('');
			}
		});
		$("#add_materialtype").autocomplete({
			//获取数据
			source: function( request, response ) {
				var term = request.term;
				var mscheme = cache['mscheme'] || {};
				if ( term in mscheme ) {
					response( mscheme[ term ] );
					return;
				}
				$.post( URL.mschemeAutoCompleteSearch, request, function(result) {
					if('000000'==result.code){
						mscheme[ term ] = result.data;
						response( result.data );
					}else{
						layer.msg(result.error,{icon:5});
					}	

				});
			},
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					ui.content.forEach(function(x,i,a){
						x.label = x.materialtype;
						x.value = x.id;
					});
				}
			},
			select: function( event, ui ) {
				//当选择的时候 显示到输入框
				$(this).val(ui.item.materialtype).attr('mschemeid', ui.item.id);
				//每当数据发生改变 ,填充对应的子信息
				fillMSDetail(ui.item);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('mschemeid');
		}).change(function(){
			if(!$(this).attr('mschemeid')){
				$(this).val('');
			}
		});
		$("#select_material").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var material = cache['material'] || {};
				if ( term in material ) {
					response( material[ term ] );
					return;
				}
				$.post( URL.materialAutoCompleteSearch, request, function( data, status, xhr ) {
					material[ term ] = data;
					response( data );
				});
			},
			//显示下拉框
			response: function( event, ui ) {
				if(ui.content && ui.content.length > 0){
					//展示下拉框
					ui.content.forEach(function(x,i,a){
						x.label = x.name;
						x.value = x.id;
					});
				}
			},
			//选定,显示结果到输入框
			select: function( event, ui ) {
				$(this).val(ui.item.name).attr('materialid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('materialid');
		}).change(function(){
			if(!$(this).attr('materialid')){
				$(this).val('');
			}
		});
	};
	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
//	time(获取指定时间的字符串) 默认返回当前时间
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