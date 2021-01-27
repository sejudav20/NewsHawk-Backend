package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Errors.CreationException;
import com.example.ProjectNova.Nova.Model.*;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.*;

@Repository("UserDao")
public class AWSUserDAO implements UserDAO {
    public void createTable(DynamoDbTable d) {
        d.createTable();
    }

    @Override
    public User createUser(User user) throws CreationException {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        try {
            uTable.putItem(user);
        } catch (ResourceNotFoundException r) {
            createTable(uTable);
            createUser(user);
        } catch (DynamoDbException de) {
            de.printStackTrace();
            throw new CreationException();
        }
        return user;
    }

    @Override
    public UserContent createUserContent(UserContent userContent) throws CreationException {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> uTable = uClient.table("UserContents", TableSchema.fromBean(UserContent.class));
        try {
            uTable.putItem(userContent);
        } catch (ResourceNotFoundException r) {
            createTable(uTable);
            createUserContent(userContent);
        } catch (DynamoDbException de) {
            de.printStackTrace();
            throw new CreationException();
        }
        return userContent;
    }

    @Override
    public User getUser(String name) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        Key key = Key.builder()
                .partitionValue(name)
                .build();
        User u;
        try {
            u = uTable.getItem(key);
        } catch (DynamoDbException de) {
            throw de;
        }
        return u;
    }

    @Override
    public UserContent getUserContent(String userContentName) {
        DynamoDbEnhancedClient ucClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> ucTable = ucClient.table("UserContents", TableSchema.fromBean(UserContent.class));
        Key key = Key.builder()
                .partitionValue(userContentName)
                .build();
        UserContent uc;
        try {
            uc = ucTable.getItem(key);
        } catch (DynamoDbException de) {
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
            if (!results.isEmpty()) {

                System.out.println(results);

                password = results.get("password").s();
            }
        } catch (DynamoDbException e) {
            throw e;
        }
        return password;
    }

    @Override
    public void updateUser(User user) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<User> uTable = uClient.table("Users", TableSchema.fromBean(User.class));
        try {
            uTable.updateItem(user);
        } catch (DynamoDbException de) {
            throw de;
        }
    }

    @Override
    public void deleteUser(String userName) {
        DynamoDbClient ddc = AWSInitializer.getClient();
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("name", AttributeValue.builder()
                .s(userName)
                .build());
        DeleteItemRequest dir = DeleteItemRequest.builder()
                .tableName("Users")
                .key(keyMap)
                .build();
        try {
            ddc.deleteItem(dir);
        } catch (DynamoDbException de) {
            throw de;
        }
    }

    public boolean usernameExists(String name) {
        return !(getUser(name) == null);
    }

    @Override
    public List<Article> getReadLater(String userName) {
        return null;
    }


    @Override
    public void updateUserContent(UserContent userContent) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<UserContent> uTable = uClient.table("UserContents", TableSchema.fromBean(UserContent.class));
        try {
            uTable.updateItem(userContent);
        } catch (DynamoDbException de) {
            throw de;
        }
    }

    @Override
    public void createComment(String articleId, Comment comment) throws CreationException { // articleId already has the correct format
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));

        try {
            comment.setId(articleId);
            uTable.putItem(comment);
        } catch (ResourceNotFoundException r) {
            createTable(uTable);
            createComment(articleId, comment);
        } catch (DynamoDbException de) {
            System.out.println(de);

            throw new CreationException();
        }
    }

    @Override
    public void updateComment(String articleId, Comment comment) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));
        try {
            comment.setId(articleId);
            uTable.updateItem(comment);
        } catch (DynamoDbException de) {
            throw de;
        }
    }

    @Override
    public void deleteComment(String articleId, String user, long timestamp) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));
        Key key = Key.builder()
                .partitionValue(articleId).sortValue(timestamp)
                .build();
        Comment c;

        try {
            c = uTable.deleteItem(key);
        } catch (DynamoDbException de) {
            throw de;
        }
    }

    @Override
    public Comment getComment(String articleId, String user, long timestamp) {
        DynamoDbEnhancedClient uClient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> uTable = uClient.table("Comments", TableSchema.fromBean(Comment.class));
        Key key = Key.builder()
                .partitionValue(articleId).sortValue(timestamp)
                .build();
        Comment c;
        try {

            c = uTable.getItem(key);
        } catch (DynamoDbException de) {
            throw de;
        }
        return c;
    }

    public List<Article> getUserHistory(String userId) {
        return null;
    }
}
