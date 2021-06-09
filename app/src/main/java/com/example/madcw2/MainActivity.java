package com.example.madcw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();  //displaying a log message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void register(View view) {
        Log.d(LOG_TAG,"Button clicked! for Register Movie"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void display(View view) {
        Log.d(LOG_TAG,"Button clicked! for display"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Display.class);
        startActivity(intent);
    }

    public void favourites(View view) {
        Log.d(LOG_TAG,"Button clicked! for favourite"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Favourites.class);
        startActivity(intent);
    }

    public void edit(View view) {
        Log.d(LOG_TAG,"Button clicked! for Edit"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Edit.class);
        startActivity(intent);
    }

    public void search(View view) {
        Log.d(LOG_TAG,"Button clicked! for search"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void rating(View view) {
        Log.d(LOG_TAG,"Button clicked! for Rating"); // displaying log message

        //creating an explicit intent by using Intent and opening the activity by using startActivity()
        Intent intent = new Intent(this, Ratings.class);
        startActivity(intent);
    }
    
}