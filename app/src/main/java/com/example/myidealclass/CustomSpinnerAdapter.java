package com.example.myidealclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<Subject> {
    private Context context;
    private List<Subject> subjects;

    public CustomSpinnerAdapter(Context context, List<Subject> subjects) {
        super(context, R.layout.custom_spinner_item, subjects);
        this.context = context;
        this.subjects = subjects;
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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_spinner_item, parent, false);
        TextView textView = view.findViewById(R.id.spinner_text);
        textView.setText(subjects.get(position).getTitle());
        return view;
    }
}
