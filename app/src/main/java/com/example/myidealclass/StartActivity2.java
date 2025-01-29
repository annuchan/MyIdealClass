package com.example.myidealclass;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StartActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start3);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);

        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
        findViewById(R.id.leftarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
                Intent intent = new Intent(StartActivity2.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.rightarrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на правую стрелку
                Intent intent = new Intent(StartActivity2.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
    }

    private void showCustomPopupMenu(View view) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.custom_menu_item, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        TextView item1 = popupView.findViewById(R.id.menu_item1);
        TextView item2 = popupView.findViewById(R.id.menu_item2);
        TextView item3 = popupView.findViewById(R.id.menu_item3);
        TextView item4 = popupView.findViewById(R.id.menu_item4);
        TextView item5 = popupView.findViewById(R.id.menu_item5);
        TextView item6 = popupView.findViewById(R.id.menu_item6);
        TextView item7 = popupView.findViewById(R.id.menu_item7);
        TextView item8 = popupView.findViewById(R.id.menu_item8);


        item1.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу школы", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        item2.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу электронного дневника", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        item3.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу домашнего задания", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        item4.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу уведомлений об учебе", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        item5.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу мероприятий", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        item6.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу актива школы", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        item7.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу питания", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        item8.setOnClickListener(v -> {
            Toast.makeText(this, "Вы перешли на страницу учителей", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        // Показываем PopupWindow
        popupWindow.showAsDropDown(view);
    }
}