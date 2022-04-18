package com.course.example.sqlitedemopro;

//This Activity uses a helper class to execute the actual SQL code.
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.widget.TextView;
import android.content.ContentValues;
import android.text.method.ScrollingMovementMethod;

/*
Extra info:

A. Variables
    - CONTENT VALUE is used to store a set of values that Content Resolver can process
    - Content Resolver resolves a URI to a specific Content provider
    - Content Provider provides an interface to query content
    - Content Providers are used to share DBs among applications

    - CURSOR is the Interface which represents a 2 dimensional table of any database
        - When you try to retrieve some data using SELECT statement, then the database will first create a CURSOR object and return its reference to you

B. Method: OnCreate
    - Good Android development style separates the actions required on the data from the actual SQL operations performed on the database
    - the actual SQL operations performed on the database are performed in a HELPER CLASS

 */

public class SQLiteDemoProActivity extends Activity {

    // A. Variables
	private TextView text;
	private SQLiteDatabase db;
	private ContentValues values;
	private Cursor cursor;
	private SQLHelper helper;

	// B. Method: OnCreate
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // finding the text view on the XML code
        text = (TextView) findViewById(R.id.Text);
        
        // let text view widget scroll
        text.setMovementMethod(new ScrollingMovementMethod());

        // we use this sql helper to run methods (add, delete, update)
        helper = new SQLHelper(this);
        
        // create database
        try {
        	db = helper.getWritableDatabase();
        }
        catch(SQLException e) {
            Log.d("SQLiteDemo", "Create database failed");
        }
            
        // insert records
        helper.addAnimal(new Animal("tiger", 4));
        helper.addAnimal(new Animal("zebra", 23));
        helper.addAnimal(new Animal("buffalo", 13));
        helper.addAnimal(new Animal("lion", 37));
        helper.addAnimal(new Animal("yak", 18));
        helper.addAnimal(new Animal("dragon", 101));
                    
        
        // update buffalo to gorilla
    	helper.updateAnimal(new Animal("buffalo"), new Animal("gorilla"));
    	
    	// delete record
    	helper.deleteAnimal(new Animal("tiger"));
        
    	// query database
        ArrayList<Animal> animalList = helper.getAnimalList();
        
        // write contents of list to screen
        for (Animal item : animalList) {
        	text.append(item.getName() + " " + item.getQuantity() + "\n" );   
        }
        
    }
    
    //close database
    @Override
	protected void onPause() {
		super.onPause();
		if(db != null)
			db.close();		
	}
}
