package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.Date;
import java.util.List;
@DynamoDbBean
public class Comment {
    /// Class to wrap each comment in

    //sortkey
    private String username;
    //Primary key  id= articleName+" "+author
    private String id;
    private String content;
    private List<String> replies;
    private boolean isReply;
    //sortkey
    private Date timeStamp;

    public Comment(@JsonProperty("username")String username,@JsonProperty("articleId")String articleId,
                   @JsonProperty("content") String content,@JsonProperty("replies") List<String> replies,
                   @JsonProperty("isReply")boolean isReply,@JsonProperty("userIconId") String userIconId,
                   @JsonProperty("timeStamp") Date timeStamp) {
        this.username = username!=null?username:this.username;
        this.id = articleId!=null?articleId:this.id;
        this.content = content!=null?content:this.content;
        this.replies = replies!=null?replies:this.replies;
        this.isReply = isReply;
        this.timeStamp = timeStamp!=null?timeStamp:this.timeStamp;
    }

    /////////////Getters and setters////////////////////////////////////////////////////////////////////////

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void setReplies(List<String> replies) {
        this.replies = replies;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }
    @DynamoDbSortKey
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
