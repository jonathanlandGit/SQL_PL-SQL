<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sales by Category</title>
	<jsp:include page="/_header.jsp"/>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>
	
	<table>
		<tr>
			<th>Category</th>
			<th>Sales</th>
			<th>Cost</th>
			<th>Profit</th>
		</tr>
		<c:set var="sales" value="0"/>
		<c:set var="cost" value="0"/>
		<c:set var="profit" value="0"/>
		<c:forEach var="cs" items="${categorySales}">
			<tr>
				<td><c:out value="${cs.categoryDesc}"/></td>
				<td class="number"><fmt:formatNumber value="${cs.totalSalePrice}" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td class="number"><fmt:formatNumber value="${cs.totalCost}" maxFractionDigits="2" minFractionDigits="2"/></td>
				<td class="number"><fmt:formatNumber value="${cs.profit}" maxFractionDigits="2" minFractionDigits="2"/></td>
			</tr>
			<c:set var="sales" value="${sales + cs.totalSalePrice}"/>
			<c:set var="cost" value="${cost + cs.totalCost}"/>
			<c:set var="profit" value="${profit + cs.profit}"/>
		</c:forEach>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<th>Total</th>
			<th><fmt:formatNumber value="${sales}" maxFractionDigits="2" minFractionDigits="2"/></th>
			<th><fmt:formatNumber value="${cost}" maxFractionDigits="2" minFractionDigits="2"/></th>
			<th><fmt:formatNumber value="${profit}" maxFractionDigits="2" minFractionDigits="2"/></th>
		</tr>
		
	</table>
	
	<jsp:include page="/_pageFooter.jsp"/>
</body>
</html>