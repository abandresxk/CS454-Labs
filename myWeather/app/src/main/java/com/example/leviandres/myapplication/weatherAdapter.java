package com.example.leviandres.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class weatherAdapter extends CursorAdapter {
    public static final String TAG = " WeatherAdapter ";
    Context mContext;
    Cursor mCursor;
    LayoutInflater myLayoutInflater;
    public weatherAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mContext = context;
        mCursor = c;
        myLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = myLayoutInflater.inflate(R.layout.weather_row, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.text = (TextView)v.findViewById(R.id.text);
        holder.image = (ImageView)v.findViewById(R.id.image);
        v.setTag(holder);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder)view.getTag();
        String descript=cursor.getString(cursor.getColumnIndex(Constants.DESCRIPTION_KEY));
        String weatherType = cursor.getString(cursor.getColumnIndex(Constants.MAIN_KEY));
        String info = "Weather: " + descript;

        holder.text.setText(info);
        Bitmap bm = null;
        if (weatherType.equals("Rain")) {
            bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.rain);
        }else if (weatherType.equals("Cloud")) {
            bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.cloudy);
        }else {
            bm = BitmapFactory.decodeResource(view.getResources(), R.drawable.sunny);
        }
        holder.image.setImageBitmap(bm);
    }

    static class ViewHolder {
        TextView text;
        ImageView image;

    }
}