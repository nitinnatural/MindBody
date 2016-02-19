package com.androidyug.mindbody.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.androidyug.mindbody.model.venuedetail.VenueDetailResponse;
import com.androidyug.mindbody.model.venuelist.VenueListResponse;
import com.androidyug.mindbody.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import com.squareup.okhttp.Response;
import com.squareup.otto.Bus;
import java.io.IOException;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by IAMONE on 2/18/2016.
 */
public class FSApiImplementation {

    private static final String LOG_TAG = "FSApiImplementation";
    Retrofit client;
    Bus bus;
    Context mContext;
    ApiEndpointInterface foursquareService;


    public FSApiImplementation(Context ctx, Bus bus){

        this.mContext = ctx;
        this.bus = bus;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();



        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public com.squareup.okhttp.Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });


        client = new Retrofit.Builder()
                .baseUrl(Constant.API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // gson
                .build();

        foursquareService = client.create(ApiEndpointInterface.class);

    }


    //"12.92,77.59"
    public void getVenueNearMe(String latlong, int radius){

        Call<VenueListResponse> callForVenuList =  foursquareService.getFitnessCenter(latlong, radius, "gym / fitness center", Constant.CLIENT_ID, Constant.CLIENT_SECRET, Constant.API_VERSION);
        callForVenuList.enqueue(new Callback<VenueListResponse>() {
            @Override
            public void onResponse(retrofit.Response<VenueListResponse> response, Retrofit retrofit) {
                if (response.isSuccess()){
                    bus.post(response.body());
                } else {
                    Toast.makeText(mContext, "Fetching Error", Toast.LENGTH_LONG).show();
                }
                Log.d(LOG_TAG, ""+response.body().getResponse().getTotalResults());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }


    public void getVenueDetail(){
        Call<VenueDetailResponse> callForVenueDetail = foursquareService.getVenueDetail("537cce8e498e6816a1eca488", Constant.CLIENT_ID, Constant.CLIENT_SECRET, Constant.API_VERSION);
        callForVenueDetail.enqueue(new Callback<VenueDetailResponse>() {
            @Override
            public void onResponse(retrofit.Response<VenueDetailResponse> response, Retrofit retrofit) {
                Log.d(LOG_TAG, "response from venue list "+ response.raw().message());
                Log.d(LOG_TAG, "base url "+ client.baseUrl());
                Log.d(LOG_TAG, "" + response.body().getResponse().getVenue().getId());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "retrofit failure: " + t.getMessage());
            }
        });
    }




}
