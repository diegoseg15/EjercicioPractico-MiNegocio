package com.minegocio.MN_Data_Management.controllers;

import com.minegocio.MN_Data_Management.DTO.AddressDTO;
import com.minegocio.MN_Data_Management.DTO.CustomerAddressesDTO;
import com.minegocio.MN_Data_Management.DTO.CustomerDTO;
import com.minegocio.MN_Data_Management.domain.Address;
import com.minegocio.MN_Data_Management.domain.Customer;
import com.minegocio.MN_Data_Management.services.AddressService;
import com.minegocio.MN_Data_Management.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;

@WebFluxTest(controllers = CustomersController.class)
class CustomersControllerTest {

        @Autowired
        private WebTestClient webTestClient;

        @MockBean
        private AddressService addressService;

        @MockBean
        private CustomerService customerService;


        // datos de ejemplo

        private Customer sampleCustomer() {
                Customer c = new Customer();
                c.setIdentification("1755555555");
                c.setIdentificacionType("CEDULA");
                c.setCompanyId(2L);
                c.setName("Mario");
                c.setLastname("Bros");
                c.setEmail("mario.bros@gmail.com");
                c.setPhone("0987654321");
                return c;
        }

        private Address sampleAddress() {
                Address a = new Address();
                a.setCustomerId(1L);
                a.setAlias("Matriz");
                a.setStreet("Av. Siempre Viva 123");
                a.setCity("Ambato");
                a.setProvince("Tungurahua");
                a.setCountry("Ecuador");
                a.setZip("180101");
                a.setIsHeadquarters(true);
                return a;
        }

        private AddressDTO sampleAddressDTO() {
                AddressDTO a = new AddressDTO();
                a.setAlias("Matriz");
                a.setStreet("Av. Siempre Viva 123");
                a.setCity("Ambato");
                a.setProvince("Tungurahua");
                a.setCountry("Ecuador");
                a.setZip("180101");
                a.setIsHeadquarters(true);
                return a;
        }


        // Tests de endpoints Clientes

        @Test
        void testGetAllCustomers() {
                Customer c1 = sampleCustomer();
                Customer c2 = sampleCustomer();
                c2.setId(2L);
                c2.setIdentification("1799999999");

                BDDMockito.given(customerService.getAll())
                                .willReturn(Flux.just(c1, c2));

                webTestClient.get()
                                .uri("/api/v1/data/clientes/lista")
                                .exchange()
                                .expectStatus().isOk()
                                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                                .expectBody()
                                .jsonPath("$[0].identification").isEqualTo("1755555555")
                                .jsonPath("$[1].id").isEqualTo(2)
                                .jsonPath("$[1].identification").isEqualTo("1799999999");
        }


        @Test
        void testGetCustomerByFullName() {
                Customer c = sampleCustomer();

                BDDMockito.given(customerService.getCustomerByName("Mario"))
                                .willReturn(Flux.just(c));

                webTestClient.get()
                                .uri("/api/v1/data/clientes/buscar-nombre/{name}", "Mario")
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$[0].name").isEqualTo("Mario")
                                .jsonPath("$[0].lastname").isEqualTo("Bros");
        }


        @Test
        void testGetCustomerByIdentification() {
                Customer c = sampleCustomer();

                BDDMockito.given(customerService.getByIdentification("1755555555"))
                                .willReturn(Mono.just(c));

                webTestClient.get()
                                .uri("/api/v1/data/clientes/identificacion/{ident}", "1755555555")
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$.identification").isEqualTo("1755555555");
        }


        @Test
        void testPostSaveCustomerMatriz() {
                Customer customer = sampleCustomer();
                AddressDTO addressDTO = sampleAddressDTO();
                CustomerAddressesDTO payload = new CustomerAddressesDTO(customer, addressDTO);

                CustomerAddressesDTO returned = new CustomerAddressesDTO(customer, addressDTO);

                BDDMockito.given(customerService.saveCustomerMatriz(any(CustomerAddressesDTO.class)))
                                .willReturn(Mono.just(returned));

                webTestClient.post()
                                .uri("/api/v1/data/clientes/agregar/matriz")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(payload)
                                .exchange()
                                .expectStatus().is2xxSuccessful()
                                .expectBody()
                                .jsonPath("$.customer.identification").isEqualTo("1755555555")
                                .jsonPath("$.address.alias").isEqualTo("Matriz")
                                .jsonPath("$.address.isHeadquarters").isEqualTo(true);

                BDDMockito.then(customerService)
                                .should()
                                .saveCustomerMatriz(any(CustomerAddressesDTO.class));
        }

        @Test
        void testUpdateCustomer() {
                Customer updated = sampleCustomer();
                updated.setName("Mario Andrés");

                BDDMockito.given(customerService.update(eq(2L), eq("1755555555"), any(CustomerDTO.class)))
                                .willReturn(Mono.just(updated));

                webTestClient.patch()
                                .uri("/api/v1/data/clientes/editar/{companyId}/{ident}", 2L, "1755555555")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(sampleCustomer())
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$.name").isEqualTo("Mario Andrés");
        }

        @Test
        void testDeleteCustomer_noContent() {
                BDDMockito.given(customerService.delete(2L, "1755555555"))
                                .willReturn(Mono.empty());

                webTestClient.delete()
                                .uri("/api/v1/data/clientes/eliminar/{companyId}/{ident}", 2L, "1755555555")
                                .exchange()
                                .expectStatus().isNoContent();

                BDDMockito.then(customerService)
                                .should()
                                .delete(2L, "1755555555");
        }

        // Tests de endpoints Direcciones

        @Test
        void testAddAddress() {
                AddressDTO dto = sampleAddressDTO();
                Address saved = sampleAddress();

                BDDMockito.given(addressService.save(eq(2L), eq("1755555555"), any(AddressDTO.class)))
                                .willReturn(Mono.just(saved));

                webTestClient.post()
                                .uri("/api/v1/data/direcciones/agregar/{companyId}/{customerId}", 2L, "1755555555")
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(dto)
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$.alias").isEqualTo("Matriz");
        }

        @Test
        void testListAddresses() {
                Address a1 = sampleAddress();
                Address a2 = sampleAddress();
                a2.setAlias("Sucursal");

                BDDMockito.given(addressService.findByCompanyAndIdentification(10L,
                                "1755555555"))
                                .willReturn(Flux.just(a1, a2));

                webTestClient.get()
                                .uri("/api/v1/data/direcciones/identificacion/{companyId}/{ident}", 10L,
                                                "1755555555")
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody()
                                .jsonPath("$[0].alias").isEqualTo("Matriz")
                                .jsonPath("$[1].alias").isEqualTo("Sucursal");
        }

}
