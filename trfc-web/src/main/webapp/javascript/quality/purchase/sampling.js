$(function(){
	//用来存储需要删除采样车辆信息
	var samplingItems = new Array();
	//用来存储已读IC卡号
	var cardIdArr = new Array();
	var URL = {
			systemDataAutoCompleteSearch:"/trfc/system/base/dataDict/autoCompleteSearch",
			pageUrl:"/trfc/quality/purchase/sampling/page",
			deleteUrl:"/trfc/quality/purchase/sampling/delete",
			updateUrl:"/trfc/quality/purchase/sampling/update",
			saveUrl:"/trfc/quality/purchase/sampling/add",
			getCodeUrl:"/trfc/system/base/code/getCode",
			updateCodeUrl:"/trfc/system/base/code/updateCode",
			getDetailUrl:"/trfc/quality/purchase/sampling/getDetailData"
	};
	init();
	function init(){
		//初始化默认查询当nian的数据
		var array = getTodayStr();
		$('#seek_starttime').val(array[0]);
		$('#seek_endtime').val(array[1]);
		ShowAction(1);
		initSelect();
	}
	// 表格内容每行单击出来下面的详细信息
	$('#list').on("click", 'tr',function () {
		$(".intel_result").css("display", "block");
		var tbody = $('#detail_list').empty();
		var id = $(this).data('obj').id;
		loadDetailData(tbody,id);
	});
	var userid = $(".user").attr("userid");
	
	//绑定刷新按钮
	$('#fresh').click(function(){
		ShowAction(1);
		layer.closeAll('dialog');
	});
	//绑定新增按钮
	$('#addBtn').click(initAddData);
	//绑定搜索按钮
	$('#seek').click(function(){ShowAction(1);});
	//绑定跳转按钮
	$("#jumpButton").click(jumpPageAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){ShowAction(1);});
	//绑定删除按钮
	$('#delete').off('click').on('click',function(e){
		e.stopPropagation();
		deleteAction(this)});
	//绑定编辑按钮
	$('#update').off('click').on('click',function(e){
		e.stopPropagation();
		initEditData(this);
	});
	$('#list').on('dblclick','tr',function(){
		$('#caigoubill').modal('show');
		initDetailData(this);
	});
	//绑定读卡功能
	$('#add_readBtn').click(function(){
		var tbody = $("#add_list");
		getSamplingCarData(tbody);});
	$('#edit_readBtn').click(function(){
		var tbody = $("#edit_list");
		getSamplingCarData(tbody);});
	//绑定新增保存
	$('#add_sure').click(saveAction);
	//绑定新增刷新
	$('#add_fresh').click(initAddData);
	$('#edit_sure').click(editAction);




	function indexOfList(tbody){
		var trs = tbody.find('tr');
		for(var i=0;i<trs.length;i++){
			trs.eq(i).find('td:first').html(i+1);
		}
	}

	//加载详情数据
	function initDetailData(tr){
		var obj = $(tr).data('obj');
		$("#detail_code").val(obj.code);
		$("#detail_samplingtime").val(getNowFormatDate(false,obj.samplingtime));
		$("#detail_creator").val(obj.creator);
		$("#detail_createtime").val(getNowFormatDate(true,obj.createtime));
		$("#detail_assaytype").val(obj.assayname).attr("assayid",obj.assaytype);
		$("#detail_remark").val(obj.remark);
		var tbody = $("#vehicle_list").empty();
		loadDetailData(tbody,obj.id);
	}

	//加载详情
	function loadDetailData(tbody,id){

		$.post(URL.getDetailUrl,{id:id},function(result){
			if('000000'==result.code){
				var list = result.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					var tr = '<tr>'
						+'<td></td>'
						+'<td>'+(obj.samplingcode || '')+'</td>'
						+'<td>'+(obj.samplingcar || '')+'</td>'
						+'<td>'+(obj.supplier || '')+'</td>'
						+'<td>'+(obj.material || '')+'</td>'
						+'<td>'+(obj.mine || '')+'</td>'
						+'<td>'+(obj.vehicle || '')+'</td>'
						+'<td>'+(obj.remark || '')+'</td>'
						+'</tr>';
					tr = $(tr);
					tbody.append(tr);
					tr.data('obj',obj);
				}
				indexOfList(tbody);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	};

	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day+" "+"23:59:59";
		return array;
	}
	
	//保存新增数据
	function editAction(){
		var param = getEditData();
		if(param){
			$.post(URL.updateUrl,param,function(result){
				if('000000'==result.code){
					ShowAction(1);
					$('#edit_cancel').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}

	//获取编辑数据
	function getEditData(){
		var code = $("#edit_code").val();
		var samplingtime = new Date($("#edit_samplingtime").val());
		samplingtime = samplingtime.getTime();
		if(isNaN(samplingtime)){
			layer.msg("采样日期无效!");
			return null;
		}
		var id = $('#edit_id').val();
		var createtime = new Date($("#edit_createtime").val());
		createtime = createtime.getTime();
		var assaytype = $("#edit_assaytype").attr('assayid');
		var remark = $("#edit_remark").val();
		var trs = $("#edit_list>tr");
		var arr = new Array();
		for(var i=0;i<trs.length;i++){
			var obj=trs.eq(i).data('obj');
			if(!obj.id){
				var samplingcode = obj.code;
				var samplingcar = obj.car;
				arr.push({samplingcode:samplingcode,samplingcar:samplingcar});
			}
		}
		var arrstr = JSON.stringify(arr);
		if(trs.length==0){
			layer.msg("采样车辆信息不能为空!")
			return null;
		}

		var param = {
				id:id,
				samplingtime:samplingtime,
				user:userid,
				assaytype:assaytype,
				remark:remark,
				arrstr:arrstr
		};
		if(samplingItems.length>0){
			param.idToDelete = JSON.stringify(samplingItems);
		}
		return param;
	}

	//保存新增数据
	function saveAction(){
		var param = getAddData();
		if(param){
			$.post(URL.saveUrl,param,function(result){
				if('000000'==result.code){
					//更新计数
					updateCode();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	//获取新增数据
	function getAddData(){
		var code = $("#add_code").val();
		var samplingtime = new Date($("#add_samplingtime").val());
		samplingtime = samplingtime.getTime();
		if(isNaN(samplingtime)){
			layer.msg("采购日期无效!");
			return null;
		}
		var createtime = Date.parseYMD_HMS($("#add_createtime").val()).getTime();
		var assaytype = $("#add_assaytype").attr('assayid');
		var remark = $("#add_remark").val();
		var trs = $("#add_list>tr");
		var arr = new Array();
		for(var i=0;i<trs.length;i++){
			var obj=trs.eq(i).data('obj');
			var samplingcode = obj.code;
			var samplingcar = obj.car;
			arr[i]={samplingcode:samplingcode,samplingcar:samplingcar};
		}
		//arr = [{samplingcode:'000000',samplingcar:'DH201709280062'}]
		var arrstr = JSON.stringify(arr);
		if(arrstr=='[]'){
			layer.msg("采样车辆信息不能为空!")
			return null;
		}
		var param = {
				code:code,
				samplingtime:samplingtime,
				user:userid,
				createtime:createtime,
				assaytype:assaytype,
				remark:remark,
				arrstr:arrstr
		};
		return param;
	}
	function updateCode(){
		var param = {
				userid:userid,
				code:"CY",
				codeType:true
		};
		$.post(URL.updateCodeUrl,param,function(result){
			if('000000'==result.code){
				ShowAction(1);
				$('#add_cancel').click();
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	//获取采样车辆信息
	function getSamplingCarData(tbody){
		if(initCardReader()) {
			//打开读卡器
			readerOpen();
			//读卡
			var cardId = openCard();
			if(cardId){
				if(cardIdArr.indexOf(cardId)<0){
					cardIdArr.push(cardId);
				}else{
					readerClose();
					layer.msg('此卡已读!');
					return;
				}
				//提示读卡成功
				readerBeep();
				try{
					var obj = {
							id:'',
							code:getDataFromCard(29),
							car:getDataFromCard(12),
							supplier:getDataFromCard(5)+getDataFromCard(6),
							material:getDataFromCard(8),
							mine:getDataFromCard(28),
							vehicle:getDataFromCard(4),
							remark:getDataFromCard(24)
					};
				}catch (e) {
					msg(e.Message);
				}
				//关闭读卡器
				readerClose();
				if(obj){
					var tr = '<tr>'
						+'<td></td>'
						+'<td>'+(obj.code || '')+'</td>'
						+'<td>'+(obj.car || '')+'</td>'
						+'<td>'+(obj.supplier || '')+'</td>'
						+'<td>'+(obj.material || '')+'</td>'
						+'<td>'+(obj.mine || '')+'</td>'
						+'<td>'+(obj.vehicle || '')+'</td>'
						+'<td>'+(obj.remark || '')+'</td>'
						+'</tr>';
					tr = $(tr);
					tbody.append(tr);
					tr.data('obj',obj);
					tr.dblclick(function(){tr.remove();indexOfList(tbody);});
					indexOfList(tbody);
				}
			}else{
				readerClose();
			}
		}else{
			layer.msg('当前游览器不支持!(只兼容IE游览器)');
		}
	}

	//初始化编辑事件
	function initEditData(btn){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		cardIdArr.splice(0,cardIdArr.length);
		samplingItems.splice(0,samplingItems.length);
		$("#edit_id").val(obj.id);
		$("#edit_code").val(obj.code);
		$("#edit_samplingtime").val(getNowFormatDate(false,obj.samplingtime));
		$("#edit_creator").val(obj.creator);
		$("#edit_createtime").val(getNowFormatDate(true,obj.createtime));
		$("#edit_assaytype").val(obj.assayname).attr("assayid",obj.assaytype);
		$("#edit_remark").val(obj.remark);
		var tbody = $("#edit_list").empty();
		loadDetailData(tbody,obj.id);
		$('#edit').modal('show');
		tbody.on('dblclick','tr',function(){
			var obj = $(this).data('obj');
			if(obj){
				if(obj.id){
					samplingItems.push(obj.id);
				}
				//移除该列
				$(this).remove();
				indexOfList(tbody);
			}
		});
	}
	//删除数据
	function deleteAction(btn){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//弹出删除确认框
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//提交删除的数据
			$.post(URL.deleteUrl,{id:obj.id},function(result){
				if('000000'==result.code){
					ShowAction(1);
					layer.close(index);
				}else{
					layer.msg(result.error, {icon: 5});
				}
			})
			//关闭对话框
		}, function(){
		});
	}

	//初始化新增数据
	function initAddData(){
		cardIdArr.splice(0,cardIdArr.length);
		var param = {
				userid:userid,
				code:"CY",
				codeType:true
		};
		$.post(URL.getCodeUrl,param,function(result){
			if('000000'==result.code){
				$('#add_code').val(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		$("#add_samplingtime").val(getNowFormatDate(false));
		$("#add_creator").val($('.user label').html());
		$("#add_createtime").val(getNowFormatDate(true));
		$("#add_assaytype").val("");
		$("#add_remark").val("");
		$("#add_list").empty();
	};

	function initSelect(){
		var cache={};
		$(".assaySel").autocomplete({
			//数据源
			source: function( request, response ) {
				var term = request.term;
				//设置为化验类型的id
				request.dictid = "18cd562f6d3041e2be797d8b289d1242";
				var assay = cache['assay'] || {};
				if ( term in assay ) {
					response( assay[ term ] );
					return;
				}
				$.post( URL.systemDataAutoCompleteSearch, request, function( result, status, xhr ) {
					assay[ term ] = result.data;
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
				$(this).val(ui.item.name).attr('assayid', ui.item.id);
				return false;
			}
		}).off('click').on('click',function(){
			$(this).autocomplete('search',' ');
		}).on('input keydown',function(){
			$(this).removeAttr('assayid');
		}).change(function(){
			if(!$(this).attr('assayid')){
				$(this).val('');
			}
		});


	}


//	页面跳转
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
			ShowAction(pageno);
		}
	}
//	前进一页
	function pageCallback(pageNo){
		ShowAction(pageNo+1);
	}

//	展示数据列表
	function ShowAction(pageNo){
		//启动缓冲动画
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var starttime = $('#seek_starttime').val();
		if(starttime){
			starttime = new Date(starttime);
			starttime = starttime.getTime();
		}
		var endtime = $('#seek_endtime').val();
		if(endtime){
			endtime = new Date(endtime);
			endtime = endtime.getTime();
		}
		if(starttime && endtime){
			if(starttime>endtime){
				layer.msg('开始时间不能大于结束时间!',{icon:5});
				layer.close(index);
				return;
			}
		}
		var assaytype = $('#seek_assaytype').attr('assayid');
		var code = $('#seek_code').val();

		var params = {
				pageSize:pageSize,
				starttime:starttime || 0,
				endtime:endtime || 0,
				assaytype:assaytype,
				code:code
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
				//关闭缓冲图标
				layer.close(index);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
//	展示列表
	function showPageData(list,pageSize,pageNo){
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td>'+(getNowFormatDate(false,obj.samplingtime) || '')+'</td>'
				+'<td>'+(obj.assayname || '')+'</td>'
				+'<td>'+(obj.creator || '')+'</td>'
				+'<td>'+(getNowFormatDate(true,obj.createtime) || '')+'</td>'
				+'<td>'+(obj.remark || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
		}
		//加载列表后隐藏详情
		$(".intel_result").css("display", "none");
	}

	//获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
	//time(获取指定时间的字符串) 默认返回当前时间
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
	};
});