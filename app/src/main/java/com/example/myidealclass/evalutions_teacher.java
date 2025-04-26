package com.example.myidealclass;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;

import retrofit2.Call;
import retrofit2.Callback;


public class evalutions_teacher extends AppCompatActivity {
    private Spinner classSpinner;
    private List<String> classList;  // Список для классов
    private ArrayAdapter<String> classAdapter;
    private TextView currentDateTextView;
    private Calendar calendar;
    private ApiService apiService;  // Ссылка на API-сервис
    ImageView detailsImageView = findViewById(R.id.detailsImageView);  // Ищем наш ImageView

    // Устанавливаем слушатель нажатия на ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalutions_teacher);

        classSpinner = findViewById(R.id.classSpinner);
        classList = new ArrayList<>();

        // Настройка адаптера для Spinner
        classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classList);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);

        // Получаем ApiService из RetrofitClient
        apiService = RetrofitClient.getApiService();

        // Загружаем данные о классах
        loadClassData();
        detailsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomMenu(v);  // Показать кастомное меню при нажатии
            }
        });
        currentDateTextView = findViewById(R.id.currentDate);
        calendar = Calendar.getInstance();

        // Настроим DatePickerDialog
        currentDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private void showDatePickerDialog() {
        // Получаем текущую дату
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Создаем DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Устанавливаем выбранную дату в TextView
                        calendar.set(year, monthOfYear, dayOfMonth);
                        updateDateDisplay();
                    }
                },
                year, month, dayOfMonth
        );

        // Показываем календарь
        datePickerDialog.show();
    }

    private void updateDateDisplay() {
        // Форматируем дату в нужном виде
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        String formattedDate = sdf.format(calendar.getTime());
        currentDateTextView.setText(formattedDate);
    }
    private void loadClassData() {
        // Вызов API для получения списка домашних заданий
        Call<List<HomeworkItem>> call = apiService.getHomeworkItem();  // Получаем данные о домашнем задании

        call.enqueue(new Callback<List<HomeworkItem>>() {

            @Override
            public void onResponse(Call<List<HomeworkItem>> call, retrofit2.Response<List<HomeworkItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<HomeworkItem> homeworkItems = response.body();
                    List<String> uniqueClasses = new ArrayList<>();

                    // Извлекаем уникальные классы из списка домашних заданий
                    for (HomeworkItem item : homeworkItems) {
                        String className = item.getClassNumber() + " " + item.getClassIdentifier();  // Номер класса + буква
                        if (!uniqueClasses.contains(className)) {
                            uniqueClasses.add(className);  // Добавляем только уникальные классы
                        }
                    }

                    // Обновляем список классов и адаптер
                    classList.clear();  // Очищаем предыдущие данные
                    classList.addAll(uniqueClasses);
                    classAdapter.notifyDataSetChanged();  // Обновляем Spinner
                } else {
                    Toast.makeText(evalutions_teacher.this, "Не удалось загрузить данные", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HomeworkItem>> call, Throwable t) {
                Toast.makeText(evalutions_teacher.this, "Ошибка сети", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showCustomMenu(View v) {
        // Инфлейтируем кастомный layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View customMenuView = inflater.inflate(R.layout.item_menu_admin, null);

        // Создаем диалог с кастомным меню
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customMenuView);

        // Настроим действия для каждого пункта меню
        TextView addOption = customMenuView.findViewById(R.id.add);
        TextView editOption = customMenuView.findViewById(R.id.edit);
        TextView deleteOption = customMenuView.findViewById(R.id.delete);

        // Действие для пункта "Добавить"
        addOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход в активность для добавления
                Intent intent = new Intent(evalutions_teacher.this, add_homework.class);
                startActivity(intent);
                Toast.makeText(evalutions_teacher.this, "Добавить выбран", Toast.LENGTH_SHORT).show();
            }
        });

        // Действие для пункта "Редактировать"
        editOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход в активность для редактирования
                Intent intent = new Intent(evalutions_teacher.this, edit_homework.class);
                startActivity(intent);
                Toast.makeText(evalutions_teacher.this, "Редактировать выбран", Toast.LENGTH_SHORT).show();
            }
        });

        // Действие для пункта "Удалить"
        deleteOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Показать кастомный MessageBox для подтверждения удаления
                showDeleteConfirmationDialog();
            }
        });

        // Показываем диалог
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDeleteConfirmationDialog() {
        // Создаем AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Инфлейтируем кастомный View
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.warning_delete, null);

        // Устанавливаем кастомное представление в диалог
        builder.setView(customView);

        // Ищем кнопки "Yes" и "No" в кастомном layout
        ImageView yesButton = customView.findViewById(R.id.yesbutton);
        ImageView noButton = customView.findViewById(R.id.nobutton);

        // Создаем AlertDialog
        AlertDialog dialog = builder.create();

        // Обработчик для кнопки "Yes" (удаление)
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Логика удаления элемента
                Toast.makeText(evalutions_teacher.this, "Элемент удален", Toast.LENGTH_SHORT).show();
                dialog.dismiss(); // Закрыть диалог
            }
        });

        // Обработчик для кнопки "No" (отмена)
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Закрыть диалог без удаления
                dialog.dismiss();
            }
        });

        // Показываем диалог
        dialog.show();
    }

}