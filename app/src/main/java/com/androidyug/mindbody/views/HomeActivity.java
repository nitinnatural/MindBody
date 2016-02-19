package com.androidyug.mindbody.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidyug.mindbody.R;
import com.androidyug.mindbody.data.FSApiImplementation;
import com.androidyug.mindbody.model.venuelist.Item;
import com.androidyug.mindbody.model.venuelist.Response;
import com.androidyug.mindbody.model.venuelist.VenueListResponse;
import com.androidyug.mindbody.service.GPSTacker;
import com.androidyug.mindbody.utils.FontsFactory;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    public static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

    Bus bus;
    GPSTacker gpsTacker;

    @Bind(R.id.lv_venu_list)
    ListView lvVenuList;

    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        bus = new Bus(ThreadEnforcer.ANY);
        init();
        requestVenusList();
    }



    void init(){
        tvTitle.setTypeface(FontsFactory.robotoCondensedBold(this));
    }


    //"12.92,77.59"
    void  requestVenusList(){

        gpsTacker = new GPSTacker(this, this);

        if (true){ //gpsTacker.canGetLocation()

            double latitude = gpsTacker.getLatitude();
            double longitude = gpsTacker.getLongitude();
            String lat = String.valueOf(gpsTacker.getLatitude());
            String lon = String.valueOf(gpsTacker.getLongitude());
            String latlong = "12.92,77.59"; // lat + "," + lon;
            int radius = 3000; // 3km

            FSApiImplementation fsApiImplementation = new FSApiImplementation(this, bus);
            // hard coded latlong
            fsApiImplementation.getVenueNearMe(latlong, radius);

        } else {
            gpsTacker.showSettingsAlert();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    /////////////////////////////////// Testing Api ////////////////////////////////////////////

    @Subscribe
    public void onResponseVenuList(VenueListResponse response){
        List<Item> venueList = response.getResponse().getGroups().get(0).getItems();
        lvVenuList.setAdapter(new VenueAdapter(this,venueList));
        Toast.makeText(this, "" + response.getResponse().getTotalResults(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onResponseVenueDetail(Response response){
        Toast.makeText(this, ""+response.getGroups().size(), Toast.LENGTH_SHORT).show();
    }


}
