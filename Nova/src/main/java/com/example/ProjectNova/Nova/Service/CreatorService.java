package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
import com.example.ProjectNova.Nova.Model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorService {
    //This service provides methods that will aid a creator such as adding new articles and managing views
    AWSUserDAO uDao;
    AWSContentDAO cDao;

    public CreatorService(@Qualifier("UserDao") AWSUserDAO aWSUserDAO, @Qualifier("ContentDao") AWSContentDAO aWSContentDAO) {
        this.uDao = aWSUserDAO;
        this.cDao = aWSContentDAO;
    }

    public Article createArticle(Article article) {
        Article newArticle = new Article(IdService.getId(), article.getAuthorId(), article.getThumbnailId(),
                article.getTitle(), article.getMainContent(), article.getSources(), article.getInfo(), article.getComments(), article.getViewCount(), IdService.getTimeStamp());
        return cDao.createArticle(newArticle);
    }

    public void updateArticle(Article article) {
        Article newArticle = new Article(article.getId(), article.getAuthorId(), article.getThumbnailId(),
                article.getTitle(), article.getMainContent(), article.getSources(), article.getInfo(), article.getComments(), article.getViewCount(), article.getTimestamp());
        cDao.updateArticle(article.getId(), newArticle);
    }
    public List<Comment> getArticleComments(String articleId){
        return cDao.getArticleComments(articleId);
    }

    public Article getArticleById(String article) {
        return cDao.getArticleById(article);
    }

    public void deleteArticle(String id) {
        cDao.deleteArticle(id);
    }

    public ReadList createReadList(String name,List<String> ids){
        return cDao.createReadList(IdService.getId(),new ReadList(IdService.getId(),ids,name));
    }
    public ReadList getReadList(String userId,String readListId){
        return cDao.getReadListById(userId,readListId);
    }


}
