<%@ page language="java" pageEncoding="UTF-8"%>
<div class="left ">
	<div class="user" userid=${requestScope.userId }>
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle"> <label>${requestScope.userName }</label>
		</a>
		<ul class="dropdown-menu">
			<li><a data-toggle="modal" data-target="#account"
				onclick="editPwd();"><i class="iconfont">&#xe60e;</i>修改密码</a></li>
			<!-- <li class="divider"></li>
            <li><a data-toggle="modal" data-target="#password"><i class="iconfont">&#xe60d;</i> 设置</a></li> -->
		</ul>
	</div>
	<div class="menu">
		<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
	</div>
	<div class="menu_collap">
		<ul class="typelist " id="menulist">

		</ul>
	</div>
</div>
<div class="leftmini hide">
	<div class="user">
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle">
		</a>
	</div>
	<div class="menu2">
		<i class="iconfont">&#xe635;</i>
	</div>
	<ul class="typelist" id="menu_imgs">

	</ul>
</div>
<script>
	function editPwd() {
		layer.open({
			title : '修改密码',
			type : 1,
			skin : 'layui-layer-demo', //样式类名
			anim : 2,
			area : [ '500px', '300px' ],
			shadeClose : true, //开启遮罩关闭
			content : '<div style="padding: 20px;">'
				   + '<form class="layui-form" action>'
				        + '<div class="layui-form-item">'
				            + '<label class="layui-form-label">旧密码</label>'
				            + '<div class="layui-input-inline">'
				                + '<input type="password" name="oldPwd" required=""'
				                    + 'lay-verify="required" placeholder="请输入密码" autocomplete="off"'
				                    + 'class="layui-input">'
				            + '</div>'
				        + '</div>'
				        + '<div class="layui-form-item">'
				            + '<label class="layui-form-label">新密码</label>'
				            + '<div class="layui-input-inline">'
				                + '<input type="password" name="newPwd" required=""'
				                    + 'lay-verify="required" placeholder="请输入密码" autocomplete="off"'
				                    + 'class="layui-input">'
				            + '</div>'
				        + '</div>'
				        + '<div class="layui-form-item">'
				            + '<label class="layui-form-label">确认密码</label>'
				            + '<div class="layui-input-inline">'
				                + '<input type="password" name="_newPwd" required=""'
				                    + 'lay-verify="required" placeholder="请输入密码" autocomplete="off"'
				                    + 'class="layui-input">'
				            + '</div>'
				        + '</div>'
				        + '<div class="layui-form-item">'
				            + '<div class="layui-input-block">'
				                + '<input class="layui-btn" lay-submit="" lay-filter="updatePwd" type="button" value="立即提交"/>'
				                + '<button type="reset" class="layui-btn layui-btn-primary">重置</button>'
				            + '</div>'
				        + '</div>'
				    + '</form>'
				+ '</div>'
		});
	}
	layui.use('form', function(){
        var form = layui.form;
        form.on('submit(updatePwd)', function(data){
            //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
            //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
            //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            if (data.field.newPwd != data.field._newPwd) {
            	layer.msg('两次密码并不一致', {anim: 6});
            	return false;
            }
            $.post('/trfc/system/auth/user/updatePwd', {oldPswd: md5(data.field.oldPwd), newPswd: md5(data.field.newPwd)}, function(result) {
				if (result.code=='000000') {
					layer.closeAll();
					layer.msg('密码修改成功', {icon:1});
				} else {
				    layer.msg(result.error,{icon:5});
				}
            });
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
          });
      });
</script>