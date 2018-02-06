package com.mmhdev.devcv.ui.screens.direction;

import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.mvp.BasePresenter;
import com.mmhdev.devcv.core.rx.SimpleSubscriber;
import com.mmhdev.devcv.domain.use_case.DirectionsUseCase;
import com.mmhdev.devcv.ui.dvo.DirectionRequestDvo;
import com.mmhdev.devcv.ui.dvo.DirectionsDvo;
import com.mmhdev.devcv.ui.dvo.RoadsDvo;

/**
 * Created by on 04.12.16.
 */

public class DirectionPresenterImpl extends BasePresenter<DirectionView> implements DirectionPresenter {

    private App app;
    private DirectionsUseCase directionsUseCase;

    public DirectionPresenterImpl(App app, DirectionsUseCase directionsUseCase) {
        this.app = app;
        this.directionsUseCase = directionsUseCase;
    }

    @Override
    public void onStartPointClick() {
        if (getView() == null) return;
        getView().onStartPositionClick();
    }

    @Override
    public void onEndPointClick() {

    }

    @Override
    public void getDirection(DirectionRequestDvo dvo) {
        if (getView() == null) return;
        addSubscription(directionsUseCase.getRoads(dvo.getStartPoint(),dvo.getEndPoint()).subscribe(new SimpleSubscriber<DirectionsDvo>(){
            @Override
            public void onNext(DirectionsDvo item) {
                super.onNext(item);
                getView().setData(item);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        }));
    }
}
