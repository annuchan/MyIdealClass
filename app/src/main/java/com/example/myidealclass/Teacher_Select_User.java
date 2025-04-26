package com.example.myidealclass;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

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
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_select_user);

        recyclerView = findViewById(R.id.recyclerViewTeachers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiService = RetrofitClient.getApiService();
        spinner = findViewById(R.id.my_spinner);

        loadTeachers();  // Загружаем всех учителей

        // Загружаем список предметов для Spinner
        loadSubjects();

        // Обработчик выбора предмета в Spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Subject selectedSubject = (Subject) parentView.getItemAtPosition(position);
                if (selectedSubject != null) {
                    loadTeachersBySubject(selectedSubject.getId());  // Загружаем учителей по выбранному предмету
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Если ничего не выбрано, загружаем всех учителей
                loadTeachers();
            }
        });
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Select_User.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Select_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Select_User.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Teacher_Select_User.this, About_the_app.class); // замените на нужную активность
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

    private void loadSubjects() {
        RetrofitClient.getApiService().getSubjects().enqueue(new Callback<List<Subject>>() {
            @Override
            public void onResponse(Call<List<Subject>> call, Response<List<Subject>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Subject> subjects = response.body();
                    CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(Teacher_Select_User.this, subjects);
                    spinner.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Subject>> call, Throwable t) {
                Log.e("API_ERROR", "Ошибка загрузки данных", t);
            }
        });
    }

    // Загружаем всех учителей
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

    // Загружаем учителей по выбранному предмету
    private void loadTeachersBySubject(int subjectId) {
        Call<List<Teacher>> call = apiService.getTeachersBySubject(subjectId);
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
