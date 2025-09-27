package com.minegocio.MN_Data_Management.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.minegocio.MN_Data_Management.domain.Address;

public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
    
}
