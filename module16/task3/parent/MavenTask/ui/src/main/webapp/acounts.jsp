<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show all accounts</title>
</head>
<body>

<p>Accounts info:</p>
<c:forEach var="account" items="${accounts}" >
      <p><c:out value="${account.description}"/></p>
      <p><b>Start date:</b> <c:out value="${account.startDate}"/></p>
      <p><b>End date:</b><c:out value="${account.endDate}"/></p>
      <p><b>Money:</b><c:out value="${account.value}"/> <c:out value="${account.curr.shortName}"/></p>
      <p><b>Client:</b><c:out value="${account.man.name}"/> <c:out value="${account.man.surname}"/></p>
<br/>
</c:forEach>
<a href="main.jsp" title="">Return to main page</a>
</body>
</html>