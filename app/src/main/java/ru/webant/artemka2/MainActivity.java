package ru.webant.artemka2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tvCurrentWindKph);
        TextView gorod = findViewById(R.id.cityName);

        Gson gson = new GsonBuilder()
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://api.weatherapi.com/v1/")
                .client(httpClient)
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        weatherApi.getCurrentWeather("00c2e9499a62410687f195525210208", "Rostov-on-Don")
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        if (response.body() == null) {
                            return;
                        }

                        WeatherData data = response.body();
                        Double windInKph = data.currentWeatherData.windKph;

                        textView.setText("Текущая скорость ветра в км/ч: " + String.valueOf(windInKph));
                        String cityName = data.weatherLocation.cityName;
                        gorod.setText("Твой ебаный город, блять," + cityName );
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        new Exception(t);
                    }
                });
    }
}