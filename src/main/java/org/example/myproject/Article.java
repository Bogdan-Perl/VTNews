package org.example.myproject;
public class Article {
    private String title = null;
    private int date = -1;
    private String tag = null;
    private String text = null;


    public Article(String title, int date, String tag, String text)
    {
        this.title = title;
        this.date = date;
        this.tag = tag;
        this.text = text;

    }


    // Getter methods
    public String getTitle() {
        return title;
    }

    public int getDate() {
        return date;
    }

    public String getTag() {
        return tag;
    }

    public String getText() {
        return text;
    }
}

