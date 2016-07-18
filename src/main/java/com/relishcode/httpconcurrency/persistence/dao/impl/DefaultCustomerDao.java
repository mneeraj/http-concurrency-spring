package com.relishcode.httpconcurrency.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.relishcode.httpconcurrency.model.Customer;
import com.relishcode.httpconcurrency.persistence.dao.CustomerDao;

@Repository("customerDao")
public class DefaultCustomerDao extends DefaultGenericDao<Customer, Long> implements CustomerDao {

	public DefaultCustomerDao() {
		super(Customer.class);
	}

}
