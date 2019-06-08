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
 <script src="js/main.js"></script>
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
        <li><a href="/about">About</a></li>
        <li><a href="/myhouse">My House</a></li>
        <li><a href="/home">Home</a></li>
        <li><a href="/logout">Logout</a></li>
  </ul>

	<div class="header">
		<h1 class="center title-font">Livable</h1>
		

	   

	</div>

<div class="container">

 <div id="house-info" class="row">
      <div class="grid-example col s6 m6"><span class="flow-text">House Information</span>
      <p>Name: <c:out value="${house.name}"/></p>
      <fmt:formatDate value="${house.createdAt}" pattern ="MMMM, dd yyyy" var="newdatevar" />
      <p>Start Date: <c:out value="${newdatevar}"/></p>
      <p>Number of Residents: <c:out value="${fn:length(house.users)}"/>
      
      </div>
      
      
      <div class="grid-example col s6 m6"><span class="flow-text">Residents</span>
      
       <table class="striped">
        <thead> 
          <tr>
              <th>Name</th>
              <th>Join Date</th>
          </tr>
        </thead>

        <tbody>
         <c:forEach items="${house.users}" var="user">
          <tr>
            <td><c:out value="${user.firstName} ${user.lastName}"/></td>
            <fmt:formatDate value="${user.createdAt}" pattern ="MMMM, dd yyyy" var="newdateUser" />
            <td><c:out value="${newdateUser}"/></td>
          </tr>
     </c:forEach>
        </tbody>
      </table>
            
      
      </div>
     
    </div>

    
 </div>
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
    
</body>
</html>