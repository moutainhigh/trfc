<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>门禁记录</title>
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
					<li class="select"><a href="/trfc/accessRecord/main">门禁记录</a></li>
				</ul>
			</div>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<div id="test" class="intel_tabcont">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="intel_sconditon">
							<div class="intel_sline">
								<div class="intel_solo">
									<label>门禁单号：</label>
									<input id="code" type="text"/>
								</div>
								<div class="intel_solo">
									<label>业务类型：</label> <select id="businesstype" class="form-control">
										<option value="">请选择</option>
										<option value="1">采购</option>
										<option value="2">销售</option>
										<option value="3">其他入库</option>
										<option value="4">其他出库</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select id="accesstype" class="form-control">
										<option value="">请选择</option>
										<option value="1">入厂</option>
										<option value="2">出厂</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>创建时间：</label> <input id="starttime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'endtime\')}'})"
										class="Wdate" style="width: 160px" /> <i>-</i> <input
										 id="endtime" type="text" readonly
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'starttime\')}'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchBtn" class="btn btnblue">搜索</button>
									</div>
								</div>
							</div>
							<div class="intel_sline">
								<div class="intel_solo">
									<label>物料：</label> <select id="materiel" class="form-control">
										<option value="">请选择</option>
										<c:forEach items="${materiel }" var="m">
											<option value="${m.id }">${m.name }</option>
										</c:forEach>
									</select>
								</div>
								<div class="intel_solo">
									<label>车号：</label> <select id="vehicle" class="form-control">
										<option value="">请选择</option>
										<c:forEach items="${vehicle }" var="v">
											<option value="${v.id }">${v.vehicleno }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div id="refreshBtn" class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>门禁单号</th>
									<th>类型</th>
									<th>单据状态</th>
									<th>车号</th>
									<th>物料</th>
									<th>派车单号</th>
									<th>单位</th>
									<th>RFID</th>
									<th>IC卡号</th>
									<th>IC卡面编号</th>
									<th>入厂时间</th>
									<th>出厂时间</th>
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
			</div>
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/logisticsManage/accessRecord.js"></script>
</body>
</html>