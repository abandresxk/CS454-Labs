package com.example.leviandres.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class fragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String TAG = " Fragment ";

    weatherInfo mWeatherInfo;   // ArrayList<Info> info_list holds list of weathers
    ArrayList<weatherInfo.Info> info;

    String[] titles;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.weather_fragment, container, false);
        MainActivity activity = (MainActivity) this.getActivity();
        info = activity.getWeather();
        if (info != null) {
            titles = new String[info.size()];
            Log.d(TAG, "info's size = " + info.size());
            for (int i = 0; i < info.size(); i++) {
                titles[i] = info.get(i).weather_list.get(0).main;
                Log.d(TAG, "added to titles array: " + titles[i]);
            }
        }

        Cursor c = activity.getCursor();
        c.moveToFirst();
        Log.d(TAG, "cursor count: " + c.getCount());
        ListView lv = (ListView) v.findViewById(R.id.listView);
        weatherAdapter wa = new weatherAdapter(activity, c, 0);
        lv.setAdapter(wa);
        return v;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//DO NOTHING, COULD ADD ANOTHER FRAGMENT TO SHOW MORE DETAILS
    }


}