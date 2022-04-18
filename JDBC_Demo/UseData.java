package com.example.androidtest;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UseData extends Activity {
	
	private TextView texted = null;
	private ArrayList<String> cities = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use_data);
		
		texted = (TextView)findViewById(R.id.text);
		
		//get ArrayList from intent object
		cities = getIntent().getStringArrayListExtra("list");
		
		//write data to UI
		for (int i=0; i<cities.size(); i++) {
			texted.append(cities.get(i) + "\n");
		}
		
	}

}
