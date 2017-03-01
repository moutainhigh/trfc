$(function(){
	var URL = {
			editUrl:"/trfc/quality/sales/report/editmain",
			addUrl:"/trfc/quality/sales/report/addmain",
			pageUrl:"/trfc/quality/sales/report/page",
			deleteUrl:"/trfc/quality/sales/report/delete",
			updateUrl:"/trfc/quality/sales/report/update",
			saveUrl:"/trfc/quality/sales/report/add",
			codeUrl:"/trfc/quality/sales/report/getCode",
			updateCodeUrl:"/trfc/quality/sales/report/updateCode",
	};
	ShowAction(1);
	//绑定刷新按钮
	$('#fresh').click(function(){ShowAction(1);});
	//绑定跳转按钮
	$("#jumpButton").click(jumpPageAction);
	//监听每页记录 事件
	$('#pageSize').change(function(){ShowAction(1);});
	//绑定搜索按钮
	$('#seek').click(function(){ShowAction(1);});
	//绑定编辑按钮
	$('#list').on('click','tr [title="编辑"]',editAction);
	//绑定新增按钮
	$('#addBtn').click(addAction);
	//编辑
	function editAction(){
		//跳转到编辑页面
		window.location.replace(URL.editUrl+"?id="+$(this).closest('tr').data('obj').id);
	}
	//跳转到新增页面
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
			alert('输入的数字必须在1~'+maxpageno+'之间');
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
		var starttime = new Date($('#seek_starttime').val());
		starttime = starttime.getTime();
		if(isNaN(starttime)){
			starttime=null;
		}
		var endtime = new Date($('#seek_endtime').val());
		endtime = endtime.getTime();
		if(isNaN(endtime)){
			endtime=null;
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
				alert(result.error);
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
			var audittime = getNowFormatDate(true,obj.audittime);
			var tr = '<tr>'
				+'<td>'+((pageNo-1)*pageSize+i+1)+'</td>'
				+'<td class="colorred">'+(obj.code || '')+'</td>'
				+'<td>'+(auditSTATE[obj.auditstate] || '')+'</td>'
				+'<td>'+(PSTATE[obj.pstate] || '')+'</td>'
				+'<td>'+(obj.materialtype || '')+'</td>'
				+'<td>'+(obj.materialname || '')+'</td>'
				+'<td class="colorblue">'+(obj.batchcode || '')+'</td>'
				+'<td>'+(producetime || '')+'</td>'
				+'<td>'+(testtime || '')+'</td>'
				+'<td>'+(selldate || '')+'</td>'
				+'<td>'+(ReportTYPE[obj.reporttype] || '')+'</td>'
				+'<td>'+(obj.reporter || '')+'</td>'
				+'<td>'+(obj.addr || '')+'</td>'
				+'<td>'+(obj.auditer || '')+'</td>'
				+'<td>'+(audittime || '')+'</td>'
				+'<td><span> <a data-toggle="modal"'
				+'		data-target="#edit"><i class="iconfont"'
				+'			data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'
				+'</span> <span> <a data-toggle="modal" data-target="#dele"><i'
				+'			class="iconfont" data-toggle="tooltip" data-placement="left"'
				+'			title="删除">&#xe63d;</i></a>'
				+'</span></td>'
				+'</tr>';
			//转换为jquery对象
			tr=$(tr);
			//追加
			tbody.append(tr);
			//将数据绑定到tr上
			tr.data('obj',obj);
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
//		判断返回结果
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