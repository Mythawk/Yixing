<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>后台登录</title>
		<link rel="stylesheet" type="text/css" href="../static/admin/layui/css/layui.css" />
		<link rel="stylesheet" type="text/css" href="../static/admin/css/login.css" />
        <script src="../static/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	</head>

	<body>
		<div class="m-login-bg">
			<div class="m-login">
				<h3>宜行后台系统登录</h3>
				<div class="m-login-warp">
					<form class="layui-form">
						<div class="layui-form-item">
							<input type="text" name="loginName" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-form-item">
							<div class="layui-inline">
								<input type="text" name="verity" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="/YiXing/manage/getVerifyCode" />
							</div>
						</div>
						<div class="layui-form-item m-login-btn">
							<div class="layui-inline">
								<button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
							</div>
							<div class="layui-inline">
								<button type="reset" class="layui-btn layui-btn-primary">取消</button>
							</div>
						</div>
					</form>
				</div>
				<p class="copyright">Copyright 2020 by Mythawk </p>
			</div>
		</div>
		<script src="../static/admin/layui/layui.js" type="text/javascript" charset="utf-8"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer;


				//自定义验证规则
				form.verify({
					loginName: function(value) {
						if(value.length == 0) {
							return '请输入用户名';
						}
					},
                    password: function(value) {
                        if(value.length == 0) {
                            return '请输入密码';
                        }
                    },
                    verity: function(value) {
                        if(value.length == 0) {
                            return '验证码';
                        }
                    },

				});

				
				//监听提交
				form.on('submit(login)', function(data) {
                    var subData = JSON.stringify(data.field);
                    console.log(subData);
                    $.ajax({
                        url:"./doLogin",
                        method:"post",
                        data:subData,
                        contentType:"application/json",
                        dataType:"json",
                        success:function (resp) {
                            if (resp.errCode == 0) {
                                layer.msg("登陆成功")
                                window.location.href="../manage/index";
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