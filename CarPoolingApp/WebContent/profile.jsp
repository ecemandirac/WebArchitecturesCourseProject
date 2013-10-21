<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.carpooling.servlet.UserPageServlet" %>
<%@ page import="java.util.*" %>
<%@ page import="com.carpooling.constants.Constant" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />  

<title>Profile page</title>
<link href="css/custom/main.css" rel="stylesheet">
<link href="css/hot-sneaks/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jquery.form.js"></script>
<script type="text/javascript" src="js/profile.js"></script>
 
</head>
<body>


	<div id="container">
		<div id="header">
			
			

		</div>
		<div id="main">

		<div id="selectors" class="ui-corner-all">
		<table id="menu">
		<tr>
			<% 

				out.println("<td><a href='/CarPoolingApp'>Home</a></td>");
			
				if(Constant.loginBean!=null && Constant.loginBean.isAuthenticated()) 
					out.println("<td><a href='/CarPoolingApp/profile.jsp?ID=" + Constant.loginBean.getId() + "'>Go to your profile</a></td> <td><a href='/CarPoolingApp/bid/?logout'>Logout</a></td>");
				else
					out.println("<td><a id='loginbutton'>Login</a></td><td><div class='res'><a id='signupbutton'>Sign Up</a></div><td>");
				
			%>
			</tr>
		</table>
		</div>
			<div id="info-container" class="info-container-male">
				<div id="img">
					<img alt='' src='img/driver_m.png'>
				</div>
				<div id="info">
					<div id=name></div>
					<div id=age></div>
					<div id=phone></div>
					<div id=carmodel></div>
					<div id="editprof"></div>
				</div>
			</div>
			
				
			<div style='margin-left: 30px;' id="addbutton" class='new-offer-container'>
					<!-- <input type='button' id='dialog_link' value='Add new offer'> -->
			</div>
			

			<div id="users-contain" class="ui-widget">
			</div>
			
			<div id="dialog">
			    <p class="validateTips">All form fields are required.</p>
			
				<form id="newoffer" action="/CarPoolingApp/InsertOfferServlet" method="get">
					<fieldset>
						<label for="from">From</label> <input type="text" name="from"
							id="from" value="Bolzano" class="text ui-widget-content ui-corner-all" /> 
						<label for="to">To</label> <input type="text" name="to"
							id="to" value="Trento" class="text ui-widget-content ui-corner-all" />
						<label for="date">Date</label> <input type="text"
							name="date" id="date" value="2013-01-08" class="text ui-widget-content ui-corner-all" />
						<label for="time">Time</label> <input type="text"
							name="time" id="time" value="13:00:00" class="text ui-widget-content ui-corner-all" />
						<label for="deadline">Deadline</label> <input type="text" name="deadline1"
							id="deadline1" value="" class="text ui-widget-content ui-corner-all" /> 
							<input type="text" name="deadline2"
							id="deadline2" value="" class="text ui-widget-content ui-corner-all" /> 
						<label for="description">Description</label> <input type="text" name="description"
							id="description" value="let me know" class="text ui-widget-content ui-corner-all" />
						<label for="freeseats">Number of free seats:</label> <input type="text"
							name="freeseats" id="freeseats" value="3" class="text ui-widget-content ui-corner-all" />
						
					</fieldset>
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
			<div id="editdialog">
			    <p class="validateTips">All form fields are required.</p>
			
				<form id="edit" action="/CarPoolingApp/EditServlet" method="post">
					<fieldset>
						<label for="oldpassword">Old Password:</label> <input type="password" name="oldpassword"
							id="oldpassword" value="" class="text ui-widget-content ui-corner-all" /> 
						 
						<label for="newpassword">New Password:</label> <input type="password" name="newpassword"
							id="newpassword" value="" class="text ui-widget-content ui-corner-all" />
					
						<label for="phone">Phone No:</label> <input type="text" name="phone"
							id="phone" value="" class="text ui-widget-content ui-corner-all" />
						<label for="carmodel">Car Model:</label> <input type="text" name="carmodel"
							id="carmodel" value="" class="text ui-widget-content ui-corner-all" />			
						
					</fieldset>
				</form>
			</div>
			
			
		</div>
		</div>
		
		<div id="footer">January, 2013 - Ece Mandiracioglu</div>
	</div>
</body>
</html>