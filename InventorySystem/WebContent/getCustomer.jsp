<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Customer Details</title>
	<jsp:include page="/_header.jsp"/>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>
	
	<c:if test="${not empty param.msg}">
		<font color="blue"><c:out value="${param.msg}"/></font>
		<br/><br/>
	</c:if>
	
	<table>
		<tr>
			<td>Customer No:</td> 
			<td><c:out value="${customer.customerNo}" /></td>
		</tr>
		<tr>
			<td>First Name:</td> 
			<td><c:out value="${customer.firstName}" /></td>
		</tr>
		<tr>
			<td>Last Name:</td> 
			<td><c:out value="${customer.lastName}" /></td>
		</tr>
		<tr>
			<td>Birth Date:</td> 
			<td><c:out value="${customer.birthDate}" /></td>
		</tr>
		<tr>
			<td>Email:</td> 
			<td><c:out value="${customer.email}" /></td>
		</tr>
		<tr>
			<td>Address:</td> 
			<td><c:out value="${customer.address}" /></td>
		</tr>
		<tr>
			<td>Home Phone:</td> 
			<td><c:out value="${customer.homePhone}" /></td>
		</tr>
		<tr>
			<td>Mobile:</td> 
			<td><c:out value="${customer.mobile}" /></td>
		</tr>
	</table>
	
	<jsp:include page="/_pageFooter.jsp"/>

</body>
</html>