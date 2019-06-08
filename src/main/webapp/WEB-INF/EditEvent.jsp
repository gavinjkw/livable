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
<div class="grid-example col s0 m12">
<h3> Edit Event</h3>
 <form:form method="POST" action="/edit_event/${event.id}" modelAttribute="eventObj">
        <div class="row">
        <div class="input-field col s12">
            <form:label path="name">Name: </form:label>
            <form:input type="text" value="${event.name}" path="name"/>
            <p><form:errors path="name"/></p>
        </div>
         </div>
         
         <div class="row">
        <div class="input-field col s12">
          <form:input path="details" value="${event.details}"></form:input>
          <label for="details">Details:</label>
        </div>
      </div>
      
      <div class="row">
        <div class="input-field col s12">
            <form:label path="location">Location:</form:label>
            <form:input type="text" value="${event.location}" path="location"/>
            <p><form:errors path="location"/></p>
        </div>
         </div>
         
         <div class="row">
         <fmt:formatDate value="${event.date}" pattern ="yyyy-MM-dd" var="newdatevar" />
	<fmt:formatDate value="${event.time}" pattern ="HH:mm" var="newtimevar" />
         
        <div class="input-field col s12 m6">
            <form:label path="date">Date:</form:label>
            <br>
             <form:input value="${newdatevar}" type="date" path="date"/>
            <p><c:out value="${noDate}"/></p>
            <p><form:errors path="date"/></p>
       </div>
 
        

        <div class="input-field col s12 m6">
            <form:label path="time">Time:</form:label>
            <br>
             <form:input value="${newtimevar}" type="time" path="time"/>
            <p><c:out value="${noDate}"/></p>
            <p><form:errors path="time"/></p>
            <span class="helper-text" data-error="wrong" data-success="right">Format: 06:30 PM</span>
    
        </div>
          </div>
      
         <form:hidden path="house" value = "${house.id}"/>
         <form:hidden path="user" value = "${user.id}"/>
       
        <input type="submit" class="btn btn-primary" value="Edit Event"/>
    </form:form>

 </div>

 
</div>
  </div>
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
  
</body>
</html>