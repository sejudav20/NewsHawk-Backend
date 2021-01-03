package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Errors.CreationException;
import com.example.ProjectNova.Nova.Model.*;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import javax.management.Attribute;
import java.util.*;
//Test
@Repository("UserDao")
public class AWSUserDAO implements UserDAO{
    @Override
    public User createUser(User user) throws CreationException {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        try{
            uTable.putItem(user);
        }catch(DynamoDbException de) {
            throw new CreationException();
        }
        return user;
    }

    @Override
    public UserContent createUserContent(UserContent userContent) throws CreationException {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> uTable = uClient.table("UserContents", TableSchema.fromBean(UserContent.class));
        try{
            uTable.putItem(userContent);
        }catch(DynamoDbException de) {
            throw new CreationException();
        }
        return userContent;
    }

    @Override
    public User getUser(String name){
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        Key key = Key.builder()
                .partitionValue(name)
                .build();
        User u;
        try{
            u = uTable.getItem(key);
        }catch(DynamoDbException de) {
            throw de;
        }
        return u;
    }

    @Override
    public UserContent getUserContent(String userContentName) {
        DynamoDbEnhancedClient ucClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> ucTable = ucClient.table("UserContents",TableSchema.fromBean(UserContent.class));
        Key key = Key.builder()
                .partitionValue(userContentName)
                .build();
        UserContent uc;
        try{
            uc = ucTable.getItem(key);
        }catch(DynamoDbException de) {
            throw de;
        }
        return uc;
    }

    @Override
    public String getPassword(String userName) {
        DynamoDbClient ddc = AWSInitializer.getClient();
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("name", AttributeValue.builder()
                .s(userName)
                .build());
        GetItemRequest gir = GetItemRequest.builder()
                .projectionExpression("password")
                .key(keyMap)
                .tableName("Users")
                .build();
        String password = "";
        try {
            Map<String, AttributeValue> results = ddc.getItem(gir).item();
            if(!results.isEmpty()) {
                password = results.get(userName).toString();
            }
        }catch (DynamoDbException e){
            throw e;
        }
        return password;
    }

    @Override
    public void updateUser(User user) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        try{
            uTable.updateItem(user);
        }catch(DynamoDbException de) {
            throw de;
        }
    }
    @Override
    public void deleteUser(String userName) {
        DynamoDbClient ddc = AWSInitializer.getClient();
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("name",AttributeValue.builder()
                .s(userName)
                .build());
        DeleteItemRequest dir = DeleteItemRequest.builder()
                .tableName("Users")
                .key(keyMap)
                .build();
        try{
            ddc.deleteItem(dir);
        }catch (DynamoDbException de){
            throw de;
        }
    }
    public boolean usernameExists(String name){
        DynamoDbClient ddc = AWSInitializer.getClient();
        boolean contains = false;
        try{
            ScanRequest sr = ScanRequest.builder()
                    .projectionExpression("name")
                    .tableName("Users")
                    .build();
            ScanResponse srp = ddc.scan(sr);
            for(Map<String, AttributeValue> results: srp.items()){
                Set<String> keys = results.keySet();
                contains = keys.contains(name);
            }
        }catch(DynamoDbException dde) {
            throw dde;
        }
        return contains;
    }

    @Override
    public List<Article> getReadLater(String userName) {
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
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> uTable = uClient.table("UserContents", TableSchema.fromBean(UserContent.class));
        try{
            uTable.updateItem(userContent);
        }catch(DynamoDbException de) {
            throw de;
        }
    }

    @Override
    public void createComment(String articleId, Comment comment) throws CreationException { // articleId already has the correct format
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));
        try{
            comment.setId(articleId);
            uTable.putItem(comment);
        }catch(DynamoDbException de) {
            throw new CreationException();
        }
    }

    @Override
    public void updateComment(String articleId, Comment comment) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));
        try{
            comment.setId(articleId);
            uTable.updateItem(comment);
        }catch(DynamoDbException de) {
            throw de;
        }
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
