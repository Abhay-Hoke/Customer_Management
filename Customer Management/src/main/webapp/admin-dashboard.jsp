<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="CSS/admin-dashboard.css">
</head>
<body>
    <h2>Dashboard</h2>
<%@ include file="Header.jsp"%>

<%

    String t = (String) session.getAttribute("jwtToken");
	response.setHeader("Authorization", "Bearer " + t);

	String r = JWTUtils.validateToken(t).get("role", String.class);
	
	//String r = (String) request.getAttribute("role");
	//System.out.println(r);
	
	if (!"ADMIN".equals(r)) {
	    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
	    return;
	}
          
        %>
  
  
    <h3>Customer List</h3>
    <table class="table table-bordered">
    <thead>
        <tr>
             <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>City</th>
            <th>State</th>
            <th>Email</th>
            <th>Phone</th>
            <% if ("ADMIN".equals(role)) { %>
                <th>Actions</th>
            <% } %>
        </tr>
    </thead>
    <%  List<Customer> customers = (List<Customer>) request.getAttribute("customers"); // Retrieved from Servlet
    
    if (customers != null) {
       for (Customer customer : customers) { %>
    <tbody>
        
            <tr>
                <td><%= customer.getFirst_name() %></td>
                <td><%= customer.getLast_name() %></td>
                <td><%= customer.getAddress() %></td>
                <td><%= customer.getCity() %></td>
                <td><%= customer.getState() %></td>
                <td><%= customer.getEmail() %></td>
                <td><%= customer.getPhone() %></td>
                <% if ("ADMIN".equals(role)) { %>
                    <td>
                        <a href="edit-customer.jsp?id=<%= customer.getId() %>" class="btn btn-warning">Edit</a>
                        <form action="AdminServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= customer.getId() %>">
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</button>
                        </form>
                    </td>
                <% } %>
            </tr>
        <% 
                }
            } else { 
        %>
            <tr>
                <td colspan="<%= "ADMIN".equals(r) ? 8 : 7 %>" class="text-center">No customers found.</td>
            </tr>
        <% } %>
    </tbody>
</table>

<footer>
    &copy; 2024 Customer Management
</footer>
    
</body>
</html>

