package com.followlikecomment_sc_2.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    FollowListRepository followListRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void followUser(Long theFollowerId){
//        FollowList followUser = userRepository.findUserByUsername(theFollowerUsername);
//        followUser.setTheFollowerUsername();
        followListRepository.save(new FollowList(theFollowerId));
        System.out.println( followListRepository.findByTheFollowerId(theFollowerId));

    }

    public void unfollowUser(Long theFollowerId){
        followListRepository.delete(followListRepository.findByTheFollowerId(theFollowerId));
        System.out.println( followListRepository.findByTheFollowerId(theFollowerId));
    }


}
