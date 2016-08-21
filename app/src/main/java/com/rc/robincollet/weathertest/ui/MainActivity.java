package com.rc.robincollet.weathertest.ui;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.rc.robincollet.weathertest.R;
import com.rc.robincollet.weathertest.models.City;
import com.rc.robincollet.weathertest.models.WeatherModel;
import com.rc.robincollet.weathertest.network.WeatherApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private PagerAdapter pagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private static String TAG = "MainActivity";
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        Fragment firstFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(WeatherFragment.IS_LOCALIZED, true);
        firstFragment.setArguments(bundle);
        fragments.add(firstFragment);

        List<City> cities = City.listAll(City.class);
        if (cities != null) {
            for (int i = 0; i < cities.size(); i++) {
                if (cities.get(i).getName() == null) {
                    City.delete(cities.get(i));
                } else {
                    Fragment newFrag = new WeatherFragment();
                    Bundle newBundle = new Bundle();
                    newBundle.putBoolean(WeatherFragment.IS_LOCALIZED, false);
                    newBundle.putParcelable(WeatherFragment.CITY_PARCELABLE, cities.get(i));
                    newFrag.setArguments(newBundle);
                    fragments.add(newFrag);
                }
            }
        }

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                WeatherApi.getApi().getWeatherFromCity(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<WeatherModel>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: "+e.getMessage());
                            }

                            @Override
                            public void onNext(WeatherModel weatherModel) {
                                Fragment newFragment = new WeatherFragment();
                                Bundle bundle2 = new Bundle();
                                bundle2.putBoolean(WeatherFragment.IS_LOCALIZED, false);
                                bundle2.putParcelable(WeatherFragment.WEATHER_PARCELABLE, weatherModel);
                                newFragment.setArguments(bundle2);
                                fragments.add(newFragment);
                                pagerAdapter.notifyDataSetChanged();
                                new City(weatherModel.getName(), weatherModel.getCoord().getLat(), weatherModel.getCoord().getLon()).save();
                                viewPager.setCurrentItem(viewPager.getAdapter().getCount());
                            }
                        });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    public void deleteFragment(Fragment fragment){
        viewPager.setCurrentItem(0);
        fragments.remove(fragment);
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.notifyDataSetChanged();
    }
}
