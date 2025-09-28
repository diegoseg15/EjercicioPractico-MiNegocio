package com.minegocio.MN_Data_Management.services;

import org.springframework.stereotype.Service;

import com.minegocio.MN_Data_Management.domain.Customer;
import com.minegocio.MN_Data_Management.repositories.AddressRepository;
import com.minegocio.MN_Data_Management.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Flux<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Mono<Customer> getByIdentification(String identification) {
        return customerRepository.findByIdentification(identification).next();
    }

    public Flux<Customer> getCustomerByName(String name) {
        return customerRepository.searchByFullName(name);
    }

    public Mono<Customer> saveCustomer(Customer c) {
        return customerRepository.save(c)
                .flatMap(customer -> {
                    return customerRepository.save(customer);
                });
    }

    // public Mono<Address> update(String identification, Customer c){
    //     return customerRepository.findById(id)
            
    // }
}
