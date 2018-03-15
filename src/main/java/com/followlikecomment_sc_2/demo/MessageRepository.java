package com.followlikecomment_sc_2.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public interface MessageRepository extends CrudRepository<Message,Long> {
    Iterable <Message> findAllByFoundContainingIgnoreCase(String found);
    Iterable <Message> findAllBySavedUsernameContainingIgnoreCase(String alias);
    Iterable <Message> findAllBySavedUsernameIs(String savedUsername);

    Iterable <Message> findAll (Sort sort);
}
