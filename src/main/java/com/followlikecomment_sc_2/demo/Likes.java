package com.followlikecomment_sc_2.demo;


import javax.persistence.*;

@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String savedUsernameWhoLiked;



    @ManyToOne
    private Message messages;

    public Likes(){

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSavedUsernameWhoLiked() {
        return savedUsernameWhoLiked;
    }

    public void setSavedUsernameWhoLiked(String savedUsernameWhoLiked) {
        this.savedUsernameWhoLiked = savedUsernameWhoLiked;
    }

    public Message getMessages() {
        return messages;
    }

    public void setMessages(Message messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "id=" + id +
                ", savedUsernameWhoLiked='" + savedUsernameWhoLiked + '\'' +
                '}';
    }
}
