package com.example.myidealclass;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @GET("/api/School_Info")
    Call<School> getSchoolInfo();
    @GET("/api/director")
    Call<Director> getDirectorInfo();
    @GET("/api/teachers")
    Call<List<Teacher>> getTeachers();
    @GET("/api/teachers")
    Call<List<Teacher>> getTeachersBySubject(@Query("subjectId") int subjectId);  // Использование query-параметра
    @GET("/api/events")
    Call<List<Event>> getEvents();
    @GET("/api/Important_information")
    Call<List<Important_information>> getImportant_Information();
    @GET("/api/School_asset")
    Call<List<School_asset>> getSchool_asset();
    @GET("/api/Homework")
    Call<List<HomeworkItem>> getHomeworkItem();
    @GET("/api/subjects")
    Call<List<Subject>> getSubjects();
    @GET("/api/scheduleDetailed")
    Call<List<Club>> getClubs(@Query("userId") int userId);


}
