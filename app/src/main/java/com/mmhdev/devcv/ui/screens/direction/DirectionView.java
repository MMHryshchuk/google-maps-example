package com.mmhdev.devcv.ui.screens.direction;

import com.mmhdev.devcv.ui.dvo.DirectionsDvo;

/**
 * Created by on 04.12.16.
 */

public interface DirectionView {

    void onStartPositionClick();
    void onEndPositionClick();
    void setData(DirectionsDvo dvo);
}
