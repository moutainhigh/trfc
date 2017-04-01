<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售化验报告-新增</title>
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
					<li class="select">销售化验报告-详情</li>
				</ul>
			</div>
    <!--tab切换的内容-->
    <div class="intel_tabbox">
        <!--采购申请单begin-->
        <div class="intel_tabcont hide">
        </div>
        <!--采购申请单end-->

        <!--到货通知单begin-->
        <div class="intel_tabcont ">
            <div class="intel_search">
                <div class="intel_bggray">
                    <div class="intel_bgblue"></div>
                </div>
                <div class="alt_opera">
							<ul>
								<li id="fresh"><i class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
								<li id="goBack"><a> <i class="iconfont colorblue">&#xe61e;</i>
										<h5>返回</h5>
								</a></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>化验报告单编辑</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>单据编号：</label> <input
									type="text" readonly="readonly" value="011" id="detail_code">
								<input type="checkbox" id="detail_pstate"> <em>打印</em>
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>批号：</label>
								<div class="input_withlogo">
									<input type="text" readonly="readonly" data-toggle="modal" data-target="#altbill"
										id="detail_batchcode"> <span
										class="form-control-feedback"><i class="iconfont">&#xe608;</i></span>
								</div>
							</div>
							<div class="daohuo_add_solo">
								<label>生产日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="detail_producedtime" />
							</div>
							<div class="daohuo_add_solo">
								<label>试验日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="detail_testtime" />
							</div>
							<div class="daohuo_add_solo">
								<label><em class="colorred">*</em>水泥品种：</label> <input
									type="text" id="detail_materialtype" readonly="readonly">
							</div>
							<div class="daohuo_add_solo">
								<label>强度等级：</label> <input type="text" readonly="readonly"
									id="detail_strength">
							</div>
							<div class="daohuo_add_solo">
								<label>混合材品种：</label> <input type="text" readonly="readonly"
									id="detail_admixture">
							</div>
							<div class="daohuo_add_solo">
								<label>混合材掺加量：</label> <input type="text" readonly="readonly"
									id="detail_admixtureadd">
							</div>
							<div class="daohuo_add_solo">
								<label>石膏种类：</label> <input type="text" readonly="readonly"
									id="detail_gypsum">
							</div>
							<div class="daohuo_add_solo">
								<label>石膏掺加量：</label> <input type="text" readonly="readonly"
									id="detail_gypsumadd">
							</div>
							<div class="daohuo_add_solo">
								<label>助磨剂种类：</label> <input type="text" readonly="readonly"
									id="detail_aid">
							</div>
							<div class="daohuo_add_solo">
								<label>助磨剂掺加量：</label> <input type="text" readonly="readonly"
									id="detail_aidadd">
							</div>
							<div class="daohuo_add_solo">
								<label>质检方案：</label> <input type="text" readonly="readonly" id="detail_qscheme">
							</div>
							<div class="daohuo_add_solo">
								<label>报告单位：</label> <input type="text" readonly="readonly"
									id="detail_reportorg">
							</div>
							<div class="daohuo_add_solo">
								<label>报告人：</label> <input type="text" readonly="readonly" id="detail_reporter">
							</div>
							<div class="daohuo_add_solo">
								<label>地址：</label> <input type="text" readonly="readonly" id="detail_addr">
							</div>
							<div class="daohuo_add_solo">
								<label>制单日期：</label> <input type="text" readonly="readonly"
									id="detail_creattime">
							</div>
							<div class="daohuo_add_solo">
								<label>制单人：</label> <input type="text" readonly="readonly"
									id="detail_creator">
							</div>
							<div class="daohuo_add_solo">
								<label>销售日期：</label> <input type="text" readonly="readonly"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:00:00'})"
									class="Wdate" id="detail_selldate" />
							</div>
							<div class="daohuo_add_solo">
								<label>备注：</label> <input type="text" readonly="readonly" id="detail_remark">
							</div>
						</div>
					</div>
            <div class="clear"></div>
            <div>
                <div class="cg_dhadd">
                    <div class="cg_tabtit">
                        <ul>
                            <li>检验明细</li>
                            <li class="select">审核信息</li>
                        </ul>
                    </div>
                </div>
                <div class="cg_tabbox">
                    <!--tab切换的内容-->
                    <div class="cg_tabcont hide">
                        <div class="total_addscroll">
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>行号</th>
                                    <th>品种指标</th>
                                    <th>单位</th>
                                    <th>标准值</th>
                                    <th>检测值</th>
                                </tr>
                                </thead>
                                <tbody id="detail_list">
                                <tr>
                                   
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="cg_tabcont">
                        <ul class="cg_hjprogress" id="recordlist">
                           
                        </ul>
                        <div id="auditDiv">
                            <h4>审批意见：</h4>
                            <textarea class="form-control" rows="1" id="record"></textarea>
                            <div>
                                <button class="btn" id="sureBtn">批准</button>
                                <button class="btn" id="notSureBtn">不同意</button>
                                <button class="btn" id="constraintBtn">强制批准</button>
                            </div>
                        </div>
                    </div>
                    <!--tab切换的内容end-->
                </div>
            </div>
        </div>


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
<!--订单号弹出begin-->
	<!-- 引用公共footer部分 -->
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/quality/sales/assayReport_detail.js"></script>
<script type="text/javascript">

    // 录入、参照tab切换菜单
    var cg_li = $('.cg_tabtit ul li');
    cg_li.click(function () {
        $(this).addClass('select').siblings().removeClass('select');
        var index = cg_li.index(this);
        $('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
    });

</script>
</body>
</html>