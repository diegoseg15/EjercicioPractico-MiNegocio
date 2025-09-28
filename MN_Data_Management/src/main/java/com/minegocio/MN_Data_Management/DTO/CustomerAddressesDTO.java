package com.minegocio.MN_Data_Management.DTO;

import com.minegocio.MN_Data_Management.domain.Address;
import com.minegocio.MN_Data_Management.domain.Customer;

public class CustomerAddressesDTO {
  private Customer customer;
  private Address address;

  public CustomerAddressesDTO() {
  }

  public CustomerAddressesDTO(Customer customer, Address address) {
    this.customer = customer;
    this.address = address;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
