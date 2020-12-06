package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserContent {
    //contains data on articles and overall channel stats
 private String name;
 private List<String> articles;
 private int followers;
 private String userId;
 private List<ReadList> readLists;
 private int channelViews;
 private double percentFollowers;

 public UserContent(@JsonProperty("name")String name,@JsonProperty("articles") List<String> articles, @JsonProperty("followers")int followers,@JsonProperty("userId") String userId,@JsonProperty("readLists") List<ReadList> readLists, @JsonProperty("channelViews")int channelViews,@JsonProperty("percentFollowers") double percentFollowers) {
  this.name = this.name !=null? this.name :this.name;
  this.articles = articles!=null?articles:this.articles;
  this.followers = followers!=0?followers:this.followers;
  this.userId = userId!=null?userId:this.userId;
  this.readLists = readLists!=null?readLists:this.readLists;
  this.channelViews = channelViews!=0?channelViews:this.channelViews;
  this.percentFollowers = percentFollowers!=0?percentFollowers:this.percentFollowers;
 }

 /////////////Getters and setters////////////////////////////////////////////////////////////////////////
 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }
 public List<String> getArticles() {
  return articles;
 }

 public void setArticles(List<String> articles) {
  this.articles = articles;
 }

 public int getFollowers() {
  return followers;
 }

 public void setFollowers(int followers) {
  this.followers = followers;
 }

 public String getUserId() {
  return userId;
 }

 public void setUserId(String userId) {
  this.userId = userId;
 }

 public List<ReadList> getReadLists() {
  return readLists;
 }

 public void setReadLists(List<ReadList> readLists) {
  this.readLists = readLists;
 }

 public int getChannelViews() {
  return channelViews;
 }

 public void setChannelViews(int channelViews) {
  this.channelViews = channelViews;
 }

 public double getPercentFollowers() {
  return percentFollowers;
 }

 public void setPercentFollowers(double percentFollowers) {
  this.percentFollowers = percentFollowers;
 }
}
