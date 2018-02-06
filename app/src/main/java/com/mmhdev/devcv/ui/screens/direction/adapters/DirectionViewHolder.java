package com.mmhdev.devcv.ui.screens.direction.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.ui.dvo.RouteDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 05.12.16.
 */

public class DirectionViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.direction_item_duration)
    TextView vDuration;
    @BindView(R.id.direction_item_distance)
    TextView vDistance;
    @BindView(R.id.direction_item_station)
    TextView vStation;
    @BindView(R.id.direction_item_walk_frame)
    FrameLayout vWalkFrame;
    @BindView(R.id.direction_item_bus_frame)
    FrameLayout vBusFrame;
    @BindView(R.id.direction_item_num_bus)
    TextView vBusNum;

    public DirectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(RouteDvo dvo){
        vBusFrame.setVisibility(View.GONE);
        vWalkFrame.setVisibility(View.GONE);
        for (StepsDvo step : dvo.getSteps()){
            if (step.getTravelMode().equals("WALKING")){
                vWalkFrame.setVisibility(View.VISIBLE);
            }
            if (step.getTravelMode().equals("TRANSIT")){
                vBusFrame.setVisibility(View.VISIBLE);
            }
        }
        vDistance.setText(dvo.getDistance());
        vDuration.setText(dvo.getDurations());
        vStation.setText(dvo.getFrom());
        vBusNum.setText(StringUtils.isNullEmpty(dvo.getNumBus()) ? "" : dvo.getNumBus());

    }
}
