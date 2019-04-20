package com.example.flowerdagger.base;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter implements Presenter {

    private CompositeDisposable mCompositeDisposeable;

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {
        configureSubscription();
    }

    private CompositeDisposable configureSubscription() {
        if(mCompositeDisposeable == null || mCompositeDisposeable.isDisposed()){
            mCompositeDisposeable = new CompositeDisposable();
        }
        return mCompositeDisposeable;
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

    protected <F> void subscribe(Observable<F> observable, Observer observer){
//        Subscription subscription = observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.computation())
//                .subscribe(observer);
//        mCompositeDisposeable.add(observable.observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.newThread()));
    }
}
