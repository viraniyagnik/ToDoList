//Name: Yagnik Virani
//Student id:A00227162

package com.example.yagnik__todolist;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //PlayerList for players
    ArrayList<String> PlayerList;

    //adapter
    ArrayAdapter<String> adapter;

    //PlayerText for edit text
    EditText PlayerText;

    //addButton to add edittext value into list
    Button addButton;

    //List_View
    ListView List_View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get List_View by it's id Player_Listview
        List_View = (ListView) findViewById(R.id.Player_Listview);

        //get PlayerText by it's id Player_Edit
        PlayerText = (EditText) findViewById(R.id.Player_Edit);

        //get addButton by it's id Button_add
        addButton = (Button) findViewById(R.id.Button_add);

        PlayerList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(MainActivity.this , android.R.layout.simple_list_item_multiple_choice, PlayerList);


        View.OnClickListener addlistner = new View.OnClickListener() {
            @Override
            //onclick events settext to add player
            public void onClick(View view) {
                PlayerList.add(PlayerText.getText().toString());
                PlayerText.setText("");
                adapter.notifyDataSetChanged();

            }
        };

        List_View.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            //On long click remove player from list
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                SparseBooleanArray positoonchecker = List_View.getCheckedItemPositions();
                int count = List_View.getCount();
                for(int item = count-1;item>=0;item--){
                    if(positoonchecker.get(item)){
                        adapter.remove(PlayerList.get(item));
                        Toast.makeText(MainActivity.this, "Player deleted", Toast.LENGTH_SHORT).show();
                    }
                }
                positoonchecker.clear();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        addButton.setOnClickListener(addlistner);
        List_View.setAdapter(adapter);
    }



}