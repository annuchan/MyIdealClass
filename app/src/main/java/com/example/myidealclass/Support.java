package com.example.myidealclass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText etMessage = findViewById(R.id.etMessage);
        TextView tvCharCount = findViewById(R.id.tvCharCount);
        Button sendButton = findViewById(R.id.sendsupport);

        // Отслеживание изменения текста для подсчета символов
        etMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentLength = s.length();
                tvCharCount.setText(currentLength + "/300");
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Обработка нажатия кнопки "Отправить"
        sendButton.setOnClickListener(v -> {
            String messageContent = etMessage.getText().toString().trim();
            if (messageContent.isEmpty()) {
                Toast.makeText(Support.this, "Введите сообщение", Toast.LENGTH_SHORT).show();
                return;
            }

            // Формирование тела письма
            String body = "Здравствуйте, ниже представлено сообщение из поддержки Мобильного приложения \"Мой Идеальный Класс\".\n\n" +
                    messageContent + "\n\n" +
                    "С уважением, поддержка МИК.";

            // Псевдо отправка (покажем сообщение на экране)
            // Вместо настоящей отправки, просто показываем Toast
            runOnUiThread(() -> Toast.makeText(Support.this, "Сообщение успешно отправлено", Toast.LENGTH_LONG).show());
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
                finish();
            }
        });

        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Support.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });


        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Support.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Support.this, About_the_app.class); // замените на нужную активность
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
        Dropdown_Menu.showCustomPopupMenu(view, this);
    }
}
