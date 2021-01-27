package com.example.ProjectNova.Nova.DAO;

import java.io.File;
import java.util.List;

public interface PhotoDAO {
    public void setPreferedBucket(String name);

    public void uploadFiles(List<File> files, List<String> paths);

    public void uploadFile(String articleTitle, String author, String name, File f);

    public void uploadFile(String path, File f);

    public File getFileForArticle(String articleTitle, String author, String name);

    public List<File> getAllFilesForArticle(String articleTitle, String author);

    public File getFile(String path);

    void deleteAllImagesFromPath(String s);
}
