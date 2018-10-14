<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Get Orders</title>
	<jsp:include page="/_header.jsp"/>
	<script>
		function validate() {
			if(document.forms[0].fromDate.value=='') {
				alert('From Date is required.');
				document.forms[0].fromDate.focus();
				return false;
			}
			if(!isDate(document.forms[0].fromDate.value)) {
				alert('Invalid From Date.');
				return false;
			}

			if(document.forms[0].toDate.value=='') {
				alert('To Date is required.');
				document.forms[0].toDate.focus();
				return false;
			}
			if(!isDate(document.forms[0].toDate.value)) {
				alert('Invalid To Date.');
				return false;
			}
		}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp"/>
	
	<form method="post" onsubmit="return validate()" action="/SalesSystem/GetOrders">
		<table>
			<tr>
				<td>From Date (MM/dd/yyyy):</td>
				<td><input type="text" name="fromDate" /></td>
			</tr>
			<tr>
				<td>To Date (MM/dd/yyyy):</td>
				<td><input type="text" name="toDate" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Get Orders" /></td>
			</tr>
		</table>
	</form>
	
	<jsp:include page="/_pageFooter.jsp"/>
</body>
</html>