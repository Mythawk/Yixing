<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>商品管理</title>
    <link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css"/>
    <script src="../static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body bgcolor="#f0f8ff">
        <div class="wrap-container">
            <div class="column-content-detail">
                <a href="./sadd"><button class="layui-btn layui-btn-normal"><i class="layui-icon">&#xe654;</i></button> </a>
                <div class="layui-form" id="category-table-list">
                    <table class="layui-table" lay-even lay-skin="nob">
                        <colgroup>
                            <col width="50">
                            <col class="hidden-xs" width="50">
                            <col width="200">
                            <col class="hidden-xs">
                            <col class="hidden-xs">
                            <col class="hidden-xs">
                            <col class="hidden-xs" width="100">
                            <col width="150">
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                            <th class="hidden-xs">ID</th>
                            <th class="hidden-xs">名称</th>
                            <th class="hidden-xs">品牌</th>
                            <th class="hidden-xs">价格</th>
                            <th class="hidden-xs">分类</th>
                            <th class="hidden-xs">图片</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${shoes}" var="s" varStatus="st" >
                        <tbody>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"  data-id="1"></td>
                            <td class="hidden-xs">${st.index+1}</td>
                            <td class="hidden-xs">${s.sneaker_name}</td>
                            <td class="hidden-xs">${s.sneaker_band}</td>
                            <td class="hidden-xs">${s.sneaker_price}</td>
                            <td class="hidden-xs">${s.sneaker_category}</td>
                            <td class="hidden-xs"><img src="${s.sneaker_img}" height="80px" /></td>
                            <td>
                                   <a href="./edit/${s.sneaker_id}">
                                        <button type="submit" class="layui-btn  layui-btn-normal " ><i class="layui-icon">&#xe642;</i></button></a>
                                   <a href="./delete/${s.sneaker_id}" onclick="return confirmDelete();">
                                        <button  type="button" class="layui-btn  layui-btn-danger " ><i class="layui-icon">&#xe640;</i></button></a>
                            </td>
                        </tr>
                        </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>

        <script type="text/javascript">
            function confirmDelete() {
                return confirm("确定删除吗？");
            }
        </script>

</body>
</html>