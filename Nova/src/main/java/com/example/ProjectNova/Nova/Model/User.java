package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class User implements Cloneable{
    //Stores data on each user such as sign in and settings
    //private String id;
   //primary key
    private String name;
    private String password;
    private String contentId;
    private String profilePic;
    private ReadList articleHistory;
    private ReadList readLater;
    private ReadList liked;
    private List<String> disliked;
    private List<String> following;
    private List<String> subscribed;
    private Date timeStamp;
    public User(){

    }
    public User(@JsonProperty("name") String name,@JsonProperty("password") String password,
                @JsonProperty("contentId") String contentId,@JsonProperty("profilePic") String profilePic,
                @JsonProperty("articleHistory")ReadList articleHistory,@JsonProperty("readLater") ReadList readLater,
                @JsonProperty("liked")ReadList liked,@JsonProperty("disliked") List<String> disliked,
                @JsonProperty("following")List<String> following,@JsonProperty("subscribed")List<String> subscribed,
                @JsonProperty("timeStamp") Date timeStamp) {


        this.name = name!=null?name:this.name;
        this.password = password!=null?password:this.password;
        this.contentId = contentId!=null?contentId:this.contentId;
        this.profilePic = profilePic!=null?profilePic:this.profilePic;
        this.articleHistory = articleHistory!=null?articleHistory:this.articleHistory;
        this.readLater = readLater!=null?readLater:this.readLater;
        this.liked = liked!=null?liked:this.liked;
        this.disliked = disliked!=null?disliked:this.disliked;
        this.following = following!=null?following:this.following;
        this.subscribed = subscribed!=null?subscribed:this.subscribed;
        this.timeStamp = timeStamp!=null?timeStamp:this.timeStamp;
    }

    ////////////////Getters and setters////////////////////////////////////////////////////////////////////////
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
