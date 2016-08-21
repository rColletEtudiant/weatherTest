package com.rc.robincollet.weathertest.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;


public class Main implements Parcelable{

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    /**
     * 
     * @return
     *     The temp
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * 
     * @param temp
     *     The temp
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * 
     * @return
     *     The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * 
     * @param pressure
     *     The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * 
     * @return
     *     The humidity
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * 
     * @param humidity
     *     The humidity
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * 
     * @return
     *     The tempMin
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     * 
     * @param tempMin
     *     The temp_min
     */
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    /**
     * 
     * @return
     *     The tempMax
     */
    public Double getTempMax() {
        return tempMax;
    }

    /**
     * 
     * @param tempMax
     *     The temp_max
     */
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }


    public Main(Parcel parcel){
        this.temp = parcel.readDouble();
        this.tempMax = parcel.readDouble();
        this.tempMin = parcel.readDouble();
        this.pressure = parcel.readDouble();
        this.humidity = parcel.readInt();
    }
    public Main(){}

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Object[] newArray(int i) {
            return new Main[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(humidity);
        parcel.writeDouble(temp);
        parcel.writeDouble(tempMax);
        parcel.writeDouble(tempMin);
        parcel.writeDouble(pressure);
    }

}
