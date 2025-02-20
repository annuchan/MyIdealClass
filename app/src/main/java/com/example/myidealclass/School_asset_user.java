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

public class School_asset_user extends AppCompatActivity {
    private RecyclerView recyclerView3;
    private School_assetAdapter adapter; // Используем правильный адаптер
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_school_asset_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView3 = findViewById(R.id.recyclerViewAssets);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));


        apiService = RetrofitClient.getApiService();

        loadasset();
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