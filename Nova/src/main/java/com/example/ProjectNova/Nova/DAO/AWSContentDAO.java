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
    public void createTable(DynamoDbTable d){
        d.createTable();
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

    @Override
    public Article createArticle(Article article) throws CreationException {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Article> atable = eclient.table("Articles", TableSchema.fromBean(Article.class));



        try {
            atable.putItem(article);
        }catch(ResourceNotFoundException r) {
            createTable(atable);
            createArticle(article);
        } catch (DynamoDbException r) {
            throw new CreationException();
        }
        return article;
    }
    public List<List<String>> getList(Map<String, AttributeValue> keyMap,String attributeName,String tableName){
        DynamoDbClient ddc = AWSInitializer.getClient();
        GetItemRequest gir = GetItemRequest.builder()
                .projectionExpression(attributeName)
                .key(keyMap)
                .tableName(tableName)
                .build();
        List<List<String>> password=new ArrayList<>();
            String[] s=attributeName.split(",");

        try {
            Map<String, AttributeValue> results = ddc.getItem(gir).item();
            System.out.println(results+ "    hi");
            if(!results.isEmpty()) {
                System.out.println(results);
                for(String e:s){
                    password.add(results.get(e).ss());
                }

                System.out.println(password);

                return password;
            }
        }catch (DynamoDbException e){
            throw e;

        }
        return null;
    }

    @Override
    public ReadList createReadList(String userId, ReadList readList) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));

        DynamoDbClient dy = AWSInitializer.getClient();
        HashMap<String, AttributeValueUpdate> updatedValues =
                new HashMap();
        HashMap<String, AttributeValue> keys =
                new HashMap();
        System.out.println("name "+userId);
        keys.put("name", AttributeValue.builder().s(userId).build());
        List<String> readListNames = getList(keys,"readLists","UserContents").get(0);

        ArrayList<String> aList= new ArrayList<>();
        aList.addAll(readListNames);
               aList.add(readList.getName());

        updatedValues.put("readLists", AttributeValueUpdate.builder()
                .value(AttributeValue.builder().ss(aList).build()).action(AttributeAction.PUT)
                .build());

        UpdateItemRequest u= UpdateItemRequest.builder().attributeUpdates(updatedValues).key(keys).
                tableName("UserContents")
                .build();
        try{
        dy.updateItem(u);

       atable.putItem(readList);}catch(ResourceNotFoundException r) {
            createTable(atable);
            createReadList(userId,readList);
        }

        return readList;
    }

    @Override
    public Comment createComment(Comment comment) {
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<Comment> atable = eclient.table("Comments", TableSchema.fromBean(Comment.class));
        try {
            atable.putItem(comment);
        }catch(ResourceNotFoundException r) {
            createTable(atable);
            createComment(comment);
        }  catch (DynamoDbException ex) {

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
                .partitionValue(name)
                .sortValue(userId)
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
            Article original=atable.getItem(key);


            article.merge(original);
            atable.deleteItem(key);
            atable.putItem(article);
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
        DynamoDbEnhancedClient eclient = AWSInitializer.getEnhancedClient();
        DynamoDbTable<ReadList> atable = eclient.table("ReadLists", TableSchema.fromBean(ReadList.class));
        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();

        keyToGet.put("name", AttributeValue.builder().s(name).build());
        keyToGet.put("author", AttributeValue.builder().s(user).build());
        List<List<String>> lists=getList(keyToGet,"articles,articleAuthors","ReadLists");
        List<String> articles=new ArrayList<>(lists.get(0));
        List<String> articleAuthors=new ArrayList<>(lists.get(1));
        articles.addAll(articleName);
        articleAuthors.addAll(ids);




        HashMap<String, AttributeValueUpdate> uMap = new HashMap();
        uMap.put("articles", AttributeValueUpdate.builder().value(AttributeValue.builder().
                ss(articles).build()).action(AttributeAction.PUT).build());
        uMap.put("articleAuthors", AttributeValueUpdate.builder().value(AttributeValue.builder()
        .ss(articleAuthors).build()).action(AttributeAction.PUT).build());

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
        List<List<String>> lists=getList(keyToGet,"articles,articleAuthors","ReadLists");
        List<String> articles=new ArrayList<>(lists.get(0));
        List<String> articleAuthors=new ArrayList<>(lists.get(1));
        articles.removeAll(articleNames);
        articleAuthors.removeAll(authorNames);
        System.out.println(articles);
        System.out.println(articleAuthors);


        HashMap<String, AttributeValueUpdate> uMap = new HashMap();
        uMap.put("articles", AttributeValueUpdate.builder().value(AttributeValue.builder().
                ss(articleNames).build()).action(AttributeAction.DELETE).build());
        uMap.put("articleAuthors", AttributeValueUpdate.builder().value(AttributeValue.builder()
                .ss(authorNames).build()).action(AttributeAction.DELETE).build());

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
