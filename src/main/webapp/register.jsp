<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<form action="register" method="post">
    <label for="firstName">First name:</label> <input type="text" name="firstName">
    <br>
    <label for="lastName">Last name:</label> <input type="text" name="lastName">
    <br>
    <label for="email">Email:</label> <input type="email" name="email">
    <br>
    <label for="password">Password:</label> <input type="password" name="password">
    <br>
    <input type="submit" value="Sign Up">
</form>

</body>
</html>
