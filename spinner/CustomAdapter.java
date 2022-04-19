package com.course.example.customarrayadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter {
    ArrayList<SubjectData> arrayList;
    Context context;
    public CustomAdapter(Context context, ArrayList<SubjectData> arrayList) {
        super(context, 0, arrayList);
        this.arrayList=arrayList;
        this.context=context;
    }

    //return view displaying data at position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SubjectData subjectData=arrayList.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.list_row, null);

            TextView title=convertView.findViewById(R.id.title);
            ImageView imag=convertView.findViewById(R.id.list_image);
            title.setText(subjectData.getSubjectName());
            imag.setImageResource(subjectData.getImage());

        }
        return convertView;
    }
}
