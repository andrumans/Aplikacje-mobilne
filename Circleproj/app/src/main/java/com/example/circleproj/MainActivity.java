package com.example.circleproj;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private CircleView circleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleView = findViewById(R.id.circleView);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                circleView.moveCircle();
                handler.postDelayed(this, 30);
            }
        });
    }
}
