package com.tish.e_cards;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class AddFolderDialog extends DialogFragment {

    private EditText etNewFolder;
    private FragmentSendDataListener fragmentSendDataListener;
    FoldersConnector foldersConnector;

    public AddFolderDialog(Context mainContext) {
        foldersConnector = new FoldersConnector(mainContext);
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
        builder.setTitle("Введите параметры новой папки");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View addFolderView = inflater.inflate(R.layout.add_folder_dialog_view, null);
        etNewFolder = addFolderView.findViewById(R.id.et_new_folder_name);
        builder.setView(addFolderView);
        builder.setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sendNewFolder = etNewFolder.getText().toString();
                long insertResult = foldersConnector.insertNewFolder(sendNewFolder);
                if (insertResult > -1)
                    fragmentSendDataListener.onSendData(sendNewFolder);
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
