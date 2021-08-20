package com.castoldi.custaddr.service;

import java.util.List;

import com.castoldi.custaddr.domain.Customer;

public interface CustomerService {

	List<Customer> findAll();

	Customer findById(Long id);

	Customer save(Customer customer);

	void deleteById(Long id);

	Customer findByZipCode(String zipCode);

}