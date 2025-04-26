package com.example.myidealclass;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class add_homework extends AppCompatActivity {

    private EditText taskEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);

        taskEditText = findViewById(R.id.editTextTask);
        saveButton = findViewById(R.id.moreButton);

        saveButton.setOnClickListener(v -> {
            String task = taskEditText.getText().toString().trim();

            if (task.isEmpty()) {
                Toast.makeText(this, "Введите текст задания", Toast.LENGTH_SHORT).show();
                return;
            }

            String dateHomework = getIntent().getStringExtra("selectedDate");
            int idClass = getIntent().getIntExtra("selectedClassId", -1);
            int idSubject = getIntent().getIntExtra("selectedSubjectId", -1);
            int idTeacher = getIntent().getIntExtra("teacherId", -1);

            if (dateHomework == null || idClass == -1 || idSubject == -1 || idTeacher == -1) {
                Toast.makeText(this, "Недостаточно данных для отправки", Toast.LENGTH_LONG).show();
                return;
            }

            HomeworkRequest request = new HomeworkRequest(task, dateHomework, idSubject, idClass, idTeacher);

            ApiService apiService = RetrofitClient.getApiService();
            Call<ResponseBody> call = apiService.addHomework(request);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(add_homework.this, "Домашнее задание добавлено", Toast.LENGTH_SHORT).show();
                        finish(); // Закрыть экран
                    } else {
                        Toast.makeText(add_homework.this, "Ошибка при добавлении", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(add_homework.this, "Сбой: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
