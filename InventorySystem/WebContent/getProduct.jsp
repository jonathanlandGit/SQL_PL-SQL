<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Product Details</title>
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
			<td>Product Id:</td> 
			<td><c:out value="${product.productId}" /></td>
		</tr>
		<tr>
			<td>Product Description:</td> 
			<td><c:out value="${product.description}" /></td>
		</tr>
		<tr>
			<td>Category:</td> 
			<td><c:out value="${product.categoryDescription}" /></td>
		</tr>
		<tr>
			<td>Available Quantity:</td> 
			<td><c:out value="${product.availableQuantity}" /></td>
		</tr>
		<tr>
			<td>Cost:</td> 
			<td><c:out value="${product.cost}" /></td>
		</tr>
		<tr>
			<td>List Price:</td> 
			<td><c:out value="${product.listPrice}" /></td>
		</tr>
		<tr>
			<td>Manuals:</td> 
			<td>
				<c:forEach var="manual" items="${manuals}">
					<a href='/SalesSystem/MongoDBFileServlet?productId=<c:out value="${manual.productId}"/>&fileName=<c:out value="${manual.fileName}"/>'>
						<c:out value="${manual.fileName}"/>
					</a>
					<br/>
				</c:forEach>
			</td>
		</tr>
	</table>
	
	<jsp:include page="/_pageFooter.jsp"/>
</body>
</html>