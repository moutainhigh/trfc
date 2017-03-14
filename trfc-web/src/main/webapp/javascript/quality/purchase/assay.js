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
	var lineData = {"烧失量":qc0,
					"质量系数":qc1,
					"水分":qc2,
					"外水分":qc3,
					"分析基水分":qc4,
					"分析基灰分":qc5,
					"分析基挥发分":qc6,
					"固定碳":qc7,
					"焦渣特征":qc8,
					"空气干燥基全硫":qc9,
					"分析基低位发热量":qc10,
					"收到基低位发热量":qc11,
					"低位热量":qc12,
	};
	ShowAction(1);
	//绑定搜索按键
	$('#seek').click(function(){ShowAction(1);});
	//绑定跳转按钮
	$('#jumpButton').click(jumpPageAction);
	//监控页码
	$('#pageSize').change(function(){ShowAction(1);});
	//点击新增按钮,初始化新增页面
	$('#addBtn').click(initAddPage);
	var userid = $('.user').attr('userid');
	var Lines = {};
	getLineAndName();
	$('#add_sure').click(saveAction);

	
	
	
	function saveAction(){
		var param = getAddData();
		if(param){
			
		}
	}
	//获取新增数据
	function getAddData(){
		var qschemeid = $('#add_qscheme').attr('qschemeid');
		var code = $('#add_code').val();
		var samplingid = $('#add_sampling').val();
		var assaytime = new Date($('#add_assaytime').val()).getTime();
		var createtime = Date.parseYMD_HMS($('#add_createtime').val()).getTime();
		var remark = $('#add_remark').val();
		var param = {
				qschemeid:qschemeid,
				code:code,
				samplingid:samplingid,
				assaytime:assaytime,
				createtime:createtime,
				remark:remark
		};
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
	}

	function getLineAndName(){
		$.post(URL.getLineAndNameUrl,{type:"0"},function(result){
			if('000000'==result.code){
				var list = result.data;
				for(var i=0;i<list.length;i++){
					var obj = list[i];
					Lines[obj.line] = obj.name;
				}
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}

	function initAddPage(){
		initSelect();
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
		$('#add_qscheme').val('').removeAttr('qschemeid').blur(function(){initAddItems();});
		$('#add_sampling').val('').blur(function(){getSamplingList()});
		$('#add_assaytime').val(getNowFormatDate(false));
		$('#add_createtime').val(getNowFormatDate(true));
		$('#add_creator').val($('.user label').html());
		$('#add_remark').val('');
		$('#add_qschemeitem').empty();
		$('#add_sampinglist').empty();
	}
	//获取质检项目列表
	function initAddItems(){
		var qschemeid = $('#add_qscheme').attr('qschemeid');
		if(qschemeid){
			$.post(URL.inquireUrl,{schemeid:qschemeid,status:"1"},function(result){
				if('000000'==result.code){
					var list = result.data;
					var tbody = $('#add_qschemeitem').empty();
					for(var i=0;i<list.length;i++){
						var div = '<div class="alt_edit_div" name="'+list[i].line+'">'
							+'<label>'+list[i].itemname+'：</label>'
							+' <input type="text">'
							+' </div>';
						div = $(div);
						tbody.append(div);
						div.data('obj',list[i]);
					}
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	//获取采样项目列表
	function getSamplingList(){
		var samplingid = $('#add_sampling').val();
		if(samplingid){
			$.post(URL.findByCodeUrl,{code:samplingid},function(result){
				if('000000'==result.code){
					var obj = result.data;
					$('#add_sampinglist').empty().append('<tr><td>'
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
		var url = URL.pageUrl;
		//获取当前页面记录数
		var pageSize = $('#pageSize').val();
		//获取查询条件
		var starttime = new Date($('#seek_starttime').val());
		starttime = starttime.getTime();
		if(isNaN(starttime)){
			starttime=0;
		}
		var endtime = new Date($('#seek_endtime').val());
		endtime = endtime.getTime();
		if(isNaN(endtime)){
			endtime=0;
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
				alert(result.error);
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
				+'<td class="colorred">'+(obj.code || '')+'</td>'
				+'<td>'+(obj.qschemename || '')+'</td>'
				+'<td>'+(obj.samplingid || '')+'</td>'
				+'<td>'+(getNowFormatDate(false,obj.assaytime) || '')+'</td>'
				+'<td>'+(1 || '')+'</td>'
				+'<td>'+(2 || '')+'</td>'
				+'<td>'+(3|| '')+'</td>'
				+'<td>'+(4 || '')+'</td>'
				+'<td>'+(5 || '')+'</td>'
				+'<td>'+(6 || '')+'</td>'
				+'<td>'+(7 || '')+'</td>'
				+'<td>'+(8 || '')+'</td>'
				+'<td>'+(9 || '')+'</td>'
				+'<td>'+(10 || '')+'</td>'
				+'<td>'+(11 || '')+'</td>'
				+'<td>'+(12|| '')+'</td>'
				+'<td>'+(13 || '')+'</td>'
				+'<td>'+(obj.creator || '')+'</td>'
				+'<td>'+(getNowFormatDate(true,obj.createtime) || '')+'</td>'
				+'<td>'+(obj.remark || '')+'</td>'
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