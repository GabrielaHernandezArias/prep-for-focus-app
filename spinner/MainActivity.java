package com.course.example.customarrayadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public ArrayList<SubjectData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.list);

        //attach listener
        list.setOnItemClickListener(this);

        //create ArrayList and add data
        arrayList = new ArrayList<SubjectData>();
        arrayList.add(new SubjectData("Android", "https://www.tutorialspoint.com/android/", R.drawable.android));
        arrayList.add(new SubjectData("JAVA", "https://www.tutorialspoint.com/java/", R.drawable.java));
        arrayList.add(new SubjectData("Python", "https://www.tutorialspoint.com/python/", R.drawable.python));
        arrayList.add(new SubjectData("Javascript", "https://www.tutorialspoint.com/javascript/", R.drawable.javascript));
        arrayList.add(new SubjectData("C", "https://www.tutorialspoint.com/cprogramming/", R.drawable.c));
        arrayList.add(new SubjectData("C++", "https://www.tutorialspoint.com/cplusplus/", R.drawable.cpp));
        arrayList.add(new SubjectData("Dart", "https://www.tutorialspoint.com/dart_programming/", R.drawable.dart));
        arrayList.add(new SubjectData("PHP", "https://www.tutorialspoint.com/php/", R.drawable.php));

        CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        list.setAdapter(customAdapter);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        String link = arrayList.get(position).getLink();

        //implicit intent to open link in browser
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}

