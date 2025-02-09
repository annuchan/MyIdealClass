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

public class Teacher_Select_User extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TeacherAdapter adapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_teacher_select_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerViewTeachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();

        loadTeachers();
    }

    private void loadTeachers() {
        Call<List<Teacher>> call = apiService.getTeachers();
        call.enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Teacher> teachers = response.body();
                    Log.d("API_SUCCESS", "Получено " + teachers.size() + " учителей.");
                    adapter = new TeacherAdapter(Teacher_Select_User.this, teachers);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API_ERROR", "Не удалось загрузить список учителей. Код ответа: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Log.e("API_ERROR", "Ошибка загрузки данных: " + t.getMessage());
            }
        });
    }
}