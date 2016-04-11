<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>Clients info:</p>
<c:forEach var="person" items="${clients}" >
      <p><b>Name:</b>  <c:out value="${person.name}"/></p>
      <p><b>Surname:</b>  <c:out value="${person.surname}"/></p>
      <p><b>Date of birth: </b> <c:out value="${person.dateOfBirth}"/></p>
      <p><b>Passport Number:</b>  <c:out value="${person.passportNumber}"/></p>
      <br/>
</c:forEach>
<a href="/MVCTask1/" title="">Return to main page</a>
