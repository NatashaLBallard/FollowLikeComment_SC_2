package com.followlikecomment_sc_2.demo;


import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


    private String commentDescription;

    private String commentTest;

    private String savedCommenterName;




    @ManyToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "message_id")
    private Message message;


    @ManyToOne (cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment() {
    }

    public Comment(String commentDescription, String commentTest, Message message, User user) {
        this.commentDescription = commentDescription;
        this.commentTest = commentTest;
        this.message = message;
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentTest() {
        return commentTest;
    }

    public void setCommentTest(String commentTest) {
        this.commentTest = commentTest;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentDescription='" + commentDescription + '\'' +
                '}';
    }


    public String getSavedCommenterName() {
        return savedCommenterName;
    }

    public void setSavedCommenterName(String savedCommenterName) {
        this.savedCommenterName = savedCommenterName;
    }
}
