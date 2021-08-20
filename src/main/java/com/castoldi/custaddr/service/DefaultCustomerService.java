package com.castoldi.custaddr.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.castoldi.custaddr.domain.Customer;
import com.castoldi.custaddr.repository.CustomerRepository;

@Service
public class DefaultCustomerService implements CustomerService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final CustomerRepository customerRespository;

	public DefaultCustomerService(CustomerRepository customerRespository) {
		this.customerRespository = customerRespository;
	}

	@Override
	public List<Customer> findAll() {
		logger.info("Finding all customers.");
		return customerRespository.findAll();
	}

	@Override
	public Customer findById(Long id) {
		logger.info("Finding customer by id={}", id);
		return customerRespository.findById(id).orElseThrow(RuntimeException::new);
	}

	@Override
	public Customer save(Customer customer) {
		logger.info("Saving customer={}", customer);
		return customerRespository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		logger.info("Deleting customer by id={}", id);
		customerRespository.deleteById(id);
	}

	@Override
	public List<Customer> findByZipCode(String zipCode) {
		logger.info("Finding customer by zipCode={}", zipCode);
		return customerRespository.findByZipCode(zipCode).orElseThrow(RuntimeException::new);
	}

}
