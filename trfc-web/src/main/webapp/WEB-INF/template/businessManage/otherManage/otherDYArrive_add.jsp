<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>厂内倒运通知单-新增</title>
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
								<li id="refresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="saveBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="goback"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>厂内倒运通知单新增</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>单据编号：</label> <input
									type="text" readonly="true" id="add_code">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>调出堆场：</label> <input
									type="text" id="add_leaveyard" class="yardSelect" placeholder="请选择调出堆场" yardid="${dy.leaveyard}" value="${dy.leaveyerdname }">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>调入堆场：</label> <input
									type="text" id="add_enteryard" class="yardSelect" placeholder="请选择调入堆场" yardid="${dy.enteryard}" value="${dy.enteryerdname }">
							</div>
							<div class="daohuo_add_solo">
								<label>倒运单位：</label> <input type="text" id="add_receivedepartment" readonly="true" value="${org }">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text" id="add_vehicle"  placeholder="请选择车号">
							</div>
							<div class="daohuo_add_solo">
								<label>RFID：</label> <input type="text" id="add_rfid" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>物料：</label> <input type="text" id="add_materiel"  placeholder="请选择物料" materielid="${dy.materielid }" value="${dy.materielname }">
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>开始时间：</label> <input
									type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'add_endtime\')}'})"
									class="Wdate" style="width: 180px" id="add_starttime"
									placeholder="请输入开始时间" readonly="readonly" value="${dy.starttimeStr }"/>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>截至时间：</label> <input
									type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'add_starttime\')}'})"
									class="Wdate" style="width: 180px" id="add_endtime"
									placeholder="请输入截至时间" readonly="readonly" value="${dy.endtimeStr }"/>
							</div>
							<div class="daohuo_add_solo" style="width: 100%;">
								<label>备注： </label> <input type="text" id="add_remark" style="width: 70%;">
							</div>
							<div class="daohuo_add_solo" style="width: 100%;">
								<label>提醒：</label> <span>无</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	</div>


	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="${staticBasePath}/js/cardReader.js"></script>
	<script type="text/javascript"
		src="/javascript/businessManage/otherManage/otherDYArrive_add.js?20171214"></script>
</body>
</html>