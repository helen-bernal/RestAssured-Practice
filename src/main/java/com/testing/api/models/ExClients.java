package com.testing.api.models;

public class ExClients {
    
    private String name;
    private String lastName;
    private String gender;
    private String country;
    private String city;
    private String id;
    
    public ExClients(String name, String lastName, String gender, String country, String city, String id) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
        this.city = city;
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
