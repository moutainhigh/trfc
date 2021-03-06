<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户退补</title>
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
                        <div class="intel_solo">
						<label>客户：</label> <input id="s_customer" type="text"
									placeholder="请选择客户">
						</div>
                        <!-- <div class="intel_solo">
                            <label>分支机构：</label>
                            <select id="branch" class="form-control">
                            	<option value="0">天瑞集团</option>
                            	<option value="1" selected="selected">天瑞集团汝州水泥有限公司</option>
                            </select>
                        </div> -->
                        <div class="intel_solo">
                            <label>制单人：</label>
                            <input type="text" placeholder="请输入制单人" id="makebillname">
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
               <!--  <div class="intel_operasolo" id="refreshBtn">
                    <a> <i class="iconfont colorlv">&#xe61b;</i>
                  		<span>刷新</span>
                    </a>
                </div>
                <div class="intel_operasolo" id="initAdd">
                    <a data-toggle="modal" data-target="#add">
                        <i class="iconfont coloradd">&#xe627;</i>
                        <span>新增</span>
                    </a>
                </div>
                <div id="audit" class="intel_operasolo">
                    <a> <i class="iconfont audit">&#xe692;</i>
                        <span>审核</span>
                    </a>
                </div>
                <div id="delete" class="intel_operasolo">
                    <a> <i class="iconfont delete">&#xe63d;</i>
                        <span>删除</span>
                    </a>
                </div> -->
            </div>
            <div class="intel_table">
                <!--用户表格begin-->
                <table class="table table-hover maintable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>单据编码</th>
                        <th>审批状态</th>
                        <th>单据日期</th>
                        <th>客户名称</th>
                        <th>收款类型</th>
                        <th>金额</th>
                        <th>收款单位</th>
                        <th>制单人</th>
                        <th>制单日期</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody id="begins">
                    <tr>
                        <td> CD201601010138</td>
                        <td>审核中</td>
                        <td>客商APP</td>
                        <td>未入厂</td>
                        <td>粉煤灰1</td>
                        <td>粉煤灰1</td>
                        <td>未入厂</td>
                        <td>粉煤灰1</td>
                        <td>粉煤灰1</td>
                        <td>粉煤灰1</td>
                        <td>粉煤灰1</td>
                        <td>
                <span>
                <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="审核">&#xe692;</i></a>
            </span>
            <span>
                <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip"
                                                              data-placement="left" title="删除">&#xe63d;</i></a>
            </span>
                        </td>
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
<div class="modal fade in" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 850px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>客户退补-新增</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>单据编号：</label>
                        <input type="text" value="121221" readonly="true" id="code">
                    </div>
                    <div class="alt_edit_div">
                        <label>客户名称：</label> <input id="a_customer" type="text"
						placeholder="请选择客户" />
                    </div>
                    <div class="alt_edit_div">
                        <label>单据日期：</label>
                        <input id="a_billtimeStr" type="text"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						class="Wdate_cg" readonly placeholder="请选择日期" style="width:220px">
                    </div>
                    <div class="alt_edit_div">
                        <label>收款类型：</label>
                        <select class="form-control" id="paymentmethod" >
                        	<option value="0">收款</option>
                        	<option value="1">退款</option>
                        </select>
                    </div>
                    <div class="alt_edit_div">
                        <label>金额：</label>
                        <input type="text" id="money" maxlength="11" check>
                    </div>
                    <div class="alt_edit_div" >
                        <label>金额大写：</label>
                        <input type="text"  readonly="readonly" id="money_capital">
                    </div>
                    <div class="alt_edit_div">
                        <label>收款单位：</label>
                        <select class="form-control" id="chargeunit">
                        	<option value="0">天瑞集团</option>
                        	<option value="1" selected="selected">天瑞集团汝州水泥有限公司</option>
                        </select>
                    </div>
                    <div class="alt_edit_div">
                        <label>制单人：</label>
                        <input type="text" readonly="readonly" id="maker">
                    </div>
                    <div class="alt_edit_div">
                        <label>制单日期：</label>
                        <input type="text" readonly="readonly" id="maketime">
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>备注：</label>
                        <input type="text" style="width: 500px" id="remark" >
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="ensureAdd">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->
<!--编辑begin-->
<!--编辑end-->
<!--删除begin-->

<!--详细begin-->
<div class="modal fade" id="caigoubill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 850px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>客户退补详细信息</h5>
                    <img src="${basePath }/images/un_sh.png" style="left:500px;display: none;" id="un_sh"/>
                    <img src="${basePath }/images/sh.png" style="left:500px;display: none;" id="sh"/>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>单据编号：</label>
                        <input type="text" value="121221" readonly="true" id="detail_code">
                    </div>
                    <div class="alt_edit_div">
                        <label> 客户名称：</label>
                        <input type="text" value="121221" readonly="true" id="detail_customer">
                    </div>
                    <div class="alt_edit_div">
                        <label>单据日期：</label>
                        <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})" class="Wdate_cg"
                               style="width:220px" id="detail_date" readonly="readonly"/>
                    </div>
                    <div class="alt_edit_div">
                        <label>收款类型：</label>
                        <input type="text" id="detail_method" readonly="readonly">
                    </div>
                    <div class="alt_edit_div">
                        <label>收款单位：</label>
                        <input type="text" value="121221" readonly="true" id="detail_unit">
                    </div>
                    <div class="alt_edit_div">
                        <label>金额：</label>
                        <input type="text" id="detail_money" readonly="readonly">
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>金额大写：</label>
                        <input type="text" style="width: 500px;" id="detail_capital" readonly="readonly">
                    </div>
                     <div class="alt_edit_div">
                        <label>制单人：</label>
                        <input type="text" id="detail_maker" readonly="readonly">
                    </div>
                    <div class="alt_edit_div">
                        <label>制单日期：</label>
                        <input type="text" id="detail_makebilltime" readonly="readonly">
                    </div>
                    <div class="alt_edit_div" style="width: 100%">
                        <label>备注：</label>
                        <input type="text" id="detail_remark" readonly="readonly" style="width: 500px;">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
        </div>
    </div>
</div>
<!--详细end-->

<jsp:include page="../../common/module/custom_choose.jsp"></jsp:include>
<!-- 引用公共footer部分 -->
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
<script type="text/javascript"
		src="/javascript/businessManage/financeManage/customerBack.js"></script>





</body>
</html>