package com.example.flowerdagger.di.modul;


import com.example.flowerdagger.di.scope.CustomScope;
import com.example.flowerdagger.service.FlowerService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @CustomScope
    FlowerService provideFlowerService(Retrofit retrofit){
        return retrofit.create(FlowerService.class);
    }
}
