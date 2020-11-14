package com.example.ProjectNova.Nova.Model;

import java.util.Date;
import java.util.List;

public class User {
    //Stores data on each user such as sign in and settings
    private String id;
    private String name;
    private String pasword;
    private String contentId;
    private String profilePic;
    private ReadList articleHistory;
    private ReadList readLater;
    private ReadList liked;
    private List<String> disliked;
    private List<String> following;
    private Date TimeStamp;




////////////////Getters and setters////////////////////////////////////////////////////////////////////////
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ReadList getArticleHistory() {
        return articleHistory;
    }

    public void setArticleHistory(ReadList articleHistory) {
        this.articleHistory = articleHistory;
    }

    public ReadList getReadLater() {
        return readLater;
    }

    public void setReadLater(ReadList readLater) {
        this.readLater = readLater;
    }

    public ReadList getLiked() {
        return liked;
    }

    public void setLiked(ReadList liked) {
        this.liked = liked;
    }

    public List<String> getDisliked() {
        return disliked;
    }

    public void setDisliked(List<String> disliked) {
        this.disliked = disliked;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        TimeStamp = timeStamp;
    }
}
