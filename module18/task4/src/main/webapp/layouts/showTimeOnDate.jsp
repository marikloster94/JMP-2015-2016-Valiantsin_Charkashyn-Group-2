<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<div id="result"></div>
<form id="search-form" action="/MVCTask4/search/getSearchResult">
<label>Showtime Date</label>
<input type="date"  id="date"/>

<button type="submit" id="bth-search">Search</button>
</form>
<script>
	jQuery(document).ready(function($) {
		$("#search-form").submit(function(event) {
			// Disble the search button
			enableSearchButton(false);
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			searchViaAjax();
		});
	});
	function searchViaAjax() {
		var search = {}
		search["date"] = $("#date").val();
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : $("#search-form").attr("action"),
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}
	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#result').html(json);
	}
</script>
