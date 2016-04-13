<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="/MVCTask4/addBooking" method="POST">
	Select person: <select name="person">
		<c:forEach var="men" items="${persons}">
			<option value="${men.idPerson}">${men.name}
				${men.surname}</option>
		</c:forEach>
	</select> <br />
	<br /> 
	Enter booking number: <input required type="text" name="bookingNumber" pattern="[A-Z]{3}[0-9]{7}"/>
	<br/><br/>
	Enter bookingDate: <input required type="date"  name="bookingDate" />
	<br />	<br />
	Enter place: <input required type="number"  name="place" /> 
	<br />	<br/>
	Enter price: <input required type="number" step="any" name="price" />
	<br/> <br />
	 <input type="submit" value=Add />
</form>
