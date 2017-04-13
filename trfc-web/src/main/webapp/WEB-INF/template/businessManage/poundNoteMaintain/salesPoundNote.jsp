<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售磅单维护</title>
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
									<label>开始时间：</label> <input id="starttime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" readonly="readonly"
										placeholder="请选择开始时间" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input id="endtime" type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" readonly="readonly"
										placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo">
									<label>订单编号：</label> <input id="billcode" type="text" placeholder="请输入订单编号">
								</div>
								<div class="intel_solo">
									<label>过磅单号：</label> <input id="code" type="text" placeholder="请输入过磅单号">
								</div>
								<div class="intel_solo">
									<label>批号：</label> <input id="serialnumber" type="text" placeholder="请输入批号">
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="noticecode" type="text" placeholder="请输入通知单号">
								</div>
								<div class="intel_solo">
									<label>客户：</label> <input id="customer" type="text" placeholder="请选择客户">
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input id="materiel" type="text" placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input id="vehicle" type="text" placeholder="请选择车号">
								</div>
								<div class="intel_solo">
									<label>净重：</label> <select id="operator" class="mini_select form-control" style="padding: 0px;">
										<option value="0">大于</option>
										<option value="1">大于等于</option>
										<option value="2">小于</option>
										<option value="3">小于等于</option>
										<option value="4">等于</option>
									</select> <input id="netweight" type="text" class="mini_input" placeholder="请输入净重">
								</div>
								<div class="intel_solo">
									<label>单据类型：</label> <select id="status" class="form-control">
										<option value="">请选择</option>
										<option value="0">红冲</option>
										<option value="1">补增</option>
										<option value="2">作废</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>NC状态：</label> <select id="returnstatus" class="form-control">
										<option value="">请选择</option>
										<option value="0">未推单</option>
										<option value="2">已推单</option>
									</select>
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
								class="iconfont coloradd">&#xe647;</i>
								<h5>补增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>过磅单号</th>
									<th>推单状态</th>
									<th>是否红冲</th>
									<th>单据状态</th>
									<th>出库单号</th>
									<th>订单号</th>
									<th>提货单</th>
									<th>客户</th>
									<th>发货单位</th>
									<th>物料</th>
									<th>车号</th>
									<th>毛重</th>
									<th>皮重</th>
									<th>净重</th>
									<th>出厂编号</th>
									<th>过磅员</th>
									<th>轻车时间</th>
									<th>重车时间</th>
									<th>制单人</th>
									<th>制单日期</th>
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

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">2</div>
				<!--到货通知单end-->
				<!--到货通知单end-->
				<!--tab切换的内容end-->
			</div>
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/salesPoundNote.js"></script>
</body>
</html>