package com.obshaga.zapivkom.Entity;

import javax.persistence.*;
import java.util.*;

//СВЯЗЬ С ОБЗОРАМИ МЭНИ ТУ МЭНИ
@Entity
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long Id;
    @Column(name="title")
    private String title;
    @Column(name="text", length = 8192)
    private String text;
    @Column(name="filename")
    private String filename;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "beer_categories",
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "beer_id")
    )
    private List<SpecificBeer> specificBeerList;


    public CategoriesEntity(){

    }

    public void addBeerToCategori(SpecificBeer specificBeer){
        if(specificBeerList==null){
            specificBeerList = new ArrayList<>();
        }
        specificBeerList.add(specificBeer);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<SpecificBeer> getSpecificBeerList() {
        return specificBeerList;
    }

    public void setSpecificBeerList(List<SpecificBeer> specificBeerList) {
        this.specificBeerList = specificBeerList;
    }
}
