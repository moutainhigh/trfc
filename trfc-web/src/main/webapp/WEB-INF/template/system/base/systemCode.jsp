<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${basePath }/css/bootstrap.css" rel="stylesheet">
<link href="${basePath }/css/base.css" rel="stylesheet">
<link href="${basePath }/css/style.css" rel="stylesheet">
<link href="${basePath }/css/iconfont.css" rel="stylesheet">
<link href="${basePath }/css/pagination.css" rel="stylesheet">
<link href="${basePath }/css/jquery-confirm.css" rel="stylesheet">
<!--这个日历控件js必须放头部-->
<script language="javascript" type="text/javascript"
	src="${basePath }/js/My97DatePicker/WdatePicker.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<div class="left ">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
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
				<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit"
					onclick=""> <label>业务管理</label> <span><i
						class="iconfont">&#xe604;</i></span>
				</a>

				<div class="in" id="ityewu">
					<li><a href="${basePath }/cg/cg_index.html"> <i
							class="iconfont">&#xe617;</i> <label>采购管理</label>
					</a></li>
					<li><a href="${basePath }/sell/sell_apply.html"> <i
							class="iconfont">&#xe615;</i> <label>销售管理</label>
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
					<li><a href="${basePath }/file_nc/client.html"> <i
							class="iconfont">&#xe617;</i> <label>NC档案</label>
					</a></li>
					<li><a href="${basePath }/file_jil/car.html"> <i
							class="iconfont">&#xe617;</i> <label>计量档案</label>
					</a></li>
					<li><a href="${basePath }/file-other/car.html"> <i
							class="iconfont">&#xe617;</i> <label>其他档案</label>
					</a></li>
				</div>
				<a href="#sys" data-toggle="collapse" class="menu_collap_tit"> <label>系统设置</label>
					<span><i class="iconfont">&#xe604;</i></span>
				</a>

				<div class="in" id="sys">
					<li><a href="#"> <i class="iconfont">&#xe617;</i> <label>系统权限</label>
					</a></li>
					<li class="active"><a
						href="${basePath }/system-yewu/bianhao.html"> <i
							class="iconfont">&#xe617;</i> <label>系统业务</label>
					</a></li>
				</div>
			</ul>
		</div>
	</div>
	<div class="leftmini hide">
		<div class="user">
			<a href="#" data-toggle="dropdown" data-target="#menu-messages"
				class="dropdown-toggle"> <img src="${basePath }/images/tx.jpg"
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
			<ul class="intel_menu">
				<li class="select"><a href="bianhao.html">自定义编号</a></li>
				<li><a href="bianhao.html">辅助资料</a></li>
			</ul>
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
			<!--自定义编号begin-->
			<div class="intel_tabcont">
				<div class="intel_opera">
					<div class="intel_operasolo" id="systemCode_refresh">
						<i class="iconfont colorlv">&#xe61b;</i>
						<h5>刷新</h5>
					</div>
					<div class="intel_operasolo" id="systemCode_add">
						<a data-toggle="modal" data-target="#add"> 
						<i class="iconfont coloradd">&#xe627;</i>
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
							<tr>
								<td>1</td>
								<td>tts</td>
								<td>zzz</td>
								<td></td>
								<td>-</td>
								<td>001</td>
								<td>00001</td>
								<td>zdfg0000</td>
								<td><span> <a data-toggle="modal"
										data-target="#edit"><i class="iconfont"
											data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
								</span></td>
							</tr>
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
					<button type="button" class="btn btn-default" data-dismiss="modal" id="systemCode_edit_cancle">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!--编辑end-->
	<!--新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
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
                <button type="button" class="btn btn-primary" id="systemCode_add_ensure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="systemCode_add_cancle">取消</button>
            </div>
        </div>
    </div>
</div>
	<!-- 新增end -->
<!--删除begin-->
<div class="modal fade" id="dele" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <div class="alert_qf">
                    <img src="${basePath }/images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="systemCode_delete_ensure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--删除end-->
	<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="${basePath }/js/bootstrap.js"></script>
	<%-- <script type="text/javascript" src="${basePath }/js/myself.js"></script> --%>
	<script type="text/javascript" src="${basePath }/js/layer/layer.js"></script>
	<script type="text/javascript"
		src="${basePath }/js/system/base/systemCode.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>