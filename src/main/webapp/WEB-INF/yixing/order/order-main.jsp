<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title>订单管理</title>
    <link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../static/admin/css/admin.css"/>
    <script src="../static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body bgcolor="#f0f8ff">
        <div class="wrap-container">
            <div class="column-content-detail">
                <div class="layui-form" id="category-table-list">
                    <table class="layui-table" lay-even lay-skin="nob">
                        <colgroup>
                            <col width="50">
                            <col class="hidden-xs" width="150">
                            <col width="200">
                            <col class="hidden-xs">
                            <col class="hidden-xs">
                            <col class="hidden-xs">
                            <col class="hidden-xs">
                            <col width="150">
                        </colgroup>
                        <thead>
                        <tr>
                            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
                            <th class="hidden-xs">订单号</th>
                            <th class="hidden-xs">收货地址</th>
                            <th class="hidden-xs">详细地址</th>
                            <th class="hidden-xs">收货人联系方式</th>
                            <th class="hidden-xs">订单状态</th>
                            <th class="hidden-xs">备注</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${order}" var="o" varStatus="st" >
                        <tbody>
                        <tr>
                            <td><input type="checkbox" name="" lay-skin="primary"  data-id="1"></td>
                            <td class="hidden-xs">${o.order_id}</td>
                            <td class="hidden-xs">${o.address_address}</td>
                            <td class="hidden-xs">${o.address_detail}</td>
                            <td class="hidden-xs">${o.address_phone}</td>
                            <td class="hidden-xs">${o.order_status}</td>
                            <td class="hidden-xs">${o.order_note}</td>
                            <td>
                                   <a href="./edit/${o.order_id}">
                                   <button type="submit" class="layui-btn  layui-btn-normal " >确认发货</button></a>

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