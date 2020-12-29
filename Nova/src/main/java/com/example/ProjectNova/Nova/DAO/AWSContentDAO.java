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

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public HashMap<String,AttributeValue> hashArticle(Article article){
        HashMap<String,AttributeValue> itemValues = new HashMap<String,AttributeValue>();
        itemValues.put("author", AttributeValue.builder().s(article.getAuthor()).build());
        itemValues.put("title", AttributeValue.builder().s(article.getTitle()).build());
        itemValues.put("thumbnailId", AttributeValue.builder().s(article.getThumbnailId()).build());
        itemValues.put("mainContent", AttributeValue.builder().s(article.getMainContent()).build());
        itemValues.put("sources", AttributeValue.builder().s(article.getSources()).build());
        itemValues.put("info", AttributeValue.builder().m(article.getInfoAsMap()).build());
        itemValues.put("viewCount", AttributeValue.builder().n(String.valueOf(article.getViewCount())).build());
        itemValues.put("timestamp", AttributeValue.builder().s(String.valueOf(article.getTimestamp().getTime())).build());

        return itemValues;
    }
    public HashMap<String,AttributeValue> hashReadList(ReadList readList){
        HashMap<String,AttributeValue> itemValues = new HashMap<String,AttributeValue>();
        itemValues.put("name", AttributeValue.builder().s(readList.getName()).build());
        itemValues.put("author", AttributeValue.builder().s(readList.getAuthor()).build());
        itemValues.put("articles", AttributeValue.builder().ns(readList.getArticles()).build());
        return itemValues;
    }
    public HashMap<String,AttributeValue> hashComments(Comment c){
        HashMap<String,AttributeValue> itemValues = new HashMap<String,AttributeValue>();
        itemValues.put("id", AttributeValue.builder().s(c.getId()).build());
        itemValues.put("username", AttributeValue.builder().s(c.getUsername()).build());
        itemValues.put("content", AttributeValue.builder().s(c.getContent()).build());
        itemValues.put("content", AttributeValue.builder().ns(c.getReplies()).build());
        itemValues.put("timestamp", AttributeValue.builder().s(String.valueOf(c.getTimeStamp().getTime())).build());

        return itemValues;
    }
    public Map<String, Object> getInfoAsMap(Map<String,AttributeValue> info) {
        HashMap<String, Object> itemValues = new HashMap();

        for (String s : info.keySet()) {
            AttributeValue o = info.get(s);

                itemValues.put(s,o.s());

            if (o.hasNs()) {
                itemValues.put(s,o.ns());
            }

        }
        return itemValues;
    }

//    public Article hashOutArticle(Map<String,AttributeValue> r){
//
//       Article a= new Article(r.get("title").s(),r.get("author").s(),r.get("thumbnailId").s(),r.get("mainContent").s(),r.get("sources").s(),r.get("info").m(),null,
//              Integer.valueOf(r.get("viewcount").n()), Date.valueOf(r.get("timestamp").n()),r.get("channelIcon").s());
//
//        return  a;
//    }

    @Override
    public Article createArticle(Article article) {

        HashMap<String,AttributeValue> art=hashArticle(article);
        PutItemRequest request = PutItemRequest.builder()
                .tableName("Articles")
                .item(art)
                .build();

        DynamoDbClient client=getDynamo();
        try {
            client.putItem(request);
        }catch(ResourceNotFoundException r){
            createArticleTable();
            createArticle(article);
        }
        return article;
    }

    @Override
    public ReadList createReadList(String userId, ReadList readList) {
        HashMap<String,AttributeValue> r=hashReadList(readList);
        PutItemRequest request = PutItemRequest.builder()
                .tableName("ReadLists")
                .item(r)
                .build();
        DynamoDbClient client=getDynamo();
        try {
            client.putItem(request);
        }catch(ResourceNotFoundException ex){
            createReadlistTable();
            createReadList(userId,readList);
        }
        return readList;
    }

    @Override
    public Comment createComment(Comment comment) {
        HashMap<String,AttributeValue> c=hashComments(comment);
        PutItemRequest request = PutItemRequest.builder()
                .tableName("Comments")
                .item(c)
                .build();
        DynamoDbClient client=getDynamo();
        try {
            client.putItem(request);
        }catch(ResourceNotFoundException ex){
            createCommentsTable();
            createComment(comment);
        }
        return comment;
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
