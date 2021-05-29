package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EditCardActivity extends AppCompatActivity {

    EditText etWord;
    EditText etTranslation;
    EditText etTag;
    EditText etDescription;
    LinearLayout errorLinearLayout;
    TextView errorTextView;
    Button buttonSave;
    CardsConnector cardsConnector;
    String previousWord;
    Card editCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        etWord = findViewById(R.id.et_edit_card_word);
        etTranslation = findViewById(R.id.et_edit_card_translation);
        etTag = findViewById(R.id.et_edit_card_tag);
        etDescription = findViewById(R.id.et_edit_card_description);
        errorLinearLayout = findViewById(R.id.ll_edit_card_error);
        errorTextView = findViewById(R.id.tv_edit_card_error);
        buttonSave = findViewById(R.id.b_save_edit_card);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            editCard = extras.getParcelable("editCard");
            previousWord = editCard.getWord();

            etWord.setText(editCard.getWord());
            etTranslation.setText(editCard.getTranslation());
            etTag.setText(editCard.getTag());
            etDescription.setText(editCard.getDescription());
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = etWord.getText().toString().trim();
                String translation = etTranslation.getText().toString().trim();
                String tag = etTag.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (word.isEmpty()) {
                    errorLinearLayout.setVisibility(View.VISIBLE);
                    errorTextView.setText("Поле слова не может быть пустым");
                } else if (translation.isEmpty()) {
                    errorLinearLayout.setVisibility(View.VISIBLE);
                    errorTextView.setText("Поле перевода не может быть пустым");
                } else if (word.isEmpty() && translation.isEmpty()) {
                    errorLinearLayout.setVisibility(View.VISIBLE);
                    errorTextView.setText("Поля слова и перевода не можегут быть пустыми");
                } else {
                    if (!word.equals(editCard.getWord())) {
                        editCard.setWord(word);
                    }
                    if (!translation.equals(editCard.getTranslation())) {
                        editCard.setTranslation(translation);
                    }
                    if (!tag.equals(editCard.getTag())) {
                        editCard.setTag(tag);
                    }
                    if (!description.equals(editCard.getDescription())) {
                        editCard.setDescription(description);
                    }
                    cardsConnector = new CardsConnector(EditCardActivity.this);
                    int result = cardsConnector.updateEditedCard(editCard, previousWord);
                    if (result != 1) {
                        Toast.makeText(EditCardActivity.this,
                                "Изменения не были сохранены. Повторите попытку", Toast.LENGTH_SHORT).show();
                    }
                    Intent backInFolder = new Intent(EditCardActivity.this, InFolderActivity.class);
                    startActivity(backInFolder);
                }
            }
        });

    }
}