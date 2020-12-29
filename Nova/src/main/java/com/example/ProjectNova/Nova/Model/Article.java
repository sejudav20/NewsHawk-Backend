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
    private Map<String, Object> info;
    private List<String> comments;
    private int viewCount;
    private Date timestamp;
    private String iconId;

    public Article(@JsonProperty("title") String title, @JsonProperty("author") String author, @JsonProperty("thumbnailId")
            String thumbnailId, @JsonProperty("mainContent") String mainContent, @JsonProperty("sources") String sources,
                   @JsonProperty("info") Map<String, Object> info, @JsonProperty("comments") List<String> comments,
                   @JsonProperty("viewCount") int viewCount, @JsonProperty("timeStamp") Date timestamp,
                   @JsonProperty("channelIcon") String iconId) {
        //this.id = id!=null?id:this.id;
        this.author = author != null ? this.author : this.author;
        this.thumbnailId = thumbnailId != null ? thumbnailId : this.thumbnailId;
        this.title = title != null ? title : this.title;
        this.mainContent = mainContent != null ? mainContent : this.mainContent;
        this.sources = sources != null ? sources : this.sources;
        this.info = info != null ? info : this.info;
        this.comments = comments != null ? comments : this.comments;
        this.viewCount = viewCount != 0 ? viewCount : this.viewCount;
        this.timestamp = timestamp != null ? timestamp : this.timestamp;
        this.iconId = iconId != null ? iconId : this.iconId;
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

    public Map<String, Object> getInfo() {
        return info;
    }

    public Map<String, AttributeValue> getInfoAsMap() {
        HashMap<String, AttributeValue> itemValues = new HashMap<String, AttributeValue>();

        for (String s : info.keySet()) {
            Object o = info.get(s);
            if (o instanceof String) {
                itemValues.put(s, AttributeValue.builder().s((String) o).build());
            }
            if (o instanceof String[]) {
                itemValues.put(s, AttributeValue.builder().ns((String[]) o).build());
            }
            if (o instanceof Boolean) {
                itemValues.put(s, AttributeValue.builder().bool((Boolean) o).build());
            }
            if (o instanceof Integer) {
                itemValues.put(s, AttributeValue.builder().n(((Integer) o).toString()).build());
            }
        }
        return itemValues;
    }

    public void setInfo(Map<String, Object> info) {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
