package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.DAO.ContentDAO;
import com.example.ProjectNova.Nova.DAO.UserDAO;
import com.example.ProjectNova.Nova.Errors.AuthenticationException;
import com.example.ProjectNova.Nova.Errors.UsernameAlreadyExistException;
import com.example.ProjectNova.Nova.Model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service()
public class UserService {
    //this service focuses on user/viewer actions such as adding to favorites, getting recommended articles
    AWSUserDAO uDao;
    AWSContentDAO cDao;

    public UserService(@Qualifier("UserDao") AWSUserDAO aWSUserDAO, @Qualifier("ContentDAO") AWSContentDAO aWSContentDAO) {
        this.uDao = aWSUserDAO;
        this.cDao = aWSContentDAO;
    }
    public ReadList getRecommendArticleList(){
        return null;
    }
    public void getUserData(){

    }
    public List<Comment> getArticleComments(String articleId){
        return cDao.getArticleComments(articleId);
    }

    public Article getArticle(String article,String author) {
        return cDao.getArticle(article,author);
    }
    public List<Article> getUserHistory(String userId){
        return uDao.getUserHistory(userId);
    }
    public List<Article> getReadLater(String userId){
        return uDao.getReadLater(userId);
    }
    public User createUser(User user) {
        String id = IdService.getId();
        User newUser = new User(user.getName(), user.getPassword(), id, user.getProfilePic(), new ReadList(user.getName(),"Read Later",
                new ArrayList<String>()), new ReadList(user.getName(), "History",new ArrayList<String>() ), new ReadList(user.getName(),"Liked", new ArrayList<String>() ),
                new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());

        return uDao.createUser(newUser);
    }
    public UserContent getUserContent(String name){
        return uDao.getUserContent(name);
    }

    public User createUser(String name, String password) throws UsernameAlreadyExistException {
        String id = IdService.getId();
        if (uDao.usernameExists(name)) {
            throw new UsernameAlreadyExistException();
        }
        User newUser = new User(name, password, id, null,  new ReadList(name, "Read Later",new ArrayList<String>()), new ReadList(name, "History",new ArrayList<String>() ),new ReadList(name, "Liked",new ArrayList<String>()),
                new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());
        return uDao.createUser(newUser);
    }

    public void updateUser(User user) {
        uDao.updateUser(user);
    }

    public void deleteUser(String id) {
        uDao.deleteUser(id);
    }

    public User getUserbyName(String name) {
        return uDao.getUser(name);
    }

    public User authenticateUser(String userId, String password) throws AuthenticationException {
        if (uDao.getPassword(userId).equals(password)) {
            return getUserbyName(userId);
        } else {
            throw new AuthenticationException();
        }
    }


    public void likeAnArticle(String username, String author, String articleName) {
        uDao.addLiked(username,author,articleName);
        cDao.addLike(author,articleName);

    }

    public void subscribe(String username, String author) {
        uDao.addSub(username,author);
        cDao.addSub(author);
    }

    public void follow(String username, String author) {
        uDao.addFollow(username,author);
        cDao.addFollow(author);
    }
}
