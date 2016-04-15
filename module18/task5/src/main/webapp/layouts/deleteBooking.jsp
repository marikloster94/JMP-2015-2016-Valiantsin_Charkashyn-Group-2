<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="/MVCTask4/delete"  method="POST">
	Select booking: 
	<select name="booking">
		<c:forEach var="booking" items="${bookings}">
			<option value="${booking.idBooking}">${booking.bookingNumber}</option>
		</c:forEach>
	</select> <br />
	<input type="submit" value=Delete />
</form>
