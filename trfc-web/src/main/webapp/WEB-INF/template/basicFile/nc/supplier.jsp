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
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li><a href="/trfc/customer/main">客戶管理</a></li>
					<li><a href="/trfc/warehouse/main">仓库管理</a></li>
					<li class="select"><a href="/trfc/supplier/main">供应商管理</a></li>
					<li><a href="/trfc/materiel/main">物料管理</a></li>
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
									<option value="py">简拼</option>
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
										<button class="btn btnblue" id="searchBtn">搜索</button>
									</div>
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
									<th>编号</th>
									<th>内码</th>
									<th>名称</th>
									<th>所属组织</th>
									<th>矿口</th>
									<th>司机身份验证</th>
									<th>描述</th>
									<th>操作</th>
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
		<!--编辑begin-->
		<div class="modal fade" id="editData" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>供应商管理</h5>
						</div>
					</div>
					<div class="modal-body">
						<input id="supplierid" type="hidden">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>供应商编号：</label> <input id="code" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>供应商内码：</label> <input id="internalcode" type="text"
									readonly>
							</div>
							<div class="alt_edit_div">
								<label>供应商名称：</label> <input id="name" type="text">
							</div>
							<div class="alt_edit_div">
								<label>供应商简码：</label> <input id="abbrname" type="text">
							</div>
							<div class="alt_edit_div">
								<label>拼音码：</label> <input id="pinyincode" type="text">
							</div>
							<div class="alt_edit_div">
								<label>选项：</label> <input id="minemouth" type="checkbox"><span>矿口</span>
								<input id="drivercheck" type="checkbox"><span>
									司机身份验证</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea id="remarks" class="form-control" rows="1"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="updateBtn">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/basicFile/nc/supplier.js"></script>
</body>
</html>