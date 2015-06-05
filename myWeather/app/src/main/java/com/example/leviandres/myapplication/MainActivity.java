package com.example.leviandres.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = " MainActivity ";
    ProgressBar pb;
    ArrayList<weatherInfo.Info> mInfo;
    DataBaseHelper mydb;
    TextView myText;
    TextView myC;
    String myCoutry;
    String myCity;
    GPSTracker gps;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(MainActivity.this);
        setContentView(R.layout.activity_main);
        myText=(TextView) findViewById(R.id.city);
        myC=(TextView) findViewById(R.id.country);

        pb = (ProgressBar)findViewById(R.id.progressBar);
        pb.setMax(100);
        mydb = new DataBaseHelper(this);
        if(gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            Toast.makeText(
                    getApplicationContext(),
                    "Your Location is -\nLat: " + latitude + "\nLong: "
                            + longitude, Toast.LENGTH_LONG).show();
        } else {
            gps.showSettingsAlert();
        }


        if (isOnline()) {
            pb.setProgress(75);
            LoadWeather task = new LoadWeather();
            task.execute();

        }else{
            pb.setVisibility(View.GONE);
            Toast.makeText(this, "Not Online", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // check for internet connection
    public boolean isOnline(){
        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (ni != null && ni.isConnected());
    }

    private class LoadWeather extends AsyncTask<String, Long, Long>{
        HttpURLConnection c;
        ArrayList<weatherInfo.Info> info;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if (aLong != 1l){
                setWeatherInfo(info);
                showList();
            }else{
                Toast.makeText(getApplicationContext(), "Background Task failed", Toast.LENGTH_LONG).show();
            }
            pb.setVisibility(View.GONE);
        }

        @Override
        protected Long doInBackground(String... params) {
            String api_str = "http://api.openweathermap.org/data/2.5/forecast/daily?lat="+String.valueOf((double)latitude)+"&lon="+String.valueOf((double)longitude)+"&cnt=10&mode=json";
            Log.i(TAG, "WeatherData=" + api_str);
            Log.i(TAG, "latttttt" + String.valueOf((double)longitude));
            try{
                URL data_url = new URL(api_str);
                c = (HttpURLConnection)data_url.openConnection();
                c.connect();
                int status = c.getResponseCode();

                // check for status
                if (status == 200) {  // OK
                    InputStream is = c.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String response_str;
                    StringBuilder sb = new StringBuilder();

                    while ((response_str = br.readLine()) != null){
                        sb = sb.append(response_str);
                    }
                    String weather_data = sb.toString();

                    Log.d(TAG, weather_data);
                    info = weatherInfo.getInfoList(weather_data);

                    myCity=weatherInfo.getCity(weather_data);
                    myCoutry=weatherInfo.getCountry(weather_data);

                    mydb.insertWeathers(weatherInfo.getSimpleWeatherList(weather_data));    // insert into db
                    return 0l;
                }else
                    return 1l;
            } catch (MalformedURLException e) {
                Log.e(TAG, "Malformed URL");
                e.printStackTrace();
                return 1l;
            } catch (IOException e) {
                e.printStackTrace();
                return 1l;
            } catch (JSONException e) {
                e.printStackTrace();
                return 1l;
            } finally {  // close the connection
                if (c != null)
                    c.disconnect();
            }
        }
    }
    public Cursor getCursor(){
        return mydb.getAll();
    }
    public ArrayList<weatherInfo.Info> getWeather(){
        return mInfo;
    }

    public void showList(){
        Log.i(TAG,"MY CITY IS:"+ myCity);
        myText.setText("City:"+myCity);
        Log.i(TAG,"MY Country IS:"+ myCoutry);
        myC.setText("Country:"+myCoutry);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, new fragment());
        ft.commit();

    }

    public void setWeatherInfo (ArrayList<weatherInfo.Info> info){
        this.mInfo = info;
    }

}