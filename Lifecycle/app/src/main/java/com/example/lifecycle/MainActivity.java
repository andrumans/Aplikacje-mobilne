package com.example.lifecycle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends Activity {

    private ConstraintLayout myScreenLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();

        Button buttonexit = findViewById(R.id.buttonexit);

        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myScreenLayout = findViewById(R.id.myScreenLayout);
        EditText editText = findViewById(R.id.editTextText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setBackgroundColor(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setBackgroundColor(String chosenColor) {
        if (chosenColor.toLowerCase().contains("red"))
            myScreenLayout.setBackgroundColor(Color.RED);
        else if (chosenColor.toLowerCase().contains("green"))
            myScreenLayout.setBackgroundColor(Color.GREEN);
        else if (chosenColor.toLowerCase().contains("blue"))
            myScreenLayout.setBackgroundColor(Color.BLUE);
        else if (chosenColor.toLowerCase().contains("white"))
            myScreenLayout.setBackgroundColor(Color.WHITE);
    }
    @Override
    protected void onStart() {
        Toast.makeText(this, "onStart", Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Toast.makeText(this, "onSaveInstanceState", Toast.LENGTH_LONG).show();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "onRestart", Toast.LENGTH_LONG).show();
        super.onRestart();
    }
}
