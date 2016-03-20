<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show all Clients</title>
</head>
<body>
<p>Clients info:</p>
<c:forEach var="person" items="${persons}" >
      <p><b>Name:</b>  <c:out value="${person.name}"/></p>
      <p><b>Surname:</b>  <c:out value="${person.surname}"/></p>
      <p><b>Date of birth: </b> <c:out value="${person.dateOfBirth}"/></p>
      <p><b>Login:</b>  <c:out value="${person.login}"/></p>
      <p><b>Email:</b>  <c:out value="${person.email}"/></p>
      <br/>
</c:forEach>
<a href="main.jsp" title="">Return to main page</a>
</body>
</html>