package com.example.android.newsapp;

public class Article {
    public String mSection;
    public String mTitle;
    public String mDate;
    public String mUrl;

    public Article(String title, String section, String date, String url) {
        mTitle = title;
        mSection = section;
        mDate = date;
        mUrl = url;
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
}







//https://github.com/PhilomenaMbura/NewsApp/tree/master/app/src