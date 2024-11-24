package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.example.dao.CustomerDao;
import com.example.models.Customer;
import com.example.utils.JWTUtils;




public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminServlet() {
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String role = (String) request.getAttribute("role");
		
		System.out.println(role);
		
		HttpSession session = request.getSession(false);
		if (session == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired. Please log in again.");
            return;
        }
		 String token = (String) session.getAttribute("jwtToken");
	        if (token == null) {
	            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized access");
	            return;
	        }
	
        try {
            
        	
        	
        	//String role = JWTUtils.validateToken(token).get("role", String.class);
            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
                return;
            }

            String action = request.getParameter("action");
            CustomerDao customerdaO = new CustomerDao();

            if ("add".equals(action)) {
                Customer customer = new Customer();
                customer.setFirst_name(request.getParameter("first_name"));
                customer.setLast_name(request.getParameter("last_name"));
                customer.setAddress(request.getParameter("address"));
                customer.setCity(request.getParameter("city"));
                customer.setState(request.getParameter("state"));
                customer.setEmail(request.getParameter("email"));
                customer.setPhone(request.getParameter("phone"));
                
                customerdaO.addCustomer(customer);
                response.sendRedirect("admin-dashboard.jsp?success=Customer added");
            
            }else if ("update".equals(action)) {
               
            	
                int id = Integer.parseInt(request.getParameter("id"));
                String first_name = request.getParameter("first_name");
                String last_name = request.getParameter("last_name");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");

                Customer customer = new Customer();
                customer.setId(id);
                customer.setFirst_name(first_name);
                customer.setLast_name(last_name);
                customer.setAddress(address);
                customer.setCity(city);
                customer.setState(state);
                customer.setEmail(email);
                customer.setPhone(phone);

                if (customerdaO.updateCustomer(customer)) {
                    response.sendRedirect("admin-dashboard.jsp?success=Customer updated");
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=Failed to update customer");
                }
            } else if ("delete".equals(action)) {


                int id = Integer.parseInt(request.getParameter("id"));

                if (customerdaO.deleteCustomer(id)) {
                    response.sendRedirect("admin-dashboard.jsp?success=Customer deleted");
                } else {
                    response.sendRedirect("admin-dashboard.jsp?error=Failed to delete customer");
                }


            }
            } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin-dashboard.jsp?error=An error occurred");
        }
	}

}
