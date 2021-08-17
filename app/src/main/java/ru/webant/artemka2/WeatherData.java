package ru.webant.artemka2;


import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("current")
    public CurrentWeatherData currentWeatherData;

    @SerializedName("location")
    public WeatherLocation weatherLocation;
}