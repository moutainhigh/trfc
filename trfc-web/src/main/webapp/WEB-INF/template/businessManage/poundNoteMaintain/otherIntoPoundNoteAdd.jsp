<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>其他入库计量单增加</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
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
						<h5>其他入库计量单增加</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>单据编号：</label> <input
									type="text" value="${code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>收货单位：</label> <input
									type="text" value="${orgname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>供应商：</label> <input
									id="supplier" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input id="department" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>物料：</label> <input
									id="materiel" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>货物：</label> <input id="cargo" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>毛重：</label> <input
									id="grossweight" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>皮重：</label> <input
									id="tareweight" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>净重：</label> <input
									id="netweight" type="text" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>车号：</label> <input
									id="vehicle" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>司机：</label> <input id="driver"
									type="text">
							</div>
							<div class="daohuo_add_solo">
								<label>身份证：</label> <input id="driveridentityno" type="text"
									readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>重车时间：</label> <input
									id="weighttime" type="text" value="${nowDate }" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>轻车时间：</label> <input
									id="lighttime" type="text" value="${nowDate }" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label>仓库：</label> <input id="warehouse" type="text">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>制单日期：</label> <input
									id="makebilltime" type="text" value="${nowDate }"
									readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate_add" />
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>制单人：</label> <input
									type="text" value="${makebillname }" readonly="true">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/poundNoteMaintain/otherIntoPoundNoteAdd.js?20171223"></script>
</body>
</html>