package com.example.flowerdagger.application;

import android.app.Application;

import com.example.flowerdagger.di.component.ApiComponent;
import com.example.flowerdagger.di.component.DaggerApiComponent;
import com.example.flowerdagger.di.component.DaggerNetworkComponent;
import com.example.flowerdagger.di.component.NetworkComponent;
import com.example.flowerdagger.di.modul.NetworkModule;
import com.example.flowerdagger.model.Constant;

public class FlowerApplication extends Application {
    public ApiComponent apiComponent;

    @Override
    public void onCreate() {
        resolveDepedency();
        super.onCreate();
    }

    private void resolveDepedency() {
        apiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
       return  DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constant.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
