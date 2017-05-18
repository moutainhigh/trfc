<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无人值守-系统管理-用户管理</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
	label.layui-form-label {
	    float: left;
	    margin: 3px;
	    text-align: right;
	    width: 130px;
	}
	.layui-input-block {
	    margin-left: 55px;
	}
	.layui-form-item {
	    margin: 20px;
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
				<!--页面begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>查询条件：</label> <select class="form-control keySelect">
										<option value="codeLike">用户编号</option>
										<option value="accountLike">登陆账号</option>
										<option value="nameLike">用户名称</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>关键字：</label> <input type="text" class="keyInput"
										mexlength=20 />
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue searchBtn">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo">
							<a class="refreshButton"> <i class="iconfont colorlv">&#xe61b;</i>
								<h5>刷新</h5>
							</a>
						</div>
						<div class="intel_operasolo">
							<a class="addButton"> <i class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>用户编号</th>
									<th>登录账号</th>
									<th>用户名称</th>
									<th>所属组织</th>
									<th>有效</th>
									<th>来源</th>
									<th>登陆次数</th>
									<th>最后登录时间</th>
									<th>说明</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<!--分页效果开始-->
					<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred" id="total">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input type="text" id="jumpPageNo"> <label>页</label>
							<button class="btn btn-default" id="jumpButton">确定</button>
						</div>
						<div class="page_date">
							<label>每页记录：</label> <select id="pageSize" class="form-control">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
							</select>
						</div>
						<div class="page_btn" id="pagination"></div>
					</div>
					<!--分页效果结束-->
				</div>
				<!--tab切换的内容end-->
			</div>
		</div>
		<!--查看详情begin-->
		<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>用户信息详情</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="sys_user">
								<h4>河南杰仁环保工程有限公司(河南杰仁环保工程有限公司)</h4>
							</div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">基本信息</li>
									<li>拥有角色</li>
									<li>拥有权限</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
									<div class="sys_userdt">
										<ul>
											<li><label>编号：</label><span>河南杰仁环保工程有限公司 </span></li>
											<li><label>用户名称：</label><span>河南杰仁环保工程有限公司 </span></li>
										</ul>

									</div>
								</div>
								<div class="cg_tabcont hide">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>采购组织</th>
												<th>物料</th>
												<th>质检方案</th>
												<th>数量</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>卫辉市天瑞水泥有限公司</td>
												<td>粉煤炭</td>
												<td>方案2</td>
												<td>1000</td>
												<td>豫GA1783</td>
											</tr>
											<tr>
												<td>合计</td>
												<td></td>
												<td></td>
												<td></td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="cg_tabcont hide">1</div>
								<!--tab切换的内容end-->
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--查看详情end-->
		<!--新增begin-->
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>用户信息-新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>用户编号：</label> <input type="text" class="formele"
									name="code" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>登录账户：</label> <input type="text" class="formele"
									name="account" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>用户名称：</label> <input type="text" class="formele"
									name="name" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>登录密码：</label> <input type="password"
									class="formele password" name="password" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>所属组织：</label> <span>${orgName}</span>
								<!--  <input type="text" class="formele" maxlength="20"> -->
								<input type="hidden" name="orgId" value="${orgId}"
									class="formele" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>说明：</label> <input type="text" class="formele"
									name="remark" maxlength="20">
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input type="checkbox" class="formele"
									name="isvalid" value="1" checked><span>有效</span> <em
									class="colorred">注：无效用户不能登录。</em>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary submitBtn">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="add_cancel">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--编辑begin-->
		<div class="modal fade" id="updateView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>用户信息-新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<input id="userId" type="hidden"/>
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>用户编号：</label> <input type="text"
									id="edit_code" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>登录账户：</label> <input type="text"
									id="edit_account" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>用户名称：</label> <input type="text"
									id="edit_name" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>登录密码：</label> <input type="password"
									id="edit_psd">
							</div>
							<div class="alt_edit_div">
								<label>所属组织：</label> <span>${orgName}</span>
							</div>
							<div class="alt_edit_div">
								<label>说明：</label> <input type="text" id="edit_remark">
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input type="checkbox" id="edit_isvalid"><span>有效</span>
								<em class="colorred">注：无效用户不能登录。</em>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button id="updateCommit" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
	</div>
	<script type="text/javascript" src="${staticBasePath}/js/md5.js"></script>
	<script type="text/javascript" src="/javascript/system/auth/userMgr.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>