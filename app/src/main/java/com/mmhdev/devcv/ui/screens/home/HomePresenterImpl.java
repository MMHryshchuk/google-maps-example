package com.mmhdev.devcv.ui.screens.home;


import com.mmhdev.devcv.core.mvp.BasePresenter;
import com.mmhdev.devcv.core.rx.SimpleSubscriber;
import com.mmhdev.devcv.domain.entities.MainEntity;
import com.mmhdev.devcv.domain.use_case.UserUseCase;

/**
 */
public class HomePresenterImpl extends BasePresenter<HomeView> implements HomePresenter {
    private UserUseCase userUseCase;

    private boolean isLocationSelected = false;
    private double lat;
    private double lon;
    private int lastSelectedType = -1;

    public HomePresenterImpl(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public void onMyLocationSelected(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        isLocationSelected = true;
        if (lastSelectedType != -1){
            generateEntitiesAndShowMap();
        }
    }

    @Override
    public boolean isMyLocationSelected() {
        return isLocationSelected;
    }

    @Override
    public void onFirstClicked() {
        lastSelectedType = MainEntity.TYPE_POKEMON;
        generateEntitiesAndShowMap();
    }

    @Override
    public void onSecondClicked() {
        lastSelectedType = MainEntity.TYPE_ZOMBI;
        generateEntitiesAndShowMap();
    }

    private void generateEntitiesAndShowMap(){
        if (!isMyLocationSelected()){
            getView().showGetMyLocationScreen();
            return;
        }

        addSubscription(userUseCase.generateEntities(lat, lon).subscribe(new SimpleSubscriber<Boolean>(){
            @Override
            public void onNext(Boolean item) {
                if (getView() == null) return;
                getView().showMapScreen(lastSelectedType);
            }
        }));
    }
}
