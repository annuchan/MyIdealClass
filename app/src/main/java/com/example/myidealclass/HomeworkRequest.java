package com.example.myidealclass;

public class HomeworkRequest {
    private String task;
    private String dateHomework;
    private int idSubject;
    private int idClass;
    private int id_teacher;

    public HomeworkRequest(String task, String dateHomework, int idSubject, int idClass, int id_teacher) {
        this.task = task;
        this.dateHomework = dateHomework;
        this.idSubject = idSubject;
        this.idClass = idClass;
        this.id_teacher = id_teacher;
    }
}

