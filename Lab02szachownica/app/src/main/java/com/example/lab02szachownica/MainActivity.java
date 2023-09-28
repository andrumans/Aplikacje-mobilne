package com.example.lab02szachownica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Panel p;
    Paint tlo_pola = new Paint();
    Paint tlo_pola2 = new Paint();

    private static final int MENU_ITEM_1 = R.id.firstcolor;
    private static final int MENU_ITEM_2 = R.id.secondcolor;
    private static final int MENU_ITEM_auth = R.id.author;
    private static final int MENU_ITEM_exit = R.id.exit;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tlo1", tlo_pola.getColor());
        outState.putInt("tlo2", tlo_pola2.getColor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        p = new Panel(this);
        setContentView(p);

        if(savedInstanceState !=null){
            tlo_pola.setColor(savedInstanceState.getInt("tlo1", tlo_pola.getColor()));
            tlo_pola2.setColor(savedInstanceState.getInt("tlo2", tlo_pola2.getColor()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == MENU_ITEM_auth) {
            Context context = getApplicationContext();
            CharSequence text = "Twórca: Jakub Czarnak";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.BOTTOM | Gravity.BOTTOM, 0, 0);
            toast.show();
            return true;
        } else if (item.getItemId() == MENU_ITEM_exit) {
            finish();
            return true;
        } else if (item.getItemId() == MENU_ITEM_1) {
            tlo_pola.setColor(Color.BLACK);
            tlo_pola2.setColor(Color.WHITE);
            p.postInvalidate();
            return true;
        } else if (item.getItemId() == MENU_ITEM_2) {
            tlo_pola.setColor(Color.RED);
            tlo_pola2.setColor(Color.YELLOW);
            p.postInvalidate();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    class Panel extends View {
        private float rozm;

        public Panel(Context context) {
            super(context);
            tlo_pola.setColor(Color.WHITE);
            tlo_pola2.setColor(Color.BLACK);
        }

        //Obliczamy rozmiar kratki na podstawie min. szerokości ekranu oraz jego wysokości
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            int minSize = Math.min(w, h);
            rozm = (float) minSize / 8.0f;
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawColor(Color.GRAY);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        canvas.drawRect(i * rozm, j * rozm, (i + 1) * rozm, (j + 1) * rozm, tlo_pola);
                    } else {
                        canvas.drawRect(i * rozm, j * rozm, (i + 1) * rozm, (j + 1) * rozm, tlo_pola2);
                    }
                }
            }
        }
    }
}
