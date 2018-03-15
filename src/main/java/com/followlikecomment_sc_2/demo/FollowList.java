package com.followlikecomment_sc_2.demo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FollowList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long theFollowerId;

    private String theFollowerUsername;

    public FollowList(Long theFollowerId) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTheFollowerId() {
        return theFollowerId;
    }

    public void setTheFollowerId(long theFollowerId) {
        this.theFollowerId = theFollowerId;
    }

    public String getTheFollowerUsername() {
        return theFollowerUsername;
    }

    public void setTheFollowerUsername(String theFollowerUsername) {
        this.theFollowerUsername = theFollowerUsername;
    }
}
