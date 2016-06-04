package com.gmail.unmacaque.spring.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.gmail.unmacaque.spring.android.google.GResult;
import com.gmail.unmacaque.spring.android.google.GSearchResult;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultFragment extends ListFragment {
    private static final String ARG_GSEARCHRESULT = "gsearchresult";

    public static ResultFragment newInstance(GSearchResult gSearchResult) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GSEARCHRESULT, gSearchResult);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            GSearchResult gSearchResult = (GSearchResult) getArguments().get(ARG_GSEARCHRESULT);
            List<Map<String,String>> searchResults = new ArrayList<>();

            for (GResult gResult : gSearchResult.getResponseData().getResults()) {
                HashMap<String, String> result = new HashMap<>();
                result.put("titleNoFormatting", gResult.getTitleNoFormatting());
                result.put("visibleUrl", gResult.getVisibleUrl());
                searchResults.add(result);
            }

            SimpleAdapter adapter = new SimpleAdapter(
                    getActivity(),
                    searchResults,
                    android.R.layout.simple_list_item_2,
                    new String[]{"titleNoFormatting", "visibleUrl"},
                    new int[]{android.R.id.text1, android.R.id.text2}
            );

            getListView().setAdapter(adapter);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }
}
