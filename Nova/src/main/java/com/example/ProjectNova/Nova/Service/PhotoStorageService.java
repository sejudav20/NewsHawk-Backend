package com.example.ProjectNova.Nova.Service;

import com.example.ProjectNova.Nova.DAO.AWSPhotoDAO;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoStorageService {
    AWSPhotoDAO photoDAO;

    public PhotoStorageService(@Qualifier("PhotoDao") AWSPhotoDAO aWSPhotoDAO) {
        this.photoDAO = aWSPhotoDAO;
    }

    public List<String> uploadFiles(List<File> files, List<String> paths) {
        return photoDAO.uploadFiles(files, paths);
    }

    public String uploadFile(String path, byte[] file) {
        return photoDAO.uploadFile(path, file);
    }

    public List<File> getArticlePhotos(String articleName, String author) {
        return photoDAO.getAllFilesForArticle(articleName, author);
    }

    public File getArticleFile(String articleName, String author, String name) {
        return photoDAO.getFileForArticle(articleName, author, name);
    }

    public File getProfileThumbnail(String author) {
        return photoDAO.getFile("author/profile");
    }
    public String setProfileThumbnail(String author, MultipartFile thumbnail) throws IOException {
        String ext1 = FilenameUtils.getExtension(thumbnail.getOriginalFilename());
        String url = photoDAO.uploadFile(author+"/profile."+ext1,thumbnail.getBytes());

        return url;
    }
    public File getArticleThumbnail(String author, String articleName) {
        return photoDAO.getFile(author + "/" + articleName + "/Thumbnail");
    }


    public String addArticleThumbnail(String author, String title, MultipartFile thumbnail) throws IOException {

        String ext1 = FilenameUtils.getExtension(thumbnail.getOriginalFilename());
        return uploadFile(author + "/" + title + "/Thumbnail."+ext1, thumbnail.getBytes());
    }

    public void deleteAllImagesForArticle(String author, String name) {
        photoDAO.deleteAllImagesFromPath(author + "/" + name);
    }

    public List<String> addArticleImages(String author, String articleName, MultipartFile[] images) throws IOException {
        List<String> fileLoc=new ArrayList<String>();
        for (MultipartFile m : images) {
            fileLoc.add(uploadFile(author + "/" + articleName + "/" + m.getOriginalFilename(), m.getBytes()));
        }
        return fileLoc;
    }
}
