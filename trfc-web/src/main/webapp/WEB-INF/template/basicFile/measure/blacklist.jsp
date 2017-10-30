<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车辆黑名单</title>
<script type="text/javascript" src="${staticBasePath }/js/menu_list.js"></script>
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
									<label>车辆：</label> <input type="text" id="vehicle"
										class="vehicle" placeholder="请输入车牌号">
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button id="search" class="btn btnblue ">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="refresh">
							<a> <i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo" id="add">
							<a> <i class="iconfont coloradd">&#xe627;</i> <span>新增</span>
							</a>
						</div>
						<div class="intel_operasolo" id="delete">
							<a> <i class="iconfont delete">&#xe63d;</i> <span>删除</span>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
							<thead>
								<tr>
									<th>序号</th>
									<th>车号</th>
									<th>创建时间</th>
									<th>创建人</th>
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
				<!--采购申请单end-->
			</div>
		</div>
	</div>
	<!--新增begin-->
	<div class="modal fade" id="addView" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>车辆黑名单-新增</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>车号：</label> <input type="text" id="add_vehicle"
								class="vehicle" placeholder="请选择车牌号">
						</div>
						<div class="alt_edit_textarea">
							<label>描述： </label>
							<textarea class="form-control" id="add_remarks"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="addCommit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增end-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/basicFile/measure/blacklist.js"></script>
</body>
</html>