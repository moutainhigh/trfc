<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机管理</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">
<link href="/resources/css/pagination.css" rel="stylesheet">
<script language="javascript" type="text/javascript" src="/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/resources/js/dateutil.js"></script>
</head>
<body>
	<div class="left ">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="/resources/images/tx.jpg"
				class="img-circle"> <label>超级管理员</label> <i class="iconfont">&#xe602;</i>
			</a>
			<ul class="dropdown-menu">
				<li><a data-toggle="modal" data-target="#account"><i
						class="iconfont">&#xe60e;</i>个人资料</a></li>
				<li class="divider"></li>
				<li><a data-toggle="modal" data-target="#password"><i
						class="iconfont">&#xe60d;</i> 设置</a></li>
			</ul>
		</div>
		<div class="menu">
			<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
		</div>
		<ul class="typelist">
			<li><i class="iconfont">&#xe617;</i> <label>采购管理</label></li>
			<li><i class="iconfont">&#xe615;</i> <label>销售管理</label></li>
			<li><i class="iconfont">&#xe614;</i> <label>其他页面</label></li>
			<li class="active"><i class="iconfont">&#xe618;</i> <label>档案管理</label>
			</li>
		</ul>
	</div>
	<div class="leftmini hide">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="/resources/images/tx.jpg"
				class="img-circle">
			</a>
			<ul class="dropdown-menu">
				<li><a data-toggle="modal" data-target="#account"><i
						class="iconfont">&#xe60e;</i>个人资料</a></li>
				<li class="divider"></li>
				<li><a data-toggle="modal" data-target="#password"><i
						class="iconfont">&#xe60d;</i> 设置</a></li>
			</ul>
		</div>
		<div class="menu2">
			<i class="iconfont">&#xe635;</i>
		</div>
		<ul class="typelist">
			<li class="active" data-toggle="tooltip" data-placement="right"
				title="采购管理"><i class="iconfont">&#xe617;</i></li>
			<li data-toggle="tooltip" data-placement="right" title="销售管理"><i
				class="iconfont">&#xe615;</i></li>
			<li data-toggle="tooltip" data-placement="right" title=" 其他"><i
				class="iconfont">&#xe614;</i></li>
			<li><i class="iconfont">&#xe618;</i></li>
			<li><i class="iconfont">&#xe619;</i></li>
			<li><i class="iconfont">&#xe613;</i></li>
			<li><i class="iconfont">&#xe612;</i></li>
			<li><i class="iconfont">&#xe610;</i></li>
			<li><i class="iconfont">&#xe60f;</i></li>
			<li><i class="iconfont">&#xe611;</i></li>
		</ul>
	</div>
	<div class="right">
		<div class="intel_tab">
			<!--tab切换标题-->
			<ul class="intel_menu">
				<li>运输单位</li>
				<li class="select">车辆管理</li>
				<li>司机管理</li>
				<li>车辆黑名单</li>

			</ul>
			<!--tab切换标题end-->
			<div class="top_opera">
				<a><i class="iconfont" data-toggle="tooltip"
					data-placement="left" title="首页">&#xe605;</i></a> <a><i
					class="iconfont" data-toggle="tooltip" data-placement="left"
					title="控制面板">&#xe606;</i></a> <a><i class="iconfont"
					data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
			</div>
		</div>

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
								<label>查询条件：</label>
								<select id="qtp" class="form-control">
									<option value="mc">名称</option>
									<option value="nm">内码</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>关键字：</label> <input id="keyword" type="text">
							</div>
							<div class="intel_solo">
								<label>所属组织：</label> <input id="orgid" type="text">
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="searchBtn" class="btn btnblue ">搜索</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="intel_opera">
					<div id="refreshBtn" class="intel_operasolo">
						<i class="iconfont colorlv">&#xe61b;</i>
						<span>刷新</span>
					</div>
					<div id="addBtn" class="intel_operasolo">
						<a> <i class="iconfont coloradd">&#xe627;</i>
							<span>新增</span>
						</a>
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
					            <th>身份证</th>
					            <th>所属组织</th>
					            <th>电话</th>
					            <th>地址</th>
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
						<label>跳到第：</label> 
						<input id="jumpPageNo" type="text"> <label>页</label>
						<button id="jumpPageNoBtn" class="btn btn-default">确定</button>
					</div>
					<div class="page_date">
						<label>每页记录：</label>
						<select id="pageSize" class="form-control">
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
	<!--新增begin-->
	<div class="modal fade" id="addDriver" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>司机管理</h5>
					</div>
				</div>
				<div class="modal-body">
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>司机编号：</label> <input id="add_code" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机内码：</label> <input id="add_internalcode" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机名称：</label> <input id="add_name" type="text">
						</div>
						<div class="alt_edit_div">
							<label>司机简称：</label> <input id="add_abbrname" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="add_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="add_telephone" type="text">
						</div>
						<div class="alt_edit_div">
							<label>身份证号：</label> <input id="add_identityno" type="text">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input id="add_isvalid" type="checkbox"><span>有效</span>
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="add_orgname" type="text" readonly>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="add_remarks" class="form-control" rows="1"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="addDriverBtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--新增end-->
	<!--编辑begin-->
	<div class="modal fade" id="updateDriver" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 750px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="alt_head">
						<h5>车辆管理</h5>
					</div>
				</div>
				<div class="modal-body">
					<input id="driverid" type="hidden"/>
					<div class="alt_edit">
						<div class="alt_edit_div">
							<label>司机编号：</label> <input id="update_code" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机内码：</label> <input id="update_internalcode" type="text" readonly>
						</div>
						<div class="alt_edit_div">
							<label>司机名称：</label> <input id="update_name" type="text">
						</div>
						<div class="alt_edit_div">
							<label>司机简称：</label> <input id="update_abbrname" type="text">
						</div>
						<div class="alt_edit_div">
							<label>地址：</label> <input id="update_address" type="text">
						</div>
						<div class="alt_edit_div">
							<label>电话：</label> <input id="update_telephone" type="text">
						</div>
						<div class="alt_edit_div">
							<label>身份证号：</label> <input id="update_identityno" type="text">
						</div>
						<div class="alt_edit_div">
							<label>有效性：</label> <input id="update_isvalid" type="checkbox"><span>有效</span>
						</div>
						<div class="alt_edit_div">
							<label>所属组织：</label> <input id="update_orgname" type="text" readonly>
						</div>
						<div class="alt_edit_textarea">
							<label>备注： </label>
							<textarea id="update_remarks" class="form-control" rows="1"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="updateDriverBtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
	<!--删除begin-->
	<div class="modal fade" id="delDriver" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" style="width: 400px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modal-title">删除</h4>
				</div>
				<div class="modal-body">
					<input id="del_id" type="hidden">
					<div class="alert_qf">
						<img src="/resources/images/tishi.png" style="width: 20px;margin-top: -3px;">
						    <label id="modal-content">删除操作不可恢复，您确定要继续么？</label>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="deleteBtn">确定</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--删除end-->
	<script type="text/javascript" src="/resources/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/js/layer/layer.js"></script>
	<script type="text/javascript" src="/resources/js/basicFile/measure/driver.js"></script>
</body>
</html>