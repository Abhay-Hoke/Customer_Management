package com.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.example.dao.CustomerDao;
import com.example.models.Customer;
import com.example.utils.JWTUtils;

/**
 * Servlet implementation class CustomerServlet
 */
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CustomerServlet() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
	        CustomerDao customerDao = new CustomerDao();
	        
	        HttpSession se = request.getSession(false);
	        String token = (String) se.getAttribute("jwtToken");
	        String role = JWTUtils.validateToken(token).get("role", String.class);
            
	        try {
	            List<Customer> customers;
	            customers = customerDao.getAllCustomers();
	            if ("sync".equals(action)) {
	                customers = customerDao.getAllCustomers();
	            } else {
	                String searchAttribute = request.getParameter("searchAttribute");
	                String searchValue = request.getParameter("searchValue");
	                if (searchAttribute != null && !searchAttribute.isEmpty() && searchValue != null && !searchValue.isEmpty()) {
	                    customers = customerDao.searchCustomers(searchAttribute, searchValue);
	                } else {
	                    customers = customerDao.getAllCustomers();
	                }
	            }

	            request.setAttribute("customers", customers);
	            if ("ADMIN".equals(role))
	            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
	            else if("USER".equals(role))
	            	request.getRequestDispatcher("customer-list.jsp").forward(request, response);
	            	
	        } catch (Exception e) {
	            e.printStackTrace();
	            if ("ADMIN".equals(role))
		            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);
		            else if("USER".equals(role))
		            	request.getRequestDispatcher("customer-list.jsp").forward(request, response);
	            //response.sendRedirect("customer-list.jsp?error=An error occurred");
	        }
	    
	}

	


}
