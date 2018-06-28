package com.example.rafael.weathertoday.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafael on 28/05/17.
 */

public class Forecast {

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @SerializedName("name")
    private String city;

    private transient String country;

    private transient Double temperature;
}
