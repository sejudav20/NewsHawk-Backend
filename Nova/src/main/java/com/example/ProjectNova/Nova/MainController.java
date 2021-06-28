package com.example.ProjectNova.Nova;

import com.example.ProjectNova.Nova.Errors.*;
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
    public User createUser(@RequestBody User user) throws CreationException, UsernameAlreadyExistException {
        return userS.createUser(user);
    }
    @GetMapping(path="/")
    public boolean getLogIn(){
        return true;
    }

    @GetMapping(path = "/getUserThumbnail/{name}")
    public File getUserThumbnail(@PathVariable("name") String user) {
        return photoS.getProfileThumbnail(user);
    }
    @GetMapping(path = "/setUserThumbnail/{name}")
    public String setUserThumbnail(@PathVariable("name") String user,@RequestParam("Profile") MultipartFile thumbnail) {
        try {
            String url =photoS.setProfileThumbnail(user,thumbnail);
            User u=userS.getUserbyName(user);
            u.setProfilePic(url);
            userS.updateUser(u);
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping(path = "/deleteUser/{name}")
    public void deleteUser(@PathVariable("name") String name) {
        userS.deleteUser(name);
    }

    @PostMapping(path = "/createUser/{name}/{password}")
    public User createUser(@PathVariable("name") String name, @PathVariable("password") String password) throws UsernameAlreadyExistException, CreationException {
        return userS.createUser(name, password);
    }

    @PutMapping(path = "/updateUser")
    public void updateUser(@RequestBody User user) {
        userS.updateUser(user);
    }

    @GetMapping(path = "/auth/{name}/{password}/{type}") ///type not implemented yet so it can be anything
    public User authenticateUser(@PathVariable("name") String name, @PathVariable("password") String password, @PathVariable("type") String type) throws AuthenticationException, CreationException {
        return userS.authenticateUser(name, password);
    }

    @GetMapping(path = "/getUserContent/{name}")
    public UserContent getUserContent(@PathVariable("name") String name) throws CreationException, ObjectDoesNotExistException {
        return userS.getUserContent(name);
    }

    @PostMapping(path = "/updateUserContent")
    public void updateUserContent(@RequestBody UserContent userContent) {

        userS.updateUserContent(userContent);
    }

    @GetMapping(path = "/getUser/{name}")
    public User getUser(@PathVariable("name") String name) throws UserDoesNotExistException {
        User u = null;
        System.out.println("Went inside get User method");
        try {
            u = userS.getUserbyName(name);
            u.setPassword(null);
        } catch (NullPointerException e) {
            throw new UserDoesNotExistException();
        }
        return u;
    }

    ///ReadList editing//////////////////////////////////////////////////////
    @PostMapping(path = "/createReadList/{username}/{name}")
    public void createReadList(@PathVariable("username") String userName, @PathVariable("name") String name) {
        creatorS.createReadList(userName, name, new ArrayList<String>(), new ArrayList<String>());
    }

    @GetMapping(path = "/getReadList/{username}/{name}")
    public ReadList getReadList(@PathVariable("username") String userName, @PathVariable("name") String name) throws CreationException, ObjectDoesNotExistException {
        ReadList r = creatorS.getReadList(userName, name);
        return r;
    }

    @PostMapping(path = "/addToReadList/{username}/{name}")
    public void addToReadList(@PathVariable("username") String userName, @PathVariable("name") String name, @RequestBody() List<List<String>> articleNameAuthor) {
        List<String> articleName = new ArrayList<>();
        List<String> authorName = new ArrayList<>();
        for (List<String> list : articleNameAuthor) {
            articleName.add(list.get(0));
            authorName.add(list.get(1));
        }
        creatorS.addArticleToReadlist(userName, name, articleName, authorName);
    }

    @PostMapping(path = "/removeFromReadList/{username}/{name}")
    public void removeFromReadList(@PathVariable("username") String userName, @PathVariable("name") String name, @RequestBody List<List<String>> articleNameAuthor) {
        List<String> articleName = new ArrayList<>();
        List<String> authorName = new ArrayList<>();
        for (List<String> list : articleNameAuthor) {
            articleName.add(list.get(0));
            authorName.add(list.get(1));
        }
        creatorS.removeArticleFromReadlist(userName, name, articleName, authorName);
    }

    @DeleteMapping(path = "/deleteReadList/{username}/{name}")
    public void deleteReadList(@PathVariable("username") String userName, @PathVariable("name") String name) {
        creatorS.deleteReadList(userName, name);
    }

    @PostMapping(path = "/createArticle")
    public void createArticle(@RequestBody Article article) throws CreationException {
        creatorS.createArticle(article);
    }

    @GetMapping(path = "/getArticle/{name}/{author}")
    public Article getArticle(@PathVariable("name") String name, @PathVariable("author") String author) throws CreationException, ObjectDoesNotExistException {
        return creatorS.getArticleByName(name, author);
    }

    @GetMapping(path = "/getArticles/{names}/{authors}")
    public List<Article> getArticles(@PathVariable("names") List<String> names, @PathVariable("authors") List<String> authors) throws CreationException, ObjectDoesNotExistException {
        return creatorS.getArticles(names, authors);
    }

    @PostMapping(path = "/addArticleImages/{author}/{name}")
    public List<String> addArticleImages(@PathVariable("author") String author, @PathVariable("name") String name, @RequestParam("images") MultipartFile[] images, @RequestParam("paths") String[] paths) {
        try {
            return photoS.addArticleImages(author, name, images, paths);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @PostMapping(path = "/setArticleThumbnail/{author}/{name}")
    public String setArticleThumbnail(@PathVariable("author") String author, @PathVariable("name") String name, @RequestParam("Thumbnail") MultipartFile thumbnail) {
        try {
           return photoS.addArticleThumbnail(author, name, thumbnail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(path = "/getArticleThumbnail/{author}/{name}")
    public @ResponseBody
    File getArticleThumbNail(@PathVariable("author") String author, @PathVariable("name") String name) throws IOException {
        return photoS.getArticleThumbnail(author, name);
    }

    @DeleteMapping(path = "/deleteArticle/{author}/{name}")
    public void deleteArticle(@PathVariable("author") String author, @PathVariable("name") String name) {
        creatorS.deleteArticle(author, name);
        photoS.deleteAllImagesForArticle(author, name);
    }

    @PostMapping(path = "/updateArticle/{author}/{originalName}")
    public void updateArticle(@PathVariable("author") String author, @PathVariable("originalName") String originalName, @RequestBody Article newArticle) {
        creatorS.updateArticle(author, originalName, newArticle);
    }

    @PostMapping(path = "/addLike/{username}/{articleName}/{author}")
    public void likeAnArticle(@PathVariable("username") String username, @PathVariable("articleName") String articleName,
                              @PathVariable("author") String author) {
        userS.likeAnArticle(username, articleName, author);
    }

    @GetMapping(path = "/getArticleComments/{articleId}")
    public List<Comment> getArticleComments(@PathVariable("articleId") String articleId) {
        //article_id= articleName+" "+author
        return creatorS.getArticleComments(articleId);
    }

    @PostMapping(path = "/createComment/{articleId}")
    public void createComment(@PathVariable("articleId") String articleId, @RequestBody Comment comment) throws CreationException {
        userS.createComment(articleId, comment);
    }

    @PostMapping(path = "/updateComment/{articleId}/{user}/{timestamp}")
    public void updateComment(@PathVariable("articleId") String articleId, @PathVariable("user") String user, @PathVariable("timestamp") String timestamp, @RequestBody Comment comment) {
        userS.updateComment(articleId, comment);
    }

    @DeleteMapping(path = "/deleteComment/{articleId}/{user}/{timestamp}")
    public void deleteComment(@PathVariable("articleId") String articleId, @PathVariable("user") String user, @PathVariable("timestamp") long timestamp) {
        userS.deleteComment(articleId, user, timestamp);
    }

    @GetMapping(path = "/getComment/{articleId}/{user}/{timestamp}")
    public Comment getComment(@PathVariable("articleId") String articleId, @PathVariable("user") String user, @PathVariable("timestamp") long timestamp) throws ObjectDoesNotExistException {
        return userS.getComment(articleId, user, timestamp);
    }

    @PostMapping(path = "/addSubscribe/{username}/{author}")
    public void subscribe(@PathVariable("username") String username, @PathVariable("author") String author) throws CopyException {
        userS.subscribe(username, author);
    }

    @PostMapping(path = "/addFollow/{username}/{author}")
    public void follow(@PathVariable("username") String username, @PathVariable("author") String author) throws CopyException {
        userS.follow(username, author);
    }
}
