package com.androidyug.mindbody.data;


import com.androidyug.mindbody.model.venuedetail.VenueDetailResponse;
import com.androidyug.mindbody.model.venuelist.Response;
import com.androidyug.mindbody.model.venuelist.VenueListResponse;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by IAMONE on 12/23/2015.
 */
public interface ApiEndpointInterface {

    //https://api.foursquare.com/v2/venues/explore?v=20131016&ll=12.92,77.59&radius=5000&query=gym%20/%20fitness%20center&client_id=EUO32AAGERMKAY0ZOLUUYCIAKA0FCI3R32UKD114I0D5SY2C&client_secret=WHFLGJKN35KIUNRE42R45Z5WVSBBHE5C2DS20YZPQ0GCLOLT&v=20131016

    @GET("explore")
    Call<VenueListResponse> getFitnessCenter(@Query("ll") String latlong, @Query("radius") int radiusMeter, @Query("query") String query, @Query("client_id") String clientId, @Query("client_secret") String clinetSecret, @Query("v") String version);

    //https://api.foursquare.com/v2/venues/537cce8e498e6816a1eca488?client_id=EUO32AAGERMKAY0ZOLUUYCIAKA0FCI3R32UKD114I0D5SY2C&client_secret=WHFLGJKN35KIUNRE42R45Z5WVSBBHE5C2DS20YZPQ0GCLOLT&v=20131016

    @GET("{venueId}")
    Call<VenueDetailResponse> getVenueDetail( @Path("venueId") String venueId, @Query("client_id") String clientId, @Query("client_secret") String clientSecret, @Query("v") String version);

}
