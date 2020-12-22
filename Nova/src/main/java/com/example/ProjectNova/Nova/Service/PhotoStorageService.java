package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSPhotoDAO;
import com.example.ProjectNova.Nova.DAO.AWSUserDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Service
public class PhotoStorageService {
    AWSPhotoDAO photoDAO;
    public PhotoStorageService(@Qualifier("UserDao") AWSPhotoDAO aWSPhotDAO){
        this.photoDAO=aWSPhotDAO;
    }
    public void uploadFiles(List<File> files, List<String> paths) {
        photoDAO.uploadFiles(files,paths);
    }

    public void uploadFile(String path, File file){
        photoDAO.uploadFile(path,file);
    }
    public List<File> getArticlePhotos(String articleName,String author){
       return photoDAO.getAllFilesForArticle(articleName,author);
    }
    public File getArticleFile(String articleName,String author,String name){
        return photoDAO.getFileForArticle(articleName,author,name);
    }

    public File getProfileThumbnail(String author){
        return photoDAO.getFile("author/profile");
    }

    public File getArticleThumbnail(String author,String articleName){
        return photoDAO.getFile(author+"/"+articleName+"/Thumbnail");
    }


    public void addArticleThumbnail(String author, String title, File thumbnail,String type) {
        uploadFile(author+"/"+title+"/Thumbnail."+type,thumbnail);
    }

    public void deleteAllImagesForArticle(String author, String name) {
        photoDAO.deleteAllImagesFromPath(author+"/"+name);
    }
}
