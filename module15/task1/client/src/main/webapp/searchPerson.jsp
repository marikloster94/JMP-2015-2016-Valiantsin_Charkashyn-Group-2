<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search person</title>
</head>
<body>
<form action="WebClient?command=searchPerson" method="POST">
<p>Enter login</p>
Login: <input required type="text" name="login" />
<br/><br/>
<input type="submit" value=Search />
</form>
<c:if test="${person!=null}">
<p>Person info:</p>
      <p>Name:  <c:out value="${person.name}"/></p>
      <p>Surname:  <c:out value="${person.surname}"/></p>
      <p>Date of birth:  <c:out value="${person.dateOfBirth}"/></p>
      <p>Login:  <c:out value="${person.login}"/></p>
      <p>Email:  <c:out value="${person.email}"/></p>
<a href="main.jsp" title="">Return to main page</a>
<p><a href="WebClient?command=delete" title="">Delete person</a></p>
<p><a href="WebClient?command=update" title="">Update person</a></p>
</c:if>

</body>
</html>