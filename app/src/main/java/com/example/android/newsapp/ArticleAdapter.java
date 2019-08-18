package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter <Article> {

    private static final String DATE_SEPARATOR = "T";

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);


        // private LayoutInflater inflater;
   //     private List<Article> articles = new ArrayList<Article>();
//

        //  this.articles = articles;
        //   inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        Article currentArticle = (Article) getItem(position);

        // get section view

        TextView sectionTextView = listItemView.findViewById(R.id.section);
        sectionTextView.setText(currentArticle.getSection());

        //get title view

        TextView titleTextView = listItemView.findViewById(R.id.title);
        titleTextView.setText(currentArticle.getTitle());

        //get date view

        TextView dateTextView = listItemView.findViewById(R.id.publish_date);
        dateTextView.setText(currentArticle.getDate());


        TextView urlTextView = listItemView.findViewById(R.id.url);
        urlTextView.setText(currentArticle.getUrl());

        return listItemView;

    }
}

 //   @Override
//     public Object getItem(int position){
 //       return articles.get(position);
//    }
 //   @Override
//    public int getCount() {
//        return articles.size();
//    }
//    @Override
//    public long getItemId(int position) {
 //       return position;

 //   }
 //       public void setArticles(List<Article> data) {
 //           boolean b = articles.addAll(data);
 //           notifyDataSetChanged();

 //   }
//}
