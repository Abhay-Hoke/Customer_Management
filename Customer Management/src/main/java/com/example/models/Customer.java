package com.example.models;

public class Customer {

	private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
    
    
    
    public Customer() {
    	
    }



	public Customer(int id, String first_Name, String last_Name, String address, String city, String state,
			String email, String phone) {
		super();
		this.id = id;
		this.first_name = first_Name;
		this.last_name = last_Name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_Name) {
		this.first_name = first_Name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_Name) {
		this.last_name = last_Name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	
	
    
    
}
