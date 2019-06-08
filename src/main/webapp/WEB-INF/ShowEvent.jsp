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
 <script src="/js/event.js"></script>
 <link rel="stylesheet" type="text/css" href="/css/style-home.css">
 <link href="https://fonts.googleapis.com/css?family=Fjalla+One|Acme|Kanit" rel="stylesheet">
</head>
<body>

<nav>
    <div class="nav-wrapper">
      <a href="/" class="brand-logo"> <img id="logo" class="text-align-middle" src="/pictures/logo1.png" alt="Logo" ></a>
      <a href="/" data-target="mobile-demo" class="sidenav-trigger"><img id="menu-icon" class="text-align-middle" src="/pictures/hamburger.png" alt="Menu" height="42" width="42"></a>
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
<div class="grid-example col s0 m3">
<a href="/events"><h3 class="black-font">Events</h3></a>


<table id="message-board">
    <thead>
        <tr class="event-item">
            <th scope="col">Date</th>
            <th scope="col">Name</th>

        </tr>
    </thead>
    <tbody>


    <c:forEach items="${house.events}" var="event">

	<tr class="event-item"> 

	<fmt:formatDate value="${event.date}" pattern ="MMMM, dd" var="newdatevar" />
	<fmt:formatDate value="${event.time}" pattern ="h:mm a" var="newtimevar" />
	
	<td><p id="date-show-event" class="block"><c:out value="${newdatevar}"/></p> <c:out value="${newtimevar}"/></td>
	<td><a href="/events/${event.id}"> <c:out value="${event.name}"/> </a> </td>


	

	</tr>

	
	</c:forEach>
	</tbody>
	</table>
	</div>

<fmt:formatDate value="${event.date}" pattern ="MMMM, dd yyyy" var="newdatevar" />
	<fmt:formatDate value="${event.time}" pattern ="h:mm a" var="newtimevar" />

 <div class="grid-example col s12 m6">

<h3><c:out value="${event.name}"/></h3>
<p>Created by <c:out value="${event.user.firstName}"/></p>
<hr>
<p><c:out value="${newdatevar}"/> <c:out value="${newtimevar}"/></p>
<p><c:out value="${event.details}"/></p>

<h5> Attendees</h5>

 <c:forEach items="${event.users}" var="user">
 <p class="user-bubble"><c:out value="${user.firstName} ${user.lastName}"/></p>
 </c:forEach>
 </div>

 <!-- Message section --> 
   
   <div class="grid-example col s12 m3">
   <p>Event Messages</p>
   <table id="message-board" >
   <c:forEach items="${event.messages}" var="message">
 
    <tr>
  
      <td id="message-item" scope="col"><p id="message-name"><c:out value="${message.user.firstName}"/></p><c:out value="${message.body}"/></td>
       </tr>
      </c:forEach>
    

</table>



 <form:form id="message-add-form" method="POST" action="/add_event_message" modelAttribute="messageObj">
        <p>
            <form:label path="body"> </form:label>
            <form:input type="text" path="body"/>
            <p><form:errors path="body"/></p>
        </p>
        <p>
            <form:input type="hidden" path="user" value="${user.id}"/>
            <form:input name="eventId" type="hidden" path="event" value="${event.id}"/>
			<input name="eventId" type="hidden" value="${event.id}"/>
        </p>
        <input type="submit" onclick="updateScroll()" class="btn btn-primary" value="Post"/>
    </form:form>
    
    </div>
</div>
  </div>
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
  
</body>
</html>