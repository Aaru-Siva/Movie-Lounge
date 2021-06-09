package com.example.madcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Favourites extends AppCompatActivity {

    //creation of array list
    ArrayList<String> checkedItems1 = new ArrayList<>();

    //Initializing list view, button, database
    ListView FavMovie;
    Button btSave;
    CheckedTextView checked;
    private MovieDB movieDataBase;
    Display input=new Display();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        //getting the ids
        FavMovie = (ListView) findViewById(R.id._dynamicAvailability);
        btSave = (Button) findViewById(R.id.buttonSave);
        FavMovie.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //creating an instance of the movie database
        movieDataBase = new MovieDB(this);
        populateMovieListView();
    }

    //display available data list
    public void populateMovieListView(){

        Cursor data = movieDataBase.displayFavourite();
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {
            listData1.add(data.getString(0));
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.checkbox, R.id.txt_Availability, listData1);
        FavMovie.setAdapter(adapter);

        for(int x=0; x<listData1.size(); x++)
            FavMovie.setItemChecked(x, true);
        FavMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if (checkedItems1.contains(selectedItem))
                    checkedItems1.remove(selectedItem);
                else
                    checkedItems1.add(selectedItem);

            }
        });
    }

    //method for save
    public void save(View view) {
        String checkItems = "";
        for (String item : checkedItems1) {
            movieDataBase.addUnCheckedList(item);

            if (checkItems == "") {
                checkItems = item;
            }
            else
                checkItems += "/" + item;
        }
        Toast.makeText(this, checkItems+" "+"added", Toast.LENGTH_LONG).show();
    }
}