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

public class Important_information_user extends AppCompatActivity {
    private RecyclerView recyclerView2;
    private Important_informationAdapter adapter; // Используем правильный адаптер
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_important_information_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView2 = findViewById(R.id.recyclerViewImportant_information);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();

        loadImportent_info();
    }

    private void loadImportent_info() {
        Call<List<Important_information>> call = apiService.getImportant_Information();
        call.enqueue(new Callback<List<Important_information>>() {
            @Override
            public void onResponse(Call<List<Important_information>> call, Response<List<Important_information>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Important_information> importantInformations = response.body();
                    Log.d("API_SUCCESS", "Получено " + importantInformations.size() + " событий.");
                    adapter = new Important_informationAdapter(Important_information_user.this, importantInformations);
                    recyclerView2.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Не удалось загрузить список событий. Код ответа: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Important_information>> call, Throwable t) {
                Log.e("API_ERROR", "Ошибка загрузки данных: " + t.getMessage());
            }
        });
    }
}
