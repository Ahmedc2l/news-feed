package com.ahmed.newsfeed.viewholders;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.newsfeed.databinding.DrawerItemBinding;
import com.ahmed.newsfeed.models.DrawerItem;
import com.bumptech.glide.Glide;

public class DrawerItemViewHolder extends RecyclerView.ViewHolder {
    private final DrawerItemBinding binding;

    public DrawerItemViewHolder(@NonNull DrawerItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindDrawerItem(DrawerItem drawerItem){
        if(!drawerItem.getSelected())
            binding.imageViewSelected.setVisibility(View.INVISIBLE);
        else
            binding.imageViewSelected.setVisibility(View.VISIBLE);
        binding.textViewTitle.setText(drawerItem.getTitle());
        Glide.with(binding.getRoot())
                .load(drawerItem.getIcon())
                .into(binding.imageViewIcon);
    }
}
