<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
		<div id="menu" style="float: left; width: 20%; "> 
			<tiles:insertAttribute name="menu" />
		</div>
       <div id="content" style="margin-left: 20%; padding-top: 20px;">
       		<tiles:insertAttribute name="body" />
       </div>
        

    </body>
</html>