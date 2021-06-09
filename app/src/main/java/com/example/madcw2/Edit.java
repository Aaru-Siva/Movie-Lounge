package com.example.madcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    //Initializing database
    private MovieDB movieDataBase;

    //creation of array
    ArrayList<String> selectedItems = new ArrayList<>();

    //Initializing list view
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        movieDataBase = new MovieDB(this);
        listView = (ListView) findViewById(R.id.List_View_Display);

        viewAll();
    }

    public void viewAll() {
        Cursor res = movieDataBase.getData();
        ArrayList<String> items = new ArrayList<>();

        if (res.getCount() == 0) {
            Toast.makeText(Edit.this, "Nothing to show", Toast.LENGTH_LONG).show();
        } else {

            while (res.moveToNext()) {
                String name = res.getString(0);
                items.add(name);

                ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
                listView.setAdapter(aa);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> p1, View p2, int i, long p4) {
                        String selectedItem = ((TextView) p2).getText().toString();
                        Intent intent = new Intent();
                        intent.setClass(Edit.this, Edit_Movie.class);
                        intent.putExtra("title", selectedItem);
                        finish();
                        startActivity(intent);

                    }
                });
            }
        }
    }
}