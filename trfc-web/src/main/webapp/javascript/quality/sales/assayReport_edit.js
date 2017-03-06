$(function(){
	var URL = {
			pageUrl:"/trfc/quality/sales/batchnum/page",
			mainUrl:"/trfc/quality/sales/report/main",
			getMschemeData:"/trfc/quality/sales/file/MaterialScheme/findOne",
			findOneUrl:"/trfc/quality/sales/report/selectOne",
			saveUrl:"/trfc/quality/sales/report/update",
			getDetailAndVal:"/trfc/quality/sales/report/getDetailAndVal",
			qschemeAutoCompleteSearch: "/trfc/quality/sales/file/qualityScheme/autoCompleteSearch",
			materialAutoCompleteSearch: "/trfc/materiel/autoCompleteSearch",
			mschemeAutoCompleteSearch: "/trfc/quality/sales/file/MaterialScheme/autoCompleteSearch",
	};

	$('#goBack').click(function(){
		window.location.href=URL.mainUrl;
	});
	//获取用户名
	var userid = $('.user').attr('userid');
	var id = getId();
	
	function getId(){
		var href = window.location.href;
		var trs = href.split('?id=');
		return trs[1];
	};
	//对新增页面进行初始化
	initPage();

	function initPage(){
		$.post(URL.findOneUrl,{id:id},function(result){
			if('000000'==result.code){
				var obj = result.data;
				$('#edit_code').val(obj.code);
				$('#edit_pstate')[0].checked=true;
				if(obj.pstate==0){
					$('#edit_pstate')[0].checked=false;
				}
				$('#edit_batchcode').val(obj.batchcode).attr('batchnumid',obj.batchnumid);
				$('#edit_producedtime').val(getNowFormatDate(false, obj.producetime));
				$('#edit_testtime').val(getNowFormatDate(false, obj.testtime));
				$('#edit_materialtype').val(obj.materialtype).attr('mschemeid',obj.mschemeid);
				getMschemeData(obj.mschemeid);
				$('#edit_reportorg').val(obj.reportorg);
				$('#edit_creattime').val(getNowFormatDate(true,obj.createtime));
				$('#edit_reporter').val(obj.reporter);
				$('#edit_creator').val(obj.creator);
				$('#edit_addr').val(obj.addr);
				$('#edit_remark').val(obj.remark);
				$('#edit_selldate').val(getNowFormatDate(false,obj.selldate));
				$('#edit_qscheme').val(obj.qschemename).attr('qschemeid',obj.qschemeid);
				showDetail(obj.qschemeid,id);
				//选择批号是 对批号列表进行初始话
				$('#edit_batchcode').click(function(){batchnumShowAction(1);});
				//下拉框初始化
				initSelect();
				//绑定搜索按钮
				$('#select_seek').click(function(){batchnumShowAction(1);});
				//监听每页记录 事件
				$('#pageSize').change(function(){batchnumShowAction(1);});
				//跳转页面
				$('#jumpButton').click(jumpPageAction);
				$('#select_list').on('dblclick','tr',function(){
					var obj = $(this).data('obj');
					$('#edit_batchcode').val(obj.factorycode).attr('batchnumid',obj.id);
					$('#edit_producedtime').val(getNowFormatDate(false,obj.producedtime));
					$('#edit_testtime').val(getNowFormatDate(false,obj.testtime));
					$('#closeBth').click();
				});
				//在保存按钮上 绑定事件
				$('#save').click(function(){
					var data = getAddData();
					if(data){
						$.post(URL.saveUrl,data,function(result){
							if('000000'==result.code){
								window.location.href=URL.mainUrl;
							}
						});
					}
				})
				
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		
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
	//获取新增数据
	function getAddData(){
		var code = $('#edit_code').val();
		var batchnumid = $('#edit_batchcode').attr('batchnumid');
		var mscheme = $('#edit_materialtype').attr('mschemeid');
		var qscheme = $('#edit_qscheme').attr('qschemeid');
		
		var reportorg = $('#edit_reportorg').val();
		var reporter = $('#edit_reporter').val();
		var addr = $('#edit_addr').val();
		var producedtime = $('#edit_producedtime').val();
		if(producedtime){
			producedtime = new Date(producedtime);
			producedtime = producedtime.getTime();
		}
		var testtime = $('#edit_testtime').val();
		if(testtime){
			testtime = new Date(testtime);
			testtime = testtime.getTime();
		}
		var createtime = $('#edit_creattime').val();
		if(createtime){
			createtime = new Date(createtime);
			createtime = createtime.getTime();
		}
		var selldate = $('#edit_selldate').val();
		if(selldate){
			selldate = new Date(selldate);
			selldate = selldate.getTime();
		}
		var remark = $('#edit_remark').val();
		var pstate = '0';
		if($('#edit_pstate').prop('checked')){
			pstate = '1';
		}
		var data = {
				id:id,
				code:code,
				batchnumid:batchnumid,
				mschemeid:mscheme,
				qschemeid:qscheme,
				reportorg:reportorg,
				reporter:reporter,
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
	//获取详细 数据(返回json字符串)
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


	//当物料方案改变时 填充数据
	function fillMSDetail(obj){
		$('#edit_strength').val(obj.strength);
		$('#edit_admixture').val(obj.admixture);
		$('#edit_admixtureadd').val(obj.admixtureadd);
		$('#edit_gypsum').val(obj.gypsum);
		$('#edit_gypsumadd').val(obj.gypsumadd);
		$('#edit_aid').val(obj.aid);
		$('#edit_aidadd').val(obj.aidadd);
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
			alert('输入的数字必须在1~'+maxpageno+'之间');
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
				alert(result.error);
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
			var tr = '<tr>'
				+'<td class="colorred">'+(obj.code || '')+'</td>'
				+'<td>'+(obj.material || '')+'</td>'
				+'<td>'+(obj.factorycode || '')+'</td>'
				+'<td>'+(obj.count || '')+'</td>'
				+'<td>'+(starttime || '')+'</td>'
				+'<td>'+(assaytime || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			tr.data('obj',obj);
		}
	}

	//加载下拉框
	function initSelect(){
		var cache = {};
		$("#edit_qscheme").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				var qscheme = cache['qscheme'] || {};
				if ( term in qscheme ) {
					response( qscheme[ term ] );
					return;
				}
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
		}).on('input propertychange',function(){
	    	$(this).removeAttr('qschemeid');
	    }).change(function(){
    		if(!$(this).attr('qschemeid')){
    			$(this).val('');
    		}
	    });
		$("#edit_materialtype").autocomplete({
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
		}).on('input propertychange',function(){
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
		}).on('input propertychange',function(){
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