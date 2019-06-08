<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livable - making roomate's lives easier</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
 <script src="js/messages.js"></script>
 <link rel="stylesheet" type="text/css" href="css/style-home.css">
 <link href="https://fonts.googleapis.com/css?family=Fjalla+One|Acme|Kanit" rel="stylesheet">
</head>
<body>

<nav>
    <div class="nav-wrapper">
      <a href="/" class="brand-logo"> <img id="logo" class="text-align-middle" src="pictures/logo1.png" alt="Logo" ></a>
      <a href="/" data-target="mobile-demo" class="sidenav-trigger"><img id="menu-icon" class="text-align-middle" src="pictures/hamburger.png" alt="Menu" height="42" width="42"></a>
      <ul class="right hide-on-med-and-down">
    	<li class="bold"> Welcome, <c:out value="${user.firstName}"/></li>
        <li><a href="/about">About</a></li>
        <li><a href="/myhouse">My House</a></li>
         <li><a href="/home">Home</a></li>
        <li><a href="/logout">Logout</a></li>
      </ul>
    </div>
  </nav>

  <ul class="sidenav" id="mobile-demo">
    <li><a href="/account">Account</a></li>
        <li><a href="/about">About</a></li>
        <li><a href="/myhouse">My House</a></li>
         <li><a href="/home">Home</a></li>
        <li><a href="/logout">Logout</a></li>
  </ul>

	<div class="header">
		<h1 class="center title-font">Livable</h1>
		

	</div>

<div class="container">
<h3>Message Wall</h3>
<div class="scrollable" id="scrollable">
<table id="message-board" >
   <c:forEach items="${house.houseMessages}" var="message">
 
    <tr>
  
      <td id="message-item" scope="col"><p id="message-name"><c:out value="${message.user.firstName}"/></p><c:out value="${message.body}"/></td>
       </tr>
      </c:forEach>
    

</table>
</div>
 <form:form id="message-add-form" method="POST" action="/add_house_message" modelAttribute="messageObj">
        <p>
            <form:label path="body"> </form:label>
            <form:input type="text" path="body"/>
            <p><form:errors path="body"/></p>
        </p>
        <p>
            <form:input type="hidden" path="user" value="${user.id}"/>
            <form:input type="hidden" path="house" value="${house.id}"/>

        </p>
        <input type="submit" onclick="updateScroll()" class="btn btn-primary" value="Post"/>
    </form:form>
    
	
 </div>
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
  
</body>
</html>