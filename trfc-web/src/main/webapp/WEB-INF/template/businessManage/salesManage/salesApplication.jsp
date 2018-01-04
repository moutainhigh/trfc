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
#billDetail td {
	margin: 0px !important;
	padding: 0px !important;
}

#billDetail td>input {
	width: 100% !important;
	height: 40px !important;
	line-height: 40px !important;
	border-radius: 0px !important;
	border: 0px !important;
	text-align: center;
}

input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none !important;
	margin: 0;
}

.daohuo_add .daohuo_add_div .daohuo_add_solo {
	width: 50% !important;
}

.modal-dialog {
	width: 800px;
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
									<label>单据来源：</label> <select id="s_source" class="form-control">
										<option value="">请选择</option>
										<option value="0">联机</option>
										<option value="1">脱机</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>客户：</label> <input id="s_customer" class="customer" type="text"
										placeholder="请选择客户">
								</div>
								<div class="intel_solo">
									<label>开始时间：</label> <input id="s_starttime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'s_endtime\')}'})"
										class="Wdate" style="width: 160px;" readonly
										placeholder="请选择开始时间" /> <i>-</i> <input id="s_endtime"
										type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s_starttime\')}'})"
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
						<!-- <div id="refreshBtn" class="intel_operasolo">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div id="addBtn" class="intel_operasolo">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div>
						<div id="update" class="intel_operasolo">
							<a> <i class="iconfont update">&#xe600;</i>
								<span>编辑</span>
							</a>
						</div>
						<div id="audit" class="intel_operasolo">
							<a> <i class="iconfont audit">&#xe692;</i>
								<span>审核</span>
							</a>
						</div>
						<div id="unaudit" class="intel_operasolo">
							<a> <i class="iconfont unaudit">&#xe651;</i>
								<span>反审</span>
							</a>
						</div>
						<div id="delete" class="intel_operasolo">
							<a> <i class="iconfont delete">&#xe63d;</i>
								<span>删除</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
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
											<th>序号</th>
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
	</div>
	<div class="modal fade" id="addView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="daohuo_add">
						<h4 style="text-align: center; color: #0573C6; font-weight: bold">一单多车订单新增</h4>
						<hr style="background-color: #0573C6; height: 1px;">
						<div class="daohuo_add_div">
							<input id="billType" type="hidden">
							<div class="daohuo_add_solo">
								<label>订单号：</label>
								<div class="input_withlogo">
									<input type="text" class="billCode" readOnly="true">
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label></label>
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input class="billTime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" readonly placeholder="请选择日期">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input class="customer" type="text" placeholder="请选择客户">
							</div>
							<div class="daohuo_add_solo">
								<label>区域：</label> <input type="text" class="channelcode" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <select class="form-control salesOrg">
									<option value="${orgId }">${orgName }</option>
									<option value="0001P11000000049UNR1">河南圣业水泥销售有限公司</option>
								</select>
							</div>
							<div class="daohuo_add_solo">
								<label>业务员：</label> <input type="text" class="salesmanname" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>运输公司：</label> <input type="text" class="transportcompanyname" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" value="${requestScope.userName }" readOnly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="modal-body add_detail">
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li class="oneBillOneCarVehicle">车辆信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class=" ">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>物料</th>
												<th>发货仓库</th>
												<th>数量</th>
											</tr>
										</thead>
										<tbody id="billDetail">
											<tr>
												<td><input class="materiel" type="text"
													placeholder="请选择物料"></td>
												<td><input class="warehouse" type="text"
													placeholder="请选择仓库"></td>
												<td><input class="number" type="number"
													placeholder="请输入数量" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class="daohuo_add">
									<div class="daohuo_add_div">
										<div class="daohuo_add_solo">
											<div class="dh_carnum">
												<label class="colorred"><em class="colorred">*</em>车号：</label>
												<input type="text" class="vehicle" placeholder="请选择车辆">
											</div>
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input type="text" class="driver" placeholder="请选择司机">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input type="text" class="rfid" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input type="text" class="idNo" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input class="remark" type="text">
										</div>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="addCommitBtn" type="button" class="btn btn-primary">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑begin-->
	<div class="modal fade" id="updateView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="daohuo_add">
						<h4 style="text-align: center; color: #0573C6; font-weight: bold">一单一车订单新增</h4>
						<hr style="background-color: #0573C6; height: 1px;">
						<div class="daohuo_add_div">
							<input id="billId" type="hidden">
							<input id="billDetailId" type="hidden">
							<input id="billType" type="hidden">
							<div class="daohuo_add_solo">
								<label>订单号：</label>
								<div class="input_withlogo">
									<input type="text" class="billCode" readOnly="true">
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label></label>
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input class="billTime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" readonly placeholder="请选择日期">
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input class="customer" type="text" placeholder="请选择客户">
							</div>
							<div class="daohuo_add_solo">
								<label>区域：</label> <input type="text" class="channelcode" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <select class="form-control salesOrg">
									<option value="${orgId }">${orgName }</option>
									<option value="0001P11000000049UNR1">河南圣业水泥销售有限公司</option>
								</select>
							</div>
							<div class="daohuo_add_solo">
								<label>业务员：</label> <input type="text" class="salesmanname" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>运输公司：</label> <input type="text" class="transportcompanyname" readOnly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" class="makebillname" readOnly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="modal-body add_detail">
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li class="oneBillOneCarVehicle">车辆信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class=" ">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>物料</th>
												<th>发货仓库</th>
												<th>数量</th>
											</tr>
										</thead>
										<tbody id="billDetail">
											<tr>
												<td><input class="materiel" type="text"
													placeholder="请选择物料"></td>
												<td><input class="warehouse" type="text"
													placeholder="请选择仓库"></td>
												<td><input class="number" type="number"
													placeholder="请输入数量" /></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="cg_tabcont hide">
								<div class="daohuo_add">
									<div class="daohuo_add_div">
										<div class="daohuo_add_solo">
											<div class="dh_carnum">
												<label class="colorred"><em class="colorred">*</em>车号：</label>
												<input type="text" class="vehicle" placeholder="请选择车辆">
											</div>
										</div>
										<div class="daohuo_add_solo">
											<label>司机：</label> <input type="text" class="driver" placeholder="请选择司机">
										</div>
										<div class="daohuo_add_solo">
											<label>RFID：</label> <input type="text" class="rfid" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>身份证号：</label> <input type="text" class="idNo" readOnly="true">
										</div>
										<div class="daohuo_add_solo">
											<label>备注：</label> <input class="remark" type="text">
										</div>
									</div>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
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
						<img id="shImg" src="${basePath }/images/un_sh.png">
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
										<th>序号</th>
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
	</div>
	<jsp:include page="../../common/module/custom_choose.jsp"></jsp:include>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/salesManage/salesApplication.js?2018010402"></script>
	<script type="text/javascript">
		// 录入、参照tab切换菜单
		var cg_li = $('.add_detail .cg_dhadd ul li');
		cg_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = cg_li.index(this);
			$('.add_detail .cg_tabcont').eq(index).show().siblings().hide();
		});
	</script>
</body>
</html>