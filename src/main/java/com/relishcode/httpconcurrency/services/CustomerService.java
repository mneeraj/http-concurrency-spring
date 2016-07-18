package com.relishcode.httpconcurrency.services;

import java.util.List;

import com.relishcode.httpconcurrency.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer);
	public Customer findCustomer(long id);
	public Customer updateCustomer(Customer customer);
	public List<Customer> getAllCustomers();	
}
