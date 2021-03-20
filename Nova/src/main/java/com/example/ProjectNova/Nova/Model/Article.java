package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DynamoDbBean
public class Article {
    //This class stores each individual article
    //   private String id;

    //sort key
    private String author;
    private String thumbnailId;
    //primary key
    private String title;
    private String mainContent;
    private String sources;
    //data and tags for each article
    private Map<String, List<String>> info;
    private List<String> comments;
    private int viewCount;
    private int liked;
    private long timestamp;
    private boolean isPublished;
    private String iconId;

    public Article() {

    }

    public Article(@JsonProperty("title") String title, @JsonProperty("author") String author, @JsonProperty("thumbnailId")
            String thumbnailId, @JsonProperty("mainContent") String mainContent, @JsonProperty("sources") String sources,
                   @JsonProperty("info") Map<String, List<String>> info, @JsonProperty("comments") List<String> comments,
                   @JsonProperty("viewCount") int viewCount, @JsonProperty("liked") int liked, @JsonProperty("timeStamp") long timestamp,
                   @JsonProperty("channelIcon") String iconId, @JsonProperty("isPublished") boolean isPublished) {
        //this.id = id!=null?id:this.id;
        this.author = author != null ? author : this.author;
        this.thumbnailId = thumbnailId != null ? thumbnailId : this.thumbnailId;
        this.title = title != null ? title : this.title;
        this.mainContent = mainContent != null ? mainContent : this.mainContent;
        this.sources = sources != null ? sources : this.sources;
        this.info = info != null ? info : this.info;
        this.comments = comments != null ? comments : this.comments;
        this.viewCount = viewCount != 0 ? viewCount : this.viewCount;
        this.liked = liked != 0 ? liked : this.liked;
        this.timestamp = timestamp != 0 ? timestamp : this.timestamp;
        this.iconId = iconId != null ? iconId : this.iconId;
        this.isPublished = isPublished;
    }

    public void merge(Article article) {
        //this.id = id!=null?id:this.id;
        this.author = author == null ? article.author : this.author;
        this.thumbnailId = thumbnailId == null ? article.thumbnailId : this.thumbnailId;
        this.title = title == null ? article.title : this.title;
        this.mainContent = mainContent == null ? article.mainContent : this.mainContent;
        this.sources = sources == null ? article.sources : this.sources;
        this.info = info == null ? article.info : this.info;
        this.comments = comments == null ? article.comments : this.comments;
        this.viewCount = viewCount == 0 ? article.viewCount : this.viewCount;
        this.liked = liked == 0 ? article.liked : this.liked;
        this.timestamp = timestamp == 0 ? article.timestamp : this.timestamp;
        this.iconId = iconId == null ? article.iconId : this.iconId;
    }

    public String getIconId() {
        return iconId;
    }

    public void setIconId(String iconId) {
        this.iconId = iconId;
    }
/////////////Getters and setters////////////////////////////////////////////////////////////////////////
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    @DynamoDbSortKey
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    @DynamoDbPartitionKey
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getSources() {
        return sources;
    }

    public void setSources(String sources) {
        this.sources = sources;
    }

    public Map<String, List<String>> getInfo() {
        return info;
    }

    public void setInfo(Map<String, List<String>> info) {
        this.info = info;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }
}
