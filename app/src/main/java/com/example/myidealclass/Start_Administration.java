package com.example.myidealclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Start_Administration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_administration);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_Administration.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_Administration.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_Administration.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_Administration.this, About_the_app.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        ImageView exitButton = findViewById(R.id.exitbutton);

        // Устанавливаем обработчик клика на кнопку
        exitButton.setOnClickListener(v -> {
            // Логика выхода
            logout();
        });

        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);
        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
    }
    private void logout() {
        finish();
    }
    private void showCustomPopupMenu(View view) {
        Dropdown_menu_admin.showCustomPopupMenu(view, this);
    }
}