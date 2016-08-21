package com.rc.robincollet.weathertest.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


public class Wind implements Parcelable{

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double deg;

    /**
     * 
     * @return
     *     The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Double getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Wind(Parcel parcel){
        this.speed = parcel.readDouble();
        this.deg = parcel.readDouble();
    }
    public Wind(){}

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Wind[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(speed);
        parcel.writeDouble(deg);
    }

}
