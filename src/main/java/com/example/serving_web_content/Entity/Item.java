package com.example.serving_web_content.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "items") // Имя таблицы в базе данных
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация ID
    private Long id;

    @Column(nullable = false) // Колонка "name" не может быть null
    private String name;

    @Column(nullable = false) // Колонка "amount" не может быть null
    private int amount;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
