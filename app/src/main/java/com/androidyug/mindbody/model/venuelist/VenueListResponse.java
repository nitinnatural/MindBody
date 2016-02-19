package com.androidyug.mindbody.model.venuelist;

import java.io.Serializable;

/**
 * Created by IAMONE on 2/18/2016.
 */
public class VenueListResponse  implements Serializable {

    private Meta meta;
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
