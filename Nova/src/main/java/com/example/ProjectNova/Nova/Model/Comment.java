package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Comment {
    /// Class to wrap each comment in
    private String userId;
    private String id;
    private String content;
    private List<String> replies;
    private boolean isReply;
    private Date timeStamp;

    public Comment(@JsonProperty("id")String id,@JsonProperty("userId")String userId, @JsonProperty("content") String content,@JsonProperty("replies") List<String> replies, @JsonProperty("isReply")boolean isReply,@JsonProperty("userIconId") String userIconId,@JsonProperty("timeStamp") Date timeStamp) {
        this.userId = userId!=null?userId:this.userId;
        this.id = id!=null?id:this.id;
        this.content = content!=null?content:this.content;
        this.replies = replies!=null?replies:this.replies;
        this.isReply = isReply;
        this.timeStamp = timeStamp!=null?timeStamp:this.timeStamp;
    }

    /////////////Getters and setters////////////////////////////////////////////////////////////////////////
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
