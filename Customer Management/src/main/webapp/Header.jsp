<%@page import="com.example.utils.JWTUtils"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.models.Customer" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<link rel="stylesheet" href="CSS/header.css">
<%
    HttpSession sess = request.getSession(false);
	String token = (String) session.getAttribute("jwtToken");
	//if (token == null) {
	//    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
	//    return;
	//}

	
	String role = JWTUtils.validateToken(token).get("role", String.class);
   //String role = (String) request.getAttribute("role");
    List<String> customerAttributes = List.of("First_Name","City", "Email", "Phone"); // Populate from DAO if needed
%>

<div class="header">
<% if ("ADMIN".equals(role)) { %>
        <a href="customer-details.jsp" class="btn btn-success">Add Customer</a>
    <% } %>
   <div> <form action="CustomerServlet" method="GET" class="search-form">
        <select name="searchAttribute" class="dropdown">
            <option value="">Search By</option>
            <% for (String attribute : customerAttributes) { %>
                <option value="<%= attribute %>"><%= attribute %></option>
            <% } %>
        </select>
        
        <input type="text" name="searchValue" placeholder="Enter value to search" class="search-box" required>
        
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
 </div>
 <div>
    <form action="CustomerServlet" method="GET">
        <button type="submit" name="action" value="sync" class="btn btn-secondary">Sync</button>
        
          </form>
    
    </div>
        <%if(sess !=null){ %>
    <a href="LogoutServlet" class="btn btn-danger" >Logout</a>
<%
} else { 
%>
<a href="login.jsp" class="btn btn-primary">Login</a>
<%
}
%>
  
    
    
    
</div>
