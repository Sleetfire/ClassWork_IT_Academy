<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn page</title>
</head>
<body>

<form action="/MK_JD2-88-2-0.0.0/signIn" method="post">
    <b>Логин</b> <input type="text" placeholder="Логин пользователя" name="login"> <br>
    <b>Пароль</b> <input type="password" placeholder="Пароль" name="password"> <br>
    <input type="submit" value="Войти">
</form>

</body>
</html>
