package com.example.serving_web_content.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wallpapers") // Имя таблицы в базе данных
public class Wallpaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация ID
    private Long id;

    @Column(nullable = false) // Колонка "name" не может быть null
    private String name;

    @Column(nullable = false) // Колонка "amount" не может быть null
    private Long amount;

    @Lob // Используется для хранения больших строк
    @Column(nullable = false, columnDefinition="TEXT") // Колонка "description" не может быть null
    private String description;

    @Lob // Используется для хранения больших строк
    @Column(nullable = false , columnDefinition="TEXT") // Колонка "src" не может быть null
    private String src;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
