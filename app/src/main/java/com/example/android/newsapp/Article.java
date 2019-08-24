package com.example.android.newsapp;

public class Article {
    public String mSection;
    public String mTitle;
    public String mDate;
    public String mUrl;
    public String mAuthor;


    public Article(String title, String section, String url, String date, String author) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
        mAuthor = author;

    }

    public String getTitle(){
        return mTitle;
    }
    public String getSection(){
        return mSection;
    }

    public String getDate(){
        return mDate;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getAuthor(){
        return mAuthor;
    }
}






