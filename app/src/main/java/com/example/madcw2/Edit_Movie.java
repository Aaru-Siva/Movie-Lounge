package com.example.madcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_Movie extends AppCompatActivity {
    private MovieDB movieDataBase;

    ArrayList<String> selectedItems = new ArrayList<>();

    //Initializing database,listview,button
    ListView listView;
    EditText titleOfMovie, year, Director,cast, rating, Review,favourite;
    Button ButtonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        movieDataBase = new MovieDB(this);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("title");
        Toast.makeText(Edit_Movie.this, name, Toast.LENGTH_LONG).show();

        titleOfMovie = findViewById(R.id.titleOfMovie);
        year = findViewById(R.id.year);
        Director = findViewById(R.id.Director);
        cast = findViewById(R.id.cast);
        rating = findViewById(R.id.rating);
        Review = findViewById(R.id.Review);
        ButtonSubmit = (Button) findViewById(R.id.Submit);
        favourite = findViewById(R.id.et_favourite);
        fillFields(name);
    }

    //display list of movies
    public void fillFields(String name){
        Cursor res = movieDataBase.search(name);

        if (res.getCount() == 0) {
            Toast.makeText(Edit_Movie.this, "Nothing to show", Toast.LENGTH_LONG).show();
        } else {
            while (res.moveToNext()) {
                titleOfMovie.append(res.getString(0));
                year.append(res.getString(1));
                Director.append(res.getString(2));
                cast.append(res.getString(3));
                rating.append(res.getString(4));
                Review.append(res.getString(5));
                favourite.append(res.getString(6));
                if(res.getString(6).equals("true")) {
                    favourite.setText("Favourite");
                }else{
                    favourite.setText("Not Favourite");
                }
            }
        }
    }

    //update data
    public void updateData(View v){
        boolean isUpdate = movieDataBase.updateEditData(
                titleOfMovie.getText().toString(),
                year.getText().toString(),
                Director.getText().toString(),
                cast.getText().toString(),
                rating.getText().toString(),
                Review.getText().toString(),
                favourite.getText().toString());
        if (isUpdate == true) {
            Toast.makeText(Edit_Movie.this, "Data Updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Edit_Movie.class);
            finish();

        } else {
            Toast.makeText(Edit_Movie.this, "Data Is Not Updated", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    public void finish() {
        Intent intent = new Intent(this, Edit.class);
        super.finish();
        startActivity(intent);
    }
}