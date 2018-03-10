package edu.uci.ics.fabflixmobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaowenbo on 2018/3/8.
 */

public class SearchActivity extends ActionBarActivity{


    private EditText moviesearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        moviesearch = (EditText) findViewById(R.id.moviesearch);
    }

    public void search(View view) {
        Intent goToIntent = new Intent(this, MovieListActivity.class);
        goToIntent.putExtra("searchcontent",moviesearch.getText().toString());
        startActivity(goToIntent);
    }


}
