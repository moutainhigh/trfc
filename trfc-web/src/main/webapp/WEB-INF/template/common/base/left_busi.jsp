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
        
    </ul> 
</div>
<div class="intel_tab">
        <!--tab切换标题-->
        <ul class="intel_menu">
           
        </ul>
        <!--tab切换标题end-->
    </div>


