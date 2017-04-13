<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供应商标准方案-编辑</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>

</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>

			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<div class="intel_tabcont hide"></div>

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
								<li id="goBack"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>供应商标准方案-编辑</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>单据编号：</label> <input
									type="text" value="admin" readonly="true" id="edit_code">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>方案名称：</label> <input
									type="text" id="edit_name">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>供应商：</label> <input
									id="edit_supplier" class="supplierSel" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input type="text" id="edit_supremark">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>物料：</label> <input
									id="edit_material" class="materialSel" type="text">
								
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>质检方案：</label> <input
									id="edit_scheme" class="qschemeSel" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>开始日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
									id="edit_starttime" />
							</div>
							<div class="daohuo_add_solo">
								<label>结束日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
									id="edit_endtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>平均方式：</label> <select class="form-control" id="edit_mean">
									<option value="">--请选择--</option>
									<option value="0">月加权平均值</option>
									<option value="1">日平均值</option>
								</select>
							</div>
							<div class="daohuo_add_solo">
								<label>扣款方式：</label> <select class="form-control"
									id="edit_deduct">
									<option value="">--请选择--</option>
									<option value="0">按金额</option>
									<option value="1">按吨</option>
								</select>
							</div>
							<div class="daohuo_add_solo">
								<label>选项：</label> <input type="checkbox" checked="checked"
									id="edit_invalid"><span>有效 </span> <input
									type="checkbox" checked="checked" id="edit_ref"><span>默认</span>
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="true"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="edit_createtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" id="edit_creator"
									readonly="true">
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea class="form-control" rows="1" id="edit_remark"></textarea>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">化验明细</li>
								</ul>
							</div>
						</div>

						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<div class="zk_table_int">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>行号</th>
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
										<tbody id="edit_detail">

										</tbody>
									</table>
								</div>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>


			</div>

		</div>

	</div>

	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/file/supplierScheme_common.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/file/supplierScheme_edit.js"></script>
</body>
</html>