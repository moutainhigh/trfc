;(function($, win){
	//请求路径
	var URL = {
			addYardUrl:"/trfc/yard/addYard",
			addBtnUrl:"/trfc/common/code/yardCode",
			pageUrl:"/trfc/yard/page",
			updateYardUrl:"/trfc/yard/updateYard",
			delYardUrl:"/trfc/yard/delYard"
	};
	
	init();
	function init(){
		bindEvent();
		queryData(1);
		//加载页面后默认只显示有效按钮被点击
		$('#show_isvalid').click();
	}
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	//公共变量，用于存储数据
	var yardData={};
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#searshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#addBtn').off('click').on('click',function(){
			addYardAction();
		});
		$('#addYard').off('click').on('click',function(){
			addYard();
		});
		//跳转按钮绑定 点击事件
		$('#jumpButton').click(jumpPageAction);
		$('#pageSize').change(function(){
			queryData(1);
		});
		$('#show_isvalid').off('click').on('click',function(){
			queryData(1);
		});
		//监听编辑按钮点击事件
		$('#yards').on('click','tr .update_yard',function(){
			var yard=$(this).closest('tr').data();
//			console.log(yard);
			$('#update_yard_code').val(yard.code);
			$('#update_yard_name').val(yard.name);
			$('#update_yard_remarks').val(yard.remarks);
			$('#update_yard_isvalid')[0].checked=false;
			if(yard.isvalid==1){
				$('#update_yard_isvalid')[0].checked=true;
			}
			yardData.id=yard.id;
		});
		//编辑页面确认按钮点击事件
		$('#update_yard').off('click').on('click',function(){
			updateYard();
		});
		//监听删除按钮点击事件
		$('#yards').on('click','tr .delete_yard',function(){
			var yard=$(this).closest('tr').data();
			yardData.id=yard.id;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteYard();
					layer.close(bn);
		        },btn2: function(index, layero){
		            //按钮【取消】的回调
		        }
		        ,cancel: function(){
		            //右上角关闭回调
		        }
		    });
		});
	}
	//分页查询数据
	function queryData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var url=URL.pageUrl;
		//获取当前页面记录数
		var pageSize=$('#pageSize').val();
		var name='';
		var code='';
		//获取查询条件
		var keyword = $('#yard_keyword').val();keyword = $.trim(keyword);
		if($('#yard_query').val()=='name'){
			name=keyword;
		}else{
			code=keyword;
		}
		
		var isvalid = 0;
		if($('#show_isvalid')[0].checked){
			isvalid = 1;
		}
		var params={
				pageSize:pageSize,
				name:name,
				code:code,
				isvalid:isvalid
		};
		//获取当前页数
		params.pageNo=pageNo;
//		console.log(params);
		$.post(url,params,function(result){
			if(result.code == '000000'){
//				console.log(result.data);
				var data=result.data;
				var list=data.list;
//				console.log(list);
				var pageNo=data.pageNo;
				var pageSize=data.pageSize;
				var total=data.total;
				var tbody=$('#yards').empty();
				//添加数据总数
				$('.colorred').html(data.total);
				//绑定 一个maxpageno属性
				$('#jumpPageNo').attr('maxPageNo',parseInt((total+pageSize-1)/pageSize));
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
				if(!list){
					return;
				}
				for(var i=0;i<list.length;i++){
					var yard=list[i];
					var tr=$('<tr><td>'+((pageNo-1)*pageSize+i+1)+'</td><td>'+yard.code+'</td><td>'+yard.name+'</td><td>'+yard.pinyincode+'</td><td><input type="checkbox" disabled id="list_isvalid'+i+
							'" ></td><td>'+yard.remarks+'</td><td>'+'<span class="update_yard"><a data-toggle="modal" data-target="#edit" ><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
							'</span><span class="delete_yard">'+'<a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>');
					tr.data(yard);
					tbody.append(tr);
					$('#list_isvalid'+i)[0].checked=false;
					if(yard.isvalid==1){
						$('#list_isvalid'+i)[0].checked=true;
					}
				}
				
			}else{
				layer.msg(result.error, {icon: 5});
			}
		});
		layer.close(index);
	}
	
	//回调函数
	function pageCallback(pageNo){
		queryData(pageNo+1);
	}
	
	function jumpPageAction(){
		var maxpageno = $('#jumpPageNo').attr('maxpageno');
		var pageno = $('#jumpPageNo').val();
		if(!pageno || !$.isNumeric(pageno) || pageno<=0 || pageno>maxpageno){
			layer.msg('输入的数字必须在1~'+maxpageno+'之间');
		}else{
			queryData(pageno);
			$('#jumpPageNo').val('');
		}
	}
	
	
	function addYardAction(){
		var param={};
		var url=URL.addBtnUrl;
		$.post(url,param,function(result){
			if(result.code='000000'){
				var data=result.data;
//				console.log(data);
				$('#yard_code').val(data.code);
				$('#yard_name').val('');
				$('#yard_isvalid')[0].checked=true;
				$('#yard_remarks').val('');
			}else{
				layer.msg(result.error,{icon:5})
			}
		});
	}
	
	function addYard(){
		if($('#add').is(':visible')){
			var url=URL.addYardUrl;
			var code = $('#yard_code').val();code = $.trim(code);
			var name = $('#yard_name').val();name = $.trim(name);
			if(!name){
				layer.msg('名称不能为空哦',{icon:5});
				$('#add .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var isvalid = 0;
			if($('#yard_isvalid')[0].checked){
				isvalid = 1;
			}
			var remarks = $('#yard_remarks').val();remarks = $.trim(remarks);
			var pinyincode=pinyin.getCamelChars(name).toUpperCase();
			
			var params={
					code:code,
					name:name,
					pinyincode:pinyincode,
					isvalid:isvalid,
					remarks:remarks,
					user:userid
			};
//			console.log(params);
			$('#addYard').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5});
				}
			});
		}
	}
	
	function updateYard(){
		if($('#edit').is(':visible')){
			var url=URL.updateYardUrl;
			var name=$('#update_yard_name').val();name=$.trim(name);
			if(!name){
				layer.msg('名称不能为空哦',{icon:5});
				$('#edit .btn-primary').removeAttr('data-dismiss');
				return;
			}
			var id=yardData.id;
			var isvalid=0;
			if($('#update_yard_isvalid')[0].checked){
				isvalid=1;
			}
			var remarks=$('#update_yard_remarks').val();remarks=$.trim(remarks);
			var params={
					id:id,
					name:name,
					isvalid:isvalid,
					remarks:remarks,
					user:userid
			};
			$('#update_yard').attr('data-dismiss','modal');
			$.post(url,params,function(result){
				if(result.code='000000'){
					queryData(1);
				}else{
					layer.msg(result.error,{icon:5})
				}
			});
		}
	}
	
	function deleteYard(){
		var url=URL.delYardUrl;
		var params={
				id:yardData.id,
				user:userid
		};
		$.post(url,params,function(result){
			if(result.code='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5})
			}
		});
	}
})(jQuery, window);