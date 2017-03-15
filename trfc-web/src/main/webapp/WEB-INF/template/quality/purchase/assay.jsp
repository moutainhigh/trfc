<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>采购采样管理</title>
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
					<li><a href="/trfc/quality/sales/batchnum/main">销售批号维护</a></li>
					<li><a href="/trfc/quality/sales/report/main">销售化验报告</a></li>
					<li><a href="/trfc/quality/purchase/sampling/main">采购采样报告</a></li>
					<li class="select">采购化验管理</li>
				</ul>
    <!--tab切换标题end-->
    <div class="top_opera">
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="首页">&#xe605;</i></a>
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="控制面板">&#xe606;</i></a>
        <a><i class="iconfont" data-toggle="tooltip" data-placement="left" title="退出">&#xe607;</i></a>
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
                <label>开始时间：</label>
                <input type="text" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
                       style="width:160px" id="seek_starttime"/>
            </div>
            <div class="intel_solo">
                <label>结束时间：</label>
                <input type="text" readonly="true" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate"
                       style="width:160px" id="seek_endtime"/>
            </div>
            <div class="intel_solo">
                <label>单据编号：</label>
                <input type="text" id="seek_code">
            </div>
            <div class="intel_solo">
                    <button class="btn btnblue " id="seek">搜索</button>
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
    <div class="intel_operasolo">
        <a  data-toggle="modal" data-target="#add" id="addBtn">
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
            <th>行号</th>
            <th>单据编号</th>
            <th>质检产品方案</th>
            <th>采样单号</th>
            <th>化验日期</th>
            <th>烧失量</th>
            <th>质量系数</th>
            <th>水分</th>
            <th>外水分</th>
            <th>分析基水分</th>
            <th>分析基灰分</th>
            <th>分析基挥发分</th>
            <th>固定碳</th>
            <th>焦渣特征</th>
            <th>空气干燥基全硫</th>
            <th>分析基低位发热量</th>
            <th>收到基低位发热量</th>
            <th>低位热量</th>
            <th>制单人</th>
            <th>制单日期</th>
            <th>备注</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="list">
        <tr>
            <td> 1</td>
            <td class="colorred">hy15215121212</td>
            <td class="colorblue">客商APP路砖渣</td>
            <td class="colorred">hy15215121212</td>
            <td>2016-02-01</td>
            <td>粉煤灰1</td>
            <td>粉煤灰1</td>
            <td>10</td>
            <td>10.23</td>
            <td>10.23</td>
            <td>10.23</td>
            <td>10.23</td>
            <td>10.23</td>
            <td>10.23</td>
            <td>10</td>
            <td>25648</td>
            <td>25648</td>
            <td>25648</td>
            <td>张二二</td>
            <td>2016-07-1216:45:21</td>
            <td>XX做</td>
            <td>
                <span >
                <a data-toggle="modal" data-target="#edit"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="编辑">&#xe600;</i></a>
            </span>
            <span >
                <a data-toggle="modal" data-target="#dele"><i class="iconfont" data-toggle="tooltip" data-placement="left" title="删除">&#xe63d;</i></a>
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
<!--采购申请单end-->

<!--到货通知单begin-->
<div class="intel_tabcont hide">
    2
</div>
<!--到货通知单end-->

<!--退货通知单begin-->
<div class="intel_tabcont hide">
    3
</div>
<!--退货通知单end-->

<!--到货通知单begin-->
<div class="intel_tabcont hide">
    4
</div>
<!--到货通知单end-->
<!--到货通知单begin-->
<div class="intel_tabcont hide">
    5
</div>
<!--到货通知单end-->
<!--tab切换的内容end-->
</div>
</div>

