package com.saif.repository;

import com.saif.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private static final List<User> userList = new ArrayList<>();

    public void saveUser(User user){
        long id = userList.size() + 1;
        user.setId(id);
        userList.add(user);
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

    public void deleteUser(Long id){
        userList.removeIf(user -> user.getId() == id);
    }
}
