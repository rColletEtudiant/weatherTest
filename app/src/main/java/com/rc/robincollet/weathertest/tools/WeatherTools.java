package com.rc.robincollet.weathertest.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.rc.robincollet.weathertest.R;

import java.util.List;

/**
 * Created by robincollet on 17/08/2016.
 */
public class WeatherTools {

    public static double kelvinToCelcius(double temp) {
        return (temp - 273.0);
    }

    public static int getWeatherImage(String weatherType){
        switch (weatherType) {
            case "Rain":
                return R.drawable.rain;
            case "Clouds":
                return R.drawable.cloud;
            case "Sun":
                return R.drawable.sun;
            case "Clear":
                return R.drawable.sun;
            case "Snow":
                return R.drawable.hail;
            case "Thunderstorm":
                return R.drawable.storm;
            case "Drizzle":
                return R.drawable.raindrop;
            case "Atmosphere":
                return R.drawable.mist;
            default:
                return R.drawable.hail;
        }
    }
}
