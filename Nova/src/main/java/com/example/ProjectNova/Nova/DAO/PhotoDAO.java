package com.example.ProjectNova.Nova.DAO;



import java.io.File;
import java.util.List;

public interface PhotoDAO {
    public void setPreferedBucket(String name);

    public List<String> uploadFiles(List<File> files, List<String> paths);

    public String uploadFile(String articleTitle, String author, String name, File f);

    public String uploadFile(String path, byte[] f);

    public File getFileForArticle(String articleTitle, String author, String name);

    public List<File> getAllFilesForArticle(String articleTitle, String author);

    public File getFile(String path);

    void deleteAllImagesFromPath(String s);
}
