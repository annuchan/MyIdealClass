package com.example.myidealclass;

import android.os.Bundle;
import android.util.Log;

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_user);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView1 = findViewById(R.id.recyclerViewEvents);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();

        loadEvent();
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
