package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Errors.CreationException;
import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.ArticleInfo;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

import java.sql.Date;
import java.util.*;

@Repository("ContentDao")
public class AWSContentDAO implements ContentDAO {
    public DynamoDbClient getDynamo() {
        return new AWSInitializer().getClient();
    }

    public void createReadlistTable() {
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

    public void createUserTable() {
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

    public void createUserContentTable() {
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

    public void createCommentsTable() {
        DynamoDbClient client = getDynamo();


        ///trying to create user table table
        CreateTableRequest ctr = CreateTableRequest.builder().attributeDefinitions(
                AttributeDefinition.builder().attributeName("id").attributeType(ScalarAttributeType.S).build(),
                AttributeDefinition.builder().attributeName("username+timestamp").attributeType(ScalarAttributeType.S).build()
        )
                .keySchema(KeySchemaElement.builder()
                        .attributeName("id")
                        .keyType(KeyType.HASH)
                        .build(), KeySchemaElement.builder()
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

    public HashMap<String, AttributeValue> hashArticle(Article article) {
        HashMap<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();
        itemValues.put("author", AttributeValue.builder().s(article.getAuthor()).build());
        itemValues.put("title", AttributeValue.builder().s(article.getTitle()).build());
        itemValues.put("thumbnailId", AttributeValue.builder().s(article.getThumbnailId()).build());
        itemValues.put("mainContent", AttributeValue.builder().s(article.getMainContent()).build());
        itemValues.put("sources", AttributeValue.builder().s(article.getSources()).build());
        itemValues.put("info", AttributeValue.builder().m(article.getInfoAsMap()).build());
        itemValues.put("viewCount", AttributeValue.builder().n(String.valueOf(article.getViewCount())).build());
        itemValues.put("timestamp", AttributeValue.builder().s(String.valueOf(article.getTimestamp())).build());

        return itemValues;
    }

    public HashMap<String, AttributeValue> hashReadList(ReadList readList) {
        HashMap<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();
        itemValues.put("name", AttributeValue.builder().s(readList.getName()).build());
        itemValues.put("author", AttributeValue.builder().s(readList.getAuthor()).build());
        itemValues.put("articles", AttributeValue.builder().ns(readList.getArticles()).build());
        return itemValues;
    }

    public HashMap<String, AttributeValue> hashComments(Comment c) {
        HashMap<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();
        itemValues.put("id", AttributeValue.builder().s(c.getId()).build());
        itemValues.put("username", AttributeValue.builder().s(c.getUsername()).build());
        itemValues.put("content", AttributeValue.builder().s(c.getContent()).build());
        itemValues.put("content", AttributeValue.builder().ns(c.getReplies()).build());
        itemValues.put("timestamp", AttributeValue.builder().s(String.valueOf(c.getTimeStamp().getTime())).build());

        return itemValues;
    }

    public Map<String, Object> getInfoAsMap(Map<String, AttributeValue> info) {
        HashMap<String, Object> itemValues = new HashMap();

        for (String s : info.keySet()) {
            AttributeValue o = info.get(s);

            itemValues.put(s, o.s());

            if (o.hasNs()) {
                itemValues.put(s, o.ns());
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
    public Article createArticle(Article article) throws CreationException {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Article> atable = eclient.table("Articles", TableSchema.fromBean(Article.class));



        try {
            atable.putItem(article);
        } catch (DynamoDbException r) {
            throw new CreationException();
        }
        return article;
    }

    @Override
    public ReadList createReadList(String userId, ReadList readList) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));
        atable.putItem(readList);

        return readList;
    }

    @Override
    public Comment createComment(Comment comment) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> atable = eclient.table("Comments", TableSchema.fromBean(Comment.class));
        try {
            atable.putItem(comment);
        } catch (DynamoDbException ex) {

        }
        return comment;
    }

    @Override
    public ArticleInfo createArticleInfo(String articleId, String info) {

        return null;
    }

    @Override
    public Article getArticle(String articleName, String author) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Article> atable = eclient.table("Articles", TableSchema.fromBean(Article.class));
        Key key = Key.builder()
                .partitionValue(articleName)
                .sortValue(author)
                .build();
        Article a = atable.getItem(key);
        return a;
    }

    @Override
    public ReadList getReadListById(String userId, String name) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));

        Key key = Key.builder()
                .partitionValue(userId)
                .sortValue(name)
                .build();
        ReadList r = atable.getItem(key);
        return r;
    }

    @Override
    public void updateArticle(String author, String originalName, Article article) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Article> atable = eclient.table("Articles", TableSchema.fromBean(Article.class));

        if (!originalName.equals(article.getTitle())) {
            Key key = Key.builder()
                    .partitionValue(originalName)
                    .sortValue(author)
                    .build();

            article.merge(atable.getItem(key));
            atable.deleteItem(key);
            atable.updateItem(article);
        } else {
            atable.updateItem(article);
        }

    }

    @Override
    public void updateReadList(ReadList readList) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));
        atable.updateItem(readList);

    }

    @Override
    public void updateArticleInfo(String articleId, String info) {

    }

    @Override
    public void updateReadList(String userId, ReadList readList) {
        readList.setAuthor(userId);
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));
        atable.updateItem(readList);
    }

    @Override
    public void updateComment(Article name, String articleAuthor, Comment comment) {
        comment.setId(name + " " + articleAuthor);
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> atable = eclient.table("Comments", TableSchema.fromBean(Comment.class));
        atable.updateItem(comment);
    }
