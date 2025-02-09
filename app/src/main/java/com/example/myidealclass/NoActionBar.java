package com.example.myidealclass;

import androidx.appcompat.app.AppCompatActivity;

public class NoActionBar {
    public static void hideActionBar(AppCompatActivity activity) {
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
    }
}
