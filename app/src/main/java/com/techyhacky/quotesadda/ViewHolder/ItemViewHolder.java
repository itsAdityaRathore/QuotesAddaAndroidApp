package com.techyhacky.quotesadda.ViewHolder;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techyhacky.quotesadda.R;

import java.util.List;

public class ItemViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {

    public TextView txtItemName;
    public ImageView imageView;

    private List<Object> recyclerViewItems;

    private ItemClickListener itemClickListener;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        txtItemName = itemView.findViewById(R.id.category_name);
        imageView = itemView.findViewById(R.id.category_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }

}
