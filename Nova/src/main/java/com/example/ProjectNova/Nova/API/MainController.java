package com.example.ProjectNova.Nova.API;

import com.example.ProjectNova.Nova.Errors.AuthenticationException;
import com.example.ProjectNova.Nova.Errors.UsernameAlreadyExistException;
import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;
import com.example.ProjectNova.Nova.Service.CreatorService;
import com.example.ProjectNova.Nova.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/nova")
@RestController
public class MainController {

    private final CreatorService creatorS;
    private final UserService userS;

    @Autowired
    public MainController(CreatorService creatorS, UserService userService) {
        this.creatorS =creatorS;
        this.userS = userService;
    }

    //////User stuff
    public User createUser(User user){
        return userS.createUser(user);
    }

    public void deleteUser(String name){
        userS.deleteUser(name);
    }
    public User createUser(String name,String password) throws UsernameAlreadyExistException {
        return userS.createUser(name,password);
    }
    public void updateUser(User user){
        userS.updateUser(user);
    }
    public User authenticateUser(String name,String password) throws AuthenticationException {
        return userS.authenticateUser(name,password);
    }
    public UserContent getUserContent(String name){
        return userS.getUserContent(name);
    }
    public User getUser(String name)  {
        User u= null;
        try {
            u = userS.getUserbyName(name).clone();
            u.setPassword(null);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return u;
    }

    ///ReadList editing

    //Article Creation




}
