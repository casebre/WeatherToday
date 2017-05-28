package com.example.rafael.weathertoday.asyntasks;

import android.os.AsyncTask;

import com.example.rafael.weathertoday.entity.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rafael on 28/05/17.
 */

public class GetForecast extends AsyncTask<String, Void, String> {

    private OnGetForecast listener;

    public interface OnGetForecast {
        public void onForecastResponse(Forecast forecast);
    }

    public GetForecast(OnGetForecast getForecastListener) {
        this.listener = getForecastListener;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection conn = null;
        try {
            String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=";
            String apiKey = "&appid=d8cbbd639936dab1a5c3b0cae7208ac8";
            URL url = new URL(openWeatherURL.concat(params[0]).concat(apiKey));
            conn = (HttpURLConnection) url.openConnection();


            conn.setUseCaches(false);
            conn.setAllowUserInteraction(true);
            conn.setConnectTimeout(12000000);
            conn.setReadTimeout(12000000);
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("appid", apiKey);
            //conn.addRequestProperty("Content-length", "1000");
            conn.connect();

            int status = conn.getResponseCode();
            if(status == 200) {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = buffer.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                buffer.close();
                conn.disconnect();
                return stringBuilder.toString();
            } else { return "Impossible to connect with the server."; }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(conn != null)
                conn.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        try {
            Forecast forecast = new Forecast();
            JSONObject jsonObject = new JSONObject(result);
            forecast.setTemperature(jsonObject.getJSONObject("main").getDouble("temp"));
            forecast.setCity(jsonObject.getString("name"));
            forecast.setCountry(jsonObject.getJSONObject("sys").getString("country"));
            listener.onForecastResponse(forecast);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
