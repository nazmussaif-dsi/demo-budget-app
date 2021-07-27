package com.saif.repository;

import com.saif.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final List<User> userList = new ArrayList<>();

    public User saveUser(User user){
        userList.add(user);
        return user;
    }

    public User getUserById(Long id){
        for(User user : userList){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        return userList;
    }
}
