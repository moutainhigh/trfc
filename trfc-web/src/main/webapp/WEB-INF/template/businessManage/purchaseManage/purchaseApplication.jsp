<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购管理</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
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
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>订单号：</label> <input id="code" type="text"
										placeholder="请输入订单号">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input id="supplier" type="text"
										placeholder="请选择供应商" />
								</div>
								<div class="intel_solo">
									<label>单据来源：</label> <select id="source" class="form-control">
										<option value="">全部</option>
										<option value="0">联机</option>
										<option value="1">脱机</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>创建时间：</label> <input id="starttime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择开始时间" /> <i>-</i>
									<input id="endtime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择结束时间" />
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
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>订单编号</th>
									<th>审核状态</th>
									<th>单据来源</th>
									<th>类型</th>
									<th>供应商</th>
									<th>订单日期</th>
									<th>部门</th>
									<th>矿口</th>
									<th>采购员</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>审核人</th>
									<th>审核日期</th>
									<th>供应商备注</th>
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
											<th>采购组织</th>
											<th>物料</th>
											<th>物料规格</th>
											<th>物料类型</th>
											<th>数量</th>
											<th>入库占用量</th>
											<th>未入库占用量</th>
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
			<!--查看详情begin-->
			<div class="modal fade" id="dataDetail" tabindex="-1" role="dialog"
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
								<img id="shImg" src="/resources/images/sh.png">
							</div>
						</div>
						<div class="modal-body">
							<div class="">
								<div class="cg_div">
									<div class="cg_solo">
										<label>单据编号：</label> <input id="v_code" type="text" readonly>
									</div>
									<div class="cg_solo">
										<label>单据来源：</label> <input id="v_source" type="text" readonly>
									</div>
									<div class="cg_solo">
										<label>订单类型：</label> <input id="v_billtypename" type="text"
											readonly>
									</div>
									<div class="cg_solo">
										<label>订单日期：</label> <input id="v_billtime" type="text"
											readonly>
									</div>
									<div class="cg_solo">
										<label>供应商：</label> <input id="v_suppliername" type="text"
											readonly>
									</div>
									<div class="cg_solo">
										<label>总数量：</label> <input id="v_sumnum" type="text" readonly>
									</div>
									<div class="cg_solo">
										<label>采购员：</label> <input id="v_buyername" type="text"
											readonly>
									</div>
									<div class="cg_solo">
										<label>制单人： </label> <input id="v_makebillname" type="text"
											readonly>
									</div>
									<div class="cg_solo">
										<label> 制单日期：</label> <input id="v_makebilltime" type="text"
											readonly>
									</div>
									<div class="cg_bz">
										<label>备注：</label> <input id="v_remark" type="text" readonly>
									</div>
								</div>
								<div id="alt_tab">
									<div class="cg_tabtit">
										<ul>
											<li class="select">订单明细</li>
											<li>质检信息</li>
										</ul>
									</div>
									<div class="cg_tabbox">
										<!--tab切换的内容-->
										<div class="cg_tabcont">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>序号</th>
														<th>采购组织</th>
														<th>物料</th>
														<th>质检方案</th>
														<th>数量</th>
														<th>备注</th>
													</tr>
												</thead>
												<tbody id="detailtab1">
												</tbody>
											</table>
										</div>
										<div class="cg_tabcont hide">
											<table class="table table-bordered">
												<thead>
													<tr>
														<th>物料</th>
														<th>检验项目</th>
														<th>比较符</th>
														<th>下限</th>
														<th>比较符</th>
														<th>上限</th>
														<th>基准值</th>
														<th>浮动值</th>
														<th>扣价</th>
														<th>扣吨</th>
													</tr>
												</thead>
												<tbody id="detailtab2">
												</tbody>
											</table>
										</div>
										<!--tab切换的内容end-->
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--查看详情end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseApplication.js?2018010301"></script>
	<script type="text/javascript">
		// 弹出信息的tab切换菜单
		var alt_li = $('#alt_tab .cg_tabtit ul li');
		alt_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_alt = alt_li.index(this);
			$('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show()
					.siblings().hide();
		});
	</script>
</body>
</html>