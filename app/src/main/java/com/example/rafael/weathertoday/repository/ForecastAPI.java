package com.example.rafael.weathertoday.repository;

import com.example.rafael.weathertoday.entity.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForecastAPI {
    @GET("data/2.5/weather?q={city}&appid=d8cbbd639936dab1a5c3b0cae7208ac8")
    Call<Forecast> getForecast(@Path("city") String city);
}
