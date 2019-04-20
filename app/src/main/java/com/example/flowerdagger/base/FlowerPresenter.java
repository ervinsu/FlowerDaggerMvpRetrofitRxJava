package com.example.flowerdagger.base;

import android.util.Log;

import com.example.flowerdagger.model.FlowerResponse;
import com.example.flowerdagger.service.FlowerViewInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FlowerPresenter implements Observer<FlowerResponse>, Presenter {
    private CompositeDisposable mCompositeDisposeable;
    private FlowerViewInterface mViewInterface;

    public FlowerPresenter(FlowerViewInterface mViewInterface) {
        this.mViewInterface = mViewInterface;
    }

    @Override
    public void onError(Throwable e) {
        mViewInterface.error(e.getMessage());
    }

    @Override
    public void onComplete() {
//        mViewInterface.complete();
    }

    @Override
    public void onSubscribe(Disposable d) {
//        mCompositeDisposeable.add(d);
    }

    @Override
    public void onNext(FlowerResponse flowerResponses) {
        mViewInterface.flowers(flowerResponses);
    }

    public void fetchFlower() {
        unSubscribeAll();
        subscribe(mViewInterface.getFlowers(), FlowerPresenter.this);
    }

    private void subscribe(Observable<List<FlowerResponse>> flowers, FlowerPresenter flowerPresenter) {
        mCompositeDisposeable = new CompositeDisposable();
        mCompositeDisposeable.add(flowers
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<FlowerResponse>>() {
                    @Override
                    public void accept(List<FlowerResponse> flowerResponses) {
                        Log.d("BIJIQ",flowerResponses.size()+"");
                        mViewInterface.complete(flowerResponses);
                    }
                }));
    }

    @Override
    public void onCreate() {
        subscribe(mViewInterface.getFlowers(), FlowerPresenter.this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    @Override
    public void onDestroy() {
        unSubscribeAll();
    }

    protected void unSubscribeAll(){
        if(mCompositeDisposeable != null){
            mCompositeDisposeable.dispose();
            mCompositeDisposeable.clear();
        }
    }

    private CompositeDisposable configureSubscription() {
        if(mCompositeDisposeable == null || mCompositeDisposeable.isDisposed()){
            mCompositeDisposeable = new CompositeDisposable();
        }
        return mCompositeDisposeable;
    }
}
