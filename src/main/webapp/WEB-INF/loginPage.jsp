<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livable - making roomates lives easier</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
 <script src="js/login.js"></script>
 <link rel="stylesheet" type="text/css" href="css/style-login.css">
 <link href="https://fonts.googleapis.com/css?family=Fjalla+One|Acme|Kanit" rel="stylesheet">
</head>
<body>

  <nav>
    <div class="nav-wrapper">
      <a href="/" class="brand-logo"> <img id="logo" class="text-align-middle" src="pictures/logo1.png" alt="Logo" ></a>
      <a href="/" data-target="mobile-demo" class="sidenav-trigger"><img id="menu-icon" class="text-align-middle" src="pictures/hamburger.png" alt="Menu"></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="/account">Account</a></li>
        <li><a href="/">Home</a></li>
        <li><a href="/about">About</a></li>
      </ul>
    </div>
  </nav>

  <ul class="sidenav" id="mobile-demo">
    <li><a href="/login">Account</a></li>
    <li><a href="/">Home</a></li>
    <li><a href="/about">About</a></li>
  </ul>

	<div class="header">
		<h1 class="center title-font">Livable</h1>
		
	      <p id="tag-line" class=" center">Making roomate's lives easier</p>
	   

	</div>
<div class="container">
  <div class="row">
    <div class="grid-example col s12 m6">
      
      <div class="row">
      <h1 class="inline">Register</h1><c:choose> <c:when test="${houseStatus == '1' || houseStatus == '2'}" ><a class="inline" id="back-button" href="/resetHouse"><button type=button class="btn btn-primary">Back</button></a> </c:when>
        </c:choose> 
    </div>
      <form:form method="POST"  action="/registration" modelAttribute="user">
 
      
      <c:choose> 
        <c:when test="${houseStatus == '0'}">
        <div class="row">
	        <div class="input-field col s6">
	        	<a href="/newHouse"><button type=button class="btn btn-primary">New House</button></a>
	        </div>
	         <div class="input-field col s6">
	        	<a href="/joinHouse"><button type=button class="btn btn-primary">Join House</button></a>
	        </div>
        </div>
        </c:when>
       <c:when test="${houseStatus == '1'}">
        <div class="row">
	        <div class="input-field col s6">
	        	<label>House Name:</label>
	    		<input type="text" name="houseName">
	    		<p><c:out value="${noHouseName}"/></p>
	    		<p><c:out value="${noHouse}"/></p>
	        </div>
	         <div class="input-field col s6">
	        	<label>Pin:</label>
	    		<input type="text" name="housePin">
	    		 <p><c:out value="${noHousePin}"/></p>
	    		 <p><c:out value="${wrongPin}"/></p>
	        </div>
	      
        </div>
        </c:when>
        <c:when test="${houseStatus == '2'}">
        <div class="row">
	        <div class="input-field col s12">
	        	<label>House Name:</label>
	    		<input type="text" name="houseName">
	    		 	 <p><c:out value="${noHouseName}"/></p>
	    		 	 <p><c:out value="${houseNameInUse}"/></p>
	        </div>
	         </div>
	     <div class="row">
	        <div class="input-field col s12 m6">
	        	<label>Pin:</label>
	    		<input type="text" name="housePin">
	    		 <p><c:out value="${noHousePin}"/></p>
	        </div>
	        <div class="input-field col s12 m6">
	        	<label>Pin Confirmation:</label>
	    		<input type="text" name="housePinConfirmation">
	    		 <p><c:out value="${pinMismatch}"/></p>
	        </div>
	        
        </div>
        </c:when>
        </c:choose> 
       
        <div class="row">
        <div class="input-field col s12 m6">
            <form:label path="firstName">First Name:</form:label>
            <form:input type="text" path="firstName"/>
            <p><form:errors path="firstName"/></p>
         </div>
        <div class="input-field col s12 m6">
            <form:label path="lastName">Last Name:</form:label>
            <form:input type="text" path="lastName"/>
            <p><form:errors path="lastName"/></p>
         </div>
         </div>
          <div class="row">
       <div class="input-field col s12">
            <form:label path="email">Email:</form:label>
            <form:input type="email" path="email"/>
                 <p><c:out value="${noEmail}"/></p>
                 <p><c:out value="${emailInUse}"/></p>   
         </div>
         </div>
         <div class="row">
        <div class="input-field col s12 m6">
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
      		<p><form:errors path="password"/></p>
         </div>
         
       <div class="input-field col s12 m6">
            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
            <form:password path="passwordConfirmation"/>
            <p><form:errors path="passwordConfirmation"/></p>
         </div>
         </div>
       <c:if test="${houseStatus != '0'}"> <input type="submit" class="btn btn-primary" value="Register"/> </c:if>
         
    </form:form>
    </div>
    
    
    <div class="grid-example col s12 m6">

      <h1>Login</h1>
    <p><c:out value="${error}" /></p>
    <form method="post" action="/login">
       <div class="row">
       <div class="input-field col s12">
            <label for="email">Email</label>
            <input type="text" id="emailLogin" name="email"/>
        </div>
        </div>
        <div class="row">
        <div class="input-field col s12">
            <label for="password">Password</label>
            <input type="password" id="passwordLogin" name="password"/>
        </div>
        </div>
        <input type="submit" class="btn btn-primary" value="Login"/>
    </form> 
  </div>
</div>
</div>
    
  </div>
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
       
        
</body>
</html>