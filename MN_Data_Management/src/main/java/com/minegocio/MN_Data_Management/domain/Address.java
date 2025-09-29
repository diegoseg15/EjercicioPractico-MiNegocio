package com.minegocio.MN_Data_Management.domain;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer_addresses")
public class Address {

    @Id
    private Long id;

    @Column("customer_id")
    private Long customerId;

    private String alias;
    private String street;
    private String city;
    private String province;
    private String country;
    private String zip;

    @Column("is_headquarters")
    private Boolean isHeadquarters;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    public Address() {
    }

    public Address(Long customerId, String alias, String street, String city, String province, String country,
            String zip, Boolean isHeadquarters) {
        this.customerId = customerId;
        this.alias = alias;
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.isHeadquarters = isHeadquarters;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
