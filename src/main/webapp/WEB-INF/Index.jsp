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
 <script src="js/main.js"></script>
 <link rel="stylesheet" type="text/css" href="css/style.css">
 <link href="https://fonts.googleapis.com/css?family=Fjalla+One|Acme|Kanit" rel="stylesheet">
</head>
<body>

  <nav>
    <div class="nav-wrapper">
      <a href="/" class="brand-logo"> <img id="logo" src="pictures/logo1.png" alt="Logo"></a>
      <a href="/" data-target="mobile-demo" class="sidenav-trigger"><img id="menu-icon" src="pictures/hamburger.png" alt="Menu"></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="/">Home</a></li>
        <li><a href="/account">Account</a></li>
      </ul>
    </div>
  </nav>

  <ul class="sidenav" id="mobile-demo">
    <li><a href="/account">Account</a></li>
    <li><a href="/about">About</a></li>
  </ul>

	<div class="header">
		<h1 class="center title-font">Livable</h1>
		
	      <p id="tag-line" class=" center">Making roomate's lives easier</p>
	   

	</div>
	<div class="container">

		
	    <div class="row">
	      <div class="grid-example col s12 m4"><span class="flow-text ">Message Board</span><img class="block center" src="pictures/chat.png" alt="Message" height="180" width="180"></div>
	      <div class="grid-example col s12 m8"><span class="flow-text text-align-middle">A place for roomate's to talk, make plans, and discuss who's hygiene habits could use improvement</span></div>
	    </div>
	    <div class="row">
	      <div class="grid-example col s12 m4"><span class="flow-text">Events</span><img class="block" src="pictures/event-new.png" alt="Event" height="200" width="200"></div>
	      <div class="grid-example col s12 m8"><span class="flow-text text-align-middle">Create events and let the house know, hopefully your party will be approved</span></div>
	    </div>
	    <div class="row">
	      <div class="grid-example col s12 m4"><span class="flow-text">Chores</span><img class="block" src="pictures/chores.png" alt="Chore" height="200" width="200"></div>
	      <div class="grid-example col s12 m8"><span class="flow-text text-align-middle">Post chores and get a schedule going on a rotation so the house can be clean (for once)</span></div>
	    </div>
	    <div class="row">
	      <div class="grid-example col s12 m4"><span class="flow-text">Supplies</span><img class="block" src="pictures/supplies.png" alt="Supply" height="200" width="200"></div>
	      <div class="grid-example col s12 m8"><span class="flow-text text-align-middle">Let the house know what supplies are needed. The app will keep track of who has bought the item last, and who's up next</span></div>
	    </div>
	    <div class="row">
	      <div class="grid-example col s12 m4"><span class="flow-text">Bills</span><img class="block" src="pictures/bills.png" alt="Bill" height="200" width="200"></div>
	      <div class="grid-example col s12 m8"><span class="flow-text text-align-middle">Everyones favorite topic. Keep track of the house bills and who's paid</span></div>
	    </div>
    
    </div>
    <div class="footer">
    <p class="center">Livable 2019</p>
    </div>
    
</body>
</html>