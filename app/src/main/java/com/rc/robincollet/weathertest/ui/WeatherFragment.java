package com.rc.robincollet.weathertest.ui;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rc.robincollet.weathertest.R;
import com.rc.robincollet.weathertest.models.City;
import com.rc.robincollet.weathertest.models.WeatherModel;
import com.rc.robincollet.weathertest.network.WeatherApi;
import com.rc.robincollet.weathertest.tools.DateTools;
import com.rc.robincollet.weathertest.tools.LocationTools;
import com.rc.robincollet.weathertest.tools.WeatherTools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WeatherFragment extends Fragment {

    public static final String IS_LOCALIZED = "is_localized";
    public static final String CITY_PARCELABLE = "city_parcelable";
    public static final String WEATHER_PARCELABLE = "weather_parcelable";
    public static String TAG = "WeatherFragment";

    @BindView(R.id.progress_layout)
    RelativeLayout progressLayout;
    @BindView(R.id.main_layout)
    RelativeLayout mainLayout;
    @BindView(R.id.loc_icon)
    ImageView locIcon;
    @BindView(R.id.weather_image)
    ImageView weatherImage;
    @BindView(R.id.temp_desc)
    TextView weatherDesc;
    @BindView(R.id.humid)
    TextView humidity;
    @BindView(R.id.high_temp)
    TextView highestTemp;
    @BindView(R.id.low_temp)
    TextView lowestTemp;
    @BindView(R.id.temp)
    TextView temperature;
    @BindView(R.id.city_time)
    TextView cityTime;
    @BindView(R.id.delete)
    Button deleteButton;

    private Unbinder unbinder;
    private City city;

    public WeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.getBoolean(IS_LOCALIZED)){
            Location location = LocationTools.getLastKnownLoaction(true, getActivity());
            if (location != null) {
                locIcon.setVisibility(View.VISIBLE);
                deleteButton.setVisibility(View.GONE);
                loadWeatherDataFromLoc(location.getLatitude(), location.getLongitude());
            } else {
                Toast.makeText(getActivity(), "Location permissions not granted", Toast.LENGTH_SHORT).show();
            }
        } else if (bundle != null && bundle.getParcelable(WEATHER_PARCELABLE) != null) {
            WeatherModel weatherModel = bundle.getParcelable(WEATHER_PARCELABLE);
            displayWeatherData(weatherModel);
        } else if (bundle != null && bundle.getParcelable(CITY_PARCELABLE) != null) {
            loadWeatherDataFromLoc(city.getLat(), city.getLon());
        }
        return view;
    }

    private void loadWeatherDataFromLoc(double lat, double lon) {
        WeatherApi.getApi().getWeatherFromLocation(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<WeatherModel>() {
                @Override
                public void onCompleted() {}
                @Override
                public void onError(Throwable e) {
                    Toast.makeText(getActivity(), "failure response : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(WeatherModel weatherModel) {
                    displayWeatherData(weatherModel);
                }
            });
    }

    private void displayWeatherData(WeatherModel weatherModel) {
        city = new City(weatherModel.getName(), weatherModel.getCoord().getLat(), weatherModel.getCoord().getLon());
        double temp = WeatherTools.kelvinToCelcius(weatherModel.getMain().getTemp());
        double hTemp = WeatherTools.kelvinToCelcius(weatherModel.getMain().getTempMax());
        double lTemp = WeatherTools.kelvinToCelcius(weatherModel.getMain().getTempMin());
        String date = DateTools.getDateFormat("dd/MM/yyyy HH:mm", DateTools.getDateTimeFromInt(weatherModel.getDt().toString()));
        temperature.setText(String.format(getString(R.string.temp_celcius), temp));
        highestTemp.setText(String.format(getString(R.string.temp_celcius), hTemp));
        lowestTemp.setText(String.format(getString(R.string.temp_celcius), lTemp));
        weatherDesc.setText(weatherModel.getWeather().get(0).getDescription());
        humidity.setText(String.format(getString(R.string.humidiy_percent), weatherModel.getMain().getHumidity()));
        cityTime.setText(String.format(getString(R.string.city_time), weatherModel.getName(), date));
        weatherImage.setImageDrawable(getResources().getDrawable(WeatherTools.getWeatherImage(weatherModel.getWeather().get(0).getMain())));
        progressLayout.setVisibility(View.INVISIBLE);
        mainLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.delete)
    void delete(){
        ((MainActivity)getActivity()).deleteFragment(this);
        City.deleteAll(City.class, "NAME = ?", city.getName());
    }
}
