<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>皮重异常审批</title>
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
			<input id="exceptionAuditId" value="${resp.id }" type="hidden" />
                <input id="type" value="${resp.type }" type="hidden"/>
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
								<c:if test="${!resp.auditStatus }">
									<li id="audit"><i class="iconfont colorlv">&#xe630;</i>
										<h5>审批</h5></li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="daohuo_add">
						<h5>皮重异常审批</h5>
						<div class="daohuo_add_div">
							<div class="daohuo_add_solo">
								<label>通知单号：</label> <input type="text"
									value="${resp.pnId }" readonly="true">
							</div>
							<div class="daohuo_add_solo">
								<label>流水号：</label> <input type="text"
									value="${resp.remark }" readonly="true">
							</div>
							<c:if test="${resp.auditStatus }">
								<div class="daohuo_add_solo">
									<label>审批人：</label> <input type="text"
										value="${resp.auditPersonName }" readonly="true">
								</div>
								<div class="daohuo_add_solo">
									<label>审批时间：</label> <input type="text"
										value="${resp.auditTimeStr }" readonly="true">
								</div>
								<div class="daohuo_add_solo" style="width: 100%">
									<label>审批意见：</label> <span>${empty resp.auditOpinion ? '无' : resp.auditOpinion }</span>
								</div>
							</c:if>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<!--到货通知单end-->
			<!--tab切换的内容end-->
		</div>
	</div>
	<jsp:include page="../../common/base/footer_busi.jsp"></jsp:include>
	<script type="text/javascript"
		src="/javascript/businessManage/examine/tareWeightAuditView.js"></script>
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