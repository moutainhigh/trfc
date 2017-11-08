<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商用户</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
.cg_tabbox tbody input[type="text"] {
	font-size: 12px;
	width: 100%;
	border: 0px;
}

.cg_tabbox .cg_tabcont table tbody td {
	text-align: center;
	padding: 0px;
	margin: 0px;
}

input[readonly] {
	background: #f5f6fb;
}

.cg_tabtit ul li {
	line-height: 40px;
}

.modal-body {
	padding-top: 0px;
	padding-bottom: 0px;
}
</style>
</head>
<body>
	<div class="it_admin ">
		<!-- 引用公共left部分 -->
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--采购申请单begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>查询条件：</label> <select id="qtp" class="form-control">
										<option value="bh">用户编号</option>
										<option value="mc">用户名称</option>
									</select>
								</div>

								<div class="intel_solo">
									<label>关键字：</label> <input id="keyword" type="text">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input id="supplier" type="text">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<!-- <div id="refreshBtn" class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i>
							<span>刷新</span>
						</div>
						<div class="intel_operasolo" id="add">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>编号</th>
									<th>供应商名称</th>
									<th>说明</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="dataBody">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo" type="text"> <label>页</label>
							<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
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
				<!--采购申请单end-->
			</div>
		</div>
		<!--新增begin-->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
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
								<label class="colorred">供应商*：</label> <input id="a_supplier"
									type="text">
							</div>
							<div class="alt_edit_div">
								<label>所属组织：</label> <input type="text" readonly="true"
									value="${orgname }">
							</div>
							<div class="alt_edit_div">
								<label>说明：</label> <input id="remark" type="text">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="addSupplierGroupBtn" type="button"
							class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--新增组成员begin-->
		<div class="modal fade" id="addGroup" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>用户数据权限设置</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>用户：</label> <input id="supplierGroup" type="text"
									readonly="true">
							</div>
							<div class="clear"></div>
							<div>
								<div class="cg_dhadd">
									<div class="cg_tabtit">
										<ul>
											<li class="select">权限明细</li>
										</ul>
									</div>
								</div>
								<div class="cg_tabbox">
									<!--tab切换的内容-->
									<div class="cg_tabcont">
										<div class="zk_table_int">
											<table class="table table-bordered" id="tab">
												<thead>
													<tr>
														<th>行号</th>
														<th style="width: 30%;">供应商</th>
														<th>说明</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>1</td>
														<td><input class="supplier" type="text"></td>
														<td><input type="text"></td>
													</tr>
													<tr>
														<td>2</td>
														<td><input class="supplier" type="text"
															readonly="true" disabled="disabled"></td>
														<td><input type="text" readonly="true"></td>
													</tr>
													<tr>
														<td>3</td>
														<td><input class="supplier" type="text"
															readonly="true" disabled="disabled"></td>
														<td><input type="text" readonly="true"></td>
													</tr>
													<tr>
														<td>4</td>
														<td><input class="supplier" type="text"
															readonly="true" disabled="disabled"></td>
														<td><input type="text" readonly="true"></td>
													</tr>
													<tr>
														<td>5</td>
														<td><input class="supplier" type="text"
															readonly="true" disabled="disabled"></td>
														<td><input type="text" readonly="true"></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
									<!--tab切换的内容end-->
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="addSupplierToGroupBtn" type="button"
							class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增组成员end-->
		<!--组成员详情begin-->
		<div class="modal fade" id="detail" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 850px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>用户数据权限设置</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>用户：</label> <input id="supplierGroupDetail" type="text"
									readonly="true">
							</div>
							<div class="clear"></div>
							<div>
								<div class="cg_dhadd">
									<div class="cg_tabtit">
										<ul>
											<li class="select">权限明细</li>
										</ul>
									</div>
								</div>
								<div class="cg_tabbox">
									<!--tab切换的内容-->
									<div class="cg_tabcont"
										style="overflow: auto; max-height: 400px;">
										<div class="zk_table_int">
											<table class="table table-bordered" id="tab_detail">
												<thead>
													<tr>
														<th style="width: 60px;">行号</th>
														<th style="width: 20%;">供应商编号</th>
														<th style="width: 30%;">供应商名称</th>
														<th>说明</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
									<!--tab切换的内容end-->
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--组成员详情end-->
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/system/merchants/supplierGroup.js"></script>
</body>
</html>