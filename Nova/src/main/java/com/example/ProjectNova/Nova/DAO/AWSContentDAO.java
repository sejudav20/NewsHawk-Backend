package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.ArticleInfo;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.util.List;

@Repository("ContentDao")
public class AWSContentDAO implements ContentDAO {
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
    public Article createArticle(Article article) {
        return null;
    }

    @Override
    public ReadList createReadList(String userId, ReadList readList) {
        return null;
    }

    @Override
    public Comment createComment(Comment comment) {
        return null;
    }

    @Override
    public ArticleInfo createArticleInfo(String articleId, String info) {
        return null;
    }

    @Override
    public Article getArticle(String articleName, String author) {
        return null;
    }

    @Override
    public ReadList getReadListById(String userId, String name) {
        return null;
    }

    @Override
    public void updateArticle(String author, String originalName, Article article) {

    }

    @Override
    public void updateReadList(ReadList readList) {

    }

    @Override
    public void updateArticleInfo(String articleId, String info) {

    }

    @Override
    public void updateReadList(String userId, ReadList readList) {

    }

    @Override
    public void updateComment(Article name, String articleAuthor, Comment comment) {

    }

    @Override
    public void deleteArticle(String name, String author) {

    }

    @Override
    public void deleteReadList(String id) {

    }

    @Override
    public void deleteComment(Article id, String commentId) {

    }

    @Override
    public List<Comment> getArticleComments(String articleId) {
        return null;
    }

    @Override
    public void addLike(String author, String articleName) {

    }

    @Override
    public void addFollow(String author) {

    }

    public void addArticleToReadList(String author, String name, List<String> articleName, List<String> ids) {
    }

    @Override
    public void removeArticleFromReadlist(String userName, String name, List<String> articleName, List<String> authorName) {

    }

    @Override
    public void deleteReadList(String userName, String name) {

    }


    public void addSub(String author) {
    }
}
