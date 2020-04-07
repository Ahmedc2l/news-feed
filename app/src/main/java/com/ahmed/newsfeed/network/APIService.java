package com.ahmed.newsfeed.network;

import com.ahmed.newsfeed.models.NewsData;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {

    @Headers("Accept: application/json")
    @GET("v1/articles?source=the-next-web&apiKey=533af958594143758318137469b41ba9")
    Observable<Response<NewsData>> getArticlesFromNextWeb();

    @Headers("Accept: application/json")
    @GET("v1/articles?source=associated-press&apiKey=533af958594143758318137469b41ba9")
    Observable<Response<NewsData>> getArticlesFromAssociatedPress();
}
