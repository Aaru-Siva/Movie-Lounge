package com.example.madcw2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;


public class MovieDB extends SQLiteOpenHelper {

    /** Reference : https://www.tutorialspoint.com/android/android_sqlite_database.htm
     * Mobile development tutorials
     * https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase
     * https://developer.android.com/training/data-storage/sqlite#java*/
    private static final String DATABASE_NAME = "movies.db"; //Initializing the database name
    private static final int DATABASE_VERSION = 1;    //Initializing the version
    private static final String TABLE_NAME = "MovieDetails";  //Initializing the Table name
    private static final String COLUMN_1 = "title";   //Initializing the column 1 name as title
    private static final String COLUMN_2 = "year";       //Initializing the column 2 name as year
    private static final String COLUMN_3 = "directorName";  //Initializing the column 3 name as directorName
    private static final String COLUMN_4 = "movieCastName"; //Initializing the column 4 name as movieCastName
    private static final String COLUMN_5 = "rating";        //Initializing the column 5 name as rating
    private static final String COLUMN_6 = "review";        //Initializing the column 6 name as review
    private static final String COLUMN_7 = "favourites";    //Initializing the column 7 name as favourites

    //Creating a constructor
    public MovieDB(Context context) {
        super(context, DATABASE_NAME ,null, DATABASE_VERSION);
    }

    //Creating a onCreate method to create a table and including the column names
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +"(title Text PRIMARY KEY , year Number, directorName Text, movieCastName Text, rating Number, review Text,favourites Text )");
    }

    // onUpgrade is created to update any changes of database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //adding movie details
    public Boolean addMovieDetail(String title, String year, String directorName, String movieCastName, String rating, String review) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1 , title);
        contentValues.put(COLUMN_2 , year);
        contentValues.put(COLUMN_3 , directorName);
        contentValues.put(COLUMN_4 , movieCastName);
        contentValues.put(COLUMN_5 , rating);
        contentValues.put(COLUMN_6 , review);
        contentValues.put(COLUMN_7 , "null");
        long result = DB.insert(TABLE_NAME , null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from " + TABLE_NAME + " ORDER BY " + COLUMN_1 + " COLLATE NOCASE ASC; ",null);
        return cursor;
    }

    //checked and un checked movies
    public void addCheckedList(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'true' WHERE title = '"+title+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'true' WHERE title = '"+title+"'");
    }

    public void addUnCheckedList(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        String test = "UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'false' WHERE title = '"+title+"'";
        System.out.println(test);
        db.execSQL("UPDATE "+TABLE_NAME+" SET "+COLUMN_7+" = 'false' WHERE title = '"+title+"'");
    }

    //methods for displaying favourite movies
    public Cursor displayFavourite() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+COLUMN_7+"= 'true'",null);

        // Cursor result=db.rawQuery("SELECT * FROM "+DataBase.TABLE_NAME+ " ORDER BY "+DataBase.inputString_Col+" ASC", new String[] {} );
        return result;
    }

    //method for update data
    public boolean updateEditData(String title, String year, String directorName, String movieCastName, String rating, String review,String favourite) {
        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor result = db.rawQuery("select * from  "+TABLE_NAME+" WHERE "+ID+"= 'id' ",null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1 , title);
        contentValues.put(COLUMN_2 , year);
        contentValues.put(COLUMN_3 , directorName);
        contentValues.put(COLUMN_4 , movieCastName);
        contentValues.put(COLUMN_5 , rating);
        contentValues.put(COLUMN_6 , review);
        contentValues.put(COLUMN_7, favourite);
        db.update(TABLE_NAME, contentValues, "title = ?",new String[] { title });
        return true;

    }

    //method for search
    public Cursor search(String letters){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE title OR directorName OR movieCastName LIKE " +
                "'"+letters+"%' OR title LIKE '%"+letters+"%'  OR directorName LIKE '%"+letters+"%'  OR  movieCastName LIKE '%"+letters+"'",null);

    }
}