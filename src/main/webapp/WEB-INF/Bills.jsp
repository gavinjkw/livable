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
<h3>Bills</h3>


<table id="bill-board">
 <thead>
      <tr class="bills">   
       
            <th scope="col">Name</th>
            <th scope="col">Bill Total</th>
            <th scope="col">Individual Due</th>
            <th scope="col">Creator</th>
            <th scope="col">Action</th>
        </tr>
    </thead>
    <tbody>


    <c:forEach items="${house.bills}" var="bill">

	<tr class="event-item"> 

	<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${bill.total}" var="total"/>
	<fmt:formatNumber type="number" maxFractionDigits="2" minFractionDigits="2" value="${bill.indivDue}" var="eachOwed"/>

	<td class="bills"><c:out value="${bill.name}"/></td>
	<td class="bills">$<c:out value="${total}"/></td>
	<td class="bills">$<c:out value="${eachOwed}"/></td>
	<td class="bills"><c:out value="${bill.user.firstName}"/></td>
	
	<td class="bills">
	<c:choose>
	
	<c:when test="${bill.user.id == user.id}">
		<form class="inline" action="/delete_bill/${bill.id}" method="post">
		<input type="hidden" name="_method" value="delete">
		<input class="btn btn-small" type="submit" value="Delete">
		</form>
		
	</c:when>	
	<c:otherwise>
	
	<c:choose>
	<c:when test="${bill.users.contains(user)}">
	<form class="inline" action="/paid/${user.id}/${bill.id}" method="post">
		<input type="hidden" name="_method" value="delete">
		<input class="btn btn-small blue" type="submit" value="Pay">
		</form>
	</c:when>	
	<c:otherwise>	
		<input id="green" class="btn btn-small disabled" type="button" value="Paid">
		</c:otherwise>
		</c:choose>
		
	</c:otherwise>
	</c:choose>
	</tr>
	
	<tr>
	
	<td class="bills-paid bills" colspan="5">Unpaid:
	 <c:forEach items="${bill.users}" var="user">
     <div class="inline" id="bill-user">  ${user.firstName} ${user.lastName} <form class="inline" action="/paid/${user.id}/${bill.id}" method="post">
		<input type="hidden" name="_method" value="delete">
		<input class="x-box" type="submit" value="X">
		</form></div>
	</c:forEach>
	</td>
	</tr>
	
	</c:forEach>
	
	
	
	</tbody>
	</table>
	</div>



 <div class="grid-example col s12 m4">

<h3>Add Bill</h3>
 <form:form method="POST" action="/bills" modelAttribute="billObj">
        <div class="row short">
        <div class="input-field col s12">
            <form:label path="name">Name: </form:label>
            <form:input type="text" path="name"/>
            <p><form:errors path="name"/></p>
        </div>
         </div>
      
      <div class="row short">
        <div class="input-field col s12">
            <form:label path="total">Total: </form:label>
            <form:input step="0.01" type="number" path="total"/>
            <p><form:errors path="total"/></p>
        </div>
         </div>
         
         <div class="row short">
        <div class="input-field col s12">
            <label path="total">Individual Due: </label>
            <input step="0.01" type="number" path="total"/>
      
            <span class="helper-text" data-error="wrong" data-success="right">Optional. If not included, the total will be split among roomate's added.</span>
        </div>
         </div>
         
         <form:hidden path="house" value = "${house.id}"/>
         <form:hidden path="user" value = "${user.id}"/>
       
<div class="row short">
        <div class="input-field col s12">
    <select multiple name="debts">
     <label>Add Roomates:</label>
      <option value="" disabled selected>Add Roomates</option>
        <c:forEach items="${house.users}" var="user">
      <option value="${user.id}"><c:out value="${user.firstName} ${user.lastName}"/> </option>
	   </c:forEach>
    </select>
   
  </div>
       
         </div>
        <input type="submit" class="btn btn-primary" value="Add Bill"/>
    
    
    
    </form:form>


 </div>
  </div>
</div>
    
    <div class="footer">
    <p class="center text-align-middle">Livable 2019</p>
    </div>
  
</body>
</html>