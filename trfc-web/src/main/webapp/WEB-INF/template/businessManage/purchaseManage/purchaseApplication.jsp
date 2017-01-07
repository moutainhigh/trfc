<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购管理</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/base.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">
<link href="/resources/css/pagination.css" rel="stylesheet">
<script language="javascript" type="text/javascript"
	src="/resources/js/My97DatePicker/WdatePicker.js"></script>
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
								<label>订单号：</label> <input id="billno" type="text">
							</div>
							<div class="intel_solo">
								<label>供应商：</label> <select id="supplierid" class="form-control">

								</select>
							</div>
							<div class="intel_solo">
								<label>单据来源：</label> <select id="source" class="form-control">
									<option value="">全部</option>
									<option value="0">联机</option>
									<option value="1">脱机</option>
								</select>
							</div>
							<div class="intel_solo">
								<label>创建时间：</label> <input id="starttime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" style="width: 160px" readonly /> <i>-</i> <input
									id="endtime" type="text"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
									class="Wdate" style="width: 160px" readonly />
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
				</div>
				<div class="intel_table">
					<!--用户表格begin-->
					<table class="table table-hover">
						<thead>
							<tr>
								<th>序号</th>
								<th>订单编号</th>
								<th>审核状态</th>
								<th>单据来源</th>
								<th>类型</th>
								<th>供应商</th>
								<th>订单日期</th>
								<th>部门</th>
								<th>采购员</th>
								<th>制单人</th>
								<th>制单日期</th>
								<th>审核人</th>
								<th>审核日期</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody id="dataBody">
						</tbody>
					</table>
					<!--用户表格end-->
				</div>
				<div id="dataMore" class="intel_result">
					<div class="cg_tabbox">
						<!--tab切换的内容-->
						<div class="cg_tabcont">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>采购组织</th>
										<th>物料</th>
										<th>物料规格</th>
										<th>物料类型</th>
										<th>数量</th>
										<th>入库占用量</th>
										<th>未入库占用量</th>
										<th>预提占用</th>
										<th>余量</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody id="moreBody">
								</tbody>
							</table>
						</div>
						<!--tab切换的内容end-->
					</div>
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
		<!--查看详情begin-->
		<div class="modal fade" id="dataDetail" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document" style="width: 900px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<div class="alt_head">
							<h5>采购申请单详细信息</h5>
							<img id="shImg" src="/resources/images/sh.png">
						</div>
					</div>
					<div class="modal-body">
						<div class="">
							<div class="cg_div">
								<div class="cg_solo">
									<label>单据编号：</label> <input id="_billno" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>单据来源：</label> <input id="_source" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>订单类型：</label> <input id="_typename" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>订单日期：</label> <input id="_applytime" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>供应商：</label> <input id="_suppliername" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>总数量：</label> <input id="_sumnum" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>采购员：</label> <input id="_buyername" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label>制单人： </label> <input id="_creator" type="text" readonly>
								</div>
								<div class="cg_solo">
									<label> 制单日期：</label> <input id="_creatortime" type="text" readonly>
								</div>
								<div class="cg_bz">
									<label>备注：</label> <input id="_remark" type="text" readonly>
								</div>
							</div>
							<div id="alt_tab">
								<div class="cg_tabtit">
									<ul>
										<li class="select">订单明细</li>
										<li>质检信息</li>
									</ul>
								</div>
								<div class="cg_tabbox">
									<!--tab切换的内容-->
									<div class="cg_tabcont">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>采购组织</th>
													<th>物料</th>
													<th>质检方案</th>
													<th>数量</th>
													<th>备注</th>
												</tr>
											</thead>
											<tbody id="detailtab1">
											</tbody>
										</table>
									</div>
									<div class="cg_tabcont hide">
										<table class="table table-bordered">
											<thead>
												<tr>
													<th>物料</th>
													<th>检验项目</th>
													<th>比较符</th>
													<th>下限</th>
													<th>比较符</th>
													<th>上限</th>
													<th>基准值</th>
													<th>浮动值</th>
													<th>扣价</th>
													<th>扣吨</th>
												</tr>
											</thead>
											<tbody id="detailtab2">
											</tbody>
										</table>
									</div>
									<!--tab切换的内容end-->
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<!--查看详情end-->
		<script type="text/javascript" src="/resources/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="/resources/js/jquery.pagination.js"></script>
		<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
		<script type="text/javascript" src="/resources/js/layer/layer.js"></script>
		<script type="text/javascript" src="/resources/js/businessManage/purchaseManage/purchaseApplication.js"></script>
		<script type="text/javascript">
			// 弹出信息的tab切换菜单
		    var alt_li = $('#alt_tab .cg_tabtit ul li');
		    alt_li.click(function () {
		        $(this).addClass('select').siblings().removeClass('select');
		        var index_alt = alt_li.index(this);
		        $('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
		    });
		</script>
</body>
</html>