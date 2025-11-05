package com.example.serving_web_content.dto;

import java.math.BigDecimal;

public class RegistrationForm {

    private String fullName;
    private String email;
    private BigDecimal price;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
