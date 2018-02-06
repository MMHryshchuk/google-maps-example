package com.mmhdev.devcv.ui.screens.direction.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.android.App;
import com.mmhdev.devcv.core.utils.StringUtils;
import com.mmhdev.devcv.ui.dvo.RouteDvo;
import com.mmhdev.devcv.ui.dvo.StepsDvo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by on 05.12.16.
 */

public class DirectionAdapter extends RecyclerView.Adapter<DirectionViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<RouteDvo> routes = new ArrayList<>();
    private OnDirectionClickListener listener;

    public DirectionAdapter(Context context,OnDirectionClickListener listener) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public DirectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.direction_item,parent,false);
        return new DirectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DirectionViewHolder holder, int position) {
        RouteDvo dvo = getRoutes().get(position);
        holder.bind(dvo);
        holder.itemView.setOnClickListener(v ->{
                    App.setRouteDvo(dvo);
            listener.onDirectionClick(dvo.getSteps(), StringUtils.isNullEmpty(dvo.getNumBus()) ? "" : dvo.getNumBus());
        }
        );
    }

    @Override
    public int getItemCount() {
        if (!routes.isEmpty()){
            return routes.size();
        }
        return 0;
    }

    public void setRoutes(List<RouteDvo> routes){
        this.routes.clear();
        this.routes.addAll(routes);
        notifyDataSetChanged();
    }

    public List<RouteDvo> getRoutes (){
        return routes;
    }

    public interface OnDirectionClickListener{
        void onDirectionClick(List<StepsDvo> steps,String numBus);
    }

}
