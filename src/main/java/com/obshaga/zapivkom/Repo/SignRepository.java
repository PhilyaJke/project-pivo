package com.obshaga.zapivkom.Repo;

import com.obshaga.zapivkom.Entity.UsersEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SignRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByUsernameAndPassword(String username, String password);
    Optional<UsersEntity> findByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UsersEntity ue SET ue.password = ?1 WHERE ue.id = ?2", nativeQuery = true)
    UsersEntity UpdatePassword(@Param("password")String password, @Param("id")Long id);
}
