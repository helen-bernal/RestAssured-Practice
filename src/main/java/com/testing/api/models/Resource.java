package com.testing.api.models;

import com.github.javafaker.Bool;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Resource {
    private String name;
    private String trademark;
    private Integer stock;
    private Double price;
    private String description;
    private String tags;
    private Boolean active;
    public String id;

    public Resource(String name, String trademark, Integer stock, Double price, String description, Boolean active) {
        this.name = name;
        this.trademark = trademark;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.active = active;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrademark() {
        return trademark;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public String getTags() {
        return tags;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return this.active;
    }
}
