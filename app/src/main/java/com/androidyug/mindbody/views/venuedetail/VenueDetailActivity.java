package com.androidyug.mindbody.views.venuedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidyug.mindbody.R;

import butterknife.ButterKnife;

public class VenueDetailActivity extends AppCompatActivity {

    public static String EXTRA_INTENT_VENUE_DETAIL = "extra_intent_venue_detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        ButterKnife.bind(this);
    }
}
