package com.minegocio.MN_Data_Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.MN_Data_Management.domain.Customer;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/data-client")
@CrossOrigin
@RequiredArgsConstructor
public class CustomersController {

    @Autowired
    private CustomersController customersController;

    // @GetMapping("/lista-clientes")
    // public Mono<ResponseEntity<Customer>> getCustomers(@PathVariable String identification) {
        
    // }
}
