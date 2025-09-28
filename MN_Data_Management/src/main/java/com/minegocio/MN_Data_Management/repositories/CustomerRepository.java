package com.minegocio.MN_Data_Management.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.minegocio.MN_Data_Management.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

  @Query("SELECT c.* FROM customers c WHERE c.company_id = :companyId AND identification = :identification")
  Mono<Customer> findByCompanyAndIdentification(Long companyId, String identification);

  @Query("SELECT c.* FROM customers c WHERE c.identification = :identification")
  Flux<Customer> findByIdentification(String identification);

  @Query("SELECT c.* FROM customers c WHERE c.company_id = :companyId")
  Flux<Customer> findByCompanyId(Long companyId);

  @Query("SELECT c.* FROM customers c WHERE (LOWER(c.name  || ' ' || c.lastname)) LIKE LOWER(CONCAT('%', :q, '%'))")
  Flux<Customer> searchByFullName(String q);

  @Query("DELETE FROM customers WHERE company_id = :companyId AND identification = :identification;")
  Mono<Customer> deleteByIdentificationAndCompanyId(Long companyId, String identification);

}
