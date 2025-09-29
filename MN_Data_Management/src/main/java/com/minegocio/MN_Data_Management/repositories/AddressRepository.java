package com.minegocio.MN_Data_Management.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.minegocio.MN_Data_Management.domain.Address;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AddressRepository extends ReactiveCrudRepository<Address, Long> {
    @Query("SELECT a.* FROM customer_addresses a JOIN customers c ON c.id = a.customer_id WHERE c.company_id = :companyId AND c.identification = :identification")
    Flux<Address> findByCompanyAndIdentification(Long companyId, String identification);

    @Query("INSERT INTO customer_addresses (customer_id, alias, street, city, province, country, zip, is_headquarters, created_at, updated_at) SELECT c.id, :#{#a.alias}, :#{#a.street}, :#{#a.city}, :#{#a.province}, :#{#a.country}, :#{#a.zip}, COALESCE(:#{#a.isHeadquarters}, false), now(), now() FROM customers c WHERE c.company_id = :companyId AND c.identification = :customerIdentification RETURNING *;")
    Mono<Address> saveByCompanyAndIdentification(Long companyId, String customerIdentification, Address a);
}
