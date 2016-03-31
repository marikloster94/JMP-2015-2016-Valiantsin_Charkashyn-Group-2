<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
<c:if test="${person!=null}">
<p>Client info:</p>
      <p>Name:  <c:out value="${person.name}"/></p>
      <p>Surname:  <c:out value="${person.surname}"/></p>
      <p>Date of birth:  <c:out value="${person.dateOfBirth}"/></p>
      <p>Passport Number:  <c:out value="${person.passportNumber}"/></p>
<a href="main.jsp" title="">Return to main page</a>
</c:if>

</body>
</html>