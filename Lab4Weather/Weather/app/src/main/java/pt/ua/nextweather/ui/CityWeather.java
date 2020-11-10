package pt.ua.nextweather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.TextView;

import pt.ua.nextweather.R;
import pt.ua.nextweather.fragments.FragmentWeather;

public class CityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);
        String name  = getIntent().getStringExtra("cityname");
        int code= getIntent().getIntExtra("citycode",0);
        TextView city=findViewById(R.id.city1);
        city.setText(name);
        getForecast(code);
    }
    private void getForecast(int city_code) {
        FragmentWeather fragment = FragmentWeather.newInstance(city_code);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }
}