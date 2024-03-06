package com.example.apkapogoda;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    private ImageView weatherIcon;
    private Button srchbutton;

    private EditText inputcity;
    private TextView multiline;
    private Button clearbutton;
    private Layout layout;
    private String request(String miasto) throws Exception {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?units=metric&q=" + miasto + "&appid=a2016f1b0329283ed5aff4113ce59fa7");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
        }
        in.close();

        return response.toString();
    }
    public void iconfunction (String icon){
        String iconUrl = "https://openweathermap.org/img/wn/" + icon + ".png";
        weatherIcon = findViewById(R.id.iconweather);
        Picasso.get().load(iconUrl).into(weatherIcon);
    }

    private class Weather extends AsyncTask<String, Void, String> {  //Stworzyłem ta klasę bo nie można robić requestów w wątku głównym
        @Override
        protected String doInBackground(String... params) {
            try {
                return request(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONObject jsonResponse = new JSONObject(result);

                    JSONArray weatherArray = jsonResponse.getJSONArray("weather");
                    JSONObject weatherObject = weatherArray.getJSONObject(0);
                    String description = weatherObject.getString("description");
                    String icon = weatherObject.getString("icon");

                    JSONObject main = jsonResponse.getJSONObject("main");
                    double temperature = main.getDouble("temp");
                    double feelsLike = main.getDouble("feels_like");
                    double tempMin = main.getDouble("temp_min");
                    double tempMax = main.getDouble("temp_max");
                    int pressure = main.getInt("pressure");
                    int humidity = main.getInt("humidity");
                    int visibility = jsonResponse.getInt("visibility");

                    JSONObject wind = jsonResponse.getJSONObject("wind");
                    double windSpeed = wind.getDouble("speed");

                    JSONObject rain = jsonResponse.optJSONObject("rain");
                    String rainData = (rain != null) ? rain.toString() : "Brak deszczu";

                    JSONObject snow = jsonResponse.optJSONObject("snow");
                    String snowData = (snow != null) ? snow.toString() : "Brak śniegu";

                    JSONObject clouds = jsonResponse.getJSONObject("clouds");
                    int cloudiness = clouds.getInt("all");

                    String cityName = jsonResponse.getString("name");

                    StringBuilder weatherMessage = new StringBuilder();
                    weatherMessage.append("Miasto: ").append(cityName).append("\n");
                    weatherMessage.append("Pogoda: ").append(description).append("\n");
                    weatherMessage.append("Temperatura: ").append(temperature).append("°C\n");
                    weatherMessage.append("Odczuwalna temperatura: ").append(feelsLike).append("°C\n");
                    weatherMessage.append("Min. Temperatura: ").append(tempMin).append("°C\n");
                    weatherMessage.append("Max. Temperatura: ").append(tempMax).append("°C\n");
                    weatherMessage.append("Ciśnienie: ").append(pressure).append(" hPa\n");
                    weatherMessage.append("Wilgotność: ").append(humidity).append("%\n");
                    weatherMessage.append("Widoczność: ").append(visibility).append("%\n");
                    weatherMessage.append("Prędkość wiatru: ").append(windSpeed).append(" m/s\n");
                    weatherMessage.append("Deszcz: ").append(rainData).append("\n");
                    weatherMessage.append("Śnieg: ").append(snowData).append("\n");
                    weatherMessage.append("Zachmurzenie: ").append(cloudiness).append("%");

                    multiline = findViewById(R.id.multilinetext);
                    multiline.setText(weatherMessage.toString());

                    iconfunction(icon);
                    ObjectAnimator animator = new ObjectAnimator();
                    ImageView background = findViewById(R.id.background);
                    LocalTime currentTime = LocalTime.now();

                    switch (description) {
                        case "clear sky":
                            Drawable sunny = getDrawable(R.drawable.sunny);
                            background.setImageDrawable(sunny);
                            break;
                        case "few clouds":
                            if (currentTime.isAfter(LocalTime.of(22, 0)) || currentTime.isBefore(LocalTime.of(5, 0))) {
                                Drawable slightlycloudy = getDrawable(R.drawable.slightlycloudynight);
                                background.setImageDrawable(slightlycloudy);
                            } else {
                                Drawable fewclouds = getDrawable(R.drawable.slightlycloudy);
                                background.setImageDrawable(fewclouds);
                            }
                            break;
                        case "scattered clouds":
                        case "broken clouds":
                            Drawable cloudy = getDrawable(R.drawable.cloudy);
                            background.setImageDrawable(cloudy);
                            break;
                        case "light rain":
                        case "shower rain":
                        case "rain":
                            Drawable rainy = getDrawable(R.drawable.rainy);
                            background.setImageDrawable(rainy);
                            break;
                        case "thunderstorm":
                            Drawable storm = getDrawable(R.drawable.storm);
                            background.setImageDrawable(storm);
                            break;
                        case "snow":
                            Drawable snw = getDrawable(R.drawable.snow);
                            background.setImageDrawable(snw);
                            break;
                        case "mist":
                            Drawable mist = getDrawable(R.drawable.foggy);
                            background.setImageDrawable(mist);
                            break;
                    }

                    animator = ObjectAnimator.ofFloat(background, "alpha", 0f, 1f);
                    animator.setDuration(2000);
                    animator.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Wystąpił błąd w pobieraniu danych", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Request się nie wykonał", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearbutton = findViewById(R.id.exit);
        srchbutton = findViewById(R.id.searchbutton);
        inputcity = findViewById(R.id.inputcity);

        srchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = inputcity.getText().toString().trim();
                if (!city.isEmpty()) {
                    Weather weatherTask = new Weather();
                    weatherTask.execute(city);
                } else {
                    Toast.makeText(MainActivity.this, "Wpisz nazwę miasta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputcity.setText(" ");
                multiline.setText(" ");
                weatherIcon.setImageAlpha(0);
            }
        });

    }
}
