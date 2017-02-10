<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售批量维护-修改</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">

<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>

	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select"><a href="">批号维护单新增</a></li>
				</ul>
				<!--tab切换标题end-->
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
								<li  id="refresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="save"><i class="iconfont colorblue" >&#xe61d;</i>
									<h5>保存</h5></li>
								<li><a href="/trfc/quality/sales/batchnum/main"> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>批号维护单录入</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>单据编号：</label> <input
									id="edit_code" type="text" readonly="true" value="000">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>批号：</label> <input
									id="edit_factorycode" type="text" value="000">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>数量：</label> <input
									id="edit_count" type="text" value="000">
							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>物料：</label> <select
									id="edit_material" class="material_select2"></select>
							</div>
							<div class="daohuo_add_solo">
								<label>生产日期：</label> <input type="text" readonly="true"
									id="edit_producedtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" />
							</div>
							<div class="daohuo_add_solo">
								<label>试验日期：</label> <input type="text" readonly="true"
									id="edit_testtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" />
							</div>
							<div class="daohuo_add_solo">
								<label>化验日期：</label> <input type="text" readonly="true"
									id="edit_assaytime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" />
							</div>
							<div class="daohuo_add_solo">
								<label>化验人：</label> <select
									id="user_select" class="material_select2"></select>							</div>
							<div class="daohuo_add_solo">
								<label><span class="colorred">*</span>化验单位：</label> <input
									id="edit_assayorg" type="text" readonly="true" value="000">
							</div>
							<div class="daohuo_add_solo">
								<label>开始日期：</label> <input type="text" readonly="true"
									id="edit_starttime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" />
							</div>
							<div class="daohuo_add_solo">
								<label>结束日期：</label> <input type="text" readonly="true"
									id="edit_endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									class="Wdate" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="true"
									id="edit_createtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" value="admin"
									id="edit_creator" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input type="text" value="admin" id="edit_remark" >
							</div>
						</div>
					</div>
					<div class="clear"></div>
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
	<script type="text/javascript"
		src="/resources/js/select2.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum_common.js"></script>
	<script type="text/javascript"
		src="/javascript/quality/sales/batchnum_edit.js"></script>
	</script>


	
</body>
</html>