/* This example connects to MySQL using JDBC running on Android.
 * It uses a background thread to run JDBC so that the main thread stays responsive.
 * The data is written to an ArrayList. The main Activity creates an explicit
 * intent, places the ArrayList on the intent, and requests a second Activity
 * be started to place the data on the UI. 
 * The application uses an INTERNET permission. The j-connector jar file
 * is on the Java build path and in the libs folder.
 */

package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.sql.*;

public class MainActivity extends Activity {
		
	private Thread t = null;
	private ArrayList<String> list = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//start thread
		t = new Thread(background);
		t.start();
			
	}
	
	private Runnable background = new Runnable() {
		public void run(){
			String URL = "jdbc:mysql://frodo.bentley.edu:3306/test";
	        String username = "harry";
	        String password = "harry";

	        try { //load driver into VM memory
	        	Class.forName("com.mysql.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            Log.e("JDBC", "Did not load driver");
	          
	        }

	        Statement stmt = null;

	        try ( //create connection using try with resources
	            Connection con = DriverManager.getConnection (
	                URL,
	                username,
	                password)) {
	            stmt = con.createStatement();

	            // execute SQL commands to create table, insert data, select contents       
	                stmt.executeUpdate("drop table if exists first;");
	                stmt.executeUpdate("create table first(id integer primary key, city varchar(25));");
	                stmt.executeUpdate("insert into first values(1, 'Waltham');");
	                stmt.executeUpdate("insert into first values(2, 'Cambridge');");
	                stmt.executeUpdate("insert into first values(3, 'Framingham');");
	              
	                ResultSet result = stmt.executeQuery("select * from first;");
	                
	                //read result set, write data to ArrayList and Log
	                while (result.next()) {
	                	String city = result.getString("city");
	                String str = String.format("%d    %s", result.getInt("id"), result.getString("city"));
	                list.add(city);	
	                Log.e("JDBC",str );              
	                }
	                
	                //Create intent, place ArrayList on intent object, 
	                //   request another Activity be started to use the data
	                Intent intent = new Intent(MainActivity.this, UseData.class);
	                intent.putStringArrayListExtra("list", list);
	                startActivity(intent);
	                
	                //clean up
	                t = null;

	        } catch (SQLException e) {
	            Log.e("JDBC","problems with SQL sent to "+URL+
	                ": "+e.getMessage());
	        }
	    
		}
	};

	
	

}

