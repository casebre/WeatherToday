package com.example.rafael.weathertoday.repository;

import retrofit2.Retrofit;

public class ForecastWebservice {
    private Retrofit retrofit = null;


    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public ForecastAPI getAPI() {
        String BASE_URL = "http://api.openweathermap.org/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }

        return retrofit.create(ForecastAPI.class);
    }
}
