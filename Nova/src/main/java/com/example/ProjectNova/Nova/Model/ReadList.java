package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReadList {
    //essentially a wrapper for a list of articles
    private String id;
    private List<String> articles;
    private String name;

    public ReadList(@JsonProperty("id")String id, @JsonProperty("articles")List<String> articles,@JsonProperty("name") String name) {
        this.id = id!=null?id:this.id;
        this.articles = articles!=null?articles:this.articles;
        this.name = name!=null?name:this.name;
    }

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
