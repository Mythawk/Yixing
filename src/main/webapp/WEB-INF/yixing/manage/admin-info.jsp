<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css"/>
    <script src="../static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body bgcolor="#f0f8ff">
<li><a href="../manage/index"><button class="layui-btn layui-btn-normal">返回</button></a></li>
    <form class="layui-form"  style="width: 90%;padding-top: 20px;">
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码：</label>
            <div class="layui-input-block">
                <input type="password" name="password1" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码：</label>
            <div class="layui-input-block">
                <input type="password" name="password2" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">重复密码：</label>
            <div class="layui-input-block">
                <input type="password" name="password3" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <a href="../manage/login"> <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminPassword">立即提交</button></a>
            </div>

        </div>
    </form>


<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>

<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form(),
            layer = layui.layer;


        //监听提交
        form.on('submit(adminPassword)', function(data) {
            var subData = JSON.stringify(data.field);
            console.log(subData);
            $.ajax({
                url:"./update",
                method:"post",
                data:subData,
                contentType:"application/json",
                dataType:"json",
                success:function (resp) {
                    if (resp.errCode == 0) {
                        window.location.href="../manage/login";
                    } else {
                        layer.alert(resp.msg)
                    }
                },
                fail:function (resp) {
                    layer.alert(resp);
                }
            })
            return false;
        });

    });
</script>

</body>
</html>