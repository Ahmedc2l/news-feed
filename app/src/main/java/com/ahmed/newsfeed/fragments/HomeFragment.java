package com.ahmed.newsfeed.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ahmed.newsfeed.R;
import com.ahmed.newsfeed.adapters.ArticlesAdapter;
import com.ahmed.newsfeed.databinding.FragmentHomeBinding;
import com.ahmed.newsfeed.models.Article;
import com.ahmed.newsfeed.models.NewsData;
import com.ahmed.newsfeed.network.APIUtils;
import com.ahmed.newsfeed.network.ErrorUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private FragmentHomeBinding binding;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArticlesAdapter articlesAdapter = new ArticlesAdapter(generic -> {
            Article article = (Article) generic.getValue();
            HomeFragmentDirections.ActionHomeFragmentToArticleDetailsFragment
                    action = HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(article);
            action.setArticle(article);
            Navigation.findNavController(activity, R.id.navHostFragment).navigate(action);
        });
        binding.recyclerViewArticles.setHasFixedSize(true);
        binding.recyclerViewArticles.setAdapter(articlesAdapter);

        Observable<Response<NewsData>> articlesFromNextWeb = APIUtils.getAPIService().getArticlesFromNextWeb();
        Observable<Response<NewsData>> articlesFromAssociatedPress = APIUtils.getAPIService().getArticlesFromAssociatedPress();
        Observable.merge(articlesFromNextWeb, articlesFromAssociatedPress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<NewsData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<NewsData> newsDataResponse) {
                        if(newsDataResponse.isSuccessful()){
                            NewsData newsData = newsDataResponse.body();
                            if(newsData != null)
                                articlesAdapter.setArticleList(newsData.getArticles());
                        }else
                            Toast.makeText(activity, ErrorUtils.parseError(newsDataResponse).toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: error :(", e);
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: loading completed :)");
                    }

                });
    }
}
