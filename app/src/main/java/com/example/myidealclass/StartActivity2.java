package com.example.myidealclass;

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
    }

    private void showCustomPopupMenu(View view) {
        // Раздуваем кастомное меню из XML
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.custom_menu_item, null);

        // Создаем PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true); // true = окно закрывается при клике вне него

        // Обработчики кликов для пунктов меню
        TextView item1 = popupView.findViewById(R.id.menu_item1);
        TextView item2 = popupView.findViewById(R.id.menu_item2);
        TextView item3 = popupView.findViewById(R.id.menu_item3);

        item1.setOnClickListener(v -> {
            Toast.makeText(this, "Выбрана опция 1", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        item2.setOnClickListener(v -> {
            Toast.makeText(this, "Выбрана опция 2", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        item3.setOnClickListener(v -> {
            Toast.makeText(this, "Выбрана опция 3", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        // Показываем PopupWindow
        popupWindow.showAsDropDown(view);
    }
}