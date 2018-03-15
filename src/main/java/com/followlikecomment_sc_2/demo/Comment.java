package com.followlikecomment_sc_2.demo;


import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


    private String commentDescription;

    private String commentTest;

    @ManyToOne
    private Message messageComment;
//            (fetch = FetchType.EAGER)
//    @JoinColumn(name = "messageid")


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Message getMessageComment() {
        return messageComment;
    }

    public void setMessageComment(Message messageComment) {
        this.messageComment = messageComment;
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
}
