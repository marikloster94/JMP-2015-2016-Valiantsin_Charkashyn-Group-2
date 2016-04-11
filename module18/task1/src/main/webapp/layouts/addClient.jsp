<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask1/addClient" method="POST">
First Name: <input type="text" name="name" required />
<br /> <br />
Last Name: <input type="text" name="surname" required />
<br /><br />
Date of birth: <input required type="text" name="dateOfBirth" pattern="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$"/>
<br /><br />
Passport number: <input required type="text" name="passportNumber" pattern="[A-Z]{2}[0-9]{7}"/>
<br /><br />
<input type="submit" value=Add />
</form>
