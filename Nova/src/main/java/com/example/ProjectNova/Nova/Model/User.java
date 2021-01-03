package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Date;
import java.util.List;
@DynamoDbBean
public class User implements Cloneable{
    //Stores data on each user such as sign in and settings
    //private String id;
   //primary key
    private String name;
    private String password;
    private String contentId;
    private String profilePic;
    private List<String> disliked;
    private List<String> following;
    private List<String> subscribed;
    private long timeStamp;
    public User(){

    }
    public User(@JsonProperty("name") String name,@JsonProperty("password") String password,
                @JsonProperty("contentId") String contentId,@JsonProperty("profilePic") String profilePic
               ,@JsonProperty("disliked") List<String> disliked,
                @JsonProperty("following")List<String> following,@JsonProperty("subscribed")List<String> subscribed,
                @JsonProperty("timeStamp") long timeStamp) {


        this.name = name!=null?name:this.name;
        this.password = password!=null?password:this.password;
        this.contentId = contentId!=null?contentId:this.contentId;
        this.profilePic = profilePic!=null?profilePic:this.profilePic;
        this.disliked = disliked!=null?disliked:this.disliked;
        this.following = following!=null?following:this.following;
        this.subscribed = subscribed!=null?subscribed:this.subscribed;
        this.timeStamp = timeStamp!=0?timeStamp:System.currentTimeMillis();

    }

    ////////////////Getters and setters////////////////////////////////////////////////////////////////////////
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
    @DynamoDbPartitionKey
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public List<String> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<String> subscribed) {
        this.subscribed = subscribed;
    }
    public void merge(User user) {
        this.name = user.name!=null?user.name:this.name;
        this.password = user.password!=null?user.password:this.password;
        this.contentId = user.contentId!=null?user.contentId:this.contentId;
        this.profilePic = user.profilePic!=null?user.profilePic:this.profilePic;;
        this.disliked = user.disliked!=null?user.disliked:this.disliked;
        this.following = user.following!=null?user.following:this.following;
        this.subscribed = user.subscribed!=null?user.subscribed:this.subscribed;
        this.timeStamp = user.timeStamp!=0?user.timeStamp:this.timeStamp;
    }
}
