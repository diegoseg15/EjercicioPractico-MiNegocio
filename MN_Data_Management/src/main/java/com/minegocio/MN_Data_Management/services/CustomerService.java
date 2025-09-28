package com.minegocio.MN_Data_Management.services;

import org.springframework.stereotype.Service;

import com.minegocio.MN_Data_Management.DTO.CustomerAddressesDTO;
import com.minegocio.MN_Data_Management.domain.Address;
import com.minegocio.MN_Data_Management.domain.Customer;
import com.minegocio.MN_Data_Management.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AddressService addressService;

    private final CustomerRepository customerRepository;

    public Flux<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Mono<Customer> getByIdentification(String identification) {
        return customerRepository.findByIdentification(identification).next();
    }

    public Mono<Customer> getByCompanyandIdentification(Long companyId, String identification) {
        return customerRepository.findByCompanyAndIdentification(companyId, identification);
    }

    public Flux<Customer> getCustomerByName(String name) {
        return customerRepository.searchByFullName(name);
    }

    public Mono<Customer> saveCustomerMatriz(CustomerAddressesDTO ca) {
        return customerRepository.save(ca.getCustomer())
                .flatMap(saved -> {
                    Address addr = ca.getAddress();
                    if (addr == null) {
                        return Mono.just(saved);
                    }
                    addr.setCustomerId(saved.getId());
                    return addressService.saveAddressMatriz(addr.getCustomerId(), addr)
                            .thenReturn(saved);
                });
    }

    public Mono<Customer> update(Long companyId, String identification, Customer c) {
        return customerRepository.findByCompanyAndIdentification(companyId, identification)
                .flatMap(existcustomer -> {
                    existcustomer.setIdentificacion(c.getIdentification());
                    existcustomer.setIdentificacionType(c.getIdentificationType());
                    existcustomer.setName(c.getName());
                    existcustomer.setLastname(c.getLastname());
                    existcustomer.setCompanyId(c.getCompanyId());
                    existcustomer.setEmail(c.getEmail());
                    existcustomer.setPhone(c.getPhone());
                    return customerRepository.save(existcustomer);
                });
    }

    public Mono<Customer> delete(Long companyId, String identification) {
        return customerRepository.deleteByIdentificationAndCompanyId(companyId, identification);
    }
}
