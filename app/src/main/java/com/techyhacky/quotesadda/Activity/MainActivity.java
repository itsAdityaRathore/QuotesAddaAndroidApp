package com.techyhacky.quotesadda.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.techyhacky.quotesadda.Models.Items;
import com.techyhacky.quotesadda.R;
import com.techyhacky.quotesadda.ViewHolder.ItemClickListener;
import com.techyhacky.quotesadda.ViewHolder.ItemViewHolder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Firebase
    FirebaseDatabase database;
    DatabaseReference items, userItem;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseRecyclerAdapter<Items, ItemViewHolder> adapter;

    MenuItem categoryItem;
    String categoryId = "";

    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Init Firebase
        database = FirebaseDatabase.getInstance();
        items = database.getReference("Category");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        //Init View
        recycler_menu = findViewById(R.id.category_recyclerview);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_menu.setLayoutManager(layoutManager);

        if (items != null) {
            loadMenuNew();
        } else
            Toast.makeText(getApplicationContext(), "You Have No Items To View", Toast.LENGTH_SHORT).show();
    }

    private void loadMenuNew() {
        adapter = new FirebaseRecyclerAdapter<Items, ItemViewHolder>(
                Items.class,
                R.layout.category_row_data,
                ItemViewHolder.class,
                items
        ) {
            @Override
            protected void populateViewHolder(ItemViewHolder viewHolder, final Items model, int position) {

                viewHolder.txtItemName.setText(model.getName());
                Picasso.get().load(model.getImage()).into(viewHolder.imageView);
                categoryId = adapter.getItem(position).getName();

                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(MainActivity.this, "click : "+position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                        intent.putExtra("catUrl", model.getUrl());
                        intent.putExtra("imgUrl", model.getImage());
                        intent.putExtra("catName", model.getName());
                        startActivity(intent);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        recycler_menu.setAdapter(adapter);
    }

    private void setSingleEventClick() {

    }
}
