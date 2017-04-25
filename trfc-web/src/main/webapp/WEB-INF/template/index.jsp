<%@ page language="java"  pageEncoding="UTF-8"%>
<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>index</title>
    <meta name="keywords" content=" 天瑞"/>
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="${staticBasePath}/css/bootstrap.css" rel="stylesheet">
    <link href="${staticBasePath}/css/base.css" rel="stylesheet">
    <link href="${staticBasePath}/css/style.css" rel="stylesheet">
    <link href="${staticBasePath}/css/iconfont.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
<!--Header-->
<div class="bghui">
    <div class="wrap">
        <!--登录头部行begin-->
        <div class="header">
            <label>欢迎来到天瑞物流！</label>
        </div>
        <!--登录头部行end-->
    </div>
</div>
<!--Header-->
<div class="wrap">
        <!--导航条begin-->
        <div class="login">
           <img src="${staticBasePath}/images/login.png">
            <div class="lgcont">
                <div class="lgleft">
                    <img src="${staticBasePath}/images/lgimg.png">
                </div>
                <div class="lgright">
                    <div class="lgline1">
                        <label>登录</label>
                    </div>
                    <div class="lgline3">
                        <i class="iconfont">&#xe622;</i>
                        <input type="text" placeholder="请输入手机号" id="user_account">
                    </div>
                    <div class="lgline3">
                        <i class="iconfont">&#xe633;</i>
                        <input type="password" placeholder="请输入密码" id="user_password">
                    </div>
                 
                    
                    <button class="btn" id="login_button">立即登录</button>
                </div>
            </div>
        </div>
        <!--导航条end-->
    </div>

<script type="text/javascript" src="${staticBasePath}/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${staticBasePath}/js/bootstrap.js"></script>
<script type="text/javascript" src="${staticBasePath}/js/cookie_util.js"></script>
<script type="text/javascript" src="${staticBasePath}/js/layer/layer.js"></script>
<script type="text/javascript" src="${staticBasePath}/js/md5.js"></script>
<script type="text/javascript" src="/javascript/index.js"></script>
</body>
</html>