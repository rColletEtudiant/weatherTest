package com.rc.robincollet.weathertest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Coord implements Parcelable{

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    /**
     * 
     * @return
     *     The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 
     * @param lon
     *     The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Coord(Parcel parcel){
        this.lon = parcel.readDouble();
        this.lat = parcel.readDouble();
    }
    public Coord(){}

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Coord[i];
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
    }
}
