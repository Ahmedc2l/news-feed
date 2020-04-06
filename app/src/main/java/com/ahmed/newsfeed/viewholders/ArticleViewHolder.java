package com.ahmed.newsfeed.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmed.newsfeed.MyDateUtils;
import com.ahmed.newsfeed.R;
import com.ahmed.newsfeed.databinding.ArticleItemBinding;
import com.ahmed.newsfeed.models.Article;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private final ArticleItemBinding binding;

    public ArticleViewHolder(@NonNull ArticleItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindArticle(Article article){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(binding.getRoot()).load(article.getUrlToImage())
                .apply(options)
                .into(binding.imageViewArticleImage);

        binding.textViewArticleAuthor.setText(article.getAuthor());
        binding.textViewArticleTitle.setText(article.getTitle());

        binding.textViewArticleDate.setText(MyDateUtils.convertDate(
                "yyyy-MM-dd'T'HH:mm:ss",
                "MMMM dd, yyyy",
                article.getPublishedAt(),
                Locale.ENGLISH,
                Locale.ENGLISH
        ));
    }
}
