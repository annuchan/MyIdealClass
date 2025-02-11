package com.example.myidealclass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;

public interface ApiService {
    @GET("users")
    Call<List<Users>> getUsers();

    @POST("users")
    Call<Void> addUser(@Body Users user);
    @GET("/api/School_Info") // Укажите правильный путь к API для информации о школе
    Call<School> getSchoolInfo();

    @GET("/api/director") // Укажите правильный путь к API для информации о директоре
    Call<Director> getDirectorInfo();
    @GET("/api/teachers") // Путь к вашему API для получения списка учителей
    Call<List<Teacher>> getTeachers();
    @GET("/api/events") // Путь к вашему API для получения списка учителей
    Call<List<Event>> getEvents();
    @GET("/api/Important_information") // Путь к вашему API для получения списка учителей
    Call<List<Important_information>> getImportant_Information();
    @GET("/api/School_asset") // Путь к вашему API для получения списка учителей
    Call<List<School_asset>> getSchool_asset();
    @GET("/api/Homework") // Путь к вашему API для получения списка учителей
    Call<List<HomeworkItem>> getHomeworkItem();

}
