<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>IC卡注册</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">
<link href="/resources/css/pagination.css" rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="/resources/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript"
	src="/resources/js/dateutil.js"></script>
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
		<div class="menu_collap">
			<ul class="typelist ">
				<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
					<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
				</a>
				<div class="in" id="ityewu">
					<li class="active"><a href="../cg/cg_index.html"> <i
							class="iconfont">&#xe617;</i> <label>采购管理</label>
					</a></li>
					<li><a href="../sell/sell_apply.html"> <i class="iconfont">&#xe615;</i>
							<label>销售管理</label>
					</a></li>
					<li><a> <i class="iconfont">&#xe614;</i> <label>其他业务</label>
					</a></li>
					<li><a> <i class="iconfont">&#xe618;</i> <label>质控管理</label>
					</a></li>
				</div>
				<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
					<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
				</a>
				<div class="in" id="itdangan">
					<li><a href="../file_nc/client.html"> <i class="iconfont">&#xe617;</i>
							<label>NC档案</label>
					</a></li>
					<li><a href="../file_jil/car.html"> <i class="iconfont">&#xe617;</i>
							<label>计量档案</label>
					</a></li>
					<li><a href="../file-other/car.html"> <i class="iconfont">&#xe617;</i>
							<label>其他档案</label>
					</a></li>
				</div>
			</ul>
		</div>
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
				<li>采购申请单</li>
				<li>到货通知单</li>
				<li>退货通知单</li>
				<li class="select">采购车辆状态</li>
				<li>采购划价单</li>
			</ul>
			<!--tab切换标题end-->
			<div class="top_opera">
				<a><i class="iconfont">&#xe605;</i></a> <a><i class="iconfont">&#xe606;</i></a>
				<a><i class="iconfont">&#xe607;</i></a>
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
								<label>卡面编号：</label> <input id="cardcode" type="text">
							</div>
							<div class="intel_solo">
								<label>卡状态：</label> <select id="cardstatus" class="form-control">
									<option value="">请选择</option>
									<option value="0">无效</option>
									<option value="1">有效</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>卡类型：</label> <select id="cardtype" class="form-control">
									<option value="">请选择</option>
									<option value="0">IC采样卡</option>
									<option value="1">IC过磅卡</option>
								</select>
							</div>
							<div class="intel_solo">
								<div class="intel_sbtn">
									<button id="searchBtn" class="btn btnblue">搜索</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="intel_opera">
					<div id="refreshBtn" class="intel_operasolo">
						<i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
					</div>
					<div id="addBtn" class="intel_operasolo">
						<i class="iconfont colorlv">&#xe627;</i> <span>新增</span>
					</div>
				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>单据编号</th>
								<th>卡序号</th>
								<th>卡面编号</th>
								<th>卡状态</th>
								<th>卡类型</th>
								<th>注册时间</th>
								<th>登记人</th>
								<th>备注</th>
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
		<!--新增begin-->
		<div class="modal fade" id="addCardView" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>IC卡注册管理-新增</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_opera mb20">
							<ul>
								<li id="add_reset"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="add_save"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="add_read"><i class="iconfont colorblue">&#xe601;</i>
									<h5>读卡</h5></li>
							</ul>
						</div>
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>单据编号：</label>
								<input id="add_code" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>IC卡序号：</label>
								<input id="add_cardno" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>卡面编号：</label>
								<input id="add_cardcode" type="text">
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>登记人：</label>
								<input id="add_registrar" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>注册时间：</label>
								<input id="add_createtime" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>卡类型：</label>
								<select id="add_cardtype" class="form-control">
									<option value="">请选择</option>
									<option value="0">IC采购卡</option>
									<option value="1">IC过磅卡</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label><em class="colorred">*</em>有效性：</label>
								<input id="add_cardstatus" type="checkbox" checked><span>有效</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea id="add_remarks" class="form-control" rows="1"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--新增end-->
		<!--编辑begin-->
		<div class="modal fade" id="updateCardView" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 750px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>IC卡注册管理-修改</h5>
						</div>
					</div>
					<div class="modal-body">
						<div class="alt_opera mb20">
							<ul>
								<li id="update_reset"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="update_save"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>保存</h5></li>
								<li id="update_read"><i class="iconfont colorblue">&#xe601;</i>
									<h5>读卡</h5></li>
							</ul>
						</div>
						<input id="update_id" type="hidden">
						<div class="alt_edit">
							<div class="alt_edit_div">
								<label>单据编号：</label>
								<input id="update_code" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>IC卡序号：</label>
								<input id="update_cardno" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>卡面编号：</label>
								<input id="update_cardcode" type="text">
							</div>
							<div class="alt_edit_div">
								<label>登记人：</label>
								<input id="update_registrar" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>注册时间：</label>
								<input id="update_createtime" type="text" readonly>
							</div>
							<div class="alt_edit_div">
								<label>卡类型：</label>
								<select id="update_cardtype" class="form-control">
									<option value="">请选择</option>
									<option value="0">IC采购卡</option>
									<option value="1">IC过磅卡</option>
								</select>
							</div>
							<div class="alt_edit_div">
								<label>有效性：</label>
								<input id="update_cardstatus" type="checkbox"><span>有效</span>
							</div>
							<div class="alt_edit_textarea">
								<label>备注： </label>
								<textarea id="update_remarks" class="form-control" rows="1"></textarea>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--编辑end-->
		<!--删除begin-->
		<div class="modal fade" id="delCardView" tabindex="-1" role="dialog"
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
							<img src="/resources/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
						</div>
					</div>
					<div class="modal-footer">
						<button id="deteleCardBtn" type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--删除end-->
		<script type="text/javascript" src="/resources/js/jquery-1.11.1.js"></script>
		<script type="text/javascript"
			src="/resources/js/jquery.pagination.js"></script>
		<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
		<script type="text/javascript" src="/resources/js/layer/layer.js"></script>
		<script type="text/javascript"
			src="/resources/js/businessManage/cardManage/card.js"></script>
</body>
</html>