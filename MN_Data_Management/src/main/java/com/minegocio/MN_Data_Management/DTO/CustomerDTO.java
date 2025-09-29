package com.minegocio.MN_Data_Management.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDTO {
    @NotBlank(message = "Identification is required")
    private String identification;

    @NotBlank(message = "IdentificationType is required")
    private String identificationType;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Lastname is required")
    private String lastname;

    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Phone is required")
    private String phone;

    @NotNull(message = "CompanyId is required")
    private Long companyId;



    public String getIdentification() {
        return identification;
    }

    public void setIdentificacion(String identification) {
        this.identification = identification;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificacionType(String identificationType) {
        this.identificationType = identificationType;
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
