package com.ahmed.newsfeed.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.newsfeed.ItemClickedListener;
import com.ahmed.newsfeed.MyGeneric;
import com.ahmed.newsfeed.databinding.DrawerItemBinding;
import com.ahmed.newsfeed.models.DrawerItem;
import com.ahmed.newsfeed.viewholders.DrawerItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class DrawerItemAdapter extends RecyclerView.Adapter<DrawerItemViewHolder> {
    private static final String TAG = "DrawerItemAdapter";

    private List<DrawerItem> drawerItems = new ArrayList<>();
    private ItemClickedListener listener;

    public DrawerItemAdapter(ItemClickedListener listener) {
        this.listener = listener;
    }

    public void setDrawerItems(List<DrawerItem> drawerItems) {
        this.drawerItems = drawerItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DrawerItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DrawerItemViewHolder(DrawerItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerItemViewHolder holder, int position) {
        DrawerItem drawerItem = drawerItems.get(position);
        if(drawerItem != null) {
            holder.itemView.setOnClickListener(view -> {
                MyGeneric<DrawerItem> myGeneric = new MyGeneric<>();
                myGeneric.setValue(drawerItem);
                listener.onItemArticleClicked(myGeneric);
            });
            holder.bindDrawerItem(drawerItem);
        }
        else
            Log.e(TAG, "onBindViewHolder: article is null :(");
    }

    @Override
    public int getItemCount() {
        return drawerItems == null ? 0 : drawerItems.size();
    }
}
