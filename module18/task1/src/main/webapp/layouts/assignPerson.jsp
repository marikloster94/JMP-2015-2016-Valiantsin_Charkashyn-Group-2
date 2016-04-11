<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask1/assignPerson" method="POST">
	Select person: <select name="person">
		<c:forEach var="men" items="${persons}">
			<option value="${men.passportNumber}">${men.name}
				${men.surname}</option>
		</c:forEach>
	</select> <br />
	<br /> Select account: <select name="account">
		<c:forEach var="acc" items="${accounts}">
			<option value="${acc.id}">${acc.id}</option>
		</c:forEach>
	</select> 
	<br />
	<br /> 
	<input type="submit" value=Assign />
</form>
