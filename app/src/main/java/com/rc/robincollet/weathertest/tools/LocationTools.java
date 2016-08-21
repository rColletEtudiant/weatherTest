package com.rc.robincollet.weathertest.tools;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by robincollet on 18/08/2016.
 */
public class LocationTools {

    public static Location getLastKnownLoaction(boolean enabledProvidersOnly, Context context) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location utilLocation;
        List<String> providers = manager.getProviders(enabledProvidersOnly);
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
            utilLocation = manager.getLastKnownLocation(provider);
            if(utilLocation != null) return utilLocation;
        }
        return null;
    }
}
