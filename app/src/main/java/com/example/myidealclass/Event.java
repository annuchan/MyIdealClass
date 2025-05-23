package com.example.myidealclass;

import java.util.Date;

public class Event {
    private int id;
    private String title;
    private String describe;
    private String type;
    private Date date;
    private String imageData;

    public Event(int id, String title, String describe, String type, Date date, String imageData) {
        this.id = id;
        this.title = title;
        this.describe = describe;
        this.type = type;
        this.date = date;
        this.imageData = imageData; // Теперь это строка
    }

    // Геттеры
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getDescribe() { return describe; }
    public Date getDate() { return date; }
    public String getImageData() { return imageData; }

    // Сеттеры
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setType(String type) { this.type = type; }
    public void setDescribe(String describe) { this.describe = describe; }
    public void setDate(Date date) { this.date = date; }
    public void setImageData(String imageData) { this.imageData = imageData; }
}

