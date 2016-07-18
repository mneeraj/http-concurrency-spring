package com.relishcode.httpconcurrency.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.relishcode.httpconcurrency.model.Customer;
import com.relishcode.httpconcurrency.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

	@Autowired
	CustomerService customerService;
	
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createCustomer(@RequestBody Customer customer, HttpServletRequest request) {
		Customer createdCustomer = customerService.createCustomer(customer);
		return ResponseEntity.created(URI.create(request.getRequestURL() + "/" + createdCustomer.getId())).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomer(@PathVariable("id") long id) {
		Customer customer = customerService.findCustomer(id);
		if (customer != null) {
			return ResponseEntity.ok().
					lastModified(customer.getUpdateTime().getTime()).
					body(customer);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCustomers() {
		List<Customer> customerList = customerService.getAllCustomers();
		if (customerList != null) {
			return ResponseEntity.ok(customerList);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer, WebRequest request) {
		Customer existingCustomer = customerService.findCustomer(id);
		if (existingCustomer != null) {
			if (request.checkNotModified(existingCustomer.getUpdateTime().getTime())) {
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
			}
			else {
				customer.setId(id);
				Customer updatedCustomer = customerService.updateCustomer(customer);
				return ResponseEntity.ok().lastModified(updatedCustomer.getUpdateTime().getTime()).body(updatedCustomer);				
			}
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
