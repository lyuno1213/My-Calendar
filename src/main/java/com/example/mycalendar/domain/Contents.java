package com.example.mycalendar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contents {
     @Id
    @Column(nullable = false, updatable = false, length = 12)
    private String id;

    @Column(columnDefinition = "text")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id_id")
    private Users usersId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(final String contents) {
        this.contents = contents;
    }

    public Users getUsersId() {
        return usersId;
    }

    public void setUsersId(final Users usersId) {
        this.usersId = usersId;
    }
}
