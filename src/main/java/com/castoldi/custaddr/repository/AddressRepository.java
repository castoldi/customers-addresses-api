package com.castoldi.custaddr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castoldi.custaddr.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}