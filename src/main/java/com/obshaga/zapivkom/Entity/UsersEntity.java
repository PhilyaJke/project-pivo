package com.obshaga.zapivkom.Entity;


import lombok.Data;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UsersEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @NotNull(message = "Поле имени пустое")
    @Size(message = "Имя должно быть от 3 до 10 символов")
    @Column(name="username")
    private String username;
    @NotNull(message = "Поле пароля пустое")
    @Size(message = "Пароль должен быть от 4 до 8 символов")
    @Column(name="password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Roles roles;
    @Enumerated(value = EnumType.STRING)
    @Column(name="status")
    private Status status;
    @Column(name="data")
    private String data;
    @Enumerated(value = EnumType.STRING)
    @Column(name="rank")
    private Rank rank;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersEntity")
    private List<CommentEntity> commentEntityList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(name = "user")
    private List<UsersEntity> usersEntityList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "friends")
    private UsersEntity user;

    @Column(name = "filename")
    private String filename;

    @PrePersist
    private void init(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        data = LocalDateTime.now().format(formatter);
    }

    public void addUserToFriend(UsersEntity usersEntity){
        if(usersEntityList==null){
            usersEntityList = new ArrayList<>();
        }
        usersEntityList.add(usersEntity);
        user.setUser(this);
    }
    public void addUserToComment(CommentEntity commentEntity){
        if(commentEntityList==null){
            commentEntityList = new ArrayList<>();
        }
        commentEntityList.add(commentEntity);
        commentEntity.setUsersEntity(this);
    }

}
