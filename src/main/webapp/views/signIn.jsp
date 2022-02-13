<%--
  Created by IntelliJ IDEA.
  User: barkovskii_aa
  Date: 12.02.2022
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>SignIn</title>
</head>
<body>

<c:if test="${wrongLogin == true}">
    <p style="color: red">Пользователя с таким логином нет!</p>
</c:if>

<c:if test="${wrongPassword == true}">
    <p style="color: red">Вы ввели неправильный пароль!</p>
</c:if>
<h3>Авторизация:</h3>
<form action="/MK_JD2-88-2-0.0.0/signIn" method="post">
    <p><b>Логин</b> <input type="text" placeholder="Логин пользователя" name="login"></p>
    <p><b>Пароль</b> <input type="password" placeholder="Пароль" name="password"></p>
    <p><input type="submit" value="Войти"></p>
</form>

</body>
</html>
