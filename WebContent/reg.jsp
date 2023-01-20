<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/public.css"/>
    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="js/function.js"></script>
    <script src="js/jquery-1.12.4.min.js"></script>
    <style>
    .reg P .error {
    display:inline-block;
    background-color:#ffe8e1;
    height:20px;
    line-heigth:25px;
    padding-left:10px;
    margin-left:20px;
    }
    </style>
</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="register" method="post" onsubmit="return checkForm(this)"><h1></h1>
        <h1>员工注册</h1>
        <p><input type="text" name="userName" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span></span></p>
        <p><input type="text" name="name" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入姓名"><span></span></p>
        <p><input type="text" name="passWord" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span></span></p>
        <p><input type="text" name="rePassWord" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"><span></span></p>
        <p>
        <input type="radio" style="width:20px;height:20px" name="sex" value="T" checked="checked">男
        <input type="radio" style="width:20px;height:20px" name="sex" value="F">女
        
        
        <span></span></p>
        <p><input type="text" name="email" value="" placeholder="请输入邮箱"><span></span></p>
        <p><input type="text" name="mobile" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入手机号"><span></span></p>
        <p><input type="text" name="address" value="" placeholder="请确认住址"><span></span></p>
        <p><input class="code" type="text" name="veryCode" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="验证码"><img
                src="getcode" alt="看不清换一张"onclick="change(this)"><span></span></p>
        <p><input type="submit" name="" value="注册"></p>
        
        <p class="txt"><a href="login.jsp"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>