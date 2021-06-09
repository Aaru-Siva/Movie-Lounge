package com.example.madcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {

    /** Reference : https://www.mysamplecode.com/2012/07/android-listview-checkbox-example.html
     * http://programmingroot.com/android-listview-with-checkbox-tutorial-with-example/
     * mobile tutorials - tutorial 8*/

    //creation of array list
    ArrayList<String> checkedItems = new ArrayList<>();

    //Initializing database,listview,button
    private MovieDB movieDataBase;
    private ListView listView;
    CheckBox checked;
    Button addToFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        movieDataBase = new MovieDB(this);
        listView = findViewById(R.id.List_View_Display);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        addToFavourite = (Button) findViewById(R.id.ADD_2);
        displayAllData();
    }

    //display movie list
    private void displayAllData() {
        Cursor cursor = movieDataBase.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (cursor.moveToNext()) {
            listData.add(cursor.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_view_display, R.id.txt_title, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if (checkedItems.contains(selectedItem))
                    checkedItems.remove(selectedItem);
                else
                    checkedItems.add(selectedItem);

            }
        });
    }

    public void addToList(View view) {
        String checkItems = "";
        for (String item : checkedItems) {
            movieDataBase.addCheckedList(item);

            if (checkItems == "")
                checkItems = item;

            else
                checkItems += "/" + item;

        }
        Toast.makeText(this, checkItems+" "+"added", Toast.LENGTH_LONG).show();

    }
}