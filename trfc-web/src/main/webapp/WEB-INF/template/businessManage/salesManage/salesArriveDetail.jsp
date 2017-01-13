<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售提货通知单详情</title>
<meta name="keywords" content=" 天瑞" />
<meta name="description" content="">
<meta name="author" content="">
<link href="${basePath }/css/bootstrap.css" rel="stylesheet">
<link href="${basePath }/css/base.css" rel="stylesheet">
<link href="${basePath }/css/style.css" rel="stylesheet">
</head>
<body>
	<div class="it_admin">
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
					<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
						<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
					</a>

					<div class="in" id="ityewu">
						<li class="active"><a href="../cg/cg_index.html"> <i
								class="iconfont">&#xe617;</i> <label>采购管理</label>
						</a></li>
						<li><a href="../sell/sell_apply.html"> <i
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
					title="Tooltip on left"><i class="iconfont">&#xe617;</i></li>
				<li><i class="iconfont">&#xe615;</i></li>
				<li><i class="iconfont">&#xe614;</i></li>
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
					<li class="select">销售申请单</li>
					<li>提货通知单</li>
					<li>销售车辆状态</li>
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
				<div class="intel_tabcont hide"></div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li id="refreshBtn"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="readCardBtn"><i class="iconfont colorblue">&#xe61d;</i>
									<h5>读卡</h5></li>
								<li id="backBtn"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>提货通知单详细信息</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>订单编号：</label> <input value="${salesArrive.billcode }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input value="${salesArrive.code }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input
									value="${salesArrive.createtimeStr }" type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>客户：</label> <input
									value="${salesArrive.salesApplication.customerManageResp.name }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>区域码：</label> <input
									value="${salesArrive.salesApplication.customerManageResp.channelcode }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>销售组织：</label> <input value="${salesArrive.salesApplication.orgname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input value="${salesArrive.salesApplication.detailResp.materiel.name }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>部门：</label> <input value="${salesArrive.salesApplication.departmentname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>单位：</label> <input value="${salesArrive.salesApplication.detailResp.unit }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>订单数量：</label> <input value="${salesArrive.salesApplication.detailResp.salessum }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input value="${salesArrive.vehicle.vehicleno }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>RFID：</label> <input value="${salesArrive.vehicle.rfid }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input value="${salesArrive.driver.name }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>身份证号：</label> <input value="${salesArrive.driver.identityno }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>提货量：</label> <input value="${salesArrive.takeamount }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>业务日期：</label> <input value="${salesArrive.salesApplication.createtimeStr }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input value="${salesArrive.creatorname }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input value="${salesArrive.remarks }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="s" value="${salesArrive.status }"></c:set>
								<label>状态：</label> <input value="${s eq '0' ? '未入厂' : s eq '1' ? '空车' : s eq '2' ? '重车' : s eq '3' ? '作废' : s eq '4' ? '发卡' : s eq '5' ? '出厂' : s eq '6' ? '入厂' : s eq '7' ? '装车' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<c:set var="sc" value="${salesArrive.source }"></c:set>
								<label>来源：</label> <input value="${sc eq '0' ? '业务平台' : sc eq '1' ? '客商平台' : sc eq '2' ? '客商APP' : '' }"
									type="text" readonly>
							</div>
							<div class="daohuo_add_solo">
								<label>作废/出场时间：</label> <input value="${salesArrive.abnormaltimeStr }"
									type="text" readonly>
							</div>
						</div>
					</div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">订单明细</li>
									<li>磅单明细</li>
									<li>读卡信息</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<div class="cg_tabcont">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>订单编号</th>
											<th>客户</th>
											<th>物料</th>
											<th>组织机构</th>
											<th>预提量</th>
											<th>订单量</th>
											<th>订单日期</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>${salesArrive.salesApplication.code }</td>
											<td>${salesArrive.salesApplication.customerManageResp.name }</td>
											<td>${salesArrive.salesApplication.detailResp.materiel.name }</td>
											<td>${salesArrive.salesApplication.orgname }</td>
											<td>${salesArrive.takeamount }</td>
											<td>${salesArrive.salesApplication.detailResp.salessum }</td>
											<td>${salesArrive.salesApplication.billtimeStr }</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="cg_tabcont hide"></div>
							<div class="cg_tabcont hide"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="${basePath }/js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="${basePath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${basePath }/js/layer/layer.js"></script>
		<script type="text/javascript"
			src="${basePath }/js/businessManage/salesManage/salesArriveDetail.js"></script>
		<script type="text/javascript">
			// 录入、参照tab切换菜单
			var cg_li = $('.cg_tabtit ul li');
			cg_li.click(function() {
				$(this).addClass('select').siblings().removeClass('select');
				var index = cg_li.index(this);
				$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings()
						.hide();
			});
		</script>
</body>
</html>