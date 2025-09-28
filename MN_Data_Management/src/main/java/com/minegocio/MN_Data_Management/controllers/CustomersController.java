package com.minegocio.MN_Data_Management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.MN_Data_Management.DTO.CustomerAddressesDTO;
import com.minegocio.MN_Data_Management.domain.Customer;
import com.minegocio.MN_Data_Management.services.CustomerService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/data")
@CrossOrigin
@RequiredArgsConstructor
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    // Funcionalidad para obtener un listado de clientes.
    @GetMapping("/clientes/lista")
    public Flux<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    // Funcionalidad para buscar clientes por Nombre o Apellido
    @GetMapping("/clientes/buscar-nombre/{name}")
    public Flux<Customer> getCustomerByFullName(@PathVariable String name) {
        return customerService.getCustomerByName(name);
    }

    // Funcionalidad para buscar clientes por Identificación
    @GetMapping("/clientes/identificacion/{identification}")
    public Mono<Customer> getCustomerByIdentification(@PathVariable String identification) {
        return customerService.getByIdentification(identification);
    }

    // Funcionalidad para crear un nuevo cliente con la dirección matriz
    @PostMapping("/clientes/agregar/matriz")
    public Mono<Customer> postSaveCustomerMatriz(@RequestBody CustomerAddressesDTO entity) {
        return customerService.saveCustomerMatriz(entity);
    }

    // Funcionalidad para editar los datos del cliente


    // Funcionalidad para eliminar un cliente

    // Funcionalidad para registrar una nueva dirección por cliente

    // Funcionalidad para listar las direcciones adicionales del cliente

}
