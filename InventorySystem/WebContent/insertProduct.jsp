<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert Product</title>
	<jsp:include page="/_header.jsp" />
	<script>
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
	<jsp:include page="/_pageHeader.jsp" />

	<form method="post" onsubmit="return validate()" action="/SalesSystem/InsertProduct">
		<table>
			<tr>
				<td>Product Description:</td>
				<td><input type="text" name="description" /></td>
			</tr>
			<tr>
				<td>Category:</td>
				<td>
					<select name="category">
						<c:forEach var="category" items="${categories}">
							<option value='<c:out value="${category.code}"/>'>
								<c:out value="${category.description}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>Available Quantity:</td>
				<td><input type="text" name="availableQuantity" /></td>
			</tr>
			<tr>
				<td>Cost:</td>
				<td><input type="text" name="cost" /></td>
			</tr>
			<tr>
				<td>List Price:</td>
				<td><input type="text" name="listPrice" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Insert Product" /></td>
			</tr>

		</table>
	</form>

	<jsp:include page="/_pageFooter.jsp" />
</body>
</html>