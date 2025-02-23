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

public class Event_User extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private EventsAdapter adapter; // Используем правильный адаптер
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_user);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView1 = findViewById(R.id.recyclerViewEvents);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();

        loadEvent();
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_User.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });


        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Event_User.this, About_the_app.class); // замените на нужную активность
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
    private void loadEvent() {
        Call<List<com.example.myidealclass.Event>> call = apiService.getEvents();
        call.enqueue(new Callback<List<com.example.myidealclass.Event>>() {
            @Override
            public void onResponse(Call<List<com.example.myidealclass.Event>> call, Response<List<com.example.myidealclass.Event>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<com.example.myidealclass.Event> events = response.body();
                    Log.d("API_SUCCESS", "Получено " + events.size() + " events.");
                    adapter = new EventsAdapter(Event_User.this, events); // Используем EventsAdapter
                    recyclerView1.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Не удалось загрузить список событий. Код ответа: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<com.example.myidealclass.Event>> call, Throwable t) {
                Log.e("API_ERROR", "Ошибка загрузки данных: " + t.getMessage());
            }
        });
    }
}
