package com.example.prostaapkaokna;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int ADD_TASK_REQUEST = 1;
    private static final int EDIT_TASK_REQUEST = 2;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(taskList, this::openEditTaskActivity);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_task);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivityForResult(intent, ADD_TASK_REQUEST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("title");
            String description = data.getStringExtra("description");

            if (requestCode == ADD_TASK_REQUEST) {
                taskList.add(new Task(title, description));
            } else if (requestCode == EDIT_TASK_REQUEST) {
                int position = data.getIntExtra("position", -1);
                if (position != -1) {
                    Task task = taskList.get(position);
                    task.setTitle(title);
                    task.setDescription(description);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
    private void openEditTaskActivity(int position) {
        Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
        intent.putExtra("title", taskList.get(position).getTitle());
        intent.putExtra("description", taskList.get(position).getDescription());
        intent.putExtra("position", position);
        startActivityForResult(intent, EDIT_TASK_REQUEST);
    }
}