<!--新增begin-->
<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>物料方案新增</h5>
                </div>
            </div>
            <div class="modal-body">
                
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>质检产品：</label>
                        <input type="text" id="add_qscheme" class="qschemeSel">
                    </div>
                    <div class="alt_edit_div">
                        <label>单据编号：</label>
                        <input type="text" id="add_code">
                    </div>
                    <div class="alt_edit_div">
                        <label>采样单号：</label>
                        <input type="text" id="add_sampling">
                    </div>
                    <div class="alt_edit_div">
                        <label>化验日期：</label>
                        <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate_cg"
                               style="width:220px" id="add_assaytime"/>
                    </div>
                    <div class="alt_edit_div">
                        <label> 制单时间：</label>
                        <input type="text" id="add_createtime">
                    </div>
                    <div class="alt_edit_div">
                        <label>制单人：</label>
                        <input type="text" id="add_creator">
                    </div>
                    <div class="alt_edit_div">
                        <label>备注： </label>
                        <input type="text" id="add_remark">
                    </div>
                </div>
                <div id="alt_tab">
                    <div class="cg_tabtit">
                        <ul>
                            <li class="select">质检项目</li>
                            <li>采样项目</li>
                        </ul>
                    </div>
                    <div class="cg_tabbox">
                        <!--tab切换的内容-->
                        <div class="cg_tabcont">
                            <div class="alt_edit" id="add_qschemeitem">
                                <div class="alt_edit_div">
                                    <label>烧失量：</label>
                                    <input type="text">
                                </div>
                            </div>

                        </div>
                        <div class="cg_tabcont hide">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>采样编号</th>
                                    <th> 化验类型</th>
                                </tr>
                                </thead>
                                <tbody id="add_sampinglist">
                                <tr>
                                    <td> 合计：</td>
                                    <td> 100</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--tab切换的内容end-->
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="add_sure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="add_cancel">取消</button>
            </div>
        </div>
    </div>
</div>
<!--新增end-->
<!--编辑begin-->
<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width: 750px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <div class="alt_head">
                    <h5>化验管理</h5>
                </div>
            </div>
            <div class="modal-body">
                <div class="alt_opera mb10">
                    <ul>
                        <li>
                            <i class="iconfont colorlv">&#xe61b;</i>
                            <h5>刷新</h5>
                        </li>
                        <li>
                            <i class="iconfont colorblue">&#xe61d;</i>
                            <h5>保存</h5>
                        </li>
                    </ul>
                </div>
                <div class="alt_edit">
                    <div class="alt_edit_div">
                        <label>质检产品：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>单据编号：</label>
                        <input type="text" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>采样单号：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label>化验日期：</label>
                        <input type="text">
                    </div>
                    <div class="alt_edit_div">
                        <label> 制单时间：</label>
                        <input type="text" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>制单人：</label>
                        <input type="text" readonly="true">
                    </div>
                    <div class="alt_edit_div">
                        <label>备注： </label>
                        <input type="text">
                    </div>
                </div>
                <div id="alt_tab">
                    <div class="cg_tabtit">
                        <ul>
                            <li class="select">质检项目</li>
                            <li>采样项目</li>
                        </ul>
                    </div>
                    <div class="cg_tabbox">
                        <!--tab切换的内容-->
                        <div class="cg_tabcont">
                            <div class="alt_edit">
                                <div class="alt_edit_div">
                                    <label>外水分(Mar)：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>分析基水分Mad：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>分析基灰分Aad：</label>
                                    <input type="text">
                                </div>
                                <div class="alt_edit_div">
                                    <label>分析基挥发分Vad：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>焦渣特征CRC：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>固定碳Fcad：</label>
                                    <input type="text" value="121221" readonly="true">
                                </div>
                                <div class="alt_edit_div">
                                    <label>空气干燥基全硫St,ad：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>分析基低位发热量Qnet,ad：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>收到基低位发热量Qnet,ar：</label>
                                    <input type="text" value="121221">
                                </div>
                                <div class="alt_edit_div">
                                    <label>低位热量：</label>
                                    <input type="text" value="121221">
                                </div>
                            </div>
                        </div>
                        <div class="cg_tabcont hide">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>采样编号</th>
                                    <th> 化验类型</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td> 合计：</td>
                                    <td> 100</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!--tab切换的内容end-->
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--编辑end-->

<script type="text/javascript">
    // 表格内容每行单击出来下面的详细信息
    var tabledata = $('.intel_table table tbody tr');
    tabledata.on("click", function () {
        $(".intel_result").css("display", "block");
    })
    // 表格内容每行双击出来下面的详细信息
    tabledata.on("dblclick", function () {
        $('#caigoubill').modal('show');
    })
      // 弹出信息的tab切换菜单
    var alt_li = $('#alt_tab .cg_tabtit ul li');
    alt_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index_alt = alt_li.index(this);
        $('#alt_tab .cg_tabbox > .cg_tabcont').eq(index_alt).show().siblings().hide();
    });

</script>
<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
		<script type="text/javascript"
			src="/javascript/quality/purchase/assay.js"></script>
</div>
</body>
</html>
