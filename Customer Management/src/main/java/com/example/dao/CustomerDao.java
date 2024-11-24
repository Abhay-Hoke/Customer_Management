package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.models.Customer;
import com.example.utils.DbUtils;



public class CustomerDao {
	
	public List<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM customers order by id desc";
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DbUtils.connectDB();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
        }
        return customers;
    }

    public boolean addCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO customers (first_name,last_name,address,city,state, email, phone) VALUES (?, ?, ?, ?,?,?,?)";
        try (Connection con = DbUtils.connectDB();
                PreparedStatement ps = con.prepareStatement(query)) {
        	ps.setString(1, customer.getFirst_name());
        	ps.setString(2, customer.getLast_name());
        	ps.setString(3, customer.getAddress());
        	ps.setString(4, customer.getCity());
        	ps.setString(5, customer.getState());
        	ps.setString(6, customer.getEmail());
        	ps.setString(7, customer.getPhone());
            return ps.executeUpdate() > 0;
        }
    }
    
    public List<Customer> searchCustomers(String attribute, String value) throws ClassNotFoundException, SQLException{
    	
    	String query = "SELECT * FROM customers WHERE " + attribute + " LIKE ?";
    	try (Connection con = DbUtils.connectDB();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
            	Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customers.add(customer);
            }
            return customers;
        }
    	
    }
    
    
    
    public boolean deleteCustomer(int id) throws ClassNotFoundException, SQLException {
        String query = "DELETE FROM customers WHERE id = ?";
        try(Connection con = DbUtils.connectDB();
                PreparedStatement ps = con.prepareStatement(query)){
        	ps.setInt(1,id);
        	return ps.executeUpdate()>0;
        	
        }
    	
    }
    
    public  boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
    	 String query = "UPDATE customers SET first_name = ?,last_name=?,address=?,city=?,state=?, email = ?, phone = ? WHERE id = ?";
    	    try (Connection conn = DbUtils.connectDB();
    	         PreparedStatement ps = conn.prepareStatement(query)) {

    	    	ps.setString(1, customer.getFirst_name());
            	ps.setString(2, customer.getLast_name());
            	ps.setString(3, customer.getAddress());
            	ps.setString(4, customer.getCity());
            	ps.setString(5, customer.getState());
            	ps.setString(6, customer.getEmail());
            	ps.setString(7, customer.getPhone());
            	ps.setInt(8, customer.getId());
                return ps.executeUpdate() > 0;
    	    }
    	
    }
    
    public Customer getCustomerById(int id) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = DbUtils.connectDB();
   	         PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                return customer;
            }
        }
        return null;
    }


}


