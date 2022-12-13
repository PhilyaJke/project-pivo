package com.obshaga.zapivkom.Repo;

import com.obshaga.zapivkom.Entity.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
    @Query("SELECT m.usersEntity.id FROM Meeting m")
    List<Long> findAllUserId();

    @Query("SELECT m FROM Meeting m")
    List<Meeting> findAll();
}
