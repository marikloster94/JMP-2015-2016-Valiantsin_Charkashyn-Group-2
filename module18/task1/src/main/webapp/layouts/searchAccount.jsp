<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask1/getAccount" method="POST">
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
      <p>Client :<c:out value="${account.man.name}"/> <c:out value="${account.man.surname}"/></p>

<a href="/MVCTask1/" title="">Return to main page</a>
</c:if>
</form>
