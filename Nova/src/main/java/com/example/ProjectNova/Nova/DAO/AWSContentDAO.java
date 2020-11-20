package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import org.springframework.stereotype.Repository;

@Repository("ContentDao")
public class AWSContentDAO implements ContentDAO {
    @Override
    public Article createArticle(Article article) {
        return null;
    }

    @Override
    public ReadList createReadList(String userId, ReadList readList) {
        return null;
    }

    @Override
    public Comment createComment(String articleId, Comment coment) {
        return null;
    }

    @Override
    public Article getArticleById(String articleId) {
        return null;
    }

    @Override
    public Article getArticleById(String UserId, String articleId) {
        return null;
    }

    @Override
    public void updateArticle(String id, Article article) {

    }

    @Override
    public void updateReadList(ReadList readList) {

    }

    @Override
    public void updateReadList(String userId, ReadList readList) {

    }

    @Override
    public void updateComment(Article id, Comment comment) {

    }

    @Override
    public void deleteArticle(String id) {

    }

    @Override
    public void deleteReadList(String id) {

    }

    @Override
    public void deleteComment(Article id, String commentId) {

    }
}
