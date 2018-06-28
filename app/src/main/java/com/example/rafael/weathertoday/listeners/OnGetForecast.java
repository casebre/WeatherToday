package com.example.rafael.weathertoday.listeners;

import com.example.rafael.weathertoday.entity.Forecast;

public interface OnGetForecast {
    public void onForecastResponse(Forecast forecast);
}
