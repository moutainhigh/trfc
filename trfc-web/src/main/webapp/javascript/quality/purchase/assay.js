$(function(){

	var URL = {
			pageUrl:"/trfc/quality/purchase/assay/page",
			deleteUrl:"/trfc/quality/purchase/assay/delete",
			updateUrl:"/trfc/quality/purchase/assay/update",
			saveUrl:"/trfc/quality/purchase/assay/add",
			getCodeUrl:"/trfc/system/base/code/getCode",
			updateCodeUrl:"/trfc/system/base/code/updateCode",
			findByCodeUrl:"/trfc/quality/purchase/sampling/findByCode",
			inquireUrl:"/trfc/quality/sales/file/qualityScheme/inquire",
			getLineAndNameUrl:"/trfc/quality/sales/file/qualityItem/getLineAndName",
			qschemeAutoCompleteSearch: "/trfc/quality/sales/file/qualityScheme/autoCompleteSearch",
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
	//绑定刷新按钮
	//绑定刷新按钮
	$('#fresh').click(function(){
		ShowAction(1);
		layer.closeAll('dialog');
	});
	//绑定搜索按键
	$('#seek').click(function(){ShowAction(1);});
	//绑定跳转按钮
	$('#jumpButton').click(jumpPageAction);
	//监控页码
	$('#pageSize').change(function(){ShowAction(1);});
	//点击新增按钮,初始化新增页面
	$('#addBtn').click(initAddPage);
	var userid = $('.user').attr('userid');
	$('#add_sure').click(saveAction);
	$('#edit_sure').click(updateAction);
	
	//绑定删除按钮
	$('#delete').off('click').on('click',function(e){
		e.stopPropagation();
		deleteAction(this)});
	//绑定编辑按钮
	$('#update').off('click').on('click',function(e){
		e.stopPropagation();
		initEditPage();
	});
	//新增页面 质检方案
//	$('#add_qscheme').blur(function(){
//		var qschemeid = $('#add_qscheme').attr('qschemeid');
//		initAddItems($('#add_qschemeitem'),qschemeid);});
	//新增页面 采购单号
	$('#add_sampling').blur(function(){
		var samplingid = $('#add_sampling').val();
		getSamplingList($('#add_sampinglist'),samplingid)});
	//编辑页面 
//	$('#edit_qscheme').blur(function(){
//		var qschemeid = $('#edit_qscheme').attr('qschemeid');
//		initAddItems($('#edit_qschemeitem'),qschemeid);});
	$('#edit_sampling').blur(function(){
		var samplingid = $('#edit_sampling').val();
		getSamplingList($('#edit_sampinglist'),samplingid)});
	// 表格内容每行单击出来下面的详细信息
	$('#list').on('dblclick','tr',function(){
		$('#detail').modal('show');
		initDetailData(this);
	});


	function initDetailData(e){
		var obj = $(e).data('obj');
		$('#detail_code').val(obj.code);
		$('#detail_qscheme').val(obj.qschemename);
		initAddItems('detail_qschemeitem',obj.qschemeid,obj);
		$('#detail_sampling').val(obj.samplingid);
		getSamplingList($('#detail_sampinglist'),obj.samplingid)
		$('#detail_assaytime').val(getNowFormatDate(false,obj.assaytime));
		$('#detail_createtime').val(getNowFormatDate(true,obj.createtime));
		$('#detail_creator').val(obj.creator);
		$('#detail_remark').val(obj.remark);
	}

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
					layer.msg(result.error,{icon:5});
				}
			});
			//关闭对话框
		}, function(){
		});
	}
	function updateAction(){
		var param = getEditData();
		if(param){
			$.post(URL.updateUrl,param,function(result){
				if('000000'==result.code){
					$('#edit_cancel').click();
					ShowAction(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	function saveAction(){
		var param = getAddData();
		if(param){
			$.post(URL.saveUrl,param,function(result){
				if('000000'==result.code){
					ShowAction(1);
					$('#add_cancel').click();
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	//获取编辑数据
	function getEditData(){
		var id = $('#edit_id').val();
		var qschemeid = $('#edit_qscheme').attr('qschemeid');
		var code = $('#edit_code').val();
		var samplingid = $('#edit_sampling').val();
		var assaytime = new Date($('#edit_assaytime').val()).getTime();
		var createtime = Date.parseYMD_HMS($('#edit_createtime').val()).getTime();
		var remark = $('#edit_remark').val();
		var param = {
				id:id,
				qschemeid:qschemeid,
				code:code,
				samplingid:samplingid,
				assaytime:assaytime,
				createtime:createtime,
				user:userid,
				remark:remark
		};
		var divs = $('#edit_qschemeitem>div');
		for(var i=0;i<divs.length;i++){
			var obj = divs.eq(i).data('obj');
			param[obj.line.toLowerCase()] = divs.eq(i).find('input').val();
		}
		return param;
	}
	

	function getTodayStr(){
		var myDate = new Date();
		var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = myDate.getDate();        //获取当前日(1-31)
		var day1 = myDate.getDate()+1;        //获取当前日(1-31)
		var hours = myDate.getHours();       //获取当前小时数(0-23)
		var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
		var seconds = myDate.getSeconds();     //获取当前秒数(0-59)
		if(month<10){
			month = "0"+ month;
		}
		if(day<10){
			day = "0"+day;
		}
		if(day1<10){
			day1 = "0"+day1;
		}
		var array = new Array();
		array[0]=year+"-"+month+"-"+day+" "+"00:00:00";
		array[1]=year+"-"+month+"-"+day1+" "+"00:00:00";
		return array;
	}
	
	//获取新增数据
	function getAddData(){
		var qschemeid = $('#add_qscheme').attr('qschemeid');
		if(!qschemeid){
			layer.msg('质检产品方案不能为空!');
			return null;
		}
		var code = $('#add_code').val();
		var samplingid = $('#add_sampling').val();
		if(''==samplingid || $('#add_sampinglist').find('tr').length==0){
			layer.msg('请填写有效单号!');
			return null;
		}
		var assaytime = new Date($('#add_assaytime').val()).getTime();
		var createtime = Date.parseYMD_HMS($('#add_createtime').val()).getTime();
		var remark = $('#add_remark').val();
		var param = {
				qschemeid:qschemeid,
				code:code,
				samplingid:samplingid,
				assaytime:assaytime,
				createtime:createtime,
				user:userid,
				remark:remark
		};
		var divs = $('#add_qschemeitem>div');
		for(var i=0;i<divs.length;i++){
			var obj = divs.eq(i).data('obj');
			if(obj.line){
				param[obj.line.toLowerCase()] = divs.eq(i).find('input').val();
			}
		}
		return param;
	}

	//加载下拉框
	function initSelect(){
		var cache = {};
		$(".qschemeSel").autocomplete({
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
				initAddItems('add_qschemeitem',ui.item.id);
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
	}

	//初始化编辑页面
	function initEditPage(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		$('#edit_id').val(obj.id);
		$('#edit_code').val(obj.code);
		$('#edit_qscheme').val(obj.qschemename).attr('qschemeid',obj.qschemeid);
		initAddItems('edit_qschemeitem',obj.qschemeid,obj);
		$('#edit_sampling').val(obj.samplingid).blur();
		$('#edit_assaytime').val(getNowFormatDate(false,obj.assaytime));
		$('#edit_createtime').val(getNowFormatDate(true,obj.createtime));
		$('#edit_creator').val(obj.creator);
		$('#edit_remark').val(obj.remark);
		$('#edit').modal('show');
	}
	//初始化新增页面
	function initAddPage(){
		var param = {
				userid:userid,
				code:"HY",
				codeType:true
		};
		$.post(URL.getCodeUrl,param,function(result){
			if('000000'==result.code){
				$('#add_code').val(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		$('#add_qscheme').val('').removeAttr('qschemeid')
		$('#add_sampling').val('');
		$('#add_assaytime').val(getNowFormatDate(false));
		$('#add_createtime').val(getNowFormatDate(true));
		$('#add_creator').val($('.user label').html());
		$('#add_remark').val('');
		$('#add_qschemeitem').empty();
		$('#add_sampinglist').empty();
	}
	//获取质检项目列表
	function initAddItems(html_id,qschemeid,obj){
		if ($('#edit').is(':visible')) {
			html_id = 'edit_qschemeitem';
		}
		var tbody = $('#'+html_id);
		//清空内容
		tbody.empty();
		if(qschemeid){
			//通过质检方案id 获取项目列表
			$.post(URL.inquireUrl,{schemeid:qschemeid,status:"1"},function(result){
				if('000000'==result.code){
					var list = result.data;
					for(var i=0;i<list.length;i++){
						if(obj && list[i].line){
							var div = '<div class="alt_edit_div">'
								+'<label style="width: 150px;">'+(list[i].itemname || 0)+'：</label>'
								+' <input type="text" value="'+obj[(list[i].line.toLowerCase())]+'">'
								+' </div>';
						}else{
							var div = '<div class="alt_edit_div">'
								+'<label style="width: 150px;">'+(list[i].itemname || '')+'：</label>'
								+' <input type="text" value="0">'
								+' </div>';
						}
						div = $(div);
						tbody.append(div);
						div.data('obj',list[i]);
						
					}
					if(html_id == 'detail_qschemeitem'){
						$('#detail_qschemeitem >div >input').attr('readOnly',true);
					}
				}else{
					layer.msg(result.error,{icon:5});
				};
			});
		}
	}
	//获取采样项目列表
	function getSamplingList(tbody,samplingid){
		tbody.empty();
		if(samplingid){
			$.post(URL.findByCodeUrl,{code:samplingid},function(result){
				if('000000'==result.code){
					var obj = result.data;
					tbody.append('<tr><td>'
							+obj.code+'</td><td>'
							+obj.assayname+'</td></tr>');
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
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
		var code = $('#seek_code').val();
		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
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
				+'<td>'+(obj.qschemename || '')+'</td>'
				+'<td>'+(obj.samplingid || '')+'</td>'
				+'<td>'+(getNowFormatDate(false,obj.assaytime) || '')+'</td>'
				+'<td>'+(obj.qc0 || '')+'</td>'
				+'<td>'+(obj.qc1 || '')+'</td>'
				+'<td>'+(obj.qc2 || '')+'</td>'
				+'<td>'+(obj.qc3 || '')+'</td>'
				+'<td>'+(obj.qc4 || '')+'</td>'
				+'<td>'+(obj.qc5 || '')+'</td>'
				+'<td>'+(obj.qc6 || '')+'</td>'
				+'<td>'+(obj.qc7 || '')+'</td>'
				+'<td>'+(obj.qc8 || '')+'</td>'
				+'<td>'+(obj.qc9 || '')+'</td>'
				+'<td>'+(obj.qc10 || '')+'</td>'
				+'<td>'+(obj.qc11 || '')+'</td>'
				+'<td>'+(obj.qc12 || '')+'</td>'
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
	}

//	获取时间 param(true:返回yyyy-MM-dd hh:mm:ss fasle:返回yyyy-MM-dd)
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
	};


});