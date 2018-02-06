package com.mmhdev.devcv.ui.screens.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import com.mmhdev.devcv.R;
import com.mmhdev.devcv.ui.dvo.EntityDvo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class MarkerAdapter implements GoogleMap.InfoWindowAdapter  {
    private final LayoutInflater inflater;
    private List<EntityDvo> entities;

    private boolean needToRefresh;

    public MarkerAdapter(Context context){
        inflater = LayoutInflater.from(context);
        entities = new ArrayList<>();
        needToRefresh = false;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = inflater.inflate(R.layout.marker_details, null);
        long id = Long.valueOf(marker.getTitle());
        EntityDvo entity = findMainEntity(id);
        if (entity != null) {
            ImageView icon = (ImageView) view.findViewById(R.id.marker_details_icon);
            if (needToRefresh){
                needToRefresh = false;
                Picasso.with(view.getContext()).load(entity.getPhoto()).into(icon);
            } else {
                needToRefresh = true;
                Picasso.with(view.getContext()).load(entity.getPhoto()).into(icon, new InfoWindowRefresher(marker));
            }
            TextView text = (TextView) view.findViewById(R.id.marker_details_name);
            text.setText(entity.getName() + "#"+entity.getId());
        }
        return view;
    }

    public void setEntities(List<EntityDvo> entities) {
        this.entities.clear();
        this.entities.addAll(entities);
    }

    private EntityDvo findMainEntity(long id){
        for (EntityDvo place : entities){
            if (place.getId() == id){
                return place;
            }
        }
        return null;
    }
}
