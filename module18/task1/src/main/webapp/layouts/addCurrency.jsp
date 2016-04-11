<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask1/addCurrency" method="POST">
Short Name of currency: <input type="text" name="shortName" required />
<br /> <br />
<input type="submit" value=Add />
</form>