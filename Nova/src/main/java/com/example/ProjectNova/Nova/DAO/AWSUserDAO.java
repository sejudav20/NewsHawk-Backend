package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.User;
import com.example.ProjectNova.Nova.Model.UserContent;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.List;

@Repository("UserDao")
public class AWSUserDAO implements UserDAO{
    public DynamoDbClient getDynamo() {
        return new AWSInitializer().getClient();
    }

    public void createReadlistTable () {
        DynamoDbClient client = getDynamo();


        ///trying to create readlist table
        CreateTableRequest ctr = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("name").attributeType(ScalarAttributeType.S).build(),
                AttributeDefinition.builder().attributeName("author").attributeType(ScalarAttributeType.S).build())
                .keySchema(KeySchemaElement.builder()
                                .attributeName("name")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder().attributeName("author").keyType(KeyType.RANGE).build()).provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build()).tableName("ReadLists").build();
        try {
            CreateTableResponse response = client.createTable(ctr);
        } catch (DynamoDbException s) {

        }


    }
    public void createUserTable () {
        DynamoDbClient client = getDynamo();


        ///trying to create user table table
        CreateTableRequest ctr = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("name").attributeType(ScalarAttributeType.S).build()
        )
                .keySchema(KeySchemaElement.builder()
                        .attributeName("name")
                        .keyType(KeyType.HASH)
                        .build()).provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build()).tableName("Users").build();
        try {
            CreateTableResponse response = client.createTable(ctr);
        } catch (DynamoDbException s) {

        }


    }
    public void createUserContentTable () {
        DynamoDbClient client = getDynamo();


        ///trying to create user table table
        CreateTableRequest ctr = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("name").attributeType(ScalarAttributeType.S).build()
        )
                .keySchema(KeySchemaElement.builder()
                        .attributeName("name")
                        .keyType(KeyType.HASH)
                        .build()).provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build()).tableName("UserContent").build();
        try {
            CreateTableResponse response = client.createTable(ctr);
        } catch (DynamoDbException s) {

        }

    }
    public void createCommentsTable () {
        DynamoDbClient client = getDynamo();


        ///trying to create user table table
        CreateTableRequest ctr = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("id").attributeType(ScalarAttributeType.S).build(),
                AttributeDefinition.builder().attributeName("username+timestamp").attributeType(ScalarAttributeType.S).build()
        )
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)
                        .build(),KeySchemaElement.builder()
                        .attributeName("username+timestamp")
                        .keyType(KeyType.RANGE)
                        .build()).provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build()).tableName("Comments").build();
        try {
            CreateTableResponse response = client.createTable(ctr);
        } catch (DynamoDbException s) {

        }


    }

    public void createArticleTable() {
        DynamoDbClient client = getDynamo();
        CreateTableRequest ctr2 = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("title").attributeType(ScalarAttributeType.S).build(),
                AttributeDefinition.builder().attributeName("author").attributeType(ScalarAttributeType.S).build())
                .keySchema(KeySchemaElement.builder()
                                .attributeName("title")
                                .keyType(KeyType.HASH)
                                .build(),
                        KeySchemaElement.builder().attributeName("author").keyType(KeyType.RANGE).build()).provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build()).tableName("Articles").build();
        try {
            CreateTableResponse response = client.createTable(ctr2);
        } catch (DynamoDbException s) {

        }
    }

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
