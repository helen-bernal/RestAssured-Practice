package com.testing.api.models;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    private String name;
    private String lastName;
    private String gender;
    private String country;
    private String city;
    private String id;
    private String email;
    private String phone;

    // Getters y Setters para todos los campos

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
