;(function($, win){
	
	var URL = {
			addBtnUrl:"/trfc/system/auth/subsystem/add",
			pageUrl:"/trfc/system/auth/subsystem/page",
			editUrl:"/trfc/system/auth/subsystem/edit",
			deleteUrl:"/trfc/system/auth/subsystem/delete",
			getTreeDataUrl:"/trfc/system/auth/subsystem/treeData",
	};
	
	
	init();
	function init(){
		bindEvent();
		queryData(1);
		getTreeData();
	}
	
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	
	var menuData={};
	var treeData=[];
	function bindEvent(){
		// 菜单图标选择效果
		var icon_li = $('.menu_icon ul li');
		icon_li.on("click", function () {
		    $(this).addClass('select').siblings().removeClass('select');
		});
	
		// 新增弹出框获取的图标代码，赋值给页面树形目录
		$("#quding").on("click", function () {
			var icon = $('#menu_icons > ul > li.select > i');
		    $("#iconid").text(icon.text()).attr("img_type",icon.attr('icon_id'));
		    $("#edit_iconid").text(icon.text()).attr("img_type",icon.attr('icon_id'));
		});
		
		//图标
		$("#iconid").dblclick(function() {
	    	$("#iconid").text(""),removeAttr("img_type");
	    });
		$("#edit_iconid").dblclick(function(){
	    	$("#edit_iconid").text(""),removeAttr("img_type");
	    });
		
		//刷新
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		
		//新增
		$('#initAdd').off('click').on('click',function(){
			initAddMenu();
		});
		//保存
		$('#ensureAdd').off('click').on('click',function(){
			if($('#add').is(':visible')){
				this.disabled = true;
				addMenuAction(this);
			}
		});
		
		
		$('#menus').on('click','tr .update',function(){
			var menu=$(this).closest('tr').data();
			$('#update_name').val(menu.name);
			$('#update_code').val(menu.code);
			$('#_easyui_textbox_input2').val(menu.roleType);
			$('#update_role').combotree('loadData',treeData).combotree('setValue',menu.roleid);
			var linkgoal=menu.linkgoal;
			switch(linkgoal){
				case 'Click':
					linkgoal=1;
					break;
				case 'Iframe':
					linkgoal=2;
					break;
				case 'Open':
					linkgoal=3;
					break;
				case 'href':
					linkgoal=4;
					break;
				default:
					linkgoal=0;
					break;
			}
			$('#update_linkgoal').val(linkgoal);
			$('#update_uri').val(menu.uri);
			$('#update_isvalid')[0].checked=false;
			if(menu.isvalid==1){
				$('#update_isvalid')[0].checked=true;
			}
			if(menu.imgType){
				$('#edit_iconid').html('&'+menu.imgType);
			}else{
				$('#edit_iconid').html('');
			}
			$('#update_orderBy').val(menu.orderBy);
			$('#update_info').val(menu.info);
			$('#update_param').val(menu.param);
			$('#update_grouping').val(menu.grouping);
			menuData.id=menu.id;
		});
		
		//编辑
		$('#ensureEdit').off('click').on('click',function(){
			if($('#edit').is(':visible')){
				this.disabled = true;
				updateMenuAction(this);
			}
		});
		
		//复制
		$('#menus').on('click','tr .copy',function(){
			var menu=$(this).closest('tr').data();
			$('#name').val(menu.name);
			$('#code').val(menu.code);
			$('#role_type').combotree('loadData',treeData).combotree('setValue',menu.roleid);
			var linkgoal=menu.linkgoal;
			switch(linkgoal){
				case 'Click':
					linkgoal=1;
					break;
				case 'Iframe':
					linkgoal=2;
					break;
				case 'Open':
					linkgoal=3;
					break;
				case 'href':
					linkgoal=4;
					break;
				default:
					linkgoal=0;
					break;
			}
			$('#linkgoal').val(linkgoal);
			$('#uri').val(menu.uri);
			$('#isvalid')[0].checked=false;
			if(menu.isvalid==1){
				$('#isvalid')[0].checked=true;
			}
			$('#order_by').val(menu.orderBy);
			$('#info').val(menu.info);
			$('#param').val(menu.param);
			$('#group').val(menu.grouping);
		});
		$('#menus').on('click','tr .delete',function(){
			var menu=$(this).closest('tr').data();
			menuData.id=menu.id;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteMenu();
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
	
	//获取树结构数据
	function getTreeData(){
		$.post(URL.getTreeDataUrl,{},function(result){
			if(result.code=='000000'){
				treeData = result.data;
			}
		});
	}
	
	//树形表结构
	function queryData(pageNo){
		var index = layer.load(2, {
			shade: [0.3,'#fff'] //0.1透明度的白色背景
		});
		var params={
				pageNo:pageNo,
				pageSize:100
		};
		var url=URL.pageUrl;
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
//				console.log(data);
				var list=data.list;
				if(!list){
					layer.msg('暂无数据.');
					return;
				}
				var tbody=$('#menus').empty();
//				//根据code对list进行排序
//				list.sort(function(a,b){
//					return a.code.localeCompare(b.code);	
//				});
				
				for(var i=0;i<list.length;i++){
					var menu=list[i];
					var img = '';
					if(menu.imgType){
						img = '&'+menu.imgType;
					}
					if(!menu.roleid){//判断是否是一级节点
						var tr=$('<tr id="'+menu.id+'" pid=""><td style="width: 200px;"><span controller="true"><i class="iconfont">'+img+'</i>'+menu.name+'</span></td><td>'+
								menu.code+'</td><td>'+menu.roleType+'</td><td>'+menu.linkgoal+'</td><td>'+menu.uri+'</td><td><input type="checkbox" disabled id="list_isvalid'+i+
								'" ></td><td>'+menu.orderBy+'</td><td><span class="update"><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
								'</span><span class="copy"> <a data-toggle="modal" data-target="#add"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="复制">&#xe61c;</i></a>'+'</span><span class="delete"><a data-toggle="modal" data-target="#dele">'+
								'<i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>'
						);
					}else{
						var tr=$('<tr id="'+menu.id+'" pid="'+menu.roleid+'"><td style="width: 200px;"><span controller="true"><i class="iconfont">'+img+'</i>'+menu.name+'</span></td><td>'+
								menu.code+'</td><td>'+menu.roleType+'</td><td>'+menu.linkgoal+'</td><td>'+menu.uri+'</td><td><input type="checkbox" disabled id="list_isvalid'+i+
								'" ></td><td>'+menu.orderBy+'</td><td><span class="update"><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
								'</span><span class="copy"> <a data-toggle="modal" data-target="#add"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="复制">&#xe61c;</i></a>'+'</span><span class="delete"><a data-toggle="modal" data-target="#dele">'+
								'<i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>'
						);
					}
					tbody.append(tr);
					$('#list_isvalid'+i)[0].checked=false;
					if(menu.isvalid==1){
						$('#list_isvalid'+i)[0].checked=true;
					}
					tr.data(menu);
					menuData.total=list.length;
				}
				
				  //树形表格treeTable插件调用
			    var option = {
			        theme: 'vsStyle',
			        expandLevel: 3,
			        column: 0,
			        beforeExpand: function ($treeTable, id) {
			            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
			            if ($('.' + id, $treeTable).length) {
			                return;
			            }
//			            //这里的html可以是ajax请求
//			            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
//			            	+ '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';
//			            $treeTable.addChilds(html);
			        },
			        onSelect: function ($treeTable, id) {
			            window.console && console.log('onSelect:' + id);
			        }
			    };
			    $('#table_menu').treeTable(option);
				
			  
				
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		
		
		layer.close(index);
	}

	
	function initAddMenu(){
		$('#name').val('');
		$('#code').val('');
		$('#role_type').combotree('loadData',treeData);
		$('#linkgoal').val(1);
		$('#uri').val('');
		$('#isvalid').prop('checked',true);
		$("#role_type").combotree('setValue',"业务管理");
		$('#info').val('');
		$('#order_by').val(parseInt(menuData.total)+1);
		$('#param').val('');
		$('#group').val(0);
		$('#menu_icons > ul > li').removeClass('select');
		$("#iconid").text('');
		
	}
	
	function getAddMenu(){
		var name=$('#name').val();name=$.trim(name);
		var code=$('#code').val();code=$.trim(code);
		var roleid = $('#role_type').val();roleid=$.trim(roleid);
		var role_type=$('#_easyui_textbox_input1').val();role_type=$.trim(role_type);
		var linkgoal=$('#linkgoal option:selected').text();
		var uri=$('#uri').val();uri=$.trim(uri);
		var isvalid = 0;
		if($('#isvalid')[0].checked){
			isvalid = 1;
		}
		var order_by=$('#order_by').val();order_by=$.trim(order_by);
		var info=$('#info').val();info=$.trim(info);
		var param=$('#param').val();param=$.trim(param);
		var group=$('#group option:selected').text();
		var imgType = $('#iconid').attr('img_type');
		return {
			name:name,
			code:code,
			roleid:roleid,
			roleType:role_type,
			linkgoal:linkgoal,
			uri:uri,
			imgType:imgType,
			isvalid:isvalid,
			orderBy:order_by,
			info:info,
			param:param,
			grouping:group,
			currUId:userid
		};
	}
	
	
	function addMenuAction(_this){
		var url=URL.addBtnUrl;
		var params=getAddMenu();
//		console.log(params);
		if(validate(params)){
			var index = layer.load(2, {
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			$.post(url,params,function(result){
				if(result.code=='000000'){
					_this.disabled=false;
					win.location.reload(true);
				}else{
					layer.msg(result.error,{icon:5});
					_this.disabled=false;
				}
			});
			layer.close(index);
		}else{
			_this.disabled=false;
		}
	}
	
	//检测
	function validate(params){
		if(!params.name){
			layer.msg('模块名称不能为空！',{icon:5});
			return false;
		}
		if(!params.code){
			layer.msg('模块编号不能为空！',{icon:5});
			return false;
		}
		if(!params.roleType){
			layer.msg('上级菜单不能为空！',{icon:5});
			return false;
		}
		if(params.grouping=='==请选择=='){
			layer.msg('分组行不能为空！',{icon:5});
			return false;
		}
		return true;
	}
	
	function getUpdateMenu(){
		var name=$('#update_name').val();name=$.trim(name);
		var code=$('#update_code').val();code=$.trim(code);
		var roleid = $('#update_role').val();roleid=$.trim(roleid);
		var role_type=$('#_easyui_textbox_input2').val();role_type=$.trim(role_type);
		var linkgoal=$('#update_linkgoal option:selected').text();
		var uri=$('#update_uri').val();uri=$.trim(uri);
		var isvalid = 0;
		if($('#update_isvalid')[0].checked){
			isvalid = 1;
		}
		
		var order_by=$('#update_orderBy').val();order_by=$.trim(order_by);
		var info=$('#update_info').val();info=$.trim(info);
		var param=$('#update_param').val();param=$.trim(param);
		var group=$('#update_grouping option:selected').text();
		var imgType = $('#edit_iconid').attr('img_type');
		return {
			name:name,
			code:code,
			roleType:role_type,
			linkgoal:linkgoal,
			uri:uri,
			imgType:imgType,
			isvalid:isvalid,
			roleid:roleid,
			orderBy:order_by,
			info:info,
			param:param,
			grouping:group,
			currUId:userid,
			id:menuData.id
		};
	}
	
	
	
	function updateMenuAction(_this){
		var url=URL.editUrl;
		var params=getUpdateMenu();
		if(validate(params)){
			var index = layer.load(2, {
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			$.post(url,params,function(result){
				if(result.code=='000000'){
					_this.disabled=false;
					win.location.reload(true);
				}else{
					layer.msg(result.error,{icon:5});
					_this.disabled=false;
				}
			});
			layer.close(index);
		}else{
			_this.disabled=false;
		}
	}
	
	//删除
	function deleteMenu(){
		var url=URL.deleteUrl;
		var param={id:menuData.id};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	
//
//		function getValue(){ 
//			var val = $('#role_type').combotree('getValue'); 
//			var text = $('#role_type').combotree('getText'); 
//			alert("val="+val+",text="+text); 
//		} 
//		function setValue(){ 
//			$('#cc').combotree('setValue', '这是设定的值'); 
//		} 
//		function disable(){ 
//			$('#cc').combotree('disable'); 
//		} 
//		function enable(){ 
//		$('#cc').combotree('enable'); 
//		}

	
})(jQuery, window);