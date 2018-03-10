package edu.uci.ics.fabflixmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaowenbo on 2018/3/8.
 */

public class MyAdapter extends BaseAdapter {

    JSONArray jsonarr;
    private Context mContext;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, String movies) throws JSONException {
        jsonarr = new JSONArray(movies);
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return jsonarr.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.list_item, viewGroup, false);
        TextView movietitle = (TextView) rowView.findViewById(R.id.movietitle);
        TextView movieid = (TextView) rowView.findViewById(R.id.movieid);
        TextView year = (TextView) rowView.findViewById(R.id.year);
        TextView director = (TextView) rowView.findViewById(R.id.director);
        TextView genres = (TextView) rowView.findViewById(R.id.genres);
        TextView stars = (TextView) rowView.findViewById(R.id.stars);
        TextView rate = (TextView) rowView.findViewById(R.id.rate);
        JSONObject jsonobj = null;
        try {
            jsonobj = jsonarr.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String movie_title =  jsonobj.optString("title");
        String movie_id =  jsonobj.optString("movieid");
        String movie_year =  jsonobj.optString("year");
        String movie_director =  jsonobj.optString("director");
        String movie_rate =  jsonobj.optString("rate");
        String movie_genres =  jsonobj.optString("genres");
        String movie_stars =  jsonobj.optString("stars");

        movietitle.setText("title:"+movie_title);
        movieid.setText("id:"+movie_id);
        year.setText("year:"+movie_year);
        director.setText("director:"+movie_director);
        genres.setText("genres:"+movie_genres);
        rate.setText("rate:"+movie_rate);
        stars.setText("stars"+movie_stars);

        return rowView;

    }
}
