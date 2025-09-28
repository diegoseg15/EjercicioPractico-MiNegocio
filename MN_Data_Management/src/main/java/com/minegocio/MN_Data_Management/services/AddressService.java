package com.minegocio.MN_Data_Management.services;

import org.springframework.stereotype.Service;

import com.minegocio.MN_Data_Management.domain.Address;
import com.minegocio.MN_Data_Management.repositories.AddressRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Mono<Address> saveAddressMatriz(Long clientId, Address c) {

        return addressRepository.save(c)
                .flatMap(add -> {
                    add.setCustomerId(clientId);
                    add.setIsHeadquarters(true);
                    return addressRepository.save(add);
                });
    }

    public Mono<Address> saveAddress(Long clientId, Address c) {
        return addressRepository.save(c)
                .flatMap(address -> {
                    address.setCustomerId(clientId);
                    return addressRepository.save(address);
                });
    }

    
}
