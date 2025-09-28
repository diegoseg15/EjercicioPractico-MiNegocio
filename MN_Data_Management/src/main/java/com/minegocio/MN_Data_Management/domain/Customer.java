package com.minegocio.MN_Data_Management.domain;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table("customers")
public class Customer {

    @Id
    private Long id;

    @Column("company_id")
    @JsonProperty("companyId")
    private Long companyId;

    private String identification;

    @Column("identification_type")
    @JsonProperty("identificationType")
    private String identification_Type;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    @Column("created_at")
    private Instant createdAt;

    @Column("updated_at")
    private Instant updatedAt;

    public Customer() {
    }

    public Customer(String identification, String identification_Type, String name, String lastname, String email,
            String phone, Long companyId) {
        this.identification = identification;
        this.identification_Type = identification_Type;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.companyId = companyId;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentificacion(String identification) {
        this.identification = identification;
    }

    public String getIdentificationType() {
        return identification_Type;
    }

    public void setIdentificacionType(String identification_Type) {
        this.identification_Type = identification_Type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
