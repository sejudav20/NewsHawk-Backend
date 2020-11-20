package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;

public interface ContentDAO {
    //This will contain methods to store data on channels and the articles on each channel

    //Create
    public Article createArticle(Article article);
    public ReadList createReadList(String userId,ReadList readList);
    public Comment createComment(String articleId,Comment coment);
    //Read
    public Article getArticleById(String articleId);
    public Article getArticleById(String UserId,String articleId);
    //Update
    public void updateArticle(String id,Article article);
    public void updateReadList(ReadList readList);
    public void updateReadList(String userId,ReadList readList);
    public void updateComment(Article id,Comment comment);
    //Delete
    public void deleteArticle(String id);
    public void deleteReadList(String id);
    public void deleteComment(Article id,String commentId);





}
