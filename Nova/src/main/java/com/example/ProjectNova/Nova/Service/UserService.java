package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.DAO.ContentDAO;
import com.example.ProjectNova.Nova.DAO.UserDAO;
import com.example.ProjectNova.Nova.Errors.AuthenticationException;
import com.example.ProjectNova.Nova.Errors.UsernameAlreadyExistException;
import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Model.User;
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

    public Article getArticleById(String article) {
        return cDao.getArticleById(article);
    }
    public List<Article> getUserHistory(String userId){
        return uDao.getUserHistory(userId);
    }
    public List<Article> getReadLater(String userId){
        return uDao.getReadLater(userId);
    }
    public User createUser(User user) {
        String id = IdService.getId();
        User newUser = new User(id, user.getName(), user.getPassword(), id, user.getProfilePic(), new ReadList(IdService.getId(),
                new ArrayList<String>(), "Article History"), new ReadList(IdService.getId(), new ArrayList<String>(), "History"), new ReadList(IdService.getId(), new ArrayList<String>(), "Liked"),
                new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());

        return uDao.createUser(newUser);
    }

    public User createUser(String name, String password) throws UsernameAlreadyExistException {
        String id = IdService.getId();
        if (uDao.usernameExists(name)) {
            throw new UsernameAlreadyExistException();
        }
        User newUser = new User(id, name, password, id, null, new ReadList(IdService.getId(),
                new ArrayList<String>(), "Article History"), new ReadList(IdService.getId(), new ArrayList<String>(), "History"), new ReadList(IdService.getId(), new ArrayList<String>(), "Liked"),
                new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());
        return uDao.createUser(newUser);
    }

    public void updateUser(User user) {
        uDao.updateUser(user);
    }

    public void deleteUser(String id) {
        uDao.deleteUser(id);
    }

    public User getUserById(String userId) {
        return uDao.getUserById(userId);
    }

    public User authenticateUser(String userId, String password) throws AuthenticationException {
        if (uDao.getPassword(userId).equals(password)) {
            return getUserById(userId);
        } else {
            throw new AuthenticationException();
        }
    }


}
