package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InFolderActivity extends AppCompatActivity implements FragmentSendCardDataListener {

    private ListView listViewCards;
    CardsConnector cardsConnector;
    CardListAdapter cardListAdapter;
    FloatingActionButton addCardButton;
    List<Card> cardsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_folder);
        initView();
    }

    private void initView() {
        listViewCards = findViewById(R.id.list_view_cards);
        listViewCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Card editCard = (Card) parent.getItemAtPosition(position);
                Intent editCardIntent = new Intent(InFolderActivity.this, EditCardActivity.class);
                editCardIntent.putExtra("editCard", editCard);
                startActivity(editCardIntent);
            }
        });

        addCardButton = findViewById(R.id.add_card_button);
        addCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCardDialog addCardDialog = new AddCardDialog(InFolderActivity.this, getIntent().getExtras().getString("folderName"));
                addCardDialog.show(getSupportFragmentManager(), "acd");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        cardsConnector = new CardsConnector(InFolderActivity.this);
        cardsList = cardsConnector.getAllCards(getIntent().getExtras().getString("folderName"));
        cardListAdapter = new CardListAdapter(InFolderActivity.this, cardsList);
        listViewCards.setAdapter(cardListAdapter);
    }

    @Override
    public void onSendData(long data) {
        Toast.makeText(this, "При добавлении слова произолша ошибка. Повторите попытку", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendData(Card card) {
        cardsList.add(card);
        cardListAdapter.notifyDataSetChanged();
        FoldersConnector foldersConnector = new FoldersConnector(InFolderActivity.this);
        foldersConnector.updateCardAmount(card.getFolderName());
    }
}