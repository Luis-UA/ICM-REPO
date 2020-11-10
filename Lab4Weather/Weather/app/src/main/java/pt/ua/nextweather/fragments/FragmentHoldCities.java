package pt.ua.nextweather.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import pt.ua.nextweather.CityItemAdapter;
import pt.ua.nextweather.R;
import pt.ua.nextweather.datamodel.City;

public class FragmentHoldCities extends Fragment {
    public static final String CITIES="cities";
    private HashMap<String, City> cities;

    Context ctx;
    private RecyclerView rv;
    private CityItemAdapter adptr;

    public FragmentHoldCities() { /*Required empty public constructor*/}

    public static FragmentHoldCities newInstance(HashMap<String,City> cities) {
        FragmentHoldCities f = new FragmentHoldCities();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CITIES,cities);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ctx = container.getContext();
        Bundle b = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_holder, container, false);
        if(b.getSerializable(CITIES) != null) {
            cities = (HashMap<String, City>) b.getSerializable(CITIES);
            rv = (RecyclerView) view.findViewById(R.id.recycler1);
            adptr = new CityItemAdapter(ctx, cities);
            rv.setLayoutManager(new LinearLayoutManager(ctx));
            rv.setAdapter(adptr);
        }
        return view;
    }
}