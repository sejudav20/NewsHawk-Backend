package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.ArticleInfo;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.List;

@Repository("ContentDao")
public class AWSContentDAO implements ContentDAO {
    public DynamoDbClient getDynamo(){
        return new AWSInitializer().getClient();
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
    public Article getArticle(String articleName,String author) {
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
