package com.example.serving_web_content.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cryptos") // Имя таблицы в базе данных
public class CryptoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Генерация ID
    private Long id;

    @Column(nullable = false) // Колонка "title" не может быть null
    private String title;

    @Lob // Используется для хранения больших строк
    @Column(nullable = false, columnDefinition="TEXT") // Колонка "description" не может быть null
    private String description;


    @Column(nullable = false) // Колонка "description" не может быть null
    private String author;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
