$(function() {

	initMenu();

	function initMenu() {
		var url = '/trfc/system/auth/menu/page';
		var params={
				pageNo:1,
				pageSize:100
		};
		$.post(url,params,function(result) {
			
			//展示菜单
			showMenu(result.data.list);	
			
			var menu_a = $('#menulist > a');
			
			//添加 加减号
			collapse_left(menu_a);
			$(".tool").tooltip();
		});
	}
	//菜单 加减号
	function collapse_left(options){
		options.on("click", function () {
			if ($(this).hasClass("collapsed")) {
				$(this).find("span i").html("&#xe604;")
			}
			else {
				$(this).find("span i").html("&#xe61f;")
			}
		});
	}


	function showMenu(list){
		//获取地址 并截取中间部分
		var href = window.location.href;
		href = href.split('/trfc/')[1];
		urlstr = href.substring(0,href.lastIndexOf('/'));

		//获取深度为1的 菜单
		var menu1 = $.grep(list,function(value) {
			return value.deep == 1;
		});

		var menuBody = $('#menulist').empty();
		var menuImg = $('#menu_imgs').empty();
		//遍历一级菜单
		for(var i = 0; i<menu1.length; i++) {
			//追加一级菜单
			menuBody.append('<a href="#ityewu'+i+'" data-toggle="collapse" class="menu_collap_tit"><label>'
					+menu1[i].name+'</label> <span><i class="iconfont">&#xe604;</i></span></a>');
			var $div_li = $('<div class="in" id="ityewu'+i+'">').appendTo(menuBody);
			//获取二级菜单
			var menu2 = $.grep(list,function(value) {
				return value.roleid == menu1[i].id;
			});
			//遍历二级菜单
			for(var j = 0;j<menu2.length;j++) {
				//通过筛选,获取三级菜单
				var menu3 = $.grep(list,function(value) {
					return value.roleid == menu2[j].id;
				});
				
				//把数据字符 拼接成图标 unicode码
				var img = menu2[j].imgType ? '&'+menu2[j].imgType : '';
				//如果有下级菜单 默认跳转到一个链接
				var url = menu3.length>0?'href='+menu3[0].uri:'';

				//菜单选中效果
				var li_selected = '';
				
				//实现左侧菜单 选中效果
				if(menu3.length>0){
					for(var f = 0;f<menu3.length;f++){
						var hh = menu3[f].uri;
						if(hh){
							hh = hh.split('/trfc/')[1];
							hh = hh.substring(0,hh.lastIndexOf('/'));
							if(hh == urlstr){
								li_selected = 'class="active"';
							}
						}
					}

				}
				
				//加载三级菜单
				if(li_selected){
					var tab_ul = $('.intel_menu').empty();
					for(var f = 0;f<menu3.length;f++){
						var hh = menu3[f].uri;
						var href_select = '';
						if(hh){
							href_select = 'href='+hh;
							hh = hh.split('/trfc/')[1];
							hh = hh.substring(0,hh.lastIndexOf('/'));
						}
						// 实现三级菜单 选中效果
						if(hh == urlstr){
							tab_ul.append('<li class="select"><a '+href_select+'>'
									+menu3[f].name+'</a></li>');
						}else{
							tab_ul.append('<li><a '+href_select+'>'
									+menu3[f].name+'</a></li>');
						}
					}
				}
				
				//追加菜单信息
				$div_li.append('<li '+li_selected+'><a '+url+'> <i class="iconfont">'
						+img+'</i> <span>'+menu2[j].name+'</span></a></li>');
				
				menuImg.append(' <li data-toggle="tooltip" class="tool" data-placement="right" title="'
						+menu2[j].name+'"><a '+url+'><i class="iconfont">'+img+'</i></a></li>');
			}
		}

	}



});