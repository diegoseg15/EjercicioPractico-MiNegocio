package com.minegocio.MN_Data_Management.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.minegocio.MN_Data_Management.domain.Customer;


@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
  

}
