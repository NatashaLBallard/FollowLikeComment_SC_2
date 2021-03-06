package com.followlikecomment_sc_2.demo;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long>{

    Iterable <Message> findById (Long id);

    Iterable <Comment> findAllByMessage(String message);


}
