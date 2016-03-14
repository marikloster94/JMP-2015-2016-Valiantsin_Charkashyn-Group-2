<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
<c:if test="${account!=null}">
<p>Account info:</p>
      <p><c:out value="${account.description}"/></p>
      <p>Start date: <c:out value="${account.startDate}"/></p>
      <p>End date :<c:out value="${account.endDate}"/></p>
      <p>Money :<c:out value="${account.value}"/> <c:out value="${account.curr.shortName}"/></p>
      <p>Client :<c:out value="${account.person.name}"/> <c:out value="${account.person.surname}"/></p>

<a href="main.jsp" title="">Return to main page</a>
</c:if>
</form>
</body>
</html>