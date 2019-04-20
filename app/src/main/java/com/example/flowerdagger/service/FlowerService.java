package com.example.flowerdagger.service;

import com.example.flowerdagger.model.FlowerResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FlowerService {

    @GET("/feeds/flowers.json")
    Observable<List<FlowerResponse>> getflower();
}
