package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import java.util.List;

public class TrainModelActivity extends AppCompatActivity {
    FrameLayout container;
    TrainSearchingFragment trainSearchingFragment;
    TrainEnteringFragment trainEnteringFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_model);


        container = findViewById(R.id.train_container);
        Bundle extras = getIntent().getExtras();
        String trainingType = extras.getString("trainingType");
        String folder = extras.getString("folder");
        String tag = extras.getString("tag");

        Bundle bundle = new Bundle();
        bundle.putString("trainType", trainingType);
        bundle.putString("folder", folder);
        bundle.putString("tag", tag);

        if (trainingType.equalsIgnoreCase("Поиск слова") ||
                trainingType.equalsIgnoreCase("Поиск перевода") ||
                trainingType.equalsIgnoreCase("Поиск слова по описанию")) {
            trainSearchingFragment = (TrainSearchingFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.train_search_fragment);
            trainSearchingFragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.train_container, trainSearchingFragment);
            transaction.commit();
        } else if (trainingType.equalsIgnoreCase("Ввод слова по переводу") ||
                trainingType.equalsIgnoreCase("Ввод слова по описанию")) {
            trainEnteringFragment = (TrainEnteringFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.train_enter_fragment);
            trainEnteringFragment.setArguments(bundle);
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.train_container, trainEnteringFragment);
            transaction.commit();
        } else if (trainingType.equalsIgnoreCase("Поиск соответствий")) {

        }


    }
}