package com.example.ProjectNova.Nova.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

@DynamoDbBean
public class ReadList {
    //essentially a wrapper for a list of articles
    //sort key
    private String author;
    private List<String> articles;
    private List<String> articleAuthors;
    //primary key
    private String name;

    public ReadList() {
    }

    public ReadList(@JsonProperty("author") String author, @JsonProperty("name") String name,
                    @JsonProperty("articles") List<String> articles, @JsonProperty("articleAuthors") List<String> articleAuthors) {
        this.author = author != null ? author : this.author;
        this.articles = articles != null ? articles : this.articles;
        this.articleAuthors = articleAuthors != null ? articleAuthors : this.articleAuthors;
        this.name = name != null ? name : this.name;

    }

    /////////////Getters and setters////////////////////////////////////////////////////////////////////////
    @DynamoDbSortKey
    public String getAuthor() {
        return author;
    }

    public List<String> getArticleAuthors() {
        return articleAuthors;
    }

    public void setArticleAuthors(List<String> articleAuthors) {
        this.articleAuthors = articleAuthors;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getArticles() {
        return articles;
    }

    public void setArticles(List<String> articles) {
        this.articles = articles;
    }

    @DynamoDbPartitionKey
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
