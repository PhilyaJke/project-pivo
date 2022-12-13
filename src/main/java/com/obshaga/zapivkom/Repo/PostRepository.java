package com.obshaga.zapivkom.Repo;

import com.obshaga.zapivkom.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<ArticleEntity, Long> {
    @Query("SELECT c FROM ArticleEntity c ORDER BY c.Id DESC")
    Iterable<ArticleEntity> getAll();

    @Query("Select c.Id FROM ArticleEntity c WHERE c.title = ?1")
    Long findIdByTitle(String title);
}
