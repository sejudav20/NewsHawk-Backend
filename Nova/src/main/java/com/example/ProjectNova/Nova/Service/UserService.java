package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.DAO.ContentDAO;
import com.example.ProjectNova.Nova.DAO.UserDAO;
import com.example.ProjectNova.Nova.Errors.*;
import com.example.ProjectNova.Nova.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UserService(@Qualifier("UserDao") AWSUserDAO aWSUserDAO, @Qualifier("ContentDao") AWSContentDAO aWSContentDAO) {
        this.uDao = aWSUserDAO;
        this.cDao = aWSContentDAO;
    }

    public ReadList getRecommendArticleList() {
        return null;
    }

    public void getUserData() {

    }

    public List<Comment> getArticleComments(String articleId) {
        return cDao.getArticleComments(articleId);
    }

    public Article getArticle(String article, String author) throws ObjectDoesNotExistException {
        return cDao.getArticle(article, author);
    }

    public List<Article> getUserHistory(String userId) {
        return uDao.getUserHistory(userId);
    }

    public List<Article> getReadLater(String userId) {
        return uDao.getReadLater(userId);
    }

    public User createUser(User user) throws CreationException, UsernameAlreadyExistException {
        if (uDao.usernameExists(user.getName())) {
            throw new UsernameAlreadyExistException();
        }
        String id = IdService.getId();
        User newUser = new User(user.getName(), user.getPassword(), id, user.getProfilePic(),
                new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());
        UserContent u = new UserContent(user.getName(), new ArrayList<String>(), 0, 0, IdService.getId(), new ArrayList<String>(), 0, 0);
        uDao.createUserContent(u);
        String name = user.getName();
        cDao.createReadList(name, new ReadList(name, "Liked", new ArrayList<String>(), new ArrayList<String>()));
        cDao.createReadList(name, new ReadList(name, "History", new ArrayList<String>(), new ArrayList<String>()));
        cDao.createReadList(name, new ReadList(name, "Read Later", new ArrayList<String>(), new ArrayList<String>()));

        return uDao.createUser(newUser);
    }

    public UserContent getUserContent(String name) throws CreationException, ObjectDoesNotExistException {
        return uDao.getUserContent(name);
    }

    public User createUser(String name, String password) throws UsernameAlreadyExistException, CreationException {
        String id = IdService.getId();
        if (uDao.usernameExists(name)) {
            throw new UsernameAlreadyExistException();
        }
        //String hashedPassword = Encryptor.Encrypt(password);
        User newUser = new User(name, password, id, null,
                new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), IdService.getTimeStamp());
        UserContent u = new UserContent(name, new ArrayList<String>(), 0, 0, IdService.getId(), new ArrayList<String>(), 0, 0);
        uDao.createUserContent(u);

        cDao.createReadList(name, new ReadList(name, "Liked", new ArrayList<String>(), new ArrayList<String>()));
        cDao.createReadList(name, new ReadList(name, "History", new ArrayList<String>(), new ArrayList<String>()));
        cDao.createReadList(name, new ReadList(name, "Read Later", new ArrayList<String>(), new ArrayList<String>()));
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

    public User authenticateUser(String userId, String password) throws AuthenticationException, CreationException {
        //String hashed_password= Encryptor.Encrypt(password);
        if (uDao.getPassword(userId).equals(password)) {
            User e = getUserbyName(userId);
            e.setPassword(null);
            return e;
        } else {
            throw new AuthenticationException();
        }
    }
    public String getPassword(String userId){
        return uDao.getPassword(userId);
    }

    public void likeAnArticle(String username, String articleName, String author) {
        cDao.addLike(username, articleName, author);
    }

    public void subscribe(String username, String author) throws CopyException {
        cDao.addSub(author, username);
    }

    public void follow(String username, String author) throws CopyException {
        cDao.addFollow(author, username);
    }

    public void updateUserContent(UserContent userContent) {
        uDao.updateUserContent(userContent);
    }

    public void createComment(String articleId, Comment comment) throws CreationException {
        Comment c = new Comment(comment.getUsername(), articleId, comment.getContent(), comment.getReplies(), comment.isReply(), comment.getUserIconId(), IdService.getTimeStamp());
        uDao.createComment(articleId, c);
    }

    public void updateComment(String articleId, Comment comment) {
        uDao.updateComment(articleId, comment);
    }

    public void deleteComment(String articleId, String user, long timestamp) {
        uDao.deleteComment(articleId, user, timestamp);
    }

    public Comment getComment(String articleId, String user, long timestamp) throws ObjectDoesNotExistException {
        return uDao.getComment(articleId, user, timestamp);
    }
}
