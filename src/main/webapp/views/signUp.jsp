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
    <title>SignUp page</title>
</head>
<body>

<h3>Регистрация нового пользователя:</h3>
<form action="/MK_JD2-88-2-0.0.0/signUp" method="post">
    <b>Логин</b> <input type="text" placeholder="Логин пользователя" name="login"> <br>
    <b>ФИО</b> <input type="text" placeholder="ФИО пользователя" name="name"> <br>
    <b>Дата рождения</b> <input type="date", placeholder="Дата рождения" name="birthday"> <br>
    <b>Пароль</b> <input type="password" placeholder="Пароль" name="password"> <br>
    <input type="submit" value="Зарегистрироваться">
</form>

</body>
</html>
