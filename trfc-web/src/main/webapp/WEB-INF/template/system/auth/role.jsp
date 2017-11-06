<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>角色管理</title>
<!-- 引用公共header部分 -->
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
<style type="text/css">
.juese_table {
margin-bottom: -20px!important;
}
</style>
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
									<label>角色编号：</label>
									<input type = "text" id="selectCode" placeholder="请输入角色编号">
								</div>
								<div class="intel_solo">
									<label>角色名称：</label>
									<input type = "text" id="selectName" placeholder="请输入角色名称">
								</div>
								<div class="intel_solo">
									<label>角色类别：</label>
									 <select id="roleType" class="form-control" >
										<option value="">==请选择==</option>
										<option value="1">应用角色</option>
										<option value="2">业务角色</option>
										<option value="3">系统角色</option>
										<option value="4">手持机角色</option>
										<option value="5">子系统角色</option>
									</select>
								</div>
								<div class="intel_solo">
									<div class="intel_sbtn">
										<button class="btn btnblue searchBtn" id="searchBtn">搜索</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="juese_mbox">
							<div class="intel_opera">
								 <div class="intel_operasolo" id="refreshBtn">
									<i class="iconfont colorlv">&#xe61b;</i> <span>刷新
										</h5>
								</div>
								<div class="intel_operasolo">
									<a data-toggle="modal" data-target="#add" id="initAdd"> <i
										class="iconfont coloradd">&#xe627;</i> <span>新增
											</h5></a>
								</div>
							</div>
							<div class="juese_table">
								<table class="table table-hover table-bordered">
									<thead>
										<tr>
											<th>序号</th>
											<th>角色编号</th>
											<th>角色名称</th>
											<th>角色分类</th>
											<th>有效</th>
											<th>允许编辑</th>
											<th>允许删除</th>
											<th>描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody id="roles">
									</tbody>
								</table>
							</div>
						</div>
					<!--采购申请单end-->
		</div>
	</div>
	<!--新增begin-->
	<div class="modal fade" id="add" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>角色信息-添加</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>角色编号：</label> <input type="text" value="121221" id="code"
								readonly="readonly">
						</div>
						<div class="alt_edit_div">
							<label>角色名称：</label> <input type="text" id="name">
						</div>
						<div class="alt_edit_div">
							<label>角色分类：</label> <select class="form-control" id="role_type">
								<option value="0">==请选择==</option>
								<option value="1">应用角色</option>
								<option value="2">业务角色</option>
								<option value="3">系统角色</option>
								<option value="4">手持机角色</option>
								<option value="5">子系统角色</option>
							</select>
						</div>
						<div class="alt_edit_div">
							<label>选项：</label> <input type="checkbox" id="isvalid"><span
								style="margin: 0 15px 0 0.8px;">有效</span> <input type="checkbox"
								id="allow_edit"><span style="margin: 0 15px 0 0.8px;">允许编辑</span>
							<input type="checkbox" id="allow_del"><span
								style="margin: 0 15px 0 0.8px;">允许删除</span>
						</div>
						<div class="alt_edit_textarea">
							<label>描述： </label>
							<textarea class="form-control" rows="1" id="info"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="ensureAdd">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增end-->
	<!--编辑begin-->
	<div class="modal fade" id="edit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>角色信息-编辑</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>角色编号：</label> <input type="text" value="121221"
								id="update_code" readonly="readonly">
						</div>
						<div class="alt_edit_div">
							<label>角色名称：</label> <input type="text" id="update_name">
						</div>
						<div class="alt_edit_div">
							<label>角色分类：</label> <select class="form-control"
								id="update_role">
								<option value="0">==请选择==</option>
								<option value="1">应用角色</option>
								<option value="2">业务角色</option>
								<option value="3">系统角色</option>
								<option value="4">手持机角色</option>
								<option value="5">子系统角色</option>
							</select>
						</div>
						<div class="alt_edit_div">
							<label>选项：</label> <input type="checkbox" id="update_isvalid"><span
								style="margin: 0 15px 0 0.8px;">有效</span> <input type="checkbox"
								id="update_edit"><span style="margin: 0 15px 0 0.8px;">允许编辑</span>
							<input type="checkbox" id="update_del"><span
								style="margin: 0 15px 0 0.8px;">允许删除</span>
						</div>
						<div class="alt_edit_textarea">
							<label>描述： </label>
							<textarea class="form-control" rows="1" id="update_info"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="ensureEdit">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
	
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript" src="/javascript/system/auth/role.js"></script>
	
</body>
</html>