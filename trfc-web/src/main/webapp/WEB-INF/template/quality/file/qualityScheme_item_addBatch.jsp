<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>质检方案-项目-批量新增</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>

</head>
<body>
	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				
				<div class="top_opera">
					<a><i class="iconfont" data-toggle="tooltip"
						data-placement="left" title="首页">&#xe605;</i></a> <a><i
						class="iconfont" data-toggle="tooltip" data-placement="left"
						title="控制面板">&#xe606;</i></a> <a><i class="iconfont"
						data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
				</div>
			</div>
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
								<li><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>质检方案项目批量录入</h5>
						<div class="daohuo_add_div">
							<div class="zhij_piliang_add">
								<label>方案：</label> <input type="text">
							</div>
							<div class="zhij_piliang_add">
								<label>类型：</label> <input type="text">
							</div>
							<div class="zhij_piliang_check">
								<label>有效性：</label> <input type="checkbox"><span>有效</span>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">项目明细</li>
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
												<th>项目</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td><input type="text" readonly="true"></td>
												<td><input type="text" readonly="true"></td>
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

<!-- 引用公共footer部分 -->
		<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript" src="/resources/js/select2.js"></script>
		<script type="text/javascript"
			src="/javascript/quality/file/qualityScheme_item_addBatch.js"></script>
		</script>


</body>
</html>