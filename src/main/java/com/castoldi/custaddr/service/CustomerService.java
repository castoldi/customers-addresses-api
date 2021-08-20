package com.castoldi.custaddr.service;

import java.util.List;

import com.castoldi.custaddr.domain.Customer;

public interface CustomerService {

	/**
	 * Finds all customer in the database. 
	 */
	List<Customer> findAll();

	/**
	 * Find customer by the system generated id.
	 */
	Customer findById(Long id);

	/**
	 * Save new or update a customer. 
	 */
	Customer save(Customer customer);

	/**
	 * Delete a customer by the auto generated system id.
	 */
	void deleteById(Long id);

	/**
	 * Find all the customers by a zipCode.
	 */
	List<Customer> findByZipCode(String zipCode);

}