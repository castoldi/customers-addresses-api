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
import com.castoldi.custaddr.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final CustomerRepository customerRespository;

	public CustomerController(CustomerRepository customerRespository) {
		this.customerRespository = customerRespository;
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerRespository.findAll();
	}

	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable Long id) {
		return customerRespository.findById(id).orElseThrow(RuntimeException::new);
	}

	@PostMapping
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws URISyntaxException {
		Customer savedCustomer = customerRespository.save(customer);
		return ResponseEntity.created(new URI("/clients/" + savedCustomer.getId())).body(savedCustomer);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		logger.info("Updating customerId={}, customer={}", id, customer);
		Customer currentCustomer = customerRespository.findById(id).orElseThrow(RuntimeException::new);
		currentCustomer.setDocumentId(customer.getDocumentId());
		currentCustomer.setName(customer.getName());
		currentCustomer.setAge(customer.getAge());
		currentCustomer.setRegistrationDate(customer.getRegistrationDate());
		currentCustomer = customerRespository.save(currentCustomer);

		return ResponseEntity.ok(currentCustomer);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
		customerRespository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/zipCode/{zipCode}")
	public Customer getCustomerByZipCode(@PathVariable String zipCode) {
		return customerRespository.findByZipCode(zipCode).orElseThrow(RuntimeException::new);
	}
}