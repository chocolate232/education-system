<%--
  Created by IntelliJ IDEA.
  User: yqb
  Date: 2022-10-12
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="divlogin">
    <form method="get" action="login">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input  name="username" type="text"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input  name="password" type="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="登陆"></td>
                <td><input type="button" value="注册" onclick="clickRegister()"></td>
            </tr>
        </table>
    </form>
</div>


<div  id="divRegister" style='visibility:hidden;'>

    <form method="get" action="register">
        <table>
            <tr>
                <td>用户名：</td>
                <td><input  name="username" type="text"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input  name="password" type="password"></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input  name="repassword" type="password"></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
                <td></td>
            </tr>
        </table>
    </form>
</div>
</body>
<script>
    function clickRegister() {


        document.getElementById("divRegister").style.visibility='visible';
        document.getElementById("divlogin").style.visibility='hidden';
    }
</script>
</html>
