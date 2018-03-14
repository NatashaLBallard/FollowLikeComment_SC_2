package com.followlikecomment_sc_2.demo;


import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Size(min=1)
    private String alias;


    private String savedUsername;

    @NotNull
    @Size(min=1)
    private String messageName;

    @NotNull
    @Size(min=1)
    private String description;

    @NotNull
    @Size(min=1)
    private String dateLost;

    @URL
    private String image;

    private String found;

    private String messageCategory;

    private String addMessage;

    private String search;

    private String viewCurrentUserMessages;




    @ManyToOne
    private User users;

    public Message(){

    }



    public long getId() {
        return id;
    }

    public void setId(long itemId) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateLost() {
        return dateLost;
    }

    public void setDateLost(String dateLost) {
        this.dateLost = dateLost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }







    @Override
    public String toString() {
        return "Message{" +
                "messageName='" + messageName + '\'' +
                ", alias='" + alias + '\'' +
                ", savedUsername='" + savedUsername + '\'' +
                ", description='" + description + '\'' +
                ", dateLost='" + dateLost + '\'' +
                ", image='" + image + '\'' +
                ", found='" + found + '\'' +
                ", messageCategory='" + messageCategory + '\'' +
                '}';
    }

    public String getAddMessage() {
        return addMessage;
    }

    public void setAddMessage(String addMessage) {
        this.addMessage = addMessage;
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSavedUsername() {
        return savedUsername;
    }

    public void setSavedUsername(String savedUsername) {
        this.savedUsername = savedUsername;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getViewCurrentUserMessages() {
        return viewCurrentUserMessages;
    }

    public void setViewCurrentUserMessages(String viewCurrentUserMessages) {
        this.viewCurrentUserMessages = viewCurrentUserMessages;
    }
}
