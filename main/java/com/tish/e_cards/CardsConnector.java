package com.tish.e_cards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardsConnector {
    static String TABLE_NAME = "cards";
    static String COLUMN_CARD_ID = "_id";
    static String COLUMN_CARD_FOLDER_NAME = "FolderName";
    static String COLUMN_CARD_WORD = "Word";
    static String COLUMN_CARD_TRANSLATION = "Translation";
    static String COLUMN_CARD_TAG = "Tag";
    static String COLUMN_CARD_DESCRIPTION = "Description";
    static String COLUMN_CARD_MARK = "Mark";
    public static final String CREATE_TABLE_CARDS = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            COLUMN_CARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CARD_FOLDER_NAME + " TEXT, " +
            COLUMN_CARD_WORD + " TEXT NOT NULL, " +
            COLUMN_CARD_TRANSLATION + " TEXT NOT NULL, " +
            COLUMN_CARD_TAG + " TEXT, " +
            COLUMN_CARD_DESCRIPTION + " TEXT, " +
            COLUMN_CARD_MARK + " INTEGER NOT NULL" +
            "FOREIGN KEY(" + COLUMN_CARD_FOLDER_NAME + ") REFERENCES " +
            FoldersConnector.TABLE_NAME + "(" + FoldersConnector.COLUMN_FOLDER_NAME + ") " +
            "ON UPDATE CASCADE" + ")";

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cardCursor;

    public CardsConnector(Context context) {
        dbHelper = new DBHelper(context);
    }

    public List<Card> getAllCards(String folderName) {
        List<Card> cards = new ArrayList<Card>();
        db = dbHelper.getReadableDatabase();
        cardCursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COLUMN_CARD_FOLDER_NAME + "=?", new String[]{folderName});
        cardCursor.moveToFirst();
        while (cardCursor.moveToNext()) {
            String word = cardCursor.getString(cardCursor.getColumnIndex(COLUMN_CARD_WORD));
            String translation = cardCursor.getString(cardCursor.getColumnIndex(COLUMN_CARD_TRANSLATION));
            String tag = cardCursor.getString(cardCursor.getColumnIndex(COLUMN_CARD_TAG));
            String description = cardCursor.getString(cardCursor.getColumnIndex(COLUMN_CARD_DESCRIPTION));
            int mark = cardCursor.getInt(cardCursor.getColumnIndex(COLUMN_CARD_MARK));
            boolean bMark = mark == 1;
            cards.add(new Card(word, translation, tag, description, bMark));
        }
        cardCursor.close();
        db.close();
        return cards;
    }

    public long insertNewCard(Card newCard) {
        ContentValues cvNewCard = new ContentValues();
        cvNewCard.put(COLUMN_CARD_FOLDER_NAME, newCard.getFolderName());
        cvNewCard.put(COLUMN_CARD_WORD, newCard.getWord());
        cvNewCard.put(COLUMN_CARD_TRANSLATION, newCard.getTranslation());
        cvNewCard.put(COLUMN_CARD_TAG, newCard.getTag());
        cvNewCard.put(COLUMN_CARD_DESCRIPTION, newCard.getDescription());
        int mark = newCard.getMark() ? 1 : 0;
        cvNewCard.put(COLUMN_CARD_MARK, mark);
        db = dbHelper.getWritableDatabase();
        long result = db.insert(TABLE_NAME, null, cvNewCard);
        db.close();
        return result;
    }

}