<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物料管理</title>
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
					<li><a href="/customer/main">客戶管理</a></li>
					<li class="select"><a href="/warehouse/main">仓库管理</a></li>
					<li><a href="/supplier/main">供应商管理</a></li>
					<li><a href="/materiel/main">物料管理</a></li>
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
							<div class="intel_solo">
								<label>查询条件：</label> <select id="qtp" class="form-control">
									<option value="mc">名称</option>
									<option value="nm">内码</option>
								</select>
							</div>
							<div class="intel_sline">
								<div class="intel_solo">
									<label>关键字：</label> <input id="keyword" type="text">
								</div>
								<div class="intel_solo">
									<label>所属组织：</label> <input id="orgid" type="text">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="searchMater" class="btn btnblue">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div id="refreshMater" class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>编号</th>
									<th>内码</th>
									<th>名称</th>
									<th>所属组织</th>
									<th>描述</th>
								</tr>
							</thead>
							<tbody id="materBody">
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
		src="/javascript/basicFile/nc/warehouse.js"></script>
</body>
</html>