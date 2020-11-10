package pt.ua.nextweather.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.Weather;
import pt.ua.nextweather.network.ForecastForACityResultsObserver;
import pt.ua.nextweather.network.IpmaWeatherClient;

public class FragmentWeather extends Fragment {
    IpmaWeatherClient client = new IpmaWeatherClient();
    public static final String CITY_CODE="citycode";
    ArrayList<HashMap<String,String>> weatherinfo =new ArrayList<>();
    String feedback="";
    TextView tv;
    int dayn =0;
    public FragmentWeather() {}
    public static FragmentWeather newInstance(int city_code) {
        FragmentWeather f = new FragmentWeather();
        Bundle arguments = new Bundle();
        arguments.putInt(CITY_CODE, city_code);
        f.setArguments(arguments);
        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmentwthr, container, false);
        tv = rootView.findViewById(R.id.tv0);
        Button prev = (Button) rootView.findViewById(R.id.prev);
        prev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(dayn >0)
                    printinfo(--dayn);
            }
        });
        Button next = (Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(dayn <4)
                    printinfo(++dayn);
            }
        });
        if (getArguments().containsKey(CITY_CODE)) {
            tv.setText(String.valueOf(this.getArguments().getInt(CITY_CODE)));
            getForecast(this.getArguments().getInt(CITY_CODE));
        }
        return rootView;
    }
    private void getForecast(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {
                for (Weather day : forecast) {
                    HashMap<String,String> dayMap =new HashMap<String,String>();
                    feedback += day.toString();
                    feedback += "\n";
                    dayMap.put("date",day.getForecastDate());
                    dayMap.put("rain",String.valueOf(day.getPrecipitaProb()));
                    dayMap.put("minT",String.valueOf(day.getTMin()));
                    dayMap.put("MaxT",String.valueOf(day.getTMax()));
                    dayMap.put("windD",String.valueOf(day.getPredWindDir()));
                    dayMap.put("windS",String.valueOf(day.getClassWindSpeed()));
                    weatherinfo.add(dayMap);
                }
                printinfo(0);
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback+= "Failed to get forecast for 5 days";
            }
        });
    }
    private void printinfo(int i){
        System.out.println(weatherinfo.toString());
        HashMap<String,String> dayMap =new HashMap<String,String>();
        dayMap= weatherinfo.get(i);
        TextView tvx;
        tvx=getView().findViewById(R.id.tv0);
        tvx.setText("Chance of Rain: " + dayMap.get("rain"));
        tvx=getView().findViewById(R.id.tv1);
        tvx.setText("Lowest Temperature: " + dayMap.get("minT"));
        tvx=getView().findViewById(R.id.tv2);
        tvx.setText("Highest Temperature: " + dayMap.get("MaxT"));
        tvx=getView().findViewById(R.id.tv3);
        tvx.setText("Wind Direction: " + dayMap.get("windD"));
        tvx=getView().findViewById(R.id.tv4);
        tvx.setText("Wind Speed: " + dayMap.get("windS"));
        tvx=getView().findViewById(R.id.tv5);
        tvx.setText("Date:" + dayMap.get("date"));
    }
    public void OnClickNext(View view){
        if(dayn <4)
            printinfo(++dayn);
    }
    public void OnClickPrev(View view){
        if(dayn >0)
            printinfo(--dayn);
    }
}