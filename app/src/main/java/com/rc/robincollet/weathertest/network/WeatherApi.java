package com.rc.robincollet.weathertest.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.rc.robincollet.weathertest.BuildConfig;
import com.rc.robincollet.weathertest.models.WeatherModel;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by robincollet on 18/08/2016.
 */
public class WeatherApi {
    private static WeatherService githubUserAPI;

    public static WeatherService getApi() {
        if (githubUserAPI == null) {
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.WEATHER_URL)
                    .client(httpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            githubUserAPI = retrofit.create(WeatherService.class);
        }

        return githubUserAPI;
    }

    public interface WeatherService {
        @GET("/data/2.5/weather?&apikey="+BuildConfig.WEATHER_KEY)
        Observable<WeatherModel> getWeatherFromCity(@Query("q") String city);

        @GET("/data/2.5/weather?&apikey="+BuildConfig.WEATHER_KEY)
        Observable<WeatherModel> getWeatherFromLocation(@Query("lat") double lat, @Query("lon") double lon);
    }
}