//testing
    @Override
    public void deleteArticle(String name, String author) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Article> atable = eclient.table("Articles", TableSchema.fromBean(Article.class));
        Key key = Key.builder()
                .partitionValue(name)
                .sortValue(author)
                .build();
        atable.deleteItem(key);
    }

    @Override
    public void deleteReadList(String id) {

    }

    public void deleteReadList(String author, String name) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));
        Key key = Key.builder()
                .partitionValue(name)
                .sortValue(author)
                .build();
        atable.deleteItem(key);
    }

    @Override
    public void deleteComment(Article id, String timestamp) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> atable = eclient.table("Comments", TableSchema.fromBean(Comment.class));
        Key key = Key.builder()
                .partitionValue(id.getTitle() + " " + id.getAuthor())
                .sortValue(String.valueOf(timestamp))
                .build();
        atable.deleteItem(key);
    }

    @Override
    public List<Comment> getArticleComments(String articleId) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> atable = eclient.table("Comments", TableSchema.fromBean(Comment.class));
        QueryConditional queryConditional = QueryConditional.keyEqualTo(Key.builder().partitionValue(articleId).build());
        Iterator<Comment> i = atable.query(queryConditional).items().iterator();
        ArrayList a = new ArrayList<Comment>();
        for (Iterator<Comment> it = i; it.hasNext(); ) {
            Comment comment = it.next();
            a.add(comment);
        }
        return a;
    }

    @Override
    public void addLike(String author, String articleName,String username) {
        DynamoDbClient dy = AWSInitializer.getClient();
        HashMap<String, AttributeValueUpdate> updatedValues =
                new HashMap<String, AttributeValueUpdate>();

        // Update the column specified by name with updatedVal
        updatedValues.put("like", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().n("1").build())
                .action(AttributeAction.ADD)
                .build());
        HashMap<String, AttributeValueUpdate> updatedValues2 =
                new HashMap<String, AttributeValueUpdate>();

        // Update the column specified by name with updatedVal
        updatedValues2.put("articles", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ns(articleName).build())
                .action(AttributeAction.ADD)
                .build());
        updatedValues2.put("authors", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ns(author).build())
                .action(AttributeAction.ADD)
                .build());

        HashMap<String, AttributeValue> keys =
                new HashMap();
        keys.put("title", AttributeValue.builder().s(articleName).build());
        keys.put("author", AttributeValue.builder().s(author).build());

        UpdateItemRequest u= UpdateItemRequest.builder().attributeUpdates(updatedValues).key(keys).tableName("Articles")
                .build();

        dy.updateItem(u);
        

    }

    @Override
    public void addFollow(String author) {
        DynamoDbClient dy = AWSInitializer.getClient();
        HashMap<String, AttributeValueUpdate> updatedValues =
                new HashMap();


        updatedValues.put("followers", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().n("1").build())
                .action(AttributeAction.ADD)
                .build());
        HashMap<String, AttributeValue> keys =
                new HashMap();
        keys.put("name", AttributeValue.builder().s(author).build());
        UpdateItemRequest u= UpdateItemRequest.builder().attributeUpdates(updatedValues).key(keys).tableName("usercontent")
                .build();
        dy.updateItem(u);
    }

    public void addArticleToReadList(String user, String name, List<String> articleName, List<String> ids) {
        DynamoDbClient dy = AWSInitializer.getClient();

        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();

        keyToGet.put("name", AttributeValue.builder().s(name).build());
        keyToGet.put("author", AttributeValue.builder().s(user).build());


        HashMap<String, AttributeValueUpdate> uMap = new HashMap();
        uMap.put("authors", AttributeValueUpdate.builder().value(AttributeValue.builder()
                .ns(ids).build()).action(AttributeAction.ADD).build());
        uMap.put("articles", AttributeValueUpdate.builder().value(AttributeValue.builder().
                ns(articleName).build()).action(AttributeAction.ADD).build());


        UpdateItemRequest u = UpdateItemRequest.builder().tableName("ReadLists").key(keyToGet).attributeUpdates(uMap)
                .build();
        dy.updateItem(u);

    }

    @Override
    public void removeArticleFromReadlist(String userName, String name, List<String> articleNames, List<String> authorNames) {
        DynamoDbClient dy = AWSInitializer.getClient();

        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();

        keyToGet.put("name", AttributeValue.builder().s(name).build());
        keyToGet.put("author", AttributeValue.builder().s(userName).build());


        HashMap<String, AttributeValueUpdate> uMap = new HashMap();
        uMap.put("authors", AttributeValueUpdate.builder().value(AttributeValue.builder()
                .ns(authorNames).build()).action(AttributeAction.DELETE).build());
        uMap.put("articles", AttributeValueUpdate.builder().value(AttributeValue.builder().
                ns(articleNames).build()).action(AttributeAction.DELETE).build());


        UpdateItemRequest u = UpdateItemRequest.builder().tableName("ReadLists").key(keyToGet).attributeUpdates(uMap)
                .build();
        dy.updateItem(u);
    }


    public void addSub(String author) {
        DynamoDbClient dy = AWSInitializer.getClient();
        HashMap<String, AttributeValueUpdate> updatedValues =
                new HashMap();


        updatedValues.put("subscribers", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().n("1").build())
                .action(AttributeAction.ADD)
                .build());
        HashMap<String, AttributeValue> keys =
                new HashMap();
        keys.put("name", AttributeValue.builder().s(author).build());
        UpdateItemRequest u= UpdateItemRequest.builder().attributeUpdates(updatedValues).key(keys).tableName("usercontent")
                .build();
        dy.updateItem(u);
    }
}
