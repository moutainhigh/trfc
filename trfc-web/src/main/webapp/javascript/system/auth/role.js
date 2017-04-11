;(function($, win){
	
	var URL = {
			addBtnUrl:"/trfc/system/auth/role/add",
			pageUrl:"/trfc/system/auth/role/page",
			editUrl:"/trfc/system/auth/role/edit",
			deleteUrl:"/trfc/system/auth/role/delete",
			getCodeUrl:"/trfc/system/base/code/getCode",
			updateCodeUrl:"/trfc/system/base/code/updateCode",
	};
	
	
	init();
	function init(){
		bindEvent();
		queryData(1);
	}
	
	// 获取当前用户id
	var userid=$('.user').attr('userid');
	
	var roleData={};
	
	function bindEvent(){
		$('#refreshBtn').off('click').on('click',function(){
			queryData(1);
		});
		$('#initAdd').off('click').on('click',function(){
			initAddRole();
		});
		$('#ensureAdd').off('click').on('click',function(){
			if($('#add').is(':visible')){
				this.disabled = true;
				addRoleAction(this);
			}
		});
		$('#roles').off('click').on('click','tr .update',function(){
			var role=$(this).closest('tr').data();
			roleData.id=role.id;
			$('#update_code').val(role.code);
			$('#update_name').val(role.name);
			$('#update_info').val(role.info);
			var roleType=role.roleType;
			switch(roleType){
				case '应用角色':
					roleType=1;
					break;
				case '业务角色':
					roleType=2;
					break;
				case '系统角色':
					roleType=3;
					break;
				default:
					roleType=0;
					break;
			}
			$('#update_role').val(roleType);
			$('#update_isvalid')[0].checked=false;
			if(role.isvalid==1){
				$('#update_isvalid')[0].checked=true;
			}
			$('#update_edit')[0].checked=false;
			if(role.allowEdit==1){
				$('#update_edit')[0].checked=true;
			}
			$('#update_del')[0].checked=false;
			if(role.allowDel==1){
				$('#update_del')[0].checked=true;
			}
		});  
		$('#ensureEdit').off('click').on('click',function(){
			if($('#edit').is(':visible')){
				this.disabled = true;
				updateRoleAction(this);
			}
		});
		$('#roles').on('click','tr .delete',function(){
			var role=$(this).closest('tr').data();
			roleData.id=role.id;
			var bn=layer.open({
		        content: '您确定要删除吗？',
		        area: '600px',
		        closeBtn:1,
		        shadeClose:true,
		        btn: ['确定', '取消'],
		        yes: function(index, layero){
		            //按钮【确定】的回调
					deleteRole();
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
		var url=URL.pageUrl;
		var rolename=$('#role_list').html().substring(7);
		var params={
			pageNo:pageNo,
			pageSize:100,
			rolename:rolename
		};
		$.post(url,params,function(result){
			if(result.code=='000000'){
				var data=result.data;
				var list=data.list;
//				console.log(data);
				if(!list){
					layer.msg('暂无数据.');
				}
				var tbody=$('#roles').empty();
				for(var i=0;i<list.length;i++){
					var role=list[i];
					var roleType=role.roleType;
					switch(roleType){
						case '系统角色':
							roleType='001';
							break;
						case '业务角色':
							roleType='002';
							break;
						case '应用角色':
							roleType='003';
							break;
						default:
							roleType='';
							break;
					}
					var tr=$('<tr><td>'+(i+1)+'</td><td>'+role.code+'</td><td>'+role.name+'</td><td>'+roleType+'</td><td><input type="checkbox" disabled id="isvalid'+i+'">'+
							'</td><td><input type="checkbox" disabled id="allow_edit'+i+'">'+'</td><td><input type="checkbox" disabled id="allow_dele'+i+'">'+'</td><td>'+role.info+'</td><td>'+
							'<span class="update"><a data-toggle="modal" data-target="#edit"><i class="iconfont" style="margin: 0 5px;" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a></span>'+
							'<span class="delete"><a data-toggle="modal" data-target="#dele"><i class="iconfont" style="margin: 0 5px;" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>'+'</span></td></tr>'
					);
					tbody.append(tr);
					$('#isvalid'+i)[0].checked=false;
					if(role.isvalid==1){
						$('#isvalid'+i)[0].checked=true;
					}
					$('#allow_edit'+i)[0].checked=false;
					if(role.allowEdit==1){
						$('#allow_edit'+i)[0].checked=true;
					}
					$('#allow_dele'+i)[0].checked=false;
					if(role.allowDel==1){
						$('#allow_dele'+i)[0].checked=true;
					}
					tr.data(role);
					roleData.total=data.total;
				}
			}else{
				layer.msg(result.error,{icon:5});	
			}
		});
		
		layer.close(index);
	}
	
	
	function initAddRole(){
		var param = {
				userid:userid,
				code:"JS",
				codeType:true
		};
		$.post(URL.getCodeUrl,param,function(result){
			if('000000'==result.code){
				$('#code').val(result.data);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
		$('#name').val('');
		$('#role_type').val(0);
		$('#isvalid').prop('checked',true);
		$('#allow_edit').prop('checked',true);
		$('#allow_del').prop('checked',true);
		$('#info').val('');
	}
	function updateCode(){
		var param = {
				userid:userid,
				code:"JS",
				codeType:true
		};
		$.post(URL.updateCodeUrl,param,function(result){
			if('000000'==result.code){
				window.location.reload();
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	function getAddRole(){
		var code=$('#code').val();code=$.trim(code);
		var name=$('#name').val();name=$.trim(name);
		var roleType=$('#role_type option:selected').text();
		var info=$('#info').val();info=$.trim(info);
		var rolename=$('#role_list').html().substring(7);
		var isvalid = 0;
		if($('#isvalid')[0].checked){
			isvalid = 1;
		}
		var allow_edit = 0;
		if($('#allow_edit')[0].checked){
			allow_edit = 1;
		}
		var allow_del = 0;
		if($('#allow_del')[0].checked){
			allow_del = 1;
		}
		return {
			code:code,
			name:name,
			roleType:roleType,
			rolename:rolename,
			info:info,
			isvalid:isvalid,
			allowEdit:allow_edit,
			allowDel:allow_del,
			currUId:userid
		};
	}
	
	
	
	function addRoleAction(_this){
		var url=URL.addBtnUrl;
		var params=getAddRole();
//		console.log(params);
		if(validate(params)){
			var index = layer.load(2, {
				shade: [0.3,'#fff'] //0.1透明度的白色背景
			});
			$.post(url,params,function(result){
				if(result.code=='000000'){
					updateCode();
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
			layer.msg('角色名称不能为空!',{icon:5});
			return false;
		}
		if(params.roleType=='==请选择=='){
			layer.msg('角色分类不能为空!',{icon:5});
			return false;
		}
		return true;
	}
	
	function getUpdateRole(){
		var code=$('#update_code').val();code=$.trim(code);
		var name=$('#update_name').val();name=$.trim(name);
		var roleType=$('#update_role option:selected').text();
		var info=$('#update_info').val();info=$.trim(info);
		var isvalid = 0;
		if($('#update_isvalid')[0].checked){
			isvalid = 1;
		}
		var allow_edit = 0;
		if($('#update_edit')[0].checked){
			allow_edit = 1;
		}
		var allow_del = 0;
		if($('#update_del')[0].checked){
			allow_del = 1;
		}
		return {
			code:code,
			name:name,
			roleType:roleType,
			info:info,
			isvalid:isvalid,
			allowEdit:allow_edit,
			allowDel:allow_del,
			id:roleData.id,
			currUId:userid
		};
		
	}
	
	
	function updateRoleAction(_this){
		var url=URL.editUrl;
		var params=getUpdateRole();
		if(validate(params)){
			$.post(url,params,function(result){
				if(result.code=='000000'){
					win.location.reload();
				}else{
					layer.msg(result.error,{icon:5});
					_this.disbaled=false;
				}
			});
		}else{
			_this.disbaled=false;
		}
	}
	
	
	function deleteRole(){
		var url=URL.deleteUrl;
		var param={id:roleData.id};
		$.post(url,param,function(result){
			if(result.code=='000000'){
				queryData(1);
			}else{
				layer.msg(result.error,{icon:5});
			}
		});
	}
	
	 // bootstrap-treeview树形菜单插件
    function getTree() {
        // Some logic to retrieve, or generate tree structure
        var tree = [
            {
                text: "天瑞集团",
                showBorder: false,
                icon: "iconfont icon-organization",
                nodes: [
                    {
                        text: "卫辉市天瑞水泥有限公司",
                        icon: "iconfont icon-factory"
                    }
                ]
            }
        ];
        return tree;
    }
	
	
	$('#tree').treeview({
		backColor: '#f5f6fb',
	        showBorder: false,
	        selectable: true,
	        state:{
	            checked: false,
	            disabled: false,
	            expanded: false,
	            selected: false
	        },
	        data: getTree()
	    });
	  
		$('#tree').on('nodeSelected', function(event, data) {
//			console.info(event);
//			console.info(data.nodeId);
//			console.info(data.text);
			$('#role_list').html('角色列表 - '+data.text);
			queryData(1);
			$(".alt_edit_div .input_zuzhialt").val($(this).find("li.node-selected").text());
	});	
	$('#tree').treeview('selectNode', [1, { silent: true } ]);
	
})(jQuery, window);