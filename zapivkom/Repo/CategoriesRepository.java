package com.obshaga.zapivkom.Repo;

import com.obshaga.zapivkom.Entity.CategoriesEntity;
import com.obshaga.zapivkom.Entity.SpecificBeer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CategoriesRepository extends CrudRepository<CategoriesEntity, Long> {

//    @Query("SELECT ce.Id FROM CategoriesEntity ce WHERE ce.title = :title")
//    Long findByTitle(String title);

    @Query("SELECT ce FROM CategoriesEntity ce WHERE ce.title = :title")
    CategoriesEntity findByTitle(String title);

}
