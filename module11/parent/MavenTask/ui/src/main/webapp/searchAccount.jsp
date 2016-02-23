<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search account</title>
</head>
<body>
<form action="ModuleUI?command=searchAccount" method="POST">
<p>Enter account number</p>
Account number: <input required type="number" name="id" />
<br/><br/>
<input type="submit" value=Search />
</form>
</body>
</html>