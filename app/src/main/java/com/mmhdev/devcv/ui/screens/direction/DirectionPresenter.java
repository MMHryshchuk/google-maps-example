package com.mmhdev.devcv.ui.screens.direction;

import com.mmhdev.devcv.core.mvp.Presenter;
import com.mmhdev.devcv.ui.dvo.DirectionRequestDvo;

/**
 * Created by on 04.12.16.
 */

public interface DirectionPresenter  extends Presenter<DirectionView>{
    void onStartPointClick();
    void onEndPointClick();
    void getDirection(DirectionRequestDvo dvo);
}
