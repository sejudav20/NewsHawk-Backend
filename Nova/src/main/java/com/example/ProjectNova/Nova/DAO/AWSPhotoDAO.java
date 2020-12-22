package com.example.ProjectNova.Nova.DAO;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository("PhotoDao")
public class AWSPhotoDAO implements PhotoDAO{

    @Override
    public void setPreferedBucket(String name) {

    }

    @Override
    public void uploadFiles(List<File> files, List<String> paths) {
        for(int i=0; i<files.size(); i++){
            uploadFile(paths.get(i),files.get(i));
        }
    }

    @Override
    public void uploadFile(String articleTitle, String author, String name, File f) {
        uploadFile(author+"/"+articleTitle+"/"+name,f);
    }

    @Override
    public void uploadFile(String path, File f) {

    }

    @Override
    public File getFileForArticle(String articleTitle, String author, String name) {
        return null;
    }

    @Override
    public List<File> getAllFilesForArticle(String articleTitle, String author) {
        return null;
    }

    @Override
    public File getFile(String path) {
        return null;
    }

    @Override
    public void deleteAllImagesFromPath(String s) {

    }

}
