package com.example.prostaapkaokna;// TaskDetailActivity.java
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");

        TextView titleTextView = findViewById(R.id.text_view_title);
        TextView descriptionTextView = findViewById(R.id.text_view_description);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
    }
}
