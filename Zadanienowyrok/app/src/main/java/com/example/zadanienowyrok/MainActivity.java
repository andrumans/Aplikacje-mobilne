package com.example.zadanienowyrok;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date currentDate = new Date();
        String formattedCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);

        TextView currentdate = findViewById(R.id.Currentdate);
        currentdate.setTextColor(Color.WHITE);
        currentdate.setText(formattedCurrentDate);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newYear = LocalDateTime.of(now.getYear(), 12, 31, 23, 59, 59);
        Duration duration = Duration.between(now, newYear);

        TextView newYearTextView = findViewById(R.id.Newyear);
        newYearTextView.setTextColor(Color.WHITE);
        new CountDownTimer(duration.toMillis(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long days = millisUntilFinished / (1000 * 60 * 60 * 24);
                long hours = (millisUntilFinished % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long minutes = (millisUntilFinished % (1000 * 60 * 60)) / (1000 * 60);
                long seconds = (millisUntilFinished % (1000 * 60)) / 1000;
                newYearTextView.setText(String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds));
            }

            @Override
            public void onFinish() {
                newYearTextView.setText("HAPPY NEW YEAR!");
            }
        }.start();
    }
}
