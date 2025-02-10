package com.example.myidealclass;

import java.util.Date;

public class Important_information {private int id;
    private String title;
    private String describe;
    private String term;
    private Date date;
    private String imageData;

    public Important_information(int id, String title, String describe, String term, Date date, String imageData) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.term = term;
        this.date = date;
        this.imageData = imageData; // Теперь это строка
    }

    // Геттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getTerm() { return term; }
    public String getDescribe() { return describe; }
    public Date getDate() { return date; }
    public String getImageData() { return imageData; }

    // Сеттеры
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setTerm(String type) { this.term = type; }
    public void setDescribe(String describe) { this.describe = describe; }
    public void setDate(Date date) { this.date = date; }
    public void setImageData(String imageData) { this.imageData = imageData; }
}
