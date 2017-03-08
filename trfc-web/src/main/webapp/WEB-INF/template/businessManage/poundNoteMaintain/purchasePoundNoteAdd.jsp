<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
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
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select"><a href="/trfc/poundNote/purchase/main">采购磅单维护</a></li>
				</ul>
			</div>
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
								<li><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li><i class="iconfont colorblue">&#xe61d;</i>
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
									<input type="text" data-toggle="modal" data-target="#altbill">
									<span class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>单据编号：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>收货单位：：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>余量：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>原发净重：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>仓库：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>矿口：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>推场：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>收料员：</label> <input type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input type="text" readonly="true"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input type="text" readonly="true"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="true"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" readonly="true">
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
		<div class="modal-dialog" role="document" style="width: 800px;">
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
							<label>物料：</label> <input type="text">
						</div>
						<div class="dhsearch_solo">
							<label>供应商：</label> <input type="text">
						</div>
						<div class="dhsearch_solo">
							<label>订单号：</label> <input type="text">
						</div>
						<div class="dhsearch_solo">
							<label>开始时间：</label> <input type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
								class="Wdate" style="width: 160px" />
						</div>
						<div class="dhsearch_solo">
							<label>结束时间：</label> <input type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
								class="Wdate" style="width: 160px" />
						</div>
						<div class="dhsearch_solo">
							<button class="btn btnblue ">搜索</button>
						</div>
					</div>
					<div class="dh_alttable">
						<table class="fancyTable" id="myTable04" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr>
									<th></th>
									<th>采购订单号</th>
									<th>订单类型</th>
									<th>供应商</th>
									<th>物料</th>
									<th>规格</th>
									<th>类型</th>
									<th>订单数量</th>
									<th>余量</th>
									<th>出库占用量</th>
									<th>未出库占用量</th>
									<th>预提占用量</th>
									<th>采购组织</th>
									<th>订单日期</th>
									<th>采购部门</th>
									<th>矿口</th>
									<th>司机身份验证</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
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
									<td>粉煤灰1</td>
									<td>2016-01-01 07:59:39</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!--分页效果开始-->
					<div class="page">
						<div class="page_date">
							<label>数据共：</label><i class="colorred">100</i><label>条</label>
						</div>
						<div class="page_btn">
							<ul class="pagination">
								<li><a href="#">&laquo;上一页</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">5454</a></li>
								<li><a href="#">3111</a></li>
								<li><a href="#">3111</a></li>
								<li><a href="#">下一页&raquo;</a></li>
							</ul>
						</div>
					</div>
					<!--分页效果结束-->
					<div class="clear"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/purchasePoundNoteAdd.js"></script>
	<script type="text/javascript">
		</body>
		</html>
	