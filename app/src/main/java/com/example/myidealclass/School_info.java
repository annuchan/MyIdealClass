package com.example.myidealclass;

import static okio.ByteString.decodeBase64;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class School_info extends AppCompatActivity {
    private TextView schoolTitle, schoolAbrev, schoolAdress, schoolPhone;
    private TextView directorFirstName, directorSecondName, directorThirdName;
    private TextView schoolEmail, schoolYear, schoolmissions, schooltask, schooldeyatelnost, schoollanguage, schoollevel, schoolwork, schoolwork2,
            schoolsrok, schoolakredit, schoolpodrazdelenie, schoolfilials, schoolplace, schoolychreditel, schoolformaobycheniya, schoolstudents;
    private ImageView imgview;

    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);
//        Button openpdf1_4 = findViewById(R.id.openpdf1_4);
//        openpdf1_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    // Открываем PDF из assets
//                    InputStream inputStream = getAssets().open("study_chart_1_4_class.pdf");
//                    // Указываем путь для сохранения PDF в память устройства
//                    File outputFile = new File(getExternalFilesDir(null), "study_chart_1_4_class.pdf");
//                    OutputStream outputStream = new FileOutputStream(outputFile);
//
//                    byte[] buffer = new byte[1024];
//                    int length;
//                    while ((length = inputStream.read(buffer)) > 0) {
//                        outputStream.write(buffer, 0, length);
//                    }
//                    outputStream.flush();
//                    outputStream.close();
//                    inputStream.close();
//
//                    // Получаем Uri для файла
//                    Uri uri = FileProvider.getUriForFile(School_info.this, "com.example.myidealclass.fileprovider", outputFile);
//
//                    // Создаем Intent для открытия PDF
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setDataAndType(uri, "application/pdf");
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    startActivity(intent);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    Toast.makeText(School_info.this, "Ошибка при открытии PDF", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        schoolTitle = findViewById(R.id.SchoolTitle);
        schoolAbrev = findViewById(R.id.SchoolAbbreviature);
        schoolAdress = findViewById(R.id.SchoolAdress);
        schoolPhone = findViewById(R.id.SchoolPhone);

        directorFirstName = findViewById(R.id.ditrectorfirstname);
        directorSecondName = findViewById(R.id.ditrectorsecondname);
        directorThirdName = findViewById(R.id.ditrectorthirsdname);

        schoolEmail = findViewById(R.id.schoolemail);
        schoolYear = findViewById(R.id.schoolyear);
        schoolmissions = findViewById(R.id.schoolmissions);
        schooltask = findViewById(R.id.schooltask);
        schooldeyatelnost = findViewById(R.id.schooldeyatelnost);
        schoollanguage = findViewById(R.id.schoollanguage);
        schoollevel = findViewById(R.id.schoollevel);
        schoolwork = findViewById(R.id.schoolwork);
        schoolwork2 = findViewById(R.id.schoolwork2);
        schoolsrok = findViewById(R.id.schoolsrok);
        schoolakredit = findViewById(R.id.schoolakredit);
        schoolpodrazdelenie = findViewById(R.id.schoolpodrazdelenie);
        schoolfilials = findViewById(R.id.schoolfilials);
        schoolplace = findViewById(R.id.schoolplace);
        schoolychreditel = findViewById(R.id.schoolychreditel);
        schoolformaobycheniya = findViewById(R.id.schoolformaobycheniya);
        schoolstudents = findViewById(R.id.schoolstudents);
        imgview = findViewById(R.id.directorimg);

        loadDirectorData();
        NoActionBar.hideActionBar(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.supportbutton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_info.this, Support.class); // замените на нужную активность
                startActivity(intent);
            }
        });

        findViewById(R.id.startactivity2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_info.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.startactivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_info.this, StartActivity.class); // замените на нужную активность
                startActivity(intent);
            }
        });
        findViewById(R.id.about_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(School_info.this, About_the_app.class); // замените на нужную активность
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
    private void loadDirectorData() {
        // Выполняем запрос к API для данных школы
        Call<School> call = apiService.getSchoolInfo();
        call.enqueue(new Callback<School>() {
            @Override
            public void onResponse(Call<School> call, Response<School> response) {
                if (response.isSuccessful() && response.body() != null) {
                    School schoolInfo = response.body();

                    // Устанавливаем данные в TextView
                    schoolTitle.setText(schoolInfo.getTitle());
                    schoolAbrev.setText(schoolInfo.getAbbreviation());
                    schoolAdress.setText(schoolInfo.getAdress_School());
                    schoolPhone.setText(schoolInfo.getPhone_School());
                    schoolEmail.setText(schoolInfo.getEmail_School());
                    schoolYear.setText(schoolInfo.getDate_of_creation() + "год");
                    schoolmissions.setText(schoolInfo.getMission_School());
                    schooltask.setText(schoolInfo.getTask_School());
                    schooldeyatelnost.setText(schoolInfo.getActivity_School());
                    schoollanguage.setText(schoolInfo.getLanguage_Study());
                    schoollevel.setText(schoolInfo.getEducation_level());
                    schoolwork.setText(schoolInfo.getOperating_mode());
                    schoolwork2.setText(schoolInfo.getWork_schedule());
                    schoolsrok.setText(String.valueOf(schoolInfo.getDuration_training()));
                    schoolakredit.setText(schoolInfo.getState_accreditation());
                    schoolpodrazdelenie.setText(schoolInfo.getStructure_podr());
                    schoolfilials.setText(schoolInfo.getDivisions());
                    schoolplace.setText(schoolInfo.getPlace());
                    schoolychreditel.setText(schoolInfo.getFounder());
                    schoolformaobycheniya.setText(schoolInfo.getForm_Education());
                    schoolstudents.setText(schoolInfo.getHow_Student() + "человек");
                    // Заполняем остальные данные аналогично
                }
            }

            @Override
            public void onFailure(Call<School> call, Throwable t) {
                Log.e("SchoolInfo", "Ошибка загрузки данных: " + t.getMessage());
            }
        });

        // Выполняем запрос к API для данных о директоре
        Call<Director> call1 = apiService.getDirectorInfo();
        call1.enqueue(new Callback<Director>() {
            @Override
            public void onResponse(Call<Director> call, Response<Director> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Director director = response.body();

                    // Убедитесь, что эти методы возвращают строки
                    directorFirstName.setText(director.getFirstName());  // Имя
                    directorSecondName.setText(director.getLastName());  // Фамилия
                    directorThirdName.setText(director.getMiddleName());

                    // Преобразуем Base64 строку в Bitmap и устанавливаем в ImageView
                    String base64Image = director.getImageData();
                    if (base64Image != null && !base64Image.isEmpty()) {
                        try {
                            byte[] decodedBytes = Base64.decode(base64Image, Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                            imgview.setImageBitmap(bitmap);
                        } catch (IllegalArgumentException e) {
                            Log.e("DirectorInfo", "Некорректная строка Base64", e);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Director> call, Throwable t) {
                Log.e("DirectorInfo", "Ошибка загрузки данных: " + t.getMessage());
            }
        });
    }
}