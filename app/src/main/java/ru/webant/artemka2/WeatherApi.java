package ru.webant.artemka2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("current.json")
    Call<WeatherData> getCurrentWeather(
            @Query("key") String key,
            @Query("q") String q
    );
}