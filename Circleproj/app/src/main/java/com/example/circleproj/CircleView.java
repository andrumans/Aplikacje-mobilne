package com.example.circleproj;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CircleView extends View {
    private Bitmap image;
    private int x, y;
    private int dx = 5, dy = 5;
    private MediaPlayer mediaPlayer;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        image = BitmapFactory.decodeResource(getResources(), R.drawable.basket);
        mediaPlayer = MediaPlayer.create(context, R.raw.bounce);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Random random = new Random();
        x = random.nextInt(w - image.getWidth());
        y = random.nextInt(h - image.getHeight());
    }

    public void moveCircle() {
        x += dx;
        y += dy;
        if (x <= 0 || x >= getWidth() - image.getWidth()) {
            dx = -dx;
            playCollisionSound();
        }
        if (y <= 0 || y >= getHeight() - image.getHeight()) {
            dy = -dy;
            playCollisionSound();
        }
        invalidate();
    }

    private void playCollisionSound() {
        float volume = 1.0f;
        mediaPlayer.setVolume(volume, volume);
        mediaPlayer.start();
    }
}
