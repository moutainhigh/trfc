<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售批号维护</title>
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>

	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<jsp:include page="../../common/base/right_head_busi.jsp"></jsp:include>
			<div class="intel_tab">
				<!--tab切换标题-->
				<ul class="intel_menu">
					<li class="select">销售批号维护</li>
					<li>销售化验报告</li>
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
									<label>开始时间：</label> <input type="text" readonly="true"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text" readonly="true"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
										class="Wdate" style="width: 160px" />
								</div>
								<div class="intel_solo">
									<label>单据来源：</label> <select class="form-control">
										<option>名称</option>
										<option>内码</option>
										<option>拼音码</option>
									</select>
								</div>
								<div class="intel_solo">
									<label>订单号：</label> <input type="text">
								</div>
								<div class="intel_solo">
									<label>客户：</label> <a href="javascript:;" class="toggle"
										id="menu-toggle1"> <input type="text">
									</a>
									
								</div>
								<div class="intel_solo">
									<button class="btn btnblue ">搜索</button>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo">
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo">
							<a href=" "> <i class="iconfont coloradd">&#xe627;</i>
								<h5>新增</h5>
							</a>
						</div>

					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>行号</th>
									<th>单据编号</th>
									<th>审核状态</th>
									<th>单据状态</th>
									<th>化验状态</th>
									<th>物料名称</th>
									<th>批号</th>
									<th>数量</th>
									<th>过磅量</th>
									<th>余量</th>
									<th>化验日期</th>
									<th>化验人</th>
									<th>开始时间</th>
									<th>结束时间</th>
									<th>审核时间</th>
									<th>审核人</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td class="colorred">审核中</td>
									<td class="colorblue">客商APP</td>
									<td class="colorred">未化验</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td><span> <a data-toggle="modal"
											data-target="#dele"><i class="iconfont"
												data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="停用">&#xe624;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="审核">&#xe692;</i></a>
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="反审">&#xe651;</i></a>
									</span> </span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
									</span></td>
								</tr>
							</tbody>
						</table>
						<!--用户表格end-->
					</div>

					<!--分页效果开始-->
					<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input type="text"> <label>页</label>
							<button class="btn btn-default">确定</button>
						</div>
						<div class="page_btn">
							<ul class="pagination">
								<li><a href="#">&laquo;上一页</a></li>
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">...</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">下一页&raquo;</a></li>
							</ul>
						</div>
					</div>
					<!--分页效果结束-->
				</div>
				<!--采购申请单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">2</div>
				<!--到货通知单end-->

				<!--退货通知单begin-->
				<div class="intel_tabcont hide">3</div>
				<!--退货通知单end-->

				<!--到货通知单begin-->
				<div class="intel_tabcont hide">4</div>
				<!--到货通知单end-->
				<!--到货通知单begin-->
				<div class="intel_tabcont hide">5</div>
				<!--到货通知单end-->
				<!--tab切换的内容end-->
			</div>
		</div>
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
							<img src="images/tishi.png"><label>注：删除操作不可恢复，您确定要继续么？</label>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary">确定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
		<!--删除end-->
		<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="../js/bootstrap.js"></script>
		<script type="text/javascript" src="../js/myself.js"></script>
		<script>$('[data-menu]').menu();</script>
		<script type="text/javascript">
    // 顶部tab切换菜单
    var $tab_li = $('.intel_menu li');
    $tab_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = $tab_li.index(this);
        $('.intel_tabbox > .intel_tabcont').eq(index).show().siblings().hide();
    });
    // 表格内容每行单击出来下面的详细信息
    var tabledata = $('.intel_table table tbody tr');
    tabledata.on("click", function () {
        $(".intel_result").css("display", "block");
    })
    // 表格内容每行双击出来下面的详细信息
    tabledata.on("dblclick", function () {
        $('#caigoubill').modal('show');
    })
    // 首页底部的tab切换菜单
    var ind_li = $('#ind_tab ul li');
    ind_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_li = cg_li.index(this);
        $('#ind_tab .cg_tabbox > .cg_tabcont').eq(index_li).show().siblings().hide();
    });

    // 弹出信息的tab切换菜单
    var alt_li = $('#alt_tab ul li');
    alt_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_alt = alt_li.index(this);
        $('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
    });


</script>
</body>
</html>