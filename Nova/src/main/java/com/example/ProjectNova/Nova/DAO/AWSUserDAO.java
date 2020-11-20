package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class AWSUserDAO implements UserDAO{
    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public UserContent createUserContent(UserContent userContent) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public UserContent getUserContentByUserId(String userId) {
        return null;
    }

    @Override
    public boolean isAuthorized(String userId, String password) {
        return false;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }
}
