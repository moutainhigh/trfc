<%@ page language="java" pageEncoding="UTF-8"%>
<div class="left ">
	<div class="user" userid=${sessionScope.systemUser.id }>
		<a href="#" data-toggle="dropdown" data-target="#menu-messages"
			class="dropdown-toggle"> <img
			src="${staticBasePath}/images/tx.jpg" class="img-circle"> <label>${sessionScope.systemUser.name }</label>
		</a>
	</div>
	<div class="menu">
		<label>菜单</label> <i class="iconfont fr">&#xe61a;</i>
	</div>
	<div class="menu_collap">
		<ul class="typelist " id="menulist">
			<a href="#ityewu" data-toggle="collapse" class="menu_collap_tit">
				<label>业务管理</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="ityewu">
				
				<li><a href="/trfc/purchaseApplication/main"> <i
						class="iconfont">&#xe617;</i> <span>采购管理</span>
				</a></li>
				<li><a href="/trfc/salesApplication/main"> <i
						class="iconfont">&#xe615;</i> <span>销售管理</span>
				</a></li>
				<li><a href="/trfc/card/main"> <i 
						class="iconfont">&#xe601;</i> <span>卡务管理</span>
				</a></li>
				<li><a href="/trfc/quality/sales/batchnum/main"> <i
						class="iconfont">&#xe618;</i> <span>质控管理</span>
				</a></li>
				<li><a href="/trfc/customerbegin/main"> <i 
						class="iconfont">&#xe612;</i> <span>财务管理</span>
				</a></li>
				<li><a href="/trfc/poundNote/purchase/main"> <i
						class="iconfont">&#xe62a;</i> <span>磅单维护</span>
				</a></li>
				<li><a href="/trfc/accessRecord/main"> <i 
						class="iconfont">&#xe629;</i> <span>厂区物流</span>
				</a></li>
				
			</div>
			<a href="#itdangan" data-toggle="collapse" class="menu_collap_tit">
				<label>基础档案</label> <span><i class="iconfont">&#xe604;</i></span>
			</a>

			<div class="in" id="itdangan">
				<li><a href="/trfc/customer/main"> <i 
						class="iconfont">&#xe670;</i> <span>NC档案</span>
				</a></li>
				<li><a href="/trfc/transport/main"> <i 
						class="iconfont">&#xe670;</i> <span>计量档案</span>
				</a></li>
				<li><a href="/trfc/quality/sales/file/MaterialScheme/main"><i 
						class="iconfont">&#xe670;</i> <span>质检档案</span>
				</a></li>
				<li><a href="/trfc/basicFile/other/customer/main"> <i
						class="iconfont">&#xe670;</i> <span>其他档案</span>
				</a></li>
			</div>
			<a href="#sys" data-toggle="collapse" class="menu_collap_tit"> <label>系统设置</label>
				<span><i class="iconfont">&#xe604;</i> </span>
			</a>
			<div class="in" id="sys">
				<li><a href="/trfc/system/auth/menu/main"> <i
						class="iconfont">&#xe668;</i> <span>系统权限</span>
				</a></li>
				<li><a href="/trfc/system/base/code/main"> <i
						class="iconfont">&#xe614;</i> <span>系统业务</span>
				</a></li>
			</div>
		</ul>
	</div>
</div>
<div class="leftmini hide">
	 <div class="user">
        <a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle">
            <img src="${staticBasePath}/images/tx.jpg" class="img-circle">
        </a>
    </div>
    <div class="menu2">
        <i class="iconfont">&#xe635;</i>
    </div>
    <ul class="typelist" id="menu_imgs">
        <li data-toggle="tooltip" data-placement="right" title="采购管理">
            <a href="/trfc/purchaseApplication/main">
                <i class="iconfont">&#xe617;</i>
            </a>
        </li>
         <li data-toggle="tooltip" data-placement="right" title="销售管理">
            <a href="/trfc/purchaseApplication/main">
                <i class="iconfont">&#xe615;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="卡务管理">
            <a href="/trfc/card/main">
                <i class="iconfont">&#xe601;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="质控管理">
            <a href="/trfc/quality/sales/batchnum/main">
                <i class="iconfont">&#xe618;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="财务管理">
            <a href="/trfc/customerbegin/main">
                <i class="iconfont">&#xe612;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="磅单维护">
            <a href="/trfc/poundNote/purchase/main">
                <i class="iconfont">&#xe62a;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="厂区物流">
            <a href="/trfc/accessRecord/main">
                <i class="iconfont">&#xe629;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="NC档案">
            <a href="/trfc/customer/main">
                <i class="iconfont">&#xe670;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="计量档案">
            <a href="/trfc/transport/main">
                <i class="iconfont">&#xe670;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="质检档案">
            <a href="/trfc/quality/sales/file/MaterialScheme/main">
                <i class="iconfont">&#xe670;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="其他档案">
            <a href="/trfc/basicFile/other/customer/main">
                <i class="iconfont">&#xe670;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="系统权限">
            <a href="/trfc/system/auth/menu/main">
                <i class="iconfont">&#xe668;</i>
            </a>
        </li>
        <li data-toggle="tooltip" data-placement="right" title="系统业务">
            <a href="/trfc/system/base/code/main">
                <i class="iconfont">&#xe614;</i>
            </a>
        </li>
    </ul> 
</div>
<div class="intel_tab">
        <!--tab切换标题-->
        <ul class="intel_menu">
           
        </ul>
        <!--tab切换标题end-->
    </div>


