package com.minegocio.MN_Data_Management.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.minegocio.MN_Data_Management.domain.Address;


@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {

}
