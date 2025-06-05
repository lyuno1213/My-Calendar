package com.example.mycalendar.model;

public class ContentsDTO {

    @Size(max = 12)
    @ContentsIdValid
    private String id;

    private String contents;

    @Size(max = 12)
    private String usersId;

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

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(final String usersId) {
        this.usersId = usersId;
    }

}
