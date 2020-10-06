<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>商品添加页面</title>
    <link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
    <script src="../static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
    <a href="./main" ><button class="layui-btn layui-btn-normal">放弃添加</button></a>
    <form class="layui-form" action="" >
        <input type="hidden" name="id" >
        <div class="layui-form-item" >
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text"  width="50%"  name="name" required  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">品牌</label>
            <div class="layui-input-block">
                <input type="text" width="50%"  name="band" required  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">价格</label>
            <div class="layui-input-block">
                <input type="text" width="50%"  name="price" required  lay-verify="required"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" >分类</label>
            <div class="layui-input-block">
                <select name="category" lay-verify="required">
                    <option value=""></option>
                    <option value="1">篮球鞋</option>
                    <option value="2">跑鞋</option>
                    <option value="3">休闲鞋</option>
                    <option value="4">其他鞋</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <input type="hidden" name="images" class="image">
            <div class="layui-form-item">
                <label class="layui-form-label ">照片</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="test2">上传图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="demo2" height="150">
                        <p id="demoText"></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item" >
            <div class="layui-input-block">
                <button class="layui-btn layui-btn-normal" lay-submit lay-filter="add">立即添加</button>
                <button class="layui-btn layui-btn-normal" type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>




    <script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>

    <script>
        layui.use(['form','upload','layer'], function() {
            var layer = layui.layer,
            form = layui.form,
            upload = layui.upload,
            $ = layui.jquery;


            //普通图片上传
            var uploadInst = upload.render({
                elem: '#test2'
                ,url: './upload'
                ,accept:'images'
                ,size:50000
                ,before: function(obj){

                    obj.preview(function(index, file, result){

                        $('#demo2').attr('src', result);
                    });
                }
                ,done: function(res){
                    //如果上传失败
                    if(res.code > 0){
                        return layer.msg('上传失败');
                    }
                    //上传成功
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #4cae4c;">上传成功</span>');

                    var fileupload = $(".image");
                    fileupload.attr("value",res.data.src);
                    console.log(fileupload.attr("value"));
                }
                ,error: function(){
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        uploadInst.upload();
                    });
                }
            });


            //监听提交
            form.on('submit(add)', function(data) {
                var subData = JSON.stringify(data.field);
                console.log(subData);
                $.ajax({
                    url:"./save",
                    method:"post",
                    data:subData,
                    contentType:"application/json",
                    dataType:"json",
                    success:function (resp) {
                        if (resp.errCode != 0) {
                            layer.alert(resp.msg);
                            window.location.href="./main";
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
