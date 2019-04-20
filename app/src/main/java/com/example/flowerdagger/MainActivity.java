package com.example.flowerdagger;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.flowerdagger.application.FlowerApplication;
import com.example.flowerdagger.base.FlowerPresenter;
import com.example.flowerdagger.model.FlowerAdapter;
import com.example.flowerdagger.model.FlowerResponse;
import com.example.flowerdagger.service.FlowerService;
import com.example.flowerdagger.service.FlowerViewInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class MainActivity extends AppCompatActivity implements FlowerViewInterface, FlowerAdapter.FlowerClickListener {

    @Inject
    FlowerService flowerService;

    private FlowerPresenter mPresenter;

    @BindView(R.id.rvFlower)
    RecyclerView recyclerView;
    private FlowerAdapter rvAdapter;
    private ProgressDialog dialog;
    private List<FlowerResponse> responseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        resolveDependency();
        configView();
        mPresenter = new FlowerPresenter(this);
        responseList = new ArrayList<>();
        mPresenter.onCreate();
    }

    private void resolveDependency() {
        ((FlowerApplication) getApplication())
                .getApiComponent()
                .Inject(this);
    }

    private void configView() {
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.hasFixedSize();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        rvAdapter = new FlowerAdapter(this, getLayoutInflater());
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        responseList = new ArrayList<>();
        mPresenter.fetchFlower();
        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading");
        dialog.setMessage("Wait PLease");
        dialog.show();
    }

    @Override
    public void complete(List<FlowerResponse> responses) {
        rvAdapter.addFlower(responses);
        Toast.makeText(this, "Download Complete", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }

    @Override
    public void error(String message) {
        dialog.dismiss();
        Toast.makeText(this, "Downlaod error " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void flowers(FlowerResponse flowerResponse) {
        responseList.add(flowerResponse);
    }

    @Override
    public Observable<List<FlowerResponse>> getFlowers() {
        return flowerService.getflower();
    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext(), "You clicked on " + name, Toast.LENGTH_SHORT).show();
    }
}
