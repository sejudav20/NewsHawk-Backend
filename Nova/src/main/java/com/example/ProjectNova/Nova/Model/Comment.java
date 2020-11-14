package com.example.ProjectNova.Nova.Model;

import java.util.Date;
import java.util.List;

public class Comment {
    /// Class to wrap each comment in
    String userId;
    String id;
    String content;
    List<String> replies;
    boolean isReply;
    private Date TimeStamp;


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
        return TimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        TimeStamp = timeStamp;
    }
}
