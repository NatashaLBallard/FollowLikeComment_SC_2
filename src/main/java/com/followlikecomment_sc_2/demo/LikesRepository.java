package com.followlikecomment_sc_2.demo;

import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository <Likes, Long>{

    Iterable <Message> findAllBySavedUsernameWhoLikedContainingIgnoreCase(String savedUsernameWhoLiked);


}
