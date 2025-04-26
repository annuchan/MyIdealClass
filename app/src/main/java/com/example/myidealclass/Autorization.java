package com.example.myidealclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Autorization extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Button loginButton = findViewById(R.id.moreButton); // Пример кнопки входа
        EditText usernameEditText = findViewById(R.id.loginEditText); // Пример поля ввода логина
        EditText passwordEditText = findViewById(R.id.passwordEditText); // Пример поля ввода пароля
        TextView textView = findViewById(R.id.fogotbutton);
        // Устанавливаем обработчик на кнопку входа
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (!username.isEmpty() && !password.isEmpty()) {
                performLogin(username, password); // Вызов метода авторизации
            } else {
                Toast.makeText(Autorization.this, "Пожалуйста, введите логин и пароль", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.fogotbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Autorization.this, forgot_account.class); // замените на нужную активность
                startActivity(intent);
            }
        });
    }

    private void performLogin(String username, String password) {
        LoginRequest request = new LoginRequest(username, password);
        ApiService apiService = RetrofitClient.getApiService();
        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(Autorization.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    int roleId = loginResponse.getRoleId();
                    Intent intent;

                    // Сохраняем логин, пароль и токен
                    saveCredentials(username, password, loginResponse.getToken());

                    // Сохраняем userId в SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("userId", loginResponse.getUserId());  // Сохраняем userId
                    editor.apply();  // Применяем изменения

                    // Проверяем роль и перенаправляем
                    switch(roleId) {
                        case 1:
                            intent = new Intent(Autorization.this, StartActivity.class);
                            break;
                        case 2:
                            intent = new Intent(Autorization.this, Start_activity_teacher.class);
                            break;
                        case 3:
                            intent = new Intent(Autorization.this, Start_Administration.class);
                            break;
                        default:
                            Toast.makeText(Autorization.this, "Неизвестная роль", Toast.LENGTH_SHORT).show();
                            Log.e("Authorization", "Unknown role ID: " + roleId);
                            return;
                    }

                    intent.putExtra("userId", loginResponse.getUserId());
                    intent.putExtra("token", loginResponse.getToken());
                    startActivity(intent);
                } else {
                    Log.e("Authorization", "Error response: " + response.message());
                    Toast.makeText(Autorization.this, "Ошибка авторизации: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Authorization", "Request failed: " + t.getMessage(), t);
                Toast.makeText(Autorization.this, "Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    // Метод для сохранения логина, пароля и токена
    private void saveCredentials(String username, String password, String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("token", token);
        editor.apply();
    }
}
