package com.example.flowerdagger.di.scope;

import com.example.flowerdagger.service.FlowerService;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Provides;
import retrofit2.Retrofit;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {


}
