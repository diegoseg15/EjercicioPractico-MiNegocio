package com.minegocio.MN_Data_Management.DTO;

import com.minegocio.MN_Data_Management.domain.Customer;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CustomerAddressesDTO {
  @Valid
  @NotNull(message = "Customer cannot be null")
  private Customer customer;
  @Valid
  @NotNull(message = "Address cannot be null")
  private AddressDTO address;

  public CustomerAddressesDTO() {
  }

  public CustomerAddressesDTO(Customer customer, AddressDTO address) {
    this.customer = customer;
    this.address = address;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public AddressDTO getAddress() {
    return address;
  }

  public void setAddress(AddressDTO address) {
    this.address = address;
  }
}
