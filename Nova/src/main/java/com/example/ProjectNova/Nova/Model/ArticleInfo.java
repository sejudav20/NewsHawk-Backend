package com.example.ProjectNova.Nova.Model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

///This class may not be necessary
public class ArticleInfo {
    static public String PARAMETER_SEPARATOR = "(͡°͜ʖ͡°)";
    static public String ITEM_SEPARATOR = "/\\/\\/\\/";


    String articleId;
    Long time;
    List<String> tags;
    List<String> keyWords;
    String title;
    String author;

    // reads string with info for all the parameters above
    //string format "time+tags+keyWords+title+author
    public ArticleInfo(String articleId, String info) {
        this.articleId = articleId;
        Scanner parameterSeperator = new Scanner(info);
        Scanner itemSeperator;
        keyWords = new ArrayList();
        tags = new ArrayList<>();
        parameterSeperator.useDelimiter(PARAMETER_SEPARATOR);
        time = parameterSeperator.nextLong();
        String tagList = parameterSeperator.next();
        itemSeperator = new Scanner(tagList);
        while (itemSeperator.hasNext()) {
            tags.add(itemSeperator.next());
        }
        String keyWordsList = parameterSeperator.next();
        itemSeperator = new Scanner(keyWordsList);
        while (itemSeperator.hasNext()) {
            tags.add(itemSeperator.next());
        }
        title = parameterSeperator.next();
        author = parameterSeperator.next();
    }

}
