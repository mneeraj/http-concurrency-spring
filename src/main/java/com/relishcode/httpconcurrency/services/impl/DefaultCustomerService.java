package com.relishcode.httpconcurrency.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relishcode.httpconcurrency.model.Customer;
import com.relishcode.httpconcurrency.persistence.dao.CustomerDao;
import com.relishcode.httpconcurrency.services.CustomerService;

@Service ("customerService")
@Transactional
public class DefaultCustomerService implements CustomerService {

	@Autowired
	private CustomerDao customerDao;	
	
	@Override
	public Customer findCustomer(long id) {
		return customerDao.read(id);
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		return customerDao.create(customer);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();	
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerDao.update(customer);
	}
}
