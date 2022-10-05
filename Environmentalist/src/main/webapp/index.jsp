<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Регистрация</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<div class="container">
<h2>Регистрация в клуб на природозащитниците</h2>
<form action="RegistrationServlet" method="post">
<fieldset>
<label>Име:</label><br/>
<input type="text" name="name"/><br/>
<label>Потребителско име:</label><br/>
<input type="text" name="username"/><br/>
<label>Парола:</label><br/>
<input type="password" name="password"/><br/>
<label>Телефон:</label><br/>
<input type="text" name="phoneNumber"/><br/>
<input class="centered" type="submit" value="Регистрирай се"/>
</fieldset>
</form>
</div>
</body>
</html>
