<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask4/addClient" method="POST">
First Name: <input type="text" name="name" required />
<br /> <br />
Last Name: <input type="text" name="surname" required />
<br /><br />
<input type="submit" value=Add />
</form>
