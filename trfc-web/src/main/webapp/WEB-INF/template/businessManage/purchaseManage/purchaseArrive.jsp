<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>到货通知单</title>
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
									<label>供应商：</label> <input id="supplier" type="text"
										placeholder="请选择供应商" />
								</div>
								<div class="intel_solo">
									<label>车号：</label> <input id="vehicle" type="text"
										placeholder="请选择车号" />
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input id="materiel" type="text"
										placeholder="请选择物料" />
								</div>
								<div class="intel_solo">
									<label>司机：</label> <input id="driver" type="text"
										placeholder="请选择司机" />
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="code" type="text"
										placeholder="请输入通知单号">
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
							<a> <i class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
						<div id="update" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe600;</i>
								<h5>编辑</h5>
							</a>
						</div>
						<div id="audit" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe692;</i>
								<h5>审核</h5>
							</a>
						</div>
						<div id="unaudit" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe651;</i>
								<h5>反审</h5>
							</a>
						</div>
						<div id="invalid" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe60c;</i>
								<h5>作废</h5>
							</a>
						</div>
						<div id="outfactory" class="intel_operasolo">
							<a> <i class="iconfont coloradd">&#xe63c;</i>
								<h5>出厂</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover purchaseArrive">
							<thead>
								<tr>
									<th>序号</th>
									<th>到货单号</th>
									<th>审核</th>
									<th>来源</th>
									<th>状态</th>
									<th>车号</th>
									<th>订单编号</th>
									<th>供应商</th>
									<th>物料</th>
									<th>矿口</th>
									<th>制单日期</th>
									<th>订单日期</th>
									<th>作废/强制出厂人</th>
									<th>作废/强制出厂时间</th>
									<th>备注</th>
									<th>供应商备注</th>
								</tr>
							</thead>
							<tbody id="dataBody">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<div class="intel_result" id="ind_tab">
						<div class="cg_tabtit">
							<ul>
								<li class="select">物料信息</li>
								<li>订单信息</li>
								<li>过磅信息</li>
							</ul>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>车号</th>
											<th>到货数量</th>
											<th>司机</th>
											<th>身份证号</th>
											<th>制单日期</th>
											<th>制单人</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="vehicleno"></td>
											<td id="arrivalamount"></td>
											<td id="drivername"></td>
											<td id="driveridentityno"></td>
											<td id="makebilltimeStr"></td>
											<td id="makebillname"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>订单编号</th>
											<th>供应商</th>
											<th>物料</th>
											<th>组织机构</th>
											<th>订单量</th>
											<th>订单日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="billno"></td>
											<td id="suppliername"></td>
											<td id="materielname"></td>
											<td id="orgname"></td>
											<td id="purchasesum"></td>
											<td id="billtime"></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>磅单号</th>
											<th>车号</th>
											<th>毛重</th>
											<th>皮重</th>
											<th>净重</th>
											<th>轻车时间</th>
											<th>重车时间</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="poundnotecode"></td>
											<td id="vehicleno2"></td>
											<td id="grossweight"></td>
											<td id="tareweight"></td>
											<td id="netweight"></td>
											<td id="lighttime"></td>
											<td id="weighttime"></td>
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
							<label>数据共：</label><i id="total" class="colorred"></i><label>条</label>
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
			<!--tab切换的内容end-->
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/purchaseManage/purchaseArrive.js"></script>
	<script type="text/javascript">
		// 首页底部的tab切换菜单
		var ind_li = $('#ind_tab ul li');
		ind_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index_li = ind_li.index(this);
			$('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show()
					.siblings().hide();
		});
	</script>
</body>
</html>