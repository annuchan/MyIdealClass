package com.example.myidealclass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class School_asset_user extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private School_assetAdapter adapter; // Используем правильный адаптер
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_asset_user);
        recyclerView3 = findViewById(R.id.recyclerViewAssets);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));


        apiService = RetrofitClient.getApiService();

        loadasset();
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.moreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход на другую активность при клике на левую стрелку
                Intent intent = new Intent(School_asset_user.this, Application_form_User.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_user.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_user.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_user.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_user.this, About_the_app.class); // замените на нужную активность
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
    private void loadasset() {
            Call<List<School_asset>> call = apiService.getSchool_asset();
            call.enqueue(new Callback<List<School_asset>>() {
                @Override
                public void onResponse(Call<List<School_asset>> call, Response<List<School_asset>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<School_asset> school_assets = response.body();
                        Log.d("API_SUCCESS", "Получено " + school_assets.size() + " элементов.");

                        adapter = new School_assetAdapter(School_asset_user.this, school_assets);
                        recyclerView3.setAdapter(adapter);
                    } else {
                        Log.e("API_ERROR", "Не удалось загрузить список. Код ответа: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<School_asset>> call, Throwable t) {
                    Log.e("API_ERROR", "Ошибка загрузки данных: " + t.getMessage());
                }
            });
        }
    }