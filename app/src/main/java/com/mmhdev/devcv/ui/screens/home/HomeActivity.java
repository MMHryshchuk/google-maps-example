package com.mmhdev.devcv.ui.screens.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.android.BaseActivity;
import com.mmhdev.devcv.ui.di.HomeComponent;
import com.mmhdev.devcv.ui.screens.GetMyLocationActivity;
import com.mmhdev.devcv.ui.screens.map.MapActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 */
public class HomeActivity extends BaseActivity implements HomeView{
    private static final int REQUEST_MY_LOCATION = 1;

    @Inject
    protected HomePresenter homePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        App.getApp(this)
                .getAppComponent()
                .plus(new HomeComponent.Module())
                .inject(this);
        ButterKnife.bind(this);
        homePresenter.attachView(this);
        GetMyLocationActivity.start(this, REQUEST_MY_LOCATION);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        homePresenter.detachView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MY_LOCATION && resultCode == RESULT_OK){
            final double lat = data.getDoubleExtra(GetMyLocationActivity.EXTRA_LAT, 0);
            final double lon = data.getDoubleExtra(GetMyLocationActivity.EXTRA_LON, 0);
            homePresenter.onMyLocationSelected(lat, lon);
            showMapScreen(0);
        }
    }

    @Override
    public void showMapScreen(int type) {
        MapActivity.start(this);
    }

    @Override
    public void showGetMyLocationScreen() {
        GetMyLocationActivity.start(this, REQUEST_MY_LOCATION);
    }

    @OnClick(R.id.home_activity_btn_first)
    public void onFirstClick(){
        homePresenter.onFirstClicked();
    }

    @OnClick(R.id.home_activity_btn_second)
    public void onSecondClick(){
        homePresenter.onSecondClicked();
    }
}
