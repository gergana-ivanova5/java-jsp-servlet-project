<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "classes.Environmentalist" %>
<%@ page import = "classes.EnvironmentalistCollection" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Потребителски данни</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<div class="container">
<h2>Данни на потребителя</h2>
<form action="Logout" method="post">
<fieldset>
<label>Име:</label>
<label>${sessionUser.name}</label><br/>
<label>Потребителско име:</label>
<label>${sessionUser.username}</label><br/>
<label>Телефон:</label>
<label>${sessionUser.phoneNumber}</label><br/>
<input class="centered" type="submit" value="Изход"/>
</fieldset>

<label>Всичко регистрирано:</label><br/>
<% EnvironmentalistCollection collection= EnvironmentalistCollection.getInstance(); %>
<% for(Environmentalist e: collection.getList()) {%>
<label><%= e.getName()%></label>
<label><%= e.getUsername()%></label>
<label><%= e.getPhoneNumber()%></label><br/>
<%} %>

</form>
</div>
</body>
</html>