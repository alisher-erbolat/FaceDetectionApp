package com.example.myfacedetectionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ShowImageActivity extends AppCompatActivity {

    private DatabaseHandler databaseHandler;
    private RecyclerView recyclerView;

    private RVadapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        recyclerView = findViewById(R.id.imagesRV);
        databaseHandler = new DatabaseHandler(this);
    }

    public void getData(View view){
        try {
            rvAdapter = new RVadapter(databaseHandler.getAllImagesData());
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(rvAdapter);
        }catch (Exception e){
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}