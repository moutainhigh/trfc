<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售申请单</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
#updateTabBody td {
	margin: 0px !important;
	padding: 0px !important;
}

#updateTabBody td>input {
	width: 100% !important;
	height: 40px !important;
	border-radius: 0px !important;
	border: 0px !important;
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
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select"><a href="/trfc/salesApplication/main">销售申请单</a></li>
					<li><a href="/trfc/salesArrive/main">提货通知单</a></li>
				</ul>
			</div>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<!--销售申请单begin-->
				<div class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>订单编号：</label> <input id="s_code" type="text"
										placeholder="请输入订单编号">
								</div>
								<div class="intel_solo">
									<label>单价来源：</label> <select id="s_source" class="form-control">
										<option value="">请选择</option>
										<option value="0">联机</option>
										<option value="1">脱机</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>客户：</label> <input id="s_customer" type="text" readonly
										placeholder="请选择客户">
								</div>
								<div class="intel_solo">
									<label>开始时间：</label> <input id="s_starttime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										class="Wdate" style="width: 160px;" readonly
										placeholder="请选择开始时间" /> <i>-</i> <input id="s_endtime"
										type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
										class="Wdate" style="width: 160px;" readonly
										placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div id="refreshBtn" class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div id="addBtn" class="intel_operasolo">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
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
									<th>订单编号</th>
									<th>状态</th>
									<th>来源</th>
									<th>类型</th>
									<th>客户</th>
									<th>订单日期</th>
									<th>业务员</th>
									<th>销售组织</th>
									<th>运输公司</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>审核人</th>
									<th>审核日期</th>
									<th>区域码</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="dataBody">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div id="dataMore" class="intel_result">
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>物料</th>
											<th>数量</th>
											<th>单价</th>
											<th>出库占用量</th>
											<th>未出库占用量</th>
											<th>预提占用</th>
											<th>余量</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="moreBody">
										<tr>
											<td>卫辉市天瑞水泥有限公司</td>
											<td>粉煤炭</td>
											<td>方案2</td>
											<td>1000</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
											<td>豫GA1783</td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
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
			</div>
		</div>
		<!--新增begin-->
		<div class="modal fade" id="addView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 1100px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>销售订单签订</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>订单号：</label> <input id="a_code" type="text"
									class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>订单类型：</label> <input id="a_billtype" type="text"
									placeholder="请选择订单类型" />
							</div>
							<div class="alt_edit_div">
								<label>业务日期：</label> <input id="a_billtimeStr" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" readonly>
							</div>
							<div class="alt_edit_div">
								<label>客户：</label> <input id="a_customer" type="text" readonly
									placeholder="请选择客户" />
							</div>
							<div class="alt_edit_div">
								<label>区域：</label> <input id="a_channelcode" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>销售组织：</label> <input id="a_orgname" type="text"
									value="${orgname }" orgid="${orgid }" class="readOnlyText"
									class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>业务员：</label> <input id="a_salesmanname" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>运输公司：</label> <input id="a_transportcompanyname"
									type="text" class="readOnlyText" class="readOnlyText"
									readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input id="a_makebillname" type="text"
									value="${user.name }" makerid="${user.id }"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
						</div>
						<div class="cg_tabtit">
							<ul>
								<li class="select">订单明细</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>物料</th>
											<th>发货仓库</th>
											<th>数量</th>
											<th>含税单价</th>
											<th>价税金额</th>
											<th>税率</th>
											<th>不含税单价</th>
											<th>不含税金额</th>
											<th>税额</th>
										</tr>
									</thead>
									<tbody id="updateTabBody">
										<tr>
											<td><input id="a_materiel" type="text"
												placeholder="请选择物料"></td>
											<td><input id="a_warehouse" type="text"
												placeholder="请选择仓库"></td>
											<td><input id="a_salessum" type="text" placeholder="请输入数量"/></td>
											<td><input id="a_taxprice" type="text" placeholder="请输入含税单价"/></td>
											<td><input id="a_taxpricesum" readonly="true"></td>
											<td><input id="a_taxrate" type="text" placeholder="请输入税率"/></td>
											<td><input id="a_untaxprice" type="text" placeholder="请输入不含税单价"></td>
											<td><input id="a_untaxpricesum" readonly="true"></td>
											<td><input id="a_taxratesum" readonly="true"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
					<div class="modal-footer">
						<button id="addCommitBtn" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--查看详情begin-->
		<div class="modal fade" id="detailView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>销售订单详细信息</h5>
							<img src="${basePath }/images/un_sh.png">
						</div>
					</div>
					<div class="modal-body">
						<div class="cg_div">
							<div class="cg_solo">
								<label>订单编号：</label> <input id="v_code" type="text" readonly>
							</div>
							<div class="cg_solo">
								<label>单据来源：</label> <input id="v_source" type="text" readonly>
							</div>
							<div class="cg_solo">
								<label>订单类型：</label> <input id="v_billtype" type="text" readonly>
							</div>
							<div class="cg_solo">
								<label>运输公司：</label> <input id="v_transportcompanyname"
									type="text" readonly>
							</div>
							<div class="cg_solo">
								<label>订单日期：</label> <input id="v_billtimeStr" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>客户：</label> <input id="v_customername" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>销售组织：</label> <input id="v_orgname" type="text" readonly>
							</div>
							<div class="cg_solo">
								<label>部门： </label> <input id="v_departmentname" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>业务员：</label> <input id="v_salesmanname" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>制单人：</label> <input id="v_creatorname" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>制单日期：</label> <input id="v_createtimeStr" type="text"
									readonly>
							</div>
							<div class="cg_solo">
								<label>审核人：</label> <input id="v_auditname" type="text" readonly>
							</div>
						</div>
						<div class="cg_tabtit">
							<ul>
								<li class="select">订单明细</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>物料</th>
											<th>数量</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody id="detailTabBody">
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--查看详情end-->
		<!--编辑begin-->
		<div class="modal fade" id="updateView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 1000px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>付款方式编辑</h5>
						</div>
					</div>
					<div class="modal-body">
						<input id="u_id" type="hidden"> <input id="u_detailid"
							type="hidden">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>订单号：</label> <input id="u_code" type="text"
									class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>订单类型：</label> <select id="u_billtype"
									class="form-control">
									<c:forEach items="${billType }" var="b">
										<option value="${b.id }">${b.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>业务日期：</label> <input id="u_billtimeStr" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" readonly>
							</div>
							<div class="alt_edit_div">
								<label>客户：</label> <input id="u_customer" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>区域：</label> <input id="u_channelcode" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>销售组织：</label> <input id="u_orgname" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>业务员：</label> <input id="u_salesmanname" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>运输公司：</label> <input id="u_transportcompanyname"
									type="text" class="readOnlyText" class="readOnlyText"
									readonly="true">
							</div>
							<div class="alt_edit_div">
								<label>制单人：</label> <input id="u_creatorname" type="text"
									class="readOnlyText" class="readOnlyText" readonly="true">
							</div>
						</div>
						<div class="cg_tabtit">
							<ul>
								<li class="select">订单明细</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>物料</th>
											<th>发货仓库</th>
											<th>数量</th>
											<th>含税单价</th>
											<th>价税金额</th>
											<th>税率</th>
											<th>不含税单价</th>
											<th>不含税金额</th>
											<th>税额</th>
										</tr>
									</thead>
									<tbody id="updateTabBody">
										<tr>
											<td><select id="u_materiel" class="materiel">
													<option value="">请选择</option>
													<c:forEach items="${materiel }" var="m">
														<option value="${m.id }">${m.name }</option>
													</c:forEach>
											</select></td>
											<td><select id="u_warehouse" class="warehouse">
													<option value="">请选择</option>
													<c:forEach items="${warehouse }" var="w">
														<option value="${w.id }">${w.name }</option>
													</c:forEach>
											</select></td>
											<td><input id="u_salessum" style="width: 80px;"></td>
											<td><input id="u_taxprice" style="width: 80px;"></td>
											<td><input id="u_taxpricesum" style="width: 80px;"
												class="readOnlyText" readonly="true"></td>
											<td><input id="u_taxrate" style="width: 80px;"></td>
											<td><input id="u_untaxprice" style="width: 80px;"></td>
											<td><input id="u_untaxpricesum" style="width: 80px;"
												class="readOnlyText" readonly="true"></td>
											<td><input id="u_taxratesum" style="width: 80px;"
												class="readOnlyText" readonly="true"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
					<div class="modal-footer">
						<button id="updateCommitBtn" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
	</div>

	<jsp:include page="../../common/module/custom_choose.jsp"></jsp:include>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/salesManage/salesApplication.js"></script>
</body>
</html>