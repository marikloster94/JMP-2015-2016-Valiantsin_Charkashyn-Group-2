<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add client</title>
</head>
<body>
<form action="/ModuleUI?command=addClient" method="POST">
First Name: <input type="text" name="first_name" required />
<br />
Last Name: <input type="text" name="last_name" required />
<br />
Date of birth: <input required type="text" name="date" pattern="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$"/>
<br />
Passport number: <input required type="text" name="passport" pattern="[A-Z]{2}[0-9]{7}"/>
<br />
<input type="submit" value=Add />
</form>
</body>
</html>