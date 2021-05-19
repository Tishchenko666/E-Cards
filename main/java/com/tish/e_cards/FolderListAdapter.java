package com.tish.e_cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FolderListAdapter extends ArrayAdapter<Folder> {

    private Context context;
    private List<Folder> folderList;

    public FolderListAdapter(Context context, List<Folder> folders) {
        super(context, R.layout.item_folder, folders);
        this.context = context;
        this.folderList = folders;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        FolderViewHolder viewHolder;
        if (view == null) {
            viewHolder = new FolderViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false);
            viewHolder.textViewFolderName = view.findViewById(R.id.tv_folder_name);
            viewHolder.textViewCardsAmount = view.findViewById(R.id.tv_amount_of_cards);
            view.setTag(viewHolder);
        } else {
            viewHolder = (FolderViewHolder) view.getTag();
        }
        Folder folder = folderList.get(position);
        viewHolder.textViewFolderName.setText(folder.getFolderName());
        String cardsAmount = folder.getLearnedCardAmount() + "/" + folder.getCardAmount();
        viewHolder.textViewCardsAmount.setText(cardsAmount);

        return view;
    }

    private static class FolderViewHolder {
        public static TextView textViewFolderName;
        public static TextView textViewCardsAmount;

    }
}
