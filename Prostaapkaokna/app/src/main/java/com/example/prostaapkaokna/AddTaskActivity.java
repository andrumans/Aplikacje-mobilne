package com.example.prostaapkaokna;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {
    private EditText titleEditText, descriptionEditText;
    private int position = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titleEditText = findViewById(R.id.edit_text_title);
        descriptionEditText = findViewById(R.id.edit_text_description);

        if (getIntent().hasExtra("title")) {
            titleEditText.setText(getIntent().getStringExtra("title"));
            descriptionEditText.setText(getIntent().getStringExtra("description"));
            position = getIntent().getIntExtra("position", -1);
        }

        Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(view -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("title", title);
            resultIntent.putExtra("description", description);
            if (position != -1) {
                resultIntent.putExtra("position", position);
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
