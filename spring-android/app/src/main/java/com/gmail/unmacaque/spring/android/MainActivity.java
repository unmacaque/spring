package com.gmail.unmacaque.spring.android;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.unmacaque.spring.android.google.GSearchResult;

import org.springframework.http.HttpStatus;

public class MainActivity extends AppCompatActivity implements GoogleSearchTask.GoogleSearchCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fragment fragment = new SearchFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    public void onLookupButtonClick(View view) {
        EditText keywordEditText = (EditText) findViewById(R.id.editText);
        String keyword = keywordEditText.getText().toString();

        new GoogleSearchTask(this).execute(keyword);
    }

    @Override
    public void onGoogleSearchResult(GSearchResult gSearchResult) {
        if (gSearchResult.getResponseStatus() == HttpStatus.OK.value()) {
            Fragment fragment = ResultFragment.newInstance(gSearchResult);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .addToBackStack(null)
                    .commit();
        } else {
            Toast.makeText(this, R.string.popup_no_results, Toast.LENGTH_SHORT).show();
        }
    }
}
