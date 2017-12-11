<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>推单管理</title>
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
									<label>申请单号：</label> <input id="requisitionNum" type="text"
										placeholder="请输入申请单号">
								</div>
								<div class="intel_solo">
									<label>通知单号：</label> <input id="noticeNum" type="text"
										placeholder="请输入通知单号" />
								</div>
								<div class="intel_solo">
									<label>状态：</label> <select id="pushStatus" class="form-control">
										<option value="">全部</option>
										<option value="1">推单中</option>
										<option value="2">推单成功</option>
										<option value="3"selected="selected">推单失败</option>
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
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="searchBtn" class="btn btnblue">搜索</button>
								</div>
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="clean" class="btn"
										style="color: #166aa8; margin: 0 5px 0 -20px">清空</button>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera"></div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>申请单号</th>
									<th>通知单号</th>
									<th>推送状态</th>
									<th>错误编码</th>
									<th>单据类型</th>
									<th>失败原因</th>
									<th>轻车时间</th>
									<th>重车时间</th>
									<th>净重</th>
									<th>创建时间</th>
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
			<!-- 引用公共footer部分 -->
			<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
			<script type="text/javascript"
				src="/javascript/businessManage/purchaseManage/PushSingle.js?1208"></script>
</body>
</html>