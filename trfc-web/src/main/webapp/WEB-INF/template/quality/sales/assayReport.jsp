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
							<i class="iconfont colorlv">&#xe61b;</i>
							<h5>刷新</h5>
						</div>
						<div class="intel_operasolo" id="addBtn">
							<a> <i class="iconfont coloradd">&#xe627;</i>
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
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="list">
								<tr>
									<td>1</td>
									<td>zg522121233</td>
									<td class="colorred">审核中</td>
									<td class="colorred">否</td>
									<td>未化验</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>28</td>
									<td>admin</td>
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
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
									</span></td>
								</tr>
								<tr>
									<td>1</td>
									<td>zg522121233</td>
									<td class="colorred">待审核</td>
									<td class="colorred">否</td>
									<td>未化验</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>28天</td>
									<td>admin</td>
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
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
												class="iconfont" data-toggle="tooltip" data-placement="left"
												title="复制">&#xe61c;</i></a>
									</span></td>
								</tr>
								<tr>
									<td>1</td>
									<td>zg522121233</td>
									<td>审核中</td>
									<td class="colorred">否</td>
									<td>未化验</td>
									<td>未入厂</td>
									<td>粉煤灰1</td>
									<td>粉煤灰1</td>
									<td>未入厂</td>
									<td>未入厂</td>
									<td>28天</td>
									<td>admin</td>
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
									</span> <span> <a data-toggle="modal" data-target="#dele"><i
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
