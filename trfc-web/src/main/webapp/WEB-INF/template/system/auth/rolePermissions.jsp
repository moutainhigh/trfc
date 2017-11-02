<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色权限</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<link href="${staticBasePath}/css/easyui.css" rel="stylesheet">
<link href="${staticBasePath}/css/icon.css" rel="stylesheet">
<script type="text/javascript"
	src="${staticBasePath }/js/jquery.easyui.min.js">
	
</script>
<style type="text/css">
.sys_collap .typelist li.active {
	background: #434e8d;
	color: #ffffff;
}
</style>
</head>
<body>
	<div class="it_admin">
		<!-- 引用公共left部分 -->
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--采购申请单begin-->
				<div class="intel_tabcont">
					<div class="juese_qx">
						<div class="juese_user">
							<h5>角色</h5>
							<div class="sys_collap">
								<ul class="typelist ">
									<a href="#jsqx_juese" data-toggle="collapse"
										class="menu_collap_tit"> <span><i class="iconfont">&#xe6c8;</i></span>
										<label><i class="iconfont">&#xe60a;</i>${orgname }</label>
									</a>
									<div class="in" id="roleList"></div>
								</ul>
							</div>
						</div>
						<div class="juese_opera">
							<div class="juese_title">
								<h5>角色授权 - 门岗(018)</h5>
							</div>
							<!--角色授权切换标题-->
							<div class="juese_tab">
								<ul>
									<li class="select">角色成员</li>
									<li>模块权限</li>
									<!-- <li>数据权限</li> -->
								</ul>
							</div>
							<!--角色授权切换标题-->

							<!--tab切换的内容-->
							<div class="juese_tabbox">
								<!--角色成员-->
								<div class="juese_tabcont">
									<div class="intel_opera">
										<!--  --><div class="intel_operasolo refresh">
											<i class="iconfont colorlv">&#xe61b;</i>
											<h5>刷新</h5>
										</div>
										<div class="intel_operasolo" id="addUserToRole">
											<a> <i
												class="iconfont coloradd" style="font-weight: bold">&#xe621;</i>
												<h5>新增成员</h5>
											</a>
										</div>
										<div class="intel_operasolo" id="deleteUserToRole">
											<a data-toggle="modal" data-target="#dele"> <i
												class="iconfont colorred" style="font-weight: bold">&#xe62b;</i>
												<h5>删除成员</h5>
											</a>
										</div>
									</div>
									<div class="juese_qxsearch">
										<div class="js_solo">
											<label>查询条件：</label> <select id="qtp" class="form-control">
												<option value="bh">编号</option>
												<option value="zh">账户</option>
												<!-- <option value="sf">身份</option> -->
											</select>
										</div>
										<div class="js_solo">
											<label>关键字：</label> <input id="keyword" type="text">
										</div>
										<div class="js_solo">
											<button class="btn btnblue" id="searchUser">搜索</button>
										</div>
									</div>
									<div class="intel_table">
										<!--角色成员表格begin-->
										<table class="table table-hover">
											<thead>
												<tr>
													<th></th>
													<th></th>
													<th>编号</th>
													<th>用户名称</th>
													<th>身份</th>
													<th>公司</th>
													<th>说明</th>
												</tr>
											</thead>
											<tbody id="userBody">
											</tbody>
										</table>
										<!--角色成员表格end-->
									</div>
								</div>
								<!--角色成员end-->

								<!--模块权限begin-->
								<div class="juese_tabcont hide">
									<div class="intel_opera">
										<div class="intel_operasolo refresh">
											<i class="iconfont colorlv">&#xe61b;</i>
											<h5>刷新</h5>
										</div>
										<div id="resetMenuBtn" class="intel_operasolo">
											<a> <i
												class="iconfont coloradd" style="font-weight: bold">&#xe623;</i>
												<h5>重置</h5>
											</a>
										</div>
										<div id="authorizeBtn" class="intel_operasolo">
											<a> <i
												class="iconfont" style="font-weight: bold">&#xe668;</i>
												<h5>授权</h5>
											</a>
										</div>
									</div>
									<div class="intel_table">
										<table id="juese_module" class="table table-bordered"
											data-options="">
											<thead>
												<tr>
													<th style="width: 20px;">序号</th>
													<th>模块(菜单)名称</th>
													<th>模块编码</th>
													<th>排序</th>
													<th>说明</th>
												</tr>
											</thead>
											<tbody id="menubody">
											</tbody>
										</table>
										<!--分页效果开始-->
										<div class="row">
											<div class="col-md-12">
												<div class="page_middle">
													<div class="page_date">
														<label>数据共：</label><i class="colorred">100</i><label>条</label>
													</div>
													<div class="page_date">
														<label>跳到第：</label> <input type="text"> <label>页</label>
														<button class="btn btn-default">确定</button>
													</div>
													<div class="page_btn">
														<ul class="pagination">
															<li><a href="#">&laquo;上一页</a></li>
															<li><a href="#">1</a></li>
															<li><a href="#">2</a></li>
															<li><a href="#">3</a></li>
															<li><a href="#">...</a></li>
															<li><a href="#">4</a></li>
															<li><a href="#">5</a></li>
															<li><a href="#">下一页&raquo;</a></li>
														</ul>
													</div>
												</div>

											</div>
										</div>
										<!--分页效果结束-->
									</div>
								</div>
								<!--模块权限end-->
								<div class="juese_tabcont hide">
									<div class="intel_opera">
										<div class="intel_operasolo">
											<i class="iconfont colorlv">&#xe61b;</i>
											<h5>刷新</h5>
										</div>
										<div class="intel_operasolo">
											<a data-toggle="modal" data-target="#module_reset"> <i
												class="iconfont coloradd" style="font-weight: bold">&#xe623;</i>
												<h5>重置</h5>
											</a>
										</div>
										<div class="intel_operasolo">
											<a data-toggle="modal" data-target="#module_sq"> <i
												class="iconfont" style="font-weight: bold">&#xe668;</i>
												<h5>授权</h5>
											</a>
										</div>
									</div>
									<div class="juese_qxsearch">
										<div class="js_solo">
											<label>授权项目列表：</label> <select class="form-control">
												<option>组织机构</option>
												<option>客户</option>
												<option>供应商</option>
											</select>
										</div>
									</div>
									<div class="juese_qxdata">
										<ul>
											<li><input type="checkbox"><i class="iconfont">&#xe60a;</i>
												北京科普斯特自动化仪表有限公司</li>
											<li><input type="checkbox"><i class="iconfont">&#xe60a;</i>
												北京科普斯特自动化仪表有限公司</li>
											<li><input type="checkbox"><i class="iconfont">&#xe60a;</i>
												北京科普斯特自动化仪表有限公司</li>
											<li><input type="checkbox"><i class="iconfont">&#xe60a;</i>
												北京科普斯特自动化仪表有限公司</li>
										</ul>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>

					</div>

				</div>
			</div>
		</div>
	</div>
	<!--角色用户新增begin-->
	<div class="modal fade" id="addUserView" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 760px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>添加用户到角色</h5>
					</div>
				</div>
				<div class="modal-body" style="max-height:500px;">
					<div class="jsqx_select">
						<div class="juese_qxsearch">
							<div class="js_solo">
								<label>关键字：</label> <input id="key" type="text">
							</div>
							<div class="js_solo">
								<button class="btn btnblue" id="searchKeyUser">搜索</button>
							</div>
						</div>
						<div class="juese_altuser">
							<label>成员</label> <span class="qx_seltall "><input
								type="checkbox" name="jsqx_seltall">全选</span> <span
								class="qx_cancelall "><input type="checkbox"
								name="jsqx_cancelall">反选</span>
						</div>

						<div class="juese_altuser_list">
							<ul>
							</ul>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="addUserBtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--角色用户新增end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/system/auth/rolePermissions.js"></script>
	<script type="text/javascript">
		// 模块权限表格每行选中背景变白
		var tabletr = $('.intel_table table tr');
		tabletr.on("click", function() {
			$(this).addClass("select").siblings().removeClass("select");
		});

		// 顶部tab切换菜单
		var $tab_li = $('.intel_menu li');
		$tab_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = $tab_li.index(this);
			$('.intel_tabbox > .intel_tabcont').eq(index).show().siblings()
					.hide();
		});
		// 角色权限的四个权限tab切换菜单
		var js_li = $('.juese_tab ul li');
		js_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = js_li.index(this);
			$('.juese_tabbox > .juese_tabcont').eq(index).show().siblings()
					.hide();
		});

		// 首页底部的tab切换菜单
		var ind_li = $('#ind_tab ul li');
		ind_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_li = cg_li.index(this);
			$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show()
					.siblings().hide();
		});

		// 弹出信息的tab切换菜单
		var alt_li = $('#alt_tab ul li');
		alt_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_alt = alt_li.index(this);
			$('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show()
					.siblings().hide();
		});
	</script>
</body>
</html>