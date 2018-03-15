package com.followlikecomment_sc_2.demo;

import org.springframework.data.repository.CrudRepository;

public interface FollowListRepository extends CrudRepository <FollowList, Long> {

    FollowList findByTheFollowerId (Long theFollowerID);


}
