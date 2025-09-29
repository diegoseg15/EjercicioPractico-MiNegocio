package com.minegocio.MN_Data_Management.DTO;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

@Table("customer_addresses")
public class AddressDTO {

    @NotBlank(message = "Alias is required")
    private String alias;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Province is required")
    private String province;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Zip is required")
    private String zip;

    @JsonProperty("isHeadquarters")
    private Boolean isHeadquarters;

    public AddressDTO() {
    }

    public AddressDTO( String alias, String street, String city, String province, String country,
            String zip, Boolean isHeadquarters) {
        this.alias = alias;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.isHeadquarters = isHeadquarters;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Boolean getIsHeadquarters() {
        return isHeadquarters;
    }

    public void setIsHeadquarters(Boolean isHeadquarters) {
        this.isHeadquarters = isHeadquarters;
    }
}
