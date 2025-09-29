package com.minegocio.MN_Data_Management.services;

import org.springframework.stereotype.Service;

import com.minegocio.MN_Data_Management.DTO.AddressDTO;
import com.minegocio.MN_Data_Management.domain.Address;
import com.minegocio.MN_Data_Management.repositories.AddressRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Mono<Address> saveAddressMatriz(Long companyId, String customerIdentification, AddressDTO a) {
        a.setIsHeadquarters(true);
        return addressRepository.saveByCompanyAndIdentification(companyId, customerIdentification, a);
    }

    public Mono<Address> save(Long companyId, String customerIdentification, AddressDTO a) {
        return addressRepository.saveByCompanyAndIdentification(companyId, customerIdentification, a);
    }

    public Flux<Address> findByCompanyAndIdentification(Long companyId, String customerIdetification) {
        return addressRepository.findByCompanyAndIdentification(companyId, customerIdetification);
    }

}
