<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form id="login-form" action="j_security_check" method="post">
    <table>
        <tbody>
            <tr>
                <td><label for="username">User name:&nbsp;</label></td>
                <td><input type="text" name="j_username" id="j_username" /></td>
            </tr>
            <tr>
                <td><label for="password">Password:&nbsp;</label></td>
                <td><input type="password" name="j_password" id="j_password" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" id="submit" name="submit" value="Login"></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><a href="/CarPoolingApp/registration.jsp">Would you like to register?</a></td>
            </tr>
        </tbody>
    </table>
</form>
</body>
</html>