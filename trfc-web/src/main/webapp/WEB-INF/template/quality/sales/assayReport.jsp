<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售化验报告</title>
<link href="${staticBasePath}/css/select2.css" rel="stylesheet">
<jsp:include page="../../common/base/header_busi.jsp"></jsp:include>
</head>
<body>

	<div class="it_admin">
		<jsp:include page="../../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
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
									<label>开始时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',maxDate:'#F{$dp.$D(\'seek_endtime\')}'})" class="Wdate"
										style="width: 160px" id="seek_starttime" placeholder="请选择开始时间"/>
								</div>
								<div class="intel_solo">
									<label>结束时间：</label> <input type="text"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00',minDate:'#F{$dp.$D(\'seek_starttime\')}'})" class="Wdate"
										style="width: 160px" id="seek_endtime" placeholder="请选择结束时间" />
								</div>
								<div class="intel_solo">
									<label>单据编号：</label> <input type="text" id="seek_code" placeholder="请输入单据编号">
								</div>
								<div class="intel_solo">
									<label>出厂批号：</label> <input type="text" id="seek_batchcode" placeholder="请输入出厂批号">
								</div>
								<div class="intel_solo">
									<label>报告天数：</label> <select class="form-control"
										id="seek_reportdays">
										<option value="">请选择</option>
										<option value="0">3天</option>
										<option value="1">28天</option>
									</select>
								</div>
								<div class="intel_solo">
									<button class="btn btnblue" id="seek">搜索</button>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<div class="intel_operasolo" id="fresh">
							<a> <i class="iconfont colorlv">&#xe61b;</i>
								<span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<a> <i class="iconfont coloradd">&#xe627;</i>
								<span>新增</span>
							</a>
						</div>
						<div id="update" class="intel_operasolo">
							<a> <i class="iconfont update">&#xe600;</i>
								<span>编辑</span>
							</a>
						</div>
						<div id="report" class="intel_operasolo">
							<a> <i class="iconfont report">&#xe610;</i>
								<span>28天报告</span>
							</a>
						</div>
						<div id="audit" class="intel_operasolo">
							<a> <i class="iconfont audit">&#xe692;</i>
								<span>审核</span>
							</a>
						</div>
						<div id="unaudit" class="intel_operasolo">
							<a> <i class="iconfont unaudit">&#xe651;</i>
								<span>反审</span>
							</a>
						</div>
						<div id="copy" class="intel_operasolo">
							<a> <i class="iconfont copy">&#xe61c;</i>
								<span>复制</span>
							</a>
						</div>
						<div id="delete" class="intel_operasolo">
							<a> <i class="iconfont delete">&#xe63d;</i>
								<span>删除</span>
							</a>
						</div>
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
							<thead>
								<tr>
									<th>行号</th>
									<th>单据编号</th>
									<th>审核状态</th>
									<th>打印状态</th>
									<th>水泥品种</th>
									<th>水泥名称</th>
									<th>批号</th>
									<th>生产日期</th>
									<th>试验日期</th>
									<th>销售日期</th>
									<th>报告天数</th>
									<th>报告人</th>
									<th>地址</th>
									<th>审核人</th>
									<th>审核时间</th>
								</tr>
							</thead>
							<tbody id="list">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
					<!--分页效果开始-->
					<div class=" row fr">
						<div class="page_date">
							<label>数据共：</label><i class="colorred" id="total">100</i><label>条</label>
						</div>
						<div class="page_date">
							<label>跳到第：</label> <input type="text" id="jumpPageNo"> <label>页</label>
							<button class="btn btn-default" id="jumpButton">确定</button>
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
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/sales/assayReport.js"></script>
</body>
</html>
