<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create Order</title>
	<jsp:include page="/_header.jsp"/>
	
	<script>
		function validate() {
			if(document.forms[0].orderDate.value=='') {
				alert('Order Date is required.');
				document.forms[0].orderDate.focus();
				return false;
			}

			if(!isDate(document.forms[0].orderDate.value)) {
				alert('Invalid Order Date.');
				return false;
			}

			var flag = false;
			for(var i=0; i<5; i++) {
				var product = document.getElementById('p' + i);
				var quantity = document.getElementById('q' + i);
				if(product.value != '') {
					if(quantity.value == '') {
						alert('Quantity is required.');
						quantity.focus();
						return false;
					}
					if(!isNumber(quantity.value) || quantity.value <= 0) {
						alert('Invalid quantity.');
						quantity.focus();
						return false;
					}
					flag = true;
				}
			}
			
			if(!flag) {
				alert('Atleast 1 product must be selected.');
				return false;
			}
		}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp" />

	<form method="post" onsubmit="return validate()" action="/SalesSystem/CreateOrder">
		<table>
			<tr>
				<td>Order Date (MM/dd/yyyy):</td>
				<td><input type="text" name="orderDate" /></td>
			</tr>
			<tr>
				<td>Customer:</td>
				<td>
					<select name="customerNo">
						<c:forEach var="customer" items="${customers}">
							<option value='<c:out value="${customer.customerNo}"/>'>
								<c:out value="${customer.firstName} ${customer.lastName}"/>
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
	  			<th>Product [Price]</th>
	  			<th>Quantity</th>
	  		</tr>
	  		<tr>
	  			<td>
					<select id="p0" name="product0">
						<option value=''/>
						<c:forEach var="product" items="${products}">
							<option value='<c:out value="${product.productId}"/>'>
								<c:out value="${product.description} [${product.listPrice}]"/>
							</option>
						</c:forEach>
					</select>
				</td>
				
				<td>
					<input id="q0" type="text" name="quantity0"/>
				</td>
	  		</tr>
	  		<tr>
	  			<td>
					<select id="p1" name="product1">
						<option value=''/>
						<c:forEach var="product" items="${products}">
							<option value='<c:out value="${product.productId}"/>'>
								<c:out value="${product.description} [${product.listPrice}]"/>
							</option>
						</c:forEach>
					</select>
				</td>
				
				<td>
					<input id="q1" type="text" name="quantity1"/>
				</td>
	  		</tr>
	  		<tr>
	  			<td>
					<select id="p2" name="product2">
						<option value=''/>
						<c:forEach var="product" items="${products}">
							<option value='<c:out value="${product.productId}"/>'>
								<c:out value="${product.description} [${product.listPrice}]"/>
							</option>
						</c:forEach>
					</select>
				</td>
				
				<td>
					<input id="q2" type="text" name="quantity2"/>
				</td>
	  		</tr>
	  		<tr>
	  			<td>
					<select id="p3" name="product3">
						<option value=''/>
						<c:forEach var="product" items="${products}">
							<option value='<c:out value="${product.productId}"/>'>
								<c:out value="${product.description} [${product.listPrice}]"/>
							</option>
						</c:forEach>
					</select>
				</td>
				
				<td>
					<input id="q3" type="text" name="quantity3"/>
				</td>
	  		</tr>
	  		<tr>
	  			<td>
					<select id="p4" name="product4">
						<option value=''/>
						<c:forEach var="product" items="${products}">
							<option value='<c:out value="${product.productId}"/>'>
								<c:out value="${product.description} [${product.listPrice}]"/>
							</option>
						</c:forEach>
					</select>
				</td>
				
				<td>
					<input id="q4" type="text" name="quantity4"/>
				</td>
	  		</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Create Order"/>
				</td>
			</tr>

		</table>
	</form>

	<jsp:include page="/_pageFooter.jsp" />

</body>
</html>
