<%@page import="com.example.utils.JWTUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="CSS/customerdetails.css">
</head>

<%
//HttpSession sess = request.getSession(false);
//String token = (String) session.getAttribute("jwtToken");
//if (token == null) {
//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
//    return;
//}



//String role = JWTUtils.validateToken(token).get("role", String.class); %>

<body>
<%@include file="Header.jsp" %>
<%
if (!"ADMIN".equals(role)) {
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
    return;
}
%>
<form action="AdminServlet" method="post" id="101">
<input type="hidden" name="action" value="add"> 
<div class="form-group">  <input class="input" type="text" id="first_name" name="first_name" required placeholder="First Name"> </div>
 <div class="form-group"> <input class="input" type="text" id="last_name" name="last_name" required placeholder="Last Name"> </div>

<div class="form-group"> <input class="input" type="text" id="address" name="address" required placeholder="Address"> </div>

<div class="form-group">  <input class="input" type="text" id="city" name="city" required placeholder="City"> </div> 

<div class="form-group"> <input class="input" type="text" id="state" name="state" required placeholder="State"> </div> 

<div class="form-group">  <input class="input" type="email" id="email" name="email" required placeholder="Email"> </div>

<div class="form-group"> <input class="input" type="text" id="phone" name="phone" required placeholder="Phone"> </div> 

<div class="form-group"><input type="hidden" name="createdBy" value="1"> 

<button type="submit" class="btn btn-primary">Add Customer</button></div>


        <!--  <input type="hidden" name="action" value="add">
        <label>First Name:</label><input type="text" name="first_name" required>
        
        <label>Last Name:</label> <input type="text" name="last_name" required>
       
        <label>Address:</label><input type="text" name="address" required>
        
        <label>City:</label><input type="text" name="city" required>
        
        <label>State:</label><input type="text" name="state" required>
        
        <label>Email:</label><input type="email" name="email" required>
        
        <label>Phone:</label><input type="text" name="phone" required>
        
        <input type="hidden" name="createdBy" value="1">
        <button type="submit">Add Customer</button>-->
    </form>
<footer>
    &copy; 2024 Customer Management
</footer>
</body>
</html>