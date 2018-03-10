package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhaowenbo on 2018/3/8.
 */


public class MovieListActivity extends ActionBarActivity {

    private ListView mListView;
    private ArrayList<String> titles;
    private RequestQueue queue;

    String searchcontent;
    MyAdapter myAdapter;
    int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);
        page = 1;
        titles = new ArrayList<String>();
        Bundle bundle = getIntent().getExtras();
        searchcontent = bundle.getString("searchcontent");
      //  mListView = (ListView)findViewById(R.id.movie_search);

        queue = Volley.newRequestQueue(this);
        mListView = (ListView)findViewById(R.id.movie_search);
        retrieve();
    }


    public void retrieve(){
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", ""+page);
        params.put("sort","yearasc");
        params.put("title",searchcontent);
        connect(params);

    }

    public void gotoPre(View view){
        page--;
        if(page>0){
            Map<String, String> params = new HashMap<String, String>();
            params.put("page", ""+page);
            params.put("sort","yearasc");
            params.put("title",searchcontent);
            connect(params);

        }

    }

    public void gotoNext(View view){
        page++;
        Log.e("test", "hello");
        Map<String, String> params = new HashMap<String, String>();
        params.put("page", ""+page);
        params.put("sort","yearasc");
        params.put("title",searchcontent);

        connect(params);


    }

    public void connect(Map<String, String> params){
        final Map<String, String> parameters = params;
        // no user is logged in, so we must connect to the serve
        final Context context = this;
        String url = "http://18.219.78.246:8080/Movie_Website/movie";

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {

                            myAdapter = new MyAdapter(context,response);
                            mListView.setAdapter(myAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return parameters;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(postRequest);
    }

}



