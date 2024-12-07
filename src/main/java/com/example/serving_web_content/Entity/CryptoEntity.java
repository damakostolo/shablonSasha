package com.example.serving_web_content.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cryptos") // Имя таблицы в базе данных
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация ID
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;


    @Column(nullable = false)
    private String mark;

    @Lob // Используется для хранения больших строк
    @Column(nullable = false, columnDefinition="TEXT") // Колонка "description" не может быть null
    private String description;

    @Lob // Используется для хранения больших строк
    @Column(nullable = false , columnDefinition="TEXT") // Колонка "src" не может быть null
    private String img;


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

    public void setName(String title) {
        this.name = title;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
