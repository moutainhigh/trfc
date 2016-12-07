/**
 * Created by ll on 2016/3/24.
 */
//全选取消按钮js
function selectAll()
{
    var allMails = document.getElementsByName("allUser")[0];
    var mails = document.getElementsByName("user");

    if(allMails.checked)
    {
        for(var i = 0; i < mails.length; ++i)
        {
            mails[i].checked = true;
        }
    }
    else
    {
        for(var i = 0; i < mails.length; ++i)
        {
            mails[i].checked = false;
        }
    }
}

//设置数据权限的左右移除1
        $(".qxalert1 .checkout ul li").on("click",function(){
            $(this).toggleClass("seled");
        });

        $(".qxalert1 .checkout ul li").on("dblclick",function(){
            if($(this).parent("ul").hasClass("left_ul")){
                toRight1($(this));
            }
            else{
                toLeft1($(this));
            }
        });

        $(".qxalert1 .right_shuang").on("click",function(){
            $(".qxalert1 .right_ul").append($(".qxalert1 .left_ul li"));
        });

        $(".qxalert1 .left_shuang").on("click",function(){
            $(".qxalert1 .left_ul").append($(".qxalert1 .right_ul li"));
        });

        $(".qxalert1 .right_dan").on("click",function(){
            $(".qxalert1 .right_ul").append($(".qxalert1 .left_ul .seled"));
        });

        $(".qxalert1 .left_dan").on("click",function(){
            $(".qxalert1 .left_ul").append($(".qxalert1 .right_ul .seled"));
        });

        function toRight1(obj){
            $(".qxalert1 .right_ul").append(obj);
        }

        function toLeft1(obj){
            $(".qxalert1 .left_ul").append(obj);
        }
//设置数据权限的左右移除2
$(".qxalert2 .checkout ul li").on("click",function(){
    $(this).toggleClass("seled");
});

$(".qxalert2 .checkout ul li").on("dblclick",function(){
    if($(this).parent("ul").hasClass("left_ul")){
        toRight2($(this));
    }
    else{
        toLeft2($(this));
    }
});

$(".qxalert2 .right_shuang").on("click",function(){
    $(".qxalert2 .right_ul").append($(".qxalert2 .left_ul li"));
});

$(".qxalert2 .left_shuang").on("click",function(){
    $(".qxalert2 .left_ul").append($(".qxalert2 .right_ul li"));
});

$(".qxalert2 .right_dan").on("click",function(){
    $(".qxalert2 .right_ul").append($(".qxalert2 .left_ul .seled"));
});

$(".qxalert2 .left_dan").on("click",function(){
    $(".qxalert2 .left_ul").append($(".qxalert2 .right_ul .seled"));
});

function toRight2(obj){
    $(".qxalert2 .right_ul").append(obj);
}

function toLeft2(obj){
    $(".qxalert2 .left_ul").append(obj);
}
//设置数据权限的左右移除3
$(".qxalert3 .checkout ul li").on("click",function(){
    $(this).toggleClass("seled");
});

$(".qxalert3 .checkout ul li").on("dblclick",function(){
    if($(this).parent("ul").hasClass("left_ul")){
        toRight3($(this));
    }
    else{
        toLeft3($(this));
    }
});

$(".qxalert3 .right_shuang").on("click",function(){
    $(".qxalert3 .right_ul").append($(".qxalert3 .left_ul li"));
});

$(".qxalert3 .left_shuang").on("click",function(){
    $(".qxalert3 .left_ul").append($(".qxalert3 .right_ul li"));
});

$(".qxalert3 .right_dan").on("click",function(){
    $(".qxalert3 .right_ul").append($(".qxalert3 .left_ul .seled"));
});

$(".qxalert3 .left_dan").on("click",function(){
    $(".qxalert3 .left_ul").append($(".qxalert3 .right_ul .seled"));
});

function toRight3(obj){
    $(".qxalert3 .right_ul").append(obj);
}

function toLeft3(obj){
    $(".qxalert3 .left_ul").append(obj);
}
//设置数据权限的左右移除4
$(".qxalert4 .checkout ul li").on("click",function(){
    $(this).toggleClass("seled");
});

$(".qxalert4 .checkout ul li").on("dblclick",function(){
    if($(this).parent("ul").hasClass("left_ul")){
        toRight4($(this));
    }
    else{
        toLeft4($(this));
    }
});

$(".qxalert4 .right_shuang").on("click",function(){
    $(".qxalert4 .right_ul").append($(".qxalert4 .left_ul li"));
});

$(".qxalert4 .left_shuang").on("click",function(){
    $(".qxalert4 .left_ul").append($(".qxalert4 .right_ul li"));
});

$(".qxalert4 .right_dan").on("click",function(){
    $(".qxalert4 .right_ul").append($(".qxalert4 .left_ul .seled"));
});

$(".qxalert4 .left_dan").on("click",function(){
    $(".qxalert4 .left_ul").append($(".qxalert4 .right_ul .seled"));
});

function toRight4(obj){
    $(".qxalert4 .right_ul").append(obj);
}

function toLeft4(obj){
    $(".qxalert4 .left_ul").append(obj);
}

//树形菜单为节点添加展开，关闭的操作
$(function () {
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span >i').on('click', function (e) {
        var children = $(this).parent().parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});

//树形菜单全选取消功能
$(function(){
    $(".tree ul li").find("input[type='checkbox']").on("click",function(){
//        console.log($(this).parentsUntil(".tree","li").html());
        var childInput=$(this).parentsUntil("ul","li").find("input[type='checkbox']");
        if($(this).is(":checked")){
            childInput.prop("checked",true);
        }
        else{
            childInput.prop("checked",false);
        }
    });
});
//屏幕高度
var htotal = $(window).height()-65;
$("#sidebar").css("min-height",htotal);
$(".ht_index").css("min-height",htotal);
$(".ht_indexcont").css("height",htotal);






