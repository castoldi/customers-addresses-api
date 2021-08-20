package com.castoldi.custaddr.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castoldi.custaddr.domain.Customer;
import com.castoldi.custaddr.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerService.findById(id);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		Customer savedCustomer = customerService.save(customer);
		return ResponseEntity.created(new URI("/clients/" + savedCustomer.getId())).body(savedCustomer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		logger.info("Updating customerId={}, customer={}", id, customer);
		Customer currentCustomer = customerService.findById(id);
		currentCustomer.setDocumentId(customer.getDocumentId());
		currentCustomer.setName(customer.getName());
		currentCustomer.setAge(customer.getAge());
		currentCustomer.setRegistrationDate(customer.getRegistrationDate());
		currentCustomer = customerService.save(currentCustomer);

		return ResponseEntity.ok(currentCustomer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
		customerService.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/zipCode/{zipCode}")
	public Customer getCustomerByZipCode(@PathVariable String zipCode) {
		return customerService.findByZipCode(zipCode);
	}
}