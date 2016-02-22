<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search person</title>
</head>
<body>
<form action="ModuleUI?command=searchPerson" method="POST">
<p>Enter passport number</p>
Passport number: <input required type="text" name="passport" pattern="[A-Z]{2}[0-9]{7}"/>
<br/><br/>
<input type="submit" value=Search />
</form>


</body>
</html>