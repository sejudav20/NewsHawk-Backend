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
    public Comment createComment(Comment comment);
    public ArticleInfo createArticleInfo(String articleId, String info);
    //Read
    public Article getArticle(String articleName,String author);;
    public ReadList getReadListById(String userId, String name);
    //Update
    public void updateArticle(Article article);
    public void updateReadList(ReadList readList);
    public void updateArticleInfo(String articleId,String info);
    public void updateReadList(String userId,ReadList readList);
    public void updateComment(Article name,String articleAuthor,Comment comment);
    //Delete
    public void deleteArticle(String name,String author);

    public void deleteReadList(String id);
    public void deleteComment(Article id,String commentId);


    List<Comment> getArticleComments(String articleId);
}
