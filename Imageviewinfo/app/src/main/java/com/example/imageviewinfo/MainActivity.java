package com.example.imageviewinfo;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView obrazek;
    TextView imgsize;
    Button loadimg, invertimg;
    Bitmap bitmap, invrtbitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obrazek = findViewById(R.id.obraz);
        imgsize = findViewById(R.id.textView);
        loadimg = findViewById(R.id.button);
        invertimg = findViewById(R.id.button2);

        loadimg.setOnClickListener(v -> {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);

            if ( bitmap!= null) {
                obrazek.setImageBitmap(bitmap);
                int szerokość = bitmap.getWidth();
                int wysokość = bitmap.getHeight();
                imgsize.setText("Rozmiar obrazka: " + szerokość + " x " + wysokość + " pikseli");
            } else {
                Toast.makeText(MainActivity.this, "Nie udało się wczytać obrazka", Toast.LENGTH_SHORT).show();
            }
        });

        invertimg.setOnClickListener(v -> {
            if (bitmap != null) {
                invrtbitmap = getInvrtbitmap(bitmap);
                obrazek.setImageBitmap(invrtbitmap);
            } else {
                Toast.makeText(MainActivity.this, "Najpierw wczytaj obrazek", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Bitmap getInvrtbitmap(Bitmap bitmapa) {
        Bitmap invertedAndShiftedBitmap = Bitmap.createBitmap(bitmapa.getWidth(), bitmapa.getHeight(), bitmapa.getConfig());
        Random random = new Random();

        for (int y = 0; y < bitmapa.getHeight(); y++) {
            for (int x = 0; x < bitmapa.getWidth(); x++) {

                int shift = random.nextInt(9) - 4;  // przesuniecie w odpowiednim zakresie zeby nie bylo szumnu na zdj
                int newX = x + shift;

                if (newX < 0) newX = 0;
                if (newX >= bitmapa.getWidth()) newX = bitmapa.getWidth() - 1;

                int pixelColor = bitmapa.getPixel(newX, y);

                int red = Color.red(pixelColor);
                int green = Color.green(pixelColor);
                int blue = Color.blue(pixelColor);

                int invrtred = 255 - red;
                int invrtgreen = 255 - green;
                int invrtblue = 255 - blue;

                int invertedColor = Color.rgb(invrtred, invrtgreen, invrtblue);

                invertedAndShiftedBitmap.setPixel(x, y, invertedColor);
            }
        }

        return invertedAndShiftedBitmap;
    }

}
