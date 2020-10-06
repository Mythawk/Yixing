<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>宜行后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css"/>
</head>
<body class="layui-layout-body">
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">

            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="navList">
                <li class="layui-nav-item"><a  href="welcome"  target="main_self_frame"><i class="iconfont">&#xe604;</i>&nbsp;管理主页</a></li>
                <li class="layui-nav-item ">
                    <a href="javascript:;"><i class="iconfont">&#xe607;</i>&nbsp;管理菜单</a>
                    <dl class="layui-nav-child">
                        <dd><a href="../sneaker/main" target="main_self_frame"><img src="../static/admin/images/l-line.png"> 商品管理</a></dd>
                         <dd><a href="../share/main" target="main_self_frame"><img src="../static/admin/images/l-line.png"> 评论管理</a></dd>
                        <dd><a href="../order/main" target="main_self_frame"><img src="../static/admin/images/l-line.png"> 订单管理</a></dd>
                        <dd><a href="../order/main2" target="main_self_frame"><img src="../static/admin/images/l-line.png"> 退货管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="../user/adminAuto" target="main_self_frame" ><i class="iconfont">&#xe606;</i>&nbsp;修改管理员密码</a></li>
                <li class="layui-nav-item"><a href="./login"><i class="iconfont">&#xe60b;</i>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe src="welcome" name="main_self_frame" width="100%" height="100%" frameborder="0" scrolling="yes" class="layadmin-iframe"></iframe>

    </div>

    <script>
        //JavaScript代码区域
        layui.use('element', function () {
            var element = layui.element
                , $ = layui.jquery;



        });
        function refreshHighlight(url) {
            $ = layui.jquery;
            $(".layui-nav[lay-filter='navList'] a").each(function (ind, val) {
                if($(this).attr('href') === url){
                    $('.layui-nav dd').removeClass('layui-this');
                    $(this).parent('dd').addClass('layui-this');
                }
            })
        }
    </script>


    <script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="../static/admin/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="../static/admin/js/main.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
