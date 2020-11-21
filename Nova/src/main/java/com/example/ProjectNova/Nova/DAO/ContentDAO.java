package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.ArticleInfo;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;

import java.util.List;

public interface ContentDAO {
    //This will contain methods to store data on channels and the articles on each channel

    //Create
    public Article createArticle(Article article);
    public ReadList createReadList(String userId,ReadList readList);
    public Comment createComment(String articleId,Comment coment);
    public ArticleInfo createArticleInfo(String articleId, String info);
    //Read
    public Article getArticleById(String articleId);
    public Article getArticleById(String UserId,String articleId);
    public ArticleInfo getArticleInfo(String articleId);
    public ReadList getReadListById(String UserId, String readListId);
    //Update
    public void updateArticle(String id,Article article);
    public void updateReadList(ReadList readList);
    public void updateArticleInfo(String articleId,String info);
    public void updateReadList(String userId,ReadList readList);
    public void updateComment(Article id,Comment comment);
    //Delete
    public void deleteArticle(String id);

    public void deleteReadList(String id);
    public void deleteComment(Article id,String commentId);


    List<Comment> getArticleComments(String articleId);
}
