package com.gmail.unmacaque.spring.android.google;

import java.io.Serializable;
import java.util.Collection;

public class GSearchResult implements Serializable {
    private GResponseData responseData;
    private int responseStatus;
    private Object responseDetails;

    public GResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(GResponseData responseData) {
        this.responseData = responseData;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Object getResponseDetails() {
        return responseDetails;
    }

    public void setResponseDetails(Object responseDetails) {
        this.responseDetails = responseDetails;
    }
}
