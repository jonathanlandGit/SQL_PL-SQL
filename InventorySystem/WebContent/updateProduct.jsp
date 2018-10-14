<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Update Product</title>
	<jsp:include page="/_header.jsp"/>
	<script>
	function setFileName(fileName) {
		document.forms[0].fileName.value=fileName;
	}
	
	function validate() {
			if(document.forms[0].description.value=='') {
				alert('Product Description is required.');
				document.forms[0].description.focus();
				return false;
			}

			if(document.forms[0].availableQuantity.value=='') {
				alert('availableQuantity is required.');
				document.forms[0].availableQuantity.focus();
				return false;
			}
			if(!isNumber(document.forms[0].availableQuantity.value)||document.forms[0].availableQuantity.value<0) {
				alert('Invalid Available Quantity.');
				document.forms[0].availableQuantity.focus();
				return false;
			}

			if(document.forms[0].cost.value=='') {
				alert('Cost is required.');
				document.forms[0].cost.focus();
				return false;
			}
			if(!isNumber(document.forms[0].cost.value)||document.forms[0].cost.value<0) {
				alert('Invalid Cost.');
				document.forms[0].cost.focus();
				return false;
			}

			if(document.forms[0].listPrice.value=='') {
				alert('listPrice is required.');
				document.forms[0].cost.focus();
				return false;
			}
			if(!isNumber(document.forms[0].listPrice.value)||document.forms[0].listPrice.value<0) {
				alert('Invalid List Price.');
				document.forms[0].listPrice.focus();
				return false;
			}
		}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>
	
	<form method="post" onsubmit="return validate()" action="/SalesSystem/UpdateProduct" enctype="multipart/form-data">
	<table>
		<tr>
			<td>Product Id:</td> 
			<td><input type="text" name="productId" readonly="readonly" value='<c:out value="${product.productId}"/>'/></td>
		</tr>
		<tr>
			<td>Product Description:</td> 
			<td><input type="text" name="description" value='<c:out value="${product.description}"/>'/></td>
		</tr>
		<tr>
			<td>Category:</td> 
			<td>
				<select name="category">
					<c:forEach var="category" items="${categories}">
					  <c:choose>
					    <c:when test="${product.categoryCode==category.code}">
					  	  <option value='<c:out value="${category.code}"/>' selected="selected">
							<c:out value="${category.description}"/>
						  </option>
					    </c:when>
					    <c:otherwise>
					      <option value='<c:out value="${category.code}"/>'>
							<c:out value="${category.description}"/>
						  </option>
					    </c:otherwise>
					  </c:choose>
						
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>Available Quantity:</td> 
			<td><input type="text" name="availableQuantity" value='<c:out value="${product.availableQuantity}"/>'/></td>
		</tr>
		<tr>
			<td>Cost:</td> 
			<td><input type="text" name="cost" value='<c:out value="${product.cost}"/>'/></td>
		</tr>
		<tr>
			<td>List Price:</td> 
			<td><input type="text" name="listPrice" value='<c:out value="${product.listPrice}"/>'/></td>
		</tr>
		<c:if test="${not empty manuals}">
			<tr>
				<td>Existing Manuals:</td> 
				<td>
					<c:forEach var="manual" items="${manuals}">
						<a href='/SalesSystem/MongoDBFileServlet?productId=<c:out value="${manual.productId}"/>&fileName=<c:out value="${manual.fileName}"/>'>
							<c:out value="${manual.fileName}"/>
						</a>
						<input type="submit" name="action" value="Delete" onclick="setFileName('<c:out value="${manual.fileName}"/>')"/>
						<br/>
					</c:forEach>
				</td>
			</tr>
		</c:if>
		<tr>
			<td>Manual 1:</td>
			<td><input type="file" name="manual1" size="50"/></td>
		</tr>
		<tr>
			<td>Manual 2:</td>
			<td><input type="file" name="manual2" size="50"/></td>
		</tr>
		<tr>
			<td>Manual 3:</td>
			<td><input type="file" name="manual3" size="50"/></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" name="action" value="Update Product"/></td>
		</tr>
	</table>
	<input type="hidden" name="fileName"/>
  </form>
	<jsp:include page="/_pageFooter.jsp"/>

</body>
</html>