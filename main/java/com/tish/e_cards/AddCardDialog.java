package com.tish.e_cards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AddCardDialog extends DialogFragment {

    private FragmentSendDataListener fragmentSendDataListener;
    CardsConnector cardsConnector;
    EditText etWord;
    EditText etTranslation;
    EditText etTag;
    EditText etDescription;
    private String thisFolder;

    public AddCardDialog(Context mainContext, String thisFolder) {
        cardsConnector = new CardsConnector(mainContext);
        this.thisFolder = thisFolder;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (FragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Введите параметры слова");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View addCardView = inflater.inflate(R.layout.add_card_dialig_view, null);
        etWord = addCardView.findViewById(R.id.et_new_card_word);
        etTranslation = addCardView.findViewById(R.id.et_new_card_translation);
        etTag = addCardView.findViewById(R.id.et_new_card_tag);
        etDescription = addCardView.findViewById(R.id.et_new_card_description);
        builder.setView(addCardView);
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Card sendNewCard = new Card(etWord.getText().toString(), etTranslation.getText().toString(), etTag.getText().toString(), etDescription.getText().toString());
                sendNewCard.setFolderName(thisFolder);
                long insertResult = cardsConnector.insertNewCard(sendNewCard);
                if (insertResult > -1)
                    fragmentSendDataListener.onSendData(sendNewCard);
                else
                    fragmentSendDataListener.onSendData(String.valueOf(insertResult));
            }
        });
        builder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();
    }

}
