package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TrainingActivity extends AppCompatActivity {

    ListView trainingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        trainingListView = findViewById(R.id.list_view_trainings);
        TrainListAdapter trainAdapter = new TrainListAdapter(TrainingActivity.this);
        trainingListView.setAdapter(trainAdapter);

        trainingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}