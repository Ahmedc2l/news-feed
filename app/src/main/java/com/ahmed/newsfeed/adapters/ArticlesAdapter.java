package com.ahmed.newsfeed.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.newsfeed.ItemClickedListener;
import com.ahmed.newsfeed.MyGeneric;
import com.ahmed.newsfeed.databinding.ArticleItemBinding;
import com.ahmed.newsfeed.models.Article;
import com.ahmed.newsfeed.viewholders.ArticleViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private static final String TAG = "ArticlesAdapter";

    private List<Article> articleList = new ArrayList<>();
    private ItemClickedListener listener;

    public ArticlesAdapter(ItemClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(ArticleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        if(article != null) {
            holder.itemView.setOnClickListener(view -> {
                MyGeneric<Article> myGeneric = new MyGeneric<>();
                myGeneric.setValue(article);
                listener.onItemArticleClicked(myGeneric);
            });
            holder.bindArticle(article);
        }
        else
            Log.e(TAG, "onBindViewHolder: article is null :(");
    }

    @Override
    public int getItemCount() {
        return articleList == null ? 0 : articleList.size();
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList.addAll(articleList);
        notifyItemRangeInserted(Math.max(articleList.size() - 1, 0), articleList.size());
    }
}
