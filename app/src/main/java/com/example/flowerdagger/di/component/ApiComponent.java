package com.example.flowerdagger.di.component;

import com.example.flowerdagger.MainActivity;
import com.example.flowerdagger.di.modul.ApiModule;
import com.example.flowerdagger.di.scope.CustomScope;

import dagger.Component;

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void Inject(MainActivity mainActivity);
}
