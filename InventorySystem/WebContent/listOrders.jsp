<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List Orders</title>
	<jsp:include page="/_header.jsp"/>
	
	<script>
		function setOrderNo(orderNo) {
			document.forms[0].orderNo.value=orderNo;
		}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>
	<form method="post" action="/SalesSystem/OrderServlet">
	<table>
		<tr>
			<th>Order No</th>
			<th>Order Date</th>
			<th>Customer Name</th>
			<th>Ship Date</th>
			<th>Total Amount</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="order" items="${orders}">
			<tr> 
				<td><c:out value="${order.orderNo}"/></td>
				<td><fmt:formatDate value="${order.orderDate}" pattern="MM/dd/yyyy"/></td>
				<td><c:out value="${order.customerName}"/></td>
				<td><fmt:formatDate value="${order.shipDate}" pattern="MM/dd/yyyy hh:mm:ss a"/></td>
				<td class="number"><fmt:formatNumber value="${order.totalAmount}" minFractionDigits="2" maxFractionDigits="2"/></td>
				<td>
					<input type="submit" name="action" value="View" onclick="setOrderNo('<c:out value="${order.orderNo}"/>')"/>
					<c:if test="${order.shipDate==null}">
						<input type="submit" name="action" value="Ship" onclick="setOrderNo('<c:out value="${order.orderNo}"/>')"/>
						<input type="submit" name="action" value="Cancel" onclick="setOrderNo('<c:out value="${order.orderNo}"/>')"/>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="orderNo"/>
  </form>
	<jsp:include page="/_pageFooter.jsp"/>
</body>
</html>