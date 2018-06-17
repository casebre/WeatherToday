package com.example.rafael.weathertoday.utils;

public class Utils {

    public static Double convertFahrenheitToCelsius(Double fahrenheit) {
        return ((fahrenheit - 32) / 1.8);
    }

    public static Double convertKelvinToCelsius(Double kelvin) {
        return kelvin - 273;
    }
}
