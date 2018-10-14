<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert Customer</title>
	<jsp:include page="/_header.jsp"/>
	
	<script language="JavaScript">
		function validate() {
			if(document.forms[0].firstName.value=='') {
				alert('First Name is required.');
				document.forms[0].firstName.focus();
				return false;
			}

			if(document.forms[0].lastName.value=='') {
				alert('Last Name is required.');
				document.forms[0].lastName.focus();
				return false;
			}

			if(document.forms[0].birthDate.value!='') {
				if(!isDate(document.forms[0].birthDate.value)) {
					alert('Invalid Birth Date.');
					return false;
				}
			}

			if(document.forms[0].email.value=='') {
				alert('Email is required.');
				document.forms[0].email.focus();
				return false;
			}
			
			if(document.forms[0].address.value=='') {
				alert('Address is required.');
				document.forms[0].address.focus();
				return false;
			}
			
			if(document.forms[0].homePhone.value=='') {
				alert('Home Phone is required.');
				document.forms[0].homePhone.focus();
				return false;
			}
			if(!isNumber(document.forms[0].homePhone.value)) {
				alert('Invalid Home Phone.');
				document.forms[0].homePhone.focus();
				return false;
			}
			
			if(document.forms[0].mobile.value!='') {
				if(!isNumber(document.forms[0].mobile.value)) {
					alert('Invalid Mobile.');
					document.forms[0].mobile.focus();
					return false;
				}	
			}	
		}
	</script>
</head>
<body>
	<jsp:include page="/_pageHeader.jsp" />

	<form method="post" onsubmit="return validate()" action="/SalesSystem/InsertCustomer">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" /></td>
			</tr>
			<tr>
				<td>Birth Date (MM/dd/yyyy): *</td>
				<td><input type="text" name="birthDate" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>Home Phone:</td>
				<td><input type="text" name="homePhone" /></td>
			</tr>
			<tr>
				<td>Mobile: *</td>
				<td><input type="text" name="mobile" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<br/>
					&nbsp;&nbsp;&nbsp;&nbsp; * <i>indicates optional fields</i>
					<br/>&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Insert Customer"/>
				</td>
			</tr>
		</table>
	</form>

	<jsp:include page="/_pageFooter.jsp" />

</body>
</html>