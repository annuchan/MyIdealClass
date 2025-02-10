package com.example.myidealclass;

import java.util.Date;

public class School_asset {
    private int id;
    private String title;
    private String describe;
    private String place;
    private String firstName;
    private String secondname;
    private String thirsdname;
    private String imageData;

    public School_asset(int id, String title, String describe, String place, Date firstName, String secondname, String thirsdname, String imageData) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.place = place;
        this.firstName = String.valueOf(firstName);
        this.secondname = secondname;
        this.thirsdname = thirsdname;
        this.imageData = imageData; // Теперь это строка
    }

    // Геттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getPlace() { return place; }
    public String getfirstName() { return firstName; }
    public String getsecondname() { return secondname; }
    public String getthirsdname() { return thirsdname; }
    public String getDescribe() { return describe; }
    public String getImageData() { return imageData; }

    // Сеттеры
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setplace(String place) { this.place = place; }
    public void setfirstName(String firstName) { this.firstName = firstName; }
    public void setsecondname(String secondname) { this.secondname = secondname; }
    public void setthirsdname(String thirsdname) { this.thirsdname = thirsdname; }
    public void setDescribe(String describe) { this.describe = describe; }
    public void setImageData(String imageData) { this.imageData = imageData; }
}


