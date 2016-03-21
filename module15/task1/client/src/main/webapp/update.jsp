<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update person</title>
</head>
<body>
<form action="WebClient?command=update" method="POST">
First Name: <input type="text" name="name" value="${person.name}" required  />
<br /> <br />
Last Name: <input type="text" name="surname" value="${person.surname}" required />
<br /><br />
Date of birth: <input required type="text" name="date" value="${person.dateOfBirth}" pattern="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$"/>
<br /><br />
Login: <input type="text" name="log" value="${person.login}" readonly/>
<br /><br />
Email: <input type="text" name="mail" value="${person.email}" required/>
<br /><br />
<input type="submit" value=Update />
</form>
</body>
</html>