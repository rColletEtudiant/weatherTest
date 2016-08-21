package com.rc.robincollet.weathertest;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orm.SugarContext;

import net.danlew.android.joda.JodaTimeAndroid;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by robincollet on 17/08/2016.
 */
public class ApplicationWeather extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        Stetho.initializeWithDefaults(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-RobotoRegular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        JodaTimeAndroid.init(this);
    }
}
