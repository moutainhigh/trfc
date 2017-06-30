$(function(){
	var URL = {
			editUrl:"/trfc/quality/sales/report/editmain",
			addUrl:"/trfc/quality/sales/report/addmain",
			detailUrl:"/trfc/quality/sales/report/detailmain",
			pageUrl:"/trfc/quality/sales/report/page",
			deleteUrl:"/trfc/quality/sales/report/delete",
			updateUrl:"/trfc/quality/sales/report/update",
			codeUrl:"/trfc/quality/sales/report/getCode",
			updateCodeUrl:"/trfc/quality/sales/report/updateCode",
	};
	ShowAction(1);
	//绑定刷新按钮
	$('#fresh').click(function(){
		ShowAction(1);
		layer.closeAll('dialog');
	});
	//绑定跳转按钮
	$("#jumpButton").click(jumpPageAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){ShowAction(1);});
	//绑定搜索按钮
	$('#seek').click(function(){ShowAction(1);});
	//绑定编辑按钮
	$('#update').off('click').on('click',function(e){
		e.stopPropagation();
		editAction();
	});
	//绑定删除按钮
	$('#delete').off('click').on('click',function(e){
		e.stopPropagation();
		deleteAction();
	});
	//绑定28天报告按钮
	$('#report').off('click').on('click',function(e){
		e.stopPropagation();
		daysReportAction();
	});
	//绑定审核按钮
	$('#audit').off('click').on('click',function(e){
		e.stopPropagation();
		auditAction();
	});
	//绑定反审按钮
	$('#unaudit').off('click').on('click',function(e){
		e.stopPropagation();
		denyAction();
	});
	//绑定复制按钮
	$('#copy').off('click').on('click',function(e){
		e.stopPropagation();
		copyAction();
	});
	//绑定详情按钮
	$('#list').on('dblclick','tr',function(){
		var id = $(this).closest('tr').data('obj').id;
		window.location.href = URL.detailUrl+"?id="+id;
	});
	//绑定新增按钮
	$('#addBtn').click(addAction);
	//绑定搜索条件 报告天数 change事件
	$('#seek_reportdays').change(function(){$('#seek').click();});
	//28天报告
	function daysReportAction(){ 
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if(obj.reporttype=='0'){
			//确认框
			var index = layer.confirm('确定更改为28报告吗?', {
				area: '600px', 
				btn: ['确定','取消'] //按钮
			}, function(){

				$.post(URL.updateUrl,{id:obj.id,reporttype:'1'},function(result){
					if('000000'==result.code){
						ShowAction(1);
					}else{
						layer.msg(result.error,{icon:5});
					}
				});

				//关闭对话框
				layer.close(index);
			}, function(){
			});
			
		}else {
			layer.msg('已是28天报告,无需重复操作',{icon:5});
		}
			
	}
	//审核
	function auditAction(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if((obj.auditstate == '2')){
			layer.msg('数据已审核,不能进行审核操作');
		}else{
			$('table.maintable tbody tr.active').dblclick();
		}
	}
	//反审
	function denyAction(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		if((obj.auditstate!='2')){
			layer.msg('数据未审核,不能对未审核的数据进行反审操作');
		}else{
			//弹出反审确认框
			var index = layer.confirm('你确定要进行反审吗?', {
				area: '600px', 
				btn: ['确定','取消'] //按钮
			}, function(){

				var param = {
						id:obj.id,
						auditstate:'0',
						user:$('.user').attr('userid')
				};
				$.post(URL.updateUrl,param,function(result){
					if('000000'==result.code){
						ShowAction(1);
					}else{
						layer.msg(result.error,{icon:5});
					}
				});

				//关闭对话框
				layer.close(index);
			}, function(){
			});
		}
	}
	function copyAction(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		window.location.href=URL.addUrl+'?id='+obj.id;
	}
//	删除
	function deleteAction(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//弹出删除确认框
		var index = layer.confirm('你确定要删除吗?', {
			area: '600px', 
			btn: ['确定','取消'] //按钮
		}, function(){
			//提交删除的数据
			submitDelete(obj.id);
			//关闭对话框
			layer.close(index);
		}, function(){
		});

	};
	function submitDelete(id){
		$.post(URL.updateUrl,{id:id,state:'0'},function(result){
			if(result.code=='000000'){
				//重新加载当前页面
				ShowAction(1);
			}else{
				layer.msg(result.error, {icon:5});
			}
		});
	}
//	编辑
	function editAction(){
		var obj = $('table.maintable tbody tr.active').data('obj');
		if(!obj) {layer.msg('需要选中一行才能操作哦！'); return;}
		//跳转到编辑页面
		window.location.replace(URL.editUrl+"?id="+obj.id);
	}
//	跳转到新增页面
	function addAction(){
		//跳转到新增页面
		window.location.href = URL.addUrl;
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
		var batchcode = $('#seek_batchcode').val();
		var reportdays = $('#seek_reportdays').val();

		var params = {
				pageSize:pageSize,
				starttime:starttime,
				endtime:endtime,
				code:code,
				batchcode:batchcode,
				reporttype:reportdays
		};
		//获得当前页面标记
		params.pageNo = pageNo;
		$.post(URL.pageUrl,params,function(result){
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
		//设置TYPE变量 ,作用为 显示结果
		var PSTATE = {
				0:'否',
				1:'是'
		};
		var auditSTATE = {
				0:'待审核',
				1:'审核中',
				2:'已审核'
		};
		var ReportTYPE = {
				0:'3天报告',
				1:'28天报告'
		};
		//加载时清空列表和跳转值
		$('#jumpPageNo').val('');
		//清空列表
		var tbody = $('#list').empty();
		for(var i=0;i<list.length;i++){
			var obj = list[i];
			var producetime = getNowFormatDate(false,obj.producetime);
			var testtime = getNowFormatDate(false,obj.testtime);
			var selldate = getNowFormatDate(false,obj.selldate);
			var audittime = '';
			if(obj.audittime){
				var audittime = getNowFormatDate(true,obj.audittime);
			}
			var auditstate = auditSTATE[obj.auditstate] || '';
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td>'+(obj.code || '')+'</td>'
				+'<td>'+auditstate+'</td>'
				+'<td>'+(PSTATE[obj.pstate] || '')+'</td>'
				+'<td>'+(obj.materialtype || '')+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td>'+(obj.batchcode || '')+'</td>'
				+'<td>'+(producetime || '')+'</td>'
				+'<td>'+(testtime || '')+'</td>'
				+'<td>'+(selldate || '')+'</td>'
				+'<td>'+(ReportTYPE[obj.reporttype] || '')+'</td>'
				+'<td>'+(obj.reporter || '')+'</td>'
				+'<td>'+(obj.addr || '')+'</td>'
				+'<td>'+(obj.auditer || '')+'</td>'
				+'<td>'+(audittime || '')+'</td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
			if(obj.auditstate==0){
				tr.find('td').eq(2).addClass('colorred');
			}else if(obj.auditstate==1){
				tr.find('td').eq(2).addClass('colorblue');
			}
			if(obj.pstate=='0'){
				tr.find('td').eq(3).addClass('colorred');
			}
		}
	}

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