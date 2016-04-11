<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="/MVCTask1/addAccount" method="POST">
	Select person: <select name="person">
		<c:forEach var="men" items="${persons}">
			<option value="${men.passportNumber}">${men.name}
				${men.surname}</option>
		</c:forEach>
	</select> <br />
	<br /> Select currency: <select name="currency">
		<c:forEach var="curr" items="${currencies}">
			<option value="${curr.shortName}">${curr.shortName}</option>
		</c:forEach>
	</select> <br />
	<br /> Enter description of account: <input type="text"
		name="description" required /> <br /> <br /> Enter start date: <input
		required type="text" name="startDate"
		pattern="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$" />
	<br />
	<br /> Enter end date: <input required type="text" name="endDate"
		pattern="^([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})$" />
	<br />
	<br /> Enter amount: <input required type="number" step="any" name="value"
		pattern="\d+\.\d+" /> <br />
	<br /> <input type="submit" value=Add />
</form>
