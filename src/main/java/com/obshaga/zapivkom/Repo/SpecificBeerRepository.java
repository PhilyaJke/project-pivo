package com.obshaga.zapivkom.Repo;


import com.obshaga.zapivkom.Entity.SpecificBeer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SpecificBeerRepository extends CrudRepository<SpecificBeer,Long> {

    @Query("SELECT se FROM SpecificBeer se")
    List findAll();

    SpecificBeer findByNameOfThebeer(String nameofTheBeer);




}
