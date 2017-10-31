<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购车辆状态</title>
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
									<label>订单号：</label> <input id="billcode" type="text"
										placeholder="请输入订单号">
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input type="text" id="supplier"
										placeholder="请选择供应商">

								</div>
								<div class="intel_solo">
									<label>车号：</label> <input type="text" id="vehicle"
										placeholder="请选择车号" />

								</div>
								<div class="intel_solo">
									<label>物料：</label> <input type="text" id="materiel"
										placeholder="请选择物料" />
								</div>
								<div class="intel_solo">
									<label>司机：</label> <input type="text" id="driver"
										placeholder="请选择司机" />
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="code" type="text"
										placeholder="请输入订单号" />
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
									<label>创建时间：</label> <input id="starttime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择开始时间" /> <i>-</i>
									<input id="endtime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo" id="searchBtn">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="refreshBtn">
							<a> </a><i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
							</a>
						</div>
						<div id="readSearch" class="intel_operasolo">
							<a> <i class="iconfont colorlv">&#xe601;</i> <span>读卡查找</span>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>车号</th>
									<th>车辆状态</th>
									<th>类型</th>
									<th>派车时间</th>
									<th>入厂时间</th>
									<th>重车时间</th>
									<th>轻车时间</th>
									<th>收货时间</th>
									<th>出厂时间</th>
									<th>通知单号</th>
									<th>订单号</th>
									<th>供应商</th>
									<th>物料</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>1</td>
									<td>CD201601010138</td>
									<td class="colorred">审核中</td>
									<td>客商APP</td>
									<td>未入厂</td>
									<td>豫GA1783</td>
									<td>CD201601010138</td>
									<td>卫辉市润晨商贸有限公司</td>
									<td>粉煤灰1</td>
									<td>2016-01-01 07:59:39</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
								</tr>
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
				<!--采购申请单end-->
			</div>
		</div>
	</div>

	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseVehicle.js"></script>

</body>
</html>
