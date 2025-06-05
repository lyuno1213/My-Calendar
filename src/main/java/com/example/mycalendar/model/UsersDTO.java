package com.example.mycalendar.model;

import jakarta.validation.constraints.Size;


public class UsersDTO {

    @Size(max = 12)
    @UsersIdValid
    private String id;

    @Size(max = 12)
    private String passwd;

    @Size(max = 5)
    private String name;

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

}
