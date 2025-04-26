package com.example.myidealclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentSpinnerAdapter extends ArrayAdapter<Student> {
    private final Context context;
    private final List<Student> students;

    public StudentSpinnerAdapter(Context context, List<Student> students) {
        super(context, R.layout.custom_spinner_item, students);
        this.context = context;
        this.students = students;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_item, parent, false);
        TextView textView = view.findViewById(R.id.spinner_text);
        textView.setText(students.get(position).getFullName());
        return view;
    }
}
