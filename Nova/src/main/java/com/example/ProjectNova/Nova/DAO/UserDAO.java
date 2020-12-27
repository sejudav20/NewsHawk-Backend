package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;

import java.util.List;

public interface UserDAO {
    ////This class will have the methods to get data on user activities such as favorites,settings and sign in

    //Create

    public User createUser(User user);
    public UserContent createUserContent(UserContent userContent);

    //Read
    public User getUser(String name);
    public UserContent getUserContent(String user);
    public String getPassword(String username);
    public List<Article> getUserHistory(String userId);

    //Update
    public void updateUser(User user);
    //Delete
    public void deleteUser(String id);
    public boolean usernameExists(String name);

    List<Article> getReadLater(String userId);

    void addSub(String username, String author);

    void addLiked(String username, String author, String articleName);

    void addFollow(String username, String author);

    void updateUserContent(UserContent userContent);

    void createComment(String articleId, Comment comment);

    void updateComment(String articleId, Comment comment);

    void deleteComment(String articleId, String user, String timestamp);

    void getComment(String articleId, String user, String timestamp);
}
