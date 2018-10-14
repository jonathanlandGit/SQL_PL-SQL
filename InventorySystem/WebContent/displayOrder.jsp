<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Order Details</title>
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
			<td>Order No:</td> 
			<td><c:out value="${order.orderNo}" /></td>
		</tr>
		<tr>
			<td>Order Date:</td> 
			<td><fmt:formatDate value="${order.orderDate}" pattern="MM/dd/yyyy"/></td>
		</tr>
		<tr>
			<td>Customer Name:</td> 
			<td><c:out value="${order.customerName}"/></td>
		</tr>
		<tr>
			<td>Ship Date:</td> 
			<td><fmt:formatDate value="${order.shipDate}" pattern="MM/dd/yyyy hh:mm:ss a"/></td>
		</tr>
		<tr>
			<td>Cancel Date:</td> 
			<td><fmt:formatDate value="${order.cancelDate}" pattern="MM/dd/yyyy hh:mm:ss a"/></td>
		</tr>
	</table>
	
	<hr/>
	
	<table>
		<tr>
			<th>Product</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Amount</th>
		</tr>
		<c:set var="totalAmount" value="0"/>
		<c:forEach var="oi" items="${order.orderItems}">
			<tr>
				<td><c:out value="${oi.productDesc}"/></td>
				<td class="number"><fmt:formatNumber value="${oi.price}" minFractionDigits="2" maxFractionDigits="2"/></td>
				<td class="number"><fmt:formatNumber value="${oi.quantity}" minFractionDigits="2" maxFractionDigits="2"/></td>
				<td class="number"><fmt:formatNumber value="${oi.price * oi.quantity}" minFractionDigits="2" maxFractionDigits="2"/></td>
			</tr>
			<c:set var="totalAmount" value="${totalAmount + (oi.price*oi.quantity)}"/>
		</c:forEach>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th colspan="3" align="right">Total Amount</th>
			<td class="number"><fmt:formatNumber value="${totalAmount}" minFractionDigits="2" maxFractionDigits="2"/></td>
		</tr>
	</table>
	
	<jsp:include page="/_pageFooter.jsp"/>
</body>
</html>