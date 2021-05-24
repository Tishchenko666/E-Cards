package com.tish.e_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentSendDataListener {

    private ListView listViewFolders;
    FoldersConnector foldersConnector;
    FolderListAdapter folderListAdapter;
    FloatingActionButton addFolderButton;
    List<Folder> foldersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        listViewFolders = findViewById(R.id.list_view_folders);
        listViewFolders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Folder folder = (Folder) parent.getItemAtPosition(position);
                Intent inFolderIntent = new Intent(MainActivity.this, InFolderActivity.class);
                inFolderIntent.putExtra("folderName", folder.getFolderName());
                startActivity(inFolderIntent);
            }
        });

        addFolderButton = findViewById(R.id.add_folder_button);
        addFolderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFolderDialog addFolderDialog = new AddFolderDialog(MainActivity.this);
                addFolderDialog.show(getSupportFragmentManager(), "afd");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        List<Folder> foldersList;
        foldersConnector = new FoldersConnector(MainActivity.this);
        foldersList = foldersConnector.getAllFolders();
        folderListAdapter = new FolderListAdapter(MainActivity.this, foldersList);
        listViewFolders.setAdapter(folderListAdapter);
    }

    @Override
    public void onSendData(String data) {
        if (data.equalsIgnoreCase("-1"))
            Toast.makeText(this, "При добавлении папки произолша ошибка. Повторите попытку", Toast.LENGTH_SHORT).show();
        else {
            foldersList.add(new Folder(data));
            folderListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSendData(Card card) { }

}