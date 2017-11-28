<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>销售明细</title>
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
        <!--采购申请单begin-->
        <div class="intel_tabcont">
            <div class="intel_search">
                <div class="intel_bggray">
                    <div class="intel_bgblue"></div>
                </div>
                <div class="intel_sconditon">
                    <div class="intel_sline">
                        <div class="intel_solo">
							<label>开始时间：</label> <input id="s_starttime" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'s_endtime\')}'})"
								class="Wdate" style="width: 160px;" readonly
								placeholder="请选择开始时间" /> <i>-</i> <input id="s_endtime"
								type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'s_starttime\')}'})"
								class="Wdate" style="width: 160px;" readonly
								placeholder="请选择结束时间" />
						</div>
						<!-- <div class="intel_solo">
                            <label>分支机构：</label>
                            <select id="branch" class="form-control">
                            	<option value="0">天瑞集团</option>
                            	<option value="1" selected="selected">天瑞集团汝州水泥有限公司</option>
                            </select>
                        </div> -->
                        <div class="intel_solo">
                            <label>订单号：</label>
                            <input type="text" placeholder="请输入订单号" id="ordercode">
                        </div>
                        <div class="intel_solo">
                            <label>提货单号：</label>
                            <input type="text" placeholder="请输入提货单号" id="goodcode">
                        </div>
                        <div class="intel_solo">
						<label>客户：</label> <input id="s_customer" type="text"
									placeholder="请选择客户">
						</div>
                        <div class="intel_solo">
                            <div class="intel_sbtn">
                                <button class="btn btnblue " id="searchBtn">搜索</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="intel_opera">
                <!-- <div class="intel_operasolo" id="refreshBtn">
                    <a> <i class="iconfont colorlv">&#xe61b;</i>
                    	<span>刷新</span>
                   	</a>
                </div> -->
            </div>
            <div class="intel_table">
                <!--用户表格begin-->
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>单据编码</th>
                        <th>订单号</th>
                        <th>提货单号</th>
                        <th>客户名称</th>
                        <th>发货单位</th>
                        <th>单价</th>
                        <th>数量</th>
                        <th>金额</th>
                        <th>消费时间</th>
                    </tr>
                    </thead>
                    <tbody id="charges">
                    
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
		        <label>跳到第：</label>
		        <input type="text" id="jumpPageNo">
		        <label>页</label>
		        <button class="btn btn-default" id="jumpButton">确定</button>
		    </div>
		    <div class="page_date">
		        <label>每页记录：</label><select id="pageSize" class="form-control">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
				</select>
		    </div>
		    <div class="page_btn" id="pagination"></div>
		</div>
            <!--分页效果结束-->
        </div>
        <!--采购申请单end-->
        <!--tab切换的内容end-->
    </div>
</div>
</div>
<!--新增begin-->

<!--新增end-->
<!--编辑begin-->
<!--编辑end-->
<!--删除begin-->

<!--详细begin-->

<!--详细end-->

<jsp:include page="../../common/module/custom_choose.jsp"></jsp:include>
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
<script type="text/javascript"
		src="/javascript/businessManage/financeManage/salesDetail.js?1"></script>





</body>
</html>