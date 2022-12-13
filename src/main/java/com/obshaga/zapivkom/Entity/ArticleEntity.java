package com.obshaga.zapivkom.Entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Entity
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long Id;
    @Column(name = "anons")
    private String anons;
    @Column(name = "full_text", length = 8192)
    private String full_text;
    @Column(name = "title")
    private String title;
    @Column(name = "data")
    private String data;
    @Column(name = "filename")
    private String filename;

    @PrePersist
    private void init(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        data = LocalDateTime.now().format(formatter);
    }

    public ArticleEntity() {
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
