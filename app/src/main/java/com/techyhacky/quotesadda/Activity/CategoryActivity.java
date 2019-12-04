package com.techyhacky.quotesadda.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;

import com.squareup.picasso.Picasso;
import com.techyhacky.quotesadda.Adapters.DataAdapter;
import com.techyhacky.quotesadda.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private String catName = "";
    private ProgressDialog mProgressDialog;
    private String url = "";
    private String imageUrl = "";
    private ArrayList<String> mAuthorNameList = new ArrayList<>();
    private ArrayList<String> mBlogUploadDateList = new ArrayList<>();
    private ArrayList<String> mBlogTitleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RelativeLayout rl = findViewById(R.id.actCat);

        if (getIntent().hasExtra("catUrl")) {
            url = getIntent().getStringExtra("catUrl");
            imageUrl = getIntent().getStringExtra("imgUrl");
            catName = getIntent().getStringExtra("catName");
        }


//        ActionBar ab = getActionBar();
//        ab.setTitle(catName);

        //Set Background image of Quotes.
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            URL urlImage = new URL(imageUrl);
            Bitmap bitmap = BitmapFactory.decodeStream(urlImage.openConnection().getInputStream());
            Drawable image = new BitmapDrawable(rl.getResources(), bitmap);
            rl.setBackground(image);

        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        if(catId==0){
//            url = "https://www.wishesmsg.com/best-friendship-messages-quotes/";
//        }else if(catId==1){
//            url = "https://www.wishesmsg.com/heart-touching-love-messages/";
//        }else if


        new Description().execute();
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(CategoryActivity.this);
            //mProgressDialog.setTitle("Fetching Latest Quotes");
            mProgressDialog.setMessage("Fetching Latest Quotes For You...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document mBlogDocument = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements mElementDataSize = mBlogDocument.select("p[class=m]");
                // Locate the content attribute
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++) {
                    //Elements mElementAuthorName = mBlogDocument.select("span[class=vcard author post-author test]").select("a").eq(i);
                    //String mAuthorName = "Aditya";//mElementAuthorName.text();

                    //Elements mElementBlogUploadDate = mBlogDocument.select("span[class=post-date updated]").eq(i);
                    // mBlogUploadDate = "Rathore";//mElementBlogUploadDate.text();

                    Elements mElementBlogTitle = mBlogDocument.select("p[class=m]").eq(i);
                    String mBlogTitle = mElementBlogTitle.text();

                    //mAuthorNameList.add(mAuthorName);
                    //mBlogUploadDateList.add(mBlogUploadDate);
                    mBlogTitleList.add(mBlogTitle);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.act_recyclerview);

            DataAdapter mDataAdapter = new DataAdapter(CategoryActivity.this, mBlogTitleList, mAuthorNameList, mBlogUploadDateList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mDataAdapter);

            mProgressDialog.dismiss();
        }
    }
}
