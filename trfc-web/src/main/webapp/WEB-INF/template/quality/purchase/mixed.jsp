<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购采混样管理</title>
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
									<label>日期：</label> <input type="text"
										onfocus="WdatePicker({maxDate:'%y-%M-\#{%d-1}',dchanging:function(){
								            document.getElementById('supplier').value='';
							            document.getElementById('supplier').removeAttribute('supplierid');
							            document.getElementById('material').value='';
							            document.getElementById('material').removeAttribute('materialid');
							            document.getElementById('minemouth').value='';
							            document.getElementById('minemouth').removeAttribute('minemouthid');
							        }})"
										class="Wdate" style="width: 160px" id="date"
										placeholder="请选择日期" value="${yesterday }"/>
								</div>
								<div class="intel_solo">
									<label>供应商：</label> <input type="text" id="supplier"
										placeholder="请选择供应商">
								</div>
								<div class="intel_solo">
									<label>物料：</label> <input type="text" id="material"
										placeholder="请选择物料">
								</div>
								<div class="intel_solo">
									<label>矿口：</label> <input type="text" id="minemouth"
										placeholder="请选择矿口">
								</div>
								<div class="intel_solo">
									<button class="btn btnblue" id="search">搜索</button>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="intel_opera">
						<!-- <div class="intel_operasolo" id="refresh">
							<a> <i class="iconfont colorlv">&#xe61b;</i> <span>刷新</span>
							</a>
						</div>
						<div class="intel_operasolo" id="mixed">
							<a> <i class="iconfont coloradd">&#xe627;</i> <span>混样</span>
							</a>
						</div> -->
					</div>
					<div class="intel_table">
						<!--用户表格begin-->
						<table class="table table-hover maintable">
							<thead>
								<tr>
									<th></th>
									<th>单据编号</th>
									<th>采样日期</th>
									<th>化验类型</th>
									<th>制单人</th>
									<th>制单日期</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody id="list">
							</tbody>
						</table>
						<!--用户表格end-->
					</div>
                    <div class="intel_result">
                        <div class="cg_tabbox">
                            <!--tab切换的内容-->
                            <div class="cg_tabcont">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>采样单号</th>
                                            <th>派车单号</th>
                                            <th>供应商</th>
                                            <th>物料</th>
                                            <th>矿点</th>
                                            <th>车辆</th>
                                            <th>备注</th>
                                        </tr>
                                    </thead>
                                    <tbody id="detail_list">
                                    </tbody>
                                </table>
                            </div>
                            <!--tab切换的内容end-->
                        </div>
                    </div>
				</div>
				<!--tab切换的内容end-->
			</div>
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/purchase/mixed.js"></script>
</body>
</html>
