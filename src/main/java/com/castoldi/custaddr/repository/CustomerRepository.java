package com.castoldi.custaddr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castoldi.custaddr.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	//@Query("SELECT c from Customer c where c.addresses.zipCode=:zipCode")
	@Query("SELECT c FROM Customer c left join c.addresses a WHERE a.zipCode = :zipCode")
	Optional<Customer> findByZipCode(@Param("zipCode") String zipCode);
	


}