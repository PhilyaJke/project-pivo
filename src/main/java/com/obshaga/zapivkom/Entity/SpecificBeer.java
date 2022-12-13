package com.obshaga.zapivkom.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SpecificBeer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="name_of_the_beer")
    private String nameOfThebeer;
    @Column(name="short_review")
    private String shortReview;
    @Column(name="filename")
    private String filename;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "beer_categories",
            joinColumns = @JoinColumn(name = "beer_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private List<CategoriesEntity> categoriesEntityList;

    public SpecificBeer(){

    }

    public void addCategoriToberr(CategoriesEntity categoriesEntity){
        if(categoriesEntityList==null){
            categoriesEntityList = new ArrayList();
        }
        categoriesEntityList.add(categoriesEntity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfThebeer() {
        return nameOfThebeer;
    }

    public void setNameOfThebeer(String nameOfThebeer) {
        this.nameOfThebeer = nameOfThebeer;
    }

    public String getShortReview() {
        return shortReview;
    }

    public void setShortReview(String shortReview) {
        this.shortReview = shortReview;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<CategoriesEntity> getCategoriesEntityList() {
        return categoriesEntityList;
    }

    public void setCategoriesEntityList(List<CategoriesEntity> categoriesEntityList) {
        this.categoriesEntityList = categoriesEntityList;
    }
}
