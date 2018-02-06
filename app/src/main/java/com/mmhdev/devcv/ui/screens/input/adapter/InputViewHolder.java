package com.mmhdev.devcv.ui.screens.input.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mmhdev.devcv.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by on 05.12.16.
 */

public class InputViewHolder  extends RecyclerView.ViewHolder{

    @BindView(R.id.input_item_place)
    public TextView vPrimaryText;


    public InputViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(){

    }
}
