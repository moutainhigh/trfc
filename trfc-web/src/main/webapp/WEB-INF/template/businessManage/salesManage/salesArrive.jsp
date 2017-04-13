<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提货通知单</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
table.table-bordered th, td {
	text-align: center;
}
</style>
</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
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
									<label>订单号：</label> <input id="billcode" type="text"
										placeholder="请输入订单号" />
								</div>
								<div class="intel_solo">
									<label>客户：</label> <input id="customer" type="text"
										placeholder="请选择客户" />
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input id="vehicle" type="text"
										placeholder="请选择车辆" />
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input id="materiel" type="text"
										placeholder="请选择物料" />
								</div>
								<div class="intel_solo">
									<label>司机：</label> <input id="driver" type="text"
										placeholder="请选择司机" />
								</div>
							</div>
							<div class="intel_sline">
								<div class="intel_solo">
									<label>通知单号：</label> <input id="code" type="text"
										placeholder="请输入通知单号" />
								</div>
								<div class="intel_solo">
									<label>审核状态：</label> <select id="auditstatus"
										class="form-control">
										<option value="">请选择</option>
										<option value="0">未审核</option>
										<option value="1">已审核</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>单据来源：</label> <select id="source" class="form-control">
										<option value="">请选择</option>
										<option value="0">业务平台</option>
										<option value="1">客商平台</option>
										<option value="2">客商APP</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select id="status" class="form-control">
										<option value="">请选择</option>
										<option value="0">未入厂</option>
										<option value="1">空车</option>
										<option value="2">重车</option>
										<option value="3">作废</option>
										<option value="4">发卡</option>
										<option value="5">出厂</option>
										<option value="6">入厂</option>
										<option value="7">装车</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>创建时间：</label> <input id="starttime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px;" readonly
										placeholder="请选择开始时间" /> <i>-</i> <input id="endtime"
										type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\')}'})"
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
						<div class="intel_operasolo" id="addBtn">
							<i class="iconfont coloradd">&#xe627;</i>
							<h5>新增</h5>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>到货单号</th>
									<th>审核</th>
									<th>来源</th>
									<th>状态</th>
									<th>车号</th>
									<th>订单编号</th>
									<th>客户</th>
									<th>物料</th>
									<th>订单日期</th>
									<th>渠道</th>
									<th>制单日期</th>
									<th>制单人</th>
									<th>作废/强制出厂人</th>
									<th>作废/强制出厂时间</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="dataBody">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result" id="dataMore"></div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i id="total" class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input id="jumpPageNo" type="text" value="1">
							<label>页</label>
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
							<h5>采购申请单详细信息</h5>
							<img src="${basePath }/images/sh.png">
						</div>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="cg_div">
								<div class="cg_solo">
									<label>单据编号：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>单据来源：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>订单类型：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>订单日期：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>供应商：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>总数量：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>采购员：</label> <input type="text">
								</div>
								<div class="cg_solo">
									<label>制单人： </label> <input type="text">
								</div>
								<div class="cg_solo">
									<label> 制单日期：</label> <input type="text">
								</div>
								<div class="cg_bz">
									<label>备注：</label> <input type="text">
								</div>
							</div>
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li>质检信息</li>
									<li>质检信息</li>
								</ul>
							</div>
							<div class="cg_tabbox">
								<!--tab切换的内容-->
								<div class="cg_tabcont">
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
						<button type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- <jsp:include page="../../common/module/custom_choose.jsp"></jsp:include> --%>
	<!--查看详情end-->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/salesManage/salesArrive.js"></script>
</body>
</html>