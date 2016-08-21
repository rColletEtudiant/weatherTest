package com.rc.robincollet.weathertest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by robincollet on 21/08/2016.
 */
public class City extends SugarRecord implements Parcelable {

    private String name;
    private Double lat;
    private Double lon;

    public City(){}
    public City(String name, Double lat, Double lon) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public City(Parcel parcel){
        this.name = parcel.readString();
        this.lat = parcel.readDouble();
        this.lon = parcel.readDouble();
    }


    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new City[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lon);
        parcel.writeDouble(lat);
        parcel.writeString(name);
    }
}

