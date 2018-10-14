<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Get Products</title>
	<jsp:include page="/_header.jsp"/>
	
	<script type="text/javascript">
		function setProductId(productId) {
			document.forms[0].productId.value=productId
		}
	</script>
</head>
<body>
  <jsp:include page="/_pageHeader.jsp"/>
  
  <form method="post" action="/SalesSystem/UpdateProductForm">
	<table>
		<tr>
			<th>Product Id</th>
			<th>Description</th>
			<th>Category</th>
			<th>Available Quantity</th>
			<th>Cost</th>
			<th>List Price</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach var="product" items="${products}">
			<tr> 
				<td><c:out value="${product.productId}"/></td>
				<td><c:out value="${product.description}"/></td>
				<td><c:out value="${product.categoryDescription}"/></td>
				<td><c:out value="${product.availableQuantity}"/></td>
				<td><c:out value="${product.cost}"/></td>
				<td><c:out value="${product.listPrice}"/></td>
				<td><input type="submit" value="Update" onclick="setProductId('<c:out value="${product.productId}"/>')"/></td>
			</tr>
		</c:forEach>
	</table>
	<input type="hidden" name="productId"/>
  </form>
  <jsp:include page="/_pageFooter.jsp"/>
</body>
</html> 