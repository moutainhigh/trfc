<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无人值守-系统业务-自定义编号</title>
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
				<!--自定义编号begin-->
				<div class="intel_tabcont">
					<div class="intel_opera">
						<div class="intel_operasolo" id="systemCode_refresh">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="systemCode_add">
							<a data-toggle="modal" data-target="#add"> <i
								class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>序号</th>
									<th>单据类型</th>
									<th>单据代号</th>
									<th>编号方式</th>
									<th>分隔符</th>
									<th>起始编号</th>
									<th>起始内码</th>
									<th>例子</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="systemCode_tbody">

							</tbody>
						</table>
						<!--用户表格end-->
					</div>

				</div>
				<!--自定义编号end-->
				<!--tab切换的内容end-->
			</div>
		</div>
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
							<h5>自定义编号信息编辑</h5>
						</div>
					</div>
					<input type="hidden" id="systemCode_edit_id" value="0">
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>单据类型：</label> <input type="text" value=" 000"
									id="systemCode_edit_key">
							</div>
							<div class="alt_edit_div">
								<label>单据代号：</label> <input type="text" value=" 000"
									id="systemCode_edit_code">
							</div>
							<div class="alt_edit_div">
								<label>编号方式：</label> <select class="form-control"
									id="systemCode_edit_codeType">
									<option value="0"></option>
									<option value="1">yyyy</option>
									<option value="2">yyyyMM</option>
									<option value="3">yyyyMMdd</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>分隔符：</label> <select class="form-control"
									id="systemCode_edit_splitType">
									<option value="0"></option>
									<option value="1">-</option>
									<option value="2">.</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>起始编号：</label> <select class="form-control"
									id="systemCode_edit_codeBegin">
									<option value="0">001</option>
									<option value="1">0001</option>
									<option value="2">00001</option>
									<option value="3">000001</option>
									<option value="4">0000001</option>
									<option value="5">00000001</option>
									<option value="6">000000001</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>起始内码：</label> <select class="form-control"
									id="systemCode_edit_innerCodeBegin">
									<option value="0">001</option>
									<option value="1">0001</option>
									<option value="2">00001</option>
									<option value="3">000001</option>
									<option value="4">0000001</option>
									<option value="5">00000001</option>
									<option value="6">000000001</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>例子：</label> <input type="text" value=" 000"
									id="systemCode_edit_example">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							id="systemCode_edit_ensure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="systemCode_edit_cancle">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
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
							<h5>自定义编号信息新增</h5>
						</div>
					</div>
					<input type="hidden" id="systemCode_add_id" value="0">
					<div class="modal-body">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>单据类型：</label> <input type="text" value=" 000"
									id="systemCode_add_key">
							</div>
							<div class="alt_edit_div">
								<label>单据代号：</label> <input type="text" value=" 000"
									id="systemCode_add_code">
							</div>
							<div class="alt_edit_div">
								<label>编号方式：</label> <select class="form-control"
									id="systemCode_add_codeType">
									<option value="0"></option>
									<option value="1">yyyy</option>
									<option value="2">yyyyMM</option>
									<option value="3">yyyyMMdd</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>分隔符：</label> <select class="form-control"
									id="systemCode_add_splitType">
									<option value="0"></option>
									<option value="1">-</option>
									<option value="2">.</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>起始编号：</label> <select class="form-control"
									id="systemCode_add_codeBegin">
									<option value="0">001</option>
									<option value="1">0001</option>
									<option value="2">00001</option>
									<option value="3">000001</option>
									<option value="4">0000001</option>
									<option value="5">00000001</option>
									<option value="6">000000001</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>起始内码：</label> <select class="form-control"
									id="systemCode_add_innerCodeBegin">
									<option value="0">001</option>
									<option value="1">0001</option>
									<option value="2">00001</option>
									<option value="3">000001</option>
									<option value="4">0000001</option>
									<option value="5">00000001</option>
									<option value="6">000000001</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>例子：</label> <input type="text" value=" 000"
									id="systemCode_add_example">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							id="systemCode_add_ensure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="systemCode_add_cancle">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 新增end -->
		<!--删除begin-->
		<div class="modal fade" id="dele" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 400px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">提示</h4>
					</div>
					<div class="modal-body">
						<div class="alert_qf">
							<img src="${staticBasePath }/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							id="systemCode_delete_ensure">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--删除end-->
	<script type="text/javascript"
		src="${staticBasePath }/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="/javascript/system/base/systemCode.js"></script>


	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>

</body>
</html>