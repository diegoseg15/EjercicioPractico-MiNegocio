package com.minegocio.MN_Data_Management.services;

import org.springframework.stereotype.Service;

import com.minegocio.MN_Data_Management.DTO.AddressDTO;
import com.minegocio.MN_Data_Management.DTO.CustomerAddressesDTO;
import com.minegocio.MN_Data_Management.DTO.CustomerDTO;
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

    public Mono<CustomerAddressesDTO> saveCustomerMatriz(CustomerAddressesDTO ca) {
        Customer c = ca.getCustomer();
        AddressDTO a = ca.getAddress();

        return customerRepository
                .findByCompanyAndIdentification(c.getCompanyId(), c.getIdentification())
                .switchIfEmpty(customerRepository.save(c))
                .flatMap(saved -> {
                    if (a == null)
                        return Mono.just(new CustomerAddressesDTO(saved, null));
                    a.setIsHeadquarters(true);
                    return addressService
                            .saveAddressMatriz(c.getCompanyId(), c.getIdentification(), a)
                            .thenReturn(new CustomerAddressesDTO(saved, a));
                });
    }

    public Mono<Customer> update(Long companyId, String identification, CustomerDTO c) {
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

    public Mono<Void> delete(Long companyId, String identification) {
        return customerRepository.deleteByIdentificationAndCompanyId(companyId, identification);
    }
}
