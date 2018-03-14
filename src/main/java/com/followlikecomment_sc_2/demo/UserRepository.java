package com.followlikecomment_sc_2.demo;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
    User findUserByUsername(String username);
    User findUserById(long id);
}
