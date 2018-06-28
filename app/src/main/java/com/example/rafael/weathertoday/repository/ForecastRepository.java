package com.example.rafael.weathertoday.repository;

import com.example.rafael.weathertoday.entity.Forecast;
import com.example.rafael.weathertoday.listeners.OnGetForecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastRepository {
    private ForecastWebservice webservice = new ForecastWebservice();

    public Forecast getForecast(String city, final OnGetForecast listener) {

        Forecast forecast = new Forecast();
        webservice.getAPI().getForecast(city).enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                listener.onForecastResponse(forecast);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {

            }
        });

        return forecast;
    }
}
