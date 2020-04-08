<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register User</title>
</head>
<body style="background-color: red;">
<h2>User Registration:</h2>
<form action="registerUser" method="post">
<pre>
First Name: <input type="text" name="first_name"/>
Last Name:  <input type="text" name="last_name"/>
Company Name:  <input type="text" name="company_name"/>
Phone:  <input type="text" name="phone"/>
Email: <input type="text" name="email"/>
Password: <input type="password" name="password"/>
Confirm Password: <input type="password" name="confirmPassword"/>
<input type="submit" value="register"/>
</pre>
</form>

</body>
</html>