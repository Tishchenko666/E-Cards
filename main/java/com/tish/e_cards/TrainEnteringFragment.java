package com.tish.e_cards;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainEnteringFragment extends Fragment {

    TextView searchTextView;
    EditText answersEditText;
    Button checkButton;
    CardsConnector cardsConnector;
    List<Card> cards;
    int index = 0;
    String answer;
    int learnedCounter = 0;
    int trainType = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.train_entering_fragment, null);

        searchTextView = view.findViewById(R.id.tv_enter);
        answersEditText = view.findViewById(R.id.et_enter);
        checkButton = view.findViewById(R.id.b_check);
        cardsConnector = new CardsConnector(getContext());

        Bundle bundle = getArguments();
        String trainingType = bundle.getString("trainType");
        String folder = bundle.getString("folder");
        String tag = bundle.getString("tag");

        if (!folder.equalsIgnoreCase("") && !tag.equalsIgnoreCase("")) {
            cards = cardsConnector.getCardsFromFolderAndTag(folder, tag);
        } else if (!folder.equalsIgnoreCase("") && tag.equalsIgnoreCase("")) {
            cards = cardsConnector.getAllCards(folder);
        } else if (folder.equalsIgnoreCase("") && !tag.equalsIgnoreCase("")) {
            cards = cardsConnector.getCardsWithTag(tag);
        } else if (folder.equalsIgnoreCase("") && tag.equalsIgnoreCase("")) {
            cards = cardsConnector.getAllCards("Все слова");
        }

        Collections.shuffle(cards);
        if (trainingType.equals("Ввод слова по переводу")) {
            trainType = 1;
            answer = getSearchItem(1, index);
        } else if (trainingType.equals("Ввод слова по описанию")) {
            trainType = 2;
            answer = getSearchItem(2, index);
        }

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index += 1;
                String userAnswer = answersEditText.getText().toString();
                if (answer.equalsIgnoreCase(userAnswer)) {
                    answersEditText.setBackgroundColor(Color.GREEN);
                    learnedCounter += 1;
                } else {
                    answersEditText.setBackgroundColor(Color.RED);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                answersEditText.setBackgroundColor(Color.WHITE);
                if (index < cards.size())
                    answer = getSearchItem(trainType, index);
                else {
                    dialogCreator(learnedCounter, cards.size());
                }
            }
        });
        return view;

    }

    private String getSearchItem(int type, int index) {
        String word = "", translation, description;
        switch (type) {
            case 1:
                word = cards.get(index).getWord();
                translation = cards.get(index).getTranslation();
                fillCard(translation);
                break;
            case 2:
                word = cards.get(index).getWord();
                description = cards.get(index).getDescription();
                fillCard(description);
                break;
        }
        return word;
    }

    private void fillCard(String value) {
        searchTextView.setText(value);
    }


    private void dialogCreator(int result, int size) {
        AlertDialog resultDialog = new AlertDialog.Builder(getContext())
                .setTitle("Окно результата тренировки")
                .setMessage("Ваш результат:  "
                        + result + " из " + size)
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create();
        resultDialog.show();
    }
}
