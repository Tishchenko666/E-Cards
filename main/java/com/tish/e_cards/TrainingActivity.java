package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TrainingActivity extends AppCompatActivity {

    ListView trainingListView;
    String[] trainNames = {"Поиск слова", "Поиск перевода", "Поиск слова по описанию",
            "Ввод слова по переводу", "Ввод слова по описанию", "Поиск соответствий"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        trainingListView = findViewById(R.id.list_view_trainings);
        TrainListAdapter trainAdapter = new TrainListAdapter(TrainingActivity.this, trainNames);
        trainingListView.setAdapter(trainAdapter);

        trainingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = trainNames[position];
                TrainSettingsDialog trainSettingsDialog = new TrainSettingsDialog(TrainingActivity.this, selectedItem);
                trainSettingsDialog.show(getSupportFragmentManager(), "tsd");
            }
        });
    }
}