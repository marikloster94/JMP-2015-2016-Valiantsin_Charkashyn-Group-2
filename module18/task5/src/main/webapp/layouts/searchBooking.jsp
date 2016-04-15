<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="/MVCTask4/getBooking" method="POST">
<p>Enter booking number</p>
Booking number: <input required type="text" name="bookingNumber" pattern="[A-Z]{3}[0-9]{7}"/>
<br/><br/>
<input type="submit" value=Search />
</form>
<c:if test="${booking!=null}">
<p>Booking info:</p>
      <p>Booking number:  <c:out value="${booking.bookingNumber}"/></p>
      <p>Place:  <c:out value="${booking.place}"/></p>
      <p>Date :  <c:out value="${booking.bookingDate}"/></p>
      <p>Price:  <c:out value="${booking.price}"/></p>
      <p>Client:  <c:out value="${booking.client.name} ${booking.client.surname}"/></p>
<a href="/MVCTask4/" title="">Return to main page</a>
</c:if>
