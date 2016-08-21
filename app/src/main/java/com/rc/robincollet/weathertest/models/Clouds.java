package com.rc.robincollet.weathertest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Clouds implements Parcelable {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     * 
     * @return
     *     The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     * 
     * @param all
     *     The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

    public Clouds(Parcel parcel){
        this.all = parcel.readInt();
    }
    public Clouds(){}

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Clouds createFromParcel(Parcel in) {
            return new Clouds(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Clouds[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(all);
    }
}
