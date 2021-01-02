package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSPhotoDAO;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PhotoStorageService {
    AWSPhotoDAO photoDAO;
    public PhotoStorageService(@Qualifier("UserDao") AWSPhotoDAO aWSPhotoDAO){
        this.photoDAO=aWSPhotoDAO;
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


    public void addArticleThumbnail(String author, String title, MultipartFile thumbnail) throws IOException {
        Tika tika=new Tika();
        File th = new File("src/main/resources/"+thumbnail.getOriginalFilename());
        uploadFile(author+"/"+title+"/Thumbnail."+tika.detect(th),th);
        th.delete();
    }

    public void deleteAllImagesForArticle(String author, String name) {
        photoDAO.deleteAllImagesFromPath(author+"/"+name);
    }

    public void addArticleImages(String author, String articleName, MultipartFile[] images) throws IOException {
        for(MultipartFile m:images){
            Tika tika=new Tika();
            File th = new File("src/main/resources/"+m.getOriginalFilename());
            uploadFile(author+"/"+articleName+"/"+m.getOriginalFilename(),th);
            th.delete();
        }
    }
}
