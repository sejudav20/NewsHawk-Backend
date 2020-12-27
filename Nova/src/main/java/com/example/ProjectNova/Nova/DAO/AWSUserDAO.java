package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public User getUser(String id) {
        return null;
    }

    @Override
    public UserContent getUserContent(String userId) {
        return null;
    }

    @Override
    public String getPassword(String userId) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }
    public boolean usernameExists(String name){
        return false;
    }

    @Override
    public List<Article> getReadLater(String userId) {
        return null;
    }

    @Override
    public void addSub(String username, String author) {

    }

    @Override
    public void addLiked(String username, String author, String articleName) {

    }

    @Override
    public void addFollow(String username, String author) {

    }

    @Override
    public void updateUserContent(UserContent userContent) {

    }

    @Override
    public void createComment(String articleId, Comment comment) {

    }

    @Override
    public void updateComment(String articleId, Comment comment) {

    }

    @Override
    public void deleteComment(String articleId, String user, String timestamp) {

    }

    @Override
    public void getComment(String articleId, String user, String timestamp) {

    }

    public List<Article> getUserHistory(String userId) {
        return null;
    }
}
