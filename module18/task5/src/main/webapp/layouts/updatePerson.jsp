<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/MVCTask4/saveChanges" method="POST">
Select person for update: <select name="person">
		<c:forEach var="men" items="${persons}">
			<option value="${men.idPerson}">${men.name}
				${men.surname}</option>
		</c:forEach>
	</select> 
Enter data for edit:
<br />	<br /> 
First Name: <input type="text" name="name" required />
<br /> <br />
Last Name: <input type="text" name="surname" required />
<br /><br />
<input type="submit" value=Update />
</form>