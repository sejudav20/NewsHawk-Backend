package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSContentDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import com.example.ProjectNova.Nova.Errors.CreationException;
import com.example.ProjectNova.Nova.Model.Article;
import com.example.ProjectNova.Nova.Model.Comment;
import com.example.ProjectNova.Nova.Model.ReadList;
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

    public Article createArticle(Article article) throws CreationException {
        Article newArticle = new Article(article.getTitle(), article.getAuthor(), article.getThumbnailId(),
                article.getMainContent(), article.getSources(), article.getInfo(), article.getComments(), article.getViewCount(), article.getLiked(),IdService.getTimeStamp(),article.getIconId());
        return cDao.createArticle(newArticle);
    }

    public void updateArticle(String author, String originalName, Article article) {
        Article newArticle = new Article(article.getTitle(), article.getAuthor(), article.getThumbnailId(), article.getMainContent(), article.getSources(), article.getInfo(), article.getComments(), article.getViewCount(), article.getLiked(), article.getTimestamp(),article.getIconId());
        cDao.updateArticle(author,originalName,newArticle);
    }
    public List<Comment> getArticleComments(String articleId){
        return cDao.getArticleComments(articleId);
    }

    public Article getArticleByName(String name,String author) {
        return cDao.getArticle(name,author);
    }

    public void deleteArticle(String name,String author) {
        cDao.deleteArticle(name,author);
    }

    public ReadList createReadList(String author,String name,List<String> ids,List<String> authors){
        return cDao.createReadList(author,new ReadList(author,name,ids,authors));
    }
    public void addArticleToReadlist(String author, String name, List<String> articleName, List<String> articleAuthor){
        cDao.addArticleToReadList(author,name,articleName,articleAuthor);
    }
    public ReadList getReadList(String userId,String readListId){
        return cDao.getReadListById(userId,readListId);
    }


    public void removeArticleFromReadlist(String userName, String name, List<String> articleName, List<String> authorName) {

    }

    public void deleteReadList(String userName, String name) {
    }
}
