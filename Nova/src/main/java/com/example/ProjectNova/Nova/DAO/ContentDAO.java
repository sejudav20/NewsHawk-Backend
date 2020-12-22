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
    public void addArticleToReadList(String author, String name, List<String> articleName, List<String> ids);
    void removeArticleFromReadlist(String userName, String name, List<String> articleName, List<String> authorName);
    void deleteReadList(String userName, String name);
    //Update

    public void updateArticle(String author, String originalName, Article article);
    public void updateReadList(ReadList readList);
    public void updateArticleInfo(String articleId,String info);
    public void updateReadList(String userId,ReadList readList);
    public void updateComment(Article name,String articleAuthor,Comment comment);
    //Delete
    public void deleteArticle(String name,String author);
    public void deleteReadList(String id);
    public void deleteComment(Article id,String commentId);


    List<Comment> getArticleComments(String articleId);

    void addLike(String author, String articleName);

    void addFollow(String author);
}
