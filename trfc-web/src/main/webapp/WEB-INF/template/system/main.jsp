<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<!-- 引用公共header部分 -->
<jsp:include page="../common/base/header_busi.jsp"></jsp:include>
</head>
<body>
	<div class="it_admin">
		<!-- 引用公共left部分 -->
		<jsp:include page="../common/base/left_busi.jsp"></jsp:include>
		<div class="right">
			<!-- 引用公共right部分 -->
			<jsp:include page="../common/base/right_head_busi.jsp"></jsp:include>
			<!--tab切换的内容-->
			<div class="intel_tabbox">
				<div class="wel_right">
					<!--导航条begin-->
					<div class="welcome">
						<img src="${staticBasePath}/images/login.png">
						<div class="wel_admin">
							<p>您好，${name }</p>
							<p>欢迎登陆天瑞信科厂区智能管理平台</p>
						</div>
						<div class="wel_info">
							<p>
								<label>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</label><span>${name }</span>
							</p>
							<p>
								<label>手机号：</label><span>${phone }</span>
							<p>
							<p>
								<label>上次登陆时间：</label><span>${nowTime }</span>
							<p>
							<p>
								<label>所属部门：</label><span>${orgName }</span>
							<p>
						</div>
					</div>
					<!--导航条end-->
				</div>
			</div>
		</div>
	</div>
	<!-- 引用公共footer部分 -->
	<jsp:include page="../common/base/footer_busi.jsp"></jsp:include>
</body>
</html>