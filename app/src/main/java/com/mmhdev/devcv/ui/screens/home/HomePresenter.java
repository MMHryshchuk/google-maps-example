package com.mmhdev.devcv.ui.screens.home;


import com.mmhdev.devcv.core.mvp.Presenter;

/**
 */
public interface HomePresenter extends Presenter<HomeView> {
    void onMyLocationSelected(double lat, double lon);
    boolean isMyLocationSelected();

    void onFirstClicked();
    void onSecondClicked();
}
