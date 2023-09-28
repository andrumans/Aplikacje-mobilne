    package com.example.lab03intencje;

    import androidx.appcompat.app.AppCompatActivity;

    import android.os.Bundle;
    import android.content.Intent;
    import android.net.Uri;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import java.net.URI;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button BtnNameSurname = findViewById(R.id.BtnNameSurname);
            Button Btnurl = findViewById(R.id.Btnurl);
            Button Btnlocal = findViewById(R.id.Btnlocal);;

            BtnNameSurname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Namesurnameactivity.class);
                    startActivity(intent);
                }
            });

            Btnurl.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText editText = findViewById(R.id.Urlinput);
                    String inputValue = editText.getText().toString();
                    Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse(inputValue));
                    startActivity(urlintent);
                }
            }));

            Btnlocal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent geointent = new Intent(MainActivity.this, Geoactivity.class);
                    startActivity(geointent);
                }
            });


        }
    }