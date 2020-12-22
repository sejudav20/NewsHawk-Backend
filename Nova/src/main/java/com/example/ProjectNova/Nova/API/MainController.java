package com.example.ProjectNova.Nova.API;

import com.example.ProjectNova.Nova.Errors.AuthenticationException;
import com.example.ProjectNova.Nova.Errors.UsernameAlreadyExistException;
import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;
import com.example.ProjectNova.Nova.Service.CreatorService;
import com.example.ProjectNova.Nova.Service.PhotoStorageService;
import com.example.ProjectNova.Nova.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/nova")
@RestController
public class MainController {

    private final CreatorService creatorS;
    private final UserService userS;
    private final PhotoStorageService photoS;

    @Autowired
    public MainController(CreatorService creatorS, UserService userService, PhotoStorageService photoS) {
        this.creatorS = creatorS;
        this.userS = userService;
        this.photoS = photoS;

    }

    //////User stuff/////////////////////////////////////////
    @PostMapping(path = "/createUser")
    public User createUser(@RequestBody User user) {
        return userS.createUser(user);
    }
    @GetMapping(path = "/getUserThumbnail/{name}")
    public File getUserThumbnail(@PathVariable("name")String user){
     return   photoS.getProfileThumbnail(user);
    }

    @DeleteMapping(path = "/deleteUser/{name}")
    public void deleteUser(@PathVariable("name") String name) {
        userS.deleteUser(name);
    }

    @GetMapping(path = "/createUser/{name}/{password}")
    public User createUser(@PathVariable("name") String name, @PathVariable("password") String password) throws UsernameAlreadyExistException {
        return userS.createUser(name, password);
    }

    @PutMapping(path = "/updateUser")
    public void updateUser(@RequestBody User user) {
        userS.updateUser(user);
    }

    @GetMapping(path = "/auth/{name}/{password}/{type}") ///type not implemented yet so it can be anything
    public User authenticateUser(@PathVariable("name") String name, @PathVariable("password") String password, @PathVariable("type") String type) throws AuthenticationException {
        return userS.authenticateUser(name, password);
    }

    @GetMapping(path = "/getUserContent/{name}")
    public UserContent getUserContent(@PathVariable("name") String name) {
        return userS.getUserContent(name);
    }

    @GetMapping(path = "/getUser/{name}")
    public User getUser(@PathVariable("name") String name) {
        User u = null;
        try {
            u = userS.getUserbyName(name).clone();
            u.setPassword(null);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return u;
    }

    ///ReadList editing//////////////////////////////////////////////////////
    public ReadList createReadList(String userName, String name) {
        return creatorS.createReadList(userName, name, new ArrayList<String>());
    }

    public void addToReadList(String userName, String name, List<String> articleName, List<String> authorName) {
        creatorS.addArticleToReadlist(userName, name, articleName, authorName);
    }

    public void removeFromReadList(String userName, String name, List<String> articleName, List<String> authorName) {
        creatorS.removeArticleFromReadlist(userName, name, articleName, authorName);
    }

    public void deleteReadList(String userName, String name) {
        creatorS.deleteReadList(userName, name);
    }

    //Article Creation

    public void createArticle(Article article) {
        creatorS.createArticle(article);
    }

    public void createArticle(Article article, File thumbnail,String thumbnailType) {
        creatorS.createArticle(article);
        photoS.addArticleThumbnail(article.getAuthor(),article.getTitle(),thumbnail,thumbnailType);
    }

    public void createArticle(Article article, File thumbnail,String thumbnailType ,List<File> images) {
        creatorS.createArticle(article);
        photoS.addArticleThumbnail(article.getAuthor(),article.getTitle(),thumbnail,thumbnailType);

    }

    public void setArticleThumbnail(String author,String name,File thumbnail,String thumbnailType) {
        photoS.addArticleThumbnail(author,name,thumbnail,thumbnailType);
    }


    public File getArticleThumbNail(String author,String name) {
        return photoS.getArticleThumbnail(author,name);
    }
    public void deleteArticle(String author,String name){
        creatorS.deleteArticle(author,name);
        photoS.deleteAllImagesForArticle(author,name);
    }
    public void updateArticle(String author,String originalName,Article newArticle){
        creatorS.updateArticle(author,originalName,newArticle);
    }

    ///Basic operations////////////////
    public void likeAnArticle(String username,String author,String articleName){
        userS.likeAnArticle(username,author,articleName);
    }
    public void subscribe(String username,String author){
        userS.subscribe(username,author);
    }

    public void follow(String username,String author){
        userS.follow(username,author);
    }


}
