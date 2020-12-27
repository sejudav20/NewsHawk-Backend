package com.example.ProjectNova.Nova.API;

import com.example.ProjectNova.Nova.Errors.AuthenticationException;
import com.example.ProjectNova.Nova.Errors.UsernameAlreadyExistException;
import com.example.ProjectNova.Nova.Model.*;
import com.example.ProjectNova.Nova.Service.CreatorService;
import com.example.ProjectNova.Nova.Service.PhotoStorageService;
import com.example.ProjectNova.Nova.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
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
    @PostMapping(path = "/updateUserContent")
    public void updateUserContent(@RequestBody UserContent userContent) {

        userS.updateUserContent(userContent);
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
    @PostMapping(path = "/createReadList/{userName}/{name}")
    public void createReadList(@PathVariable("username")String userName, @PathVariable("name")String name) {
        creatorS.createReadList(userName, name, new ArrayList<String>());
    }

    @PostMapping(path = "/addToReadList/{userName}/{name}")
    public void addToReadList(@PathVariable("username")String userName, @PathVariable("name")String name, @RequestBody()List<List<String>> articleNameAuthor) {
        ArrayList<String> articleName=new ArrayList();
        ArrayList<String> authorName=new ArrayList();
        for( List<String> list:articleNameAuthor){
            articleName.add(list.get(0));
            authorName.add(list.get(1));
        }
        creatorS.addArticleToReadlist(userName, name, articleName, authorName);
    }
    @PostMapping(path = "/removeFromReadList/{userName}/{name}")
    public void removeFromReadList(@PathVariable("username")String userName, @PathVariable("name")String name,@RequestBody List<List<String>> articleNameAuthor) {
        ArrayList<String> articleName=new ArrayList();
        ArrayList<String> authorName=new ArrayList();
        for( List<String> list:articleNameAuthor){
            articleName.add(list.get(0));
            authorName.add(list.get(1));
        }
        creatorS.removeArticleFromReadlist(userName, name, articleName, authorName);
    }
    @DeleteMapping(path = "/deleteReadList/{userName}/{name}")
    public void deleteReadList(@PathVariable("username")String userName, @PathVariable("name")String name) {
        creatorS.deleteReadList(userName, name);
    }

    //Article Creation
    @PostMapping(path = "/createArticle")
    public void createArticle(@RequestBody Article article) {
        creatorS.createArticle(article);
    }

    @PostMapping(path="/addArticleImages/{author}/{name}")
    public void addArticleImages(@PathVariable("author")String author,@PathVariable("name")String name,@RequestParam("images") MultipartFile[] images) {

        try {
            photoS.addArticleImages(author,name,images);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping(path="/setArticleThumbnail/{author}/{name}")
    public void setArticleThumbnail(@PathVariable("author")String author,@PathVariable("name")String name,@RequestParam("Thumbnail") MultipartFile thumbnail) {
        try {
            photoS.addArticleThumbnail(author,name,thumbnail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(path="/getArticleThumbnail/{author}/{name}"
    )
    public @ResponseBody File getArticleThumbNail(@PathVariable("author")String author,@PathVariable("name")String name) throws IOException {
        return photoS.getArticleThumbnail(author,name);
    }
    @DeleteMapping(path="/deleteArticle/{author}/{name}")
    public void deleteArticle(@PathVariable("author")String author,@PathVariable("name")String name){
        creatorS.deleteArticle(author,name);
        photoS.deleteAllImagesForArticle(author,name);
    }
    @PostMapping(path="/updateArticle/{author}/{originalName}")
    public void updateArticle(@PathVariable("author")String author,@PathVariable("originalName")String originalName,@RequestBody Article newArticle){
        creatorS.updateArticle(author,originalName,newArticle);
    }

    @PostMapping(path="/addLike/{username}/{author}/{name}")
    public void likeAnArticle(@PathVariable("username")String username,
                              @PathVariable("author")String author,@PathVariable("name")String articleName){
        userS.likeAnArticle(username,author,articleName);
    }

/////Comments//////////////////////////////////////////////////////


    @GetMapping(path="/getArticleComments/{articleId}")
    public List<Comment> getArticleComments(@PathVariable("articleId")String articleId){
        return creatorS.getArticleComments(articleId);
    }
    @PostMapping(path="/createComment/{articleId}")
    public void createComment(@PathVariable("articleId")String articleId,@RequestBody Comment comment){
        userS.createComment(articleId,comment);
    }
    @PostMapping(path="/updateComment/{articleId}/{user}/{timestamp}")
    public void updateComment(@PathVariable("articleId")String articleId,@PathVariable("user")String user,@PathVariable("timestamp")String timestamp,@RequestBody Comment comment){
        userS.updateComment(articleId,comment);
    }
    @PostMapping(path="/deleteComment/{articleId}/{user}/{timestamp}")
    public void deleteComment(@PathVariable("articleId")String articleId,@PathVariable("user")String user,@PathVariable("timestamp")String timestamp){
        userS.deleteComment(articleId,user,timestamp);
    }
    @PostMapping(path="/getComment/{articleId}/{user}/{timestamp}")
    public void getComment(@PathVariable("articleId")String articleId,@PathVariable("user")String user,@PathVariable("timestamp")String timestamp){
        userS.getComment(articleId,user,timestamp);
    }
    ///Basic operations////////////////

    @PostMapping(path="/addSubscribe/{username}/{author}")
    public void subscribe(@PathVariable("username")String username,@PathVariable("author")String author){
        userS.subscribe(username,author);
    }
    @PostMapping(path="/addSubscribe/{username}/{author}")
    public void follow(@PathVariable("username")String username,@PathVariable("author")String author){
        userS.follow(username,author);
    }


}
