package com.mmhdev.devcv.ui.screens.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.internal.PolylineEncoding;
import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.android.BaseActivity;
import com.mmhdev.devcv.core.utils.MapUtils;
import com.mmhdev.devcv.core.utils.MarkerUtils;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.domain.repository.EntitiesRepository;
import com.mmhdev.devcv.location.FusedLocationProvider;
import com.mmhdev.devcv.ui.di.MapComponent;
import com.mmhdev.devcv.ui.dvo.BusDvo;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.DriversDvo;
import com.mmhdev.devcv.ui.dvo.EntityDvo;
import com.mmhdev.devcv.ui.dvo.MapDataDvo;
import com.mmhdev.devcv.ui.dvo.PlaceDetailDvo;
import com.mmhdev.devcv.ui.dvo.RouteDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;
import com.mmhdev.devcv.ui.screens.direction.DirectionActivity;


import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 */
public class MapActivity extends BaseActivity implements MapView, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener,
        GoogleMap.OnMapClickListener {

    public final static int REQUEST_DIRECTION = 2131;

    @BindView(R.id.progress)
    protected View vProgress;
    @BindView(R.id.map_place)
    LinearLayout vPlaceDetailsView;
    @BindView(R.id.map_place_info_bus_title)
    TextView vTime;
    @BindView(R.id.map_place_info_name)
    TextView vStation;
    private Marker busMarker;
    private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    Map<String , Polyline> mHashMap = new HashMap<String , Polyline>();


    @Inject
    protected FusedLocationProvider fusedLocationProvider;
    @Inject
    protected MapPresenter mapPresenter;
    @BindView(R.id.map_activity_settings)
    protected CheckBox checkBox;

    private GoogleMap googleMap;
    private Marker myLastLocationMarker;
    private MarkerAdapter markerAdapter;
    private List<Marker> entities = new ArrayList<>();
    private List<Marker> drivers = new ArrayList<>();
    private ScheduledFuture futer;

    private Polyline poly;


    //Android native
    public static void start(Activity activity) {
        Intent intent = new Intent(activity, MapActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        setupDagger();
        ButterKnife.bind(this);
        SupportMapFragment fragment = SupportMapFragment.newInstance();
        fragment.getMapAsync(this);
        replaceFragment(fragment, SupportMapFragment.class.getName()).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fusedLocationProvider.unregister();
        mapPresenter.detachView();
        googleMap = null;
        executor.shutdown();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DIRECTION && resultCode == RESULT_OK) {

            vPlaceDetailsView.setVisibility(View.GONE);
            if (!App.getSteps().isEmpty()) {
                showPolyline(App.getSteps());
            }
            if (data != null){
                if (!StringUtils.isNullEmpty(data.getStringExtra(DirectionActivity.EXTRA_NUM_BUS))){

                stopHanndler();
                showBusView();
                show(data.getStringExtra(DirectionActivity.EXTRA_NUM_BUS));
                }

            }

        }
    }

    // View
    @Override
    public void showProgress() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgress.setVisibility(View.GONE);
    }

    @Override
    public void showMyPosition(double lat, double lan) {
        if (googleMap == null) return;

        if (myLastLocationMarker != null) {
            myLastLocationMarker.setPosition(new LatLng(lat, lan));
        } else {
            myLastLocationMarker = googleMap.addMarker(MarkerUtils.createMyMarker(this, 0, "Я", lat, lan));
        }
        App.setMyPos(myLastLocationMarker.getPosition());
    }

    @Override
    public void showMapData(MapDataDvo mapDataDvo) {
        markerAdapter.setEntities(mapDataDvo.getEntities());
        for (EntityDvo entityDvo : mapDataDvo.getEntities()) {
            entities.add(googleMap.addMarker(MarkerUtils.createEntityMarker(this, entityDvo)));
        }
        showMyPosition(myLastLocationMarker.getPosition().latitude, myLastLocationMarker.getPosition().longitude);
    }

    @Override
    public void removeOldMapData() {
        for (Marker marker : entities) {
            marker.remove();
        }
        entities.clear();
    }

    @Override
    public void openDirection() {
        DirectionActivity.start(this, REQUEST_DIRECTION);
    }

    @Override
    public void showBusView() {
        RouteDvo dvo = App.getRouteDvo();
        vTime.setText(dvo.getTime());
        vStation.setText(dvo.getFrom());
        showBusMarker(dvo.getLocation());
        vPlaceDetailsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBusView() {
        vPlaceDetailsView.setVisibility(View.GONE);
    }

    @Override
    public void cameraMovementDisable() {
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);

    }

    @Override
    public void cameraMovementEnable() {
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    @Override
    public void moveCamera(double lat, double lon) {
        MapUtils.cameraGoTo(googleMap, new LatLng(lat, lon), 18);
    }

    @Override
    public void showPolyline(List<StepsDvo> steps) {
       if (!mHashMap.isEmpty()){
           Polyline polyline = mHashMap.get("poly");
           polyline.remove();
       }
        for (StepsDvo step : steps) {
            List<com.google.maps.model.LatLng> line = PolylineEncoding.decode(step.getPolyline());
            drow(line, step.getTravelMode().equals("WALKING"));
        }
    }

    @Override
    public void showBusMarker(LatLng location) {
        if (busMarker != null) {
            busMarker.remove();
            vPlaceDetailsView.setVisibility(View.GONE
            );
        }
        busMarker = googleMap.addMarker(new MarkerOptions()
                .position(location));
    }

    @Override
    public void showBusDrivers(List<DriversDvo> drivers,String busNum) {
        View marker = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.bus_marker, null);
        deleteBusss();
        for (DriversDvo dvo : drivers){
            this.drivers.add(googleMap.addMarker(new MarkerOptions()
                    .position(dvo.getLocation())
                    .title(dvo.getNumBus()).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this,marker,busNum)))));

        }
    }

    @Override
    public void deleteBusss() {
        for (Marker marker : drivers){
            marker.remove();
        }
        drivers.clear();
    }

    void stopHanndler(){
//        handler.removeCallbacks(runnable);
        if (futer != null) {
            futer.cancel(true);
            futer = null;
        }
    }

    private void show(String num){
        if (futer == null)
        futer = executor.scheduleWithFixedDelay(() -> {
            if (!StringUtils.isNullEmpty(num)){
                mapPresenter.getBusRouts(num);
            }
//                        handler.postDelayed(this,10000);
//                executor.scheduleWithFixedDelay(runnable,2000,10000, TimeUnit.MILLISECONDS);
        },2000,10000, TimeUnit.MILLISECONDS);
//        handler.postDelayed(runnable,10000);
    }

    private void drow(List<com.google.maps.model.LatLng> polyline, boolean iswalking) {
        PolylineOptions rectOptins = new PolylineOptions();
        for (com.google.maps.model.LatLng lng : polyline) {
            rectOptins.add(new LatLng(lng.lat, lng.lng));
            if (iswalking) {
                rectOptins.color(Color.GRAY);
            } else {
                rectOptins.color(Color.BLUE);
            }
        }

        poly = googleMap.addPolyline(rectOptins);
        mHashMap.put("poly",poly);
    }

    private void setupDagger() {
        App.getApp(this)
                .getAppComponent()
                .plus(new MapComponent.Module(0))
                .inject(this);
    }

    // Android listeners
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow();
            mapPresenter.onMarkerClicked(marker);
            return true;
        });
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);
        markerAdapter = new MarkerAdapter(this);
        googleMap.setInfoWindowAdapter(markerAdapter);

        mapPresenter.attachView(this);
        mapPresenter.onLocationChange(fusedLocationProvider.getLastLocation(this));
        fusedLocationProvider.register(mapPresenter);
        googleMap.setOnMarkerClickListener(this);

    }

    @OnCheckedChanged(R.id.map_activity_settings)
    public void onCameraCheckListener() {
        mapPresenter.onDisableCameraMovement(checkBox.isChecked());
    }

    @OnClick(R.id.map_activity_fab)
    public void onDirectionClick() {
        mapPresenter.onFabClick();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " " + marker.getId(), Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle() + " ", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.d("MAPPER", latLng.latitude + " " + latLng.longitude);
//        mapPresenter.searchPlace(latLng);
    }

    public static Bitmap createDrawableFromView(Context context, View view,String num) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        TextView numTxt = (TextView) view.findViewById(R.id.marker_text);
        numTxt.setText(num);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }



}
