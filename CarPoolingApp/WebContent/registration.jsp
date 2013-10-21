<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>

<form id="reg-form" action="/CarPoolingApp/RegistrationServlet" method="post">
    <table>
        <tbody>
            <tr>
                <td><label for="email">E-mail:&nbsp;</label></td>
                <td><input type="text" name="email" id="email" /></td>
            </tr>
            <tr>
                <td><label for="password">Password:&nbsp;</label></td>
                <td><input type="password" name="password" id="password" /></td>
            </tr>
            <tr>
                <td><label for="name">Name:&nbsp;</label></td>
                <td><input type="text" name="name" id="name" /></td>
            </tr>
             <tr>
                <td><label for="age">Age:&nbsp;</label></td>
                <td><input type="text" name="age" id="age" /></td>
            </tr>
            <tr>
                <td><label for="gender">Gender:&nbsp;</label></td>
					<td><select name="gender">
							<option value="0">Female</option>
							<option value="1">Male</option>
					</select>
				</td>
			</tr>
			<tr>
                <td><label for="carmodel">Car Model:&nbsp;</label></td>
                <td><input type="text" name="carmodel" id="carmodel" /></td>
            </tr>
            <% out.print("<input type='hidden' name='referer' id='referer' value='"+ request.getHeader("referer") +"'/>"); %>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" id="submit" name="submit" value="Submit"></td>
            </tr>
            
        </tbody>
    </table>
</form>

</body>
</html>