<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpooling.constants.Constant"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />

<title>CarPooling.com</title>
<link href="css/custom/main.css" rel="stylesheet">
<link href="css/hot-sneaks/jquery-ui-1.9.2.custom.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jquery.form.js"></script>
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>

	<div id="container">
		<div id="header">
			<%-- <%
			String email = null;
			Cookie cookies[] = request.getCookies();
			if (cookies != null) {
				for (Cookie ck : cookies) {
					if ("user".equals(ck.getName())) {
						email = ck.getValue();
					}
				}
			}

			if (email != null)
				out.println("<a href='/CarPoolingApp/bid/?logout'>Logout</a>");
			else
				out.println("<a href='/CarPoolingApp/login.jsp'>Login</a>");
			%> --%>
			<%-- <% 
			if(Constant.loginBean==null)  
				out.println("Login Bean is null</br>");
			if(Constant.loginBean!=null){ 
				out.print("Login Bean is not null");
				if(Constant.loginBean.isAuthenticated())
					out.println("Authenticated");
			}
			%> --%>

			

		</div>
		<div id="main">

			<div id="selectors" class="ui-corner-all">
				<form id="offers" action="/CarPoolingApp/RideOffersServlet"
					method="get">
					<table>
						<tr>
							<td>Date:</td>
							<td>Departure City:</td>
							<td>Arrival City:</td>
						</tr>
						<tr>
							<td><input type="text" id="datepicker" name="date">
							</td>
							<td><input id="autocompleteDept" title="type &quot;a&quot;"
								name="departure"></td>
							<td><input id="autocompleteArrv" title="type &quot;a&quot;"
								name="arrival"></td>
							<td><input id="submit" type="submit" value="Search">
							</td>
							<%
							if(Constant.loginBean!=null && Constant.loginBean.isAuthenticated()) 
								out.println("<td><a href='/CarPoolingApp/profile.jsp?ID=" + Constant.loginBean.getId() + "'>Go to your profile</a></td> <td><a href='/CarPoolingApp/bid/?logout'>Logout</a></td>");
							else
								out.println("<td><a id='loginbutton'>Login</a></td><td><div class='res'><a id='signupbutton'>Sign Up</a></div><td>");
							
							%>
						</tr>
					</table>
				</form>

			</div>
			
			<div id="logindialog">
			    <p class="validateTips">All form fields are required.</p>
			
				<form id="login" action="/CarPoolingApp/LoginServlet" method="get">
					<fieldset>
						<label for="email">Email:</label> <input type="text" name="email"
							id="email" value="" class="text ui-widget-content ui-corner-all" /> 
						<label for="password">Password:</label> <input type="password" name="password"
							id="password" value="" class="text ui-widget-content ui-corner-all" />
						
					</fieldset>
				</form>
			</div>
			
			<div id="signupdialog">
			    <p class="validateTips">All form fields are required.</p>
			
				<form id="signup" action="/CarPoolingApp/RegistrationServlet" method="post">
					<fieldset>
						<label for="name">Name:</label> <input type="text" name="name"
							id="name" value="" class="text ui-widget-content ui-corner-all" /> 
						<label for="email">Email:</label> <input type="text" name="email"
							id="signupemail" value="" class="text ui-widget-content ui-corner-all" /> 
						<label for="password">Password:</label> <input type="password" name="password"
							id="signuppassword" value="" class="text ui-widget-content ui-corner-all" />
						<label for="age">Age:</label> <input type="text" name="age"
							id="age" value="" class="text ui-widget-content ui-corner-all" />
						<label for="gender">Gender:</label> <select name="gender">
							<option value="0">Female</option>
							<option value="1">Male</option>
							</select>
						<label for="phone">Phone No:</label> <input type="text" name="phone"
							id="phone" value="" class="text ui-widget-content ui-corner-all" />
						<label for="carmodel">Car Model:</label> <input type="text" name="carmodel"
							id="carmodel" value="" class="text ui-widget-content ui-corner-all" />			
						
					</fieldset>
				</form>
			</div>
			
		</div>
		
		<div id='img' style='margin: 20px 100px;'>
				<img  style='margin-bottom: 30px;' src='img/bannerfans_6235183.jpg'/>
				<img  style='height: 300px;' src='img/car-sharing.jpg'/> 
		</div>


		<div id="result">
		
			<h2 class="demoHeaders"></h2>
			<div id="accordion"></div>
			

		</div>
		<div id="footer">January, 2013 - Ece Mandiracioglu</div>
	</div>
</body>
</html>