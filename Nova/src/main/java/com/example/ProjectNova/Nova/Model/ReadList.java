package com.example.ProjectNova.Nova.Model;

import java.util.List;

public class ReadList {
    //essentially a wrapper for a list of articles
    String id;
    List<String> articles;
    String name;
/////////////Getters and setters////////////////////////////////////////////////////////////////////////
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
