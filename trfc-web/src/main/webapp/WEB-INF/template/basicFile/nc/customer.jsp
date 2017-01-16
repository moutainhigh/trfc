<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户管理</title>



<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<link href="${staticBasePath }/css/pagination.css" rel="stylesheet">

</head>
<body>
	<!-- 引用公共left部分 -->
	<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
	<div class="right">

		<!-- 引用公共right部分 -->
		<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
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
								<option value="py">拼音码</option>
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
								<th>渠道类型</th>
								<th>渠道类型内码</th>
								<th>所属组织</th>
								<th>描述</th>
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
	<div class="modal fade" id="updateCustomer" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>客户管理</h5>
					</div>
				</div>
				<div class="modal-body">
					<input id="update_id" type="hidden">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>客户编号：</label> <input id="update_code" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>客户内码：</label> <input id="update_internalcode" type="text"
								readonly>
						</div>
						<div class="alt_edit_div">
							<label>客户名称：</label> <input id="update_name" type="text">
						</div>
						<div class="alt_edit_div">
							<label>客户简称：</label> <input id="update_abbrname" type="text">
						</div>
						<div class="alt_edit_div">
							<label>所属单位：</label> <input id="update_orgname" type="text"
								readonly>
						</div>
						<div class="alt_edit_div">
							<label>区域码：</label> <input id="update_channelcode" type="text">
						</div>
						<div class="alt_edit_div">
							<label>拼音助记码：</label> <input id="update_pinyincode" type="text">
						</div>
						<div class="alt_edit_div">
							<label>客户类型：</label> <select id="update_customertype"
								class="form-control">
								<option value="">请选择</option>
								<option value="0">其他客户</option>
								<option value="1">本地客户</option>
							</select>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="update_remarks" class="form-control" rows="1"></textarea>
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
	<script type="text/javascript"
		src="${staticBasePath }/js/jquery.pagination.js"></script>
	<script type="text/javascript"
		src="${staticBasePath }/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="/javascript/basicFile/nc/customer.js"></script>


	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
</body>
</html>