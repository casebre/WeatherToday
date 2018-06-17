package com.example.rafael.weathertoday.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rafael.weathertoday.R;
import com.example.rafael.weathertoday.asyntasks.GetForecast;
import com.example.rafael.weathertoday.entity.Forecast;
import com.example.rafael.weathertoday.utils.Utils;


public class ForecastFragment extends Fragment {

    private static final String ARG_PARAM1 = "city";
    private static final String ARG_PARAM2 = "param2";

    private String city;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GetForecast.OnGetForecast onGetForecastListener;

    public ForecastFragment() {
        // Required empty public constructor
    }


    public static ForecastFragment newInstance(String city, String param2) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, city);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        final TextView txtCity = (TextView) view.findViewById(R.id.text_city);
        final TextView txtCountry = (TextView) view.findViewById(R.id.text_country);
        final TextView txtTemperature = (TextView) view.findViewById(R.id.text_temp);

        onGetForecastListener = new GetForecast.OnGetForecast() {
            @Override
            public void onForecastResponse(Forecast forecast) {
                txtCity.setText(forecast.getCity());
                txtCountry.setText(forecast.getCountry());
                txtTemperature.setText(String.valueOf(Utils.convertKelvinToCelsius(forecast.getTemperature())));
            }
        };

        new GetForecast(onGetForecastListener).execute(city);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
