<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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

<title>Offer Page</title>
<link href="css/custom/main.css" rel="stylesheet">
<link href="css/hot-sneaks/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="jquery.form.js"></script>
<script type="text/javascript" src="js/offer.js"></script>  
</head>
<body>
<div id="container">
	<div id="header">
			
			
	</div>
	<div id="main">
	<div id="selectors" class="ui-corner-all">
		<table id="menu" >
		<tr>
			<% 
			
				out.println("<td><a href='/CarPoolingApp'>Home</a></td>");
				
				if(Constant.loginBean!=null && Constant.loginBean.isAuthenticated()) 
					out.println("<td><a href='/CarPoolingApp/profile.jsp?ID=" + Constant.loginBean.getId() + "'>Go to your profile</a></td><td><a href='/CarPoolingApp/bid/?logout'>Logout</a></td<>");
				else
					out.println("<td><a id='loginbutton'>Login</a></td><td><div class='res'><a id='signupbutton'>Sign Up</a></div><td>");
				
				

			%>
			</tr>
		</table>
		</div>
	
		<table id="offercontainer" class="ui-widget ui-widget-content">
			<tr>
				<td>From:
				</td>
				<td id="from">
				</td>
			</tr>
			<tr>
				<td>To:
				</td>
				<td id="to">
				</td>
			</tr>
			<tr>
				<td>Date:
				</td>
				<td id="date">
				</td>
			</tr>
			<tr>
				<td>Time: 
				</td>
				<td id="time">
				</td>
			</tr>
			<tr>
				<td>Driver:
				</td>
				<td id="driver">
				</td>
			</tr>
			<tr>
				<td>Free Seats:
				</td>
				<td id="freeseats">
				</td>
			</tr>
			<tr>
				<td>Description:
				</td>
				<td id="description">
				</td>
			</tr>
			<tr>
				<td>Deadline for bidding:
				</td>
				<td id="deadline">
				</td>
			</tr>
			<tr>
				<td>Would you like to bid for a seat?
				</td>
				<td>
				
				
				<form id="bid" action="/CarPoolingApp/BiddingServlet" method="get">
					<input type="text" name="amount">
					<input type="submit" id="submit" value="Bid">
				
				</form>	
					
				</td>		
			</tr>
			<tr><td></td>
			<td id="bidresult"></td>
			</tr>
		</table>
		<div id="users-contain" class="ui-widget">
				<h4>Bids:</h4>
				<table id="bids" class="ui-widget ui-widget-content">
					
					
				</table>
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
	<div id="footer">January, 2013 - Ece Mandiracioglu</div>
</div>
</body>
</html>