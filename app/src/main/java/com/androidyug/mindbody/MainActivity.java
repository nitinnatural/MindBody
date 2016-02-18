package com.androidyug.mindbody;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.androidyug.mindbody.data.FSApiImplementation;
import com.androidyug.mindbody.model.venuelist.Group;
import com.androidyug.mindbody.model.venuelist.Response;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

public class MainActivity extends AppCompatActivity {

    Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus = new Bus(ThreadEnforcer.ANY);

        FSApiImplementation fsApiImplementation = new FSApiImplementation(this, bus);
        fsApiImplementation.getVenueNearMe();
        fsApiImplementation.getVenueDetail();
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
    public void onResponse(Response response){
        Toast.makeText(MainActivity.this, ""+response.getGroups().size(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onResponseVenueDetail(Response response){
        Toast.makeText(MainActivity.this, ""+response.getGroups().size(), Toast.LENGTH_SHORT).show();
    }



}
