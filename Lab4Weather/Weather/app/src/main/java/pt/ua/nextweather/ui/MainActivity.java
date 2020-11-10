package pt.ua.nextweather.ui;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import java.util.HashMap;

import pt.ua.nextweather.fragments.FragmentHoldCities;
import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;
import pt.ua.nextweather.datamodel.WeatherType;
import pt.ua.nextweather.network.CityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;

public class MainActivity extends AppCompatActivity {
    private String feedback="";
    IpmaWeatherClient client = new IpmaWeatherClient();
    private HashMap<String, City> cities;
    private HashMap<Integer, WeatherType> weatherDescriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadCities();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadCities();
            }
        });
    }
    public void LoadCities(){
        client.retrieveCitiesList(new CityResultsObserver() {
            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                MainActivity.this.cities = citiesCollection;
                if(cities.size()!=0){
                    FragmentHoldCities fragment = FragmentHoldCities.newInstance(cities);
                    displayFragment(fragment);
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback+="Failed to get cities list!";
            }
        });
    }
    public void displayFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }
}
