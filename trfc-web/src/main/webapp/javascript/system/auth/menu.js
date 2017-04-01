;(function($, win){
	
	var URL = {
			addBtnUrl:"/trfc/system/auth/menu/add",
			pageUrl:"/trfc/system/auth/menu/page",
			editUrl:"/trfc/system/auth/menu/edit",
			deleteUrl:"/trfc/system/auth/menu/delete",
	};
	
	
	init();
	function init(){
		bindEvent();
		queryData(1);
	}
	
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	
	var menuData={};
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#initAdd').off('click').on('click',function(){
			initAddMenu();
		});
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
			$('#update_role').val(menu.roleType);
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
			$('#update_orderBy').val(menu.orderBy);
			$('#update_info').val(menu.info);
			$('#update_param').val(menu.param);
			$('#update_grouping').val(menu.grouping);
			menuData.id=menu.id;
		});
		$('#ensureEdit').off('click').on('click',function(){
			if($('#edit').is(':visible')){
				this.disabled = true;
				updateMenuAction(this);
			}
		});
		$('#menus').on('click','tr .copy',function(){
			var menu=$(this).closest('tr').data();
			$('#name').val(menu.name);
			$('#code').val('');
			$('#code').focus();
			$('#_easyui_textbox_input2').val(menu.roleType);
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
				//根据code对list进行排序
				list.sort(function(a,b){
					return a.code.localeCompare(b.code);	
				});
				
				for(var i=0;i<list.length;i++){
					var menu=list[i];
					if(!menu.roleType){//判断是否是一级节点
						var tr=$('<tr id="'+menu.name+'"><td>'+(i+1)+'</td><td style="width: 200px;"><span controller="true">'+menu.name+'</span></td><td>'+
								menu.code+'</td><td>'+menu.roleType+'</td><td>'+menu.linkgoal+'</td><td>'+menu.uri+'</td><td><input type="checkbox" disabled id="list_isvalid'+i+
								'" ></td><td>'+menu.orderBy+'</td><td><span class="update"><a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>'+
								'</span><span class="copy"> <a data-toggle="modal" data-target="#add"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="复制">&#xe61c;</i></a>'+'</span><span class="delete"><a data-toggle="modal" data-target="#dele">'+
								'<i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>'
						);
					}else{
						var tr=$('<tr id="'+menu.name+'" pid="'+menu.roleType+'"><td>'+(i+1)+'</td><td style="width: 200px;"><span controller="true">'+menu.name+'</span></td><td>'+
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
			        expandLevel: 5,
			        column: 1,
			        beforeExpand: function ($treeTable, id) {
			            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
			            if ($('.' + id, $treeTable).length) {
			                return;
			            }
			            //这里的html可以是ajax请求
			            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
			                    + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';
			            $treeTable.addChilds(html);
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
		$('#role_type').val('');
		$('#linkgoal').val(1);
		$('#uri').val('');
		$('#isvalid').prop('checked',true);
		$('#info').val('');
		$('#order_by').val(parseInt(menuData.total)+1);
		$('#param').val('');
		$('#group').val(0);
		
	
		
	}
	
	function getAddMenu(){
		var name=$('#name').val();name=$.trim(name);
		var code=$('#code').val();code=$.trim(code);
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
		return {
			name:name,
			code:code,
			roleType:role_type,
			linkgoal:linkgoal,
			uri:uri,
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
					win.location.reload();
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
		return {
			name:name,
			code:code,
			roleType:role_type,
			linkgoal:linkgoal,
			uri:uri,
			isvalid:isvalid,
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
					win.location.reload();
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
	
	
	//下拉树本地数据
	var data=[{
		"id":1,
		"text":"天瑞信科厂区智能管理平台",
		"children":[{
			"id":11,
			"text":"业务管理",
			"children":[{
				"id":111,
				"text":"采购管理",
				"children":[{
					"id":1111,
					"text":"采购申请单"
				},{
					"id":1112,
					"text":"到货通知单"
				},{
					"id":1113,
					"text":"退货通知单"
				}]
			},{
				"id":112,
				"text":"销售管理",
				"children":[{
					"id":1121,
					"text":"销售申请单"
				},{
					"id":1122,
					"text":"提货通知单"
				}]
			},{
				"id":113,
				"text":"卡务管理",
				"children":[{
					"id":1131,
					"text":"IC卡注册"
				},{
					"id":1132,
					"text":"补卡业务"
				}]
			},{
				"id":114,
				"text":"质控管理",
				"children":[{
					"id":1141,
					"text":"销售批号维护"
				},{
					"id":1142,
					"text":"销售化验报告"
				},{
					"id":1143,
					"text":"采购采样管理"
				},{
					"id":1144,
					"text":"采购化验报告"
				}]
			},{
				"id":115,
				"text":"财务管理",
				"children":[{
					"id":1151,
					"text":"客户期初"
				},{
					"id":1152,
					"text":"销售收款"
				},{
					"id":1153,
					"text":"客户退补"
				},{
					"id":1154,
					"text":"收款台账"
				},{
					"id":1154,
					"text":"销售明细"
				}]
			},{
				"id":116,
				"text":"磅单维护",
				"children":[{
					"id":1161,
					"text":"采购磅单维护"
				},{
					"id":1162,
					"text":"销售榜单维护"
				}]
			},{
				"id":117,
				"text":"厂区物流",
				"children":[{
					"id":1171,
					"text":"门禁记录"
				}]
			}]
		},{
			"id":12,
			"text":"基础档案",
			"children":[{
				"id":121,
				"text":"NC档案",
				"children":[{
					"id":1211,
					"text":"客户管理"
				},{
					"id":1212,
					"text":"仓库管理"
				},{
					"id":1213,
					"text":"供应商管理"
				},{
					"id":1214,
					"text":"物料管理"
				}]
			},{
				"id":122,
				"text":"计量档案",
				"children":[{
					"id":1221,
					"text":"运输单位"
				},{
					"id":1222,
					"text":"车辆管理"
				},{
					"id":1223,
					"text":"司机管理"
				},{
					"id":1224,
					"text":"矿口管理"
				},{
					"id":1225,
					"text":"堆场管理"
				}]
			},{
				"id":123,
				"text":"质检档案",
				"children":[{
					"id":1231,
					"text":"物料方案"
				},{
					"id":1232,
					"text":"质检方案"
				},{
					"id":1233,
					"text":"质检项目"
				},{
					"id":1234,
					"text":"供应商标准方案"
				},{
					"id":1235,
					"text":"合格证维护"
				}]
			},{
				"id":124,
				"text":"其他档案",
				"children":[{
					"id":1241,
					"text":"其他客户"
				},{
					"id":1242,
					"text":"其他车辆"
				},{
					"id":1243,
					"text":"其他物料"
				},{
					"id":1244,
					"text":"其他司机"
				},{
					"id":1245,
					"text":"其他供应商"
				}]
			}]
		},{
			"id":13,
			"text":"系统设置",
			"children":[{
				"id":131,
				"text":"系统权限",
				"children":[{
					"id":1311,
					"text":"菜单管理"
				},{
					"id":1312,
					"text":"用户管理"
				}]
			},{
				"id":132,
				"text":"系统业务",
				"children":[{
					"id":1321,
					"text":"自定义编号"
				},{
					"id":1322,
					"text":"辅助资料"
				}]
			}]
		}]
	}];
	
	$(function(){ 
		$('#role_type').combotree('loadData', data);
		}); 
	
		function getValue(){ 
			var val = $('#cc').combotree('getValue'); 
			var text = $('#cc').combotree('getText'); 
			alert("val="+val+",text="+text); 
		} 
		function setValue(){ 
			$('#cc').combotree('setValue', '这是设定的值'); 
		} 
		function disable(){ 
			$('#cc').combotree('disable'); 
		} 
		function enable(){ 
		$('#cc').combotree('enable'); 
		}

	
})(jQuery, window);