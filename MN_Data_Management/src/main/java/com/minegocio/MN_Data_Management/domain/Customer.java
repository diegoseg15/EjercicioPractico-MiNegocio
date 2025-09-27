package com.minegocio.MN_Data_Management.domain;

import org.springframework.data.annotation.Id;

public class Customer {
    
    @Id
    private String id;

    private String identification;
    private String identification_Type;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    public Customer(){}

    public Customer(String identification, String identification_Type, String name, String lastname, String email, String phone){
        this.identification = identification;
        this.identification_Type = identification_Type;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    //Getters y Setters

    public String getIdentification(){
        return identification;
    }

    public void setIdentificacion(String identification){
        this.identification = identification;
    }

    public String getIdentificationType(){
        return identification_Type;
    }

    public void setIdentificacionType(String identification_Type){
        this.identification_Type = identification_Type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLastname(){
        return lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
