package com.mmhdev.devcv.core.android;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.di.components.AppComponent;
import com.mmhdev.devcv.core.di.components.DaggerAppComponent;
import com.mmhdev.devcv.core.di.modules.ApiModule;
import com.mmhdev.devcv.core.di.modules.AppModule;
import com.mmhdev.devcv.core.di.modules.DataModule;
import com.mmhdev.devcv.core.di.modules.ThreadExecutorsModule;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.RouteDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;

import java.util.ArrayList;
import java.util.List;


public class App extends Application{

    private AppComponent appComponent;

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public static List<StepsDvo> steps = new ArrayList<>();
    public static LatLng myPos;
    public static RouteDvo routeDvo;

    @Override
    public void onCreate() {
        super.onCreate();
        setupAppComponent();
        setupPicasso();
    }

    private void setupAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(getString(R.string.baseUrl),getString(R.string.severUrl)))
                .dataModule(new DataModule())
                .threadExecutorsModule(new ThreadExecutorsModule())
                .build();
    }

    private void setupPicasso(){

    }

    public static LatLng getMyPos() {
        return myPos;
    }

    public static void setMyPos(LatLng myPos) {
        App.myPos = myPos;
    }

    public static List<StepsDvo> getSteps() {
        return steps;
    }

    public static void setSteps(List<StepsDvo> steps) {
        App.steps.clear();
        App.steps.addAll(steps);
    }

    public static RouteDvo getRouteDvo() {
        return routeDvo;
    }

    public static void setRouteDvo(RouteDvo routeDvo) {
        App.routeDvo = routeDvo;
    }
}
