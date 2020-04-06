package com.ahmed.newsfeed.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahmed.newsfeed.MyDateUtils;
import com.ahmed.newsfeed.R;
import com.ahmed.newsfeed.databinding.FragmentArticleDetailsBinding;
import com.ahmed.newsfeed.models.Article;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

public class ArticleDetailsFragment extends Fragment {
    private FragmentArticleDetailsBinding binding;

    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    public ArticleDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentArticleDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Article article = ArticleDetailsFragmentArgs.fromBundle(getArguments()).getArticle();

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
        binding.textViewArticleDescription.setText(article.getDescription());
        binding.textViewArticleTitle.setText(article.getTitle());

        binding.textViewArticleDate.setText(MyDateUtils.convertDate(
                "yyyy-MM-dd'T'HH:mm:ss",
                "MMMM dd, yyyy",
                article.getPublishedAt(),
                Locale.ENGLISH,
                Locale.ENGLISH
        ));

        binding.buttonOpenWebsite.setOnClickListener(v -> {
            Uri webPage = Uri.parse(article.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
            if (intent.resolveActivity(activity.getPackageManager()) != null)
                startActivity(intent);
        });
    }
}
