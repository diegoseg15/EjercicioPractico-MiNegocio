package com.minegocio.MN_Data_Management.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import com.minegocio.MN_Data_Management.domain.Customer;

import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
  @Query("SELECT c.* FROM customers c")
  Flux<Customer> findAllProjected(); 

  @Query("SELECT c.* FROM customers c WHERE c.identification = :identification")
  Flux<Customer> findByIdentification(String identification);

  @Query("SELECT c.* FROM customers c WHERE c.company_id = :companyId")
  Flux<Customer> findByCompanyId(Long companyId);

  @Query("""
        SELECT c.* FROM customers c
        WHERE (LOWER(c.name) || ' ' || LOWER(c.lastname))
              LIKE LOWER(CONCAT('%', :q, '%'))
      """)
  Flux<Customer> searchByFullName(String q);

}
