package com.androidyug.mindbody.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidyug.mindbody.model.venuelist.Group;
import com.androidyug.mindbody.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
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


    public FSApiImplementation(Context ctx, Bus bus){

        this.mContext = ctx;
        this.bus = bus;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();



        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        });


        client = new Retrofit.Builder()
                .baseUrl(Constant.API_ENDPOINT)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }


    public void getVenueNearMe(){
        ApiEndpointInterface foursquareService = client.create(ApiEndpointInterface.class);
        Call<com.androidyug.mindbody.model.venuelist.Response> callFitnessCenter =  foursquareService.getFitnessCenter("12.95,72.59", 600, "gym / fitness", Constant.CLIENT_ID, Constant.CLIENT_SECRET, Constant.API_VERSION);
        callFitnessCenter.enqueue(new Callback<com.androidyug.mindbody.model.venuelist.Response>() {
            @Override
            public void onResponse(retrofit.Response<com.androidyug.mindbody.model.venuelist.Response> response, Retrofit retrofit) {
                Log.d(LOG_TAG, "response from venue list "+ response.raw().message());
                Log.d(LOG_TAG, "base url "+ client.baseUrl());
//                bus.post(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(LOG_TAG, "retrofit failure: " + t.getMessage());
            }
        });

    }




}
