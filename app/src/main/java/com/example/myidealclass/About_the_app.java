package com.example.myidealclass;

import static com.example.myidealclass.Dropdown_Menu.showCustomPopupMenu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Retrofit;

public class About_the_app extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about_the_app);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            TextView textView = findViewById(R.id.CustomText);
            SpannableString spannableString = new SpannableString("Наша миссия – упростить и улучшить взаимодействие между родителями " +
                    "и сотрудниками школы, обеспечив эффективную коммуникацию и поддержку образовательного процесса.");
            Typeface typeface = ResourcesCompat.getFont(this, R.font.jost_semibold);

        spannableString.setSpan(new CustomTypefaceSpan("", typeface), 0, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannableString);

            TextView textView1 = findViewById(R.id.CustomText2);
        SpannableString spannableString1 = new SpannableString("Мой идеальный класс – мобильное приложение для эффективной связи сотрудников школы " +
                "и родителей, интегрированное с платформой для управления учебным процессом и заявками на секции и кружки.");
        Typeface typeface1 = ResourcesCompat.getFont(this, R.font.jost_semibold);
        spannableString1.setSpan(new CustomTypefaceSpan("", typeface1), 0, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView1.setText(spannableString1);
        //Обработчики всех кнопок
        //Обработчик кнопки дропдавн меню
        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);
        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
               finish();
            }
        });
    }
    private void showCustomPopupMenu(View view) {
        Dropdown_Menu.showCustomPopupMenu(view, this);
    }
}