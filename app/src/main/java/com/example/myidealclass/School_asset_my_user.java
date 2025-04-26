package com.example.myidealclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class School_asset_my_user extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SectionsAdapter adapter; // Используем SectionsAdapter
    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_asset_my_user); // Устанавливаем разметку

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recyclerViewAssetmychild);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Теперь создаем адаптер и устанавливаем его в RecyclerView
        adapter = new SectionsAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter); // Теперь recyclerView не будет null

        apiService = RetrofitClient.getApiService();

        loadSchoolAssets();

        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_my_user.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_my_user.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_my_user.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_asset_my_user.this, About_the_app.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        ImageView exitButton = findViewById(R.id.exitbutton);

        // Устанавливаем обработчик клика на кнопку
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, Autorization.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        ImageView dropdownMenu = findViewById(R.id.dropdown_menu);
        dropdownMenu.setOnClickListener(view -> showCustomPopupMenu(view));
    }

    private void showCustomPopupMenu(View view) {
        Dropdown_Menu.showCustomPopupMenu(view, this);
    }
    private void loadSchoolAssets() {
        // Извлекаем userId из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1); // Предполагается, что userId был сохранен в SharedPreferences

        if (userId == -1) {
            // Если userId не найден, показываем ошибку
            Log.e("API_ERROR", "User ID не найден.");
            return;
        }

        // Формируем URL с параметром userId для нового API
        String baseUrl = "https://yourapiurl.com";  // Замените на ваш URL
        String endpoint = "/api/scheduleDetailed?userId=" + userId;  // Новый путь
        String fullUrl = baseUrl + endpoint;

        Log.d("API_REQUEST", "Requesting URL: " + fullUrl);

        // Создаем запрос
        Call<List<Club>> call = apiService.getClubs(userId);
        call.enqueue(new Callback<List<Club>>() {
            @Override
            public void onResponse(Call<List<Club>> call, Response<List<Club>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Club> clubs = response.body();

                    // Теперь обновляем данные через адаптер
                    adapter.updateData(clubs);  // Вызываем updateData через экземпляр адаптера

                    for (Club club : clubs) {
                        Log.d("CLUB_DATA", "Клуб: " + club.getTitle() + ", Руководитель: " + club.getTeacherFIO());

                        List<Club.Schedule> schedules = club.getSchedules();
                        for (Club.Schedule schedule : schedules) {
                            Log.d("SCHEDULE", "День: " + schedule.getDayOfWeek() +
                                    ", Время: " + schedule.getStartTime() + " - " + schedule.getEndTime());
                        }
                    }
                } else {
                    Log.e("API_ERROR", "Ответ от сервера пуст или ошибка");
                }
            }

            @Override
            public void onFailure(Call<List<Club>> call, Throwable t) {
                Log.e("API_ERROR", "Ошибка запроса: " + t.getMessage());
            }
        });
    }

    private int getUserIdFromDatabase() {
        // Пример: Получаем роль пользователя из базы данных (замените на ваш метод доступа к БД)
        // Это просто пример, в реальном коде вам нужно будет использовать соответствующую логику
        int userId = -1;

        // Предположим, что у вас есть таблица с пользователями, в которой есть роли
        // Здесь проверяем, какая роль у пользователя и на основе этого выбираем ID

        // Например:
        boolean isParent = checkIfUserIsParent(); // Этот метод должен вернуть true, если это родитель
        boolean isTeacher = checkIfUserIsTeacher(); // Этот метод должен вернуть true, если это учитель
        boolean isAdmin = checkIfUserIsAdmin(); // Этот метод должен вернуть true, если это администратор

        if (isParent) {
            userId = getParentIdFromDatabase(); // Получаем Id родителя из БД
        } else if (isTeacher) {
            userId = getTeacherIdFromDatabase(); // Получаем Id учителя из БД
        } else if (isAdmin) {
            userId = getAdminIdFromDatabase(); // Получаем Id администратора из БД
        }

        return userId;
    }

    // Примерные методы для получения данных из БД для разных ролей:
    private boolean checkIfUserIsParent() {
        // Реализуйте логику для проверки, является ли пользователь родителем
        return true; // Например, возвращаем true для теста
    }

    private boolean checkIfUserIsTeacher() {
        // Реализуйте логику для проверки, является ли пользователь учителем
        return false; // Для теста вернем false
    }

    private boolean checkIfUserIsAdmin() {
        // Реализуйте логику для проверки, является ли пользователь администратором
        return false; // Для теста вернем false
    }

    private int getParentIdFromDatabase() {
        // Реализуйте получение Id родителя из БД
        return 1; // Примерный Id для родителя
    }

    private int getTeacherIdFromDatabase() {
        // Реализуйте получение Id учителя из БД
        return 2; // Примерный Id для учителя
    }

    private int getAdminIdFromDatabase() {
        // Реализуйте получение Id администратора из БД
        return 3; // Примерный Id для администратора
    }
}