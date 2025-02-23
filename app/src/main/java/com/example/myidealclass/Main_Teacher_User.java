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

public class Main_Teacher_User extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher_user);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Teacher_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Teacher_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.rightarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(Main_Teacher_User.this, StartActivity2.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.moreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(Main_Teacher_User.this, MainParent.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(Main_Teacher_User.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        ImageView exitButton = findViewById(R.id.exitbutton);

        // Устанавливаем обработчик клика на кнопку
        exitButton.setOnClickListener(v -> {
            // Логика выхода
            logout();
        });
        findViewById(R.id.leftarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
                Intent intent = new Intent(Main_Teacher_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.rightarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(Main_Teacher_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);
        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
    }
    private void showCustomPopupMenu(View view) {
        Dropdown_Menu.showCustomPopupMenu(view, this);
    }

    public void about_app(View view) {
        Intent intent = new Intent(Main_Teacher_User.this, About_the_app.class); // замените на нужную активность
        startActivity(intent);
    }
    private void logout() {
        finish();
    }
}