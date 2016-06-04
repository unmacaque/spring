package com.gmail.unmacaque.spring.android.google;

import java.io.Serializable;

public class GSearchCursor implements Serializable {
    private String resultCount;

    public String getResultCount() {
        return resultCount;
    }

    public void setResultCount(String resultCount) {
        this.resultCount = resultCount;
    }
}
