package com.techyhacky.quotesadda.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.techyhacky.quotesadda.Activity.CategoryActivity;
import com.techyhacky.quotesadda.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private ArrayList<String> mBlogTitleList = new ArrayList<>();
    //private ArrayList<String> mAuthorNameList = new ArrayList<>();
    //private ArrayList<String> mBlogUploadDateList = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;

    public DataAdapter(CategoryActivity activity, ArrayList<String> mBlogTitleList, ArrayList<String> mAuthorNameList, ArrayList<String> mBlogUploadDateList) {
        this.mActivity = activity;
        this.mBlogTitleList = mBlogTitleList;
        // this.mAuthorNameList = mAuthorNameList;
        // this.mBlogUploadDateList = mBlogUploadDateList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_blog_title.setText(mBlogTitleList.get(position));
        //  holder.tv_blog_author.setText(mAuthorNameList.get(position));
        // holder.tv_blog_upload_date.setText(mBlogUploadDateList.get(position));
    }

    @Override
    public int getItemCount() {
        return mBlogTitleList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_blog_title, tv_blog_author, tv_blog_upload_date;

        public MyViewHolder(View view) {
            super(view);
            tv_blog_title = (TextView) view.findViewById(R.id.row_tv_blog_title);
            //tv_blog_author = (TextView) view.findViewById(R.id.row_tv_blog_author);
            //tv_blog_upload_date = (TextView) view.findViewById(R.id.row_tv_blog_upload_date);
        }
    }
}