package com.obshaga.zapivkom.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private String date;
    @Column(name = "place")
    private String place;
    @Column(name = "message")
    private String message;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UsersEntity usersEntity;

}
