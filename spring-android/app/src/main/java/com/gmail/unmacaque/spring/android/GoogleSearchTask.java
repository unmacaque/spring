package com.gmail.unmacaque.spring.android;

import android.os.AsyncTask;

import com.gmail.unmacaque.spring.android.google.GResponseData;
import com.gmail.unmacaque.spring.android.google.GSearchResult;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class GoogleSearchTask extends AsyncTask<String, Void, GSearchResult> {

    public static final String GOOGLE_SEARCH_API = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q={query}";

    private final GoogleSearchCallback mSearchResultCallback;

    public interface GoogleSearchCallback {
        void onGoogleSearchResult(GSearchResult gSearchResult);
    }

    public GoogleSearchTask(GoogleSearchCallback mSearchResultCallback) {
        this.mSearchResultCallback = mSearchResultCallback;
    }

    @Override
    protected GSearchResult doInBackground(String... keywords) {
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(new MediaType("text", "javascript")));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.<HttpMessageConverter<?>>singletonList(gsonHttpMessageConverter));
        return restTemplate.getForObject(GOOGLE_SEARCH_API, GSearchResult.class, keywords);
    }

    @Override
    protected void onPostExecute(GSearchResult gSearchResult) {
        mSearchResultCallback.onGoogleSearchResult(gSearchResult);
    }
}
