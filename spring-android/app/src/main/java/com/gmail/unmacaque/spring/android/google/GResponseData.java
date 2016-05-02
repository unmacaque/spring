package com.gmail.unmacaque.spring.android.google;

import java.io.Serializable;
import java.util.Collection;

public class GResponseData implements Serializable {
    private Collection<GResult> results;
    private GSearchCursor cursor;

    public Collection<GResult> getResults() {
        return results;
    }

    public void setResults(Collection<GResult> results) {
        this.results = results;
    }

    public GSearchCursor getCursor() {
        return cursor;
    }

    public void setCursor(GSearchCursor cursor) {
        this.cursor = cursor;
    }
}
