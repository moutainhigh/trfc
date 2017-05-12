<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购计量单详细信息</title>
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
				<!--到货通知单begin-->
				<div class="intel_tabcont ">
					<div class="intel_search">
						<div class="intel_bggray">
							<div class="intel_bgblue"></div>
						</div>
						<div class="alt_opera">
							<ul>
								<li onclick="window.location.reload();"><i
									class="iconfont colorlv">&#xe61b;</i>
									<h5>刷新</h5></li>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>采购计量单详细信息</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>过磅单号：</label> <input type="text"
									value="${poundNote.code }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>发货单位：</label> <input type="text"
									value="${poundNote.senddepartmentname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商：</label> <input type="text"
									value="${poundNote.suppliername }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>订单号：</label> <input type="text"
									value="${poundNote.billcode }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>物料：</label> <input type="text"
									value="${poundNote.materielname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>车号：</label> <input type="text"
									value="${poundNote.vehicleno }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>毛重：</label> <input type="text"
									value="${poundNote.grossweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>皮重：</label> <input type="text"
									value="${poundNote.tareweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>净重：</label> <input type="text"
									value="${poundNote.netweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>司机：</label> <input type="text"
									value="${poundNote.drivername }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>轻车时间：</label> <input type="text"
									value="${poundNote.lighttimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>重车时间：</label> <input type="text"
									value="${poundNote.weighttimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>到货通知单：</label> <input type="text"
									value="${poundNote.noticecode }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>收料员：</label> <input type="text"
									value="${poundNote.receiverpersonname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>收货时间：</label> <input type="text"
									value="${poundNote.receivertimeStr }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>原发数量：</label> <input type="text"
									value="${poundNote.originalnetweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>扣重：</label> <input type="text"
									value="${poundNote.deductionweight }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>扣杂：</label> <input type="text"
									value="${poundNote.deductionother }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>矿口：</label> <input type="text"
									value="${poundNote.minemouthname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>堆场：</label> <input type="text"
									value="${poundNote.yardname }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>供应商备注：</label> <input type="text"
									value="${poundNotesupplierremark.supplierremark }"
									readonly="true">
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div>
						<div class="cg_dhadd">
							<div class="cg_tabtit">
								<ul>
									<li class="select">门禁图片</li>
									<li>过磅图片</li>
								</ul>
							</div>
						</div>
						<div class="cg_tabbox">
							<!--tab切换的内容-->
							<div class="cg_tabcont">
								<c:if test="${ fn:length(accessImages) == 0}">
									<div class="noimg">
										<img alt="" src="${staticBasePath}/images/noimg.gif" />
									</div>
								</c:if>
								<c:forEach items="${ accessImages}" var="i">
									<img alt="" src=${ i.imgurl} />
								</c:forEach>
							</div>
							<div class="cg_tabcont hide">
								<c:if test="${ fn:length(poundImages) == 0}">
									<div class="noimg">
										<img alt="" src="${staticBasePath}/images/noimg.gif" />
									</div>
								</c:if>
								<c:forEach items="${ poundImages}" var="i">
									<img alt="" src=${ i.imgurl}>
								</c:forEach>
							</div>
							<!--tab切换的内容end-->
						</div>
					</div>
				</div>
			</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>
	</div>
	<script type="text/javascript">
		// 录入、参照tab切换菜单
		var cg_li = $('.cg_tabtit ul li');
		cg_li.click(function() {
			$(this).addClass('select').siblings().removeClass('select');
			var index = cg_li.index(this);
			$('.cg_tabbox > .cg_tabcont').eq(index).show().siblings().hide();
		});
	</script>
</body>
</html>