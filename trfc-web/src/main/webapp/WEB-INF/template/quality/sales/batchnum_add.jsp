<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售批号维护</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">

<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
select {
	-webkit-appearance: none;
	-webkit-tap-highlight-color: #fff;
	outline: 0;
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
				<div class="intel_tabcont hide"></div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li id="fresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="save"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="rollback"><a
									href="/trfc/quality/sales/batchnum/main"> <i
										class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>批号维护单录入</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>化验日期：</label> <input type="text" id="add_assaytime"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
									readonly="readonly" placeholder="请选择化验日期" />
							</div>
							<div class="daohuo_add_solo">
								<label>化验人：</label> <input type="text" id="user_select" placeholder="请选择化验人">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>化验单位：</label> <input
									type="text" id="add_assayorg" readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>开始日期：</label> <input type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
									readonly="readonly" placeholder="请选择开始日期" id="add_starttime" />
							</div>
							<div class="daohuo_add_solo">
								<label>结束日期：</label> <input type="text" 
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
									readonly="readonly" placeholder="请选择结束日期"
									id="add_endtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="readonly"
									id="add_createtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" readonly="readonly"
									id="add_creator">
							</div>

						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">物料明细</li>
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
												<th>行号</th>
												<th>物料</th>
												<th>出厂批号</th>
												<th>数量</th>
												<th>生产日期</th>
												<th>试验日期</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody id="material">
											<tr>
												<td>1</td>
												<td width="200px"><select type="text"
													class="material_select2"
													style="border: none; width: 100%; height: 100%">
												</select></td>
												<td><input readonly="true" type="text"
													style="border: none; width: 100%;"></td>
												<td><input type="text" readonly="true"
													style="border: none; width: 100%;"></td>
												<td><input type="text" readonly="true"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
													style="border: none; width: 100%;"></td>
												<td><input type="text" readonly="true"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
													style="border: none; width: 100%;"></td>
												<td><input type="text" readonly="true"
													style="border: none; width: 100%;"></td>
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
			<!--到货通知单end-->

			<!--退货通知单begin-->
			<div class="intel_tabcont hide">3</div>
			<!--退货通知单end-->

			<!--到货通知单begin-->
			<div class="intel_tabcont hide">4</div>
			<!--到货通知单end-->
			<!--到货通知单begin-->
			<div class="intel_tabcont hide">5</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>

	</div>

	</div>



	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum_common.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum_add.js"></script>
	</script>

</body>
</html>