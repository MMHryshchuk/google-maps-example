package com.mmhdev.devcv.ui.screens.input;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.model.EncodedPolyline;
import com.mmhdev.devcv.R;
import com.mmhdev.devcv.core.android.BaseActivity;
import com.mmhdev.devcv.ui.screens.direction.DirectionActivity;
import com.mmhdev.devcv.ui.screens.input.adapter.InputAdapter;
import com.mmhdev.devcv.ui.screens.input.adapter.RecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by on 05.12.16.
 */

public class InputActivity extends BaseActivity implements GoogleApiClient.ConnectionCallbacks
                                ,GoogleApiClient.OnConnectionFailedListener
                                ,View.OnClickListener{

    public static final int RESULT_START_POINT = 12012;
    public static final int RESULT_END_POINT = 19912;
    public static final int RESULT_MY_POS = 231;
    public static final int RESULT_ALL_FINE = 6691;

    public static final String EXTRA_LAT = "EXTRA_LAT";
    public static final String EXTRA_LNG = "EXTRA_LNG";
    public static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";

    private static final String EXTRA_MY_POS = "EXTRA_MY_POS";






    @BindView(R.id.input_activity_search_ed_tx)
    EditText vSearch;
    @BindView(R.id.input_activity_recycler)
    RecyclerView vRecyclerView;
    @BindView(R.id.input_activity_my_pos)
    LinearLayout vMyPos;

    private InputAdapter mAdapter;
    protected GoogleApiClient mApiClient;
    private static final LatLngBounds BOUNDS_CHERNIVTSI = new LatLngBounds(
            new LatLng(48.209330, 26.103291),
            new LatLng(48.344019, 25.839630)

    );


    public static void start(Activity activity, int request,boolean start){
        Intent intent = new Intent(activity, InputActivity.class);
        intent.putExtra(EXTRA_MY_POS,start);

        activity.startActivityForResult(intent, request);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_activity);
        ButterKnife.bind(this);
        buildGoogleApiClient();
        initTextWatcher();
        vMyPos.setVisibility(getIntent().getBooleanExtra(EXTRA_MY_POS,false) ? View.VISIBLE : View.GONE);
        initRecycler();

    }



    @Override
    protected void onResume() {
        super.onResume();
        if (!mApiClient.isConnected() && !mApiClient.isConnecting()){
            Log.v("Google API","Connecting");
            mApiClient.connect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mApiClient.isConnected()){
            Log.v("Google API","Dis-Connecting");
            mApiClient.disconnect();
        }
    }

    private void initRecycler(){
        mAdapter = new InputAdapter(this, R.layout.input_item,
                mApiClient, BOUNDS_CHERNIVTSI, null);
        vRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        vRecyclerView.setAdapter(mAdapter);
        vRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, (view, position) -> {
                    final com.mmhdev.devcv.ui.dvo.PlaceAutocomplete item = mAdapter.getItem(position);
                    final String placeId = String.valueOf(item.placeId);
                    Log.i("TAG", "Autocomplete item selected: " + item.description);
                    /*
                         Issue a request to the Places Geo Data API to retrieve a Place object with additional details about the place.
                     */

                    PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                            .getPlaceById(mApiClient, placeId);
                    placeResult.setResultCallback(places -> {
                        if(places.getCount()==1){
                            Intent intent = new Intent();
                            intent.putExtra(EXTRA_LAT,places.get(0).getLatLng().latitude);
                            intent.putExtra(EXTRA_LNG,places.get(0).getLatLng().longitude);
                            intent.putExtra(EXTRA_ADDRESS,places.get(0).getName());
                            setResult(RESULT_ALL_FINE,intent);
                            finish();
                            Toast.makeText(getApplicationContext(),String.valueOf(places.get(0).getLatLng()),Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),Constants.SOMETHING_WENT_WRONG,Toast.LENGTH_SHORT).show();
                        }
                    });
                    Log.i("TAG", "Clicked: " + item.description);
                    Log.i("TAG", "Called getPlaceById to get Place details for " + item.placeId);
                })
        );
    }

    private void initTextWatcher(){
        vSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (!s.toString().equals("") && mApiClient.isConnected()) {
                    mAdapter.getFilter().filter(s.toString());
                }else if(!mApiClient.isConnected()){
                    Toast.makeText(getApplicationContext(), Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
                    Log.e(Constants.PlacesTag,Constants.API_NOT_CONNECTED);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.input_activity_my_pos)
    public void onMyPosClick(){
        setResult(RESULT_MY_POS);
        finish();
    }

    protected synchronized void buildGoogleApiClient() {
        mApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v("Google API Callback", "Connection Done");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v("Google API Callback", "Connection Suspended");
        Log.v("Code", String.valueOf(i));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("Google API Callback","Connection Failed");
        Log.v("Error Code", String.valueOf(connectionResult.getErrorCode()));
        Toast.makeText(this, Constants.API_NOT_CONNECTED,Toast.LENGTH_SHORT).show();
    }
}
