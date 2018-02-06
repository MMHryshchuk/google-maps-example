package com.mmhdev.devcv.core.android;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

/**
 */
public class BaseFragment extends Fragment {

    public void showProgress(){

    }

    public void hideProgress(){

    }


    protected void setupToolbar(Toolbar toolbar, String text, int icon, android.view.View.OnClickListener onNavigationClickListener){
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(text);
        toolbar.setNavigationIcon(icon);
        toolbar.setNavigationOnClickListener(onNavigationClickListener);
    }
}
