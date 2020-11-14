package com.example.ProjectNova.Nova.Model;

import java.util.List;

public class UserContent {
    //contains data on articles and overall channel stats
 private List<String> articles;
 private int followers;
 private int userId;
 private List<ReadList> readLists;
 private int channelViews;
 private double percentFollowers;






 /////////////Getters and setters////////////////////////////////////////////////////////////////////////
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

 public int getUserId() {
  return userId;
 }

 public void setUserId(int userId) {
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
