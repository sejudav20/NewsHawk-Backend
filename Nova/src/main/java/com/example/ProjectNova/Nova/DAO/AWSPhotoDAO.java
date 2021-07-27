package com.example.ProjectNova.Nova.DAO;

import com.example.ProjectNova.Nova.Service.AWSInitializer;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository("PhotoDao")
public class AWSPhotoDAO implements PhotoDAO {
    private String bucketName = "project-novus-test";

    public S3Client getS3() {
        return AWSInitializer.getS3();
    }

    @Override
    public void setPreferedBucket(String name) {
        bucketName = name;
    }

    @Override
    public List<String> uploadFiles(List<File> files, List<String> paths) {
        List<String> fileLoc = new ArrayList<String>();
        for (int i = 0; i < files.size(); i++) {
            fileLoc.add(uploadFile(paths.get(i), files.get(i)));
        }
        return fileLoc;

    }
    public String pathify(String path) {
        return "https://" + bucketName + ".s3-us-west-2.amazonaws.com/" + path;
    }

    @Override
    public String uploadFile(String articleTitle, String author, String name, File f) {
        return uploadFile(author + "/" + articleTitle + "/" + name, f);
    }

    @Override
    public String uploadFile(String path, byte[] f) {
        S3Client s3 = getS3();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path).acl(ObjectCannedACL.PUBLIC_READ)
                .build();

        s3.putObject(objectRequest, RequestBody.fromBytes(f));
        return pathify(path);
    }

    public String uploadFile(String path, File f) {
        S3Client s3 = getS3();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(path)
                .build();

        s3.putObject(objectRequest, RequestBody.fromFile(f));
        return pathify(path);
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
