package ru.webant.artemka2;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherData {

    @SerializedName("temp_f")
    public double currentTemperatureF;

    @SerializedName("wind_kph")
    public double windKph;
}