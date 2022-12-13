package com.obshaga.zapivkom.Repo;

import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Entity.UsersEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query("SELECT sb FROM CommentEntity sb INNER JOIN SpecificBeer ce ON sb.specificBeer.id = ce.id WHERE ce.id = :id")
    List<CommentEntity> findByBeer_id(Long id);

    @Query("SELECT sb FROM CommentEntity sb INNER JOIN UsersEntity ue ON sb.usersEntity.id = ue.id WHERE ue.id = :id")
    List<CommentEntity> findByUsername(Long id);
}
