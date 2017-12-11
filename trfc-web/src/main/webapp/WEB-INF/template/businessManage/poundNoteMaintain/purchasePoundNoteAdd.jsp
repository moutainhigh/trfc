<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购计量单新增</title>
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
				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="addBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>采购计量单新增</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>订单号：</label>
								<div class="input_withlogo">
									<input id="billcode" type="text" placeholder="请选择订单"
										readonly="readonly"> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>单据编号：</label> <input type="text" readonly="true"
									value="${code }">
							</div>
							<div class="daohuo_add_solo">
								<label>收货单位：</label> <input id="receivedepartmentname"
									type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input id="suppliername" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input id="purchasesum" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input id="margin" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input id="grossweight" type="text" placeholder="请输入毛重">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input id="tareweight" type="text" placeholder="请输入皮重">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input id="netweight" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>原发净重：</label> <input id="originalnetweight" type="text" placeholder="请输入原发净重">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input id="vehicle" type="text" placeholder="请选择车号">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input id="materielname" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input id="driver" type="text" placeholder="请选择司机">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input id="driveridentityno" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>仓库：</label> <input id="warehouse" type="text" placeholder="请选择仓库">
							</div>
							<div class="daohuo_add_solo">
								<label>矿口：</label> <input id="minemouth" type="text" placeholder="请选择矿口">
							</div>
							<div class="daohuo_add_solo">
								<label>堆场：</label> <input id="yard" type="text" placeholder="请选择堆场">
							</div>
							<div class="daohuo_add_solo">
								<label>收料员：</label> <input id="receiverperson" type="text" placeholder="请选择收料员">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input id="weighttime" type="text"
									value="${nowDate }" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择重车时间"/>
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input id="lighttime" type="text"
									value="${nowDate }" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择轻车时间"/>
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input id="supplierremark" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input id="makebilltime" type="text"
									value="${nowDate }" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" placeholder="请选择制单日期"/>
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input id="makebillname" type="text"
									value="${makebillname }" readonly="true">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>

	</div>
	<!--订单号弹出begin-->
	<div class="modal fade" id="altbill" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document"
			style="width: 75%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>选采购订单</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="dhadd_search">
						<div class="dhsearch_solo">
							<label>物料：</label> <input id="materiel" type="text"
								placeholder="请选择物料" />
						</div>
						<div class="dhsearch_solo">
							<label>供应商：</label> <input id="supplier" type="text"
								placeholder="请选择供应商" />
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input id="purchaseApplicationCode"
								type="text" placeholder="请输入订单号" />
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input id="starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择开始时间" />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input id="endtime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
								class="Wdate" style="width: 160px" readonly
								placeholder="请选择结束时间" />
						</div>
						<div class="dhsearch_solo">
							<button id="PurchaseApplicationSearchBtn" class="btn btnblue ">搜索</button>
						</div>
					</div>
					<div style="width: 100%">
						<div class="dh_alttable">
							<table class="table table-hover">
								<thead>
									<tr>
										<th>序号</th>
										<th>采购订单号</th>
										<th>供应商名称</th>
										<th>物料</th>
										<th>物料规格</th>
										<th>物料类型</th>
										<th>订单数量</th>
										<th>余量</th>
										<th>入库占用量</th>
										<th>未入库占用量</th>
										<th>到货占用量</th>
										<th>采购组织</th>
										<th>订单日期</th>
										<th>采购部门</th>
										<th>矿口</th>
										<th>司机身份验证</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="purchaseApplicationBody">

								</tbody>
							</table>
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
	<!--订单号弹出end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/purchasePoundNoteAdd.js"></script>
</body>
</html>
