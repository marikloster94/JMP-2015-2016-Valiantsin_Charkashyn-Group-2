<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form action="/MVCTask4/delete" method="delete">
	Select person: 
	<select name="booking">
		<c:forEach var="booking" items="${bookings}">
			<option value="${booking.idBooking}">${booking.bookingNumber}</option>
		</c:forEach>
	</select> <br />
	<input type="submit" value=Delete />
</form:form>
