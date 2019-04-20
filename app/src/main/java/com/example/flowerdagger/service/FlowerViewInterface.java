package com.example.flowerdagger.service;

import com.example.flowerdagger.model.FlowerResponse;

import java.util.List;

import io.reactivex.Observable;


public interface FlowerViewInterface {
    void complete(List<FlowerResponse> flowerResponses);

    void error(String message);

    void flowers(FlowerResponse flowerResponse);

    Observable<List<FlowerResponse>> getFlowers();
}
