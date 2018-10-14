<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Get Customers</title>
	<jsp:include page="/_header.jsp"/>
	<script type="text/javascript">
			function setCustomerNo(customerNo) {
				document.forms[0].customerNo.value=customerNo
			}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>

  <form method="post" action="/SalesSystem/UpdateCustomerForm">
	<table>
		<tr>
			<th>Customer No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Birth Date</th>
			<th>Email</th>
			<th>Address</th>
			<th>Home Phone</th>
			<th>Mobile</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="customer" items="${customers}">
			<tr> 
				<td><c:out value="${customer.customerNo}"/></td>
				<td><c:out value="${customer.firstName}"/></td>
				<td><c:out value="${customer.lastName}"/></td>
				<td><fmt:formatDate value="${customer.birthDate}" pattern="MM/dd/yyyy"/></td>
				<td><c:out value="${customer.email}"/></td>
				<td><c:out value="${customer.address}"/></td>
				<td><c:out value="${customer.homePhone}"/></td>
				<td><c:out value="${customer.mobile}"/></td>
				<td><input type="submit" value="Update" onclick="setCustomerNo('<c:out value="${customer.customerNo}"/>')"/></td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="customerNo"/>
  </form>
  
  <jsp:include page="/_pageFooter.jsp"/>
</body>
</html>