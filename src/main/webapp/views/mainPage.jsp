<%--
  Created by IntelliJ IDEA.
  User: barko
  Date: 13.02.2022
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Main</title>
</head>
<body>

<h3>Вы можете:</h3>
<c:if test="${user == null}">
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/signUp'" value="Зарегистрироваться"></p>
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/signIn'" value="Войти"></p>
</c:if>

<c:if test="${user != null}">
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/message'" value="Написать сообщение"></p>
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/chats'" value="Посмотреть сообщения"></p>
    <p><input type="button" onclick="location.href='/MK_JD2-88-2-0.0.0/leave'" value="Выйти"></p>
</c:if>

</body>
</html>
