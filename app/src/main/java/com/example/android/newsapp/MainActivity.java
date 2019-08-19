package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;
//import androidx.appcompat.app.AppCompatActivity;

//import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity
     implements androidx.loader.app.LoaderManager.LoaderCallbacks<List<Article>> {


        private static final String LOG_TAG = MainActivity.class.getName();

        private static final String GUARDIAN_REQUEST_URL =
                "https://content.guardianapis.com/search?api-key=f9e15623-f7be-471d-ad33-462e228e2cdb";


        private static final int ARTICLE_LOADER_ID = 1;

        private ArticleAdapter mAdapter;

        private TextView mEmptyStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView articleListView = (ListView) findViewById(R.id.list);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        articleListView.setEmptyView(mEmptyStateTextView);

    //    LoaderManager supportLoaderManager = getSupportLoaderManager();
       // private static final String LOG_TAG = MainActivity.class.getName();


        mAdapter = new ArticleAdapter(this, new  ArrayList<Article>());
        articleListView.setAdapter(mAdapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener()  {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Article currentArticle = mAdapter.getItem(position);
                Uri articleUri = Uri.parse(currentArticle.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                startActivity(websiteIntent);
           }
        });

        ConnectivityManager connMgr = (ConnectivityManager)

                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
           LoaderManager loaderManager = getSupportLoaderManager();
          // LoaderManager supportLoaderManager = getSupportLoaderManager();
           getSupportLoaderManager().initLoader(ARTICLE_LOADER_ID, null, this);
         //   loaderManager.initLoader(ARTICLE_LOADER_ID, null, (LoaderManager.LoaderCallbacks<Object>) this);
        }
        else {
            View loadingIndicator = findViewById(R.id.loading_indicator);

            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }


    @NonNull
    @Override
    public Loader<List<Article>> onCreateLoader(int id, @Nullable Bundle args) {

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", "business");
        uriBuilder.appendQueryParameter("api-key", "test");

        return new ArticleLoader(this, uriBuilder.toString());
    }



    @Override
    public void onLoadFinished(@NonNull Loader<List<Article>> loader, List<Article> articles) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyStateTextView.setText(R.string.no_articles);

        mAdapter.clear();

        if (articles != null && !articles.isEmpty()) {

            mAdapter.addAll(articles);
        }
    }

    private void updateUi(List<Article> articles) {

    }
    @Override
    public void onLoaderReset(@NonNull Loader<List<Article>> loader) {

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
