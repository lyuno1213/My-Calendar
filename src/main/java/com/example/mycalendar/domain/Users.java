package com.example.mycalendar.domain;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Users {
    @Id
    @Column(nullable = false, updatable = false, length = 12)
    private String id;

    @Column(length = 12)
    private String passwd;

    @Column(length = 5)
    private String name;

    @OneToMany(mappedBy = "usersId")
    private Set<Contents> usersId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(final String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Contents> getUsersId() {
        return usersId;
    }

    public void setUsersId(final Set<Contents> usersId) {
        this.usersId = usersId;
    }
}
