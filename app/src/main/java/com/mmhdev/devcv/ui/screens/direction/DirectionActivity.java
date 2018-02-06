package com.mmhdev.devcv.ui.screens.direction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.android.BaseActivity;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.ui.di.DirectionComponent;
import com.mmhdev.devcv.ui.dvo.DirectionRequestDvo;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;
import com.mmhdev.devcv.ui.screens.GetMyLocationActivity;
import com.mmhdev.devcv.ui.screens.direction.adapters.DirectionAdapter;
import com.mmhdev.devcv.ui.screens.input.InputActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 04.12.16.
 */

public class DirectionActivity extends BaseActivity implements DirectionView, DirectionAdapter.OnDirectionClickListener {

    public static final int REQUEST_START_POIINT = 1231;
    public static final int REQUEST_END_POIINT = 1261;
    public static final String EXTRA_NUM_BUS = "EXTRA_NUM_BUS";




    @Inject
    DirectionPresenter mPresenter;
    @BindView(R.id.direction_activity_start_tv)
    TextView vStartText;
    @BindView(R.id.direction_activity_end_tv)
    TextView vEndText;
    @BindView(R.id.direction_activity_recycler)
    RecyclerView vRecycler;

    private LatLng startPoint;
    private LatLng endPoint;
    private LatLng currentPoint;
    private DirectionAdapter adapter;



    public static void start(Activity activity, int request) {
        Intent intent = new Intent(activity, DirectionActivity.class);
        activity.startActivityForResult(intent, request);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_activity);
        ButterKnife.bind(this);
        setupDagger();
        mPresenter.attachView(this);
        initRecycler();
        currentPoint = App.getMyPos();
        startPoint = currentPoint;
    }




    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new DirectionComponent.Module())
                .inject(this);
    }

    private void initRecycler(){
        vRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DirectionAdapter(this,this);
        vRecycler.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.direction_activity_close)
    public void close() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @OnClick(R.id.direction_activity_start_tv)
    public void onStartClick() {
        InputActivity.start(this, REQUEST_START_POIINT, true);
    }

    @OnClick(R.id.direction_activity_end_tv)
    public void onEndClick() {
        InputActivity.start(this, REQUEST_END_POIINT, false);
    }

    @Override
    public void onStartPositionClick() {
        InputActivity.start(this, REQUEST_START_POIINT, true);
    }

    @Override
    public void onEndPositionClick() {

    }

    @Override
    public void setData(DirectionsDvo dvo) {
        adapter.setRoutes(dvo.getRoutes());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_START_POIINT){
            if (resultCode == InputActivity.RESULT_ALL_FINE && data != null){
                vStartText.setText(data.getStringExtra(InputActivity.EXTRA_ADDRESS));
                startPoint = new LatLng(data.getDoubleExtra(InputActivity.EXTRA_LAT,0),data.getDoubleExtra(InputActivity.EXTRA_LNG,0));
            }else if (resultCode == InputActivity.RESULT_MY_POS){
                vStartText.setText("Моє місцезнаходження");
                startPoint = currentPoint;
            }/*else if (resultCode == RESULT_CANCELED){
                startPoint = null;
            }*/
        }
        if (requestCode == REQUEST_END_POIINT){
            if (resultCode == InputActivity.RESULT_ALL_FINE && data != null){
                vEndText.setText(data.getStringExtra(InputActivity.EXTRA_ADDRESS));
                endPoint = new LatLng(data.getDoubleExtra(InputActivity.EXTRA_LAT,0),data.getDoubleExtra(InputActivity.EXTRA_LNG,0));
            }/*else if (resultCode == RESULT_CANCELED){
                endPoint = null;
            }*/
        }
        Log.d("LatLang",startPoint != null ? startPoint.toString() : " null");
        Log.d("LatLang",endPoint != null ? endPoint.toString() : " null");
        if (startPoint != null && endPoint != null){
            mPresenter.getDirection(new DirectionRequestDvo(startPoint,endPoint));
        }

    }

    @Override
    public void onDirectionClick(List<StepsDvo> steps,String numBus) {
        App.setSteps(steps);
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NUM_BUS,numBus);
        setResult(RESULT_OK,intent);
        finish();
    }
}
