package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;

public interface UserDAO {
    ////This class will have the methods to get data on user activities such as favorites,settings and sign in

    //Create

    public User createUser(User user);
    public UserContent createUserContent(UserContent userContent);

    //Read
    public User getUserById(String id);
    public UserContent getUserContentByUserId(String userId);
    public boolean isAuthorized(String userId, String password);

    //Update
    public void updateUser(User user);
    //Delete
    public void deleteUser(String id);
}
