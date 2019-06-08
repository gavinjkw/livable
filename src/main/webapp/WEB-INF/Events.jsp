<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livable - making roomate's lives easier</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
 <script src="js/event.js"></script>
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



<div class="row">
<div class="grid-example col s12 m8">
<h3>Events</h3>


<table id="message-board">
    <thead>
        <tr>
            <th scope="col">Date</th>
            <th scope="col">Name</th>
            <th scope="col">Location</th>
            <th scope="col">Attendees</th>
            <th scope="col">Creator</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>


    <c:forEach items="${house.events}" var="event">

	<tr class="event-item"> 

	<fmt:formatDate value="${event.date}" pattern ="MMMM, dd yyyy" var="newdatevar" />
	<fmt:formatDate value="${event.time}" pattern ="h:mm a" var="newtimevar" />
	
	<td><c:out value="${newdatevar}"/> <c:out value="${newtimevar}"/></td>
	<td><a href="/events/${event.id}"> <c:out value="${event.name}"/> </a> </td>
	<td><c:out value="${event.location}"/></td>
	<td><c:out value="${fn:length(event.users)}"/></td>
	<td><c:out value="${event.user.firstName}"/></td>
	<td>
	
	<c:choose>
	
	<c:when test="${event.user.id == user.id}">
		<form class="inline" action="/deleteEvent/${event.id}" method="post">
		<input type="hidden" name="_method" value="delete">
		<input class="btn btn-small" type="submit" value="Delete">
		</form>
		<form class="inline" action="/events/${event.id}/edit" method="post"><input class="btn btn-small" type="submit"value="Edit"></form></td>
	</c:when>
	
	<c:otherwise>
	
	<c:choose>
	<c:when test="${user.events.contains(event)}">
	<input id="going-button" class="btn btn-small disabled" type="button" value="Going">
	<form class="inline" action="/leaveEvent/${user.id}/${event.id}" method="post">
	<input type="hidden" name="_method" value="delete">
	<input type="submit" class="btn btn-small " value="Cancel"> </form></p>
	</c:when>
	
	<c:otherwise>
	<form:form class="inline" action="/joinEvent" method="post" modelAttribute="middleTableObj">
	<form:hidden path="user" value="${user.id}"></form:hidden>
	<form:hidden path="event" value="${event.id}"></form:hidden>
	<input type="submit" class="btn btn-small" value="Join"/>
	</form:form>
	</c:otherwise>
	
	</c:choose>
	
	</c:otherwise>
	
	</c:choose>

	</c:forEach>
	</tbody>
	</table>
	</div>



 <div class="grid-example col s12 m4">

<h3>Add Event</h3>
 <form:form method="POST" action="/events" modelAttribute="eventObj">
        <div class="row">
        <div class="input-field col s12">
            <form:label path="name">Name: </form:label>
            <form:input type="text" path="name"/>
            <p><form:errors path="name"/></p>
        </div>
         </div>
         
         <div class="row">
        <div class="input-field col s12">
          <form:textarea path="details" class="materialize-textarea"></form:textarea>
          <label for="details">Details:</label>
        </div>
      </div>
      
      <div class="row">
        <div class="input-field col s12">
            <form:label path="location">Location: </form:label>
            <form:input type="text" path="location"/>
            <p><form:errors path="location"/></p>
        </div>
         </div>
         
         <div class="row">
        <div class="input-field col s12 m6">
            <form:label path="date">Date:</form:label>
            <br>
             <form:input type="date" path="date"/>
            <p><c:out value="${noDate}"/></p>
            <p><form:errors path="date"/></p>
       </div>
 
        

        <div class="input-field col s12 m6">
            <form:label path="time">Time:</form:label>
            <br>
             <form:input type="time" path="time"/>
            <p><c:out value="${noDate}"/></p>
            <p><form:errors path="time"/></p>
            <span class="helper-text" data-error="wrong" data-success="right">Format: 06:30 PM</span>
    
        </div>
          </div>
      
         <form:hidden path="house" value = "${house.id}"/>
         <form:hidden path="user" value = "${user.id}"/>
       
        <input type="submit" class="btn btn-primary" value="Add Event"/>
    </form:form>


 </div>
  </div>
</div>
    
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
  
</body>
</html>