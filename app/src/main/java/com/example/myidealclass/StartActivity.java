package com.example.myidealclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        NoActionBar.hideActionBar(this);
        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);

        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
        findViewById(R.id.leftarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
                Intent intent = new Intent(StartActivity.this, StartActivity2.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1); // Если значение не найдено, вернется -1
        if (userId == -1) {
            Log.e("StartActivity", "Не удалось получить ID пользователя");
        } else {
            Log.d("StartActivity", "Получен ID пользователя: " + userId);
        }

        findViewById(R.id.rightarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(StartActivity.this, StartActivity2.class); // замените на нужную активность
                startActivity(intent);
            }
        });
    }

    private void showCustomPopupMenu(View view) {
        Dropdown_Menu.showCustomPopupMenu(view, this);
    }

    public void about_app(View view) {
        Intent intent = new Intent(StartActivity.this, About_the_app.class); // замените на нужную активность
        startActivity(intent);
    }
}
