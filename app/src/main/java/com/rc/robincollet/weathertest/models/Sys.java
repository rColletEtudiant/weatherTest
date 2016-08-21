package com.rc.robincollet.weathertest.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


public class Sys implements Parcelable{

    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;

    /**
     * 
     * @return
     *     The type
     */
    public Integer getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The message
     */
    public Double getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The sunrise
     */
    public Integer getSunrise() {
        return sunrise;
    }

    /**
     * 
     * @param sunrise
     *     The sunrise
     */
    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * 
     * @return
     *     The sunset
     */
    public Integer getSunset() {
        return sunset;
    }

    /**
     * 
     * @param sunset
     *     The sunset
     */
    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public Sys(Parcel parcel){
        this.type = parcel.readInt();
        this.sunrise = parcel.readInt();
        this.sunset = parcel.readInt();
        this.message = parcel.readDouble();
        this.country = parcel.readString();
    }
    public Sys(){}

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Sys[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type);
        parcel.writeInt(sunrise);
        parcel.writeInt(sunset);
        parcel.writeDouble(message);
        parcel.writeString(country);
    }

}
